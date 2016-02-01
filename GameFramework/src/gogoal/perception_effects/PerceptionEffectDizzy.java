package gogoal.perception_effects;

import gogoal.rendering.Camera;

public class PerceptionEffectDizzy extends PerceptionEffectAbsTimed
{
	public static final float DEFAULT_STIRRING_INTENSITY = 3.0f;
	public static final float DEFAULT_STIRRING_THRESHOLD = 50.0f;
	
	private float threshold, position;
	private boolean direction;
	
	public PerceptionEffectDizzy(){
		this(DEFAULT_DURATION, null);
	}
	
	public PerceptionEffectDizzy(long duration, PerceptionEffectAbs deco){
		super(duration, deco);
		threshold = DEFAULT_STIRRING_THRESHOLD;
		intensity = DEFAULT_STIRRING_INTENSITY;
		direction = false;
		position = 0.0f;
	}
	
	@Override
	protected void runEffects() {
		Camera c = Camera.getInstance();
		
		float xOff = c.getXOffset();
		float mvt = (position + intensity > threshold) ? threshold - position : intensity;
		
		xOff += direction ? mvt : - mvt;
		position += mvt;
		
		if ( position == threshold ){
			direction = !direction;
			position = 0.0f;
		}
		
		c.setXOffset(xOff);
	}

}
