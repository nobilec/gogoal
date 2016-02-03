package gogoal.perception_effects.decorators;

import gogoal.GoGoal;
import gogoal.game.TrainingSession;
import gogoal.game.entities.BalloonEntity;

public class PerceptionEffectSpeed extends PerceptionEffectAbsTimed
{
	public static final float DEFAULT_SPEED_MULT = 0.5f;
	
	private BalloonEntity lastModifiedRef;
	private float lastModifiedBaseMult;
	
	public PerceptionEffectSpeed() {
		this(null);
	}
	
	public PerceptionEffectSpeed(PerceptionEffectAbs deco){
		super(deco);
	}
	
	@Override
	protected void runEffects() {
		TrainingSession ts = GoGoal.getInstance().getCurrentTrainingSession();
		
		if ( ts != null ){
			BalloonEntity be = ts.getCurrentBalloon();
			
			if ( be != null && be != lastModifiedRef) {
					lastModifiedRef = be;
					lastModifiedBaseMult = be.getSpeedMult();
					lastModifiedRef.setSpeedMult(intensity);
			}
		}
	}
	
	@Override
	public void wearOff(){
		super.wearOff();
		
		if ( lastModifiedRef != null ){
			lastModifiedRef.setSpeedMult(lastModifiedBaseMult);
		}
	}

}
