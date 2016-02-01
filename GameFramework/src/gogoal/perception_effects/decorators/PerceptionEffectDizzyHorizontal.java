package gogoal.perception_effects.decorators;

import gogoal.rendering.Camera;

public class PerceptionEffectDizzyHorizontal extends PerceptionEffectDizzy 
{
	public PerceptionEffectDizzyHorizontal() {
		this(null);
	}
	
	public PerceptionEffectDizzyHorizontal(PerceptionEffectAbs deco){
		super(deco);
	}
	
	@Override
	protected float getCameraPosition(){
		return Camera.getInstance().getXOffset();
	}
	
	@Override
	protected void setCameraPosition(float pos){
		Camera.getInstance().setXOffset(pos);
	}
}
