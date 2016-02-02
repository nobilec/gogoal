package gogoal.game.entities;

import java.awt.Canvas;

import gogoal.game.GoGoalConfig;
import gogoal.utility.Point3D;

public class GoalEntity extends GoGoal3DEntity {

	public GoalEntity(Canvas defaultCanvas) {
		super( 	defaultCanvas, 
				GoGoalConfig.getInstance().GOAL_IMG,
				800, 1024, new Point3D(395.0f, 400.0f, 700.0f));
	}

}
