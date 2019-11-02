package entities;


public class Punto {
	public float x;
	public float z;
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
	public Punto(double x,double z) {
		this.x=(float) x;
		this.z=(float) z;
		
	}

}

