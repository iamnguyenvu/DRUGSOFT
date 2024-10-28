package nguyenvu.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isClicked;
    private int currentRow;
    private ActionListener actionListener;

    public ButtonEditor(String label, ActionListener actionListener) {
        super(new JCheckBox());
        button = new JButton();
        this.label = label;
        this.actionListener = actionListener;
        button.setOpaque(true);

        // Xử lý sự kiện khi nút được nhấn
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isClicked = true;
                fireEditingStopped();
                if (actionListener != null) {
                    actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, label + "_" + currentRow));
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        button.setText(label);
        isClicked = true;
        currentRow = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        isClicked = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isClicked = false;
        return super.stopCellEditing();
    }
}
