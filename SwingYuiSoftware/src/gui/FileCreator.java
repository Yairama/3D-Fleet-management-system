package gui;


import objects.Point;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class FileCreator {
        public FileCreator(List<Point> totalPoints){
            try {
                // Si el archivo no existe es creado
                PrintWriter printWriter = new PrintWriter("input.txt","UTF-8");

                for(Point point : totalPoints){
                    printWriter.println(point.getX()+";"+point.getY()+";"+point.getZ());
                }
                printWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }