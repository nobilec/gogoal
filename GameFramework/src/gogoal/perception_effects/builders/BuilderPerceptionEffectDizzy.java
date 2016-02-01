package gogoal.perception_effects.builders;

import gogoal.perception_effects.decorators.PerceptionEffectAbs;
import gogoal.perception_effects.decorators.PerceptionEffectDizzy;

public class BuilderPerceptionEffectDizzy extends BuilderPerceptionEffectAbsTimed
{
	protected boolean vertical;

	protected void setAttributes(PerceptionEffectDizzy res){
		super.setAttributes(res);
		res.setVertical(vertical);
	}
	
	@Override
	public PerceptionEffectAbs build() {
		PerceptionEffectDizzy res = new PerceptionEffectDizzy();
		setAttributes(res);
		return res;
	}
	
	public void setVertical(boolean vertical){
		this.vertical = vertical;
	}
}
