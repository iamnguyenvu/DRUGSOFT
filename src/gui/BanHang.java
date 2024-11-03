/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui;

import bill.BillManeger;
import bill.FieldBill;
import bill.ParameterBill;
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
import entity.DonTam_entity;
import entity.HoaDon_entity;
import entity.KhachHang_entity;
import entity.NhanVien_entity;
import entity.SanPham_entity;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import net.miginfocom.swing.MigLayout;
import nguyenvu.components.SimpleForm;
import nguyenvu.forms.StatisticalForm;
import nguyenvu.menu.FormManager;
import nguyenvu.model.ModelItemSell;
import nguyenvu.model.ModelUser;
import nguyenvu.utils.CustomerSelectListener;
import nguyenvu.utils.HeaderRenderer;
import nguyenvu.utils.ImageRenderer;
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
public class BanHang extends SimpleForm {

    private TableDeleteEvent ev;
    private JPopupMenu menuProduct;
    private ListProductSearchPanel listProductSearch;
    private BanHang_DAO dao;
    private final DecimalFormat df = new DecimalFormat("#,##0.##");
    
    private JPopupMenu menuCustomer;
    private ListCustomerPanel listCustomer;
    
    private KhachHang_entity kh;
    private NhanVien_entity nv;
    
    private ModelUser user;
    
    private int giamTru;
    
    private DialogTempOrderProcess tempOrder;
    private JPopupMenu menuTempOrder;

