package gogoal.game.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gogoal.game.GoGoalConfig;
import gogoal.perception_effects.PerceptionEffectAbs;
import gogoal.perception_effects.PerceptionEffectDizzy;
import gogoal.rendering.Camera;
import gogoal.rendering.Pseudo3DDrawableImage;
import gogoal.utility.Point3D;

public class BalloonEntity implements Drawable, GameEntity, Overlappable
{
	protected Pseudo3DDrawableImage image;
	/* Donnees de collision et de position
	 * temporaires, en attendant que ça passe en 3D
	 */
	protected Point3D position;
	protected Point pos2;
	
	// TEST EFFETS CAMERA
	PerceptionEffectAbs effects;

	public BalloonEntity(Canvas defaultCanvas, Point3D pos) {
		image = new Pseudo3DDrawableImage(GoGoalConfig.getInstance().BALLOON_IMG, defaultCanvas, 128, 128);
		position = pos;
		pos2 = new Point((int)pos.getX(), (int) pos.getY());
		effects = new PerceptionEffectDizzy();
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
	public Point getPosition() {
		return pos2;
	}
	
	public Point3D get3DPosition() {
		return position;
	}

	@Override
	public void draw(Graphics g) {
		// TEST MOUVEMENT
		position.move(-0.7f, -0.5f, -5.0f);
		// TEST PERCEPTION
		effects.apply();
		
		pos2.setLocation(position.getX(), position.getY());
		image.render(g, position, Camera.getInstance().getPosition());
	}

}