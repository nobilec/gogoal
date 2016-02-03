package gogoal.game.training_sessions;

import gameframework.game.Game;
import gogoal.game.TrainingSession;
import gogoal.game.entities.BalloonEntity;
import gogoal.utility.Point3D;

public class TrainingSession01 extends TrainingSession
{
	public TrainingSession01(Game g) {
		super(g);
	}

	@Override
	protected void setUpLevel() {
		while ( bStack.size() < 4 ){
			bStack.push(new BalloonEntity(canvas, new Point3D(400, 300, 1000)));
		}
	}

}
