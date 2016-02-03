package gogoal.game;

import java.awt.Canvas;
import java.io.IOException;
import java.util.Date;
import java.util.Stack;

import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.GameUniverseViewPortDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;
import gogoal.GoGoal;
import gogoal.game.entities.BalloonEntity;
import gogoal.game.entities.FootballFieldEntity;
import gogoal.game.entities.GlovesEntity;
import gogoal.game.entities.GoalEntity;
import gogoal.game.items.ListCommandItem;
import gogoal.game.items.VisitorBalloon;
import gogoal.game.items.VisitorBalloonImpl;
import gogoal.perception_effects.ProxyPerceptionEffect;
import gogoal.rendering.Camera;
import gogoal.utility.Point3D;

public abstract class TrainingSession extends GameLevelDefaultImpl
{
	private static final int DEFAULT_GAME_UPDATE_DELAY = 20;
	
	private boolean stopTS;
	private int gameUpdateDelay;
	protected Canvas canvas;
	
	protected Stack<BalloonEntity> bStack;
	protected int balloonsFired;
	protected boolean goalChecked;
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
		
		bStack = new Stack<BalloonEntity>();
		gameUpdateDelay = DEFAULT_GAME_UPDATE_DELAY;
		balloonsFired = 0;
		but = 0;
		goalChecked = false;
		effects = new ProxyPerceptionEffect();
	}
	
	/* 
	 * Switches superposition of the gloves and the balloon when one goes
	 * behind the other.
	 */
	private void checkBalloonGlovesPosition(){
		if ( !goalChecked ){
			if ( 	currentBalloon != null && 
					currentBalloon.get3DPosition().isBehindZ(gloves.get3DPosition())) 
			{
				universe.removeGameEntity(currentBalloon);
				universe.removeGameEntity(gloves);
				
				universe.addGameEntity(gloves);
				universe.addGameEntity(currentBalloon);
				
				if  ( Math.abs(deffx) > 70 && Math.abs(deffy) > 50)
				{
					//System.out.println("but");
					but = 1;
				} else {
					//System.out.println("pas but");
					but = 0;
					nextBalloon();
				}
				goalChecked = true;
				
			} else {
					deffx = currentBalloon.getPosition().x - gloves.getPosition().x;
					deffy = currentBalloon.getPosition().y - gloves.getPosition().y;
					//System.out.println("différence x ="+deffx);
					//System.out.println("différence y ="+deffy);
			}
		}
	}
	
	// Score, or not.
	public void checkBalloonCameraPosition(){
		if ( 	currentBalloon != null && 
				currentBalloon.get3DPosition().isBehindZ(Camera.getInstance().getPosition())) 
		{
			nextBalloon();
		}
	}
	
	public void nextBalloon(){
		boolean defeat = false;
		boolean malus = false;
		
		if ( but == 1 ){
			modLife(-1);
			defeat = ((GoGoal) g).isPlayerDefeated();
			malus = true;
		} else {
			addToScore(1);
			malus = false;
		}
		
		if ( !defeat ){
			if ( !bStack.isEmpty() ){
				currentBalloon.executeCommand(malus);
				BalloonEntity nb = bStack.pop();
				placeBalloonEntity(nb);
			} else {
				((GoGoal) g).nextLevel();
			}
		} else {
			currentBalloon = null;
		}
	}
	
	// Places the balloon and the gloves in the right order
	public void placeBalloonEntity(BalloonEntity be){
		universe.removeGameEntity(gloves);
		universe.removeGameEntity(currentBalloon);
		
		universe.addGameEntity(be);
		universe.addGameEntity(gloves);
		currentBalloon = be;
		goalChecked = false;
		but = 0;
	}
	
	public void addToScore(int value){
		score[0].setValue(score[0].getValue() + value);
	}
	
	public void modLife(int value){
		life[0].setValue(life[0].getValue() + value);
		
		if ( life[0].getValue() == 0 ){
			((GoGoal) g).defeat();
		}
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
		placeBalloonEntity(bStack.pop());
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
			checkBalloonCameraPosition();
			
			try {
				long sleepTime = gameUpdateDelay
						- (new Date().getTime() - start);
				if (sleepTime > 0) {
					Thread.sleep(sleepTime);
				}
			} catch (Exception e) {}
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
