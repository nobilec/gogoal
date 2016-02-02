package gogoal;

import java.util.ArrayList;

import gameframework.game.GameDefaultImpl;
import gameframework.game.GameLevel;
import gogoal.game.TrainingSession;

public class GoGoal extends GameDefaultImpl {
	private static GoGoal instance = null;
	
	private TrainingSession currentLevel;
	
	private GoGoal(){
		currentLevel = new TrainingSessionOne(this);
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();
		levels.add(currentLevel);
		this.setLevels(levels);
	}
	
	public static GoGoal getInstance(){
		if ( instance == null )
			instance = new GoGoal();
		return instance;
	}
	
	public TrainingSession getCurrentTrainingSession(){
		return currentLevel;
	}
}
