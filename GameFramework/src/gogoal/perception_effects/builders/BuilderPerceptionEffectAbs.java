package gogoal.perception_effects.builders;

import gogoal.perception_effects.decorators.PerceptionEffectAbs;

public abstract class BuilderPerceptionEffectAbs implements BuilderPerceptionEffect
{
	protected float intensity, magnitude;
	
	protected void setAttributes(PerceptionEffectAbs res){
		res.setIntensity(intensity);
		res.setMagnitude(magnitude);
	}
	
	public abstract PerceptionEffectAbs build();
	
	public void setIntensity(float intensity){
		this.intensity = intensity;
	}
	
	public void setMagnitude(float magnitude){
		this.magnitude = magnitude;
	}
}
