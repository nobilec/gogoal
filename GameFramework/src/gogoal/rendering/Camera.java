package gogoal.rendering;

import gogoal.game.GoGoalConfig;
import gogoal.utility.Point3D;

public class Camera
{
	private static Camera instance = null;
	
	private Point3D position;
	private float xOff, yOff;
	
	private Camera(){
		this(0.0f, 0.0f, 0.0f);
	}
	
	private Camera(float x, float y, float z){
		setPosition(new Point3D(x, y, z));
		xOff = 0.0f;
		yOff = 0.0f;
	}
	
	public static Camera getInstance(){
		if ( instance == null )
			instance = new Camera(
					GoGoalConfig.getInstance().WIDTH / 2,
					GoGoalConfig.getInstance().HEIGHT / 2,
					0);
		
		return instance;
	}
	
	public float perspectiveComp(float cComp, float cOff, float comp, float z){
		return ((comp) * ( (z - position.getZ()) / z )) + cOff;
	}
	
	public float perspectiveX(float x, float z){
		return perspectiveComp(position.getX(), xOff, x, z);
	}
	
	public float perspectiveY(float y, float z){
		return perspectiveComp(position.getY(), yOff, y, z);
	}

	public Point3D getPosition() {
		return position;
	}

	public void setPosition(Point3D position) {
		this.position = position;
	}
	
	public void setXOffset(float off){
		xOff = off;
	}
	
	public float getXOffset(){
		return xOff;
	}
	
	public void setYOffset(float off){
		yOff = off;
	}
	
	public float getYOffset(){
		return yOff;
	}
}
