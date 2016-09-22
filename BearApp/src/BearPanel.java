

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;




public class BearPanel extends JPanel implements MouseListener {
	public static final int WIN_WIDTH = 600;
	public static final int WIN_HEIGHT = 600;
	private Bear bear;

	public BearPanel() {
		super();
		this.setPreferredSize(new Dimension(WIN_WIDTH , WIN_HEIGHT));

		bear = new Bear();

		this.setBackground(Color.white);

		addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		bear.drawBear(g);

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
		int x = e.getX();
		int y = e.getY();
		int currentScale = bear.getScale();
		if (bear.checkPointHit(x, y)) {
			bear.setScale(currentScale + 1);
			bear.setColor(new Color(139, 99, 55));
		}
		
		repaint();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		int currentScale = bear.getScale();
		if (bear.checkPointHit(x, y)) {
			bear.setScale(currentScale - 1);
			bear.setColor(bear.getDefaultColor());
		}
		
		repaint();


	}


}
