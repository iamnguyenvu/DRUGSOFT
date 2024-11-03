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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import nguyenvu.utils.RoundedPanel;

import javax.swing.border.EtchedBorder;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class SanPham extends SimpleForm implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField tf_timKiem;
	private JTable tb_SanPham;
	private DefaultTableModel dftb_SanPham;
	private JButton btn_Add;
	private SanPham_DAO sp_dao;
	private JComboBox cb_LocTheoLoai;
	private JRadioButton radio_giaTuThapDenCao;
	private JRadioButton radio_giaTuCaoDenThap;
	private JRadioButton radio_NhhTangdan;
	private JRadioButton radio_NhhGiamdan;
	private JRadioButton rdo_NhieuDenIt;
	private JRadioButton rdo_ItToiNhieu;
	private JButton btnXuatEx;

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
		pnContent.setLayout(new BorderLayout(0, 0));

		RoundedPanel pnHeading = new RoundedPanel(50);
		pnHeading.setBackground(new Color(11,101,136));
		pnHeading.setPreferredSize(new Dimension(10, 70));
		pnContent.add(pnHeading, BorderLayout.NORTH);
		pnHeading.setLayout(null);
		
//		tbDark = new TableDark();
		tf_timKiem = new JTextField();
		tf_timKiem.setFont(new Font("Arial", Font.PLAIN, 15));
		tf_timKiem.setBackground(new Color(11, 101, 136));
		tf_timKiem.setBounds(240, 16, 537, 32);
		pnHeading.add(tf_timKiem);
		tf_timKiem.setColumns(10);
		// Tạo đường viền chỉ có phía dưới
		Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		tf_timKiem.setBorder(bottomBorder);

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
		tf_timKiem.setForeground(new Color(255, 255, 255)); // Đặt màu xám cho văn bản placeholder

		JButton btn_Search = new JButton(new FlatSVGIcon("gui/icon/search_123.svg", 0.6f));
		btn_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchText = tf_timKiem.getText().trim();
				if (searchText.equals("") || searchText.equals("Nhập Tên Hoặc Mã Sản Phẩm")) {
					docDuLieuVaoTable();
				} else {
					dftb_SanPham.setRowCount(0);
					List<SanPham_entity> filteredProducts = sp_dao.timKiemSanPham(searchText);
					for (SanPham_entity product : filteredProducts) {
						dftb_SanPham.addRow(new Object[] { product.getMaSP(), product.getTenSP(), product.getSoLuong(),
								product.getNgaySanXuat(), product.getNgayHetHan(), product.getKhoiLuong(),
								product.getDonViTinh(), product.getNhaCungCap(), product.getGia(),

								product.getThanhPhan(),

								product.getCongDung(), product.getHinhAnhSP(), product.getLoaiSanPham().getMaLoaiSP(),
								// Add actions for Update and Delete as necessary
						});
					}
				}

			}
		});

		btn_Search.setBounds(787, 16, 47, 32);
		pnHeading.add(btn_Search);

		btn_Add = new JButton(new FlatSVGIcon("gui/icon/add.svg", 0.03f));
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
		btn_Add.setBounds(1242, 16, 58, 32);
		pnHeading.add(btn_Add);
		
		btnXuatEx = new JButton("Xuất File Excel");
		btnXuatEx.setFont(new Font("Arial", Font.BOLD, 12));
		btnXuatEx.setBounds(1334, 16, 125, 32);
		btnXuatEx.addActionListener(this);
		pnHeading.add(btnXuatEx);

		JPanel pnCenter = new JPanel();
		pnCenter.setBackground(new Color(240, 240, 240,0));
		pnContent.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(null);

		String[] columnNames = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Ngày Sản Xuất", "Ngày Hết Hạn",
				"Khối Lượng", "Đơn Vị Tính", "Nhà Cung Cấp", "Giá", "Thành Phần", "Công Dụng", "Hình Ảnh",
				"Loại Sản Phẩm", "Cập Nhật", "Xóa" };

		dftb_SanPham = new DefaultTableModel(columnNames, 0); // columnNames là mảng chứa tên cột
		tb_SanPham = new JTable(dftb_SanPham);
		tb_SanPham.setForeground(new Color(0, 0, 0));
		tb_SanPham.setFont(new Font("Arial", Font.PLAIN, 10));
		tb_SanPham.setModel(dftb_SanPham);

		tb_SanPham.setBackground(new Color(255, 255, 255));

		tb_SanPham.getTableHeader().setReorderingAllowed(false);
		tb_SanPham.setRowHeight(60);
		

