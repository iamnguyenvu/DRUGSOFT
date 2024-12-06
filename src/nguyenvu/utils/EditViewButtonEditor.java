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
public class EditViewButtonEditor extends DefaultCellEditor{
    private EditViewButtonEvent event;

    public EditViewButtonEditor(EditViewButtonEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        Component com = super.getTableCellEditorComponent(table, value, isSelected, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        EditViewButtonPanel pn = new EditViewButtonPanel();
        pn.initEvent(event, row, table);
        pn.setBackground(table.getSelectionBackground());
        return pn;
    }
}
