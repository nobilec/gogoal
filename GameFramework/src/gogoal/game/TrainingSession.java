package gogoal.game;

import java.awt.Canvas;

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
	protected Canvas canvas;

	public TrainingSession(Game g) {
		super(g);
		canvas = g.getCanvas();
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
		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((GameUniverseViewPortDefaultImpl)gameBoard).setBackground(GoGoalConfig.getInstance().BACKGROUND_IMG);
		gameBoard.refresh();
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		setUpLevel();
	}
	
	protected abstract void setUpLevel();
}
