package uni.cmmsb;

public class Point implements Comparable<Point>{

    double x;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public int getId() {
        return id;
    }

    double y;
    double z;
    int id;

    Point(double x, double y, double z, int id) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
    }
    Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    @Override
    public boolean equals(Object obj) {
        Point p = (Point) obj;
        return (p.x == this.x && p.y == this.y && p.z == this.z);
    }

    @Override
    public int hashCode() {
//            int hash = 7;
//            hash = 71 * hash + Double.valueOf(this.x).hashCode();
//            hash = 71 * hash + Double.valueOf(this.y).hashCode();
//            hash = 71 * hash + Double.valueOf(this.z).hashCode();
//            return hash;

        return id;
    }

    @Override
    public String toString() {
        return "P" + id;// + "|" + "x= " + this.x + ", y = " + this.y + ", z = " + this.z;
    }

    @Override
    public int compareTo(Point o) {
        if (this.x > o.x ) {
            return 1;
        } else {
            return -1;
        }
    }
}


