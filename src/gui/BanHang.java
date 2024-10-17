/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import javax.swing.Icon;
import net.miginfocom.swing.MigLayout;
import nguyenvu.components.SimpleForm;
import nguyenvu.utils.WindowsTabbed;

/**
 *
 * @author HP
 */
public class BanHang extends SimpleForm {
    public BanHang() {
        initComponents();
        WindowsTabbed.getInstance().install(pnContent);
    }
    
    private void init() {
        
    }
    
    private Icon createIcon(String path) {
        FlatSVGIcon icon = new FlatSVGIcon(path, 0.05f);
        FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
        colorFilter.add(Color.decode("#969696"), Color.decode("#FAFAFA"), Color.decode("#969696"));
        icon.setColorFilter(colorFilter);
        return icon;
    }

  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnSearch = new javax.swing.JPanel();
        btnFilter = new javax.swing.JButton(createIcon("gui/icon/filter.svg"));
        txtSearch = new javax.swing.JTextField();
        pnContent = new javax.swing.JPanel();
        pnRightContent = new javax.swing.JPanel();
        pnInputCustomer = new javax.swing.JPanel();
        txtCustomer = new javax.swing.JTextField();
        btnAddCustomer = new javax.swing.JButton(new FlatSVGIcon("gui/icon/add.svg", 0.05f));
        pnSouthContent = new javax.swing.JPanel();
        pnCenterContent = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        pnSearch.setBackground(new java.awt.Color(11, 101, 136));
        pnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnSearch.setPreferredSize(new java.awt.Dimension(100, 50));
        pnSearch.setLayout(null);

        btnFilter.setBackground(new java.awt.Color(11, 101, 136));
        btnFilter.setBorderPainted(false);
        btnFilter.setFocusPainted(false);
        btnFilter.setPreferredSize(new java.awt.Dimension(75, 40));
        pnSearch.add(btnFilter);
        //btnFilter.setIcon(new FlatSVGIcon("/gui/icon/filter.svg"));

        txtSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtSearch.setPreferredSize(new java.awt.Dimension(85, 40));
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "  [F2] Thêm sản phẩm vào đơn");
        pnSearch.add(txtSearch);
        txtSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("gui/icon/search.svg", 0.1f));
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5,$Component.borderColor,,20");

        pnSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");

        add(pnSearch, java.awt.BorderLayout.PAGE_START);

        pnContent.setPreferredSize(new java.awt.Dimension(10, 900));
        pnContent.setLayout(new java.awt.BorderLayout());

        pnRightContent.setPreferredSize(new java.awt.Dimension(400, 500));

        pnInputCustomer.setBackground(new java.awt.Color(204, 204, 204));
        pnInputCustomer.setPreferredSize(new java.awt.Dimension(100, 40));
        pnInputCustomer.setLayout(new java.awt.BorderLayout());

        txtCustomer.setBackground(new java.awt.Color(204, 204, 204));
        txtCustomer.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtCustomer.setOpaque(true);
        txtCustomer.setPreferredSize(new java.awt.Dimension(85, 40));
        pnInputCustomer.add(txtCustomer, java.awt.BorderLayout.CENTER);
        //txtCustomer.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("gui/icon/add.svg", 0.05f));
        txtCustomer.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, " [F3] Thêm khách hàng");

        btnAddCustomer.setBorder(null);
        btnAddCustomer.setBorderPainted(false);
        btnAddCustomer.setContentAreaFilled(false);
        btnAddCustomer.setPreferredSize(new java.awt.Dimension(40, 40));
        pnInputCustomer.add(btnAddCustomer, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout pnRightContentLayout = new javax.swing.GroupLayout(pnRightContent);
        pnRightContent.setLayout(pnRightContentLayout);
        pnRightContentLayout.setHorizontalGroup(
            pnRightContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnRightContentLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(pnInputCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        pnRightContentLayout.setVerticalGroup(
            pnRightContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnRightContentLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(pnInputCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(327, Short.MAX_VALUE))
        );

        pnContent.add(pnRightContent, java.awt.BorderLayout.LINE_END);
        pnRightContent.setLayout(new MigLayout("wrap,fill,gap 10", "fill"));

        pnSouthContent.setPreferredSize(new java.awt.Dimension(100, 200));
        pnContent.add(pnSouthContent, java.awt.BorderLayout.PAGE_END);
        pnSouthContent.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5,$Component.borderColor,,20");

        pnCenterContent.setPreferredSize(new java.awt.Dimension(100, 600));
        pnContent.add(pnCenterContent, java.awt.BorderLayout.CENTER);

        add(pnContent, java.awt.BorderLayout.CENTER);
        //pnContent.add(new DonHangPanel());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCustomer;
    private javax.swing.JButton btnFilter;
    private javax.swing.JPanel pnCenterContent;
    private javax.swing.JPanel pnContent;
    private javax.swing.JPanel pnInputCustomer;
    private javax.swing.JPanel pnRightContent;
    private javax.swing.JPanel pnSearch;
    private javax.swing.JPanel pnSouthContent;
    private javax.swing.JTextField txtCustomer;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
