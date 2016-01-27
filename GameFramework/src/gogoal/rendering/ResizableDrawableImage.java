package gogoal.rendering;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;

import gameframework.base.DrawableImage;

public class ResizableDrawableImage extends DrawableImage
{
	public static final int DEFAULT_SCALING_MODE = Image.SCALE_FAST;
	public static final int SIZE_LIMIT = 2048;
	
	private Image processedImg;
	private Point dimension, baseDimension;
	private MediaTracker tracker;
	
	public ResizableDrawableImage(String filename, Canvas canvas, int imgWidth, int imgHeight){
		super(filename, canvas);
		processedImg = this.image;
		dimension = new Point(imgWidth, imgHeight);
		baseDimension = new Point(imgWidth, imgHeight);
		tracker = new MediaTracker(canvas);
	}
	
	public void resize(int width, int height){
		resize(width, height, DEFAULT_SCALING_MODE);
	}
	
	public void resize(int width, int height, int hints){
		if ( width <= SIZE_LIMIT && height <= SIZE_LIMIT ){
			try {
				// Removing formerly scaled image if any
				tracker.removeImage(processedImg);
				
				processedImg = this.image.getScaledInstance(width, height, hints);
				tracker.addImage(processedImg, 0);
				tracker.waitForAll();
				dimension.move(width, height);
				
			} catch (Exception e){
				e.printStackTrace();
				processedImg = this.image;
			}
		}
	}
	
	public Point getBaseDimension(){
		return baseDimension;
	}
	
	public Point getDimension(){
		return dimension;
	}
	
	@Override
	public Image getImage(){
		return processedImg;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(processedImg, 0, 0, canvas);
	}

}
