package gogoal.perception_effects;

import java.util.Observable;
import java.util.Observer;

import gogoal.perception_effects.builders.BuilderPerceptionEffect;
import gogoal.perception_effects.decorators.PerceptionEffect;
import gogoal.perception_effects.decorators.PerceptionEffectAbs;

public class ProxyPerceptionEffect implements Observer, PerceptionEffect {
	private PerceptionEffectAbs end;
	private boolean removeWornOff;
	
	public ProxyPerceptionEffect(){
		super();
		end = null;
		removeWornOff = false;
	}
	
	public void composeEffect(BuilderPerceptionEffect bpe){
		PerceptionEffectAbs nPE = bpe.build();
		nPE.setDecorated(end);
		end = nPE;
		end.addObserver(this);
	}
	
	public void apply(){
		if ( end != null )
			end.apply();	
		if ( removeWornOff )
			removeTerminatedEffects();
	}

	public void wearOff(){
		PerceptionEffectAbs cur = end;
		while ( cur != null ){
			cur.wearOff();
			cur = cur.getDecorated();
		}
		
		removeTerminatedEffects();
	}

	@Override
	public void update(Observable o, Object arg) {
		if ( o instanceof PerceptionEffectAbs ){
			if ( ((PerceptionEffectAbs) o).hasWornOff() )
				removeWornOff = true;
		}
	}
	
	public int getNbComposedEffects(){
		int res = 0;
		PerceptionEffectAbs cur = end;
		
		while ( cur != null ){
			++res;
			cur = cur.getDecorated();
		}
		
		return res;
	}
	
	private void removeTerminatedEffects(){
		PerceptionEffectAbs cur = end;
		if ( cur != null ){
			PerceptionEffectAbs nxt = cur.getDecorated();
			
			while ( cur != null && cur.hasWornOff() ){
				end = cur.getDecorated();
				cur.setDecorated(null);
				cur = null;
			}
			
			while ( cur != null ){
				
				if (nxt != null )
					if ( nxt.hasWornOff() ){
						cur.setDecorated(nxt.getDecorated());
						nxt.setDecorated(null);
						nxt = null;
					}
				
				cur = cur.getDecorated();
				if ( cur != null ) nxt = cur.getDecorated();
			}
			
			removeWornOff = false;
		}
	}
	
	@Override
	public void finalize(){
		wearOff();
	}
}
