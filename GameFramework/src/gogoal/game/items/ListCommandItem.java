package gogoal.game.items;

import java.util.ArrayList;

import gogoal.GoGoal;
import gogoal.game.GoGoalConfig;
import gogoal.perception_effects.builders.BuilderPerceptionEffectBloblotte;
import gogoal.perception_effects.builders.BuilderPerceptionEffectDizzy;
import gogoal.perception_effects.builders.BuilderPerceptionEffectSpeed;

public class ListCommandItem extends ArrayList<CommandItem>{
	private static final long serialVersionUID = 2269448829493289046L;
	private static ListCommandItem instance = null;
	
	private ListCommandItem(){
		
		/*
		 * Here will be created the game's different
		 * effects and their items.
		 */
		
		// Murgex : 
		
		BuilderPerceptionEffectDizzy murgexBuilder = new BuilderPerceptionEffectDizzy();
		murgexBuilder.setDuration(15000);
		murgexBuilder.setIntensity(2.0f);
		murgexBuilder.setMagnitude(75.0f);
		murgexBuilder.setVertical(false);
		this.add(
				new CommandItemImpl(
						GoGoalConfig.getInstance().MURGEX_IMG, GoGoal.getInstance().getCanvas(), 128, 128,
						true, murgexBuilder)
				);
		
		// Speed :
		
		BuilderPerceptionEffectSpeed speedBuilder = new BuilderPerceptionEffectSpeed();
		speedBuilder.setSpeedMultiplier(0.6f);
		speedBuilder.setDuration(8000);
		this.add(
				new CommandItemImpl(
						GoGoalConfig.getInstance().SPEED_IMG, GoGoal.getInstance().getCanvas(), 128, 128,
						false, speedBuilder)
				);
		
		// Bloblotte :
		
		BuilderPerceptionEffectBloblotte bbBuilder = new BuilderPerceptionEffectBloblotte();
		bbBuilder.setIntensity(30.0f);
		bbBuilder.setMagnitude(60.0f);
		bbBuilder.setDuration(10000);
		this.add(
				new CommandItemImpl(
						GoGoalConfig.getInstance().BLOBLOTTE_IMG, GoGoal.getInstance().getCanvas(), 128, 128,
						true, bbBuilder)
				);
	}
	
	public static ListCommandItem getInstance(){
		if ( instance == null )
			instance = new ListCommandItem();
		return instance;
	}
}
