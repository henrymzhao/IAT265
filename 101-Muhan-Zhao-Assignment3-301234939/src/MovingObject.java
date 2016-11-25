
public class MovingObject {
	
	protected double posx;
	protected double posy;
	protected double myWidth;
	protected double offset;
	protected double lastCollision;
	protected double myHeight;
	
	MovingObject(double x, double y)
	{
		posx = x;
		posy = y;
		lastCollision = 0;
	}

	boolean handleCollision(MovingObject target)
	{
		if ((System.currentTimeMillis()-lastCollision) >= 500)
		{
			if (Math.abs(posx - target.posx) <= myWidth/2+offset + target.myWidth/2+target.offset && Math.abs(posy - target.posy) <= myHeight/2 + target.myHeight/2)
			{
				lastCollision = System.currentTimeMillis();
				return true;
			}
		}
		return false;
	}

}