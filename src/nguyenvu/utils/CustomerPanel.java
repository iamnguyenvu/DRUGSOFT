/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package nguyenvu.utils;

import com.formdev.flatlaf.FlatClientProperties;
import entity.KhachHang_entity;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author HP
 */
public class CustomerPanel extends javax.swing.JPanel {
    private CustomerSelectListener listener;
    private KhachHang_entity kh;
    
    // Setter cho listener
    public void addCustomerSelectListener(CustomerSelectListener listener) {
        this.listener = listener;
    }

    private void customerSelected() {
        if (listener != null) {
            listener.onCustomerSeclect(kh);
        }
    }
    
    public CustomerPanel(KhachHang_entity kh) {
        this.kh = kh;
        initComponents();
        setCustomerData(kh);
        addListeners();
    }
    
    private void addListeners() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                customerSelected();
            }
        });
    }
    
    private void setCustomerData(KhachHang_entity kh) {
        lblTenKH.setText(kh.getTenKH());
        lblTenKH.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +1");
        lblSDT.setText(kh.getSDT());
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTenKH = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));

        lblTenKH.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        lblSDT.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(lblSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    // End of variables declaration//GEN-END:variables
}
