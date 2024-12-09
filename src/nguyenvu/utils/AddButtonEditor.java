/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.utils;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import gui.DoiTra;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import raven.alerts.MessageAlerts;

/**
 *
 * @author HP
 */
public class AddButtonEditor extends DefaultCellEditor{
    private JButton button;
    private boolean isPushed;
    private DefaultTableModel model1;
    private DefaultTableModel model2;
    private JTable table;
    private DoiTra doiTra;

    public AddButtonEditor(JTable table, DefaultTableModel model1, DefaultTableModel model2, DoiTra doiTra) {
        super(new JCheckBox());
        this.table = table;
        this.model1 = model1;
        this.model2 = model2;
        this.doiTra = doiTra;
        button = new JButton(new FlatSVGIcon("gui/icon/add.svg", 0.05f));
        button.setOpaque(true);
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0 && row < model1.getRowCount()) {
                    String existingMaSP = (String) model1.getValueAt(row, 1);
                    
                    for(int i = 0; i < model2.getRowCount(); ++i) {
                        if(existingMaSP.equals((String) model2.getValueAt(i, 1))) {
                            if((int) model2.getValueAt(i, 3) < (int) model1.getValueAt(row, 3)) {
                                model2.setValueAt((int) model2.getValueAt(i, 3) + 1, i, 3);
//                                doiTra.updateInfor();
                            }
                            else {
                                MessageAlerts.getInstance().showMessage("Lỗi", 
                                        "Số lượng đổi trả không được lớn hơn số lượng đã mua!", MessageAlerts.MessageType.ERROR);
                            }
                            return;
                        }
                    }
                        
                    Object[] rowData = new Object[]{
                        model1.getValueAt(row, 0),
                        model1.getValueAt(row, 1),
                        model1.getValueAt(row, 2),
                        1
                    };
                    model2.addRow(rowData);
//                    doiTra.updateInfor();
                }
                fireEditingStopped();
            }
        });
    }


    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, 
        boolean isSelected, int row, int column) {isPushed = true;
        return button;
    }
    
    

    @Override
    public Object getCellEditorValue() {
        isPushed = false;
        return null;
    }
    
    
}
