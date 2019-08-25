package uni.cmmsb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

public class TriangulacionFinal {


    //Ordenar nube de puntos de derecha a izquierda
    public static void quickSort(Punto vector[], int inicio, int fin){
        if(inicio>=fin) return;
        double pivote=vector[inicio].getX();
        int elemIzq=inicio+1;
        int elemDer=fin;
        while(elemIzq<=elemDer){
            while(elemIzq<=fin && vector[elemIzq].getX()<pivote){
                elemIzq++;
            }
            while(elemDer>inicio && vector[elemDer].getX()>=pivote){
                elemDer--;
            }
            if(elemIzq<elemDer){
                Punto temp=vector[elemIzq];
                vector[elemIzq]=vector[elemDer];
                vector[elemDer]=temp;
            }
        }

        if(elemDer>inicio){
            Punto temp=vector[inicio];
            vector[inicio]=vector[elemDer];
            vector[elemDer]=temp;
        }
        quickSort(vector, inicio, elemDer-1);
        quickSort(vector, elemDer+1, fin);
    }

    //Devuelve true or false si el punto es soporte o no
    public static boolean es_soporte_inferior_izquierda(Vector<Punto> cierre, int pos_pivote_izquierda, int pos_pivote_derecha, Punto[] nube, Punto limite) {
        boolean resultado = true;
        int contador = pos_pivote_izquierda - 1;
        while (contador >=0  && resultado && !limite.equals(cierre.elementAt(contador))) {
            resultado = cierre.elementAt(pos_pivote_izquierda).esta_abajo(cierre.elementAt(pos_pivote_derecha), cierre.elementAt(contador));
            contador--;
        }
        return resultado;
    }
    //Devuelve true or false si el punto es soporte o no
    public static boolean es_soporte_superior_izquierda(Vector <Punto> cierre, int pos_pivote_izquierda, int pos_pivote_derecha, Punto[] nube, Punto limite) {
        boolean resultado = true;
        int contador = pos_pivote_izquierda - 1;
        while (contador >=0  && resultado && !limite.equals(cierre.elementAt(contador))) {
            resultado = cierre.elementAt(pos_pivote_izquierda).esta_arriba(cierre.elementAt(pos_pivote_derecha), cierre.elementAt(contador));
            contador--;
        }
        return resultado;
    }
    //Devuelve true or false si el punto es soporte o no
    public static boolean es_soporte_inferior_derecha(Vector <Punto> cierre, int pos_pivote_izquierda, int pos_pivote_derecha) {
        boolean resultado = true;
        int contador = pos_pivote_derecha +1 ;
        while (contador < cierre.size() && resultado) {
            resultado = cierre.elementAt(pos_pivote_derecha).esta_abajo(cierre.elementAt(pos_pivote_izquierda), cierre.elementAt(contador));
            contador++;
        }
        return resultado;
    }

    //Devuelve true or false si el punto es soporte o no
    public static boolean es_soporte_superior_derecha(Vector <Punto> cierre, int pos_pivote_izquierda, int pos_pivote_derecha) {
        boolean resultado = true;
        int contador = pos_pivote_derecha +1 ;
        while (contador < cierre.size() && resultado) {
            resultado = cierre.elementAt(pos_pivote_derecha).esta_arriba(cierre.elementAt(pos_pivote_izquierda), cierre.elementAt(contador));
            contador++;
        }
        return resultado;
    }

