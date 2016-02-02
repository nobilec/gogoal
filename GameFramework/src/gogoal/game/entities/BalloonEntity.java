package gogoal.game.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gogoal.game.GoGoalConfig;
import gogoal.utility.Point3D;

public class BalloonEntity extends GoGoal3DEntity
{
	public BalloonEntity(Canvas defaultCanvas, Point3D pos) {
		super(defaultCanvas, GoGoalConfig.getInstance().BALLOON_IMG, 128, 128, pos);
	}
	
	/*
	 * Must be changed in order to adapt the the true size of the balloon
	 */
	@Override
	public Rectangle getBoundingBox() {
		Point dimension = image.getDimension();
		return new Rectangle( 
				(int) position.getX(), (int) position.getY(),
				(int) dimension.getX(), (int) dimension.getY());
	}

	@Override
	public void draw(Graphics g) {
		// TEST MOUVEMENT
		position.move(-0.5f, -0.8f, -5.0f);
		
		super.draw(g);
	}

}