package objects;

public class Triangle {
    private Point a;
    private Point b;
    private Point c;
    private int indice;
    public Triangle(Point a, Point b, Point c, int indice) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.indice=indice;
    }
    public Point getpunto1 () {
        return this.a;
    }


    public Point getpunto2 () {
        return this.b;
    }
    public Point getpunto3 () {
        return this.c;
    }

    public int getIndice() {
        return this.indice;
    }


}
