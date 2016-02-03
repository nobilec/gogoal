package gogoal.game.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gogoal.GoGoal;
import gogoal.game.GoGoalConfig;
import gogoal.game.items.CommandItem;
import gogoal.game.items.VisitorBalloon;
import gogoal.utility.Point3D;

public class BalloonEntity extends GoGoal3DEntity
{
	protected CommandItem carriedItem;
	
	public BalloonEntity(Canvas defaultCanvas, Point3D pos) {
		super(defaultCanvas, GoGoalConfig.getInstance().BALLOON_IMG, 128, 128, pos);
		carriedItem = null;
	}

	public void accept(VisitorBalloon v){
		v.visit(this);
	}
	
	public void setCarriedItem(CommandItem item){
		this.carriedItem = item;
	}
	
	public void executeCommand(){
		if ( (isLost() && carriedItem.isMalus()) ||
			 !(isLost() && carriedItem.isMalus()) )
		{
			carriedItem.execute();
			carriedItem = null;
		}
	}
	
	public boolean isLost(){
		return false; // A implémenter
	}
	
	/*
	 * Must be changed in order to adapt the the true size of the balloon
	 */
	@Override
	public Rectangle getBoundingBox() {
		Point dimension = image.getDimension();
		return new Rectangle( 
				(int) position.getX(), (int) position.getY(),
				(int) dimension.getX(), (int) dimension.getY());
	}

	@Override
	public void draw(Graphics g) {
		// TEST MOUVEMENT
		position.move(-0.5f, -0.8f, -5.0f);
		
		if ( carriedItem == null ){
			super.draw(g);
		} else {
			super.draw(g, carriedItem.getImage());
			
			/* TEST EXECUTION COMMANDE, A FAIRE QUAND LE BALLON EST 
			 * TERMINE (pris ou perdu)
			 */
			if ( position.getZ() <= 0.0f ){
				executeCommand();
				GoGoal.getInstance().getCurrentTrainingSession().addToScore(1);
				GoGoal.getInstance().nextLevel();
			}
		}
	}

}