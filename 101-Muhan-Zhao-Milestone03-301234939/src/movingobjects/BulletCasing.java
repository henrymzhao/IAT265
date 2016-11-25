package movingobjects;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GunRangePanel;

public class BulletCasing extends MovingObjects
{
	private double x, y, randomness;
	public boolean delete;
	public BulletCasing(double posx, double posy) {
		super(posx, posy);
		// TODO Auto-generated constructor stub
		try {
			avatar = ImageIO.read(getClass().getResourceAsStream("/graphics/casing.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		width = 50*0.4;
		height = 200*0.4;
		angle = Math.toRadians(-90);

		x = 0;
		y = -(x*x)+3*x;
		randomness = 5 + Math.random() * (10 - 5);
		delete = false;
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		update();
		AffineTransform old = g2.getTransform();
		g2.translate(posx, posy);
		g2.rotate(angle);
		g2.drawImage(avatar, (int) -width/2, (int) -height/2, (int) width, (int) height, null);
		g2.setTransform(old);
	}
	
	public void update()
	{
		x ++;
		

//		y = -(x*x)+randomness*x;
		y = -(x*x)+8*x;

		angle++;
		
		posx += x;
		posy -= y;
		
		if (posy+100 > GunRangePanel.WIN_H)
		{
			delete = true;
		}
	}

}