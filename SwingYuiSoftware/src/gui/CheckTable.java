package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CheckTable {
    private Object[] columnName;

    public JTable getTable() {
        return table;
    }

    private JTable table = new JTable();

    public DefaultTableModel getdModel() {
        return model;
    }

    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column)
        {
            return column==1;
        }
    };
    public CheckTable(Object[] columnName) {
        this.columnName = columnName;
        table.setModel(model);
        model.setColumnIdentifiers(columnName);

        TableColumn Tcol = new TableColumn();
        Tcol=table.getColumnModel().getColumn(1);//el numero dentro de getColum se refiere a la posiciónen la que se encuentra tu columna
        Tcol.setCellEditor(table.getDefaultEditor(Boolean.class));
        Tcol.setCellRenderer(table.getDefaultRenderer(Boolean.class));
    }


    public void addRow(Object[] items){
        model.addRow(items);
    }

    public List<String> getChecked(){
        List<String> results = new ArrayList<>();
        for (int i=0; i<table.getRowCount();i++){
            Boolean checked = Boolean.valueOf((Boolean) table.getValueAt(i,1));
            String col = table.getValueAt(i,0).toString();
            if(checked==true){results.add(col);}
        }
        return results;
    }
}
