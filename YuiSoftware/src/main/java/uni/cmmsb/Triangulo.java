package uni.cmmsb;

import java.util.List;

public class Triangulo {
    private Punto a;
    private Punto b;
    private Punto c;
    private int indice;
    public Triangulo(Punto a, Punto b, Punto c, int indice) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.indice=indice;
    }
    public Punto getpunto1 () {
        return this.a;
    }


    public Punto getpunto2 () {
        return this.b;
    }
    public Punto getpunto3 () {
        return this.c;
    }

    public int getIndice() {
        return this.indice;
    }


}
