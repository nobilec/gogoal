package gogoal;

import gameframework.game.Game;
import gogoal.game.TrainingSession;
import gogoal.game.entities.BalloonEntity;
import gogoal.game.items.ListCommandItem;
import gogoal.game.items.VisitorBalloon;
import gogoal.game.items.VisitorBalloonImpl;
import gogoal.utility.Point3D;

public class TrainingSessionOne extends TrainingSession
{
	public TrainingSessionOne(Game g) {
		super(g);
	}

	@Override
	protected void setUpLevel() {
		BalloonEntity be = new BalloonEntity(canvas, new Point3D(400, 300, 1000));
		
		// TEST ITEM :
		VisitorBalloon vb = new VisitorBalloonImpl(ListCommandItem.getInstance());
		be.accept(vb);
		
		placeBalloonEntity(be);
	}

}
