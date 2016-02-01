package gogoal.perception_effects.decorators;

import java.util.Observable;

public abstract class PerceptionEffectAbs extends Observable implements PerceptionEffect
{
	private PerceptionEffectAbs deco;
	protected boolean wornOff;
	protected float intensity, magnitude;
	
	public PerceptionEffectAbs(){
		this(null);
	}
	
	public PerceptionEffectAbs(PerceptionEffectAbs deco){
		wornOff = false;
		this.deco = deco;
		this.setIntensity(0.0f);
	}
	
	public void apply(){
		if ( deco != null ){
			deco.apply();
		}if ( !wornOff ){
			runEffects();
		}
	}
	
	public void wearOff(){
		wornOff = true;
		setChanged();
		notifyObservers();
	}

	public float getIntensity() {
		return intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}
	
	public float getMagnitude(){
		return magnitude;
	}
	
	public void setMagnitude(float magnitude){
		this.magnitude = magnitude;
	}
	
	public boolean hasWornOff(){
		return wornOff;
	}
	
	public PerceptionEffectAbs getDecorated(){
		return deco;
	}
	
	public void setDecorated(PerceptionEffectAbs deco){
		this.deco = deco;
	}
	
	protected abstract void runEffects();
}
