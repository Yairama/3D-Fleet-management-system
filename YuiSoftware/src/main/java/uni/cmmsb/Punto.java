package uni.cmmsb;

public class Punto {
    public double x;
    public double y;
    public double z;
    public int indice;

    public Punto(double x, double y,double z, int indice) {
        this.x = x;
        this.y = y;
        this.z=z;
        this.indice = indice;
    }

    public double getX () {
        return this.x;
    }
    public double getZ () {
        return this.z;
    }


    public double getY () {
        return this.y;
    }

    public int getIndice() {
        return this.indice;
    }

    //Soporte inferior
    public boolean esta_abajo(Punto pivote, Punto c) {
        Recta r = this.creaRecta(pivote);
        return r.por_arriba(c);
    }

    //Soporte superior
    public boolean esta_arriba(Punto pivote, Punto c) {
        Recta r = this.creaRecta(pivote);
        return r.por_abajo(c);
    }

    public Recta creaRecta (Punto b) {
        double vec_x = b.getX() - this.getX();
        double vec_y = b.getY() - this.getY();

        return new Recta (-vec_y/-vec_x, (-this.getY()*vec_x + this.getX()*vec_y)/-vec_x);

    }
}