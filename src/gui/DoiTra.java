/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui;

import bill.BillDTManeger;
import bill.FieldBillDT;
import bill.ParameterBillDT;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import dao.BanHang_DAO;
import dao.DoiTra_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon_entity;
import entity.KhachHang_entity;
import entity.NhanVien_entity;
import entity.SanPham_entity;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import nguyenvu.components.SimpleForm;
import nguyenvu.model.ModelUser;
import nguyenvu.utils.AddButtonEditor;
import nguyenvu.utils.AddButtonRenderer;
import nguyenvu.utils.DoiTraCheckboxEditor;
import nguyenvu.utils.DoiTraCheckboxEvent;
import nguyenvu.utils.DoiTraCheckboxRenderer;
import nguyenvu.utils.DoiTraQuantityCellEditor;
import nguyenvu.utils.GenerateCode;
import nguyenvu.utils.HeaderRenderer;
import nguyenvu.utils.ImageRenderer;
import nguyenvu.utils.ListProductSearchPanel;
import nguyenvu.utils.RoundedTextField;
import nguyenvu.utils.TableDeleteCellEditor;
import nguyenvu.utils.TableDeleteEvent;
import raven.alerts.MessageAlerts;

/**
 *
 * @author HP
 */
public class DoiTra extends SimpleForm {
    
    private JPopupMenu menuProduct;
    private ListProductSearchPanel listProductSearch;
    private BanHang_DAO banHangDao;
    private TableDeleteEvent ev;
    private DoiTra_DAO doiTraDao;
    private final DecimalFormat df = new DecimalFormat("#,##0.##");
    
    private KhachHang_entity kh;
    private NhanVien_entity nv;
    private HoaDon_entity hd;
    
    private ModelUser user;