    public static void une_hacia_abajo (Vector<Punto> cierre, int [][] uniones, int pos_pivote, Punto[]nube, Punto limite) {
        //Calcular si los dos pivotes son soportes inferiores
        Punto pivote_izq = cierre.elementAt(pos_pivote);
        Punto pivote_dcha = cierre.elementAt(pos_pivote + 1);
        int nueva_pos_piv_izq = pos_pivote;
        int nueva_pos_piv_dcha = pos_pivote + 1;
        boolean es_soporte_izq = es_soporte_inferior_izquierda(cierre, nueva_pos_piv_izq, nueva_pos_piv_dcha, nube, limite);
        boolean es_soporte_dcho = es_soporte_inferior_derecha(cierre, nueva_pos_piv_izq, nueva_pos_piv_dcha);

        while((!es_soporte_izq || !es_soporte_dcho) && nueva_pos_piv_izq >0 && nueva_pos_piv_dcha < cierre.size()) {

            //Busca el siguiente pivote, lo une y comprueba si los pivotes son soportes
            if(!es_soporte_izq) {
                boolean encontrado_izq = false;
                int contador_izq = nueva_pos_piv_izq;
                while(!encontrado_izq) {
                    contador_izq--;
                    encontrado_izq = !cierre.elementAt(nueva_pos_piv_izq).esta_abajo(cierre.elementAt(nueva_pos_piv_dcha),cierre.elementAt(contador_izq));

                }
                if(cierre.elementAt(nueva_pos_piv_izq).equals(pivote_izq)) {

                }else {
                    cierre.remove(cierre.elementAt(nueva_pos_piv_izq));
                }
                nueva_pos_piv_izq = contador_izq;
                //Uno los puntos
                System.out.println("El punto " + cierre.elementAt(nueva_pos_piv_izq).getIndice() + " se une a " + cierre.elementAt(nueva_pos_piv_dcha).getIndice());
                uniones[cierre.elementAt(nueva_pos_piv_izq).getIndice()][cierre.elementAt(nueva_pos_piv_dcha).getIndice()] = 1;
                uniones[cierre.elementAt(nueva_pos_piv_dcha).getIndice()][cierre.elementAt(nueva_pos_piv_izq).getIndice()] = 1;
                es_soporte_izq = es_soporte_inferior_izquierda(cierre, nueva_pos_piv_izq, nueva_pos_piv_dcha, nube, limite);
                es_soporte_dcho = es_soporte_inferior_derecha(cierre, nueva_pos_piv_izq, nueva_pos_piv_dcha);
            }

            //Busca el siguiente pivote, lo une y comprueba si la combinacion de pivotes son soportes
            if(!es_soporte_dcho) {
                boolean encontrado_dcha = false;
                int contador_dcha = nueva_pos_piv_dcha;
                while(!encontrado_dcha) {
                    contador_dcha++;
                    encontrado_dcha = !cierre.elementAt(nueva_pos_piv_dcha).esta_abajo(cierre.elementAt(nueva_pos_piv_izq), cierre.elementAt(contador_dcha));
                }

                if(cierre.elementAt(nueva_pos_piv_dcha).equals(pivote_dcha)) {

                }else {
                    System.out.println("El punto " + cierre.elementAt(nueva_pos_piv_dcha).getIndice() + " ha salido del cierre");
                    cierre.remove(cierre.elementAt(nueva_pos_piv_dcha));
                    contador_dcha--;
                }

                nueva_pos_piv_dcha = contador_dcha;
                //Uno los puntos
                System.out.println("El punto " + cierre.elementAt(nueva_pos_piv_izq).getIndice() + " se une a " + cierre.elementAt(nueva_pos_piv_dcha).getIndice());
                uniones[cierre.elementAt(nueva_pos_piv_izq).getIndice()][cierre.elementAt(nueva_pos_piv_dcha).getIndice()] = 1;
                uniones[cierre.elementAt(nueva_pos_piv_dcha).getIndice()][cierre.elementAt(nueva_pos_piv_izq).getIndice()] = 1;
                es_soporte_izq = es_soporte_inferior_izquierda(cierre, nueva_pos_piv_izq, nueva_pos_piv_dcha, nube, limite);
                es_soporte_dcho = es_soporte_inferior_derecha(cierre, nueva_pos_piv_izq, nueva_pos_piv_dcha);
            }
        }

    }

