package gui;

        import objects.Point;
        import org.kabeja.dxf.*;

        import java.io.FileReader;
        import java.util.*;

public class DXFReader {

    private static List<DXFLayer> layerList = new ArrayList<>();
    public static List<Point> totalPoints = new ArrayList<>();
    private static List<DXFPoint> points = new ArrayList<>();
    private static List<DXFPolyline> polylines = new ArrayList<>();
    private static List<DXFLWPolyline> lwPolylines = new ArrayList<>();

    public DXFReader() {System.out.println("Point reading started");}

    public void setTotalPoints(){
        FileRead fileRead = new FileRead();
        FileCreator fileCreator = new FileCreator(FileRead.totalPoints);

        if(totalPoints!=null){System.out.println("list of points created successfully, sise of: "+totalPoints.size());}
        else {System.out.println("Error in the creation");}
    }



}
