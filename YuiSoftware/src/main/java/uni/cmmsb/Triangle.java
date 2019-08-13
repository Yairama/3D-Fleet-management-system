package uni.cmmsb;


import java.util.Arrays;

public  class Triangle{

    Point p1,p2,p3;
    Edge e1, e2, e3;
    int id;

    Triangle(Point p1, Point p2, Point p3, int id)  {
        this.id = id;

        //point are ALWAYS from lowest to highest
        Point[] pointsArray = new Point[]{p1,p2,p3};
        Arrays.sort(pointsArray);
        this.p1 = pointsArray[0];
        this.p2 = pointsArray[1];
        this.p3 = pointsArray[2];

        this.e1 = new Edge(p1, p2, this);
        this.e2 = new Edge(p2, p3, this);
        this.e3 = new Edge(p3, p1, this);
    }

    @Override
    public boolean equals(Object obj) {
        Triangle tr = (Triangle) obj;
        return (tr.p1.equals(this.p1) && tr.p2.equals(this.p2) && tr.p3.equals(this.p3));
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {

        return "T." + p1+","+p2+ ","+ p3;// + ": points(" + this.p1 + "; " + this.p2 + "; " + p3;
    }
}
