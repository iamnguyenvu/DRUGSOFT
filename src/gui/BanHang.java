/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Customizer.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import dao.BanHang_DAO;
import entity.SanPham_entity;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import nguyenvu.components.SimpleForm;
import nguyenvu.menu.FormManager;
import nguyenvu.utils.LayerSearchList;
import nguyenvu.utils.ListProductSearchPanel;
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

/**
 *
 * @author HP
 */
public class BanHang extends SimpleForm {

    private TableDeleteEvent ev;
    private JPopupMenu menuProduct;
    private ListProductSearchPanel listProductSearch;
    private BanHang_DAO dao;

    public BanHang() {
        setPreferredSize(new Dimension(1020, 740));
        initComponents();
        dao = new BanHang_DAO();
        listProductSearch = new ListProductSearchPanel();
        menuProduct = new JPopupMenu();
        menuProduct.add(listProductSearch);
        menuProduct.setFocusable(false);
        addKeyBindings();
        
        listProductSearch.addProductSelectListener(new ProductSelectListener() {
            @Override
            public void onProductSelected(SanPham_entity sp) {
                for (int i = 0; i < table.getRowCount(); i++) {
                    String existingMaSP = (String) table.getValueAt(i, 2);
                    if (existingMaSP.equals(sp.getMaSP())) {
                        // Nếu sản phẩm đã tồn tại, tăng số lượng
                        int existingQuantity = (int) table.getValueAt(i, 5);
                        table.setValueAt(existingQuantity + 1, i, 5); // Cập nhật số lượng
                        // Cập nhật thành tiền
                        double price = (double) table.getValueAt(i, 6);
                        table.setValueAt(price * (existingQuantity + 1), i, 7);
                        return;
                    }
                }
                addProductToTable(sp);
            }
        });

        txtProductSearch.requestFocusInWindow();
    }

    private Icon createIcon(String path, float scale) {
        FlatSVGIcon icon = new FlatSVGIcon(path, scale);
        FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
        colorFilter.add(Color.decode("#969696"), Color.decode("#FAFAFA"), Color.decode("#969696"));
        icon.setColorFilter(colorFilter);
        return icon;
    }