    public DoiTra(ModelUser user) {
        this.user = user;
        setPreferredSize(new Dimension(1020, 740));
        initComponents();
        doiTraDao = new DoiTra_DAO();
        addKeyBindings();
        txtHoaDonSearch.requestFocusInWindow();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnSearch = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        txtHoaDonSearch = new RoundedTextField(40);
        btnScanQrcode = new javax.swing.JButton();
        pnContent = new javax.swing.JPanel();
        pnRightContent = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnDoiTra = new javax.swing.JButton();
        btnChonTatCa = new javax.swing.JButton();
        lbl7 = new javax.swing.JLabel();
        lblTienHoan = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        lblNgayLapHD = new javax.swing.JLabel();
        btnLamMoi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel(new FlatSVGIcon("gui/icon/edit.svg", 0.3f));
        txtNote = new javax.swing.JTextField();
        pnCustomer = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        pnLeftContent = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        tableExchange = new javax.swing.JTable();
        pnProductSearch = new javax.swing.JPanel();
        txtProductSearch = new RoundedTextField(40);
        btnScanBarcode = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1470, 730));
        setLayout(new java.awt.BorderLayout());

        pnSearch.setBackground(new java.awt.Color(11, 101, 136));
        pnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnSearch.setPreferredSize(new java.awt.Dimension(100, 50));

        btnSearch.setBackground(new java.awt.Color(11, 101, 136));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tìm kiếm");
        btnSearch.setFocusPainted(false);
        btnSearch.setPreferredSize(new java.awt.Dimension(75, 40));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtHoaDonSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        txtHoaDonSearch.setPreferredSize(new java.awt.Dimension(85, 40));
        txtHoaDonSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "[F2] Tìm hóa đơn");
        txtHoaDonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoaDonSearchActionPerformed(evt);
            }
        });

        btnScanQrcode.setBackground(new java.awt.Color(11, 101, 136));
        btnScanQrcode.setForeground(new java.awt.Color(255, 255, 255));
        btnScanQrcode.setText("Scan QrCode hóa đơn");
        btnScanQrcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanQrcodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnSearchLayout = new javax.swing.GroupLayout(pnSearch);
        pnSearch.setLayout(pnSearchLayout);
        pnSearchLayout.setHorizontalGroup(
            pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSearchLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtHoaDonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnScanQrcode, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnSearchLayout.setVerticalGroup(
            pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSearchLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnScanQrcode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtHoaDonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtHoaDonSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("gui/icon/search.svg"));

        txtHoaDonSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "showClearButton: true");
        btnScanQrcode.setIcon(new FlatSVGIcon("gui/icon/scan.svg", 0.5f));
        btnScanQrcode.setHorizontalAlignment(SwingConstants.LEFT);

        pnSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");

        add(pnSearch, java.awt.BorderLayout.PAGE_START);

        pnContent.setPreferredSize(new java.awt.Dimension(1100, 800));

        pnRightContent.setPreferredSize(new java.awt.Dimension(400, 700));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đổi trả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(11, 101, 136))); // NOI18N

        btnDoiTra.setBackground(new java.awt.Color(1, 201, 16));
        btnDoiTra.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiTra.setText("Đổi trả (F1)");
        btnDoiTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiTraActionPerformed(evt);
            }
        });

        btnChonTatCa.setBackground(new java.awt.Color(183, 218, 246));
        btnChonTatCa.setForeground(new java.awt.Color(255, 255, 255));
        btnChonTatCa.setText("Chọn tất cả (F3)");
        btnChonTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonTatCaActionPerformed(evt);
            }
        });

        lbl7.setText("Tiền hoàn trả");

        jLabel12.setText("Mã hóa đơn");

        jLabel13.setText("Ngày lập hóa đơn");

        btnLamMoi.setBackground(new java.awt.Color(183, 218, 246));
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setText("Làm mới (F4)");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel1.setText("Ghi chú");

        txtNote.setBackground(new Color(0, 0, 0, 0)
        );
        txtNote.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(lblNgayLapHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(132, 132, 132))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(lbl7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblTienHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addComponent(btnChonTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(btnDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(lblNgayLapHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnChonTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        //btnThanhToan.putClientProperty(FlatClientProperties., value);
        btnDoiTra.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +6");
        btnChonTatCa.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +2");
        lbl7.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        lblTienHoan.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        lblTienHoan.setHorizontalAlignment(SwingConstants.RIGHT);
        btnLamMoi.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +4");

        pnCustomer.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(11, 101, 136))); // NOI18N

        jLabel2.setText("Tên khách hàng:");

        jLabel4.setText("SDT:");

        javax.swing.GroupLayout pnCustomerLayout = new javax.swing.GroupLayout(pnCustomer);
        pnCustomer.setLayout(pnCustomerLayout);
        pnCustomerLayout.setHorizontalGroup(
            pnCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCustomerLayout.createSequentialGroup()
                .addGroup(pnCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnCustomerLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel2))
                    .addGroup(pnCustomerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(lblSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnCustomerLayout.setVerticalGroup(
            pnCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCustomerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên lâp hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(11, 101, 136))); // NOI18N

        jLabel8.setText("Mã nhân viên");

        jLabel9.setText("Tên nhân viên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lblTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnRightContentLayout = new javax.swing.GroupLayout(pnRightContent);
        pnRightContent.setLayout(pnRightContentLayout);
        pnRightContentLayout.setHorizontalGroup(
            pnRightContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnRightContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnRightContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        pnRightContentLayout.setVerticalGroup(
            pnRightContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnRightContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        //jPanel2.putClientProperty(FlatClientProperties.STYLE, ""
            //                + "border:5,5,5,5,$Component.borderColor,,20");
        //pnCustomer.putClientProperty(FlatClientProperties.STYLE, ""
            //                + "border:5,5,5,5,$Component.borderColor,,20");

        pnLeftContent.setPreferredSize(new java.awt.Dimension(1085, 700));

        jScrollPane15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm trong hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(11, 101, 136))); // NOI18N
        jScrollPane15.setPreferredSize(new java.awt.Dimension(452, 500));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "HÌnh ảnh", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền", "Vấn đề", "Tình trạng", "Chọn đổi trả"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setCellSelectionEnabled(false);
        table.setRowHeight(60);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane15.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(3).setPreferredWidth(50);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(4).setPreferredWidth(100);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(5).setPreferredWidth(100);
            table.getColumnModel().getColumn(6).setResizable(false);
            table.getColumnModel().getColumn(7).setResizable(false);
            table.getColumnModel().getColumn(8).setResizable(false);
            table.getColumnModel().getColumn(8).setPreferredWidth(60);
        }
        //table.setTableHeader(null);
        DefaultTableModel model1 = (DefaultTableModel) table.getModel();
        DefaultTableModel model2 = (DefaultTableModel) tableExchange.getModel();

        table.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());

        table.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 40));
        table.getTableHeader().setBackground(new Color(11,101,136));
        table.getTableHeader().setForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setVerticalAlignment(SwingConstants.CENTER);

        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        table.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        String[] statuses = { "100%", "95%", "90%", "85%", "80%", "75%", "70%" };
        JComboBox<String> statusComboBox = new JComboBox<>(statuses);
        statusComboBox.setPreferredSize(new Dimension(statusComboBox.getPreferredSize().width, 20));

        table.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(statusComboBox));

        table.getColumnModel().getColumn(8).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox checkBox = new JCheckBox();
                if (value != null) {
                    checkBox.setSelected((Boolean) value);
                }
                return checkBox;
            }
        });

        table.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JComboBox<String> comboBox = new JComboBox<>(statuses);

                comboBox.setPreferredSize(new Dimension(comboBox.getPreferredSize().width, 30));

                if (value != null) {
                    comboBox.setSelectedItem(value);
                }

                comboBox.setRenderer(new DefaultListCellRenderer() {
                    @Override
                    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        setHorizontalAlignment(CENTER);
                        return c;
                    }
                });

                return comboBox;
            }
        });

        //table.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

        //DoiTraCheckboxEvent checkboxEvent = new DoiTraCheckboxEvent() {
            //    @Override
            //    public void onChecked(int row) {
                //        System.out.println("Checkbox checked at row: " + row);
                //    }
            //};
        //
        //table.getColumnModel().getColumn(8).setCellEditor(new DoiTraCheckboxEditor(checkboxEvent));
        //table.getColumnModel().getColumn(8).setCellRenderer(new DoiTraCheckboxRenderer());

        jScrollPane16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm đổi mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(11, 101, 136))); // NOI18N

        tableExchange.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hình ảnh", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền", "Xóa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableExchange.setRowHeight(60);
        tableExchange.setRowSelectionAllowed(false);
        tableExchange.getTableHeader().setReorderingAllowed(false);
        jScrollPane16.setViewportView(tableExchange);
        if (tableExchange.getColumnModel().getColumnCount() > 0) {
            tableExchange.getColumnModel().getColumn(0).setResizable(false);
            tableExchange.getColumnModel().getColumn(0).setPreferredWidth(80);
            tableExchange.getColumnModel().getColumn(1).setResizable(false);
            tableExchange.getColumnModel().getColumn(1).setPreferredWidth(120);
            tableExchange.getColumnModel().getColumn(2).setResizable(false);
            tableExchange.getColumnModel().getColumn(2).setPreferredWidth(200);
            tableExchange.getColumnModel().getColumn(3).setResizable(false);
            tableExchange.getColumnModel().getColumn(3).setPreferredWidth(60);
            tableExchange.getColumnModel().getColumn(4).setResizable(false);
            tableExchange.getColumnModel().getColumn(4).setPreferredWidth(100);
            tableExchange.getColumnModel().getColumn(5).setResizable(false);
            tableExchange.getColumnModel().getColumn(5).setPreferredWidth(100);
            tableExchange.getColumnModel().getColumn(6).setResizable(false);
            tableExchange.getColumnModel().getColumn(6).setPreferredWidth(60);
        }
        tableExchange.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        tableExchange.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 40));
        tableExchange.getTableHeader().setBackground(new Color(11,101,136));
        tableExchange.getTableHeader().setForeground(Color.WHITE);

        pnProductSearch.setBackground(new java.awt.Color(11, 101, 136));
        pnProductSearch.setPreferredSize(new java.awt.Dimension(618, 50));

        txtProductSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        txtProductSearch.setPreferredSize(new java.awt.Dimension(85, 40));
        txtProductSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductSearchKeyReleased(evt);
            }
        });

        btnScanBarcode.setBackground(new java.awt.Color(11, 101, 136));
        btnScanBarcode.setForeground(new java.awt.Color(255, 255, 255));
        btnScanBarcode.setText("Scan Barcode sản phẩm");
        btnScanBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanBarcodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnProductSearchLayout = new javax.swing.GroupLayout(pnProductSearch);
        pnProductSearch.setLayout(pnProductSearchLayout);
        pnProductSearchLayout.setHorizontalGroup(
            pnProductSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnProductSearchLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtProductSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnScanBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnProductSearchLayout.setVerticalGroup(
            pnProductSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnProductSearchLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnProductSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnScanBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtProductSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("gui/icon/search.svg"));
        txtProductSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "showClearButton: true");
        txtProductSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "[F3] Thêm sản phẩm đổi mới");
        btnScanQrcode.setIcon(new FlatSVGIcon("gui/icon/scan.svg", 0.5f));
        btnScanQrcode.setHorizontalAlignment(SwingConstants.LEFT);

        javax.swing.GroupLayout pnLeftContentLayout = new javax.swing.GroupLayout(pnLeftContent);
        pnLeftContent.setLayout(pnLeftContentLayout);
        pnLeftContentLayout.setHorizontalGroup(
            pnLeftContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnProductSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 1083, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLeftContentLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnLeftContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1083, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1083, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnLeftContentLayout.setVerticalGroup(
            pnLeftContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLeftContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnProductSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        //jScrollPane2.putClientProperty(FlatClientProperties.STYLE, ""
            //                + "border:5,5,5,5,$Component.borderColor,,20");
        pnProductSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");

        javax.swing.GroupLayout pnContentLayout = new javax.swing.GroupLayout(pnContent);
        pnContent.setLayout(pnContentLayout);
        pnContentLayout.setHorizontalGroup(
            pnContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContentLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnLeftContent, javax.swing.GroupLayout.DEFAULT_SIZE, 1083, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnRightContent, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnContentLayout.setVerticalGroup(
            pnContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContentLayout.createSequentialGroup()
                .addGroup(pnContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnLeftContent, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnContentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnRightContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(pnContent, java.awt.BorderLayout.CENTER);
        //pnContent.add(new DonHangPanel());
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoiTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiTraActionPerformed
        // TODO add your handling code here:
        if(table.getRowCount() < 1 || tableExchange.getRowCount() < 1) {
            MessageAlerts.getInstance().showMessage("Lỗi", "Chưa thêm sản phẩm vào đơn!", MessageAlerts.MessageType.ERROR);
            return;
        }
        
        try {

            String employeeName = user != null ? user.getName() : "Nhân viên";
            String employeeId = user != null ? user.getUserName() : "";
             
            String ghiChu = txtNote.getText();
            String invoiceCode = doiTraDao.generateInvoiceCode();
            String date = getCurrentDate();
            double totalAmount = 0;
            
           
            
            List<FieldBillDT> fields = new ArrayList<>();
        
            DefaultTableModel model = (DefaultTableModel) tableExchange.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                String productName = (String) model.getValueAt(i, 2); // tenSP
                int quantity = (int) model.getValueAt(i, 3);           // soLuong
                double unitPrice = doiTraDao.getSP((String) model.getValueAt(i, 1)).getGia();    // donGia
                double totalPrice = quantity * unitPrice;   // thanhTien

                fields.add(new FieldBillDT(productName, quantity, unitPrice, totalPrice));
            }
            
            String ptThanhToan = (String) hd.getHinhThucThanhToan();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            LocalDateTime issueDate = LocalDateTime.parse(date, formatter);
            
            HoaDon_entity hd = new HoaDon_entity(invoiceCode, issueDate, totalAmount, 0, ptThanhToan, true, kh.getSdtKH(), employeeId, "", ghiChu);
            
            if(!doiTraDao.createHD(hd)) {
                MessageAlerts.getInstance().showMessage("LỖI", "Không thể tạo hóa đơn!", MessageAlerts.MessageType.ERROR);
                refresh();
                return;
            } else {
                for (int i = 0; i < tableExchange.getRowCount(); ++i) {
                    String maSP = (String) tableExchange.getValueAt(i, 1);
                    int quantity = (int) model.getValueAt(i, 3); 
                    
                    double totalValue = 0;
                    
//                    if(loaiDT.equals("TraSanPham")) {
//                        double gia = dao.getSP(maSP).getGia();
//                        totalValue = gia * quantity;
//                    }
//                    else {
//                        if(!dao.updateSLSP(maSP, quantity)){
//                            System.out.println("Lỗi update số lượng sản phẩm");
//                        }
//                    }
//                    
                    if(!BanHang_DAO.createCTHD(new ChiTietHoaDon(invoiceCode, maSP,
                        (int) table.getValueAt(i, 3), totalValue))) {
                        System.out.println("Lỗi insert cthd");
                    }
                    
                }
                
                ParameterBillDT billData = new ParameterBillDT(date, employeeName, kh.getTenKH(), 
                    kh.getSdtKH(), totalAmount, "Đổi trả sản phẩm", ghiChu, invoiceCode, GenerateCode.generateQrcode(invoiceCode), fields); 
                BillDTManeger.getInstance().printBill(billData);
            }
            refresh();
        } catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDoiTraActionPerformed
   
    private void btnChonTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonTatCaActionPerformed
        // TODO add your handling code here:
        
        if(table.getRowCount() > 0) {
            DefaultTableModel model = (DefaultTableModel) tableExchange.getModel();
            model.setRowCount(0);
            
            for(int i = 0; i < table.getRowCount(); ++i) {
                Object[] rowData = new Object[]{
                    table.getValueAt(i, 0),
                    table.getValueAt(i, 1),
                    table.getValueAt(i, 2),
                    table.getValueAt(i, 3),
                };
                model.addRow(rowData);
            }
            updateInfor();
        }
        else MessageAlerts.getInstance().showMessage("Lỗi", "Không có sản phẩm trong đơn!", MessageAlerts.MessageType.ERROR);
        
    }//GEN-LAST:event_btnChonTatCaActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        refresh();
        
        if(txtHoaDonSearch.getText().isEmpty()) {
            MessageAlerts.getInstance().showMessage("Lỗi tìm kiếm", "Chưa nhập mã hóa đơn!", MessageAlerts.MessageType.ERROR);
            return;
        }
        String maHD = txtHoaDonSearch.getText().trim();
        
        getInforFromInvoiceCode(maHD, table);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnScanQrcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScanQrcodeActionPerformed
        // TODO add your handling code here:
        refresh();
        
        String maHD = GenerateCode.startQrcodeScanner();
//        System.out.println("maHD: " +  maHD);
        if(maHD == null) {
            MessageAlerts.getInstance().showMessage("Lỗi", "Không thể quét mã QR hoặc mã không hợp lệ.", MessageAlerts.MessageType.ERROR);
            return;
        }
        
        getInforFromInvoiceCode(maHD, table);
    }//GEN-LAST:event_btnScanQrcodeActionPerformed

    private void txtHoaDonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoaDonSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoaDonSearchActionPerformed

    private void txtProductSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductSearchKeyReleased
        // TODO add your handling code here:
        String productKey = txtProductSearch.getText().trim().toLowerCase();
        if(productKey.length() > 0) {
            ArrayList<SanPham_entity> listSP = banHangDao.searchSanPham(productKey);
            listProductSearch.setData(listSP);

            if(listProductSearch.getListSize() > 0) {
                menuProduct.show(txtProductSearch, 0, txtProductSearch.getHeight());
                menuProduct.setPopupSize(menuProduct.getWidth(), listProductSearch.getListSize() * 82 + 3);
            } else {
                menuProduct.setVisible(false);
            }
        }
    }//GEN-LAST:event_txtProductSearchKeyReleased

    private void btnScanBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScanBarcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnScanBarcodeActionPerformed
 
    private void getInforFromInvoiceCode(String maHD, JTable table)  {
        ArrayList<ChiTietHoaDon> listCTHD = doiTraDao.searchCTHD(maHD);
        
        if (listCTHD == null || listCTHD.isEmpty()) {
            MessageAlerts.getInstance().showMessage("Lỗi mã đơn", "Mã hóa đơn không hợp lệ!", MessageAlerts.MessageType.ERROR);
            refresh();
            return;
        }
        
        nv = doiTraDao.getNhanVien(maHD);
        kh = doiTraDao.getKhachHang(maHD);
        hd = doiTraDao.getHoaDon(maHD);
        
        if(kh == null) {
            MessageAlerts.getInstance().showMessage("Không thỏa điều kiện đổi trả", "Không có thông tin số điện thoại khách hàng!", MessageAlerts.MessageType.ERROR);
            refresh();
            return;
        }
        
        lblTenKH.setText(kh.getTenKH());
        lblSDT.setText(kh.getSdtKH());
        lblMaNV.setText(nv.getMaNV());
        lblTenNV.setText(nv.getHoTenNV());
        lblMaHD.setText(hd.getMaHD());
        lblNgayLapHD.setText(String.valueOf(hd.getNgayLapHD()));
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        
        for (ChiTietHoaDon cthd : listCTHD) {
            SanPham_entity sp = doiTraDao.getSP(cthd.getMaSP());
            
            if(sp != null){
                ImageIcon iiSP = new ImageIcon(getClass().getResource(sp.getHinhAnhSP()));
                Image imgSP = iiSP.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            
                Object[] rowData = new Object[]{
                    new ImageIcon(imgSP),    
                    cthd.getMaSP(),     
                    sp.getTenSP(),  
                    cthd.getSoLuongSanPham(),       
                    cthd.getThanhTien(),
                    cthd.getThanhTien(),
                    "Add"
                };
                model.addRow(rowData);
            }
        }
    }
    
    private void refresh() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        DefaultTableModel model1 = (DefaultTableModel) tableExchange.getModel();
        model1.setRowCount(0);
        lblMaNV.setText("");
        lblTenNV.setText("");
        lblTenKH.setText("");
        lblSDT.setText("");
        nv = null;
        kh = null;
        hd = null;
        lblMaNV.setText("");
        lblTenNV.setText("");
        lblTienHoan.setText("");
    }

    private double calculateTotalAmount() {
        double sum = 0;
        DefaultTableModel model = (DefaultTableModel) tableExchange.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            double gia = doiTraDao.getSP((String) model.getValueAt(i, 1)).getGia();
            int quantity = (int) model.getValueAt(i, 3); 
            Object totalValue = gia * quantity;
            if (totalValue instanceof Double) {
                sum += (Double) totalValue; // Add to sum if it's a Double
            } else if (totalValue != null && !totalValue.toString().isEmpty()) {
                try {
                    sum += Double.parseDouble(totalValue.toString()); // Try parsing if not Double
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return sum;
    }

    private String getCurrentDate() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(new java.util.Date());
    }
    
    private void addKeyBindings() {
        bindKeyToFocus(txtHoaDonSearch, KeyEvent.VK_F2);
        bindButtonKey(btnDoiTra, KeyEvent.VK_F1);
        bindButtonKey(btnChonTatCa, KeyEvent.VK_F3);
        bindButtonKey(btnLamMoi, KeyEvent.VK_F4);
    }
     
    private void bindKeyToFocus(JComponent component, int key) {
        InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = component.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(key, 0), "focusComponent");
        actionMap.put("focusComponent", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                component.requestFocusInWindow();
            }
        });
    }
    
    private void bindButtonKey(JButton button, int key) {
       InputMap inputMap = button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
       ActionMap actionMap = button.getActionMap();
       inputMap.put(KeyStroke.getKeyStroke(key, 0), "clickButton");
       actionMap.put("clickButton", new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
               button.doClick();
           }
       });
    }
    
    private void addStyleBtn(JComponent btn) {
        btn.putClientProperty(FlatClientProperties.STYLE, ""
            + "font: bold +1");
    }
    
    public void updateInfor() {
//        if(rbtnTra.isSelected()) {
//            lblTienHoan.setText(df.format(calculateTotalAmount()));
//        }
//        else {
//            lblTienHoan.setText("");
//        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonTatCa;
    private javax.swing.JButton btnDoiTra;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnScanBarcode;
    private javax.swing.JButton btnScanQrcode;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblNgayLapHD;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTienHoan;
    private javax.swing.JPanel pnContent;
    private javax.swing.JPanel pnCustomer;
    private javax.swing.JPanel pnLeftContent;
    private javax.swing.JPanel pnProductSearch;
    private javax.swing.JPanel pnRightContent;
    private javax.swing.JPanel pnSearch;
    private javax.swing.JTable table;
    private javax.swing.JTable tableExchange;
    private javax.swing.JTextField txtHoaDonSearch;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtProductSearch;
    // End of variables declaration//GEN-END:variables
}
