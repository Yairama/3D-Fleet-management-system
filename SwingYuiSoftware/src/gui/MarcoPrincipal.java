package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarcoPrincipal extends JFrame{
     private JMenuBar menuBar = new JMenuBar();
     private JMenu jMenuFile= new JMenu("file");
     private JMenu jMenuAbout = new JMenu("About");
     public static JMenuItem openFile = new JMenuItem("Open File");
     private JMenuItem aboutUniversity = new JMenuItem("About University");
     private JMenuItem aboutStudents = new JMenuItem("About Students");
     public static thePane principalPane;
     public static inputFiles dxfFile;
    public MarcoPrincipal(Dimension dimension, String Title) {
        setSize(dimension);
        setTitle(Title);
        //Action
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dxfFile = new inputFiles();
                if (inputFiles.doc == null){return;}
                controlPane.setLayersCheck();
            }
        });
        //menubar
        menuBar.add(jMenuFile);
        menuBar.add(jMenuAbout);
        //files
        jMenuFile.add(openFile);
        //about
        jMenuAbout.add(aboutStudents);
        jMenuAbout.add(aboutUniversity);
        setJMenuBar(menuBar);
        principalPane = new thePane();
        this.add(principalPane);
    }

}

