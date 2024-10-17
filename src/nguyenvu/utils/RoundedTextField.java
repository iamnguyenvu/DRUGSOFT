/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author HP
 */
public class RoundedTextField extends JTextField{
    private int cornerRadius = 20;

    public RoundedTextField(int columns) {
        super(columns);
        setOpaque(false);
        setBorder(new EmptyBorder(5, 20, 5, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), height, height);

        super.paintComponent(g);
    }
    
//     @Override
//    protected void paintBorder(Graphics g) {
//        // Xóa viền mặc định
//    }
//
//    @Override
//    public void setBorder(Border border) {
//        // Không cho phép đặt viền bên ngoài
//    }

}
