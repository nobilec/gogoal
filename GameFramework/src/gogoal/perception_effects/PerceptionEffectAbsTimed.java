package gogoal.perception_effects;

import java.util.Date;

public abstract class PerceptionEffectAbsTimed extends PerceptionEffectAbs
{
	public static final long DEFAULT_DURATION = 20000;
	
	private final long duration; // In milliseconds
	private long beginTime;
	
	public PerceptionEffectAbsTimed(){
		this(DEFAULT_DURATION, null);
	}
	
	public PerceptionEffectAbsTimed(long duration){
		this(duration, null);
	}
	
	public PerceptionEffectAbsTimed(PerceptionEffectAbs deco){
		this(DEFAULT_DURATION, deco);
	}
	
	public PerceptionEffectAbsTimed(long duration, PerceptionEffectAbs deco){
		super(deco);
		this.duration = duration;
		beginTime = -1;
	}
	
	@Override
	public void apply(){
		if ( started() ){
			if ( ((new Date()).getTime() - beginTime) >= duration ){
				wearOff();
			}
		} else {
			beginTime = (new Date()).getTime();
		}
		super.apply();
	}
	
	public boolean started(){
		return beginTime != -1;
	}
}
