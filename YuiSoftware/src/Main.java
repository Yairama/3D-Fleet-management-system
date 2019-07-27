import javax.swing.*;

public class Main{

    public static void main(String[] args)  {

        Frames baseFrame = new Frames(1200,800,50,50);
        baseFrame.setContentPane(new BaseGUI().getPanelBase());
        baseFrame.setVisible(true);
        baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
