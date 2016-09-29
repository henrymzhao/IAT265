import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class FishPanel extends JPanel implements MouseListener
{
	public final static int WIN_H = 1080;
	public final static int WIN_W = 1920;
	
	private Fish fish;
	
	public FishPanel()
	{
		super();
		this.setPreferredSize(new Dimension(WIN_W,WIN_H));
//		TODO awaiting constructor
		fish = new Fish(0,200);
		Color sky = Color.decode("#1FBED6");
		this.setBackground(sky);
		
		addMouseListener(this);
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
		drawPond(g);
//		drawGrids(g);
		
		g.translate(WIN_W/2, WIN_H/2);
		fish.drawFish(g);
		g.translate(-WIN_W/2, -WIN_H/2);
	}
	
	private void drawRocks(Graphics g, int x, int y)
	{
		g.setColor(Color.gray);
		g.fillOval(x, y, 50, 25);
	}
	
	private void drawPond(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillRect(10, WIN_H*1/2, WIN_W-20, WIN_H/2-10);
		g.setColor(Color.cyan);
		g.fillOval(10, WIN_H*1/2, WIN_W-20, WIN_H/4);
		g.fillRect(10, WIN_H*1/2+(WIN_H/4)/2, WIN_W-20, (WIN_H*3/4)/2-10);
		
//		rocks
		drawRocks(g, 100, WIN_H-100);
		drawRocks(g, 901, 1012);
		drawRocks(g, 1165, 930);
		drawRocks(g, 1467, 978);
		drawRocks(g, 1620, 898);
		drawRocks(g, 1779, 979);
		drawRocks(g, 1004, 904);
		drawRocks(g, 575, 907);
		drawRocks(g, 390, 996);
		drawRocks(g, 288, 921);
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
		
//		System.out.println(e.getX());
//		System.out.println(e.getY());
//		System.out.println("\n");
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
