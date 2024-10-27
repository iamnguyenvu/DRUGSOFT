/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package nguyenvu.utils;

import com.formdev.flatlaf.FlatClientProperties;
import entity.SanPham_entity;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.ImageIcon;

/**
 *
 * @author HP
 */
public class ProductSearchPanel extends javax.swing.JPanel{
    private ProductSelectListener listener;
    private SanPham_entity sp;
    
    // Setter cho listener
    public void addProductSelectListener(ProductSelectListener listener) {
        this.listener = listener;
    }

    private void productSelected() {
        if (listener != null) {
            listener.onProductSelected(sp);
        }
    }
    
    public ProductSearchPanel(SanPham_entity sp) {
        this.sp = sp;
        initComponents();
        setProductData(sp);
        addListeners();
    }
    
    private void addListeners() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                productSelected();
            }
        });
    }
    
    private void setProductData(SanPham_entity sp) {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        String giaVaDonVi = numberFormat.format(sp.getGia()) + "đ/" + sp.getDonViTinh();
//        lblImgSP.setIcon(new ImageIcon(getClass().getResource(sp.getHinhAnhSP())));
        lblTenSP.setText(sp.getTenSP());
        lblTenSP.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        lblGiaDonViSP .setText(giaVaDonVi);
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImgSP = new javax.swing.JLabel();
        lblTenSP = new javax.swing.JLabel();
        lblGiaDonViSP = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblImgSP, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblGiaDonViSP, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblImgSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGiaDonViSP, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblGiaDonViSP;
    private javax.swing.JLabel lblImgSP;
    private javax.swing.JLabel lblTenSP;
    // End of variables declaration//GEN-END:variables
}
