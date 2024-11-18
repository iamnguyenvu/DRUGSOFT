/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui.components;

import java.text.DecimalFormat;
import javax.swing.SwingConstants;
import nguyenvu.model.ModelTransaction;

/**
 *
 * @author HP
 */
public class LastTransactionPanel extends javax.swing.JPanel{
   
    public LastTransactionPanel() {
        initComponents();
    }
    
    public void setData(ModelTransaction data ) {
        DecimalFormat df = new DecimalFormat("#,##0.##");
        lblRole.setText(data.getRole());
        lblTenNV.setText(data.getTenNV());
        lblValues.setText(df.format(data.getThanhTien()));
        lblValues.setHorizontalAlignment(SwingConstants.RIGHT);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRole = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblValues = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lblRole.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblRole.setForeground(new java.awt.Color(11, 101, 136));
        lblRole.setText("Role");

        lblTenNV.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTenNV.setForeground(new java.awt.Color(11, 101, 136));
        lblTenNV.setText("tenNV");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("đã tạo hóa đơn trị giá ");

        lblValues.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblValues.setForeground(new java.awt.Color(11, 101, 136));
        lblValues.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValues, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenNV)
                    .addComponent(lblRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblValues))
                .addGap(2, 2, 2))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblValues;
    // End of variables declaration//GEN-END:variables
}
