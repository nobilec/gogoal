package gogoal.perception_effects.decorators;

import gogoal.rendering.Camera;

public class PerceptionEffectDizzy extends PerceptionEffectAbsTimed
{
	public static final float DEFAULT_STIRRING_INTENSITY = 1.0f;
	public static final float DEFAULT_STIRRING_MAGNITUDE = 50.0f;
	
	private float position;
	private boolean direction, vertical;
	
	public PerceptionEffectDizzy(){
		this( null);
	}
	
	public PerceptionEffectDizzy(PerceptionEffectAbs deco){
		super(deco);
		magnitude = DEFAULT_STIRRING_MAGNITUDE;
		intensity = DEFAULT_STIRRING_INTENSITY;
		direction = false;
		position = 0.0f;
		vertical = false;
	}
	
	@Override
	protected void runEffects() {
		Camera c = Camera.getInstance();
		
		float cOff = vertical ? c.getYOffset() : c.getXOffset();
		float mvt = (position + intensity > magnitude) ? magnitude - position : intensity;
		
		cOff += direction ? mvt : - mvt;
		position += mvt;
		
		if ( position == magnitude ){
			direction = !direction;
			position = 0.0f;
		}
		
		if ( vertical ) c.setYOffset(cOff);
		else c.setXOffset(cOff);
	}

	public boolean isVertical() {
		return vertical;
	}

	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}
}