//		tb_SanPham.getColumn("Xóa").setCellRenderer((TableCellRenderer) new DeleteButtonPanel());
		if (tb_SanPham.getColumnModel().getColumnCount() > 0) {
			tb_SanPham.getColumnModel().getColumn(0).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(0).setPreferredWidth(60);
			tb_SanPham.getColumnModel().getColumn(1).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(1).setPreferredWidth(60);
			tb_SanPham.getColumnModel().getColumn(2).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(2).setPreferredWidth(40);
			tb_SanPham.getColumnModel().getColumn(3).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(3).setPreferredWidth(60);
			tb_SanPham.getColumnModel().getColumn(4).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(4).setPreferredWidth(60);
			tb_SanPham.getColumnModel().getColumn(5).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(5).setPreferredWidth(40);
			tb_SanPham.getColumnModel().getColumn(6).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(6).setPreferredWidth(40);
			tb_SanPham.getColumnModel().getColumn(7).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(7).setPreferredWidth(80);
			tb_SanPham.getColumnModel().getColumn(8).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(8).setPreferredWidth(40);
			tb_SanPham.getColumnModel().getColumn(9).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(9).setPreferredWidth(100);
			tb_SanPham.getColumnModel().getColumn(10).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(10).setPreferredWidth(100);
			tb_SanPham.getColumnModel().getColumn(11).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(11).setPreferredWidth(60);
			tb_SanPham.getColumnModel().getColumn(12).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(12).setPreferredWidth(40);
			tb_SanPham.getColumnModel().getColumn(13).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(13).setPreferredWidth(40);
			tb_SanPham.getColumnModel().getColumn(14).setResizable(false);
			tb_SanPham.getColumnModel().getColumn(14).setPreferredWidth(40);
		}

		
		
		// Gán renderer và editor cho các cột "Cập Nhật" và "Xóa"
		tb_SanPham.getColumn("Cập Nhật").setCellRenderer(new ButtonRenderer("Cập Nhật"));
		tb_SanPham.getColumn("Cập Nhật").setCellEditor(new ButtonEditor("Cập Nhật", new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String command = e.getActionCommand();
		        if (command.startsWith("Cập Nhật")) {
		            int row = Integer.parseInt(command.split("_")[1]);
		            System.out.println("Cập Nhật cho dòng: " + row);
		            // Thực hiện logic cập nhật tại đây
		            String maSP = (String) dftb_SanPham.getValueAt(row, 0);
		            String tenSP = (String) dftb_SanPham.getValueAt(row, 1);
		            int soLuong = (int) dftb_SanPham.getValueAt(row, 2);
		            LocalDate ngaySX = (LocalDate) dftb_SanPham.getValueAt(row, 3);
		            LocalDate ngayHH = (LocalDate) dftb_SanPham.getValueAt(row, 4);
		            double khoiLuong = (double) dftb_SanPham.getValueAt(row, 5);
		            String donViTinh = (String) dftb_SanPham.getValueAt(row, 6);
		            String nhaCC = (String) dftb_SanPham.getValueAt(row, 7);
		            double gia = (double) dftb_SanPham.getValueAt(row, 8);
		            String thanhPham = (String) dftb_SanPham.getValueAt(row, 9);
		            String congDung = (String) dftb_SanPham.getValueAt(row, 10);
		            String hinhAnh = (String) dftb_SanPham.getValueAt(row, 11);
		            String maLoaiSP = (String) dftb_SanPham.getValueAt(row, 12);
		            LoaiSanPham_entity loaiSP = new LoaiSanPham_entity(maLoaiSP);
		            SanPham_entity sp = new SanPham_entity(maSP, tenSP, ngaySX, ngayHH, khoiLuong, donViTinh, nhaCC, gia, thanhPham, congDung, hinhAnh, loaiSP, soLuong);
		            
		            formCapNhatSanPham updateForm = new formCapNhatSanPham();
		            
		         // Đưa thông tin sản phẩm vào các trường trong formCapNhatSanPham
		    	    updateForm.tf_Tensp.setText(tenSP);
		    	    updateForm.tf_soLuong.setText(soLuong+"");
		    	    updateForm.tf_Gia.setText(String.valueOf(gia));
		    	    updateForm.tf_HinhAnh.setText(hinhAnh);
		    	    updateForm.tf_KhoiLuong.setText(String.valueOf(khoiLuong));
		    	    updateForm.tf_NhaCungCap.setText(nhaCC);
		    	    updateForm.ta_CongDung.setText(congDung);
		    	    updateForm.ta_ThanhPhan.setText(thanhPham);
		    	    updateForm.cb_LoaiSP.setSelectedItem(loaiSP);

		    	    // Thiết lập ngày sản xuất và ngày hết hạn
		    	    if (ngaySX != null) {
		    	        updateForm.dcNgaySanXuat.setDate(Date.valueOf(ngaySX));
		    	    }
		    	    if (ngayHH != null) {
		    	        updateForm.dcNgayHetHan.setDate(Date.valueOf(ngayHH));
		    	    }

		    	    // Thiết lập đơn vị tính
		    	    updateForm.cb_DonViTinh.setSelectedItem(donViTinh);
		            
					// Tạo JDialog chứa formThemSanPham
					JDialog dialog = new JDialog();

					// Thêm formThemSanPham vào dialog
					
					dialog.getContentPane().add(updateForm);

					// Đặt kích thước cho dialog (phù hợp với formThemSanPham)
					dialog.setSize(1150, 800);

					// Căn giữa màn hình
					dialog.setLocationRelativeTo(null);

					// Thiết lập đóng form khi nhấn nút "X"
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

					// Hiển thị dialog
					dialog.setVisible(true);
		        }
		    }
		}));

		tb_SanPham.getColumn("Xóa").setCellRenderer(new ButtonRenderer("Xóa"));
		tb_SanPham.getColumn("Xóa").setCellEditor(new ButtonEditor("Xóa", new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String command = e.getActionCommand();
		        if (command.startsWith("Xóa")) {
		            int row = Integer.parseInt(command.split("_")[1]);

		            // Lấy mã sản phẩm (giả sử cột đầu tiên là maSP)
		            String maSP = (String) dftb_SanPham.getValueAt(row, 0);

		            // Hiển thị hộp thoại xác nhận xóa ở giữa màn hình
		            int confirm = JOptionPane.showConfirmDialog(null, 
		                    "Bạn có xác nhận xóa không?", 
		                    "Xác nhận xóa", 
		                    JOptionPane.YES_NO_OPTION);

		            if (confirm == JOptionPane.YES_OPTION) {
		                // Gọi hàm xoaSanPham
		                boolean isDeleted = sp_dao.xoaSanPham(maSP);
		                
		                if (isDeleted) {
		                    // Xóa dòng trong TableModel nếu xóa thành công từ cơ sở dữ liệu
		                    dftb_SanPham.removeRow(row);
		                    JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		                } else {
		                    JOptionPane.showMessageDialog(null, "Xóa sản phẩm thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
		                System.out.println("Đã hủy thao tác xóa.");
		            }
		        }
		    }
		}));




		JScrollPane scp_SanPham = new JScrollPane(tb_SanPham);
		scp_SanPham.setBackground(new Color(240, 240, 240,0));
		scp_SanPham.setBounds(23, 20, 1107, 688);
		pnCenter.add(scp_SanPham);

		// đưa dữ liệu từ database vào table

		RoundedPanel pnLoc = new RoundedPanel(30);
		pnLoc.setBackground(new Color(11,101,136));
		pnLoc.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "L\u1ECDc S\u1EA3n Ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnLoc.setBounds(1167, 22, 307, 698);
		pnCenter.add(pnLoc);
		pnLoc.setLayout(null);

		JLabel lbLocTheoLoai = new JLabel("Loại Sản Phẩm");
		lbLocTheoLoai.setForeground(new Color(255, 255, 255));
		lbLocTheoLoai.setBackground(new Color(255, 255, 255));
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
		pn_SapXepTheoGia.setBackground(new Color(0, 196, 196));
		pn_SapXepTheoGia.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "G\u00EDa S\u1EA3n Ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));

		pn_SapXepTheoGia.setBounds(10, 154, 275, 125);
		pnLoc.add(pn_SapXepTheoGia);
		pn_SapXepTheoGia.setLayout(null);

		radio_giaTuThapDenCao = new JRadioButton("Từ Thấp Đến Cao");
		radio_giaTuThapDenCao.setBackground(new Color(0, 196, 196));
		radio_giaTuThapDenCao.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_giaTuThapDenCao.setBounds(19, 34, 139, 21);
		pn_SapXepTheoGia.add(radio_giaTuThapDenCao);

		radio_giaTuCaoDenThap = new JRadioButton("Từ Cao Đến Thấp");
		radio_giaTuCaoDenThap.setBackground(new Color(0, 196, 196));
		radio_giaTuCaoDenThap.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_giaTuCaoDenThap.setBounds(19, 80, 139, 21);
		pn_SapXepTheoGia.add(radio_giaTuCaoDenThap);

		ButtonGroup group_gia = new ButtonGroup();
		group_gia.add(radio_giaTuThapDenCao);
		group_gia.add(radio_giaTuCaoDenThap);
		radio_giaTuThapDenCao.setSelected(true);

		ButtonGroup group_ngsx = new ButtonGroup();

		JPanel pn_NgayHetHan = new JPanel();
		pn_NgayHetHan.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ng\u00E0y H\u1EBFt H\u1EA1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pn_NgayHetHan.setBackground(new Color(0, 196, 196));
		pn_NgayHetHan.setBounds(10, 529, 275, 128);
		pnLoc.add(pn_NgayHetHan);
		pn_NgayHetHan.setLayout(null);

		radio_NhhTangdan = new JRadioButton("Tăng Dần");
		radio_NhhTangdan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_NhhTangdan.setBackground(new Color(0, 196, 196));
		radio_NhhTangdan.setBounds(25, 32, 133, 25);
		pn_NgayHetHan.add(radio_NhhTangdan);

		radio_NhhGiamdan = new JRadioButton("Giảm Dần");
		radio_NhhGiamdan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_NhhGiamdan.setBackground(new Color(0, 196, 196));
		radio_NhhGiamdan.setBounds(25, 71, 139, 21);
		pn_NgayHetHan.add(radio_NhhGiamdan);

		ButtonGroup group_nghh = new ButtonGroup();
		group_nghh.add(radio_NhhTangdan);
		group_nghh.add(radio_NhhGiamdan);
		radio_NhhTangdan.setSelected(true);

		JPanel pnSLTON = new JPanel();
		pnSLTON.setForeground(new Color(0, 128, 128));
		pnSLTON.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "S\u1ED1 L\u01B0\u1EE3ng T\u1ED3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnSLTON.setBackground(new Color(0, 196, 196));
		pnSLTON.setBounds(10, 344, 275, 114);
		pnLoc.add(pnSLTON);
		pnSLTON.setLayout(null);

		rdo_NhieuDenIt = new JRadioButton("Từ Nhiều Đến ít");
		rdo_NhieuDenIt.setBackground(new Color(0, 196, 196));
		rdo_NhieuDenIt.setBounds(21, 25, 157, 21);
		pnSLTON.add(rdo_NhieuDenIt);

		rdo_ItToiNhieu = new JRadioButton("Từ Ít Đến Nhiều");
		rdo_ItToiNhieu.setBackground(new Color(0, 196, 196));
		rdo_ItToiNhieu.setBounds(21, 68, 157, 21);
		pnSLTON.add(rdo_ItToiNhieu);

		ButtonGroup groupSL = new ButtonGroup();
		groupSL.add(rdo_NhieuDenIt);
		groupSL.add(rdo_ItToiNhieu);

		rdo_NhieuDenIt.setSelected(true);

		radio_giaTuThapDenCao.addActionListener(e -> docDuLieuVaoTable());
		radio_giaTuCaoDenThap.addActionListener(e -> docDuLieuVaoTable());
		radio_NhhTangdan.addActionListener(e -> docDuLieuVaoTable());
		radio_NhhGiamdan.addActionListener(e -> docDuLieuVaoTable());
		rdo_NhieuDenIt.addActionListener(e -> docDuLieuVaoTable());
		rdo_ItToiNhieu.addActionListener(e -> docDuLieuVaoTable());

		cb_LocTheoLoai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				docDuLieuVaoTable();

			}
		});
		docDuLieuVaoTable();

	}
	public void showUpdateForm(SanPham_entity sanPham) {
	    // Khởi tạo form cập nhật
	    formCapNhatSanPham updateForm = new formCapNhatSanPham();

	    // Đưa thông tin sản phẩm vào các trường trong formCapNhatSanPham
	    updateForm.tf_Tensp.setText(sanPham.getTenSP());
	    updateForm.tf_soLuong.setText(String.valueOf(sanPham.getSoLuong()));
	    updateForm.tf_Gia.setText(String.valueOf(sanPham.getGia()));
	    updateForm.tf_HinhAnh.setText(sanPham.getHinhAnhSP());
	    updateForm.tf_KhoiLuong.setText(String.valueOf(sanPham.getKhoiLuong()));
	    updateForm.tf_NhaCungCap.setText(sanPham.getNhaCungCap());
	    updateForm.ta_CongDung.setText(sanPham.getCongDung());
	    updateForm.ta_ThanhPhan.setText(sanPham.getThanhPhan());
	    updateForm.cb_LoaiSP.setSelectedItem(sanPham.getLoaiSanPham());

	    // Thiết lập ngày sản xuất và ngày hết hạn
	    if (sanPham.getNgaySanXuat() != null) {
	        updateForm.dcNgaySanXuat.setDate(Date.valueOf(sanPham.getNgaySanXuat()));
	    }
	    if (sanPham.getNgayHetHan() != null) {
	        updateForm.dcNgayHetHan.setDate(Date.valueOf(sanPham.getNgayHetHan()));
	    }

	    // Thiết lập đơn vị tính
	    updateForm.cb_DonViTinh.setSelectedItem(sanPham.getDonViTinh());

	    // Hiển thị form cập nhật sản phẩm
	    JFrame frame = new JFrame("Cập Nhật Sản Phẩm");
	    frame.setContentPane(updateForm);
	    frame.pack();
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Để đóng cửa sổ
	    frame.setLocationRelativeTo(null); // Đặt vị trí ở giữa màn hình
	    frame.setVisible(true);
	}



	// Phương thức thêm dòng vào bảng
	public void addRowTable(SanPham_entity sp) {

		// Thêm dòng mới vào mô hình bảng

		dftb_SanPham.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), sp.getNgaySanXuat(),
				sp.getNgayHetHan(), sp.getKhoiLuong(), sp.getDonViTinh(), sp.getNhaCungCap(), sp.getGia(),
				sp.getThanhPhan(), sp.getCongDung(), sp.getHinhAnhSP(), sp.getLoaiSanPham().getMaLoaiSP() });
				sp_dao.themSanPham(sp);
				
	}
	//Phương thức cập nhật sản phẩm
	public void updateRowTable(SanPham_entity sp) {
		
	}
	// Phương thức thêm tất cả dữ liệu vào bảng

	private void docDuLieuVaoTable() {
		dftb_SanPham.setRowCount(0); // Clear the current table model
		List<SanPham_entity> products = sp_dao.getAllSanPham(); // Assume this method exists

		// Filtering based on selected product type
		String selectedType = cb_LocTheoLoai.getSelectedItem().toString();

		if (!selectedType.equalsIgnoreCase("Tất cả")) {
			products = products.stream().filter(sp -> sp.getLoaiSanPham().getMaLoaiSP().equalsIgnoreCase(selectedType))
					.collect(Collectors.toList());
		}

		// Sorting based on selected options
		Comparator<SanPham_entity> comparator = null;

		// Check quantity sorting option
		if (rdo_ItToiNhieu.isSelected()) {
			comparator = Comparator.comparingInt(SanPham_entity::getSoLuong);
		} else if (rdo_NhieuDenIt.isSelected()) {
			comparator = Comparator.comparingInt(SanPham_entity::getSoLuong).reversed();
		}

		// Check price sorting option
		if (radio_giaTuThapDenCao.isSelected()) {
			comparator = (comparator == null) ? Comparator.comparingDouble(SanPham_entity::getGia)
					: comparator.thenComparing(Comparator.comparingDouble(SanPham_entity::getGia));
		} else if (radio_giaTuCaoDenThap.isSelected()) {
			comparator = (comparator == null) ? Comparator.comparingDouble(SanPham_entity::getGia).reversed()
					: comparator.thenComparing(Comparator.comparingDouble(SanPham_entity::getGia).reversed());
		}

		// Check expiration date sorting option
		if (radio_NhhTangdan.isSelected()) {
			comparator = (comparator == null) ? Comparator.comparing(SanPham_entity::getNgayHetHan)
					: comparator.thenComparing(Comparator.comparing(SanPham_entity::getNgayHetHan));
		} else if (radio_NhhGiamdan.isSelected()) {
			comparator = (comparator == null) ? Comparator.comparing(SanPham_entity::getNgayHetHan).reversed()
					: comparator.thenComparing(Comparator.comparing(SanPham_entity::getNgayHetHan).reversed());
		}

		// Apply sorting if comparator is defined
		if (comparator != null) {
			products.sort(comparator);
		}

		// Adding products to the table model
		for (SanPham_entity product : products) {
			dftb_SanPham.addRow(new Object[] { product.getMaSP(), product.getTenSP(), product.getSoLuong(),
					product.getNgaySanXuat(), product.getNgayHetHan(), product.getKhoiLuong(), product.getDonViTinh(),
					product.getNhaCungCap(), product.getGia(), product.getThanhPhan(), product.getCongDung(),
					product.getHinhAnhSP(), product.getLoaiSanPham().getMaLoaiSP(),
					// Add actions for Update and Delete as necessary
			});
		}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnXuatEx)) {
			try {
				JFileChooser fileChoose = new JFileChooser();
				fileChoose.showSaveDialog(this);
				File saveFile = fileChoose.getSelectedFile();
				if(saveFile != null) {
					saveFile = new File(saveFile.toString()+".xlsx");
					Workbook wb = new XSSFWorkbook();
					Sheet sheet = wb.createSheet("Product");
					Row rowCol = sheet.createRow(0);
					for(int i = 0; i < tb_SanPham.getColumnCount(); i ++) {
						Cell cell = rowCol.createCell(i);
						cell.setCellValue(tb_SanPham.getColumnName(i));
					}
					for(int j = 0; j < tb_SanPham.getRowCount();j++) {
						Row row = sheet.createRow(j);
						for( int k = 0; k < tb_SanPham.getColumnCount();k++) {
							Cell cell = row.createCell(k);
							if(tb_SanPham.getValueAt(j, k)!= null) {
								cell.setCellValue(tb_SanPham.getValueAt(j, k).toString());
							}
						}
					}
					FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
					wb.write(out);
					wb.close();
					out.close();
					openFile(saveFile.toString());
				}else {
					JOptionPane.showMessageDialog(null,"Error");
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

	}
	
}
