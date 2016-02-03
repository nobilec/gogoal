package gogoal.perception_effects.builders;

import gogoal.perception_effects.decorators.PerceptionEffectAbs;
import gogoal.perception_effects.decorators.PerceptionEffectBloblotte;

public class BuilderPerceptionEffectBloblotte extends BuilderPerceptionEffectAbsTimed
{
	@Override
	public PerceptionEffectAbs build() {
		PerceptionEffectBloblotte res = new PerceptionEffectBloblotte();
		
		super.setAttributes(res);
		
		return res;
	}
}
