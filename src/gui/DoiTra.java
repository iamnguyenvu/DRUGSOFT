/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui;

import bill.BillDTManeger;
import bill.BillManeger;
import bill.FieldBill;
import bill.FieldBillDT;
import bill.ParameterBill;
import bill.ParameterBillDT;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.components.FlatPopupMenu;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import dao.BanHang_DAO;
import dao.DoiTra_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon_entity;
import entity.KhachHang_entity;
import entity.NhanVien_entity;
import entity.SanPham_entity;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import net.miginfocom.swing.MigLayout;
import nguyenvu.components.SimpleForm;
import nguyenvu.forms.StatisticalForm;
import nguyenvu.menu.FormManager;
import nguyenvu.model.ModelItemSell;
import nguyenvu.model.ModelUser;
import nguyenvu.utils.AddButtonEditor;
import nguyenvu.utils.AddButtonRenderer;
import nguyenvu.utils.CustomerSelectListener;
import nguyenvu.utils.LayerSearchList;
import nguyenvu.utils.ListCustomerPanel;
import nguyenvu.utils.ListProductSearchPanel;
import nguyenvu.utils.MoneySuggestion;
import nguyenvu.utils.ProductSearchPanel;
import nguyenvu.utils.ProductSelectListener;
import nguyenvu.utils.QuantityCellEditor;
import nguyenvu.utils.QuantityCellEvent;
import nguyenvu.utils.QuantityCellRenderer;
import nguyenvu.utils.RoundedTextField;
import nguyenvu.utils.TableActionCellEditor;
import nguyenvu.utils.TableActionEvent;
import nguyenvu.utils.TableDeleteCellEditor;
import nguyenvu.utils.TableDeleteCellRenderer;
import nguyenvu.utils.TableDeleteEvent;
import nguyenvu.utils.WindowsTabbed;
import raven.alerts.MessageAlerts;

/**
 *
 * @author HP
 */
public class DoiTra extends SimpleForm {

    private TableDeleteEvent ev;
    private DoiTra_DAO dao;
    private final DecimalFormat df = new DecimalFormat("#,##0.##");
    
    private KhachHang_entity kh;
    private NhanVien_entity nv;
    private HoaDon_entity hd;
    
    private ModelUser user;

    public DoiTra(ModelUser user) {
        this.user = user;
        setPreferredSize(new Dimension(1020, 740));
        initComponents();
        dao = new DoiTra_DAO();
        addKeyBindings();
        txtHoaDonSearch.requestFocusInWindow();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnSearch = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        txtHoaDonSearch = new RoundedTextField(40);
        pnContent = new javax.swing.JPanel();
        pnRightContent = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnDoiTra = new javax.swing.JButton();
        btnChonTatCa = new javax.swing.JButton();
        lbl7 = new javax.swing.JLabel();
        lblTienHoan = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rbtnDoi = new javax.swing.JRadioButton();
        rbtnTra = new javax.swing.JRadioButton();
        cbbLyDo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel(new FlatSVGIcon("gui/icon/edit.svg", 0.3f));
        txtLyDo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        lblNgayLapHD = new javax.swing.JLabel();
        btnLamMoi = new javax.swing.JButton();
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
        pnTableExchange = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableExchange = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

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

        javax.swing.GroupLayout pnSearchLayout = new javax.swing.GroupLayout(pnSearch);
        pnSearch.setLayout(pnSearchLayout);
        pnSearchLayout.setHorizontalGroup(
            pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSearchLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtHoaDonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnSearchLayout.setVerticalGroup(
            pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSearchLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoaDonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(230, 230, 230))
        );

        txtHoaDonSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("gui/icon/search.svg"));

        txtHoaDonSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "showClearButton: true");

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

        jLabel6.setText("Loại:");

        rbtnDoi.setSelected(true);
        rbtnDoi.setText("Đổi sản phẩm");
        rbtnDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnDoiActionPerformed(evt);
            }
        });

        rbtnTra.setText("Trả sản phẩm");
        rbtnTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnTraActionPerformed(evt);
            }
        });

        cbbLyDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbLyDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLyDoActionPerformed(evt);
            }
        });

        jLabel1.setText("Lý do:");

        txtLyDo.setBackground(new Color(0, 0, 0, 0)
        );
        txtLyDo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtLyDo)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTienHoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rbtnDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rbtnTra, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNgayLapHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbLyDo, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnChonTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnDoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(rbtnTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbLyDo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(txtLyDo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
        jLabel6.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:normal +1");
        addStyleBtn(jLabel6);
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
                .addGroup(pnCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addGroup(pnRightContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
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

        pnTableExchange.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm đổi trả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(11, 101, 136))); // NOI18N
        pnTableExchange.setPreferredSize(new java.awt.Dimension(100, 200));

        tableExchange.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "maSP", "TenSP", "soLuong", "btnDelete"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableExchange.setRowHeight(60);
        jScrollPane3.setViewportView(tableExchange);
        if (tableExchange.getColumnModel().getColumnCount() > 0) {
            tableExchange.getColumnModel().getColumn(0).setResizable(false);
            tableExchange.getColumnModel().getColumn(0).setPreferredWidth(100);
            tableExchange.getColumnModel().getColumn(1).setResizable(false);
            tableExchange.getColumnModel().getColumn(1).setPreferredWidth(200);
            tableExchange.getColumnModel().getColumn(2).setResizable(false);
            tableExchange.getColumnModel().getColumn(2).setPreferredWidth(80);
            tableExchange.getColumnModel().getColumn(3).setResizable(false);
            tableExchange.getColumnModel().getColumn(3).setPreferredWidth(60);
        }
        tableExchange.getColumnModel().getColumn(3).setCellRenderer(new nguyenvu.utils.TableDeleteCellRenderer());
        tableExchange.getColumnModel().getColumn(3).setCellEditor(new TableDeleteCellEditor(new TableDeleteEvent() {
            @Override
            public void onDelete(int row) {
                if (row >= 0) {
                    ((DefaultTableModel) tableExchange.getModel()).removeRow(row);
                    ((DefaultTableModel) tableExchange.getModel()).fireTableDataChanged();
                    //            updateLblSoLuongSP();
                }
            }
        }));

        //DefaultTableModel model1 = (DefaultTableModel) table.getModel();
        //DefaultTableModel model2 = (DefaultTableModel) tableExchange.getModel();

        //model2.addTableModelListener(new TableModelListener() {
            //    @Override
            //    public void tableChanged(TableModelEvent e) {
                //        if (e.getType() == TableModelEvent.UPDATE) {
                    //            int row = e.getFirstRow();
                    //            int column = e.getColumn();
                    //
                    //            if (column == 2) {
                        //                int newQuantity = (int) model2.getValueAt(row, column);
                        //                String maSP = (String) model2.getValueAt(row, 0);
                        //
                        //                for (int i = 0; i < model1.getRowCount(); i++) {
                            //                    String existingMaSP = (String) model1.getValueAt(i, 1);
                            //                    int availableQuantity = (int) model1.getValueAt(i, 3);
                            //                    if (maSP.equals(existingMaSP)) {
                                //                        if (newQuantity > availableQuantity) {
                                    //                            MessageAlerts.getInstance().showMessage("Lỗi",
                                        //                                "Số lượng không được lớn hơn số lượng tối đa!", MessageAlerts.MessageType.ERROR);
                                    //                            model2.setValueAt(availableQuantity, row, column);
                                    //                        }
                                //                        break;
                                //                    }
                            //                }
                        //            }
                    //        }
                //    }
            //});

    javax.swing.GroupLayout pnTableExchangeLayout = new javax.swing.GroupLayout(pnTableExchange);
    pnTableExchange.setLayout(pnTableExchangeLayout);
    pnTableExchangeLayout.setHorizontalGroup(
        pnTableExchangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
    );
    pnTableExchangeLayout.setVerticalGroup(
        pnTableExchangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
    );

    jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm trong hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(11, 101, 136))); // NOI18N
    jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 500));

    table.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "maHD", "maSP", "tenSP", "soLuong", "donGia", "thanhTien", "btnAdd"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false, true
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    table.setCellSelectionEnabled(false);
    table.setPreferredSize(new java.awt.Dimension(675, 500));
    table.setRowHeight(60);
    table.getTableHeader().setResizingAllowed(false);
    table.getTableHeader().setReorderingAllowed(false);
    jScrollPane2.setViewportView(table);
    if (table.getColumnModel().getColumnCount() > 0) {
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setResizable(false);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setResizable(false);
        table.getColumnModel().getColumn(6).setPreferredWidth(60);
    }
    //table.setTableHeader(null);
    DefaultTableModel model1 = (DefaultTableModel) table.getModel();
    DefaultTableModel model2 = (DefaultTableModel) tableExchange.getModel();

    table.getColumnModel().getColumn(6).setCellEditor(new AddButtonEditor(table, model1, model2));
    table.getColumnModel().getColumn(6).setCellRenderer(new AddButtonRenderer());

    javax.swing.GroupLayout pnLeftContentLayout = new javax.swing.GroupLayout(pnLeftContent);
    pnLeftContent.setLayout(pnLeftContentLayout);
    pnLeftContentLayout.setHorizontalGroup(
        pnLeftContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnLeftContentLayout.createSequentialGroup()
            .addContainerGap(14, Short.MAX_VALUE)
            .addGroup(pnLeftContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(pnTableExchange, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );
    pnLeftContentLayout.setVerticalGroup(
        pnLeftContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLeftContentLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(pnTableExchange, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    //pnTableExchange.putClientProperty(FlatClientProperties.STYLE, ""
        //                + "border:5,5,5,5,$Component.borderColor,,20");
    //jScrollPane2.putClientProperty(FlatClientProperties.STYLE, ""
        //                + "border:5,5,5,5,$Component.borderColor,,20");

    javax.swing.GroupLayout pnContentLayout = new javax.swing.GroupLayout(pnContent);
    pnContent.setLayout(pnContentLayout);
    pnContentLayout.setHorizontalGroup(
        pnContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnContentLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(pnLeftContent, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(pnRightContent, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );
    pnContentLayout.setVerticalGroup(
        pnContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnContentLayout.createSequentialGroup()
            .addGroup(pnContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(pnLeftContent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnContentLayout.createSequentialGroup()
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
        
//        if(txtLyDo.getText().isEmpty() || cbbLyDo.get)
        
        try {

            String employeeName = user != null ? user.getName() : "Nhân viên";  // Replace with actual employee data if available
            String employeeId = user != null ? user.getUserName() : "";
             
            String lyDo = txtLyDo.getText();
            String loaiDT;
            String billCode = generateBillCode();
            String date = getCurrentDate();
            double totalAmount = 0;
            
            if (rbtnDoi.isSelected()) {
                loaiDT = "DoiSanPham";
                totalAmount = 0;
            } else if(rbtnTra.isSelected()) {
                loaiDT = "TraSanPham";
                totalAmount = calculateTotalAmount();
            } else {
                MessageAlerts.getInstance().showMessage("LỖI", "Chưa chọn loại đổi trả!", MessageAlerts.MessageType.ERROR);
                return;
            }
            
            String loai = loaiDT.equals("DoiSanPham") ? "Đổi sản phẩm" : "Trả sản phẩm";
            
            List<FieldBillDT> fields = new ArrayList<>();
        
            DefaultTableModel model = (DefaultTableModel) tableExchange.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                String productName = (String) model.getValueAt(i, 1); // tenSP
                int quantity = (int) model.getValueAt(i, 2);           // soLuong
                double unitPrice = dao.getSP((String) model.getValueAt(i, 0)).getGia();    // donGia
                double totalPrice = quantity * unitPrice;   // thanhTien

                fields.add(new FieldBillDT(productName, quantity, unitPrice, totalPrice));
            }
            
            ParameterBillDT billData = new ParameterBillDT(date, employeeName, kh.getTenKH(), 
                    kh.getSdtKH(), totalAmount, loai, lyDo, billCode, generateQrcode(), fields);
            
            BillDTManeger.getInstance().printBill(billData);
            
            String ptThanhToan = (String) hd.getHinhThucThanhToan();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            LocalDateTime issueDate = LocalDateTime.parse(date, formatter);
            
            HoaDon_entity hd = new HoaDon_entity(billCode, issueDate, totalAmount, 0, ptThanhToan, true, kh.getSdtKH(), employeeId, loaiDT, lyDo);
            
            if(!dao.createHD(hd)) {
                MessageAlerts.getInstance().showMessage("LỖI", "Không thể tạo hóa đơn!", MessageAlerts.MessageType.ERROR);
                refresh();
                return;
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
                    table.getValueAt(i, 1),
                    table.getValueAt(i, 2),
                    table.getValueAt(i, 3),
                };
                model.addRow(rowData);
            }
        }
        else MessageAlerts.getInstance().showMessage("Lỗi", "Không có sản phẩm trong đơn!", MessageAlerts.MessageType.ERROR);
        
    }//GEN-LAST:event_btnChonTatCaActionPerformed

    private void rbtnDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnDoiActionPerformed
        // TODO add your handling code here:
        rbtnDoi.setSelected(true);
        rbtnTra.setSelected(false);
        updateInfor();
    }//GEN-LAST:event_rbtnDoiActionPerformed

    private void rbtnTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnTraActionPerformed
        // TODO add your handling code here:
        rbtnDoi.setSelected(false);
        rbtnTra.setSelected(true);
        updateInfor();
    }//GEN-LAST:event_rbtnTraActionPerformed

    private void cbbLyDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLyDoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbLyDoActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        refresh();
        
        if(txtHoaDonSearch.getText().isEmpty()) {
            MessageAlerts.getInstance().showMessage("Tìm hóa đơn", "Chưa nhập mã hóa đơn!", MessageAlerts.MessageType.ERROR);
            return;
        }
        String maHD = txtHoaDonSearch.getText().trim();
        ArrayList<ChiTietHoaDon> listCTHD = dao.searchCTHD(maHD);
        
        
        nv = dao.getNhanVien(maHD);
        kh = dao.getKhachHang(maHD);
        hd = dao.getHoaDon(maHD);
        
        lblMaNV.setText(nv.getMaNV());
        lblTenNV.setText(nv.getHotenNV());
        
        lblMaHD.setText(hd.getMaHD());
        lblNgayLapHD.setText(String.valueOf(hd.getNgayLapHD()));
        
        if(kh != null) {
            lblTenKH.setText(kh.getTenKH());
            lblSDT.setText(kh.getSdtKH());
        }
        else {
            MessageAlerts.getInstance().showMessage("Không thỏa điều kiện đổi trả", "Khách hàng không thỏa điều kiện đổi trả!", MessageAlerts.MessageType.ERROR);
            refresh();
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        
        for (ChiTietHoaDon cthd : listCTHD) {
            SanPham_entity sp = dao.getSP(cthd.getMaSP());
            
            if(sp != null){
                Object[] rowData = new Object[]{
                    cthd.getMaHD(),    
                    cthd.getMaSP(),     
                    sp.getTenSP(),  
                    cthd.getSoLuongSanPham(),       
                    cthd.getThanhTien(),
                    cthd.getSoLuongSanPham(),
                    "Add"
                };
                model.addRow(rowData);
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_btnLamMoiActionPerformed
 
    
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
        cbbLyDo.setSelectedIndex(0);
        txtLyDo.setText("");
    }

    private double calculateTotalAmount() {
        double sum = 0;
        DefaultTableModel model = (DefaultTableModel) tableExchange.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            double gia = dao.getSP((String) model.getValueAt(i, 0)).getGia();
            int quantity = (int) model.getValueAt(i, 2); 
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
    
    private InputStream generateQrcode() throws WriterException, IOException {
        NumberFormat nf = new DecimalFormat("00000000");
        Random ran = new Random();
        String invoice = nf.format(ran.nextInt(99999999) + 1);
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMat = new MultiFormatWriter().encode(invoice, BarcodeFormat.QR_CODE, 60, 60, hints);
        BufferedImage img = MatrixToImageWriter.toBufferedImage(bitMat);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(img, "png", output);
        return new ByteArrayInputStream(output.toByteArray());
    }
    
    public static String generateBillCode() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
        String billCode = "HD" + sdf.format(new java.util.Date());
        return billCode;
    }
    
    private Icon createIcon(String path, float scale) {
        FlatSVGIcon icon = new FlatSVGIcon(path, scale);
        FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
        colorFilter.add(Color.decode("#969696"), Color.decode("#FAFAFA"), Color.decode("#969696"));
        icon.setColorFilter(colorFilter);
        return icon;
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
    
    private void updateInfor() {
        DefaultTableModel model = (DefaultTableModel) tableExchange.getModel();
        if(rbtnTra.isSelected()) {
            lblTienHoan.setText(df.format(calculateTotalAmount()));
        }
        else {
            lblTienHoan.setText("");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonTatCa;
    private javax.swing.JButton btnDoiTra;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbbLyDo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
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
    private javax.swing.JPanel pnRightContent;
    private javax.swing.JPanel pnSearch;
    private javax.swing.JPanel pnTableExchange;
    private javax.swing.JRadioButton rbtnDoi;
    private javax.swing.JRadioButton rbtnTra;
    private javax.swing.JTable table;
    private javax.swing.JTable tableExchange;
    private javax.swing.JTextField txtHoaDonSearch;
    private javax.swing.JTextField txtLyDo;
    // End of variables declaration//GEN-END:variables
}
