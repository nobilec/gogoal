package gogoal.perception_effects.builders;

import gogoal.perception_effects.decorators.PerceptionEffectAbs;
import gogoal.perception_effects.decorators.PerceptionEffectDizzy;
import gogoal.perception_effects.decorators.PerceptionEffectDizzyHorizontal;
import gogoal.perception_effects.decorators.PerceptionEffectDizzyVertical;

public class BuilderPerceptionEffectDizzy extends BuilderPerceptionEffectAbsTimed
{
	protected boolean vertical;
	
	@Override
	public PerceptionEffectAbs build() {
		PerceptionEffectDizzy res = null;
		
		if ( vertical )
			res = new PerceptionEffectDizzyVertical();
		else
			res = new PerceptionEffectDizzyHorizontal();
		
		super.setAttributes(res);
		
		return res;
	}
	
	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}

}
