package gogoal.rendering;

import gogoal.utility.Point3D;

public class CameraSingleton {
	private static Point3D instance = null;
	
	public static Point3D getInstance(){
		return getInstance(200, 200, 0);
	}
	
	public static Point3D getInstance(int x, int y, int z){
		if ( instance == null )
			instance = new Point3D(x, y, z);
		
		return instance;
	}
	
	private CameraSingleton(){}
}
