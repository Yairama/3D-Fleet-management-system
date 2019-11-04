package entities;


public class Punto {
	public float x;
	public float z;
	public float y;
	public double getX;
	public double getZ;

	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}


	public float getZ() {
		return z;
	}


	public void setZ(float z) {
		this.z = z;
	}
	public float getY() {
		return y;
	}


	public void setY(float x) {
		this.y = x;
	}
	public Punto(double x,double z) {
		this.x=(float) x;
		this.z=(float) z;
		
	}


	public Punto(float cx, float cz, float cz2) {
		this.x=(float) x;
		this.z=(float) z;
		this.y=(float) y;
		
		
	}


}

