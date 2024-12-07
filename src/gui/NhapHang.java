
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import dao.ChiTietNhapHang_DAO;
import dao.NhapHang_DAO;
import entity.ChiTietNhapHang_entity;
import entity.NhapHang_entity;
import entity.SanPham_entity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import nguyenvu.components.SimpleForm;
import nguyenvu.utils.ButtonEditor;
import nguyenvu.utils.ButtonRenderer;
import nguyenvu.utils.EditViewButtonEditor;
import nguyenvu.utils.EditViewButtonRenderer;
import nguyenvu.utils.HeaderRenderer;
import nguyenvu.utils.ListProductSearchPanel;
import nguyenvu.utils.ProductSelectListener;
import nguyenvu.utils.QuantityCellEditor;
import nguyenvu.utils.QuantityCellRenderer;
import nguyenvu.utils.RoundedTextField;
import raven.alerts.MessageAlerts;
import nguyenvu.utils.EditViewButtonEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class NhapHang extends SimpleForm {
	
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnHeader;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    private JPanel panel;
    private JButton btnThemSP;
    private JLabel lbl2;
    private JLabel lbl3;
    private JLabel lbl5;
    private JLabel lblPhuongThucThanhToan;
    private JPopupMenu popupMenu;
    private NhapHang_DAO nhapHang_DAO;
    private ListProductSearchPanel listProductSearch;
	private DefaultTableModel dftable;
	private JLabel lblTinTha;
	private JLabel lblTongTien;
	private JLabel lblVAT;
	private JLabel lblTienCanTra;
	private JTextField tfTienTra;
	private JTextField tfGhiChu;
	private JComboBox<String> cbbPhuongThucThanhToan;
	private JLabel  lblTienThua;
	private ChiTietNhapHang_DAO chiTietNhapHang_DAO;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JButton btnLuuTam;
   
    public NhapHang() {
    	nhapHang_DAO = new NhapHang_DAO();
    	chiTietNhapHang_DAO = new ChiTietNhapHang_DAO();
        initComponents();
    }
    private void initComponents() {
    	
    	listProductSearch = new ListProductSearchPanel();
        pnHeader = new javax.swing.JPanel();
        txtSearch = new RoundedTextField(40);
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        listProductSearch.addProductSelectListener(new ProductSelectListener() {
            @Override
            public void onProductSelected(SanPham_entity sp) {
                if(sp.getSoLuong() < 1) {
                    MessageAlerts.getInstance().showMessage("Cảnh báo", "Sản phẩm không đủ số lượng để thêm vào giỏ hàng!", MessageAlerts.MessageType.WARNING);
                    return;
                }
                addProductToTable(sp);
                popupMenu.setVisible(false);
                txtSearch.requestFocusInWindow();
            }
        });

        setPreferredSize(new java.awt.Dimension(1470, 730));
        pnHeader.setBackground(new java.awt.Color(11, 101, 136));
        pnHeader.setPreferredSize(new java.awt.Dimension(1470, 50));
        jButton1.setBackground(new java.awt.Color(11, 101, 136));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tìm kiếm");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton2.setBackground(new java.awt.Color(11, 101, 136));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Scan barcode");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnThemSP = new JButton("Thêm Sản Phẩm");

        javax.swing.GroupLayout pnHeaderLayout = new javax.swing.GroupLayout(pnHeader);
        pnHeaderLayout.setHorizontalGroup(
        	pnHeaderLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnHeaderLayout.createSequentialGroup()
        			.addGap(20)
        			.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
        			.addGap(683)
        			.addComponent(btnThemSP)
        			.addContainerGap(30, Short.MAX_VALUE))
        );
        pnHeaderLayout.setVerticalGroup(
        	pnHeaderLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnHeaderLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(pnHeaderLayout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(pnHeaderLayout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jButton2, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        					.addComponent(btnThemSP, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
        				.addComponent(jButton1, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        				.addComponent(txtSearch, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
        			.addContainerGap())
        );
        pnHeader.setLayout(pnHeaderLayout);
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("gui/icon/search.svg"));
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "showClearButton: true");
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "[F2] Tìm sản phẩm");
        popupMenu = new JPopupMenu();
        popupMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        popupMenu.add(listProductSearch);
        popupMenu.setFocusable(false);




        dftable = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) {
                    return ImageIcon.class;
                }
                return String.class;
            }
        };
        dftable.setColumnIdentifiers(new String[]{
            "STT", "Hình ảnh", "Mã sản phẩm", "Tên sản phẩm", "Đơn Vị Tính", "Số lượng","Thuế", "Đơn giá", "Thành tiền","Xóa"
        });
        table.setModel(dftable);
        table.setRowHeight(50);
        table.setRowSelectionAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(3).setPreferredWidth(200);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(4).setPreferredWidth(50);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(5).setPreferredWidth(100);
            table.getColumnModel().getColumn(6).setResizable(false);
            table.getColumnModel().getColumn(6).setPreferredWidth(60);
            table.getColumnModel().getColumn(7).setResizable(false);
            table.getColumnModel().getColumn(7).setPreferredWidth(100);
            table.getColumnModel().getColumn(8).setResizable(false);
            table.getColumnModel().getColumn(8).setPreferredWidth(100);
            table.getColumnModel().getColumn(9).setResizable(false);
            table.getColumnModel().getColumn(9).setPreferredWidth(80);
        }
        table.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        table.getTableHeader().setPreferredSize(new Dimension(table.getWidth(), 40));
        table.getTableHeader().setBackground(new Color(11,101,136));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getColumnModel().getColumn(5).setCellEditor(new QuantityCellEditor(this));
        table.getColumnModel().getColumn(5).setCellRenderer(new QuantityCellRenderer());
        table.getColumn("Xóa").setCellRenderer(new ButtonRenderer("Xóa"));
        table.getColumn("Xóa").setCellEditor(new ButtonEditor("Xóa", e -> {
        	removeSelectedRow();
		}));
        txtSearch.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
          String keyword = txtSearch.getText().trim().toLowerCase();
          if (keyword.length() > 0) {
              ArrayList<SanPham_entity> listSP = nhapHang_DAO.searchSanPham(keyword);
              listProductSearch.setData(listSP);

              if (listSP.isEmpty()) {
                  popupMenu.setVisible(false);
              } else {
                  popupMenu.show(txtSearch, 0, txtSearch.getHeight());
                  popupMenu.setPopupSize(popupMenu.getWidth(), listProductSearch.getListSize() * 82 + 3);
              }
          } else {
              popupMenu.setVisible(false);
          		}
      		}
        });
        dftable.addTableModelListener(e -> {
            int column = e.getColumn();
            if (column == 5) { // Cột số lượng
                int row = e.getFirstRow();
                int quantity = (int) dftable.getValueAt(row, 5); // Số lượng mới
                double price = (double) dftable.getValueAt(row, 7); // Đơn giá
                dftable.setValueAt(price * quantity, row, 8); // Cập nhật thành tiền
                updateTongTien(); // Cập nhật tổng tiền
            }
        });

        
        panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng Tin Nh\u1EADp H\u00E0ng", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 1055, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
        			.addGap(18))
        		.addComponent(pnHeader, GroupLayout.DEFAULT_SIZE, 1494, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(pnHeader, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 643, GroupLayout.PREFERRED_SIZE)
        				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(21, Short.MAX_VALUE))
        );
        panel.setLayout(null);


        
        JButton btnThanhToan = new JButton();
        btnThanhToan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	insertNhapHang();
            }
        });

        btnThanhToan.setText("[F1] Thanh toán");
        btnThanhToan.setForeground(Color.WHITE);
        btnThanhToan.setBackground(new Color(1, 201, 16));
        btnThanhToan.setBounds(213, 440, 178, 87);
        panel.add(btnThanhToan);
        
        btnLuuTam = new JButton();
        btnLuuTam.setText("[F7] Lưu tạm");
        btnLuuTam.setForeground(Color.WHITE);
        btnLuuTam.setBackground(new Color(183, 218, 246));
        btnLuuTam.setBounds(10, 440, 162, 87);
        panel.add(btnLuuTam);
        btnLuuTam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	insertNhapHangLuuTam();
            }
        });
        
        panel_1 = new JPanel();
        panel_1.setBounds(10, 29, 381, 47);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        lbl2 = new JLabel();
        lbl2.setBounds(0, 0, 174, 47);
        panel_1.add(lbl2);
        lbl2.setText("Tổng Tiền Sản Phẩm");
        lbl2.setPreferredSize(new Dimension(0, 30));
        
        lblTongTien = new JLabel("0");
        lblTongTien.setBounds(203, 0, 178, 47);
        panel_1.add(lblTongTien);
        
        panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBounds(10, 86, 381, 47);
        panel.add(panel_2);
        
        lbl3 = new JLabel();
        lbl3.setBounds(0, 0, 174, 40);
        panel_2.add(lbl3);
        lbl3.setText("VAT");
        lbl3.setPreferredSize(new Dimension(0, 30));
        
        lblVAT = new JLabel("0");
        lblVAT.setBounds(203, 0, 178, 47);
        panel_2.add(lblVAT);
        
        panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBounds(10, 143, 381, 47);
        panel.add(panel_3);
        
        lbl5 = new JLabel();
        lbl5.setBounds(0, 3, 172, 40);
        panel_3.add(lbl5);
        lbl5.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        lbl5.setText("Cần Trả Nhà Cung Cấp");
        lbl5.setPreferredSize(new Dimension(0, 30));
        
        lblTienCanTra = new JLabel("0");
        lblTienCanTra.setFont(new java.awt.Font("Segoe UI", 1, 12));
        
        lblTienCanTra.setBounds(203, 3, 178, 40);
        panel_3.add(lblTienCanTra);
        
        panel_4 = new JPanel();
        panel_4.setLayout(null);
        panel_4.setBounds(10, 200, 381, 47);
        panel.add(panel_4);
        
        JLabel lblTinTrNh = new JLabel();
        lblTinTrNh.setBounds(0, 0, 174, 47);
        panel_4.add(lblTinTrNh);
        lblTinTrNh.setText("Tiền Trả Nhà Cung Cấp\r\n");
        lblTinTrNh.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        lblTinTrNh.setPreferredSize(new Dimension(0, 30));
        
        tfTienTra = new JTextField();
        tfTienTra.setBounds(203, 0, 178, 37);
        panel_4.add(tfTienTra);
        tfTienTra.setColumns(10);
        tfTienTra.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        tfTienTra.setBackground(new Color(0, 0, 0, 0));
        tfTienTra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(153, 153, 153)));
        
        panel_5 = new JPanel();
        panel_5.setLayout(null);
        panel_5.setBounds(10, 257, 381, 47);
        panel.add(panel_5);
        
        lblPhuongThucThanhToan = new JLabel();
        lblPhuongThucThanhToan.setBounds(0, 0, 174, 47);
        panel_5.add(lblPhuongThucThanhToan);
        lblPhuongThucThanhToan.setText("Phương thức thanh toán");
        
        cbbPhuongThucThanhToan = new JComboBox<String>();
        cbbPhuongThucThanhToan.setBounds(203, 0, 178, 47);
        panel_5.add(cbbPhuongThucThanhToan);
        
        panel_6 = new JPanel();
        panel_6.setLayout(null);
        panel_6.setBounds(10, 314, 381, 47);
        panel.add(panel_6);
        
        lblTinTha = new JLabel();
        lblTinTha.setBounds(0, 0, 174, 47);
        lblTinTha.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        panel_6.add(lblTinTha);
        
        lblTinTha.setText("Tiền Thừa");
        lblTinTha.setPreferredSize(new Dimension(0, 30));
        
        lblTienThua = new JLabel("0");
        lblTienThua.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblTienThua.setBounds(203, 0, 178, 47);
        panel_6.add(lblTienThua);
        
        panel_7 = new JPanel();
        panel_7.setLayout(null);
        panel_7.setBounds(10, 371, 381, 47);
        panel.add(panel_7);
        JLabel lbl7 = new JLabel();
        lbl7.setBounds(0, 0, 162, 47);
        panel_7.add(lbl7);
        lbl7.setText("Ghi Chú");
        tfGhiChu = new JTextField();
        tfGhiChu.setBounds(203, 0, 178, 47);
        panel_7.add(tfGhiChu);
        tfGhiChu.setColumns(10);
        cbbPhuongThucThanhToan.addItem("Tiền Mặt");
        cbbPhuongThucThanhToan.addItem("Chuyển Khoản");
        cbbPhuongThucThanhToan.addItem("Thẻ Tín Dụng");
        tfTienTra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    // Lấy giá trị số tiền trả từ tfTienTra
                    double tienTra = Double.parseDouble(tfTienTra.getText().trim());
                    
                    // Lấy giá trị số tiền cần trả từ lblTienCanTra
                    double tienCanTra = Double.parseDouble(lblTienCanTra.getText().trim());
                    
                    // Tính tiền thừa
                    double tienThua = tienTra - tienCanTra;
                    
                    // Gán giá trị tiền thừa vào lblTienThua
                    lblTienThua.setText(String.format("%.2f", tienThua));
                } catch (NumberFormatException ex) {
                    // Xử lý khi nhập giá trị không hợp lệ
                    lblTienThua.setText("0.00");
                }
            }
        });
        this.setLayout(layout);

        pnHeader.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:0,0,0,0,$Component.borderColor,,20");
    }


