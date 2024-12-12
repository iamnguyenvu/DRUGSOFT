/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.utils;

import dao.BanHang_DAO;

import gui.BanHang;
import gui.DoiTra;
import gui.NhapHang;

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
import raven.alerts.MessageAlerts;

/**
 *
 * @author HP
 */
public class QuantityCellEditor extends DefaultCellEditor {
    private JSpinner input;
    
    private JTable table;
    private JTable tableExchange;
    private int row;
    private BanHang banHang;
    private BanHang_DAO dao;
    private NhapHang nhapHang;
    private DoiTra doiTra;
    

    public QuantityCellEditor(BanHang banHang) {
        super(new JCheckBox());
        this.banHang = banHang;
        dao = new BanHang_DAO();
        input = new JSpinner();
        SpinnerNumberModel numberModel = (SpinnerNumberModel) input.getModel();
        numberModel.setMinimum(1);
        numberModel.setMaximum(50);
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
    
    public QuantityCellEditor(DoiTra doiTra, JTable tableExchange) {
        super(new JCheckBox());
        this.doiTra = doiTra;
        this.tableExchange = tableExchange;
        dao = new BanHang_DAO();
        input = new JSpinner();
        SpinnerNumberModel numberModel = (SpinnerNumberModel) input.getModel();
        numberModel.setMinimum(1);
        numberModel.setMaximum(50);
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) input.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) editor.getTextField().getFormatter();
        formatter.setCommitsOnValidEdit(true);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        input.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tableExchange != null) {
                    int quantity = (int) input.getValue();
                    double pricePerUnit = (double) tableExchange.getValueAt(row, 6);
                    double newTotal = pricePerUnit * quantity;
                    tableExchange.setValueAt(newTotal, row, 7);
                    doiTra.updatePnTraHang();
                    doiTra.updateLblSoLuongSP();
                }
            }
        });

    }

    public QuantityCellEditor(NhapHang nhapHang) {
        super(new JCheckBox());
        this.nhapHang = nhapHang;
        dao = new BanHang_DAO();
        input = new JSpinner();
        SpinnerNumberModel numberModel = (SpinnerNumberModel) input.getModel();
        numberModel.setMinimum(1);
        numberModel.setMaximum(50);
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) input.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) editor.getTextField().getFormatter();
        formatter.setCommitsOnValidEdit(true);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        input.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (table != null) {
                    int quantity = (int) input.getValue();
                    double pricePerUnit = (double) table.getValueAt(row, 7);
                    double newTotal = pricePerUnit * quantity;
                    table.setValueAt(newTotal, row, 8);
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
        
//        so luong ton kho
        int maxQuantity = dao.getSP((String) table.getValueAt(row, 2)).getSoLuong();
        
//        toi da 50 hoac kh vuot qua so luong ton kho
        int maxAllowedQuantity = Math.min(maxQuantity, 50);

        if (quantity > maxAllowedQuantity) {
            quantity = maxAllowedQuantity;
            
            ((SpinnerNumberModel) input.getModel()).setMaximum(maxAllowedQuantity); 
            
            String message = (quantity == 50) 
                    ? "Số lượng tối đa cho phép là 50!" 
                    : "Vượt quá số lượng tồn kho!";
            MessageAlerts.getInstance().showMessage("Cảnh báo", message, MessageAlerts.MessageType.WARNING);
        }
            
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
