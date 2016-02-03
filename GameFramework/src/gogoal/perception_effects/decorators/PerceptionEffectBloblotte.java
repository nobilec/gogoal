package gogoal.perception_effects.decorators;

import java.util.Date;
import java.util.Random;

import gogoal.GoGoal;
import gogoal.game.TrainingSession;
import gogoal.game.entities.GlovesEntity;

public class PerceptionEffectBloblotte extends PerceptionEffectAbsTimed{
	public PerceptionEffectBloblotte(){
		this(null);
	}
	
	public PerceptionEffectBloblotte(PerceptionEffectAbs deco){
		super(deco);
	}
	
	@Override
	protected void runEffects() {
		TrainingSession ts = GoGoal.getInstance().getCurrentTrainingSession();
		if ( ts != null ){
			GlovesEntity ge = ts.getGloves();
			
			if ( ge != null ) {
				Random r = new Random(new Date().getTime());
				
				float xShaking = ge.getXOff() + (-1.0f + 2.0f * r.nextFloat()) * intensity;
				float yShaking = ge.getYOff() + (-1.0f + 2.0f * r.nextFloat()) * intensity;
				
				if ( xShaking > magnitude ) xShaking = magnitude;
				else if ( xShaking < -magnitude ) xShaking = - magnitude;
				if ( yShaking > magnitude ) yShaking = magnitude;
				else if ( yShaking < -magnitude ) yShaking = - magnitude;
				
				ge.setXOff(xShaking);
				ge.setYOff(yShaking);
			}
		}
	}
	
	@Override
	public void wearOff(){
		super.wearOff();
		
		TrainingSession ts = GoGoal.getInstance().getCurrentTrainingSession();
		if ( ts != null ){
			GlovesEntity ge = ts.getGloves();
			
			if ( ge != null ) {
				ge.setXOff(0.0f);
				ge.setYOff(0.0f);
			}
		}
	}

}