//public void addProductToTable(SanPham_entity sp) {
//    for (int i = 0; i < table.getRowCount(); i++) {
//        String existingMaSP = (String) table.getValueAt(i, 1);
//        if (existingMaSP.equals(sp.getMaSP())) {
//            int existingQuantity = (int) table.getValueAt(i, 3);
//            
//            if(existingQuantity == sp.getSoLuong()) {
//                MessageAlerts.getInstance().showMessage("Cảnh báo", "Vượt quá số lượng tồn kho!", MessageAlerts.MessageType.WARNING);
//                return;
//            }
//            
//            if(existingQuantity == 50) {
//                MessageAlerts.getInstance().showMessage("Cảnh báo", "Số lượng tối đa cho phép là 50", MessageAlerts.MessageType.WARNING);
//                return;
//            }
//            
//            table.setValueAt(existingQuantity + 1, i, 5);
//            double price = (double) table.getValueAt(i, 6);
//            table.setValueAt(price * (existingQuantity + 1), i, 7);
//            popupMenu.setVisible(false);
//            txtSearch.requestFocusInWindow();
////            updateLblSoLuongSP();
//            return;
//        }
//    }
//    
////	    DefaultTableModel model = (DefaultTableModel) table.getModel();
//	    ImageIcon iiSP = new ImageIcon(getClass().getResource(sp.getHinhAnhSP()));
//	Image imgSP = iiSP.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
//	    Object[] rowData = new Object[] {
//	        dftable.getRowCount() + 1, // STT
//	        new ImageIcon(imgSP), // hinhAnh
//	        sp.getMaSP(), // maSP
//	        sp.getTenSP(), // tenSP
//	        sp.getDonViTinh(), // donVi
//	        1, // soLuong (mặc định là 1)
//	        sp.getGia(), // donGia
//	        sp.getGia()
//	    };
//	    dftable.addRow(rowData);
////    updateLblSoLuongSP();
//}
    public void addProductToTable(SanPham_entity sp) {
        for (int i = 0; i < table.getRowCount(); i++) {
            String existingMaSP = (String) table.getValueAt(i, 2); // Cột Mã sản phẩm
            if (existingMaSP.equals(sp.getMaSP())) {
                int existingQuantity = (int) table.getValueAt(i, 5); // Cột Số lượng
                if (existingQuantity == sp.getSoLuong()) {
                    MessageAlerts.getInstance().showMessage("Cảnh báo", "Vượt quá số lượng tồn kho!", MessageAlerts.MessageType.WARNING);
                    return;
                }
                if (existingQuantity == 50) {
                    MessageAlerts.getInstance().showMessage("Cảnh báo", "Số lượng tối đa cho phép là 50", MessageAlerts.MessageType.WARNING);
                    return;
                }
                table.setValueAt(existingQuantity + 1, i, 5); // Cập nhật số lượng
                double price = (double) table.getValueAt(i, 6); // Đơn giá
                table.setValueAt(price * (existingQuantity + 1), i, 7); // Thành tiền
                popupMenu.setVisible(false);
                txtSearch.requestFocusInWindow();
                return;
            }
        }

        // Nếu không có sản phẩm nào khớp, thêm sản phẩm mới
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ImageIcon iiSP = new ImageIcon(getClass().getResource(sp.getHinhAnhSP()));
        Image imgSP = iiSP.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        Object[] rowData = new Object[]{
            model.getRowCount() + 1, // STT
            new ImageIcon(imgSP), // Hình ảnh
            sp.getMaSP(), // Mã sản phẩm
            sp.getTenSP(), // Tên sản phẩm
            sp.getDonViTinh(), // Đơn vị
            1, // Số lượng (mặc định là 1)
            sp.getThue(),
            sp.getGiaNhap(), // Đơn giá
            sp.getGiaNhap() // Thành tiền
        };
        model.addRow(rowData);
        updateTongTien();
    }
    private void updateTongTien() {
        double tongTien = 0;
        double tienThue = 0;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            double thanhTien = (double) model.getValueAt(i, 8);
            tongTien += thanhTien;
            double VAT = thanhTien * ((double) model.getValueAt(i, 6) / 100);
            tienThue += VAT;
        }
        lblTongTien.setText(String.format("%.2f", tongTien));
        lblVAT.setText(String.format("%.2f", tienThue));
        lblTienCanTra.setText(String.format("%.2f", tienThue + tongTien));
        String tienTraText = tfTienTra.getText();
        double tienTra = 0;
        if (tienTraText != null && !tienTraText.trim().isEmpty()) {
            try {
                tienTra = Double.parseDouble(tienTraText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Giá trị tiền trả không hợp lệ. Vui lòng nhập số hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; 
            }
        }

        // Tính tiền thừa
        double tinhLaiTienThua = tienTra - (tienThue + tongTien);
        lblTienThua.setText(String.format("%.2f", tinhLaiTienThua));
    }

    private void removeSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(selectedRow);
            updateTongTien(); // Cập nhật tổng tiền sau khi xóa
        }
    }


	public static String generateMovieCode(String key) {
	    Calendar now = Calendar.getInstance();
	    int currentYear = now.get(Calendar.YEAR) % 100; // Lấy 2 chữ số cuối của năm
	    int currentMonth = now.get(Calendar.MONTH) + 1;
	    int currentDay = now.get(Calendar.DAY_OF_MONTH);
	    
	    Random random = new Random();
	    int randomDigits = 10000 + random.nextInt(90000); // Tạo 5 chữ số ngẫu nhiên

	    // Tạo mã sản phẩm với định dạng yêu cầu
	    String ma = key.toUpperCase() + String.format("%02d%02d%02d", currentYear, currentMonth, currentDay) + randomDigits;
	    return ma;
	}
	public void insertNhapHang() {
        // Kiểm tra danh sách sản phẩm
        if (table.getRowCount() == 0) { // Kiểm tra nếu bảng sản phẩm trống
            MessageAlerts.getInstance().showMessage(
                "Cảnh báo",
                "Vui lòng thêm ít nhất một sản phẩm trước khi thanh toán!",
                MessageAlerts.MessageType.WARNING
            );
            return; // Ngừng xử lý tiếp
        }

        // Lấy số tiền người dùng nhập
        double tientra = Double.parseDouble(tfTienTra.getText());
        double tienCanTra = Double.parseDouble(lblTienCanTra.getText());

        // Kiểm tra số tiền trả và số tiền cần trả
        if (tienCanTra > tientra || tientra == 0) {
            MessageAlerts.getInstance().showMessage(
                "Cảnh báo",
                "Tiền Trả phải lớn hơn Tiền Cần Trả!",
                MessageAlerts.MessageType.WARNING
            );
        } else {
        	String maNhapHang = generateMovieCode("NH");
        	LocalDate ngayNhapHang = LocalDate.now();
        	double tongTien = tienCanTra;
        	String ghiChu = tfGhiChu.getText();
        	String hhht = (String) cbbPhuongThucThanhToan.getSelectedItem();
        	NhapHang_entity nh = new NhapHang_entity(maNhapHang, ngayNhapHang, tongTien, ghiChu, "Đã Thanh Toán",hhht);
        	System.out.println(nh.toString());
        	nhapHang_DAO.insert(nh);
        	
        	for (int row = 0; row < table.getRowCount(); row++) {
        	    String maSanPham = (String) table.getValueAt(row, 2); // Product Code
        	    int soLuong = (int) table.getValueAt(row, 5); // Quantity
        	    double thanhTien = (double) table.getValueAt(row, 8); 
        	    
        	    SanPham_entity sp = new SanPham_entity(maSanPham);
        	    
        	    
//        	    ChiTietNhapHang_entity ctnh = new ChiTietNhapHang_entity(nh, sp, soLuong, thanhTien);
//        	    chiTietNhapHang_DAO.insert(ctnh); 
        	    nhapHang_DAO.UpdateQuantity(maSanPham, soLuong);
            MessageAlerts.getInstance().showMessage(
                "Thông báo",
                "Thanh toán thành công!",
                MessageAlerts.MessageType.SUCCESS
            );
        }
        	xoaTrong();
	}
	}
	public void insertNhapHangLuuTam() {
        // Kiểm tra danh sách sản phẩm
        if (table.getRowCount() == 0) { // Kiểm tra nếu bảng sản phẩm trống
            MessageAlerts.getInstance().showMessage(
                "Cảnh báo",
                "Vui lòng thêm ít nhất một sản phẩm trước khi lưu tạm!",
                MessageAlerts.MessageType.WARNING
            );
            return; // Ngừng xử lý tiếp
        }

        // Lấy số tiền người dùng nhập
        double tientra = Double.parseDouble(tfTienTra.getText());
        double tienCanTra = Double.parseDouble(lblTienCanTra.getText());

        // Kiểm tra số tiền trả và số tiền cần trả
        if (tienCanTra > tientra || tientra == 0) {
            MessageAlerts.getInstance().showMessage(
                "Cảnh báo",
                "Tiền Trả phải lớn hơn Tiền Cần Trả!",
                MessageAlerts.MessageType.WARNING
            );
        } else {
        	String maNhapHang = generateMovieCode("NH");
        	LocalDate ngayNhapHang = LocalDate.now();
        	double tongTien = tienCanTra;
        	String ghiChu = tfGhiChu.getText();
        	String hhht = (String) cbbPhuongThucThanhToan.getSelectedItem();
        	NhapHang_entity nh = new NhapHang_entity(maNhapHang, ngayNhapHang, tongTien, ghiChu, "Lưu Tạm",hhht);
        	System.out.println(nh.toString());
        	nhapHang_DAO.insert(nh);
        	
        	for (int row = 0; row < table.getRowCount(); row++) {
        	    String maSanPham = (String) table.getValueAt(row, 2); // Product Code
        	    int soLuong = (int) table.getValueAt(row, 5); // Quantity
        	    double thanhTien = (double) table.getValueAt(row, 8); 
        	    
        	    SanPham_entity sp = new SanPham_entity(maSanPham);
//        	    ChiTietNhapHang_entity ctnh = new ChiTietNhapHang_entity(nh, sp, soLuong, thanhTien);
//        	    chiTietNhapHang_DAO.insert(ctnh); 
        	    nhapHang_DAO.UpdateQuantity(maSanPham, soLuong);
            MessageAlerts.getInstance().showMessage(
                "Thông báo",
                "Thanh toán thành công!",
                MessageAlerts.MessageType.SUCCESS
            );
        }
        	xoaTrong();
	}
	}
	public void xoaTrong() {
		dftable.setRowCount(0);
		lblTongTien.setText("");
		lblVAT.setText("");
		lblTienCanTra.setText("");
		tfTienTra.setText("");
		cbbPhuongThucThanhToan.setSelectedIndex(0);
		lblTienThua.setText("");
		tfGhiChu.setText("");
	}
}
