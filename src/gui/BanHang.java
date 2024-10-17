/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import net.miginfocom.swing.MigLayout;
import nguyenvu.components.SimpleForm;
import nguyenvu.utils.RoundedTextField;
import nguyenvu.utils.WindowsTabbed;

/**
 *
 * @author HP
 */
public class BanHang extends SimpleForm {

    private DefaultListModel<SanPham> model;
    public BanHang() {
        setPreferredSize(new Dimension(1000, 740));
        initComponents();
        WindowsTabbed.getInstance().install(pnContent);
    }
    
    private void init() {
        
    }
    
    private Icon createIcon(String path) {
        FlatSVGIcon icon = new FlatSVGIcon(path, 0.04f);
        FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
        colorFilter.add(Color.decode("#969696"), Color.decode("#FAFAFA"), Color.decode("#969696"));
        icon.setColorFilter(colorFilter);
        return icon;
    }

    private void txtKeyReleased(KeyEvent e) {
        model = new DefaultListModel<>();
        listSanPham.setM;
        try {
            String key = txtSearch.getText().trim();
            
        }
        catch {
            
        }
    }
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnSearch = new javax.swing.JPanel();
        btnFilter = new javax.swing.JButton(new FlatSVGIcon("gui/icon/filter.svg"));
        txtSearch = new RoundedTextField(40);
        pnContent = new javax.swing.JPanel();
        pnRightContent = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pnInputCustomer = new javax.swing.JPanel();
        btnAddCustomer = new javax.swing.JButton(new FlatSVGIcon("gui/icon/add.svg"));
        txtCustomer = new javax.swing.JTextField();
        lblSoLuongSP = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblVAT = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblSuDungDiemThuong = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblPhaiTra = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblKhachDua = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        btnLuuTam = new javax.swing.JButton();
        pnLeftContent = new javax.swing.JPanel();
        pnFunc = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        listSanPham = new javax.swing.JList<>();

        setPreferredSize(new java.awt.Dimension(1470, 730));
        setLayout(new java.awt.BorderLayout());

