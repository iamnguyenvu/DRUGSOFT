/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.utils;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author HP
 */
public class TableDeleteCellEditor extends DefaultCellEditor{
    private TableDeleteEvent ev;

    public TableDeleteCellEditor(TableDeleteEvent ev) {
        super(new JCheckBox());
        this.ev = ev;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        DeleteButtonPanel pn = new DeleteButtonPanel();
        pn.initEvent(ev, row, table);
//        pn.setBackground(table.getSelectionBackground());
        return pn;
    }
}
