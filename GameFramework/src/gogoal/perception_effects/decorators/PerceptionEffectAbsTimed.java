package gogoal.perception_effects.decorators;

import java.util.Date;

public abstract class PerceptionEffectAbsTimed extends PerceptionEffectAbs
{
	public static final long DEFAULT_DURATION = 20000;
	
	private long duration; // In milliseconds
	private long beginTime;
	
	public PerceptionEffectAbsTimed(){
		this(null);
	}
	
	public PerceptionEffectAbsTimed(PerceptionEffectAbs deco){
		super(deco);
		this.duration = DEFAULT_DURATION;
		beginTime = -1;
	}
	
	@Override
	public void apply(){
		if ( !wornOff ){
			if ( started() ){
				if ( ((new Date()).getTime() - beginTime) >= duration ){
					wearOff();
				}
			} else {
				beginTime = (new Date()).getTime();
			}
		}
		super.apply();
	}
	
	public long getDuration(){
		return duration;
	}
	
	public void setDuration(long duration){
		this.duration = duration;
	}
	
	public boolean started(){
		return beginTime != -1;
	}
}
