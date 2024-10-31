/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.utils;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author HP
 */
public class AddButtonRenderer extends JButton implements TableCellRenderer{
    private FlatSVGIcon icon;

    public AddButtonRenderer() {
        setOpaque(true);
        icon = new FlatSVGIcon("gui/icon/add.svg", 0.05f);
    }


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setIcon(icon);
        setText("");
        return this;
    }
}
