package com.yui.lib.yuiutil;
import java.awt.*;
import javax.swing.*;


public class YuiutilAWT{
    //se crearan los metodos

    private YuiutilAWT() {
    }
    public static JFrame getJFrame(Component component)    {
        Component c = component;

        if (c instanceof JFrame)  return (JFrame)c;

        while ((c = c.getParent()) != null)   {

            if(c instanceof JFrame)
                return (JFrame)c;
        }
        return null;
        //se obtiene el jgrame asociado con un componente y si no hay jframe devuelve null
    }

    public static void constrain(Container container, Component component,

                   int gridx,
                   // Specifies the cell at the left of the component's display area, where the
                   // leftmost cell has gridx = 0.  The value RELATIVE specifies that the component
                   // be placed just to the right of the component that was added to the
                   // container just before this component was added.
                   // Default = RELATIVE
                   int gridy,
                   // Specifies the cell at the top of the component's display area, where the
                   // topmost cell has gridy = 0.  The value RELATIVE specifies that the component
                   // be placed just below the component that was added to the
                   // container just before this component was added.
                   // Default = RELATIVE


                   int gridwidth,
                   // Specifies the number of cells in a row for the component's display area.
                   // Use REMAINDER to specify that the component be the last one in its row.
                   // Use RELATIVE to specify that the component be the next-to-last one in its row.
                   // Default = 1
                   int gridheight,
                   // Specifies the number of cells in a column for the component's display area.
                   // Use REMAINDER to specify that the component be the last one in its column.
                   // Use RELATIVE to specify that the component be the next-to-last one in its column.
                   // Default = 1


                   int fill,
                   // This field is used when the component's display area is larger than the
                   // component's requested size.
                   // It determines whether to resize the component, and if so, how.
                   // Values: NONE, HORIZONTAL, VERTICAL, BOTH
                   // Default = NONE


                   int anchor,
                   // This field is used when the component is smaller than its display area.
                   // It determines where, within the area, to place the component.
                   // Values: CENTER, NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST.
                   // Default = CENTER


                   double weightx,
                   // This field specifies how to distribute extra horizontal space.
                   // The grid bag layout manager calculates the weight of a column to be the
                   // maximum weighty of all the components in a row.  If the resulting layout is
                   // smaller horizontally than the area it needs to fill, the exta space is distributed
                   // to each column in proportion to its weight.  A column that has weight 0
                   // receives no extra space.
                   // If all the weights are zero, all the extra space sppears between the grids of
                   // the cell and the right and left edges.
                   // Default = 0
                   double weighty,
                   // This field specifies how to distribute extra vertical space.
                   // The grid bag layout manager calculates the weight of a row to be the maximum
                   // weightx of all the components in a row.  If the resulting layout is
                   // smaller vertically than the area it needs to fill, the extra space is distributed to
                   // each row in proportion to its weight.  A row that has weight 0 receives no extra space.
                   // If all the weights are zero, all the extra space appears between the grids of
                   // the cell and the top and bottom edges.
                   // Default = 0


                   int ipadx,
                   // This field specifies the internal padding, that is, how much space to add to
                   // the minimum width of the component.  The width of the component is at
                   // least its minimum width plus ipadx*2 pixels.
                   int ipady,
                   // This field specifies the internal padding, that is, how much space to add to
                   // the minimum height of the component.  The height of the component is at
                   // least its minimum height plus ipady*2 pixels.


                   int top, int left, int bottom, int right
                   // This field specifies the external padding of the component, the minimum
                   // amount of space between the component and the edges of its display area.
                   // Default = Insets(0, 0, 0, 0)

    )
    {
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = gridx;
        c.gridy = gridy;

        c.gridwidth  = gridwidth;
        c.gridheight = gridheight;

        c.fill = fill;

        c.anchor = anchor;

        c.weightx = weightx;
        c.weighty = weighty;

        if ((top + bottom + left + right) > 0)
            c.insets = new Insets(top, left, bottom, right);

        ((GridBagLayout)container.getLayout()).setConstraints(component, c);
// se colocan los limites de los componentes del layout para cuando se achique o agrande la pantalla
        container.add(component);
    }



    public static
    void constrain(Container container, Component component,
                   int gridx, int gridy,
                   int gridwidth, int gridheight,
                   int top, int left, int bottom, int right)   {
        constrain(container, component, gridx, gridy,gridwidth, gridheight,
                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor
                0.0, 0.0, // weightx, weighty
                0, 0, // ipadx, ipady
                top, left, bottom, right); // Insets()
        // se coloca la posicion, anchura, altura, y llenado
    }

    public static
    void constrain(Container container, Component component,int gridx, int gridy,int gridwidth, int gridheight){
        constrain(container, component,
                gridx, gridy,
                gridwidth, gridheight,
                GridBagConstraints.NONE, // fill
                GridBagConstraints.NORTHWEST, // anchor
                0.0, 0.0, // weightx, weighty
                0, 0, // ipadx, ipady
                0, 0, 0, 0); // Insets()
    }
}