    private void addKeyBindings() {
        // Phím tắt cho txtProductSearch
        bindKeyToFocus(txtProductSearch, KeyEvent.VK_F2);

        // Phím tắt cho txtCustomer
        bindKeyToFocus(txtCustomer, KeyEvent.VK_F3);

        // Phím tắt cho cbbPhuongThucThanhToan
        bindKeyToFocus(cbbPhuongThucThanhToan, KeyEvent.VK_F6);

        // Phím tắt cho txtTienKhachDua
        bindKeyToFocus(txtTienKhachDua, KeyEvent.VK_F5);

        // Thêm phím tắt cho các nút
        addButtonKeyBindings();
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

    private void addButtonKeyBindings() {
        // Ví dụ phím tắt cho nút btnAddCustomer
        InputMap inputMapAddCustomer = btnAddCustomer.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMapAddCustomer = btnAddCustomer.getActionMap();
        
        inputMapAddCustomer.put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), "addCustomer");
        actionMapAddCustomer.put("addCustomer", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddCustomer.doClick();
            }
        });

        // Thêm tương tự cho các nút khác như btnFilter, btnLuuTam, btnThanhToan...
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
        btnThanhToan = new javax.swing.JButton();
        btnLuuTam = new javax.swing.JButton();
        btnSuggest1 = new javax.swing.JButton();
        lblTienThua = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnSuggest2 = new javax.swing.JButton();
        btnSuggest3 = new javax.swing.JButton();
        lblPhuongThucThanhToan = new javax.swing.JLabel();
        cbbPhuongThucThanhToan = new javax.swing.JComboBox<>();
        btnSuggest4 = new javax.swing.JButton();
        btnSuggest5 = new javax.swing.JButton();
        btnSuggest6 = new javax.swing.JButton();
        txtTienKhachDua = new javax.swing.JTextField();
        pnLeftContent = new javax.swing.JPanel();
        pnFunc = new javax.swing.JPanel();
        btnDeleteAllSP = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnNote = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnTempProcess = new javax.swing.JButton();
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

        btnFilter.setBackground(new java.awt.Color(11, 101, 136));
        btnFilter.setBorderPainted(false);
        btnFilter.setFocusPainted(false);
        btnFilter.setPreferredSize(new java.awt.Dimension(75, 40));

        txtProductSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txtProductSearch.setPreferredSize(new java.awt.Dimension(85, 40));
        txtProductSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "[F2] Thêm sản phẩm vào đơn");
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
        layer.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5,$Component.borderColor,,20");

        pnSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");

        add(pnSearch, java.awt.BorderLayout.PAGE_START);

        pnContent.setPreferredSize(new java.awt.Dimension(1100, 800));

        pnRightContent.setPreferredSize(new java.awt.Dimension(400, 700));

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

        lblSuDungDiemThuong.setText("[F4] Sử dụng điểm thưởng");
        lblSuDungDiemThuong.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel8.setPreferredSize(new java.awt.Dimension(0, 30));

        lblPhaiTra.setText("Khách phải trả");
        lblPhaiTra.setPreferredSize(new java.awt.Dimension(0, 30));

        jLabel11.setPreferredSize(new java.awt.Dimension(0, 30));

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

        btnSuggest1.setText("[1]");

        lblTienThua.setText("Tiền thừa");

        btnSuggest2.setText("[2]");
        btnSuggest2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuggest2ActionPerformed(evt);
            }
        });

        btnSuggest3.setText("[3]");
        btnSuggest3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuggest3ActionPerformed(evt);
            }
        });

        lblPhuongThucThanhToan.setText("[F6] Phương thức thanh toán");

        cbbPhuongThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Thẻ" }));
        cbbPhuongThucThanhToan.setAlignmentX(RIGHT_ALIGNMENT);

        btnSuggest4.setText("[4]");

        btnSuggest5.setText("[5]");

        btnSuggest6.setText("[6]");
        btnSuggest6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuggest6ActionPerformed(evt);
            }
        });

        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLuuTam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
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
                                    .addComponent(lblPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(lblSuDungDiemThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(pnInputCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(3, 3, 3))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(lblPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cbbPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(lblKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(8, Short.MAX_VALUE))
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
                    .addComponent(lblSuDungDiemThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuggest2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuggest1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuggest3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuggest5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuggest4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuggest6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuTam, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblPhaiTra.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        jLabel11.putClientProperty(FlatClientProperties.STYLE, ""
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
        jLabel1.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");
        txtTienKhachDua.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +3");

        javax.swing.GroupLayout pnRightContentLayout = new javax.swing.GroupLayout(pnRightContent);
        pnRightContent.setLayout(pnRightContentLayout);
        pnRightContentLayout.setHorizontalGroup(
            pnRightContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnRightContentLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnRightContentLayout.setVerticalGroup(
            pnRightContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnRightContentLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5,$Component.borderColor,,20");

        pnLeftContent.setPreferredSize(new java.awt.Dimension(1085, 700));

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

        jButton3.setBackground(new java.awt.Color(183, 218, 246));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("jButton1");
        jButton3.setPreferredSize(new java.awt.Dimension(80, 40));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnNote.setBackground(new java.awt.Color(183, 218, 246));
        btnNote.setForeground(new java.awt.Color(255, 255, 255));
        btnNote.setText("[F9] Ghi chú đơn");
        btnNote.setPreferredSize(new java.awt.Dimension(80, 40));

        jButton5.setBackground(new java.awt.Color(183, 218, 246));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("jButton1");
        jButton5.setPreferredSize(new java.awt.Dimension(80, 40));

        btnTempProcess.setBackground(new java.awt.Color(183, 218, 246));
        btnTempProcess.setForeground(new java.awt.Color(255, 255, 255));
        btnTempProcess.setText("[F10] Xử lý đơn tạm");
        btnTempProcess.setPreferredSize(new java.awt.Dimension(80, 40));

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
                    .addComponent(btnDeleteAllSP, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(pnFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNote, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(pnFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTempProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(btnDeleteAllSP, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnFuncLayout.createSequentialGroup()
                        .addComponent(btnNote, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnFuncLayout.createSequentialGroup()
                        .addComponent(btnTempProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnFuncLayout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        addStyleBtn(btnDeleteAllSP);
        addStyleBtn(btnNote);
        addStyleBtn(btnTempProcess);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 500));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "hinhAnh", "maSP", "tenSP", "donVi", "soLuong", "donGia", "thanhTien", "btnDelete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
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
        table.setRowSelectionAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
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
            table.getColumnModel().getColumn(5).setPreferredWidth(100);
            table.getColumnModel().getColumn(6).setResizable(false);
            table.getColumnModel().getColumn(6).setPreferredWidth(100);
            table.getColumnModel().getColumn(7).setResizable(false);
            table.getColumnModel().getColumn(7).setPreferredWidth(100);
            table.getColumnModel().getColumn(8).setResizable(false);
            table.getColumnModel().getColumn(8).setPreferredWidth(30);
        }
        table.getColumnModel().getColumn(8).setCellRenderer(new nguyenvu.utils.TableDeleteCellRenderer());
        table.getColumnModel().getColumn(8).setCellEditor(new TableDeleteCellEditor(new TableDeleteEvent() {
            @Override
            public void onDelete(int row) {
                if (row >= 0 && row < table.getRowCount()) {
                    ((DefaultTableModel) table.getModel()).removeRow(row);
                    ((DefaultTableModel) table.getModel()).fireTableDataChanged();
                }
            }
        }));

        //table.getColumnModel().getColumn(5).setCellRenderer(new QuantityCellRenderer());
        //table.getColumnModel().getColumn(5).setCellEditor(new QuantityCellEditor(new QuantityCellEvent() {
            //    @Override
            //    public void onIncrease(int row) {
                //        if (row >= 0) {
                    //            int currentQuantity = (int) table.getValueAt(row, 5);
                    //            table.setValueAt(currentQuantity + 1, row, 5);
                    //        }
                //    }
            //
            //    public void onDecrease(int row) {
                //        if (row >= 0) {
                    //            int currentQuantity = (int) table.getValueAt(row, 5);
                    //            if (currentQuantity > 0) {
                        //                table.setValueAt(currentQuantity - 1, row, 5);
                        //            }
                    //        }
                //    }
            //}));

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

    private void txtProductSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductSearchActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSuggest3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuggest3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuggest3ActionPerformed

    private void btnSuggest6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuggest6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuggest6ActionPerformed

    private void btnSuggest2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuggest2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuggest2ActionPerformed

    private void btnAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddCustomerActionPerformed

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    
    private void btnDeleteAllSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAllSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteAllSPActionPerformed

    private void txtProductSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductSearchKeyReleased
        // TODO add your handling code here:
        String productKey = txtProductSearch.getText().trim().toLowerCase();
        if(productKey.length() > 0) {
            ArrayList<SanPham_entity> listSP = dao.searchSanPham(productKey);
            System.out.println("Searching for: " + productKey);
            System.out.println("Found products: " + listSP.size());
            listProductSearch.setData(listSP);

            if(listProductSearch.getListSize() > 0) {
                menuProduct.show(txtProductSearch, 0, txtProductSearch.getHeight());
                menuProduct.setPopupSize(menuProduct.getWidth(), listProductSearch.getListSize() * 80);
            } else {
                menuProduct.setVisible(false);
            }
        }
    }//GEN-LAST:event_txtProductSearchKeyReleased
 
    private void addProductToTable(SanPham_entity sp) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] rowData = new Object[] {
            model.getRowCount() + 1, // STT
            sp.getHinhAnhSP(), // hinhAnh
            sp.getMaSP(), // maSP
            sp.getTenSP(), // tenSP
            sp.getDonViTinh(), // donVi
            1, // soLuong (mặc định là 1)
            sp.getGia(), // donGia
            sp.getGia(), // thanhTien (1 * donGia)
            "Delete" // btnDelete
        };
        model.addRow(rowData);
    }

    
    private void addStyleBtn(JButton btn) {
        btn.putClientProperty(FlatClientProperties.STYLE, ""
            + "font: bold +1");
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
    private javax.swing.JButton btnTempProcess;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JComboBox<String> cbbPhuongThucThanhToan;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTextField txtProductSearch;
    private javax.swing.JTextField txtTienKhachDua;
    // End of variables declaration//GEN-END:variables
}
