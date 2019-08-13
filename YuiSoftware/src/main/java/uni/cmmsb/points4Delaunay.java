package uni.cmmsb;

import org.kabeja.dxf.DXFLWPolyline;

import java.util.*;

public class points4Delaunay {

    public List<Point> getPointList() {
        return pointList;
    }

    /**Atributos*/
    protected List<Point> pointList = new ArrayList<>();

    public points4Delaunay(List<DXFLWPolyline> polylineList) {
        int id = 1;
        for(int i = 0; i<polylineList.size();i++){
            for (int k = 0; k<polylineList.get(i).getVertexCount();k++){
                pointList.add(new Point(
                        polylineList.get(i).getVertex(k).getBounds().getMaximumX(),
                        polylineList.get(i).getVertex(k).getBounds().getMaximumY(),
                        polylineList.get(i).getElevation(),id
                ));
                id++;
            }
        }

        /**Ordenando los puntos con respecto a las X y Y*/
        Collections.sort(pointList, new Comparator<Point>() {
            @Override
            public int compare(Point x1, Point x2) {
                int result = Double.compare(x1.getX(), x2.getX());
                if ( result == 0 ) {
                    // both X are equal -> compare Y too
                    result = Double.compare(x1.getY(), x2.getY());
                }
                return result;
            }
        });

        for(int i = 0; i<100;i++) {
            System.out.println(pointList.get(i).getX() + "-" + pointList.get(i).getY() );
        }
    }
}
