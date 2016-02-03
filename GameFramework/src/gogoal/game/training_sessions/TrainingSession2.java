package gogoal.game.training_sessions;

import gameframework.game.Game;
import gogoal.game.TrainingSession;
import gogoal.game.entities.BalloonEntity;
import gogoal.utility.Point3D;

public class TrainingSession2 extends TrainingSession
{

	public TrainingSession2(Game g) {
		super(g);
	}

	@Override
	protected void setUpLevel() {
		for ( int i = 1; i <= 8; ++i){
			
			BalloonEntity be = new BalloonEntity(canvas, new Point3D(400, 300, 1000));
			be.setSpeedMult(1.0f + 2.0f / (float) i );
			bStack.push(be);
		}
	}

}
