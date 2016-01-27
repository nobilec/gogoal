package gogoal.rendering;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

import gameframework.base.Drawable;
import gogoal.utility.Point3D;

public class Pseudo3DDrawableImage implements Drawable
{
	private InDepthDrawableImage iddi;
	private Point3D position;
	
	public Pseudo3DDrawableImage(String filename, Canvas canvas, int imgWidth, int imgHeight){
		iddi = new InDepthDrawableImage(filename, canvas, imgWidth, imgHeight);
		position = new Point3D(0, 0, 0);
	}
	
	public Point3D getPosition(){
		return position;
	}
	
	public Point getDimension(){
		return iddi.getDimension();
	}
	
	public void render(Graphics g, Point3D cameraPosition){
		int distanceToCamera = Math.round(position.distance(cameraPosition));
		
		if ( !position.isBehindZ(cameraPosition) ) {
			iddi.setDistanceToCamera(distanceToCamera);
			iddi.render(g, position.get2DComponent());
		}
	}
	
	@Override
	public void draw(Graphics g) {
		iddi.draw(g);
	}
}
