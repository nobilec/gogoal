package gogoal.utility;

import java.awt.Point;

public class Point3D {
	private int x, y, z;
	
	public Point3D(){
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Point3D(int x, int y, int z){
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
		return new Point(this.x, this.y);
	}
	
	public void moveTo(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void move(int xOff, int yOff, int zOff){
		this.x += xOff;
		this.y += yOff;
		this.z += zOff;
	}
	
	public boolean isBehindX(Point3D other){ return this.x < other.x; }
	public boolean isBehindY(Point3D other){ return this.y < other.y; }
	public boolean isBehindZ(Point3D other){ return this.z < other.z; }
	
	public int getX(){ return x; }
	public int getY(){ return y; }
	public int getZ(){ return z; }
	
	public void setX(int x){ this.x = x; }
	public void setY(int y){ this.y = y; }
	public void setZ(int z){ this.z = z; }
	
	public String toString(){
		return "{ " + x + " ; " + y + " ; " + z + " }";
	}
}
