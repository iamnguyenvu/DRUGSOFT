package gui;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import connectDB.connectDB;
import dao.SanPham_DAO;
import entity.LoaiSanPham_entity;
import entity.SanPham_entity;
import nguyenvu.components.SimpleForm;
import nguyenvu.utils.ButtonEditor;
import nguyenvu.utils.ButtonRenderer;
import nguyenvu.utils.DeleteButtonPanel;
import nguyenvu.utils.HeaderRenderer;
import nguyenvu.utils.RoundedPanel;
import raven.alerts.MessageAlerts;

import javax.swing.border.EtchedBorder;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import nguyenvu.utils.RoundedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class SanPham extends SimpleForm implements ActionListener,MouseListener {

	private static final long serialVersionUID = 1L;
	private RoundedTextField tf_timKiem;
	public JTable tb_SanPham;
	public DefaultTableModel dftb_SanPham;
	private JButton btn_Add;
	private SanPham_DAO sp_dao;
	private JComboBox cb_LocTheoLoai;
	private JRadioButton radio_giaTuThapDenCao;
	private JRadioButton radio_giaTuCaoDenThap;
	private JButton btnXuatEx;
	private formCapNhatSanPham updateForm;
	private int currentPage = 1;
	private final int rowsPerPage = 9;
	private int totalPages = 0;
	private JLabel lblPageIndicator;

	/**
	 * Create the panel.
	 */
	public SanPham() {

		connectDB.accessDataBase();
		sp_dao = new SanPham_DAO();

		setPreferredSize(new Dimension(1500, 800));
		setLayout(new BorderLayout(0, 0));

		JPanel pnContent = new JPanel();
		pnContent.setBackground(new Color(240, 240, 240,0));
		add(pnContent, BorderLayout.CENTER);
		pnContent.setLayout(null);
		// Tạo đường viền chỉ có phía dưới
		Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);

		JPanel pnCenter = new JPanel();
		pnCenter.setBounds(0, 49, 1500, 751);
		pnCenter.setBackground(new Color(240, 240, 240,0));
		pnContent.add(pnCenter);
		pnCenter.setLayout(null);

		String[] columnNames = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Giá Bán", "Loại Sản Phẩm", "Thuế", "Cập Nhật", "Xóa" };

		dftb_SanPham = new DefaultTableModel(columnNames, 0); // columnNames là mảng chứa tên cột
		tb_SanPham = new JTable(dftb_SanPham);
		tb_SanPham.setForeground(Color.BLACK);
		tb_SanPham.setFont(new Font("Arial", Font.PLAIN, 13));
		tb_SanPham.setModel(dftb_SanPham);
		tb_SanPham.setBackground(Color.WHITE);
		tb_SanPham.setRowHeight(60);
		tb_SanPham.getTableHeader().setReorderingAllowed(false);

		// Tùy chỉnh tiêu đề bảng
		tb_SanPham.getTableHeader().setDefaultRenderer(new HeaderRenderer());
		tb_SanPham.getTableHeader().setPreferredSize(new Dimension(tb_SanPham.getWidth(), 40));
		tb_SanPham.getTableHeader().setBackground(new Color(11, 101, 136));
		tb_SanPham.getTableHeader().setForeground(Color.WHITE);

		// Căn giữa nội dung các ô
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < tb_SanPham.getColumnCount(); i++) {
		    tb_SanPham.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		// Định dạng các cột đặc biệt
		tb_SanPham.getColumnModel().getColumn(0).setPreferredWidth(60); // Mã Sản Phẩm
		tb_SanPham.getColumnModel().getColumn(1).setPreferredWidth(100); // Tên Sản Phẩm
		tb_SanPham.getColumnModel().getColumn(2).setPreferredWidth(80); // Giá Bán
		tb_SanPham.getColumnModel().getColumn(5).setPreferredWidth(40); // Cập Nhật
		tb_SanPham.getColumnModel().getColumn(6).setPreferredWidth(40); // Xóa

		// Thêm hành động khi double-click chuột
		tb_SanPham.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = tb_SanPham.getSelectedRow();
		        int col = tb_SanPham.getSelectedColumn();

		        // Kiểm tra số lần click
		        if (e.getClickCount() == 2 && col != 5 && col != 6) { // Double-click, không phải cột "Cập Nhật" hoặc "Xóa"
		            try {
		                String maSP = (String) dftb_SanPham.getValueAt(row, 0);
		                SanPham_entity sp = sp_dao.getThongTinSP(maSP);

		                // Hiển thị form thông tin sản phẩm
		                formThongTinSP thongTinSPForm = new formThongTinSP();
		                thongTinSPForm.CN(sp);

		                JDialog dialog = new JDialog();
		                dialog.getContentPane().add(thongTinSPForm);
		                dialog.setSize(1150, 800);
		                dialog.setLocationRelativeTo(null);
		                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		                dialog.setVisible(true);
		            } catch (Exception ex) {
		                JOptionPane.showMessageDialog(null, "Lỗi xử lý sản phẩm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});


		// Gán nút cho các cột "Cập Nhật" và "Xóa"
		tb_SanPham.getColumn("Cập Nhật").setCellRenderer(new ButtonRenderer("Cập Nhật"));
		tb_SanPham.getColumn("Cập Nhật").setCellEditor(new ButtonEditor("Cập Nhật", e -> {
		    try {
		        int row = Integer.parseInt(e.getActionCommand().split("_")[1]);
		        String maSP = (String) dftb_SanPham.getValueAt(row, 0);
		        SanPham_entity sp = sp_dao.getThongTinSP(maSP);

		        // Hiển thị form cập nhật sản phẩm
		        formCapNhatSanPham updateForm = new formCapNhatSanPham();
		        updateForm.CN(sp);

		        JDialog dialog = new JDialog();
		        dialog.getContentPane().add(updateForm);
		        dialog.setSize(1150, 800);
		        dialog.setLocationRelativeTo(null);
		        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        dialog.setVisible(true);
		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(null, "Lỗi cập nhật sản phẩm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		    }
		}));

		tb_SanPham.getColumn("Xóa").setCellRenderer(new ButtonRenderer("Xóa"));
		tb_SanPham.getColumn("Xóa").setCellEditor(new ButtonEditor("Xóa", e -> {
		    try {
		        int row = Integer.parseInt(e.getActionCommand().split("_")[1]);
		        String maSP = (String) dftb_SanPham.getValueAt(row, 0);

		        int confirm = JOptionPane.showConfirmDialog(null,
		                "Bạn có xác nhận xóa không?",
		                "Xác nhận xóa",
		                JOptionPane.YES_NO_OPTION);

		        if (confirm == JOptionPane.YES_OPTION) {
		            boolean isDeleted = sp_dao.xoaSanPham(maSP);

		            if (isDeleted) {
		                dftb_SanPham.removeRow(row);
		                JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(null, "Xóa sản phẩm thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(null, "Lỗi xóa sản phẩm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		    }
		}));

		JScrollPane scp_SanPham = new JScrollPane(tb_SanPham);
		scp_SanPham.setBackground(new Color(240, 240, 240,0));
		scp_SanPham.setBounds(23, 60, 1107, 589);
		pnCenter.add(scp_SanPham);
		// đưa dữ liệu từ database vào table
		RoundedPanel pnLoc = new RoundedPanel(30);
		pnLoc.setForeground(new Color(0, 0, 0));
		pnLoc.setBackground(new Color(240,240,240,0));
		pnLoc.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "L\u1ECDc S\u1EA3n Ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnLoc.setBounds(1165, 78, 307, 370);
		pnCenter.add(pnLoc);
		pnLoc.setLayout(null);
		JLabel lbLocTheoLoai = new JLabel("Loại Sản Phẩm");
		lbLocTheoLoai.setForeground(new Color(0, 0, 0));
//		lbLocTheoLoai.setBackground(new Color(255, 255, 255));
		lbLocTheoLoai.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lbLocTheoLoai.setBounds(10, 70, 106, 28);
		pnLoc.add(lbLocTheoLoai);

		cb_LocTheoLoai = new JComboBox<>();
		cb_LocTheoLoai.setForeground(new Color(0, 0, 0));
		cb_LocTheoLoai.setBackground(new Color(255, 255, 255));
		// Thêm các mục vào JComboBox
		cb_LocTheoLoai.addItem("Tất cả");
		cb_LocTheoLoai.addItem("Thuoc");
		cb_LocTheoLoai.addItem("TPCN");
		cb_LocTheoLoai.addItem("TBYT");
		cb_LocTheoLoai.setBounds(126, 72, 106, 28);
		cb_LocTheoLoai.setBorder(bottomBorder);
		cb_LocTheoLoai.setSelectedIndex(0);

		pnLoc.add(cb_LocTheoLoai);

		ButtonGroup group_ten = new ButtonGroup();

		JPanel pn_SapXepTheoGia =  new JPanel();
		pn_SapXepTheoGia.setBackground(new Color(240, 240, 240,0));
		pn_SapXepTheoGia.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "G\u00EDa S\u1EA3n Ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		pn_SapXepTheoGia.setBounds(10, 154, 275, 125);
		pnLoc.add(pn_SapXepTheoGia);
		pn_SapXepTheoGia.setLayout(null);

		radio_giaTuThapDenCao = new JRadioButton("Từ Thấp Đến Cao");
		radio_giaTuThapDenCao.setForeground(new Color(0, 0, 0));
		radio_giaTuThapDenCao.setBackground(new Color(240, 240, 240));
		radio_giaTuThapDenCao.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_giaTuThapDenCao.setBounds(19, 34, 139, 21);
		pn_SapXepTheoGia.add(radio_giaTuThapDenCao);

		radio_giaTuCaoDenThap = new JRadioButton("Từ Cao Đến Thấp");
		radio_giaTuCaoDenThap.setForeground(new Color(0, 0, 0));
		radio_giaTuCaoDenThap.setBackground(new Color(240, 240, 240));
		radio_giaTuCaoDenThap.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_giaTuCaoDenThap.setBounds(19, 80, 139, 21);
		pn_SapXepTheoGia.add(radio_giaTuCaoDenThap);

		ButtonGroup group_gia = new ButtonGroup();
		group_gia.add(radio_giaTuThapDenCao);
		group_gia.add(radio_giaTuCaoDenThap);
		radio_giaTuThapDenCao.setSelected(true);
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 659, 394, 57);
		pnCenter.add(panel);
		panel.setLayout(null);
		
		JButton btnFirst = new JButton((new FlatSVGIcon("gui/icon/first-page.svg",0.03f)));
		btnFirst.setBounds(10, 10, 67, 37);
		panel.add(btnFirst);
		
		JButton btnPrevious = new JButton((new FlatSVGIcon("gui/icon/pre-page.svg",0.02f)));
		btnPrevious.setBounds(90, 10, 67, 37);
		panel.add(btnPrevious);
		
		JButton btnNext = new JButton((new FlatSVGIcon("gui/icon/next-page.svg",0.02f)));
		btnNext.setBounds(242, 10, 67, 37);
		panel.add(btnNext);
		
		JButton btnLast = new JButton((new FlatSVGIcon("gui/icon/last-page.svg",0.03f)));
		btnLast.setBounds(319, 10, 67, 37);
		panel.add(btnLast);
		
		lblPageIndicator = new JLabel("");
		lblPageIndicator.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPageIndicator.setBounds(174, 10, 45, 37);
		panel.add(lblPageIndicator);
										
										JLabel jLabel1 = new JLabel();
										jLabel1.setText("Sản phẩm Bán Hàng");
										jLabel1.setForeground(new Color(11, 101, 136));
										jLabel1.setFont(new Font("Arial", Font.BOLD, 24));
										jLabel1.setBounds(23, 20, 247, 30);
										pnCenter.add(jLabel1);
										
										
										RoundedPanel pnHeader = new RoundedPanel(30);
										pnHeader.setPreferredSize(new Dimension(1522, 50));
										pnHeader.setBackground(new Color(11, 101, 136));
										pnHeader.setBounds(0, 0, 1522, 50);
										pnContent.add(pnHeader);
										
//		tbDark = new TableDark();
										tf_timKiem = new RoundedTextField(40);
//										tf_timKiem = new JTextField();
										tf_timKiem.setFont(new Font("Arial", Font.PLAIN, 15));
										tf_timKiem.setBackground(new Color(255, 255, 255));
										tf_timKiem.setColumns(10);
										
												// Placeholder behavior khi JTextField mất hoặc có focus
												tf_timKiem.addFocusListener(new FocusAdapter() {
													@Override
													public void focusGained(FocusEvent e) {
														if (tf_timKiem.getText().equals("Nhập Tên Hoặc Mã Sản Phẩm")) {
															tf_timKiem.setText("");
															tf_timKiem.setForeground(Color.GRAY); // Đặt lại màu văn bản xám cho placeholder
														} else {
															tf_timKiem.setForeground(Color.BLACK); // Đặt màu văn bản bình thường nếu có nội dung
														}
													}
										
													@Override
													public void focusLost(FocusEvent e) {
														if (tf_timKiem.getText().equals("")) {
															tf_timKiem.setForeground(Color.GRAY);
															tf_timKiem.setText("Nhập Tên Hoặc Mã Sản Phẩm"); // Nếu không có nội dung thì để trống
														}
													}
												});
												
														// Khởi tạo với placeholder
														tf_timKiem.setText("Nhập Tên Hoặc Mã Sản Phẩm"); // Hiện placeholder
														tf_timKiem.setForeground(new Color(0, 0, 0)); // Đặt màu xám cho văn bản placeholder
										
												btn_Add = new JButton(new FlatSVGIcon("gui/icon/add.svg", 0.03f));
												btn_Add.setBackground(new Color(255, 255, 255));
												btn_Add.addActionListener(new ActionListener() {
													private formThemSanPham themSanPhamPanel;

													public void actionPerformed(ActionEvent e) {
														// Tạo JDialog chứa formThemSanPham
														JDialog dialog = new JDialog();

														// Thêm formThemSanPham vào dialog
														themSanPhamPanel = new formThemSanPham(SanPham.this);
														dialog.getContentPane().add(themSanPhamPanel);

														// Đặt kích thước cho dialog (phù hợp với formThemSanPham)
														dialog.setSize(1150, 800);

														// Căn giữa màn hình
														dialog.setLocationRelativeTo(null);

														// Thiết lập đóng form khi nhấn nút "X"
														dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

														// Hiển thị dialog
														dialog.setVisible(true);
													}
												});
										
										btnXuatEx = new JButton("Xuất File Excel");
										btnXuatEx.setBackground(new Color(255, 255, 255));
										btnXuatEx.setFont(new Font("Arial", Font.BOLD, 12));
										btnXuatEx.addActionListener(this);
										
												JButton btn_Search = new JButton();
												btn_Search.setBackground(new Color(255, 255, 255));
												btn_Search.setText("Tìm kiếm");
												btn_Search.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
												btn_Search.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														String searchText = tf_timKiem.getText().trim();
														if (searchText.equals("") || searchText.equals("Nhập Tên Hoặc Mã Sản Phẩm")) {
															docDuLieuVaoTable(currentPage, rowsPerPage);
														} else {
															dftb_SanPham.setRowCount(0);
															List<SanPham_entity> filteredProducts = sp_dao.timKiemSanPham(searchText);
															for (SanPham_entity product : filteredProducts) {
																dftb_SanPham.addRow(new Object[] { product.getMaSP(), product.getTenSP(), product.getSoLuong(),
																		product.getNgaySanXuat(), product.getNgayHetHan(), product.getKhoiLuong(),
																		product.getDonViTinh(), product.getNhaCungCap(), product.getGia(),

																		product.getThanhPhan(),

																		product.getCongDung(), product.getHinhAnhSP(), product.getLoaiSanPham().getMaLoaiSP(),product.getThue()
																		// Add actions for Update and Delete as necessary
																});
															}
														}

													}
												});
										
										
										GroupLayout gl_pnHeader = new GroupLayout(pnHeader);
										gl_pnHeader.setHorizontalGroup(
											gl_pnHeader.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_pnHeader.createSequentialGroup()
													.addGap(21)
													.addComponent(tf_timKiem, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(btn_Search, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, 778, Short.MAX_VALUE)
													.addComponent(btn_Add, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
													.addGap(30)
													.addComponent(btnXuatEx, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
													.addGap(42))
										);
										gl_pnHeader.setVerticalGroup(
											gl_pnHeader.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_pnHeader.createSequentialGroup()
													.addContainerGap()
													.addGroup(gl_pnHeader.createParallelGroup(Alignment.LEADING)
														.addComponent(btn_Add, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnXuatEx, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
														.addGroup(gl_pnHeader.createParallelGroup(Alignment.LEADING, false)
															.addComponent(btn_Search, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
															.addComponent(tf_timKiem, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
													.addContainerGap())
										);
										pnHeader.setLayout(gl_pnHeader);

		ButtonGroup group_ngsx = new ButtonGroup();

		ButtonGroup group_nghh = new ButtonGroup();

		ButtonGroup groupSL = new ButtonGroup();
		
		btnFirst.addActionListener(e -> {
	        currentPage = 1;
	        docDuLieuVaoTable(currentPage, rowsPerPage);
	    });

	    btnPrevious.addActionListener(e -> {
	        if (currentPage > 1) {
	            currentPage--;
	            docDuLieuVaoTable(currentPage, rowsPerPage);
	        }
	    });

	    btnNext.addActionListener(e -> {
	        if (currentPage < totalPages) {
	            currentPage++;
	            docDuLieuVaoTable(currentPage, rowsPerPage);
	        }
	    });

	    btnLast.addActionListener(e -> {
	        currentPage = totalPages;
	        docDuLieuVaoTable(currentPage, rowsPerPage);
	    });

		radio_giaTuThapDenCao.addActionListener(e -> docDuLieuVaoTable(currentPage, rowsPerPage));
		radio_giaTuCaoDenThap.addActionListener(e -> docDuLieuVaoTable(currentPage, rowsPerPage));

		cb_LocTheoLoai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				docDuLieuVaoTable(currentPage, rowsPerPage);

			}
		});
		docDuLieuVaoTable(currentPage, rowsPerPage);

	}

	// Phương thức thêm dòng vào bảng
	public void addRowTable(SanPham_entity sp) {
		// Thêm dòng mới vào mô hình bảng
		dftb_SanPham.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getGia(), sp.getLoaiSanPham().getMaLoaiSP(),sp.getThue() });
				sp_dao.themSanPham(sp);
				
	}

	public void docDuLieuVaoTable(int currentPage, int rowsPerPage) {
	    dftb_SanPham.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng
	    List<SanPham_entity> products = sp_dao.getAllSanPham();
	    // Lọc theo loại sản phẩm (nếu có)
	    String selectedType = cb_LocTheoLoai.getSelectedItem().toString();
	    if (!selectedType.equalsIgnoreCase("Tất cả")) {
	        products = products.stream().filter(sp -> sp.getLoaiSanPham().getMaLoaiSP().equalsIgnoreCase(selectedType))
	                .collect(Collectors.toList());
	    }

	    // Sắp xếp sản phẩm (nếu cần)
	    Comparator<SanPham_entity> comparator = null;
	    if (radio_giaTuThapDenCao.isSelected()) {
	        comparator = Comparator.comparingDouble(SanPham_entity::getGia);
	    } else if (radio_giaTuCaoDenThap.isSelected()) {
	        comparator = Comparator.comparingDouble(SanPham_entity::getGia).reversed();
	    }
	    if (comparator != null) {
	        products.sort(comparator);
	    }

	    // Tính tổng số trang
	    totalPages = (int) Math.ceil((double) products.size() / rowsPerPage);

	    // Tính toán chỉ số bắt đầu và kết thúc
	    int start = (currentPage - 1) * rowsPerPage;
	    int end = Math.min(start + rowsPerPage, products.size());

	    // Hiển thị dữ liệu của trang hiện tại
	    for (int i = start; i < end; i++) {
	        SanPham_entity product = products.get(i);
	        dftb_SanPham.addRow(new Object[]{
	            product.getMaSP(), product.getTenSP(), product.getGia(),
	            product.getLoaiSanPham().getMaLoaiSP(), product.getThue()
	        });
	    }
	    lblPageIndicator.setText(currentPage + " / " + totalPages);
	}
	public void openFile(String file) {
		try {
			File path = new File(file);
			Desktop.getDesktop().open(path);

					
		} catch (IOException io) {
			// TODO: handle exception
			System.out.println(io);
		}
		
	}
	
	   private void xuatExcel() throws SQLException {

	        // Thực hiện logic để lấy danh sách sản phẩm theo tiêu chí lọc
	        ArrayList<SanPham_entity> sanPhamList;
	        sanPhamList = sp_dao.getAllSanPham();
			
			
			// Tạo workbook và sheet
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Danh sách sản phẩm");

			// Tạo tiêu đề cột
			XSSFRow headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("Mã sản phẩm");
			headerRow.createCell(1).setCellValue("Tên sản phẩm");
			headerRow.createCell(2).setCellValue("Giá");
			headerRow.createCell(3).setCellValue("Ngày sản xuất");
			headerRow.createCell(4).setCellValue("Ngày hết hạn");
			headerRow.createCell(5).setCellValue("Khối lượng");
			headerRow.createCell(6).setCellValue("Đơn vị tính");
			headerRow.createCell(7).setCellValue("Nhà cung cấp");
			headerRow.createCell(8).setCellValue("Thành phần");
			headerRow.createCell(9).setCellValue("Công dụng");
			headerRow.createCell(10).setCellValue("Hình ảnh");
			headerRow.createCell(11).setCellValue("Loại Sản Phẩm");
			
			// Thêm dữ liệu vào sheet
			int rowNum = 1;
			for (SanPham_entity sp : sanPhamList) {
			    XSSFRow row = sheet.createRow(rowNum++);
			    row.createCell(0).setCellValue(sp.getMaSP());
			    row.createCell(1).setCellValue(sp.getTenSP());
			    row.createCell(2).setCellValue(sp.getGia());
			    row.createCell(3).setCellValue(sp.getNgaySanXuat().toString()); // Chuyển đổi LocalDate thành String
			    row.createCell(4).setCellValue(sp.getNgayHetHan().toString());   // Chuyển đổi LocalDate thành String
			    row.createCell(5).setCellValue(sp.getKhoiLuong());
			    row.createCell(6).setCellValue(sp.getDonViTinh());
			    row.createCell(7).setCellValue(sp.getNhaCungCap());
			    row.createCell(8).setCellValue(sp.getThanhPhan());
			    row.createCell(9).setCellValue(sp.getCongDung());
			    row.createCell(10).setCellValue(sp.getHinhAnhSP());
			    row.createCell(11).setCellValue(sp.getLoaiSanPham().getMaLoaiSP());
			}

			// Hiển thị JFileChooser để chọn vị trí lưu file
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
			fileChooser.setSelectedFile(new File("DanhSachSanPham.xlsx")); // Đặt tên file mặc định

			int userSelection = fileChooser.showSaveDialog(null);
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			    File fileToSave = fileChooser.getSelectedFile();
			    try (FileOutputStream fileOut = new FileOutputStream(fileToSave)) {
			        workbook.write(fileOut);
			        JOptionPane.showMessageDialog(null, "Xuất Excel thành công!");
			    } catch (IOException ex) {
			        JOptionPane.showMessageDialog(null, "Lỗi xuất Excel: " + ex.getMessage());
			    }
			} else {
			    JOptionPane.showMessageDialog(null, "Bạn đã hủy thao tác lưu file.");
			}

			try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Đảm bảo workbook được đóng
	    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnXuatEx)) {
			try {
				xuatExcel();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
