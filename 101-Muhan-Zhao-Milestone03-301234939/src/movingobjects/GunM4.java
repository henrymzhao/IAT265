package movingobjects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class GunM4 extends MovingObjects
{
	private BufferedImage overlay;
	private GunM4Pin pin;
	private Rectangle2D.Double magDetection;
	private Magazine currMag;
	private boolean gunLoaded;
	private Bullet bullet;
	private GunM4Trigger trigger;
	
	private double jerkX;
	private double jerkTimer = 0;
	private BufferedImage muzzleFlash;
	private boolean flash;
	
	public GunM4(double posx, double posy, GunM4Pin pin, GunM4Trigger trigger) {
		super(posx, posy);
		// TODO Auto-generated constructor stub
		try {
			avatar = ImageIO.read(getClass().getResourceAsStream("/graphics/GunBase.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			overlay = ImageIO.read(getClass().getResourceAsStream("/graphics/GunOverlay.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			muzzleFlash = ImageIO.read(getClass().getResourceAsStream("/graphics/muzzleFlash.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		width = 2856*0.4;
		height = 746*0.4;
		this.pin = pin;
		this.trigger = trigger;
		magDetection = new Rectangle2D.Double();
		magDetection.setFrame((posx+width*0.04-(width*0.08)/2), 
				(posy+height*0.05-(height*0.2)/2), 
				(width*0.08), (height*0.2));
		gunLoaded = false;
		currMag = null;
		bullet = new Bullet(994-80, 433-5, 1);
		bullet.setAngle(Math.toDegrees(-90));

		jerkX=0;
		flash = false;
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		old = g2.getTransform();
		oldC = g2.getComposite();
//		g2.translate(250,0);
		if (System.currentTimeMillis() - jerkTimer >= 250/3 && jerkTimer != 0)
		{
			if (currMag != null)
			{
				currMag.setPos(currMag.getX()-50, currMag.getY());
			}
			posx-=50;
			jerkTimer = 0;
			flash = false;
		}
		opacity = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, initialOpacity);
		g2.setComposite(opacity);
		

		g2.drawImage(avatar, (int)(posx-width/2), (int) (posy-height/2), (int) width, (int) height, null);
		if (currMag != null && currMag.isEmpty() == false)
		{
			bullet.draw(g2);			
		}
		pin.draw(g2);
		trigger.draw(g2);
		if (flash)
		{
			g2.drawImage(muzzleFlash, (int) posx-640-450, (int) posy-480/2-55, 640, 480, null);
		}
		g2.drawImage(overlay, (int)(posx-width/2), (int) (posy-height/2), (int) width, (int) height, null);
		
//		if (currMag != null && currMag.isEmpty())
//		{
//			eject();
//		}
		appear();
//		g2.setColor(Color.red);
//		g2.fill(magDetection);
		
		g2.setComposite(oldC);
		g2.setTransform(old);
	}
	
	public boolean isLoaded()
	{
		if (currMag == null)
		{
			return false;
		}
		return true;
	}
	
	public boolean reloadDetection(Magazine mag)
	{
//		if (Math.abs(magDetection.getX()+magDetection.getWidth()/2-mag.getX()) <= magDetection.getWidth()/2 + mag.getW()/2 
//				&& Math.abs(magDetection.getY()+magDetection.getHeight()/2-mag.getY()) <= magDetection.getHeight()/2 + mag.getH()/2)
//		{
//			return true;
//		}
		if (magDetection.intersects(mag.getOpenSlot()))
		{
			currMag = mag;
			return true;
		}
		return false;
	}
	
	private boolean reload()
	{
		System.out.println("reloading");

		if (currMag != null && currMag.isEmpty() == false)
		{

			System.out.println("reloaded");
			currMag.useBullet();
			
			gunLoaded = true;
			
			return true;
		}

		System.out.println("failed to reload");
		return false;
	}
	
	public boolean fire()
	{
		if (gunLoaded == false)
		{
			System.out.println("no bullet");
			return false;
		}
		else
		{
			if (currMag != null)
			{
				currMag.setPos(currMag.getX()+50, currMag.getY());
			}
			posx += 50;
			jerkTimer = System.currentTimeMillis();
			System.out.println("fired");
			gunLoaded = false;
			reload();
			jerkX = 0;
			flash = true;
			return true;
		}
	}
	
	public void manualReload()
	{
		if (currMag != null && currMag.isEmpty() == false)
		{
			if (gunLoaded == false)
			{

				System.out.println("manual reloaded");
				currMag.useBullet();
				gunLoaded = true;	
			}
		}
	}
	
	public void eject()
	{
		currMag.setEject();
		currMag = null;
	}
}
	