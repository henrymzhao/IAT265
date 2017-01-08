package factory;

import movingobjects.Bullet;
import movingobjects.MovingObjects;

/* BulletFactory class to offer a clean creation interface for user that utilizes ConcreteBulletFactory
 * CREATED: 11/23/2016
 * AUTHOR: HENRY ZHAO*/
public abstract class BulletFactory 
{
	/* WHAT IT DOES: Force createBullet to be created in the child class 
	 * PARAMETERS: 
	 * 	mod - modifier for which type of bullet to spawn
	 * 	offset - the position to offset the drawing by
	 * 	type - which type of bullet to create, regular bullet or casings
	 * RETURN:*/
	abstract MovingObjects createBullet(String mod, int offset, int type);
}