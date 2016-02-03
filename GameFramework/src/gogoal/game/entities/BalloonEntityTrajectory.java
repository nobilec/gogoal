package gogoal.game.entities;

import java.util.Random;


public class BalloonEntityTrajectory 
{
	private BalloonEntity owner;
	private boolean v = true; 
	private int x=0;
	private int y=0;
	private float tabx [] = {-2.0f,-1.5f,-1.0f,-0.5f,0.0f,0.5f,1.0f,1.5f,2.0f};
	private float taby [] = {-1.5f,-1.0f,-0.5f,0.0f,0.5f};

	public BalloonEntityTrajectory(BalloonEntity owner) {
		super();
		this.owner = owner;
	}

	public void update() {
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
		owner.get3DPosition().move(tabx[x], taby[y], -5.0f);
	}
	
	private float randRange(int min, int max) {
		Random r = new Random(); 
		int x = min + r.nextInt(max - min);
		float res = (float) x;
		return res;
	}
}
