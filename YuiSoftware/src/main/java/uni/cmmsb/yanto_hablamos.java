package uni.cmmsb;

import java.util.*;

public class yanto_hablamos {
    //metodo para determinar si el punto pt esta en el circulo circunscrito poro v1,v2,v3



    // metodo pa saber si un punto esta en un triangulo kge siono
    public static boolean inTriangle(Point pt, Triangle triangle){
        if(inCircle(pt,triangle.p1,triangle.p2,triangle.p3)){return true;}
        else{return false;}

    }
    private static boolean ccw(double ax, double ay, double bx, double by, double cx, double cy) {//counter-clockwise
        return (bx - ax) * (cy - ay) - (cx - ax) * (by - ay) > 0; // area de la pinga, digo triangulo
    }
    private static boolean inCircle(Point pt, Point v1, Point v2, Point v3) {

        double ax = v1.x;
        double ay = v1.y;
        double bx = v2.x;
        double by = v2.y;
        double cx = v3.x;
        double cy = v3.y;
        double dx = pt.x;
        double dy = pt.y;

        double ax_ = ax - dx;
        double ay_ = ay - dy;
        double bx_ = bx - dx;
        double by_ = by - dy;
        double cx_ = cx - dx;
        double cy_ = cy - dy;
        double det = ((ax_ * ax_ + ay_ * ay_) * (bx_ * cy_ - cx_ * by_) -
                (bx_ * bx_ + by_ * by_) * (ax_ * cy_ - cx_ * ay_) +
                (cx_ * cx_ + cy_ * cy_) * (ax_ * by_ - bx_ * ay_)
        );

        if (ccw(ax, ay, bx, by, cx, cy)) {
            return (det > 0);
        } else {
            return (det < 0);
        }

    }
    //metodo para ver si dos lineas se intersectan
    public static boolean secruza(Edge borde1, Edge borde2){
        return (intersect(borde1.p1,borde1.p2,borde2.p1,borde2.p2)) ? true : false;
    }
    //orientacion de un plano por 3 puntos
    public static int orientation(Point p, Point q, Point r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX())
                - (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0.0)
            return 0; // colinear
        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }
//metodo para saber si una lina p1q1 se intersecta con una linea p2q2
    public static boolean intersect(Point p1, Point q1, Point p2, Point q2) {

        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4){
            return true;}
else{
        return false;}
    }
    //metodo que te devuelve el borde base entre 2 listas de puntos ordenados por X
    public static Edge getbordebase(List<Point> lista1,List<Point> lista2){

        Point Min1;
        Point Min2;
        Point puntoizquierda;
        Point puntoderecha;
        Point[] array1= (Point[]) lista1.toArray();
        Point[] array2= (Point[]) lista2.toArray();

        Min1= array1[0];
        Min2=array2[0];
        Edge gigimitre=new Edge(Min1,Min2);
        if(gigimitre.p1.x<gigimitre.p2.x) {
            puntoderecha = gigimitre.p2;
            puntoizquierda=gigimitre.p1;
        }
        else{puntoderecha=gigimitre.p1;
            puntoizquierda=gigimitre.p2;
        }
        Edge base=new Edge(puntoizquierda,puntoderecha);


    return base;
    }
    //metodo para hallar a los candidatos por la parte derecha de un segmento ( o borde xd)
    // input (borde base, primera lista de segmentos, segunda lista de segmentos)
    // las listas deben ordenadas por x
public static HashSet<Point> candidatosderecha(Edge bordebase, HashSet<Edge> lista1, HashSet<Edge> lista2){
        HashSet<Point> chupetintrujillo=new HashSet<Point>();
        for (Edge edge:lista2){
            if (bordebase.p2==edge.p1) {
                chupetintrujillo.add(edge.p2);
            }
            else if(bordebase.p2==edge.p2){
                chupetintrujillo.add(edge.p1);
            }
        }

       return chupetintrujillo;
    }

    //metodo para hallar los bordes(de un hashset de edges) que contengan a cierto punto ( creo que esto tomara mucho tiempo)
    public static HashSet<Edge> getEdges(Point point, HashSet<Edge> edgelist){
        HashSet susydiaz= new HashSet<Edge>();
        for (Edge edge : edgelist){
            if (edge.p1==point){
                susydiaz.add(edge);
            }
            if (edge.p2==point){
                susydiaz.add(edge);
            }
        }
return susydiaz;
    }
//metodo para hallar los bordes de una lista de puntos (de 3 o 2 puntos)
public static HashSet<Edge> hallaredgesfrom2listas(List<Point> listadepuntos){
        HashSet<Edge> listadebordes = new HashSet<Edge>();
        if (listadepuntos.size()==2){
            Edge borde1 = new Edge(listadepuntos.get(1),listadepuntos.get(2));
            listadebordes.add(borde1);
        }
        if (listadepuntos.size()==3){
            Edge borde1 = new Edge(listadepuntos.get(1),listadepuntos.get(2));
            Edge borde2 = new Edge(listadepuntos.get(2),listadepuntos.get(3));
            Edge borde3 = new Edge(listadepuntos.get(3),listadepuntos.get(1));
            listadebordes.add(borde1);
            listadebordes.add(borde2);
            listadebordes.add(borde3);
        }

return listadebordes;}
}
