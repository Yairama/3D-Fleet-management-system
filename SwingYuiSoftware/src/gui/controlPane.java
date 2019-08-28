package gui;

import objects.Point;
import org.kabeja.dxf.DXFLayer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class controlPane extends JPanel {
    private static JButton openFile = new JButton("Open File");
    private static JButton svgGraph = new JButton("Graph SVG");
    private static JButton dxfGraph = new JButton("Graph DXF");

    public static CheckTable getCheckLayer() {
        return checkLayer;
    }

    public static CheckTable getCheckEntity() {
        return checkEntity;
    }

    private static CheckTable checkEntity;
    private static CheckTable checkLayer = new CheckTable(new Object[]{"Layer","Checked"});
    private static DXFReader reader = new DXFReader();
    public controlPane() {
        setLayout(new GridLayout(5,1));
        actionAdder();
        panel1();
        panel2();
        panel3();
        panel4();

    }

    private void panel1(){
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new Label("Choose File"));
        panel1.add(openFile);
        this.add(panel1);
    }
    private void panel2(){
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(new Label("Select layers"),BorderLayout.WEST);
        JScrollPane scrollPane = new JScrollPane(checkLayer.getTable());
        panel2.add(scrollPane);
        this.add(panel2);
    }

    private void panel3(){
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.add(new Label("Select Entity"),BorderLayout.WEST);
        checkEntity = new CheckTable(new Object[]{"Entity","Checked"});
        checkEntity.addRow(new Object[]{"Polyline",true});
        checkEntity.addRow(new Object[]{"LWPolyline",true});
        checkEntity.addRow(new Object[]{"Point",true});
        JScrollPane scrollPane = new JScrollPane(checkEntity.getTable());
        panel3.add(scrollPane);
        this.add(panel3);
    }

    private void panel4(){
        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());
        panel4.add(svgGraph);
        panel4.add(dxfGraph);
        this.add(panel4);
    }
    private void panel5(){
        JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout());

    }

    protected List<Point> pointsList = new ArrayList<>();

    private void actionAdder(){
        openFile.addActionListener(e -> {
            MarcoPrincipal.dxfFile = new inputFiles();
            if (inputFiles.doc == null){return;}
            setLayersCheck();

        });

        svgGraph.addActionListener(e -> {
            if (inputFiles.doc == null){return;}
            tabPanes.svgPane.svgRender();
        });

        dxfGraph.addActionListener(e->{
            if(inputFiles.doc==null){return;}
            reader.setLists();
            reader.setTotalPoints();
        });
    }


    public static void setLayersCheck(){
        checkLayer.getdModel().setRowCount(0);
        Iterator<DXFLayer> iterator = inputFiles.doc.getDXFLayerIterator();
        while (iterator.hasNext()){
            checkLayer.addRow(new Object[]{iterator.next().getName(),true});
        }
    }
}
