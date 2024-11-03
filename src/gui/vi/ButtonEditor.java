package gui.vi;

import gui.TaiKhoan_GUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private JButton button;
    private JTable table;
    private TaiKhoan_GUI parent;
    private int columnIndex;  // Lưu cột để phân biệt nút sửa và nút xóa.

    public ButtonEditor(String iconPath, JTable table, TaiKhoan_GUI tkGui, int columnIndex) {
        this.table = table;
        this.parent = tkGui;
        this.columnIndex = columnIndex;

        button = new JButton();
        button.setOpaque(true);
        button.setContentAreaFilled(true);  // Giữ nguyên nền.
        button.setFocusPainted(false);  // Bỏ viền focus.
        button.setBorderPainted(false);  // Bỏ viền nút.

        // Đặt icon cho nút.
        button.setIcon(new javax.swing.ImageIcon(
                new javax.swing.ImageIcon(getClass().getResource(iconPath))
                        .getImage().getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH)
        ));
        button.setHorizontalAlignment(JButton.CENTER);
        button.setPreferredSize(new Dimension(32, 32));

        // Hiệu ứng hover.
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(255, 192, 203));  // Hồng nhạt.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.WHITE);  // Trở về trắng.
            }
        });

        // Xử lý sự kiện khi bấm vào nút.
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();  // Kết thúc việc chỉnh sửa.

                int row = table.getSelectedRow();  // Lấy hàng được chọn.

                if (columnIndex == 5) {  // Nút chỉnh sửa.
                    parent.showEditDialog(row);  // Gọi phương thức chỉnh sửa.
                } else if (columnIndex == 6) {  // Nút xóa.
                    int confirm = javax.swing.JOptionPane.showConfirmDialog(
                            table,
                            "Bạn có chắc chắn muốn xóa tài khoản này?",
                            "Xác nhận xóa", 
                            javax.swing.JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                        parent.confirmAndDeleteAccount(row);  // Thực hiện xóa.
                    }
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        return button;  // Trả về nút cho bảng.
    }

    @Override
    public Object getCellEditorValue() {
        return "";  // Giá trị trả về không quan trọng.
    }
}
