package gogoal.game;

import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

public class GoGoalOverlapRulesApplier extends OverlapRulesApplierDefaultImpl {
	protected GameUniverse universe;
	
	
	public GoGoalOverlapRulesApplier() {
		this.universe = null;
	}

	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}

}
