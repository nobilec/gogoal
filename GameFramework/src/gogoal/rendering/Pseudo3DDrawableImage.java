package gogoal.rendering;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

import gameframework.base.Drawable;
import gogoal.utility.Point3D;

public class Pseudo3DDrawableImage implements Drawable
{
	private InDepthDrawableImage iddi;
	
	public Pseudo3DDrawableImage(String filename, Canvas canvas, int imgWidth, int imgHeight){
		iddi = new InDepthDrawableImage(filename, canvas, imgWidth, imgHeight);
	}
	
	public Point getDimension(){
		return iddi.getDimension();
	}
	
	public void render(Graphics g, Point3D position, Point3D cameraPosition){
		iddi.setDistanceToCamera(position.getZ());
		iddi.render(g, position);
	}
	
	@Override
	public void draw(Graphics g) {
		iddi.draw(g);
	}
}
