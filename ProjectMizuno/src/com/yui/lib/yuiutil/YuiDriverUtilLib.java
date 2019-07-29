package com.yui.lib.yuiutil;
import javax.swing.*;
import java.awt.*;

public class YuiDriverUtilLib {
    //clase principal


    public static void main(String argv[])  {
        MarcoYui marquito = new MarcoYui();
        marquito.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
class MarcoYui extends JFrame{
    int coordenadax=
    int coordenaday=
    int coordenadaz=
    public MarcoYui() {
        setVisible(true);
        setTitle("Seleccione el primer punto");
        setBounds(300, 300, 600, 700);
        add(laminadeyui, BorderLayout_SOUTH);

    }
    class laminadeyui extends JPanel{
        public laminadeyui() {
            setLayout(new BorderLayout());
            System.out.println("x: " + coordenadax + " y: " + coordenaday + " z: " + coordenadaz);
        }
    }


    }