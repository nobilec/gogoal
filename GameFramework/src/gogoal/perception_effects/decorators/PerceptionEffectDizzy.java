package gogoal.perception_effects.decorators;


public abstract class PerceptionEffectDizzy extends PerceptionEffectAbsTimed
{
	public static final float DEFAULT_STIRRING_INTENSITY = 1.0f;
	public static final float DEFAULT_STIRRING_MAGNITUDE = 50.0f;
	
	protected float position;
	protected boolean direction;
	
	public PerceptionEffectDizzy(){
		this( null);
	}
	
	public PerceptionEffectDizzy(PerceptionEffectAbs deco){
		super(deco);
		magnitude = DEFAULT_STIRRING_MAGNITUDE;
		intensity = DEFAULT_STIRRING_INTENSITY;
		direction = false;
		position = 0.0f;
	}
	
	@Override
	protected void runEffects() {
		float cOff = getCameraPosition();
		float mvt = (position + intensity >= magnitude) ? magnitude - position : intensity;
		
		cOff += direction ? mvt : - mvt;
		position += mvt;
		
		if ( position >= magnitude ){
			direction = !direction;
			position = 0.0f;
		}
		
		setCameraPosition(cOff);
	}
	
	@Override
	public void wearOff(){
		super.wearOff();
		
		// Resetting camera
		setCameraPosition(0.0f);
	}
	
	protected abstract float getCameraPosition();
	protected abstract void setCameraPosition(float pos);
}
