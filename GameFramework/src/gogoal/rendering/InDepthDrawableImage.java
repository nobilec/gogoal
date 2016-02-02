package gogoal.rendering;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import gameframework.base.Drawable;
import gogoal.utility.Point3D;

public class InDepthDrawableImage implements Drawable
{
	private ResizableDrawableImage rImg;
	private int distanceToCam;
	private boolean canBeSeen;
	
	public InDepthDrawableImage(String filename, Canvas canvas, int imgWidth, int imgHeight){
		rImg = new ResizableDrawableImage(filename, canvas, imgWidth, imgHeight);
		distanceToCam = -1;
		setDistanceToCamera(0);
		canBeSeen = true;
	}
	
	public int getDistanceToCamera(){
		return distanceToCam;
	}

	public void setDistanceToCamera(int dtc){
		if ( dtc != distanceToCam ){
			Point bDimension = rImg.getBaseDimension();
			float bWidth = (float) bDimension.getX(), bHeight = (float) bDimension.getY();
			float factor = dtc >= 0 ? ((float) dtc) + 1.0f : -1.0f;
			
			if ( factor != - 1.0f) {
				// Image is in front of the camera :
				float nWidth = bWidth * (bWidth/factor);
				float nHeight = bHeight * (bHeight/factor);
				
				if ( 	nWidth >= 0.5f && nHeight >= 0.5f && 
						nWidth <= ResizableDrawableImage.SIZE_LIMIT && 
						nHeight <= ResizableDrawableImage.SIZE_LIMIT )
				{
					// Image is at the right distance
					rImg.resize(Math.round(nWidth), Math.round(nHeight));
					canBeSeen = true;
				} else {
					// Image is too close or too far
					canBeSeen = false;
				}
			} else { 
				// Image is behind the camera :
				canBeSeen = false;
			}
			distanceToCam = dtc;//*/
			
		}
	}
	
	public Point getDimension(){
		return rImg.getDimension();
	}
	
	public Image getImage(){
		return rImg.getImage();
	}
	
	@Override
	public void draw(Graphics g) {
		rImg.draw(g);
	}
	
	public void render(Graphics g, Point3D position){
		if ( canBeSeen ){
			Point dimension = getDimension();
			
			int w = (int) dimension.getX();
			int h = (int) dimension.getY();
			
			Camera c = Camera.getInstance();
			int nx = Math.round( c.perspectiveX(position.getX(), position.getZ())) - w/2;
			int ny = Math.round( c.perspectiveY(position.getY(), position.getZ())) - h/2;
			
			g.drawImage(
					getImage(), nx, ny, w, h, null);
		}
	}

}