        pnSearch.setBackground(new java.awt.Color(11, 101, 136));
        pnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnSearch.setPreferredSize(new java.awt.Dimension(100, 50));
        pnSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFilter.setBackground(new java.awt.Color(11, 101, 136));
        btnFilter.setBorderPainted(false);
        btnFilter.setFocusPainted(false);
        btnFilter.setPreferredSize(new java.awt.Dimension(75, 40));
        pnSearch.add(btnFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 5, 60, 40));

        txtSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        txtSearch.setPreferredSize(new java.awt.Dimension(85, 40));
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "[F2] Thêm sản phẩm vào đơn");
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        pnSearch.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, 730, 40));
        txtSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("gui/icon/search.svg"));
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5,$Component.borderColor,,20");

        pnSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");

        add(pnSearch, java.awt.BorderLayout.PAGE_START);

        pnContent.setPreferredSize(new java.awt.Dimension(1100, 800));

        pnRightContent.setPreferredSize(new java.awt.Dimension(400, 700));
        pnRightContent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnInputCustomer.setBackground(new java.awt.Color(204, 204, 204));
        pnInputCustomer.setPreferredSize(new java.awt.Dimension(100, 40));
        pnInputCustomer.setLayout(new java.awt.BorderLayout());

        btnAddCustomer.setBackground(new java.awt.Color(204, 204, 204));
        btnAddCustomer.setBorderPainted(false);
        btnAddCustomer.setFocusPainted(false);
        btnAddCustomer.setPreferredSize(new java.awt.Dimension(40, 40));
        pnInputCustomer.add(btnAddCustomer, java.awt.BorderLayout.LINE_END);

        txtCustomer.setBackground(new java.awt.Color(204, 204, 204));
        txtCustomer.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 1));
        txtCustomer.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtCustomer.setPreferredSize(new java.awt.Dimension(85, 40));
        txtCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerActionPerformed(evt);
            }
        });
        pnInputCustomer.add(txtCustomer, java.awt.BorderLayout.CENTER);
        txtCustomer.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, " [F3] Thêm khách hàng");

        lblSoLuongSP.setText("Số lượng sản phẩm");
        lblSoLuongSP.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel2.setPreferredSize(new java.awt.Dimension(0, 30));

        lblTongTien.setText("Tổng tiền");
        lblTongTien.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel4.setPreferredSize(new java.awt.Dimension(0, 30));

        lblVAT.setText("VAT");
        lblVAT.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel10.setPreferredSize(new java.awt.Dimension(0, 30));

        lblSuDungDiemThuong.setText("[F6] Sử dụng điểm thưởng");
        lblSuDungDiemThuong.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel8.setPreferredSize(new java.awt.Dimension(0, 30));

        lblPhaiTra.setText("Khách phải trả");
        lblPhaiTra.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel11.setPreferredSize(new java.awt.Dimension(0, 30));

        lblKhachDua.setText("[F5] Tiền khách đưa");
        lblKhachDua.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel14.setPreferredSize(new java.awt.Dimension(0, 30));

        btnThanhToan.setBackground(new java.awt.Color(1, 201, 16));
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("[F1] Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnLuuTam.setBackground(new java.awt.Color(183, 218, 246));
        btnLuuTam.setForeground(new java.awt.Color(255, 255, 255));
        btnLuuTam.setText("Lưu tạm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblSoLuongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblVAT, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lblSuDungDiemThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnInputCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(10, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLuuTam, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(pnInputCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSoLuongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSuDungDiemThuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuTam, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        lblPhaiTra.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        lblKhachDua.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        //btnThanhToan.putClientProperty(FlatClientProperties., value);
        btnThanhToan.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +6");
        btnLuuTam.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +4");

        pnRightContent.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 660));
        jPanel2.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5,$Component.borderColor,,20");

        pnLeftContent.setPreferredSize(new java.awt.Dimension(1085, 700));

        pnFunc.setPreferredSize(new java.awt.Dimension(100, 200));

        jButton2.setBackground(new java.awt.Color(183, 218, 246));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("jButton1");
        jButton2.setPreferredSize(new java.awt.Dimension(80, 40));

        jButton3.setBackground(new java.awt.Color(183, 218, 246));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("jButton1");
        jButton3.setPreferredSize(new java.awt.Dimension(80, 40));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(183, 218, 246));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("jButton1");
        jButton4.setPreferredSize(new java.awt.Dimension(80, 40));

        jButton5.setBackground(new java.awt.Color(183, 218, 246));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("jButton1");
        jButton5.setPreferredSize(new java.awt.Dimension(80, 40));

        jButton6.setBackground(new java.awt.Color(183, 218, 246));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("jButton1");
        jButton6.setPreferredSize(new java.awt.Dimension(80, 40));

        jButton7.setBackground(new java.awt.Color(183, 218, 246));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("jButton1");
        jButton7.setPreferredSize(new java.awt.Dimension(80, 40));

        jButton8.setBackground(new java.awt.Color(183, 218, 246));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("jButton1");
        jButton8.setPreferredSize(new java.awt.Dimension(80, 40));

        jButton9.setBackground(new java.awt.Color(183, 218, 246));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("jButton1");
        jButton9.setPreferredSize(new java.awt.Dimension(80, 40));

        javax.swing.GroupLayout pnFuncLayout = new javax.swing.GroupLayout(pnFunc);
        pnFunc.setLayout(pnFuncLayout);
        pnFuncLayout.setHorizontalGroup(
            pnFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFuncLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(pnFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(pnFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(pnFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        pnFuncLayout.setVerticalGroup(
            pnFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFuncLayout.createSequentialGroup()
                .addGroup(pnFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnFuncLayout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnFuncLayout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnFuncLayout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnFuncLayout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(listSanPham);

        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnLeftContentLayout = new javax.swing.GroupLayout(pnLeftContent);
        pnLeftContent.setLayout(pnLeftContentLayout);
        pnLeftContentLayout.setHorizontalGroup(
            pnLeftContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLeftContentLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(pnFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(pnLeftContentLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnLeftContentLayout.setVerticalGroup(
            pnLeftContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLeftContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pnFunc.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5,$Component.borderColor,,20");

        javax.swing.GroupLayout pnContentLayout = new javax.swing.GroupLayout(pnContent);
        pnContent.setLayout(pnContentLayout);
        pnContentLayout.setHorizontalGroup(
            pnContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContentLayout.createSequentialGroup()
                .addComponent(pnLeftContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnRightContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnContentLayout.setVerticalGroup(
            pnContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContentLayout.createSequentialGroup()
                .addComponent(pnRightContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnContentLayout.createSequentialGroup()
                .addComponent(pnLeftContent, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                .addContainerGap())
        );

        //pnRightContent.setLayout(new MigLayout("wrap,fill,gap 10", "fill"));
        //pnRightContent.putClientProperty(FlatClientProperties.STYLE, ""
            //                + "border:5,5,5,5,$Component.borderColor,,20");

        add(pnContent, java.awt.BorderLayout.CENTER);
        //pnContent.add(new DonHangPanel());
    }// </editor-fold>//GEN-END:initComponents

    private void txtCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCustomer;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnLuuTam;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblKhachDua;
    private javax.swing.JLabel lblPhaiTra;
    private javax.swing.JLabel lblSoLuongSP;
    private javax.swing.JLabel lblSuDungDiemThuong;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblVAT;
    private javax.swing.JList<String> listSanPham;
    private javax.swing.JPanel pnContent;
    private javax.swing.JPanel pnFunc;
    private javax.swing.JPanel pnInputCustomer;
    private javax.swing.JPanel pnLeftContent;
    private javax.swing.JPanel pnRightContent;
    private javax.swing.JPanel pnSearch;
    private javax.swing.JTextField txtCustomer;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
