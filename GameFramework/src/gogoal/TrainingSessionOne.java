package gogoal;

import gameframework.game.Game;
import gogoal.game.TrainingSession;
import gogoal.game.entities.BalloonEntity;
import gogoal.perception_effects.builders.BuilderPerceptionEffectDizzy;
import gogoal.utility.Point3D;

public class TrainingSessionOne extends TrainingSession
{
	public TrainingSessionOne(Game g) {
		super(g);
	}

	@Override
	protected void setUpLevel() {
		BalloonEntity be = new BalloonEntity(canvas, new Point3D(400, 300, 1000));
		universe.addGameEntity(be);
		
		// TEST COMPOSITION D'EFFETS :
		BuilderPerceptionEffectDizzy bped = new BuilderPerceptionEffectDizzy();
		
		bped.setDuration(3000);
		bped.setIntensity(1.0f);
		bped.setMagnitude(50.0f);
		bped.setVertical(true);
		
		effects.composeEffect(bped);
		
		bped.setDuration(6000);
		bped.setIntensity(1.5f);
		bped.setMagnitude(25.0f);
		bped.setVertical(false);
		
		effects.composeEffect(bped);
	}

}
