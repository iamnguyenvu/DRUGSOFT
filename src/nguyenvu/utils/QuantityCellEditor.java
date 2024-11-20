/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.utils;

import gui.BanHang;
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
public class QuantityCellEditor extends DefaultCellEditor {
    private JSpinner input;
    
    private JTable table;
    private int row;
    private BanHang banHang;
    

    public QuantityCellEditor(BanHang banHang) {
        super(new JCheckBox());
        this.banHang = banHang;
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
                if (table != null) {
                    int quantity = (int) input.getValue();
                    double pricePerUnit = (double) table.getValueAt(row, 6);
                    double newTotal = pricePerUnit * quantity;
                    table.setValueAt(newTotal, row, 7);
                    banHang.updateLblSoLuongSP();
                }
            }
        });

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        this.row = row;
        Component com = super.getTableCellEditorComponent(table, value, isSelected, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        int quantity = Integer.parseInt(value.toString());
        input.setValue(quantity);
        input.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                table.editingStopped(null);
            }
        });
        
        com.setBackground(table.getBackground());
        return input;
    }

    @Override
    public Object getCellEditorValue() {
        return input.getValue();
    }
}
