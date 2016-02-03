package gogoal.game.entities;

import java.util.Random;


public class BalloonEntityTrajectory 
{
	private static final float DEFAULT_Z_SPEED = -5.0f;
	
	private float zSpeed;
	private BalloonEntity owner;
	private boolean v = true; 
	private int x=0;
	private int y=0;
	private float tabx [] = {-2.0f,-1.5f,-1.0f,-0.5f,0.0f,0.5f,1.0f,1.5f,2.0f};
	private float taby [] = {-1.5f,-1.0f,-0.5f,0.0f,0.5f};

	BalloonEntityTrajectory(BalloonEntity owner) {
		super();
		this.owner = owner;
		this.zSpeed = DEFAULT_Z_SPEED;
	}

	void update() {
		// TEST MOUVEMENT
		// les limites [2,-2] & [0.5,-1.5], -5 fixe
		if (v == true){
		x = (int)randRange (0,9);
		y = (int)randRange (0,5);
		v = false;
		//System.out.println("-----------------------------------");
		//System.out.println("x ="+tabx[x]);
		//System.out.println("y="+taby[y]);
		}
		//System.out.println(2.0f);
		owner.get3DPosition().move(tabx[x], taby[y], zSpeed);
	}
	
	private float randRange(int min, int max) {
		Random r = new Random(); 
		int x = min + r.nextInt(max - min);
		float res = (float) x;
		return res;
	}

	float getZSpeed() {
		return zSpeed;
	}

	void setZSpeed(float zSpeed) {
		this.zSpeed = zSpeed;
	}
}
