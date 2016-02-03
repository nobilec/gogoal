package gogoal.game.training_sessions;

import gameframework.game.Game;
import gogoal.game.TrainingSession;
import gogoal.game.entities.BalloonEntity;
import gogoal.game.items.ListCommandItem;
import gogoal.game.items.VisitorBalloon;
import gogoal.game.items.VisitorBalloonImpl;
import gogoal.utility.Point3D;

public class TrainingSession7 extends TrainingSession {

	public TrainingSession7(Game g) {
		super(g);
	}
	
	@Override
	protected void setUpLevel() {
		VisitorBalloon vb = new VisitorBalloonImpl(
				ListCommandItem.getInstance(),
				30, 60);
		
		for ( int i = 1; i <= 20; ++i){
			
			BalloonEntity be = new BalloonEntity(canvas, new Point3D(400, 300, 1000));
			be.setSpeedMult(1.6f + 2.0f / (float) i );
			be.accept(vb);
			bStack.push(be);
		}
	}
}