    public static void une_hacia_arriba (Vector<Punto> cierre, int [][] uniones, int pos_pivote, Punto[]nube, Punto limite) {
        Punto pivote_izq = cierre.elementAt(pos_pivote);
        Punto pivote_dcha = cierre.elementAt(pos_pivote + 1);
        int nueva_pos_piv_izq = pos_pivote;
        int nueva_pos_piv_dcha = pos_pivote + 1;
        boolean es_soporte_izq = es_soporte_superior_izquierda(cierre, nueva_pos_piv_izq, nueva_pos_piv_dcha, nube, limite);
        boolean es_soporte_dcho = es_soporte_superior_derecha(cierre, nueva_pos_piv_izq, nueva_pos_piv_dcha);

        //Donde situar el contador para que encuentre el punto que queremos. (Maxima pendiente)
        double pendiente = -9990;
        for(int i = pos_pivote + 1; i  < cierre.size() ; i++) {
            Recta prueba = cierre.elementAt(pos_pivote).creaRecta(cierre.elementAt(i));
            if(prueba.getCoefX() > pendiente) {
                pendiente = prueba.getCoefX();
                nueva_pos_piv_dcha = i - 1;
            }
        }

        if(es_soporte_dcho) {
            nueva_pos_piv_dcha++;
        }


        while((!es_soporte_izq || !es_soporte_dcho) && nueva_pos_piv_izq >0 && nueva_pos_piv_dcha < cierre.size()) {

            //Busca el siguiente pivote, lo une y comprueba si el soporte es pivote
            if(!es_soporte_izq) {
                boolean encontrado_izq = false;
                int contador_izq = nueva_pos_piv_izq;
                while(!encontrado_izq) {
                    contador_izq--;
                    encontrado_izq = !cierre.elementAt(nueva_pos_piv_izq).esta_arriba(cierre.elementAt(nueva_pos_piv_dcha),cierre.elementAt(contador_izq));

                }
                if(cierre.elementAt(nueva_pos_piv_izq).equals(pivote_izq)) {
                    if(es_soporte_inferior_izquierda(cierre, nueva_pos_piv_izq, nueva_pos_piv_dcha, nube, limite)){
                        System.out.println("El punto " + cierre.elementAt(nueva_pos_piv_izq).getIndice()+ " ha salido del cierre");
                        cierre.remove(cierre.elementAt(nueva_pos_piv_izq));
                        nueva_pos_piv_dcha--;
                        pos_pivote--;

                    }
                }else {
                    cierre.remove(cierre.elementAt(nueva_pos_piv_izq));
                }
                nueva_pos_piv_izq = contador_izq;
                //Uno los puntos
                System.out.println("El punto " + cierre.elementAt(nueva_pos_piv_izq).getIndice() + " se une a " + cierre.elementAt(nueva_pos_piv_dcha).getIndice());
                uniones[cierre.elementAt(nueva_pos_piv_izq).getIndice()][cierre.elementAt(nueva_pos_piv_dcha).getIndice()] = 1;
                uniones[cierre.elementAt(nueva_pos_piv_dcha).getIndice()][cierre.elementAt(nueva_pos_piv_izq).getIndice()] = 1;
                es_soporte_izq = es_soporte_superior_izquierda(cierre, nueva_pos_piv_izq, nueva_pos_piv_dcha, nube, limite);
                es_soporte_dcho = es_soporte_superior_derecha(cierre, nueva_pos_piv_izq, nueva_pos_piv_dcha);
            }

            //Busca el siguiente soporte, lo une y comprueba si el punto es soporte
            if(!es_soporte_dcho) {

                boolean encontrado_dcha = false;
                int contador_dcha = nueva_pos_piv_dcha;
                while(!encontrado_dcha) {
                    contador_dcha++;
                    encontrado_dcha = !cierre.elementAt(nueva_pos_piv_dcha).esta_arriba(cierre.elementAt(nueva_pos_piv_izq), cierre.elementAt(contador_dcha));
                }

                if(cierre.elementAt(nueva_pos_piv_dcha).equals(pivote_dcha)) {
                    if(!es_soporte_inferior_derecha(cierre, nueva_pos_piv_izq, pos_pivote+1)){
                        cierre.remove(cierre.elementAt(nueva_pos_piv_dcha));
                        contador_dcha--;
                    }
                }else {
                    cierre.remove(cierre.elementAt(nueva_pos_piv_dcha));
                    contador_dcha--;
                }

                nueva_pos_piv_dcha = contador_dcha;
                //Uno los puntos
                System.out.println("El punto " + cierre.elementAt(nueva_pos_piv_izq).getIndice() + " se une a " + cierre.elementAt(nueva_pos_piv_dcha).getIndice());
                uniones[cierre.elementAt(nueva_pos_piv_izq).getIndice()][cierre.elementAt(nueva_pos_piv_dcha).getIndice()] = 1;
                uniones[cierre.elementAt(nueva_pos_piv_dcha).getIndice()][cierre.elementAt(nueva_pos_piv_izq).getIndice()] = 1;
                es_soporte_izq = es_soporte_superior_izquierda(cierre, nueva_pos_piv_izq, nueva_pos_piv_dcha, nube, limite);
                es_soporte_dcho = es_soporte_superior_derecha(cierre, nueva_pos_piv_izq, nueva_pos_piv_dcha);
            }
        }
    }
    public static boolean serepite(List<Triangulo> triangulos,Triangulo triangulito){
        boolean repetido=false;
        Punto p1=triangulito.getpunto1();
        Punto p2=triangulito.getpunto2();
        Punto p3=triangulito.getpunto3();
        for(Triangulo triangulo:triangulos){
            Punto pp1=triangulo.getpunto1();
            Punto pp2=triangulo.getpunto2();
            Punto pp3=triangulo.getpunto3();
            if(p1==pp2){
                if((p2==pp3)&(p3==pp1)){
                    repetido=true;
                }
                if((p2==pp1)&(p3==pp3)){
                    repetido=true;
                }
                if((p3==pp3)&(p2==pp1)){
                    repetido=true;
                }
                if((p3==pp1)&(p2==pp3)){
                    repetido=true;
                }
            }
            else if (p1==pp3){
                if((p2==pp1)&(p3==pp2)){
                    repetido=true;
                }
                if((p2==pp2)&(p3==pp1)){
                    repetido=true;
                }
                if((p3==pp1)&(p2==pp2)){
                    repetido=true;
                }
                if((p3==pp2)&(p2==pp1)){
                    repetido=true;
                }

            }


        }

        return repetido;}
    public static void triangula (Vector<Punto> cierre, int [][] uniones, Punto nube [], Punto limite) {
        System.out.println();
        System.out.println("Siguiente nivel");
        //Caso base
        if(nube.length <= 3) {
            for(int i = 0; i < nube.length; i++) {
                for(int j = i + 1; j < nube.length; j ++ ) {
                    System.out.println("El punto " + nube[i].getIndice() + " se une al " + nube[j].getIndice());
                    //Unir los puntos
                    uniones[nube[i].getIndice()] [nube[j].getIndice()] = 1;
                    uniones[nube[j].getIndice()] [nube[i].getIndice()] = 1;
                }
            }
            //Los meto en el cierre
            for(int i = 0; i < nube.length; i++) {
                cierre.add(nube[i]);
            }
            return;
        }

        //Si no es caso base, divido la nube de puntos
        Punto[] nube1 = new Punto [nube.length/2];
        Punto[] nube2 = new Punto [nube.length/2];
        if(nube.length%2 != 0) {
            nube2 = new Punto [nube.length/2 +1];
        }
        for(int i = 0; i < nube1.length; i++) {
            nube1[i] = nube[i];
        }
        for(int i = nube1.length; i < nube.length; i ++) {
            nube2[i - nube1.length] = nube[i];
        }

        //Guardo el indice del pivote izquierdo (Pos pivote derecho = Pos pivote izquierdo + 1)
        int pivote1 = nube1[nube1.length - 1].getIndice();

        //Triangulo los subproblemas
        triangula (cierre, uniones, nube1, new Punto(1000, 1000, 1000));
        triangula (cierre, uniones, nube2, nube1[nube1.length-1]);

        //Localizamos los dos pivotes
        int pos_pivote;
        for(pos_pivote = 0; pos_pivote < cierre.size(); pos_pivote++) {
            if(cierre.elementAt(pos_pivote).getIndice() == pivote1) {
                break;
            }
        }
        // el pivote de la izquierda va a ser el de la nube1 y no hay nube1 sin nube2, por eso siempre un pivote izquierdo tendra uno derecho
        //Unimos los dos pivotes (Matriz simétrica, en ambos lados de la matriz)
        System.out.println();
        System.out.println("Union de pivotes");
        System.out.println("El punto " + cierre.elementAt(pos_pivote).getIndice()+ " se une al " + cierre.elementAt(pos_pivote + 1).getIndice());
        uniones[cierre.elementAt(pos_pivote).getIndice()][cierre.elementAt(pos_pivote + 1).getIndice()] = 1;
        uniones[cierre.elementAt(pos_pivote + 1).getIndice()][cierre.elementAt(pos_pivote).getIndice()] = 1;
        System.out.println();
        System.out.println();
        System.out.println("Ahora hacia abajo");

        //Inicia la union de las dos subtriangulaciones hacia abajo
        une_hacia_abajo(cierre, uniones, pos_pivote, nube, limite);
        System.out.println();
        System.out.println("Ahora hacia arriba");

        //Inicia las subtriangulaciones hacia arriba
        une_hacia_arriba(cierre, uniones, pos_pivote, nube, limite);
        System.out.println();
        System.out.println("Se acabó");

        }




    public static void main(String[] args) {


        // TODO code application logic here

        Punto [] nube = new Punto [12];
        nube[0] = new Punto (2.5, 8.5, 0);
        nube[1] = new Punto (0.5, 6, 1);
        nube[2] = new Punto (1, 7.5, 2);
        nube[3] = new Punto (6, 4, 3);
        nube[4] = new Punto (3, 1, 4);
        nube[5] = new Punto (7, 0.25, 5);
        nube[6] = new Punto (2, 2.5, 6);
        nube[7] = new Punto (9, 1.5, 7);
        nube[8] = new Punto (4.5, 9.5, 8);
        nube[9] = new Punto (8, 6.5, 9);
        nube[10] = new Punto (11, 0.5, 10);
        nube[11] = new Punto (12, 2, 11);

        quickSort(nube, 0, nube.length - 1);
        Vector<Punto> cierre = new Vector<>();
        int [][] uniones = new int [nube.length][nube.length];
        triangula(cierre, uniones,nube, new Punto(1000, 1000, 1000));
        ArrayList<Triangulo> triangulos=new ArrayList<Triangulo>();
        triangulos=sacartriangulos(uniones, nube);
        for(int i=0;i<triangulos.size();i++) {
            System.out.println("triangulo " + i + " tiene puntos de indice: " + triangulos.get(i).getpunto1().indice +" " +triangulos.get(i).getpunto2().indice + " " + triangulos.get(i).getpunto3().indice);
        }
        //for (int i=0; i<nube.length;i++){
          //  System.out.println("");
          //  for (int j=0; j<nube.length;j++) {
          //      System.out.print(uniones[i][j] + " ");
          //  }


   // }
}
public   static ArrayList<Triangulo> sacartriangulos(int [][] uniones, Punto nube [] ){
        ArrayList<Triangulo> triangulos=new ArrayList<Triangulo>();
        int indice=1;
        for (int i=0; i<uniones.length; i++){
            for(int j=i+1;j<uniones.length;j++){
                if (uniones[i][j]==1) {
                    int punto1=i;
                    int punto2=j;
                    for (int k=0; k<uniones.length;k++){
                        if ((uniones[j][k]==1)&(uniones[k][i]==1)){
                            Triangulo nuevotriangulo=new Triangulo(nube[i], nube[j], nube[k], indice);
                            if (!serepite(triangulos, nuevotriangulo))
                            triangulos.add(new Triangulo(nube[i], nube[j], nube[k], indice));
                            indice++;
                        }
                    }
                }
            }
        }


return triangulos;}
}
