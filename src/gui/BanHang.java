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
import nguyenvu.menu.FormManager;
import nguyenvu.utils.LayerSearchList;
import nguyenvu.utils.RoundedTextField;
import nguyenvu.utils.TableDeleteCellRender;
import nguyenvu.utils.WindowsTabbed;

/**
 *
 * @author HP
 */
public class BanHang extends SimpleForm {

    public BanHang() {
        setPreferredSize(new Dimension(1000, 740));
        initComponents();
//        WindowsTabbed.getInstance().install(pnContent);
    }
    
    private void init() {
        
    }
    
    private Icon createIcon(String path, float scale) {
        FlatSVGIcon icon = new FlatSVGIcon(path, scale);
        FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
        colorFilter.add(Color.decode("#969696"), Color.decode("#FAFAFA"), Color.decode("#969696"));
        icon.setColorFilter(colorFilter);
        return icon;
    }

    private void txtKeyReleased(KeyEvent ev) {
        DefaultListModel model = new DefaultListModel<SanPham>();
        listSanPham.setModel(model);
        try {
            String key = txtSearch.getText().trim();
            if(key.equals("")) {
                model.removeAllElements();
            }
            else {
                
            }
        }
        catch (Exception e) {
            
        }
    }
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnSearch = new javax.swing.JPanel();
        btnFilter = new javax.swing.JButton(createIcon("gui/icon/filter.svg", 1f));
        txtSearch = new RoundedTextField(40);
        layer = new LayerSearchList();
        jScrollPane1 = new javax.swing.JScrollPane();
        listSanPham = new javax.swing.JList<>();
        pnContent = new javax.swing.JPanel();
        pnRightContent = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pnInputCustomer = new javax.swing.JPanel();
        btnAddCustomer = new javax.swing.JButton(new FlatSVGIcon("gui/icon/add.svg", 0.05f));
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
        jButton1 = new javax.swing.JButton();
        lblTienThua = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        lblPhuongThucThanhToan = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

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

        txtSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
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
        //txtSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("gui/icon/search.svg"));
        //txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
            //                + "border:5,5,5,5,$Component.borderColor,,20");

        jScrollPane1.setViewportView(listSanPham);

        layer.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layerLayout = new javax.swing.GroupLayout(layer);
        layer.setLayout(layerLayout);
        layerLayout.setHorizontalGroup(
            layerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
        );
        layerLayout.setVerticalGroup(
            layerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );

        pnSearch.add(layer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, 730, 230));
        layer.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5,$Component.borderColor,,20");

        pnSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");

        add(pnSearch, java.awt.BorderLayout.PAGE_START);

        pnContent.setPreferredSize(new java.awt.Dimension(1100, 800));

        pnRightContent.setPreferredSize(new java.awt.Dimension(400, 700));
        pnRightContent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnInputCustomer.setPreferredSize(new java.awt.Dimension(100, 40));
        pnInputCustomer.setLayout(new java.awt.BorderLayout());

        btnAddCustomer.setBorderPainted(false);
        btnAddCustomer.setFocusPainted(false);
        btnAddCustomer.setPreferredSize(new java.awt.Dimension(40, 40));
        pnInputCustomer.add(btnAddCustomer, java.awt.BorderLayout.LINE_END);

        txtCustomer.setBackground(pnRightContent.getBackground());
        txtCustomer.setForeground(new java.awt.Color(102, 102, 102));
        txtCustomer.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtCustomer.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtCustomer.setPreferredSize(new java.awt.Dimension(85, 40));
        txtCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerActionPerformed(evt);
            }
        });
        pnInputCustomer.add(txtCustomer, java.awt.BorderLayout.CENTER);
        txtCustomer.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, " [F3] Thêm khách hàng");
        //txtCustomer.putClientProperty(FlatClientProperties.STYLE, ""
            //                + "border:5,0,0,5,$Component.borderColor,,20");
        txtCustomer.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("gui/icon/search.svg"));
        txtCustomer.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_COMPONENT, btnAddCustomer);

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

        jButton1.setText("jButton1");

        lblTienThua.setText("Tiền thừa");

        jButton10.setText("jButton10");

        jButton11.setText("jButton11");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        lblPhuongThucThanhToan.setText("Phương thức thanh toán");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton12.setText("jButton1");

        jButton13.setText("jButton10");

        jButton14.setText("jButton11");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLuuTam, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(45, 45, 45)
                                        .addComponent(jButton10))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton12)
                                        .addGap(45, 45, 45)
                                        .addComponent(jButton13)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton11)
                                    .addComponent(jButton14))
                                .addGap(19, 19, 19)))
                        .addContainerGap(10, Short.MAX_VALUE))))
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
                    .addComponent(lblPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
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
        lblTienThua.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");

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

        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 500));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "hinhAnh", "maSP", "tenSP", "donVi", "soLuong", "donGia", "thanhTien", "btnDelete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setPreferredSize(new java.awt.Dimension(675, 500));
        table.setRowHeight(60);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(20);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(60);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(3).setPreferredWidth(200);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(4).setPreferredWidth(80);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(5).setPreferredWidth(40);
            table.getColumnModel().getColumn(6).setResizable(false);
            table.getColumnModel().getColumn(6).setPreferredWidth(100);
            table.getColumnModel().getColumn(7).setResizable(false);
            table.getColumnModel().getColumn(7).setPreferredWidth(100);
            table.getColumnModel().getColumn(8).setResizable(false);
            table.getColumnModel().getColumn(8).setPreferredWidth(30);
        }
        table.getColumnModel().getColumn(8).setCellRenderer(new TableDeleteCellRender());
        //table.putClientProperty(FlatClientProperties.STYLE, ""
            //                + "border:5,5,5,5,$Component.borderColor,,20");

        javax.swing.GroupLayout pnLeftContentLayout = new javax.swing.GroupLayout(pnLeftContent);
        pnLeftContent.setLayout(pnLeftContentLayout);
        pnLeftContentLayout.setHorizontalGroup(
            pnLeftContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLeftContentLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(pnLeftContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnFunc, javax.swing.GroupLayout.DEFAULT_SIZE, 1057, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
        pnLeftContentLayout.setVerticalGroup(
            pnLeftContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLeftContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pnFunc.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5,$Component.borderColor,,20");
        jScrollPane2.putClientProperty(FlatClientProperties.STYLE, ""
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
                .addComponent(pnLeftContent, javax.swing.GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE)
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

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCustomer;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnLuuTam;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel lblKhachDua;
    private javax.swing.JLabel lblPhaiTra;
    private javax.swing.JLabel lblPhuongThucThanhToan;
    private javax.swing.JLabel lblSoLuongSP;
    private javax.swing.JLabel lblSuDungDiemThuong;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblVAT;
    private javax.swing.JList<String> listSanPham;
    private javax.swing.JPanel pnContent;
    private javax.swing.JPanel pnFunc;
    private javax.swing.JPanel pnInputCustomer;
    private javax.swing.JPanel pnLeftContent;
    private javax.swing.JPanel pnRightContent;
    private javax.swing.JPanel pnSearch;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtCustomer;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
