package movingobjects;

import java.awt.Graphics2D;

/* POINTER class exists for the convenience of extending default MovingObjects methods to the mouse cursor, 
 * 	specifically the collision method to make collision detection with objects simpler. 
 * CREATED: 11/11/2016
 * AUTHOR: HENRY ZHAO*/
public class Pointer extends MovingObjects
{	
	/* WHAT IT DOES: Constructor for the Pointer class 
	 * PARAMETERS: 
	 * 	posx - position x
	 * 	posy - position y
	 * RETURN: NONE*/
	public Pointer(double posx, double posy) {
		super(posx, posy);
		// TODO Auto-generated constructor stub
		width = 25;
		height = 25;
	}

	/* WHAT IT DOES: Nothing. Useless inherited method from MovingObjects as cursor is drawn by default 
	 * PARAMETERS: NONE
	 * RETURN: NONE*/
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}

	/* WHAT IT DOES: Getter method for the variable OldX 
	 * PARAMETERS: NONE
	 * RETURN: The value of OldX*/
	public double getOldX() {
		return oldX;
	}

	/* WHAT IT DOES: Setter method for variable OldX 
	 * PARAMETERS: 
	 * 	oldX - the desired position for oldX, when the mouse was first clicked
	 * RETURN: NONE*/
	public void setOldX(double oldX) {
		this.oldX = oldX;
	}

	/* WHAT IT DOES: Getter method for variable OldY 
	 * PARAMETERS: NONE
	 * RETURN: The value of OldY*/
	public double getOldY() {
		return oldY;
	}

	/* WHAT IT DOES: Setter method for variable oldY 
	 * PARAMETERS: 
	 * 	oldY - Desired OldY position, when the mouse was first clicked
	 * RETURN: NONE*/
	public void setOldY(double oldY) {
		this.oldY = oldY;
	}

}
