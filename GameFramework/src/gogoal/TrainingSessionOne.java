package gogoal;

import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.GameUniverseViewPortDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;
import gogoal.game.GoGoalOverlapRulesApplier;
import gogoal.game.entities.BalloonEntity;

import java.awt.Canvas;
import java.awt.Point;

public class TrainingSessionOne extends GameLevelDefaultImpl
{
	Canvas canvas;

	@Override
	protected void init() {
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();
		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		GoGoalOverlapRulesApplier overlapRules = new GoGoalOverlapRulesApplier();
		
		overlapProcessor.setOverlapRules(overlapRules);
		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapRules.setUniverse(universe);

		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		BalloonEntity be = new BalloonEntity(canvas, new Point(200, 200));
		
		universe.addGameEntity(be);
	}

	public TrainingSessionOne(Game g) {
		super(g);
		canvas = g.getCanvas();
	}

}
