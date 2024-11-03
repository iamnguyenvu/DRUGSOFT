package gui.vi;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer(String iconPath) {
        setOpaque(true);
        setContentAreaFilled(true);  // Giữ nguyên nền của nút
        setFocusPainted(false);  // Bỏ focus viền
        setBorderPainted(false);  // Bỏ viền nút

        // Đặt icon cho nút và căn chỉnh đúng kích thước
        setIcon(new javax.swing.ImageIcon(
                new javax.swing.ImageIcon(getClass().getResource(iconPath))
                .getImage().getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH)
        ));
        setHorizontalAlignment(CENTER);  // Căn giữa icon
        setPreferredSize(new Dimension(32, 32));  // Kích thước nút cố định

        // Hiệu ứng hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(173, 216, 230));  // Màu xanh nhạt
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.WHITE);  // Màu trắng
            }
        });
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        return this;
    }
}
