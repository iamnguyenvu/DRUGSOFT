package nguyenvu.utils;

import java.awt.Component;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.toedter.calendar.JDateChooser;

public class DateCellEditor extends AbstractCellEditor implements TableCellEditor {
    private JDateChooser dateChooser;

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        if (value != null) {
            dateChooser.setDate( (Date) value);
        }
        return dateChooser;
    }

    @Override
    public Object getCellEditorValue() {
        return dateChooser.getDate();
    }
}