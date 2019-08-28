package objects;

public class Point implements Comparable<Point> {
    public double x;
    public double y;
    public double z;
    public int indice;

    public Point(double x, double y, double z, int indice) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.indice = indice;
    }

    public Point(double x, double y , double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setID(int indice){
        this.indice = indice;
    }

    public double getX () {
        return this.x;
    }

    public double getY () {
        return this.y;
    }

    public double getZ() { return this.z;}

    public int getIndice() {
        return this.indice;
    }

    //Soporte inferior
    public boolean esta_abajo(Point pivote, Point c) {
        straight r = this.creaRecta(pivote);
        return r.por_arriba(c);
    }

    //Soporte superior
    public boolean esta_arriba(Point pivote, Point c) {
        straight r = this.creaRecta(pivote);
        return r.por_abajo(c);
    }

    public straight creaRecta (Point b) {
        double vec_x = b.getX() - this.getX();
        double vec_y = b.getY() - this.getY();

        return new straight(-vec_y/-vec_x, (-this.getY()*vec_x + this.getX()*vec_y)/-vec_x);

    }


    @Override
    public int compareTo(Point other) {
        return
                (this.x < other.x) ? -1 :
                        (this.x > other.x) ? +1 :
                                (this.y < other.y) ? -1 :
                                        (this.y > other.y) ? +1 :
                                                0;
    }

}