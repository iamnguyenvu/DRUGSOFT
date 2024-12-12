/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package nguyenvu.utils;

import com.formdev.flatlaf.FlatClientProperties;
import entity.SanPham_entity;
import java.awt.Color;
import java.awt.Image;
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
    private Image imgSP;
    
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
        ImageIcon iiSP = new ImageIcon(getClass().getResource(sp.getHinhAnhSP()));
	Image imgSP = iiSP.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        this.imgSP = imgSP;
        initComponents();
        setProductData(sp);
        addListeners();
//        setBackground(new Color(236,236,236));
        setBackground(Color.WHITE);
    }
    
    private void addListeners() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                productSelected();
            }
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(11,101,136));
            }
            
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
//                setBackground(new Color(236,236,236));
                setBackground(Color.WHITE);
            }
            
        });
    }
    
    private void setProductData(SanPham_entity sp) {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        String giaVaDonVi = numberFormat.format(sp.getGia()) + "đ/" + sp.getDonViTinh();
        
        ImageIcon iiSP = new ImageIcon(getClass().getResource(sp.getHinhAnhSP()));
    
        if (iiSP.getIconWidth() > 0 && iiSP.getIconHeight() > 0) {
            Image imgSP = iiSP.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            lblImgSP.setIcon(new ImageIcon(imgSP));
        } else {
//            lblImgSP.setIcon(new ImageIcon("path/to/default/image.png"));
        }
        
        lblTenSP.setText(sp.getTenSP());
        lblTenSP.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        lblGiaDonViSP .setText(giaVaDonVi);
        lblSlTon.setText(String.valueOf(sp.getSoLuong()));
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImgSP = new javax.swing.JLabel();
        lblTenSP = new javax.swing.JLabel();
        lblGiaDonViSP = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblSlTon = new javax.swing.JLabel();

        setForeground(new java.awt.Color(204, 204, 204));

        jLabel1.setText("Số lượng tồn:");

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
                        .addComponent(lblGiaDonViSP, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSlTon, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGiaDonViSP, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSlTon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblGiaDonViSP;
    private javax.swing.JLabel lblImgSP;
    private javax.swing.JLabel lblSlTon;
    private javax.swing.JLabel lblTenSP;
    // End of variables declaration//GEN-END:variables
}
