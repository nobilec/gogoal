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

public abstract class TrainingSession extends GameLevelDefaultImpl
{
	private static final int DEFAULT_GAME_UPDATE_DELAY = 20;
	
	private boolean stopTS;
	private int gameUpdateDelay;
	protected Canvas canvas;

	public TrainingSession(Game g) {
		super(g);
		canvas = g.getCanvas();
		stopTS = false;
		gameUpdateDelay = DEFAULT_GAME_UPDATE_DELAY;
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
		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((GameUniverseViewPortDefaultImpl)gameBoard).setBackground(GoGoalConfig.getInstance().BACKGROUND_IMG);
		gameBoard.refresh();
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		setUpLevel();
	}
	
	@Override
	public void run() {
		stopTS = false;
		// main game loop 
		long start;
		while (!stopTS && !this.isInterrupted()) {
			start = new Date().getTime();
			gameBoard.paint();
			universe.allOneStepMoves();
			universe.processAllOverlaps();
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
