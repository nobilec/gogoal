package gogoal;

import java.util.ArrayList;
import java.awt.Frame;
import java.awt.Point;
import java.awt.image.BufferedImage;

import gameframework.game.GameDefaultImpl;
import gameframework.game.GameLevel;
import gogoal.game.GoGoalConfig;
import gogoal.game.TrainingSession;

public class GoGoal extends GameDefaultImpl {
	private static GoGoal instance = null;
	
	private TrainingSession currentLevel;
	private int currentLevelIndex;
	
	private GoGoal(){
		currentLevel = new TrainingSessionOne(this);
		currentLevelIndex = 0;
		
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();
		levels.add(currentLevel);
		levels.add(new TrainingSessionOne(this));
		this.setLevels(levels);
		
		int w = GoGoalConfig.getInstance().WIDTH;
		int h = GoGoalConfig.getInstance().HEIGHT;

		((Frame) this.defaultCanvas.getParent()).setTitle("GoGoal");
		this.defaultCanvas.setSize(w, h);
		this.defaultCanvas.getParent().setSize(w, h);
		this.defaultCanvas.getParent().validate();
		
		/*
		 * Replaces mouse cursor by blank image.
		 * Source : https://stackoverflow.com/questions/1984071/how-to-hide-cursor-in-a-swing-application#1984117
		 */
		this.defaultCanvas.setCursor(defaultCanvas.getToolkit().createCustomCursor(
	            new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
	            "null"));
	}
	
	public static GoGoal getInstance(){
		if ( instance == null )
			instance = new GoGoal();
		return instance;
	}
	
	public TrainingSession getCurrentTrainingSession(){
		return currentLevel;
	}
	
	public void nextLevel(){
		this.currentLevel.end();
		++this.currentLevelIndex;
		
		if ( currentLevelIndex < this.gameLevels.size() ) {
			this.currentLevel = (TrainingSession) gameLevels.get(currentLevelIndex);
		} else {
			// END OF THE GAME
			this.currentLevel = null;
			System.out.println("Felicitations, vous avez terminé le jeu");
		}
	}
}
