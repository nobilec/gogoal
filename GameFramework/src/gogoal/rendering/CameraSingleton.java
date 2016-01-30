package gogoal.rendering;

import gogoal.game.GoGoalConfig;
import gogoal.utility.Point3D;

public class CameraSingleton {
	private static Point3D instance = null;
	
	public static Point3D getInstance(){
		return getInstance(GoGoalConfig.getInstance().WIDTH / 2, GoGoalConfig.getInstance().HEIGHT / 2, 0);
	}
	
	public static Point3D getInstance(int x, int y, int z){
		if ( instance == null )
			instance = new Point3D(x, y, z);
		
		return instance;
	}
	
	private CameraSingleton(){}
}
