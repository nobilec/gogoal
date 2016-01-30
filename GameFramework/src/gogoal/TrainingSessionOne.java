package gogoal;

import gameframework.game.Game;
import gogoal.game.TrainingSession;
import gogoal.game.entities.BalloonEntity;

import java.awt.Point;

public class TrainingSessionOne extends TrainingSession
{
	public TrainingSessionOne(Game g) {
		super(g);
	}

	@Override
	protected void setUpLevel() {
		BalloonEntity be = new BalloonEntity(canvas, new Point(400, 300));
		universe.addGameEntity(be);
	}

}
