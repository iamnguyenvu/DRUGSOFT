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
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;
import raven.alerts.MessageAlerts;

/**
 *
 * @author HP
 */
public class DoiTraQuantityCellEditor extends DefaultCellEditor{
    private JSpinner input;
    private JTable table;
    private int row;
    private DoiTra doiTra;
    private int maxQuantity; 

    public DoiTraQuantityCellEditor(DoiTra doiTra) {
        super(new JCheckBox());
        this.doiTra = doiTra;

        input = new JSpinner();
        SpinnerNumberModel numberModel = (SpinnerNumberModel) input.getModel();
        numberModel.setMinimum(0);
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) input.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) editor.getTextField().getFormatter();
        formatter.setCommitsOnValidEdit(true);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        input.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (table != null) {
                    int quantity = (int) input.getValue();
                    System.out.println("quantity " + quantity);
                    double price = (double) table.getValueAt(row, 5);
                    System.out.println("price " + price);
                    double totalPrice = quantity * price;
                    System.out.println("totalPrice " + totalPrice);
                    table.setValueAt(totalPrice, row, 6);
                    
                    doiTra.updatePnTraHang();
                    doiTra.updateLblSoLuongSP();
                }
            }
        });
    }

    
    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        this.row = row;
        
        int quantity = Integer.parseInt(value.toString());

        int maxQuantity = (int) table.getValueAt(row, 4);

        ((SpinnerNumberModel) input.getModel()).setMaximum(maxQuantity);

        if (quantity > maxQuantity) {
            quantity = maxQuantity;
            MessageAlerts.getInstance().showMessage("Cảnh báo", "Vượt quá số lượng sản phẩm trong hóa đơn đã mua!", MessageAlerts.MessageType.WARNING);
        }

        input.setValue(quantity);
        input.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                table.editingStopped(null);
            }
        });
        Component com = super.getTableCellEditorComponent(table, value, isSelected, row, column);
        com.setBackground(table.getBackground());
        return input;
    }

    @Override
    public Object getCellEditorValue() {
        return input.getValue();
    }

}
