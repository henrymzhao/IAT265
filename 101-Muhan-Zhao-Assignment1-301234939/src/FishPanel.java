import java.awt.*;
import javax.swing.*;

public class FishPanel extends JPanel
{
	public final static int WIN_H = 600;
	public final static int WIN_W = 800;
	
	private Fish fish;
	
	public FishPanel()
	{
		super();
		this.setPreferredSize(new Dimension(WIN_W,WIN_H));
//		TODO awaiting constructor
		fish = new Fish(0,0);
		this.setBackground(Color.white);
	}
	
	private void drawGrids(Graphics g)
	{
//		draws some grids for ease of measurement
		g.setColor(Color.gray);
		int i = 0;
		for (i = 0; i < WIN_W; i+=50)
		{
			g.drawLine(i, 0, i, WIN_H);
		}
		i = 0;
		for (i = 0; i < WIN_H; i+=50)
		{
			g.drawLine(0, i, WIN_W, i);
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
//		DRAWING GRIDS
		drawGrids(g);
		
		g.translate(WIN_W/2, WIN_H/2);
		fish.drawFish(g);
		g.translate(-WIN_W/2, -WIN_H/2);
	}
}