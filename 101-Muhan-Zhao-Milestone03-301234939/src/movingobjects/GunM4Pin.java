package movingobjects;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GunM4Pin extends MovingObjects
{
	
	public GunM4Pin(double posx, double posy) {
		super(posx, posy);
//		994.0
//		433.0
		// TODO Auto-generated constructor stub
		width = 594*0.4;
		height = 57*0.4;
		try {
			avatar = ImageIO.read(getClass().getResourceAsStream("/graphics/reloadTrigger.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.drawImage(avatar, (int) (posx-width/2), (int) (posy-height/2), (int) width, (int) height, null);
	}
}