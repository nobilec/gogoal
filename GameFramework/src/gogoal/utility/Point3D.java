package gogoal.utility;

import java.awt.Point;

public class Point3D {
	private float x, y, z;
	
	public Point3D(){
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Point3D(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float distance(Point3D other){
		return (float) Math.sqrt(
				Math.pow(this.x - other.x, 2) +
				Math.pow(this.y - other.y, 2) +
				Math.pow(this.z - other.z, 2));
	}
	
	public Point get2DComponent(){
		return new Point((int) this.x, (int)this.y);
	}
	
	public void moveTo(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void move(float xOff, float yOff, float zOff){
		this.x += xOff;
		this.y += yOff;
		this.z += zOff;
	}
	
	public boolean isBehindX(Point3D other){ return this.x < other.x; }
	public boolean isBehindY(Point3D other){ return this.y < other.y; }
	public boolean isBehindZ(Point3D other){ return this.z < other.z; }
	
	public float getX(){ return x; }
	public float getY(){ return y; }
	public float getZ(){ return z; }
	
	public void setX(float x){ this.x = x; }
	public void setY(float y){ this.y = y; }
	public void setZ(float z){ this.z = z; }
	
	public String toString(){
		return "{ " + x + " ; " + y + " ; " + z + " }";
	}
}
