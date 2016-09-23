import java.awt.*;

public class Fish 
{
	private int posX, posY;
	private int myWidth, myHeight;
	private int speed, scale;
	
	public Fish(int x, int y)
	{
		posX = x;
		posY = y;
		myWidth = 100;
		myHeight = 100;
	}
	
	private void centerFillOval(int x, int y, int w, int h, Graphics g)
	{
//		centers a circle around x, y
		
		g.fillOval(x-w/2, y-h/2, w, h);
	}
	
	public void drawFish(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.blue);
		
//		g.fillOval(posX-myWidth/2, posY-myHeight/2, myWidth, myHeight);
		
//		body
//		g.fillOval(posX-myWidth/2, posY-myHeight/2, 200, 100);
		centerFillOval(posX, posY, 200, 100, g);
		
//		tail
		int[] x = {-150,-50,-150};
		int[] y = {50,0,-50};
		g.fillPolygon(x, y, 3);
		
//		eyes
		g.setColor(Color.black);
		centerFillOval(50,-25/2,25,25,g);
		
//		gills
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawArc(25, -75/2, 75, 75, 90, 180);
		
	}
}