    public BanHang(ModelUser user) {
        this.user = user;
        setPreferredSize(new Dimension(1020, 740));
        initComponents();
        dao = new BanHang_DAO();
        listProductSearch = new ListProductSearchPanel();
        menuProduct = new JPopupMenu();
        menuProduct.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        menuProduct.add(listProductSearch);
        menuProduct.setFocusable(false);
        listProductSearch.addProductSelectListener(new ProductSelectListener() {
            @Override
            public void onProductSelected(SanPham_entity sp) {
                for (int i = 0; i < table.getRowCount(); i++) {
                    String existingMaSP = (String) table.getValueAt(i, 2);
                    if (existingMaSP.equals(sp.getMaSP())) {
                        int existingQuantity = (int) table.getValueAt(i, 5);
                        table.setValueAt(existingQuantity + 1, i, 5);
                        double price = (double) table.getValueAt(i, 6);
                        table.setValueAt(price * (existingQuantity + 1), i, 7);
                        menuProduct.setVisible(false);
                        txtProductSearch.requestFocusInWindow();
                        updateLblSoLuongSP();
                        return;
                    }
                }
                addProductToTable(sp);
                menuProduct.setVisible(false);
                txtProductSearch.requestFocusInWindow();
            }
        });
        
        addKeyBindings();
        
        listCustomer = new ListCustomerPanel();
        menuCustomer = new JPopupMenu();
        menuCustomer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        menuCustomer.add(listCustomer);
        menuCustomer.setFocusable(false);
        
        listCustomer.addCustomertSelectListener(new CustomerSelectListener() {
            @Override
            public void onCustomerSeclect(KhachHang_entity customer) {
                kh = customer;
                txtCustomer.setText(kh.getSdtKH() + " - " + kh.getTenKH());
                menuCustomer.setVisible(false);
                updateLblSoLuongSP();
                cbbPhuongThucThanhToan.requestFocusInWindow();
            }
        });
        
        txtProductSearch.requestFocusInWindow();
        
        menuTempOrder = new JPopupMenu();
        tempOrder = new DialogTempOrderProcess(menuTempOrder, this);
        menuTempOrder.add(tempOrder);
        menuTempOrder.setFocusable(false);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnSearch = new javax.swing.JPanel();
        btnFilter = new javax.swing.JButton(createIcon("gui/icon/filter.svg", 1f));
        txtProductSearch = new RoundedTextField(40);
        layer = new LayerSearchList();
        jScrollPane1 = new javax.swing.JScrollPane();
        listSanPham = new javax.swing.JList<>();
        pnContent = new javax.swing.JPanel();
        pnRightContent = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pnInputCustomer = new javax.swing.JPanel();
        btnAddCustomer = new javax.swing.JButton(new FlatSVGIcon("gui/icon/add.svg", 0.05f));
        txtCustomer = new javax.swing.JTextField();
        lbl1 = new javax.swing.JLabel();
        lblSoLuongSP = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lblVAT = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lblDiemThuong = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lblKhachPhaiTra = new javax.swing.JLabel();
        lblKhachDua = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        btnLuuTam = new javax.swing.JButton();
        btnSuggest1 = new javax.swing.JButton();
        lbl7 = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        btnSuggest2 = new javax.swing.JButton();
        btnSuggest3 = new javax.swing.JButton();
        lblPhuongThucThanhToan = new javax.swing.JLabel();
        cbbPhuongThucThanhToan = new javax.swing.JComboBox<>();
        btnSuggest4 = new javax.swing.JButton();
        btnSuggest5 = new javax.swing.JButton();
        btnSuggest6 = new javax.swing.JButton();
        txtTienKhachDua = new javax.swing.JTextField();
        pnNote = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel(new FlatSVGIcon("gui/icon/edit.svg", 0.3f));
        txtNote = new javax.swing.JTextField();
        pnLeftContent = new javax.swing.JPanel();
        pnFunc = new javax.swing.JPanel();
        btnDeleteAllSP = new javax.swing.JButton();
        btnNote = new javax.swing.JButton();
        btnTempOrderProcess = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1470, 730));
        setLayout(new java.awt.BorderLayout());

        pnSearch.setBackground(new java.awt.Color(11, 101, 136));
        pnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnSearch.setPreferredSize(new java.awt.Dimension(100, 50));

        btnFilter.setBackground(new java.awt.Color(11, 101, 136));
        btnFilter.setBorderPainted(false);
        btnFilter.setFocusPainted(false);
        btnFilter.setPreferredSize(new java.awt.Dimension(75, 40));

        txtProductSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        txtProductSearch.setPreferredSize(new java.awt.Dimension(85, 40));
        txtProductSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "[F2] Thêm sản phẩm vào đơn");
        txtProductSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProductSearchFocusGained(evt);
            }
        });
        txtProductSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductSearchActionPerformed(evt);
            }
        });
        txtProductSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductSearchKeyReleased(evt);
            }
        });

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
            .addGroup(layerLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 230, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnSearchLayout = new javax.swing.GroupLayout(pnSearch);
        pnSearch.setLayout(pnSearchLayout);
        pnSearchLayout.setHorizontalGroup(
            pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSearchLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnSearchLayout.createSequentialGroup()
                        .addComponent(txtProductSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnSearchLayout.setVerticalGroup(
            pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSearchLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProductSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        //txtSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        txtProductSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("gui/icon/search.svg"));
        //txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
            //                + "border:5,5,5,5,$Component.borderColor,,20");
        txtProductSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "showClearButton: true");
        layer.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5,$Component.borderColor,,20");

        pnSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");

        add(pnSearch, java.awt.BorderLayout.PAGE_START);

        pnContent.setPreferredSize(new java.awt.Dimension(1100, 800));

        pnRightContent.setPreferredSize(new java.awt.Dimension(400, 700));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(11, 101, 136))); // NOI18N

        pnInputCustomer.setPreferredSize(new java.awt.Dimension(100, 40));
        pnInputCustomer.setLayout(new java.awt.BorderLayout());

        btnAddCustomer.setBorderPainted(false);
        btnAddCustomer.setFocusPainted(false);
        btnAddCustomer.setPreferredSize(new java.awt.Dimension(40, 40));
        btnAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCustomerActionPerformed(evt);
            }
        });
        pnInputCustomer.add(btnAddCustomer, java.awt.BorderLayout.LINE_END);

        txtCustomer.setBackground(new Color(0, 0, 0, 0));
        txtCustomer.setForeground(new java.awt.Color(102, 102, 102));
        txtCustomer.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        txtCustomer.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtCustomer.setPreferredSize(new java.awt.Dimension(85, 40));
        txtCustomer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCustomerFocusGained(evt);
            }
        });
        txtCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerActionPerformed(evt);
            }
        });
        txtCustomer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCustomerKeyReleased(evt);
            }
        });
        pnInputCustomer.add(txtCustomer, java.awt.BorderLayout.CENTER);
        txtCustomer.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, " [F3] Thêm khách hàng");
        //txtCustomer.putClientProperty(FlatClientProperties.STYLE, ""
            //                + "border:5,0,0,5,$Component.borderColor,,20");
        txtCustomer.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("gui/icon/search.svg"));
        txtCustomer.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_COMPONENT, btnAddCustomer);
        txtCustomer.putClientProperty(FlatClientProperties.STYLE, ""
            + "showClearButton: true");

        lbl1.setText("Số lượng sản phẩm");
        lbl1.setPreferredSize(new java.awt.Dimension(0, 30));

        lblSoLuongSP.setPreferredSize(new java.awt.Dimension(0, 30));

        lbl2.setText("Tổng tiền");
        lbl2.setPreferredSize(new java.awt.Dimension(0, 30));

        lblTongTien.setPreferredSize(new java.awt.Dimension(0, 30));

        lbl3.setText("VAT");
        lbl3.setPreferredSize(new java.awt.Dimension(0, 30));

        lblVAT.setPreferredSize(new java.awt.Dimension(0, 30));

        lbl4.setText("[F4] Sử dụng điểm thưởng");
        lbl4.setPreferredSize(new java.awt.Dimension(0, 30));

        lblDiemThuong.setPreferredSize(new java.awt.Dimension(0, 30));

        lbl5.setText("Khách phải trả");
        lbl5.setPreferredSize(new java.awt.Dimension(0, 30));

        lblKhachPhaiTra.setPreferredSize(new java.awt.Dimension(0, 30));

        lblKhachDua.setText("[F5] Tiền khách đưa");
        lblKhachDua.setPreferredSize(new java.awt.Dimension(0, 30));

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
        btnLuuTam.setText("[F7] Lưu tạm");
        btnLuuTam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuTamActionPerformed(evt);
            }
        });

        btnSuggest1.setText("[1]");
        btnSuggest1.setEnabled(false);

        lbl7.setText("Tiền thừa");

        btnSuggest2.setText("[2]");
        btnSuggest2.setEnabled(false);
        btnSuggest2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuggest2ActionPerformed(evt);
            }
        });

        btnSuggest3.setText("[3]");
        btnSuggest3.setEnabled(false);
        btnSuggest3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuggest3ActionPerformed(evt);
            }
        });

        lblPhuongThucThanhToan.setText("[F6] Phương thức thanh toán");

        cbbPhuongThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Thẻ" }));

        btnSuggest4.setText("[4]");
        btnSuggest4.setEnabled(false);

        btnSuggest5.setText("[5]");
        btnSuggest5.setEnabled(false);

        btnSuggest6.setText("[6]");
        btnSuggest6.setEnabled(false);

        txtTienKhachDua.setBackground(new Color(0, 0, 0, 0)
        );
        txtTienKhachDua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(153, 153, 153)));
        txtTienKhachDua.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTienKhachDuaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTienKhachDuaFocusLost(evt);
            }
        });
        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        jLabel1.setText("Ghi chú");

        txtNote.setBackground(new Color(0, 0, 0, 0)
        );
        txtNote.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout pnNoteLayout = new javax.swing.GroupLayout(pnNote);
        pnNote.setLayout(pnNoteLayout);
        pnNoteLayout.setHorizontalGroup(
            pnNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNoteLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        pnNoteLayout.setVerticalGroup(
            pnNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNoteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienKhachDua))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl5, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(lblKhachPhaiTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblSoLuongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(lblVAT, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(lblDiemThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(pnInputCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSuggest1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSuggest4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSuggest2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSuggest5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSuggest6, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                    .addComponent(btnSuggest3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnLuuTam, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnNote, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnInputCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSoLuongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiemThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKhachPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuggest2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuggest1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuggest3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuggest5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuggest4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuggest6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLuuTam, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        lblSoLuongSP.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
        lblVAT.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDiemThuong.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl5.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        lblKhachPhaiTra.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        lblKhachPhaiTra.setHorizontalAlignment(SwingConstants.RIGHT);
        lblKhachDua.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        //btnThanhToan.putClientProperty(FlatClientProperties., value);
        btnThanhToan.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +6");
        btnLuuTam.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +4");
        lbl7.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        lblTienThua.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        lblTienThua.setHorizontalAlignment(SwingConstants.RIGHT);
        txtTienKhachDua.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        txtTienKhachDua.putClientProperty(FlatClientProperties.STYLE, ""
            + "showClearButton: true");
        txtTienKhachDua.setHorizontalAlignment(SwingConstants.RIGHT);
        //pnNote.setVisible(false);

        javax.swing.GroupLayout pnRightContentLayout = new javax.swing.GroupLayout(pnRightContent);
        pnRightContent.setLayout(pnRightContentLayout);
        pnRightContentLayout.setHorizontalGroup(
            pnRightContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnRightContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        pnRightContentLayout.setVerticalGroup(
            pnRightContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnRightContentLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        //jPanel2.putClientProperty(FlatClientProperties.STYLE, ""
            //                + "border:5,5,5,5,$Component.borderColor,,20");

        pnLeftContent.setPreferredSize(new java.awt.Dimension(1085, 700));

        pnFunc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(11, 101, 136))); // NOI18N
        pnFunc.setPreferredSize(new java.awt.Dimension(100, 200));

        btnDeleteAllSP.setBackground(new java.awt.Color(183, 218, 246));
        btnDeleteAllSP.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteAllSP.setText("[F8] Xóa tất cả sản phẩm");
        btnDeleteAllSP.setPreferredSize(new java.awt.Dimension(80, 40));
        btnDeleteAllSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAllSPActionPerformed(evt);
            }
        });

        btnNote.setBackground(new java.awt.Color(183, 218, 246));
        btnNote.setForeground(new java.awt.Color(255, 255, 255));
        btnNote.setText("[F9] Ghi chú đơn hàng");
        btnNote.setPreferredSize(new java.awt.Dimension(80, 40));
        btnNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoteActionPerformed(evt);
            }
        });

        btnTempOrderProcess.setBackground(new java.awt.Color(183, 218, 246));
        btnTempOrderProcess.setForeground(new java.awt.Color(255, 255, 255));
        btnTempOrderProcess.setText("[F10] Xử lý đơn tạm");
        btnTempOrderProcess.setPreferredSize(new java.awt.Dimension(80, 40));
        btnTempOrderProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTempOrderProcessActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(183, 218, 246));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Xem danh sách sản phẩm");
        jButton8.setPreferredSize(new java.awt.Dimension(80, 40));

        javax.swing.GroupLayout pnFuncLayout = new javax.swing.GroupLayout(pnFunc);
        pnFunc.setLayout(pnFuncLayout);
        pnFuncLayout.setHorizontalGroup(
            pnFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFuncLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnDeleteAllSP, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnNote, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnTempOrderProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        pnFuncLayout.setVerticalGroup(
            pnFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFuncLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeleteAllSP, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNote, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTempOrderProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        addStyleBtn(btnDeleteAllSP);
        addStyleBtn(btnNote);
        addStyleBtn(btnTempOrderProcess);
        addStyleBtn(jButton8);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(11, 101, 136))); // NOI18N
        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 500));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Hình ảnh", "Mã sản phẩm", "Tên sản phẩm", "Đơn vị", "Số lương", "Đơn giá", "Thành tiền", "Xóa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setPreferredSize(new java.awt.Dimension(675, 500));
        table.setRowHeight(60);
        table.setRowSelectionAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(30);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(80);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(120);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(3).setPreferredWidth(280);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(4).setPreferredWidth(100);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(5).setPreferredWidth(50);
            table.getColumnModel().getColumn(6).setResizable(false);
            table.getColumnModel().getColumn(6).setPreferredWidth(100);
            table.getColumnModel().getColumn(7).setResizable(false);
            table.getColumnModel().getColumn(7).setPreferredWidth(100);
            table.getColumnModel().getColumn(8).setResizable(false);
            table.getColumnModel().getColumn(8).setPreferredWidth(60);
        }
        table.getColumnModel().getColumn(8).setCellRenderer(new nguyenvu.utils.TableDeleteCellRenderer());
        table.getColumnModel().getColumn(8).setCellEditor(new TableDeleteCellEditor(new TableDeleteEvent() {
            @Override
            public void onDelete(int row) {
                if (row >= 0 && row < table.getRowCount()) {
                    ((DefaultTableModel) table.getModel()).removeRow(row);
                    ((DefaultTableModel) table.getModel()).fireTableDataChanged();
                }
                for(int i = 0; i < table.getRowCount(); ++i) {
                    table.setValueAt(i+1, i, 0);
                }
                updateLblSoLuongSP();
            }
        }));

        table.getColumnModel().getColumn(5).setCellEditor(new QuantityCellEditor(this));
        table.getColumnModel().getColumn(5).setCellRenderer(new QuantityCellRenderer());
        //table.setTableHeader(null);
        table.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        table.getTableHeader().setBackground(new Color(11,101,136));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 40));
        table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());

        javax.swing.GroupLayout pnLeftContentLayout = new javax.swing.GroupLayout(pnLeftContent);
        pnLeftContent.setLayout(pnLeftContentLayout);
        pnLeftContentLayout.setHorizontalGroup(
            pnLeftContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftContentLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(pnLeftContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnFunc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnLeftContentLayout.setVerticalGroup(
            pnLeftContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLeftContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        //pnFunc.putClientProperty(FlatClientProperties.STYLE, ""
            //                + "border:5,5,5,5,$Component.borderColor,,20");
        //jScrollPane2.putClientProperty(FlatClientProperties.STYLE, ""
            //                + "border:5,5,5,5,$Component.borderColor,,20");

        javax.swing.GroupLayout pnContentLayout = new javax.swing.GroupLayout(pnContent);
        pnContent.setLayout(pnContentLayout);
        pnContentLayout.setHorizontalGroup(
            pnContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnContentLayout.createSequentialGroup()
                .addComponent(pnLeftContent, javax.swing.GroupLayout.PREFERRED_SIZE, 1079, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnRightContent, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void txtProductSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductSearchActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        if(table.getRowCount() < 1) {
            MessageAlerts.getInstance().showMessage("Lỗi", "Chưa thêm sản phẩm vào đơn!", MessageAlerts.MessageType.ERROR);
            return;
        }
        
        if(txtTienKhachDua.getText().trim().replace(",", "").isEmpty()) {
            MessageAlerts.getInstance().showMessage("Lỗi", "Chưa nhập tiền khách đưa!", MessageAlerts.MessageType.ERROR);
            return;
        }
        
        double thanhToan = Double.parseDouble(lblKhachPhaiTra.getText().trim().replace(",", ""));
        double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText().trim().replace(",", ""));
        
        if(tienKhachDua < thanhToan) {
            MessageAlerts.getInstance().showMessage("Lỗi", "Tiền khách đưa phải lớn hơn hoặc bằng tiền phải thanh toán!", MessageAlerts.MessageType.ERROR);
            return;
        }
        
        try {
            List<FieldBill> fields = new ArrayList<>();
        
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                String productName = (String) model.getValueAt(i, 3); // tenSP
                int quantity = (int) model.getValueAt(i, 5);           // soLuong
                double unitPrice = (double) model.getValueAt(i, 6);    // donGia
                double totalPrice = (double) model.getValueAt(i, 7);   // thanhTien

                fields.add(new FieldBill(productName, quantity, unitPrice, totalPrice));
            }
        
            kh = txtCustomer.getText().isEmpty() ? null : kh;

            String employeeName = user != null ? user.getName() : "Nhân viên";  // Replace with actual employee data if available
            String employeeId = user != null ? user.getUserName() : "";
            
            String customerName = kh != null ? kh.getTenKH() : "Khách vãng lai"; // Default customer name if kh is null
            String customerPhone = kh != null ? kh.getSdtKH().trim() : "";
            double totalAmount = calculateTotalAmount();
            int discount = kh != null ? giamTru : 0;                       // Adjust if discounts apply

            int rewardPoints = (int) (kh != null ? thanhToan * 0.01 : 0);
            String billID = generateBillCode();

            ParameterBill billData = new ParameterBill(
                    getCurrentDate(), employeeName, customerName, customerPhone, 
                    totalAmount, discount, thanhToan, rewardPoints, 
                    billID, generateQrcode(), fields);

            BillManeger.getInstance().printBill(billData);
            
            String ptThanhToan = (String) cbbPhuongThucThanhToan.getSelectedItem();
            String ghiChu = txtNote.getText();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            LocalDateTime issueDate = LocalDateTime.parse(getCurrentDate(), formatter);
            
            HoaDon_entity hd = new HoaDon_entity(billID, issueDate, thanhToan, discount, ptThanhToan, true, customerPhone, employeeId, "BanSanPham", ghiChu);
            if(!dao.createHD(hd)) {
                MessageAlerts.getInstance().showMessage("LỖI", "Không thể tạo hóa đơn!", MessageAlerts.MessageType.ERROR);
                refresh();
                return;
            }
            
            refresh();
        } catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnSuggest3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuggest3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuggest3ActionPerformed

    private void btnSuggest2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuggest2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuggest2ActionPerformed

    private void btnAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCustomerActionPerformed
        // TODO add your handling code here:
        KhachHang_GUI gui = new KhachHang_GUI();
        FormManager.showForm(gui);
        gui.hienThiDialogThem();
    }//GEN-LAST:event_btnAddCustomerActionPerformed

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed
   
    private void btnDeleteAllSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAllSPActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_btnDeleteAllSPActionPerformed

    private void txtProductSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductSearchKeyReleased
        // TODO add your handling code here:
        String productKey = txtProductSearch.getText().trim().toLowerCase();
        if(productKey.length() > 0) {
            ArrayList<SanPham_entity> listSP = dao.searchSanPham(productKey);
            listProductSearch.setData(listSP);

            if(listProductSearch.getListSize() > 0) {
                menuProduct.show(txtProductSearch, 0, txtProductSearch.getHeight());
                menuProduct.setPopupSize(menuProduct.getWidth(), listProductSearch.getListSize() * 82 + 3);
            } else {
                menuProduct.setVisible(false);
            }
        }
    }//GEN-LAST:event_txtProductSearchKeyReleased

    private void txtCustomerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustomerKeyReleased
        // TODO add your handling code here:
        String customerSDTKey = txtCustomer.getText().trim();
        if(customerSDTKey.length() > 0) {
            ArrayList<KhachHang_entity> listKH = dao.searchKhachHang(customerSDTKey);
            listCustomer.setData(listKH);

            if(listCustomer.getListSize() > 0) {
                menuCustomer.show(txtCustomer, 0, txtCustomer.getHeight());
                menuCustomer.setPopupSize(txtCustomer.getWidth(), listCustomer.getListSize() * 51);
            } else {
                menuCustomer.setVisible(false);
            }
        }
    }//GEN-LAST:event_txtCustomerKeyReleased

    private void btnNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoteActionPerformed
        // TODO add your handling code here:
        txtNote.requestFocus();
    }//GEN-LAST:event_btnNoteActionPerformed

    private void btnLuuTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuTamActionPerformed
        // TODO add your handling code here:
        kh = txtCustomer.getText().isEmpty() ? null : kh;
        
        if(table.getRowCount() < 1) {
            MessageAlerts.getInstance().showMessage("Lỗi", "Không có sản phẩm trong đơn để lưu tạm!", MessageAlerts.MessageType.ERROR);
            return;
        }
        
        if(kh != null) {
            luuDonTam(kh.getSdtKH(), kh.getTenKH());
            refresh();
        }
        else MessageAlerts.getInstance().showMessage("Lỗi", "Cần phải có thông tin khách hàng để LƯU TẠM!", MessageAlerts.MessageType.ERROR);
    }//GEN-LAST:event_btnLuuTamActionPerformed

    private void txtTienKhachDuaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTienKhachDuaFocusGained
        // TODO add your handling code here:
        btnSuggest1.setEnabled(false);
        btnSuggest2.setEnabled(false);
        btnSuggest3.setEnabled(false);
        btnSuggest4.setEnabled(false);
        btnSuggest5.setEnabled(false);
        btnSuggest6.setEnabled(false);
    }//GEN-LAST:event_txtTienKhachDuaFocusGained

    private void txtTienKhachDuaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTienKhachDuaFocusLost
        // TODO add your handling code here:
        updateSuggestButton();
        updateTienThua();
    }//GEN-LAST:event_txtTienKhachDuaFocusLost

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        // TODO add your handling code here:
        if(!txtTienKhachDua.getText().trim().isEmpty()) {
            updateTienThua();
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void txtCustomerFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCustomerFocusGained
        // TODO add your handling code here:
        menuProduct.setVisible(false);
    }//GEN-LAST:event_txtCustomerFocusGained

    private void txtProductSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProductSearchFocusGained
        // TODO add your handling code here:
        menuCustomer.setVisible(false);
    }//GEN-LAST:event_txtProductSearchFocusGained

    private void btnTempOrderProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTempOrderProcessActionPerformed
        // TODO add your handling code here:
        menuTempOrder.setVisible(true);
        tempOrder.setVisible(true);
        menuTempOrder.show(this, 100, 100);
    }//GEN-LAST:event_btnTempOrderProcessActionPerformed
 
    private void addProductToTable(SanPham_entity sp) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ImageIcon iiSP = new ImageIcon(getClass().getResource(sp.getHinhAnhSP()));
	Image imgSP = iiSP.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        Object[] rowData = new Object[] {
            model.getRowCount() + 1, // STT
            new ImageIcon(imgSP), // hinhAnh
            sp.getMaSP(), // maSP
            sp.getTenSP(), // tenSP
            sp.getDonViTinh(), // donVi
            1, // soLuong (mặc định là 1)
            sp.getGia(), // donGia
            sp.getGia()
        };
        model.addRow(rowData);
        updateLblSoLuongSP();
    }

    public void updateLblSoLuongSP() {
        int sumSoLuong = 0;
        double sumThanhTien = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            sumSoLuong += Integer.parseInt(table.getValueAt(i, 5).toString());
            sumThanhTien += Double.parseDouble(table.getValueAt(i, 7).toString());
        }
        
        lblSoLuongSP.setText(String.valueOf(sumSoLuong));
        lblTongTien.setText(df.format(calculateTotalAmount()));
        lblVAT.setText("0");

        if(kh != null) {
            int diemThuong = kh.getDiemThuong();
            int coefDT = diemThuong / 1000;
            giamTru = coefDT > 0 ? coefDT * 1000 : 0;
        }
        lblDiemThuong.setText(String.valueOf(df.format(giamTru)));
        lblKhachPhaiTra.setText(String.valueOf(df.format(sumThanhTien - giamTru)));
        
        updateSuggestButton();
        updateTienThua();
    }

    private void updateTienThua() {
        try {
            double khachPhaiTra = Double.parseDouble(lblKhachPhaiTra.getText().replace(",", ""));
            double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText().replace(",", ""));
            lblTienThua.setText(df.format(tienKhachDua - khachPhaiTra));
        } catch (NumberFormatException e) {
            lblTienThua.setText("0");
        }
    }

    private void updateSuggestButton() {
        double khachPhaiTra = Double.parseDouble(lblKhachPhaiTra.getText().replace(",", ""));
        List<Integer> suggestions = MoneySuggestion.suggestAmounts((int) khachPhaiTra);

        for (int i = 1; i <= 6; i++) {
            JButton button = getSuggestButton(i);

            if (i <= suggestions.size()) {
                int suggestionAmount = suggestions.get(i - 1);
                button.setText(df.format(suggestionAmount) + " (" + i + ")");
                button.setEnabled(true);
                
                for (ActionListener al : button.getActionListeners()) {
                    button.removeActionListener(al);
                }
                
                button.addActionListener(e -> {
                    txtTienKhachDua.setText(df.format(suggestionAmount));
                    updateTienThua();
                });
            } else {
                button.setText("N/A");
                button.setEnabled(false);
            }
        }
    }
    
    private void refresh() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        kh = null;
        giamTru = 0;
        lblDiemThuong.setText("");
        lblSoLuongSP.setText("");
        lblTongTien.setText("");
        lblVAT.setText("");
        lblKhachPhaiTra.setText("");
        txtTienKhachDua.setText("");
        txtCustomer.setText("");
        txtProductSearch.setText("");
        lblTienThua.setText("");
        cbbPhuongThucThanhToan.setSelectedIndex(0);
        updateLblSoLuongSP();
        defaultButton();
    }

    private void defaultButton() {
        for (int i = 1; i <= 6; i++) {
            JButton button = getSuggestButton(i);
            button.setText("N/A");
            button.setEnabled(false);
        }
    }

    private JButton getSuggestButton(int index) {
        switch (index) {
            case 1: return btnSuggest1;
            case 2: return btnSuggest2;
            case 3: return btnSuggest3;
            case 4: return btnSuggest4;
            case 5: return btnSuggest5;
            case 6: return btnSuggest6;
            default: return null;
        }
    }
    
    private double calculateTotalAmount() {
        double sum = 0;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            Object totalValue = model.getValueAt(i, 7); // Get thanhTien from column 7
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
    
    private static String generateBillCode() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyMMdd");
        Random random = new Random();
        int rdDigit = 1000 + random.nextInt(90000);
        String billCode = "HD" + sdf.format(new java.util.Date()) + rdDigit;
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
        bindKeyToFocus(txtProductSearch, KeyEvent.VK_F2);
        bindKeyToFocus(txtCustomer, KeyEvent.VK_F3);
        bindKeyToFocus(cbbPhuongThucThanhToan, KeyEvent.VK_F6);
        bindKeyToFocus(txtTienKhachDua, KeyEvent.VK_F5);
        bindButtonKey(btnSuggest1, KeyEvent.VK_1);
        bindButtonKey(btnSuggest2, KeyEvent.VK_2);
        bindButtonKey(btnSuggest3, KeyEvent.VK_3);
        bindButtonKey(btnSuggest4, KeyEvent.VK_4);
        bindButtonKey(btnSuggest5, KeyEvent.VK_5);
        bindButtonKey(btnSuggest6, KeyEvent.VK_6);
        bindButtonKey(btnThanhToan, KeyEvent.VK_F1);
        bindButtonKey(btnLuuTam, KeyEvent.VK_F7);
        bindButtonKey(btnDeleteAllSP, KeyEvent.VK_F8);
        bindButtonKey(btnNote, KeyEvent.VK_F9);
        bindButtonKey(btnTempOrderProcess, KeyEvent.VK_F10);
        bindButtonKey(btnAddCustomer, KeyEvent.VK_ADD);
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
    
    private void addStyleBtn(JButton btn) {
        btn.putClientProperty(FlatClientProperties.STYLE, ""
            + "font: bold +1");
    }
    
    private void luuDonTam(String sdt, String tenKhachHang) {
        List<SanPham_entity> listSP = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            SanPham_entity sanPham = new SanPham_entity(
                model.getValueAt(i, 2).toString(),  // maSP
                model.getValueAt(i, 3).toString(),  // tenSP
                (double) model.getValueAt(i, 6),    // donGia
                (int) model.getValueAt(i, 5)       // soLuong
            );
            listSP.add(sanPham);
        }

        DonTam_entity donTam = new DonTam_entity(sdt, tenKhachHang, listSP);
        tempOrder.themDonTam(donTam);
    }
    
    public void loadDonTam(DonTam_entity donTam) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        
        kh = dao.getKhachHang(donTam.getSdtKH());
        txtCustomer.setText(kh.getTenKH() + " - " + kh.getSdtKH());
        
        for (SanPham_entity sp : donTam.getListSP()) {
            SanPham_entity temp = dao.getSP(sp.getMaSP());
            ImageIcon iiSP = new ImageIcon(getClass().getResource(temp.getHinhAnhSP()));
            Image imgSP = iiSP.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            Object[] rowData = new Object[] {
                model.getRowCount() + 1, // STT
                new ImageIcon(imgSP), // hinhAnh
                sp.getMaSP(), // maSP
                sp.getTenSP(), // tenSP
                temp.getDonViTinh(), // donVi
                sp.getSoLuong(), 
                temp.getGia(), // donGia
                sp.getSoLuong() * temp.getGia()
            };
            model.addRow(rowData);
        }
        updateLblSoLuongSP();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCustomer;
    private javax.swing.JButton btnDeleteAllSP;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnLuuTam;
    private javax.swing.JButton btnNote;
    private javax.swing.JButton btnSuggest1;
    private javax.swing.JButton btnSuggest2;
    private javax.swing.JButton btnSuggest3;
    private javax.swing.JButton btnSuggest4;
    private javax.swing.JButton btnSuggest5;
    private javax.swing.JButton btnSuggest6;
    private javax.swing.JButton btnTempOrderProcess;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JComboBox<String> cbbPhuongThucThanhToan;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lblDiemThuong;
    private javax.swing.JLabel lblKhachDua;
    private javax.swing.JLabel lblKhachPhaiTra;
    private javax.swing.JLabel lblPhuongThucThanhToan;
    private javax.swing.JLabel lblSoLuongSP;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblVAT;
    private javax.swing.JList<String> listSanPham;
    private javax.swing.JPanel pnContent;
    private javax.swing.JPanel pnFunc;
    private javax.swing.JPanel pnInputCustomer;
    private javax.swing.JPanel pnLeftContent;
    private javax.swing.JPanel pnNote;
    private javax.swing.JPanel pnRightContent;
    private javax.swing.JPanel pnSearch;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtCustomer;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtProductSearch;
    private javax.swing.JTextField txtTienKhachDua;
    // End of variables declaration//GEN-END:variables
}
