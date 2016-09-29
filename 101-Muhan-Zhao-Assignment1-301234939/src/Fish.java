import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Fish 
{
	private int x, y;
	private int myWidth, myHeight;
	private double speed, scale;
	private Color fins;
	
	public Fish(int x, int y)
	{
		this.x = x;
		this.y = y;
		myWidth = 200;
		myHeight = 100;
		scale = 1;
		fins = Color.red;
	}
	
	private void centerFillOval(int _x, int _y, int w, int h, Graphics g)
	{
//		centers a circle around x, y
		
		g.fillOval(_x-w/2, _y-h/2, w, h);
	}
	
	public void drawFish(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.scale(scale, scale);
		g.setColor(Color.blue);
		
//		g.fillOval(x-myWidth/2, y-myHeight/2, myWidth, myHeight);
		
//		fins upper
		g2.setColor(fins);
		int[] fx1 = {x+-25, x+0, x+25};
		int[] fy1 = {y+-45, y+-95, y+-45};
		g2.fillPolygon(fx1, fy1, 3);
		
//		fins lower left		
		AffineTransform old = g2.getTransform();
		g2.rotate(Math.toRadians(25/2));
		int[] fx2 = {x+25, x, x-25};
		int[] fy2 = {y+45, y+95, y+45};
		g2.fillPolygon(fx2, fy2, 3);
		g2.setTransform(old);
		
		
//		fins lower right
		old = g2.getTransform();
		g2.rotate(Math.toRadians(-25/2));
		int[] fx3 = {x-25, x, x+25};
		int[] fy3 = {y+45, y+95, y+45};
		g2.fillPolygon(fx3, fy3, 3);
		g2.setTransform(old);
		
//		tail
		g.setColor(fins);
		int[] tx = {x+(-150),x+(-50),x+(-150)};
		int[] ty = {y+50,y+0,y+(-50)};
		g.fillPolygon(tx, ty, 3);
		
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(x-150, y, x-150+30, y);
		g2.drawLine(x-150, y-10, x-150+30, y-10);
		g2.drawLine(x-150, y+10, x-150+30, y+10);
		
//		body
//		g.fillOval(x-myWidth/2, y-myHeight/2, 200, 100);
		g2.setColor(Color.gray);
//		centerFillOval(x, y, 200, 100, g);
		Ellipse2D.Double fishBody = new Ellipse2D.Double(x-200/2, y-100/2, 200, 100);
		g2.fill(fishBody);
		
//		decoration
		g.setColor(Color.white);
		int fishBodyBoundaryLeft = x - 200/2;
		int fishBodyBoundaryUp = y-100/2;
		int fishBodyBoundaryRight = 200 + fishBodyBoundaryLeft;
		int fishBodyBoundaryDown = 100 + fishBodyBoundaryUp;
		
		for (int i = fishBodyBoundaryLeft; i < fishBodyBoundaryRight; i += 10)
		{
			for (int j = fishBodyBoundaryUp; j < fishBodyBoundaryDown; j += 10)
			{
				
				if (fishBody.contains(i,j))
				{
					centerFillOval(i, j, 4, 4, g);
				}
			}
		}
		
//		eyes
		g.setColor(Color.black);
		centerFillOval(x+50,y+-25/2,25,25,g);
		
//		gills
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawArc(x+10, y+-50/2, 50/2, 50, 90, 180);		
	}
}
