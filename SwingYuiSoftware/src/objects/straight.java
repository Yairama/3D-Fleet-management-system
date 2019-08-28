package objects;

public class straight {
    private double coefX;
    private double constante;


    public straight(double x, double a) {
        coefX = x;
        constante = a;
    }

    public double getCoefX () {
        return this.coefX;
    }

    public double getConstante () {
        return this.constante;
    }

    //Soporte inferior
    public boolean por_arriba (Point c) {
        boolean resultado = true;
        if(c.getX()*this.getCoefX() + this.getConstante() >= c.getY()) {
            resultado = false;
        }
        return resultado;
    }

    //Soporte superior
    public boolean por_abajo (Point c) {
        boolean resultado = true;
        if(c.getX()*this.getCoefX() + this.getConstante() <= c.getY()) {
            resultado = false;
        }
        return resultado;
    }
}