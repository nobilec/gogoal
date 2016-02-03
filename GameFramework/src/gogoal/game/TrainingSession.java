package gogoal.game;

import java.awt.Canvas;
import java.util.Date;

import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.GameUniverseViewPortDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;
import gogoal.game.entities.BalloonEntity;
import gogoal.game.entities.FootballFieldEntity;
import gogoal.game.entities.GlovesEntity;
import gogoal.game.entities.GoalEntity;
import gogoal.perception_effects.ProxyPerceptionEffect;

public abstract class TrainingSession extends GameLevelDefaultImpl
{
	private static final int DEFAULT_GAME_UPDATE_DELAY = 20;
	
	private boolean stopTS;
	private int gameUpdateDelay;
	protected Canvas canvas;
	
	protected int balloonsFired;
	private int deffx = 0;
	private int deffy = 0;
	private int but = 0;
	protected ProxyPerceptionEffect effects;
	
	protected FootballFieldEntity footballField;
	protected GoalEntity goal;
	protected GlovesEntity gloves;
	protected BalloonEntity currentBalloon;
	
	public TrainingSession(Game g) {
		super(g);
		canvas = g.getCanvas();
		stopTS = true;
		gameUpdateDelay = DEFAULT_GAME_UPDATE_DELAY;
		balloonsFired = 0;
		effects = new ProxyPerceptionEffect();
	}
	
	/* 
	 * Switches superposition of the gloves and the balloon when one goes
	 * behind the other.
	 */
	private void checkBalloonGlovesPosition(){
		if ( 	currentBalloon != null && 
				currentBalloon.get3DPosition().isBehindZ(gloves.get3DPosition())) 
		{
			universe.removeGameEntity(currentBalloon);
			universe.removeGameEntity(gloves);
			
			universe.addGameEntity(gloves);
			universe.addGameEntity(currentBalloon);
			
			if (Math.abs(deffx)>100){
				System.out.println("but");
			}else{
				System.out.println("pas but");
			} 
		} else {
				deffx = currentBalloon.getPosition().x - gloves.getPosition().x;
				deffy = currentBalloon.getPosition().y - gloves.getPosition().y;
	
				System.out.println("différence x ="+deffx);
				//System.out.println("différence y ="+deffy);1
		}
	}
	
	// Places the balloon and the gloves in the right order
	public void placeBalloonEntity(BalloonEntity be){
		universe.removeGameEntity(gloves);
		universe.removeGameEntity(currentBalloon);
		
		universe.addGameEntity(be);
		universe.addGameEntity(gloves);
		currentBalloon = be;
	}
	
	public void addToScore(int value){
		score[0].setValue(score[0].getValue() + value);
	}

	@Override
	protected void init() {
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();
		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		GoGoalOverlapRulesApplier overlapRules = new GoGoalOverlapRulesApplier();
		
		overlapProcessor.setOverlapRules(overlapRules);
		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapRules.setUniverse(universe);
		
		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		gameBoard.refresh();
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		footballField = new FootballFieldEntity(canvas);
		goal = new GoalEntity(canvas);
		gloves = new GlovesEntity(canvas);
		
		universe.addGameEntity(footballField);
		universe.addGameEntity(goal);
		
		setUpLevel();
	}
	
	@Override
	public void run() {
		stopTS = false;
		
		long start;
		while (!stopTS && !this.isInterrupted()) {
			start = new Date().getTime();
			
			effects.apply();
			gameBoard.paint();
			universe.allOneStepMoves();
			universe.processAllOverlaps();
			checkBalloonGlovesPosition();
			
			try {
				long sleepTime = gameUpdateDelay
						- (new Date().getTime() - start);
				if (sleepTime > 0) {
					Thread.sleep(sleepTime);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void end(){
		effects.terminate();
		stopTS = true;
	}
	
	protected abstract void setUpLevel();
	
	public ProxyPerceptionEffect getProxyPreceptionEffect(){
		return effects;
	}
	
	public GlovesEntity getGloves(){
		return gloves;
	}
	
	public BalloonEntity getCurrentBalloon(){
		return currentBalloon;
	}
}
