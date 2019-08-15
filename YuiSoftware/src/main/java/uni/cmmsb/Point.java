package uni.cmmsb;

public class Point
{
    private double x;
    private double y;
    private double angle;
    public Point() { }
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double X() {
        return x;
    }

    public double Y() {
        return y;
    }

    public void setAngle(double angle){
        this.angle = angle;
    }

    public double getAngle(){
        return this.angle;
    }

    public void Set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void SetAngle(double x) {
        this.angle = x;
    }

    public Boolean Equals(Point other) {
        return (this.x == other.x && this.y == other.y);
    }

    public String ToString() {
    return "(" + this.x + "," + this.y + ")";
}
}