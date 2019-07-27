import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BaseGUI {

    private JPanel panelBase;

    public JPanel getPanelBase() {
        panelBase.setSize(1200,800);
        return panelBase;
    }

    private JButton sampleBut;

    public BaseGUI(){

        sampleBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panelBase,"holi");
            }
        });
    }

}
