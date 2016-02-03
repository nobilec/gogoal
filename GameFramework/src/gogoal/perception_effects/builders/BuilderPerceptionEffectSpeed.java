package gogoal.perception_effects.builders;

import gogoal.perception_effects.decorators.PerceptionEffectAbs;
import gogoal.perception_effects.decorators.PerceptionEffectSpeed;

public class BuilderPerceptionEffectSpeed extends BuilderPerceptionEffectAbsTimed
{
	@Override
	public PerceptionEffectAbs build() {
		PerceptionEffectSpeed res = new PerceptionEffectSpeed();
		
		super.setAttributes(res);
		
		return res;
	}
	
	public void setSpeedMultiplier(float mult){
		intensity = mult;
	}
}
