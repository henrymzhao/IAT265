import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

public class BearPanel extends JPanel implements MouseListener
{
	private Bear bear;
	
	public BearPanel()
	{
//		call JPanel's default constructor
		super();
		
		bear = new Bear();
		
//		set panel size
		this.setPreferredSize(new Dimension(600, 600));
		this.setBackground(Color.white);
		
//		add a MouseListener to the current panel
		addMouseListener(this);
	}
	
//	semi call back
	public void paintComponent(Graphics g)
	{
//		call JPanel's paintComponent
		super.paintComponent(g);
		
		bear.drawBear(g);
	}
	
	public void mouseCLicked(MouseEvent e)
	{
		
	}
	public void mouseEntered(MouseEvent e)
	{
		
	}
	public void mouseExited(MouseEvent e)
	{
		
	}
	public void mousePressed(MouseEvent e)
	{
//		get mouse current pos
		int x = e.getX();
		int y = e.getY();
		int s = bear.getScale();
		
//		detect hit
		if(bear.checkPointHit(x,y))
		{
//			change scale and color
			bear.setScale(s+1);
			bear.setBearColor(Color.blue);
		}
//		call the paintComponent
		repaint();
	}
	public void mouseReleased(MouseEvent e)
	{
//		get mouse current pos
		int x = e.getX();
		int y = e.getY();
		int s = bear.getScale();
		
//		detect hit
		if(bear.checkPointHit(x,y))
		{
//			change scale and color back
			bear.setScale(s-1);
			bear.setBearColor(Color.red);
		}
//		call the paintComponent
		repaint();		
	}
}
