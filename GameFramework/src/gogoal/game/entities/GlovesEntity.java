package gogoal.game.entities;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;

import gogoal.game.GoGoalConfig;
import gogoal.utility.Point3D;

public class GlovesEntity extends GoGoal3DEntity {

	public GlovesEntity(Canvas defaultCanvas) {
		super(	defaultCanvas, GoGoalConfig.getInstance().GLOVES_IMG, 154, 118,
				new Point3D(400.0f, 300.0f, 100.0f));
	}
	
	@Override
	public void draw(Graphics g){
		Point mPos = MouseInfo.getPointerInfo().getLocation();
		position.moveTo(mPos.x, mPos.y, position.getZ());
		super.draw(g);
	}

}
