/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui.components;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import nguyenvu.model.ModelCard;

/**
 *
 * @author HP
 */
public class Card1 extends javax.swing.JPanel{
    private Color gradientStart;
    private Color gradientEnd;
    
    public Card1() {
        initComponents();
        gradientStart = new Color(11, 101, 136);
        gradientEnd = new Color(255, 255, 255);
        lblTitle.setPreferredSize(new java.awt.Dimension(150, 40));
        lblValues.setPreferredSize(new java.awt.Dimension(150, 40));
        lblIcon.setPreferredSize(new java.awt.Dimension(50, 50));
    }
    
    public void setData(ModelCard data) {
        DecimalFormat df = new DecimalFormat("#,##0.##");
        lblTitle.setText(data.getTitle());
        lblValues.setText(df.format(data.getValue()));
        lblIcon.setIcon(new FlatSVGIcon(data.getIconPath()));
        revalidate();
        repaint();

        System.out.println("lblTitle size: " + lblTitle.getSize());
        System.out.println("lblValues size: " + lblValues.getSize());
    }

    public Color getGradientStart() {
        return gradientStart;
    }

    public void setGradientStart(Color gradientStart) {
        this.gradientStart = gradientStart;
    }

    public Color getGradientEnd() {
        return gradientEnd;
    }

    public void setGradientEnd(Color gradientEnd) {
        this.gradientEnd = gradientEnd;
    }
    
   


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, getHeight(), gradientStart, getWidth(), 0, gradientEnd);
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
//        g2.dispose();
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblValues = new javax.swing.JLabel();
        lblIcon = new javax.swing.JLabel();

        lblTitle.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setPreferredSize(new java.awt.Dimension(150, 40));

        lblValues.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblValues.setForeground(new java.awt.Color(255, 255, 255));
        lblValues.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblValues.setPreferredSize(new java.awt.Dimension(100, 40));

        lblIcon.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValues, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblValues, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblValues;
    // End of variables declaration//GEN-END:variables
}
