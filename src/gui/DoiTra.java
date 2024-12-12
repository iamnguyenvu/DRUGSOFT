/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui;

import bill.BillDTManeger;
import bill.FieldBillDoi;
import bill.FieldBillDoiTra;
import bill.ParameterBillDT;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import dao.BanHang_DAO;
import dao.DoiTra_DAO;
import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonDoiTra_entity;
import entity.HoaDonDoiTra_entity;
import entity.HoaDon_entity;
import entity.KhachHang_entity;
import entity.NhanVien_entity;
import entity.SanPham_entity;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
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
import nguyenvu.utils.DoiTraQuantityCellRenderer;
import nguyenvu.utils.GenerateCode;
import nguyenvu.utils.HeaderRenderer;
import nguyenvu.utils.ImageRenderer;
import nguyenvu.utils.ListProductSearchPanel;
import nguyenvu.utils.MoneySuggestion;
import nguyenvu.utils.ProductSelectListener;
import nguyenvu.utils.QuantityCellEditor;
import nguyenvu.utils.QuantityCellRenderer;
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
    private int giamTru;
    
    private double tongTienHoan = 0;
    private double tongTienMua = 0;
    private boolean isKhachPhaiTra = false;

    public DoiTra(ModelUser user) {
        this.user = user;
        setPreferredSize(new Dimension(1020, 740));
        initComponents();
        banHangDao = new BanHang_DAO();
        doiTraDao = new DoiTra_DAO();
        addKeyBindings();
        txtHoaDonSearch.requestFocusInWindow();
        
        listProductSearch = new ListProductSearchPanel();
        menuProduct = new JPopupMenu();
        menuProduct.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        menuProduct.add(listProductSearch);
        menuProduct.setFocusable(false);
        listProductSearch.addProductSelectListener(new ProductSelectListener() {
            @Override
            public void onProductSelected(SanPham_entity sp) {
                if(sp.getSoLuong() < 1) {
                    MessageAlerts.getInstance().showMessage("Cảnh báo", "Sản phẩm không đủ số lượng để thêm vào giỏ hàng!", MessageAlerts.MessageType.WARNING);
                    return;
                }
                addProductToTable(sp);
                menuProduct.setVisible(false);
                txtProductSearch.requestFocusInWindow();
            }
        });
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
        btnXoaTatCa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel(new FlatSVGIcon("gui/icon/edit.svg", 0.3f));
        txtNote = new javax.swing.JTextField();
        lbl7 = new javax.swing.JLabel();
        lblTienHoan = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTongTienGoc = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTongTienTra = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblPhiTraHang = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTongTienHoan = new javax.swing.JLabel();
        pnMuaHang = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        lblSoLuongSP = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lblVAT = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lblDiemThuong = new javax.swing.JLabel();
        pnKhachTra = new javax.swing.JPanel();
        lbl5 = new javax.swing.JLabel();
        lblKhachDua = new javax.swing.JLabel();
        lblKhachPhaiTra = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        lblPhuongThucThanhToan = new javax.swing.JLabel();
        cbbPhuongThucThanhToan = new javax.swing.JComboBox<>();
        btnSuggest1 = new javax.swing.JButton();
        btnSuggest2 = new javax.swing.JButton();
        btnSuggest3 = new javax.swing.JButton();
        btnSuggest4 = new javax.swing.JButton();
        btnSuggest5 = new javax.swing.JButton();
        btnSuggest6 = new javax.swing.JButton();
        lblTienThua = new javax.swing.JLabel();
        lbl8 = new javax.swing.JLabel();
        pnCustomer = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
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
        btnDoiTra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDoiTra.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiTra.setText("Đổi trả (F1)");
        btnDoiTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiTraActionPerformed(evt);
            }
        });

        btnXoaTatCa.setBackground(new java.awt.Color(183, 218, 246));
        btnXoaTatCa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaTatCa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaTatCa.setText("Xóa tất cả sản phẩm đổi mới (F4)");
        btnXoaTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTatCaActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(183, 218, 246));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setText("Làm mới (F5)");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel1.setText("Ghi chú");

        txtNote.setBackground(new Color(0, 0, 0, 0)
        );
        txtNote.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        lbl7.setText("Tiền hoàn trả");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(11, 101, 136));
        jLabel3.setText("Trả hàng");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tổng tiền gốc hàng mua");

        lblTongTienGoc.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Tổng tiền hàng trả");

        lblTongTienTra.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Phí trả hàng");

        lblPhiTraHang.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Tổng tiền hoàn trả");

        lblTongTienHoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTongTienHoan.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(11, 101, 136));
        jLabel9.setText("Mua hàng");

        lbl1.setText("Số lượng sản phẩm");
        lbl1.setPreferredSize(new java.awt.Dimension(0, 30));

        lblSoLuongSP.setPreferredSize(new java.awt.Dimension(0, 30));

        lbl2.setText("Tổng tiền");
        lbl2.setPreferredSize(new java.awt.Dimension(0, 30));

        lblTongTien.setPreferredSize(new java.awt.Dimension(0, 30));

        lbl3.setText("VAT");
        lbl3.setPreferredSize(new java.awt.Dimension(0, 30));

        lblVAT.setPreferredSize(new java.awt.Dimension(0, 30));

        lbl4.setText("Sử dụng điểm thưởng");
        lbl4.setPreferredSize(new java.awt.Dimension(0, 30));

        lblDiemThuong.setPreferredSize(new java.awt.Dimension(0, 30));

        lbl5.setText("Khách phải trả");
        lbl5.setPreferredSize(new java.awt.Dimension(0, 30));

        lblKhachDua.setText("[F5] Tiền khách đưa");
        lblKhachDua.setPreferredSize(new java.awt.Dimension(0, 30));

        lblKhachPhaiTra.setPreferredSize(new java.awt.Dimension(0, 30));

        txtTienKhachDua.setBackground(new Color(0, 0, 0, 0)
        );
        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
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

        lblPhuongThucThanhToan.setText("[F6] Phương thức thanh toán");

        cbbPhuongThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Thẻ" }));

        btnSuggest1.setText("N/A");
        btnSuggest1.setEnabled(false);

        btnSuggest2.setText("N/A");
        btnSuggest2.setEnabled(false);
        btnSuggest2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuggest2ActionPerformed(evt);
            }
        });

        btnSuggest3.setText("N/A");
        btnSuggest3.setEnabled(false);
        btnSuggest3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuggest3ActionPerformed(evt);
            }
        });

        btnSuggest4.setText("N/A");
        btnSuggest4.setEnabled(false);

        btnSuggest5.setText("N/A");
        btnSuggest5.setEnabled(false);

        btnSuggest6.setText("N/A");
        btnSuggest6.setEnabled(false);

        lbl8.setText("Tiền thừa");

        javax.swing.GroupLayout pnKhachTraLayout = new javax.swing.GroupLayout(pnKhachTra);
        pnKhachTra.setLayout(pnKhachTraLayout);
        pnKhachTraLayout.setHorizontalGroup(
            pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhachTraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnKhachTraLayout.createSequentialGroup()
                        .addComponent(lbl8, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKhachTraLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnKhachTraLayout.createSequentialGroup()
                                .addGroup(pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSuggest1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSuggest4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addGroup(pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSuggest2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSuggest5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSuggest6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSuggest3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnKhachTraLayout.createSequentialGroup()
                                    .addComponent(lblPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cbbPhuongThucThanhToan, 0, 159, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnKhachTraLayout.createSequentialGroup()
                                    .addGroup(pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbl5, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                        .addComponent(lblKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblKhachPhaiTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))))
                        .addGap(22, 22, 22))))
        );
        pnKhachTraLayout.setVerticalGroup(
            pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhachTraLayout.createSequentialGroup()
                .addGroup(pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(lblKhachPhaiTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuggest2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuggest1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuggest3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuggest5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuggest4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuggest6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnKhachTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        lbl5.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        lblKhachDua.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        lblKhachPhaiTra.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        lblKhachPhaiTra.setHorizontalAlignment(SwingConstants.RIGHT);
        txtTienKhachDua.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        txtTienKhachDua.putClientProperty(FlatClientProperties.STYLE, ""
            + "showClearButton: true");
        txtTienKhachDua.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTienThua.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        lblTienThua.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl7.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");

        javax.swing.GroupLayout pnMuaHangLayout = new javax.swing.GroupLayout(pnMuaHang);
        pnMuaHang.setLayout(pnMuaHangLayout);
        pnMuaHangLayout.setHorizontalGroup(
            pnMuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 357, Short.MAX_VALUE)
            .addGroup(pnMuaHangLayout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnMuaHangLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnMuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnMuaHangLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDiemThuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnMuaHangLayout.createSequentialGroup()
                        .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVAT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnMuaHangLayout.createSequentialGroup()
                        .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnMuaHangLayout.createSequentialGroup()
                        .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSoLuongSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnMuaHangLayout.setVerticalGroup(
            pnMuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMuaHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnMuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSoLuongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnMuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnMuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnMuaHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiemThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblSoLuongSP.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
        lblVAT.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDiemThuong.setHorizontalAlignment(SwingConstants.RIGHT);
        pnKhachTra.setVisible(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl7, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNote))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(lblTienHoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTongTienHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(lblPhiTraHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(lblTongTienTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(lblTongTienGoc, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 31, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(pnMuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongTienGoc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongTienTra, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhiTraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongTienHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnMuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lbl7.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        lblTienHoan.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        lblTienHoan.setHorizontalAlignment(SwingConstants.RIGHT);
        pnMuaHang.setVisible(false);

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnXoaTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnDoiTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        //btnThanhToan.putClientProperty(FlatClientProperties., value);
        btnDoiTra.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +6");

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
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(pnCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout pnRightContentLayout = new javax.swing.GroupLayout(pnRightContent);
        pnRightContent.setLayout(pnRightContentLayout);
        pnRightContentLayout.setHorizontalGroup(
            pnRightContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnRightContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnRightContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnRightContentLayout.setVerticalGroup(
            pnRightContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnRightContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
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

            },
            new String [] {
                "HÌnh ảnh", "Mã sản phẩm", "Tên sản phẩm", "Số lượng trả", "Số lượng tối đa", "Đơn giá", "Thành tiền", "Vấn đề", "Tình trạng", "Chọn đổi trả"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, true, true, true
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
            table.getColumnModel().getColumn(4).setPreferredWidth(0);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(5).setPreferredWidth(100);
            table.getColumnModel().getColumn(6).setResizable(false);
            table.getColumnModel().getColumn(6).setPreferredWidth(100);
            table.getColumnModel().getColumn(7).setResizable(false);
            table.getColumnModel().getColumn(7).setPreferredWidth(120);
            table.getColumnModel().getColumn(8).setResizable(false);
            table.getColumnModel().getColumn(8).setPreferredWidth(60);
            table.getColumnModel().getColumn(9).setResizable(false);
            table.getColumnModel().getColumn(9).setPreferredWidth(0);
        }
        table.getColumnModel().getColumn(4).setMinWidth(0);
        table.getColumnModel().getColumn(4).setMaxWidth(0);
        table.getColumnModel().getColumn(4).setWidth(0);

        table.getColumnModel().getColumn(9).setMinWidth(0);
        table.getColumnModel().getColumn(9).setMaxWidth(0);
        table.getColumnModel().getColumn(9).setWidth(0);

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
        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);

        String[] statuses = { "100%", "95%", "90%", "85%", "80%", "75%", "70%" };
        JComboBox<String> statusComboBox = new JComboBox<>(statuses);
        statusComboBox.setPreferredSize(new Dimension(statusComboBox.getPreferredSize().width, 20));

        table.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(statusComboBox));

        statusComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int row = table.getEditingRow();
                    int column = table.getEditingColumn();

                    Object selectedStatus = statusComboBox.getSelectedItem();

                    if (row != -1 && column != -1) {
                        table.setValueAt(selectedStatus, row, column);
                    }
                    updatePnTraHang();
                }
            }
        });

        table.getColumnModel().getColumn(8).setCellRenderer(new DefaultTableCellRenderer() {
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

        DoiTraQuantityCellEditor editor = new DoiTraQuantityCellEditor(this);
        table.getColumnModel().getColumn(3).setCellEditor(editor);

        jScrollPane16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm đổi mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(11, 101, 136))); // NOI18N

        tableExchange.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Hình ảnh", "Mã sản phẩm", "Tên sản phẩm", "Đơn vị", "Số lượng", "Đơn giá", "Thành tiền", "Xóa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false, true
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
            tableExchange.getColumnModel().getColumn(0).setPreferredWidth(40);
            tableExchange.getColumnModel().getColumn(1).setResizable(false);
            tableExchange.getColumnModel().getColumn(1).setPreferredWidth(80);
            tableExchange.getColumnModel().getColumn(2).setResizable(false);
            tableExchange.getColumnModel().getColumn(2).setPreferredWidth(120);
            tableExchange.getColumnModel().getColumn(3).setResizable(false);
            tableExchange.getColumnModel().getColumn(3).setPreferredWidth(200);
            tableExchange.getColumnModel().getColumn(4).setResizable(false);
            tableExchange.getColumnModel().getColumn(5).setResizable(false);
            tableExchange.getColumnModel().getColumn(5).setPreferredWidth(60);
            tableExchange.getColumnModel().getColumn(6).setResizable(false);
            tableExchange.getColumnModel().getColumn(6).setPreferredWidth(100);
            tableExchange.getColumnModel().getColumn(7).setResizable(false);
            tableExchange.getColumnModel().getColumn(7).setPreferredWidth(100);
            tableExchange.getColumnModel().getColumn(8).setResizable(false);
            tableExchange.getColumnModel().getColumn(8).setPreferredWidth(60);
        }
        tableExchange.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        tableExchange.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 40));
        tableExchange.getTableHeader().setBackground(new Color(11,101,136));
        tableExchange.getTableHeader().setForeground(Color.WHITE);

        tableExchange.getColumnModel().getColumn(8).setCellRenderer(new nguyenvu.utils.TableDeleteCellRenderer());
        tableExchange.getColumnModel().getColumn(8).setCellEditor(new TableDeleteCellEditor(new TableDeleteEvent() {
            @Override
            public void onDelete(int row) {
                if (row >= 0 && row < tableExchange.getRowCount()) {
                    ((DefaultTableModel) tableExchange.getModel()).removeRow(row);
                    if (tableExchange.getRowCount() > 0) {
                        for (int i = 0; i < tableExchange.getRowCount(); ++i) {
                            tableExchange.setValueAt(i + 1, i, 0);
                        }
                    } else {
                        ((DefaultTableModel) tableExchange.getModel()).setRowCount(0);
                    }
                    ((DefaultTableModel) tableExchange.getModel()).fireTableDataChanged();
                    updateLblSoLuongSP();
                }
            }
        }));

        tableExchange.getColumnModel().getColumn(5).setCellEditor(new QuantityCellEditor(this, tableExchange));
        tableExchange.getColumnModel().getColumn(5).setCellRenderer(new QuantityCellRenderer());
        //table.setTableHeader(null);

        tableExchange.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());

        tableExchange.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableExchange.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tableExchange.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tableExchange.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tableExchange.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

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
        btnScanBarcode.setIcon(new FlatSVGIcon("gui/icon/scan.svg", 0.5f));
        btnScanBarcode.setHorizontalAlignment(SwingConstants.LEFT);

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
        int sumSLTra = 0;
        for(int i = 0; i < table.getRowCount(); i++) {
            sumSLTra += (int) table.getValueAt(i, 3);
        }
        
        if(sumSLTra < 1) {
            MessageAlerts.getInstance().showMessage("Lỗi", "Chưa thêm sản phẩm đổi trả!", MessageAlerts.MessageType.ERROR);
            return;
        }
        
        
        
        try {

            String employeeName = user != null ? user.getName() : "Nhân viên";
            String employeeId = user != null ? user.getUserName() : "";
             
            String ghiChu = txtNote.getText();
            String invoiceCode = doiTraDao.generateInvoiceCode();
            String date = getCurrentDate();
           
            List<FieldBillDoiTra> fields = new ArrayList<>();
        
            DefaultTableModel model1 = (DefaultTableModel) table.getModel();
            DefaultTableModel model2 = (DefaultTableModel) tableExchange.getModel();
            
            
            
            String ptThanhToan = (String) hd.getHinhThucThanhToan();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            LocalDateTime issueDate = LocalDateTime.parse(date, formatter);
            
            
            
            double tongTienHangTra = parseDoubleSafely(lblTongTienTra.getText());
            double tongPhiTraHang = parseDoubleSafely(lblPhiTraHang.getText());
            double tienHoan = parseDoubleSafely(lblTienHoan.getText());
            double tongTienDoi = parseDoubleSafely(lblKhachPhaiTra.getText());;
            double giamTru = parseDoubleSafely(lblDiemThuong.getText());
            double thanhToan = 0;
            
            if(isKhachPhaiTra) {    
                thanhToan = parseDoubleSafely(lblKhachPhaiTra.getText());
                tienHoan = parseDoubleSafely(lblTongTienHoan.getText());
            }
            
            HoaDonDoiTra_entity hddt = new HoaDonDoiTra_entity(invoiceCode, hd.getMaHD(), issueDate, tienHoan, thanhToan, ptThanhToan, ghiChu, nv.getMaNV());
            
            if(!doiTraDao.createHDDT(hddt)) {
                MessageAlerts.getInstance().showMessage("LỖI", "Không thể tạo hóa đơn!", MessageAlerts.MessageType.ERROR);
                refresh();
                return;
            } else {
                
                for (int i = 0; i < model1.getRowCount(); i++) {
                    int quantity = (int) model1.getValueAt(i, 3);           // soLuong
                    if(quantity > 0) {
                        String productName = (String) model1.getValueAt(i, 2); // tenSP
                        double unitPrice = doiTraDao.getSP((String) model1.getValueAt(i, 1)).getGia();    // donGia
                        double totalPrice = quantity * unitPrice;   // thanhTien
                        String tinhTrang = String.valueOf(table.getValueAt(i, 8));

                        fields.add(new FieldBillDoiTra(productName, quantity, unitPrice, totalPrice, tinhTrang, "Trả"));
                        
                        if(!doiTraDao.insertCTHDDT(new ChiTietHoaDonDoiTra_entity(date, date, SOMEBITS, tienHoan, thanhToan, tinhTrang)));
                    }
                }

                for(int i = 0; i < model2.getRowCount(); i++) {
                    int quantity = (int) model2.getValueAt(i, 5);           // soLuong
                    String productName = (String) model2.getValueAt(i, 3); // tenSP
                    double unitPrice = doiTraDao.getSP((String) model2.getValueAt(i, 2)).getGia();    // donGia
                    double totalPrice = quantity * unitPrice;   // thanhTien


                    fields.add(new FieldBillDoiTra(productName, quantity, unitPrice, totalPrice, "", "Đổi"));
                }
            
                for(int i = 0; i < table.getRowCount(); ++i) {
                   int quantity = (int) table.getValueAt(i, 3); 
                   
                   if(quantity > 0) {
                       
                   }
                }
                
                for (int i = 0; i < tableExchange.getRowCount(); ++i) {
                    String maSP = (String) tableExchange.getValueAt(i, 1);
                    int quantity = (int) tableExchange.getValueAt(i, 3); 
                    
                    double totalValue = 0;
 
                    if(!BanHang_DAO.createCTHD(new ChiTietHoaDon(invoiceCode, maSP,
                        (int) table.getValueAt(i, 3), totalValue))) {
                        System.out.println("Lỗi insert cthd");
                    }
                    
                }
                
                
                        
                ParameterBillDT billData = new ParameterBillDT(date, employeeName, kh.getTenKH(), 
                        kh.getSdtKH(), tongTienHangTra, tongPhiTraHang, tienHoan, 
                        tongTienDoi, giamTru,thanhToan,
                        ghiChu, invoiceCode, GenerateCode.generateQrcode(invoiceCode), 
                        fields); 
                BillDTManeger.getInstance().printBill(billData);
                
                
            }
            
            
            refresh();
        } catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDoiTraActionPerformed
   
    private void btnXoaTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTatCaActionPerformed
        // TODO add your handling code here:
        
        if(tableExchange.getRowCount() > 0) {
            DefaultTableModel model = (DefaultTableModel) tableExchange.getModel();
            model.setRowCount(0);
            updateLblSoLuongSP();
        }
        
    }//GEN-LAST:event_btnXoaTatCaActionPerformed

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
                menuProduct.show(txtProductSearch, txtProductSearch.getWidth(), txtProductSearch.getHeight());
                menuProduct.setPopupSize(menuProduct.getWidth(), listProductSearch.getListSize() * 82 + 3);
            } else {
                menuProduct.setVisible(false);
            }
        }
    }//GEN-LAST:event_txtProductSearchKeyReleased

    private void btnScanBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScanBarcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnScanBarcodeActionPerformed

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

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        // TODO add your handling code here:
        if(!txtTienKhachDua.getText().trim().isEmpty()) {
            updateTienThua();
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void btnSuggest2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuggest2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuggest2ActionPerformed

    private void btnSuggest3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuggest3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuggest3ActionPerformed
 
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
        
//        if(hd.getNgayLapHD().isBefore(LocalDateTime.now().minusDays(30)) ) {
//            MessageAlerts.getInstance().showMessage("Không thỏa điều kiện đổi trả", "Quá thời hạn đổi trả!", MessageAlerts.MessageType.ERROR);
//            refresh();
//            return;
//        }
        
        if(kh == null) {
            MessageAlerts.getInstance().showMessage("Không thỏa điều kiện đổi trả", "Không có thông tin số điện thoại khách hàng!", MessageAlerts.MessageType.ERROR);
            refresh();
            return;
        }
        
        lblTenKH.setText(kh.getTenKH());
        lblSDT.setText(kh.getSdtKH());
        
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
                    0,
                    cthd.getSoLuongSanPham(),
                    cthd.getThanhTien(),
                    0,
                    "",
                    "100%"
                };
                model.addRow(rowData);
            }
        }
    }
    
    private void refresh() {
        tongTienHoan = 0;
        tongTienMua = 0;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        DefaultTableModel model1 = (DefaultTableModel) tableExchange.getModel();
        model1.setRowCount(0);
        lblTenKH.setText("");
        lblSDT.setText("");
        nv = null;
        kh = null;
        hd = null;
        lblTienHoan.setText("");
        lblTongTienTra.setText("");
        lblPhiTraHang.setText("");
        lblTongTienHoan.setText("");
        pnMuaHang.setVisible(false);
        pnKhachTra.setVisible(false);
    }

    private double calculateTotalAmount() {
        double sum = 0;
        DefaultTableModel model = (DefaultTableModel) tableExchange.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            double gia = doiTraDao.getSP((String) model.getValueAt(i, 2)).getGia();
            int quantity = (int) model.getValueAt(i, 5); 
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
        bindKeyToFocus(txtProductSearch, KeyEvent.VK_F3);
        bindButtonKey(btnDoiTra, KeyEvent.VK_F1);
        bindButtonKey(btnXoaTatCa, KeyEvent.VK_F4);
        bindButtonKey(btnLamMoi, KeyEvent.VK_F5);
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
    
    public void addProductToTable(SanPham_entity sp) {
        for (int i = 0; i < tableExchange.getRowCount(); i++) {
            String existingMaSP = (String) tableExchange.getValueAt(i, 2);
            if (existingMaSP.equals(sp.getMaSP())) {
                int existingQuantity = (int) tableExchange.getValueAt(i, 5);
                
                if(existingQuantity == sp.getSoLuong()) {
                    MessageAlerts.getInstance().showMessage("Cảnh báo", "Vượt quá số lượng tồn kho!", MessageAlerts.MessageType.WARNING);
                    return;
                }
                
                if(existingQuantity == 50) {
                    MessageAlerts.getInstance().showMessage("Cảnh báo", "Số lượng tối đa cho phép là 50", MessageAlerts.MessageType.WARNING);
                    return;
                }
                
                table.setValueAt(existingQuantity + 1, i, 5);
                double price = (double) table.getValueAt(i, 6);
                table.setValueAt(price * (existingQuantity + 1), i, 7);
                menuProduct.setVisible(false);
                txtProductSearch.requestFocusInWindow();
                updateLblSoLuongSP();
                return;
            }
        }
        
        DefaultTableModel model = (DefaultTableModel) tableExchange.getModel();
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
        pnMuaHang.setVisible(true);
        updateLblSoLuongSP();
    }
    
    private void updateTienThua() {
        try {
            double khachPhaiTra = Double.parseDouble(lblKhachPhaiTra.getText().replace(",", ""));
            double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText().replace(",", ""));
            lblTienThua.setText(df.format(tienKhachDua - khachPhaiTra));
        } catch (NumberFormatException e) {
            lblTienThua.setText("");
        }
    }
    
    private void updateVAT() {
        if(tableExchange.getRowCount() > 0) {
            double sumVAT = 0;
            for (int i = 0; i < tableExchange.getRowCount(); ++i) {
                Integer quantity = (Integer) tableExchange.getValueAt(i, 5);
                Double price = (Double) tableExchange.getValueAt(i, 6);

                if (quantity != null && price != null) {
                    double taxPercentage  = banHangDao.getSP((String) tableExchange.getValueAt(i, 2)).getThue();
                    double taxAmount = taxPercentage / 100;

                    sumVAT += taxAmount * quantity * price;
                }
            }

            lblVAT.setText(df.format(sumVAT)); 
        }
        else lblVAT.setText("");
        
    }
    
    public void updateLblSoLuongSP() {
        int sumSoLuong = 0;
        double sumThanhTien = 0;
        
        if(tableExchange.getRowCount() > 0) {
            for (int i = 0; i < tableExchange.getRowCount(); i++) {
                sumSoLuong += Integer.parseInt(tableExchange.getValueAt(i, 5).toString());
                sumThanhTien += Double.parseDouble(tableExchange.getValueAt(i, 7).toString());
            }
            lblSoLuongSP.setText(String.valueOf(sumSoLuong));
            lblTongTien.setText(df.format(calculateTotalAmount()));

            if(kh != null) {
                int diemThuong = kh.getDiemThuong();
                int coefDT = diemThuong / 1000;
                giamTru = coefDT > 0 ? coefDT * 1000 : 0;
            }
            lblDiemThuong.setText(String.valueOf(df.format(giamTru)));
            //set label khach phai tra
            lblKhachPhaiTra.setText(String.valueOf(df.format(sumThanhTien - giamTru)));
            
            tongTienMua = sumThanhTien - giamTru;
            
            if(tongTienMua > tongTienHoan) {
                isKhachPhaiTra = true;
                pnKhachTra.setVisible(true);
                double tienPhaiTra = tongTienMua - tongTienHoan;
                lblKhachPhaiTra.setText(df.format(tienPhaiTra));
                updateSuggestButton();
                lbl7.setVisible(false);
                lblTienHoan.setVisible(false);
                updateTienThua();
            }
            else {
                isKhachPhaiTra = false;
                pnKhachTra.setVisible(false);
                lbl7.setVisible(true);
                lblTienHoan.setVisible(true);
                defaultButton();
                lblTienHoan.setText(df.format(tongTienHoan - tongTienMua));
            }
        } else {
            lblSoLuongSP.setText("");
            lblTongTien.setText("");
            lblDiemThuong.setText("");
            lblKhachPhaiTra.setText("");
            lblVAT.setText("");
            pnMuaHang.setVisible(false);
            pnKhachTra.setVisible(false);
            pnMuaHang.repaint();
        }
        
        
        
        pnKhachTra.setVisible(isKhachPhaiTra);
        pnMuaHang.repaint();
        updateVAT();
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

    private void updateSuggestButton() {
        if(lblKhachPhaiTra.getText().isEmpty()) return;
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


    
    public void updatePnTraHang() {
        double totalAmount = 0.0; 
        double totalReturnFee = 0.0;
        
        for (int i = 0; i < table.getRowCount(); i++) {
            int quantity = (int) table.getValueAt(i, 3);

            double price = (double) table.getValueAt(i, 5);

            double rowTotal = quantity * price;
            totalAmount += rowTotal;

            String selectedStatus = (String) table.getValueAt(i, 8);
            double statusPercentage = 1.0;
            if (selectedStatus != null) {
                statusPercentage = getPercentageFromStatus(selectedStatus);
            }
//            System.out.println("statusPercentage " + statusPercentage);

            double returnFee = rowTotal * (1.0 - statusPercentage);
            totalReturnFee += returnFee;

            table.setValueAt(rowTotal, i, 6);
        }
        lblTongTienGoc.setText(df.format(totalAmount));
        lblTongTienTra.setText(df.format(totalAmount));
        lblPhiTraHang.setText(df.format(totalReturnFee));
        lblTongTienHoan.setText(df.format(totalAmount - totalReturnFee));
        
        tongTienHoan = totalAmount - totalReturnFee;
    }
    
    private double getPercentageFromStatus(String status) {
        if (status == null) {
            return 1.0;
        }
        switch (status) {
            case "100%": return 1.0;
            case "95%": return 0.95;
            case "90%": return 0.90;
            case "85%": return 0.85;
            case "80%": return 0.80;
            case "75%": return 0.75;
            case "70%": return 0.70;
            default: return 1.0;
        }
    }
    
    private double parseDoubleSafely(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        try {
            return Double.parseDouble(text.replace(",", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoiTra;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnScanBarcode;
    private javax.swing.JButton btnScanQrcode;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSuggest1;
    private javax.swing.JButton btnSuggest2;
    private javax.swing.JButton btnSuggest3;
    private javax.swing.JButton btnSuggest4;
    private javax.swing.JButton btnSuggest5;
    private javax.swing.JButton btnSuggest6;
    private javax.swing.JButton btnXoaTatCa;
    private javax.swing.JComboBox<String> cbbPhuongThucThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lblDiemThuong;
    private javax.swing.JLabel lblKhachDua;
    private javax.swing.JLabel lblKhachPhaiTra;
    private javax.swing.JLabel lblPhiTraHang;
    private javax.swing.JLabel lblPhuongThucThanhToan;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSoLuongSP;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTienHoan;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTongTienGoc;
    private javax.swing.JLabel lblTongTienHoan;
    private javax.swing.JLabel lblTongTienTra;
    private javax.swing.JLabel lblVAT;
    private javax.swing.JPanel pnContent;
    private javax.swing.JPanel pnCustomer;
    private javax.swing.JPanel pnKhachTra;
    private javax.swing.JPanel pnLeftContent;
    private javax.swing.JPanel pnMuaHang;
    private javax.swing.JPanel pnProductSearch;
    private javax.swing.JPanel pnRightContent;
    private javax.swing.JPanel pnSearch;
    private javax.swing.JTable table;
    private javax.swing.JTable tableExchange;
    private javax.swing.JTextField txtHoaDonSearch;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtProductSearch;
    private javax.swing.JTextField txtTienKhachDua;
    // End of variables declaration//GEN-END:variables
}
