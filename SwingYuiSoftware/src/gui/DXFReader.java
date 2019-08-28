package gui;

import objects.Point;
import org.kabeja.dxf.*;
import triangulation.finalTriangulation;

import java.util.*;

public class DXFReader {

    private static List<DXFLayer> layerList = new ArrayList<>();
    public static List<Point> totalPoints = new ArrayList<>();
    private static List<DXFPoint> points = new ArrayList<>();
    private static List<DXFPolyline> polylines = new ArrayList<>();
    private static List<DXFLWPolyline> lwPolylines = new ArrayList<>();
    private static finalTriangulation triangulation;

    public DXFReader() {System.out.println("Point reading started");}

    public void setLists(){
        layerList.clear();
        totalPoints.clear();
        points.clear();
        polylines.clear();
        lwPolylines.clear();

        for(String layer : controlPane.getCheckLayer().getChecked()){
            layerList.add(inputFiles.doc.getDXFLayer(layer));
        }
        for (String entity : controlPane.getCheckEntity().getChecked()){
            switch (entity){
                case "Polyline":
                    for(DXFLayer layer : layerList){
                        if(layer.getDXFEntities(DXFConstants.ENTITY_TYPE_POLYLINE)!=null){
                        polylines.addAll(layer.getDXFEntities(DXFConstants.ENTITY_TYPE_POLYLINE));}
                    }
                    System.out.println("Polilineas: "+polylines.size());
                    break;
                case "LWPolyline":
                    for(DXFLayer layer: layerList){
                        if(layer.getDXFEntities(DXFConstants.ENTITY_TYPE_LWPOLYLINE)!=null){
                        lwPolylines.addAll(layer.getDXFEntities(DXFConstants.ENTITY_TYPE_LWPOLYLINE));}
                    }
                    break;
                case "Point":

                   for (DXFLayer layer : layerList){
                        if(layer.getDXFEntities(DXFConstants.ENTITY_TYPE_POINT)!=null){
                        points.addAll(layer.getDXFEntities(DXFConstants.ENTITY_TYPE_POINT));}
                    }
            }}
        System.out.println("layers obtained");
    }

    public void setTotalPoints(){
        totalPoints.clear();

        if(polylines.size()!=0){
            for(DXFPolyline polyline : polylines){
                for(int i = 0; i<polyline.getVertexCount();i++){
                    totalPoints.add(new Point(polyline.getVertex(i).getX(), polyline.getVertex(i).getY(), polyline.getVertex(i).getZ()));
                }
            }
        }

        if(lwPolylines.size()!=0){
            for(DXFLWPolyline lwPolyline : lwPolylines){
               for(int i = 0; i<lwPolyline.getVertexCount(); i++){
                   totalPoints.add(new Point(lwPolyline.getVertex(i).getX(), lwPolyline.getVertex(i).getY(), lwPolyline.getElevation()));
               }
            }
        }

        if(points.size()!= 0){
            for (DXFPoint point : points){
                totalPoints.add(new Point(point.getX(),point.getY(),point.getZ()));
            }
        }


        Collections.sort(totalPoints);
        for(int i = 0; i<totalPoints.size();i++){
            totalPoints.get(i).setID(i);
        }

        if(totalPoints!=null){System.out.println("list of points created successfully, sise of: "+totalPoints.size());}
        else {System.out.println("Error in the creation");}
        totalPoints= totalPoints.subList(0,500);
        //Main main = new Main();
        triangulation = new finalTriangulation(totalPoints);
    }



}
