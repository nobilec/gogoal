package gogoal.game.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gogoal.game.GoGoalConfig;
import gogoal.rendering.CameraSingleton;
import gogoal.rendering.Pseudo3DDrawableImage;
import gogoal.utility.Point3D;

public class BalloonEntity implements Drawable, GameEntity, Overlappable
{
	protected Pseudo3DDrawableImage image;
	/* Donnees de collision et de position
	 * temporaires, en attendant que ça passe en 3D
	 */
	protected Point position;

	public BalloonEntity(Canvas defaultCanvas, Point pos) {
		image = new Pseudo3DDrawableImage(GoGoalConfig.getInstance().BALLOON_IMG, defaultCanvas, 128, 128);
		position = pos;
		
		// TEST MOUVEMENT IMAGE, peut etre retire :
		image.getPosition().setZ(1000);
	}
	
	/*
	 * Must be changed in order to adapt the the true size of the balloon
	 */
	@Override
	public Rectangle getBoundingBox() {
		Point dimension = image.getDimension();
		return new Rectangle( (int) position.getX(), (int) position.getY(),
				(int) dimension.getX(), (int) dimension.getY());
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public void draw(Graphics g) {
		position.move((int) position.getX()-7, (int) position.getY()-4);
		Point3D imgPos = image.getPosition();
		imgPos.move((int) position.getX(), (int) position.getY(), imgPos.getZ());
		
		// TEST MOUVEMENT  DE L'IMAGE, peut etre retiré:
		imgPos.setZ(imgPos.getZ() - 40);
		// FIN TEST MOUVEMENT
		
		image.render(g, CameraSingleton.getInstance());
	}

}