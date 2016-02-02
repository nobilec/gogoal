package gogoal.game.entities;

import java.awt.Canvas;
import gogoal.game.GoGoalConfig;
import gogoal.utility.Point3D;

public class FootballFieldEntity extends GoGoal3DEntity
{
	public FootballFieldEntity(Canvas defaultCanvas){
		super( 	defaultCanvas, 
				GoGoalConfig.getInstance().BACKGROUND_IMG,
				800, 600, new Point3D(400.0f, 250.0f, 500.0f));
	}
}
