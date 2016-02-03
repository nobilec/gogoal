package gogoal.game.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gogoal.game.GoGoalConfig;
import gogoal.game.items.CommandItem;
import gogoal.game.items.VisitorBalloon;
import gogoal.utility.Point3D;

public class BalloonEntity extends GoGoal3DEntity
{
	protected BalloonEntityTrajectory trajectory;
	protected CommandItem carriedItem;
	
	public BalloonEntity(Canvas defaultCanvas, Point3D pos) {
		super(defaultCanvas, GoGoalConfig.getInstance().BALLOON_IMG, 128, 128, pos);
		trajectory = new BalloonEntityTrajectory(this);
		carriedItem = null;
	}
	
	public float getSpeedMult() {
		return trajectory.getSpeedMult();
	}

	public void setSpeedMult(float speedMult) {
		trajectory.setSpeedMult(speedMult);
	}

	public void accept(VisitorBalloon v){
		v.visit(this);
	}
	
	public void setCarriedItem(CommandItem item){
		this.carriedItem = item;
	}
	
	public boolean carriesItem(){
		return carriedItem != null;
	}
	
	public void executeCommand(boolean lost){
		if ( carriedItem != null ){
			if ( (lost && carriedItem.isMalus()) ||
				 (!lost && !carriedItem.isMalus()) )
			{
				carriedItem.execute();
				carriedItem = null;
			}
		}
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
		trajectory.update();
		
		if ( carriedItem == null ){
			super.draw(g);
		} else {
			super.draw(g, carriedItem.getImage());
		}
	}
}