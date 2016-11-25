package main;

import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import factory.BulletConcreteFactory;
import movingobjects.*;

import java.awt.*;

public class GunRangePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
	public static final int WIN_H = 972;
	public static final int WIN_W = 1728;
	public static final double PUMPX = 622;
	private JFrame frame;
	private BufferedImage bg;
	private Timer timer;
	private Pointer pointer;
	private MovingObjects held;
	private Magazine mag;
	private ArrayList <Bullet> ammo = new ArrayList<Bullet>();
	
	private JButton spawnAmmo;
	
	private int spawnAmmoTimer;
	
	private MovingObjects rifle;
	private SGPump pump;
	private GunM4Pin pin;
	private GunM4Trigger trigger;
	public ArrayList<BulletCasing> casings = new ArrayList<BulletCasing>();
	private JButton spawnM4;
	private JButton spawnSG;
	
//	new
	BulletConcreteFactory bullets;
	
	public GunRangePanel(JFrame frame)
	{
		super();
		this.frame = frame;
		this.setPreferredSize(new Dimension(WIN_W, WIN_H));
		setLayout(new FlowLayout());
		try {
			bg = ImageIO.read(getClass().getResourceAsStream("/graphics/background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMouseListener(this);
		addMouseMotionListener(this);
		timer = new Timer(60, this);
		timer.start();
		
		pointer = new Pointer(0,0);
		
//		BUTTONS
		spawnAmmo = new JButton("Spawn Ammo");
		add(spawnAmmo);
		spawnAmmo.addActionListener(this);
		
		spawnAmmoTimer = 50;
		
		bullets = new BulletConcreteFactory();
		spawnM4 = new JButton("Spawn M4");
		add(spawnM4);
		spawnM4.addActionListener(this);
		
		spawnSG = new JButton("Spawn Shot Gun");
		add(spawnSG);
		spawnSG.addActionListener(this);
	}
	
	private void generateM4()
	{
		pin = new GunM4Pin(994,433);
		trigger = new GunM4Trigger(1003.0, 490.0);
		rifle = new GunM4(WIN_W/2, WIN_H/2, pin, trigger);
		mag = new Magazine(WIN_W/2-300, WIN_H/2);
	}
	
	private void generateSG()
	{
		pump = new SGPump(622, 428);
		rifle = new ShotGun(WIN_W/2, WIN_H/2, pump);
	}
	
	private void generateAmmo()
	{
		int spawnTime = 60;
		if (rifle instanceof GunM4)
		{
			for (int i = 0; i < 5; i++)
			{
				ammo.add((Bullet) bullets.createBullet("bullet", i, 1));
			}
		}
		else if (rifle instanceof ShotGun)
		{
			for (int i = 0; i < 5; i++)
			{
				ammo.add((Bullet) bullets.createBullet("bullet", i, 2));
			}
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
//		draw background
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(bg, 0,0,WIN_W, WIN_H, null);
		for (int i = 0; i < ammo.size(); i++)
		{
			ammo.get(i).draw(g2);
			if (mag != null)
			{
				if (ammo.get(i).handleCollision(mag) && ammo.get(i) != held)
				{
					mag.reload();
					ammo.remove(i);
				}
			}
		}
		if (mag != null)
		{
			mag.draw(g2);
		}
		
		if (rifle != null)
		{
			rifle.draw(g2);			
		}
		for (int i = 0; i < casings.size(); i++)
		{
			if (casings.get(i).delete == true)
			{
				casings.remove(i);
			}
			else
			{
				casings.get(i).draw(g2);
			}
		}
		if (rifle != null && !(rifle instanceof GunM4))
			mag = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "Spawn Ammo")
		{
			generateAmmo();
		}
		if (e.getActionCommand() == "Spawn M4")
		{
			generateM4();
		}
		if (e.getActionCommand() == "Spawn Shot Gun")
		{
			generateSG();
		}
//		for (int i = 0; i < casings.size(); i++)
//		{
//			casings.get(i).update();
//		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
//		LOADING/DRAGGING THE BULLET
		for (int i = 0; i < ammo.size(); i++)
		{
			if(ammo.get(i).handleCollision(pointer))
			{
				held = ammo.get(i);
			}
		}
		if (mag != null)
		{
			if(mag.handleCollision(pointer) && mag.isLoaded() == false)
			{
				held = mag;
			}
			if(mag.handleCollision(pointer) && mag.isLoaded() == true)
			{
				if (rifle instanceof GunM4)
					((GunM4) rifle).eject();
			}
		}
		if (pin != null)
		{
			if(pin.handleCollision(pointer))
			{
				pointer.setOldX(e.getX());
				pointer.setOldY(e.getY());
				pin.setOldX(pin.getX());
				pin.setOldY(pin.getY());
				held = pin;
			}
		}
		if (trigger != null)
		{
			if(trigger.handleCollision(pointer))
			{
				trigger.fire();
				if (rifle instanceof GunM4)
				{
					if(((GunM4) rifle).fire())
					{
						casings.add((BulletCasing) bullets.createBullet("casing", 0, 0));
					}
				}
			}
		}
		if (pump != null)
		{
			if (pump.handleCollision(pointer))
			{
				pointer.setOldX(e.getX());
				pointer.setOldY(e.getY());
//				pump.setOldX(pump.getX());
//				pump.setOldY(pump.getY());
				held = pump;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (held instanceof Bullet)
		{
			if (mag != null)
			{
				if (held.handleCollision(mag) == false)
				{
					held.setAngle(0);				
				}
			}
		}
		if (held instanceof GunM4Pin)
		{
//			snap back effect
			GunM4Pin heldTrigger = (GunM4Pin) held;
			heldTrigger.setPos(heldTrigger.getOldX(), heldTrigger.getOldY());
		}
		if (held instanceof SGPump)
		{
			pump.setOldX(pump.getX());
		}
		held = null;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		pointer.setPos(arg0.getX(), arg0.getY());
		if (held != null)
		{
			if (held instanceof GunM4Pin)
			{
				GunM4Pin heldTrigger = (GunM4Pin) held;
				if (heldTrigger.getX() >= heldTrigger.getOldX() && heldTrigger.getX() < heldTrigger.getOldX() + 100)
					heldTrigger.setPos(
						heldTrigger.getOldX()+(pointer.getX()-pointer.getOldX()), heldTrigger.getY());
				if (heldTrigger.getX() > heldTrigger.getOldX() + 100)
					heldTrigger.setPos(heldTrigger.getOldX()+100, heldTrigger.getOldY());
				if (((GunM4) rifle).isLoaded() == true && heldTrigger.getX() >= heldTrigger.getOldX() + 90)
				{
					((GunM4) rifle).manualReload();
				}
			}
			else if (held instanceof SGPump)
			{
//				if (held.getX() >= held.getOldX() && held.getX() < held.getOldX() + 100)
//					held.setPos(held.getOldX()+(pointer.getX()-pointer.getOldX()), held.getY());
//				if (held.getX() > held.getOldX() + 100)
//					held.setPos(held.getOldX()+100, held.getOldY());
				if (held.getX() >= PUMPX && held.getX() <= PUMPX + 100)
				{
					held.setPos(held.getOldX() + (pointer.getX()-pointer.getOldX()), held.getY());
				}
//				CHECK NOT OUT OF BOUNDS
				if (held.getX() > PUMPX + 100)
				{
					held.setPos(PUMPX+100, held.getY());
				}
				if (held.getX() < PUMPX)
				{
					held.setPos(PUMPX, held.getY());
				}
				
				
				if (held.getX() >= PUMPX + 90)
				{
//					((ShotGun) rifle).manualReload();
					((ShotGun) rifle).setGunPumped(true);
				}
				if (held.getX() == PUMPX && ((ShotGun) rifle).isGunPumped() && ((ShotGun) rifle).isChambered())
				{
					((ShotGun) rifle).manualReload();
				}
			}
			else
			{
				held.setPos(pointer.getX(), pointer.getY());
				if (held instanceof Bullet)
				{
					((Bullet) held).handleProximity(mag);
				}
				else if(held instanceof Magazine)
				{
					if (((GunM4) rifle).reloadDetection(mag))
					{
						mag.setLoaded(true);
						mag.setPos(904.0,555.0);
						held = null;
					}
				}
			}
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub	
		pointer.setPos(arg0.getX(), arg0.getY());
	}
}