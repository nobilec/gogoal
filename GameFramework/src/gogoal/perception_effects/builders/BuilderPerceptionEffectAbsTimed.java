package gogoal.perception_effects.builders;

import gogoal.perception_effects.decorators.PerceptionEffectAbsTimed;

public abstract class BuilderPerceptionEffectAbsTimed extends BuilderPerceptionEffectAbs
{
	protected long duration;
	
	protected void setAttributes(PerceptionEffectAbsTimed res){
		super.setAttributes(res);
		res.setDuration(duration);
	}
	
	public void setDuration(long duration){
		this.duration = duration;
	}
}
