/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.utils;

import java.awt.Checkbox;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author HP
 */
public class TableActionCellEditor extends DefaultCellEditor{
    private TableActionEvent ev;
    
    public TableActionCellEditor(TableActionEvent ev) {
        super(new JCheckBox());
        this.ev = ev;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        ActionButtonPanel ac = new ActionButtonPanel();
        ac.initEvent(ev, row);
        ac.setBackground(table.getSelectionBackground());
        return ac;
    }
    
    
}
