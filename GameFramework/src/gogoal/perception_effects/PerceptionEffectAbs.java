package gogoal.perception_effects;

public abstract class PerceptionEffectAbs implements PerceptionEffect
{
	private PerceptionEffectAbs deco;
	private boolean wornOff;
	protected float intensity;
	
	public PerceptionEffectAbs(){
		this(null);
	}
	
	public PerceptionEffectAbs(PerceptionEffectAbs deco){
		wornOff = false;
		this.deco = deco;
		this.setIntensity(0.0f);
	}
	
	public void apply(){
		if ( deco != null )
			deco.apply();
		if ( !wornOff ){
			runEffects();
		}
	}
	
	public void wearOff(){
		wornOff = true;
	}

	public float getIntensity() {
		return intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}
	
	protected abstract void runEffects();
}
