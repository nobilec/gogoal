package gogoal.game;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.image.BufferedImage;
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
	protected ProxyPerceptionEffect effects;
	
	protected FootballFieldEntity footballField;
	protected GoalEntity goal;
	protected GlovesEntity gloves;
	protected BalloonEntity currentBalloon;
	
	public TrainingSession(Game g) {
		super(g);
		canvas = g.getCanvas();
		stopTS = false;
		gameUpdateDelay = DEFAULT_GAME_UPDATE_DELAY;
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

	@Override
	protected void init() {
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();
		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		GoGoalOverlapRulesApplier overlapRules = new GoGoalOverlapRulesApplier();
		
		overlapProcessor.setOverlapRules(overlapRules);
		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapRules.setUniverse(universe);

		int w = GoGoalConfig.getInstance().WIDTH;
		int h = GoGoalConfig.getInstance().HEIGHT;
		
		canvas.setSize(w, h);
		canvas.getParent().setSize(w, h);
		canvas.getParent().validate();
		
		/*
		 * Replaces mouse cursor by blank image.
		 * Source : https://stackoverflow.com/questions/1984071/how-to-hide-cursor-in-a-swing-application#1984117
		 */
		canvas.setCursor(canvas.getToolkit().createCustomCursor(
	            new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
	            "null"));
		
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
		// main game loop 
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
			}
		}
	}

	@Override
	public void end(){
		stopTS = true;
	}
	
	public void setGameUpdateDelay(int gud){
		gameUpdateDelay = gud;
	}
	
	public int getGameUpdateDelay(){
		return gameUpdateDelay;
	}
	
	protected abstract void setUpLevel();
}
