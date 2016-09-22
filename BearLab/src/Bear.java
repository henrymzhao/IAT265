import java.awt.Color;
import java.awt.*;

public class Bear 
{
	private static final int WIN_WIDTH = 600;
	private static final int WIN_HEIGHT = 600;	
	
	private int xPos;
	private int yPos;
	private int scale = 2;
	private int bearW = scale * 30;
	private int bearH = scale * 70;
	
	private Color bearColor = Color.red;
	private Color outlineColor = Color.black;
	
	public Bear()
	{
		xPos = WIN_WIDTH/2;
		yPos = WIN_HEIGHT/2;
	}
	
	public void drawBear(Graphics g)
	{
//		legs
		g.setColor(bearColor);
		g.fillOval(xPos-scale*35, yPos+scale*20, scale*70, scale*70);
		g.setColor(outlineColor);
		g.drawOval(xPos-scale*35, yPos+scale*20, scale*70, scale*70);
		
//		body
		g.setColor(bearColor);
		g.fillOval(xPos-scale*20, yPos-scale*40, scale*40, scale*80);
		g.setColor(outlineColor);
		g.drawOval(xPos-scale*20, yPos-scale*40, scale*40, scale*80);
		
//		head
		g.setColor(bearColor);
		g.fillOval(xPos-scale*20, yPos-scale*50, scale*40, scale*40);
		g.setColor(outlineColor);
		g.drawOval(xPos-scale*20, yPos-scale*50, scale*40, scale*40);				
	}
	
//	setter
	public void setBearColor(Color c)
	{
		bearColor = c;
	}
	
//	setter for the scale
	public void setScale(int s)
	{
		scale = s;
	}
	
//	getter
	public int getScale()
	{
		return scale;
	}
	
//	check if a point is on the bear
	public boolean checkPointHit(int x, int y)
	{
		boolean hit = false;
		
		if(x > xPos - bearW/2 && x < xPos + bearW/2 && y > yPos - bearH/2 && y < yPos + bearH/2)
		{
			hit = true;
		}
		
		return hit;
	}
}
