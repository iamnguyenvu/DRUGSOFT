package gui.vi;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Roundvi extends JPanel {
    private int borderRadius;

    // Constructor để nhận bán kính bo góc
    public Roundvi(int borderRadius) {
        this.borderRadius = borderRadius;
        setOpaque(false); // Đảm bảo panel này trong suốt
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Vẽ nền với bo góc
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Tạo hình chữ nhật bo góc
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), borderRadius, borderRadius));
    }
}
