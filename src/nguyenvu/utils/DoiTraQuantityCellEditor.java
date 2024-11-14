/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.utils;

import gui.DoiTra;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

/**
 *
 * @author HP
 */
public class DoiTraQuantityCellEditor extends DefaultCellEditor{
    private JSpinner input;
    private JTable table;
    private JTable tableExchange;
    private DoiTra doiTra;

    public DoiTraQuantityCellEditor(DoiTra doiTra, JTable table, JTable tableExchange) {
        super(new JCheckBox());
        this.doiTra = doiTra;
        this.table = table;
        this.tableExchange = tableExchange;
        
        input = new JSpinner();
        SpinnerNumberModel numberModel = (SpinnerNumberModel) input.getModel();
        numberModel.setMinimum(1);
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) input.getEditor();

        DefaultFormatter formatter = (DefaultFormatter) editor.getTextField().getFormatter();
        formatter.setCommitsOnValidEdit(true);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);

        input.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tableExchange != null) {
                    doiTra.updateInfor();
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable tableExchange, Object value, boolean isSelected, int row, int column) {String maSP = (String) tableExchange.getValueAt(row, 1); // Product ID in tableExchange
        Component com = super.getTableCellEditorComponent(table, value, isSelected, row, column);
        int maxQuantity = getMaxQuantity(maSP); // Fetch maximum allowed quantity for this product

        // Update spinner model to ensure it doesn't exceed maxQuantity
        ((SpinnerNumberModel) input.getModel()).setMaximum(maxQuantity);
        input.setValue(value != null ? value : 1); // Set the initial value (minimum is 1)
        input.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tableExchange.editingStopped(null);
            }
        });
        com.setBackground(tableExchange.getBackground());

        return input;
    }

    private int getMaxQuantity(String maSP) {
        for (int i = 0; i < table.getRowCount(); i++) {
            String existingMaSP = (String) table.getValueAt(i, 1);
            if (maSP.equals(existingMaSP)) {
                return (int) table.getValueAt(i, 3); // Available quantity from the main table
            }
        }
        return 1; // Default minimum if product is not found
    }

    @Override
    public Object getCellEditorValue() {
        return input.getValue(); // Return the selected quantity
    }

}
