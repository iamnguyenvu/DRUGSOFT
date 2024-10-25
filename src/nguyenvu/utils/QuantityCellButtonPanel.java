/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package nguyenvu.utils;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

/**
 *
 * @author HP
 */
public class QuantityCellButtonPanel extends javax.swing.JPanel{

    public QuantityCellButtonPanel() {
        initComponents();
    }
    
    public String getSoLuongText() {
        return txtSoLuong.getText();
    }
    
    public void setSoLuongText(String soLuong) {
        txtSoLuong.setText(soLuong);
    }
    
    public void initEvent(QuantityCellEvent ev, int row, JTable table) {
        btnIncrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ev != null) ev.onIncrease(row);
                table.getCellEditor().stopCellEditing();
            };
        });
        btnDecrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentQuantity = Integer.parseInt(txtSoLuong.getText());
                if (currentQuantity > 1) {
                    if (ev != null) ev.onDecrease(row); // Gọi sự kiện giảm số lượng nếu lớn hơn 1
                }
                table.getCellEditor().stopCellEditing();
            };
        });
        txtSoLuong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ev != null) ev.onUpdateQuantity(row);
                table.getCellEditor().stopCellEditing();
            };
        });
    }
    
    public void updateSoLuong(int quantity) {
        txtSoLuong.setText(String.valueOf(quantity));
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDecrease = new javax.swing.JButton(new FlatSVGIcon("gui/icon/minus.svg"));
        btnIncrease = new javax.swing.JButton(new FlatSVGIcon("gui/icon/add.svg", 0.04f));
        txtSoLuong = new javax.swing.JTextField();

        btnDecrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecreaseActionPerformed(evt);
            }
        });

        btnIncrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncreaseActionPerformed(evt);
            }
        });

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDecrease, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIncrease, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDecrease, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnIncrease, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncreaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIncreaseActionPerformed

    private void btnDecreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecreaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDecreaseActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtSoLuongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDecrease;
    private javax.swing.JButton btnIncrease;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
