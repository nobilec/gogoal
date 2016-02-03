package gogoal;

import java.util.ArrayList;
import java.awt.Frame;
import java.awt.Point;
import java.awt.image.BufferedImage;

import gameframework.game.GameDefaultImpl;
import gameframework.game.GameLevel;
import gogoal.game.GoGoalConfig;
import gogoal.game.TrainingSession;
import gogoal.game.training_sessions.*;

public class GoGoal extends GameDefaultImpl {
	private static GoGoal instance = null;
	
	private TrainingSession currentLevel;
	private int currentLevelIndex, lives;
	private boolean started, defeat;
	
	private GoGoal(){
		super();
		started = false;
		
		int w = GoGoalConfig.getInstance().WIDTH;
		int h = GoGoalConfig.getInstance().HEIGHT;

		((Frame) this.defaultCanvas.getParent()).setTitle("GoGoal");
		this.defaultCanvas.setSize(w, h);
		this.defaultCanvas.getParent().setSize(w, h);
		this.defaultCanvas.getParent().validate();
	}
	
	private void init(){
		lifeValue.setText(String.valueOf(lives));
		setLives(1);
		defeat = false;
		currentLevel = new TrainingSession01(this);
		currentLevelIndex = 0;
		
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();
		
		levels.add(currentLevel);
		levels.add(new TrainingSession02(this));
		levels.add(new TrainingSession03(this));
		levels.add(new TrainingSession04(this));
		levels.add(new TrainingSession05(this));
		levels.add(new TrainingSession06(this));
		levels.add(new TrainingSession07(this));
		levels.add(new TrainingSession08(this));
		levels.add(new TrainingSession09(this));
		levels.add(new TrainingSession10(this));
		
		this.setLevels(levels);
		
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
	
	@Override
	public void start(){
		init();
		if ( !started ){
			super.start();
			started = true;
		}
	}
	
	public TrainingSession getCurrentTrainingSession(){
		return currentLevel;
	}
	
	public void nextLevel(){
		if ( currentLevel != null ){
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
	
	public void removeLife(){
		setLives( getLives() - 1);
	}
	
	public void addLife(){
		setLives( getLives() + 1);
	}
	
	public void setLives(int v){
		lives = v;
		lifeValue.setText(String.valueOf(lives));
		if ( lives == 0 )
			defeat();
	}
	
	public int getLives(){
		return lives;
	}
	
	public boolean isPlayerDefeated(){
		return defeat;
	}
	
	public void defeat(){
		if ( currentLevel != null ){
			informationValue.setText("Defeat");
			currentLevel.interrupt();
		}
		defeat = true;
		System.out.println("Vous avez perdu!");
	}
}
