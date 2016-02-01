package gogoal.perception_effects.decorators;

import gogoal.rendering.Camera;

public class PerceptionEffectDizzyVertical extends PerceptionEffectDizzy
{
	public PerceptionEffectDizzyVertical() {
		this(null);
	}
	
	public PerceptionEffectDizzyVertical(PerceptionEffectAbs deco){
		super(deco);
	}
	
	@Override
	protected float getCameraPosition(){
		return Camera.getInstance().getYOffset();
	}
	
	@Override
	protected void setCameraPosition(float pos){
		Camera.getInstance().setYOffset(pos);
	}
}
