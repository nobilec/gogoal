package gogoal.rendering;

import java.awt.Canvas;
import java.awt.Point;

import gameframework.base.DrawableImage;

public class ResizableDrawableImage extends DrawableImage
{
	public static final int SIZE_LIMIT = 2048;
	private Point dimension, baseDimension;
	
	public ResizableDrawableImage(String filename, Canvas canvas, int imgWidth, int imgHeight){
		super(filename, canvas);
		dimension = new Point(imgWidth, imgHeight);
		baseDimension = new Point(imgWidth, imgHeight);
	}
	
	public void resize(int width, int height){
		if ( width <= SIZE_LIMIT && height <= SIZE_LIMIT ){
			//System.out.println("nDIM = " + width + " ; " + height );
			dimension.move(width, height);
		}
	}
	
	public Point getBaseDimension(){
		return baseDimension;
	}
	
	public Point getDimension(){
		return dimension;
	}
}
