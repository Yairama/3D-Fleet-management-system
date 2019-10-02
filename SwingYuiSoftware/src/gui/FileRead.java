package gui;

import objects.Point;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
     System.out.println("Lista creada con un total de "+totalPoints.size());
    }

}
