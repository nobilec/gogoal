package gogoal.game.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gogoal.rendering.Camera;
import gogoal.rendering.Pseudo3DDrawableImage;
import gogoal.utility.Point3D;

public abstract class GoGoal3DEntity implements Drawable, GameEntity, Overlappable
{
	protected Pseudo3DDrawableImage image;
	protected Point3D position;
	protected Point pos2;
	
	public GoGoal3DEntity(Canvas defaultCanvas, 
			String imageFile, int imgWidth, int imgHeight,
			Point3D position)
	{
		this.image = new Pseudo3DDrawableImage(imageFile, defaultCanvas, imgWidth, imgHeight);
		this.position = position;
		this.pos2 = position.get2DComponent();
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return null;
	}

	@Override
	public Point getPosition() {
		return pos2;
	}

	@Override
	public void draw(Graphics g) {
		pos2.setLocation(position.getX(), position.getY());
		image.render(g, position, Camera.getInstance().getPosition());
	}

}
