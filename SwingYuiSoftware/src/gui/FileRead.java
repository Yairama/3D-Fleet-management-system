package gui;

import objects.Point;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileRead {

    private FileReader fr;
    private BufferedReader br;
    public static List<Point> totalPoints = new ArrayList<>();

    public FileRead(){
        totalPoints.clear();
        try {
            fr = new FileReader(inputFiles.jfilechooser.getSelectedFile());
            //System.out.println("asdasdsadads "+inputFiles.jfilechooser.getSelectedFile().getName());
            br = new BufferedReader(fr);
            String linea=br.readLine();
            while((linea!=null)) {
               // System.out.println("*"+linea+"*");
                if(linea.equals("AcDb3dPolylineVertex") || linea.equals("AcDbPoint") ){
                    //System.out.println("Holii");
                    double x=0;
                    double y=0;
                    double z=0;
                    if(br.readLine().equals(" 10")){x = Double.parseDouble(br.readLine());}
                    if(br.readLine().equals(" 20")){y = Double.parseDouble(br.readLine());}
                    if(br.readLine().equals(" 30")){z = Double.parseDouble(br.readLine());}
                    totalPoints.add(new Point(x,y,z));
                }
                if(linea.equals(" 38")){
                    double x = 0;
                    double y = 0;
                    double z = Double.parseDouble(br.readLine());
                    String controller = br.readLine();
                    while (controller.equals("  0")==false){
                        if(controller.equals(" 10")){x = Double.parseDouble(br.readLine());}
                        if(br.readLine().equals(" 20")){y = Double.parseDouble(br.readLine());}
                        totalPoints.add(new Point(x,y,z));
                        controller=br.readLine();
                    }
                }
                linea = br.readLine();
            }
            br.close();
            Point minxpoint =  Collections.min(totalPoints, Comparator.comparing(s -> s.getX()));
            Point maxxpoint =  Collections.max(totalPoints, Comparator.comparing(s -> s.getX()));
            Point minypoint =  Collections.min(totalPoints, Comparator.comparing(s -> s.getY()));
            Point maxypoint =  Collections.max(totalPoints, Comparator.comparing(s -> s.getY()));
            Point minzpoint =  Collections.min(totalPoints, Comparator.comparing(s -> s.getZ()));
            Point maxzpoint =  Collections.max(totalPoints, Comparator.comparing(s -> s.getZ()));
            double rangex=maxxpoint.getX()-minxpoint.getX();
            double rangey=maxypoint.getY()-minypoint.getY();
            double rangez=maxzpoint.getZ()-minzpoint.getZ();

            System.out.println(" range x: " +  rangex + " range y: " + rangey + " range z: " + rangez);
            System.out.println(" max x " +  maxxpoint.getX() + " minx: " + minxpoint.getX() + " miny " + minypoint.getY()+" minz: " + minzpoint.getZ());



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
     System.out.println("Lista creada con un total de "+totalPoints.size());
    }

}
