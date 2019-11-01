package toolbox;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import entities.Punto;

public class FileCreator {
        public FileCreator(ArrayList<Punto> totalPoints){
            try {
                PrintWriter printWriter = new PrintWriter("inputoxd.txt","UTF-8");

                for(Punto punto : totalPoints){
                    printWriter.println(punto.getX()+" "+punto.getZ());
                }
                printWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }