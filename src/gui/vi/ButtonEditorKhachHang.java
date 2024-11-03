package gui.vi;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import gui.KhachHang_GUI;

public class ButtonEditorKhachHang extends AbstractCellEditor implements TableCellEditor {

    private JButton button;
    private String actionType;
    private JTable table;
    private KhachHang_GUI parent;

    public ButtonEditorKhachHang(String iconPath, JTable table, KhachHang_GUI parent, String actionType) {
        this.table = table;
        this.parent = parent;
        this.actionType = actionType;

        button = new JButton();
        button.setIcon(new javax.swing.ImageIcon(
                new javax.swing.ImageIcon(getClass().getResource(iconPath))
                        .getImage().getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH)
        ));

        button.addActionListener(e -> {
                fireEditingStopped();  // Kết thúc quá trình chỉnh sửa

                int row = table.getSelectedRow();  // Lấy dòng được chọn
                if (actionType.equals("edit")) {
                    parent.hienThiThongTinKhachHang(row);  // Hiển thị form chỉnh sửa
                } else if (actionType.equals("delete")) {
                    int confirm = JOptionPane.showConfirmDialog(
                            table,
                            "Bạn có chắc chắn muốn xóa khách hàng này?",
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        parent.xoaKhachHang(row);  // Thực hiện xóa khách hàng
                    }
                }
            });

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "";
    }
}
