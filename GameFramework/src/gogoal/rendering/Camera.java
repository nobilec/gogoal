package gogoal.rendering;

import gogoal.game.GoGoalConfig;
import gogoal.utility.Point3D;

public class Camera
{
	private static Camera instance = null;
	
	private Point3D position;
	
	private Camera(int x, int y, int z){
		setPosition(new Point3D(x, y, z));
	}
	
	public static Camera getInstance(){
		if ( instance == null )
			instance = new Camera(
					GoGoalConfig.getInstance().WIDTH / 2,
					GoGoalConfig.getInstance().HEIGHT / 2,
					0);
		
		return instance;
	}
	
	public float perspectiveComp(float cComp, float comp, float z){
		return ((comp) * ( (z - position.getZ()) / z ));
	}
	
	public float perspectiveX(float x, float z){
		return perspectiveComp(position.getX(), x, z);
	}
	
	public float perspectiveY(float y, float z){
		return perspectiveComp(position.getY(), y, z);
	}

	public Point3D getPosition() {
		return position;
	}

	public void setPosition(Point3D position) {
		this.position = position;
	}
}
