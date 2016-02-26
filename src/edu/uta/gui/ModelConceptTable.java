package edu.uta.gui;

import javax.swing.table.AbstractTableModel;

//http://java.sun.com/docs/books/tutorial/uiswing/components/table.html
public class ModelConceptTable extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Class", "Attribute", "Association", "Aggregation",
									"Inheritance", "Multiplicity", "Role"};
    private Object[][] data = { { "One", "Two", 3, 4, 5, 6, 7 },
    							{ "Three", "Four", 5, 6, 7, 8, 9 },
    							{ 5, 6, 7, 8, 9, 10, 11 } };

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
