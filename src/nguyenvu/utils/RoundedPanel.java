package nguyenvu.utils;
import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int cornerRadius;  // Bán kính của góc tròn

    public RoundedPanel(int radius) {
        this.cornerRadius = radius; 
        setOpaque(false);  // Đặt panel trong suốt để hiển thị hiệu ứng bo tròn
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Bật khử răng cưa để có viền mượt mà hơn
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Đặt màu nền cho panel
        g2.setColor(getBackground());
        
        // Vẽ hình chữ nhật với góc bo tròn
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();  // Giải phóng tài nguyên
    }
}
