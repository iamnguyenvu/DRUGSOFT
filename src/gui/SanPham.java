package gui;

import javax.swing.JPanel;



import nguyenvu.components.SimpleForm;


import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import connectDB.connectDB;
import dao.SanPham_DAO;
import entity.SanPham_entity;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import java.awt.Color;

public class SanPham extends SimpleForm implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField tf_timKiem;
	private JTable tb_SanPham;
	private DefaultTableModel dftb_SanPham;
	private JButton btn_Add;
	private SanPham_DAO sp_dao;
	private JComboBox cb_LocTheoLoai;
	private JRadioButton radio_giaTuThapDenCao;
	private JRadioButton radio_giaTuCaoDenThap;
	private JRadioButton radio_NsxTangdan;
	private JRadioButton radio_NsxGiamdan;
	private JRadioButton radio_NhhTangdan;
	private JRadioButton radio_NhhGiamdan;

	/**
	 * Create the panel.
	 */
	public SanPham() {
		
		connectDB.accessDataBase(); 
		sp_dao = new SanPham_DAO();
		
		setPreferredSize(new Dimension(1500, 800));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnContent = new JPanel();
		pnContent.setBackground(new Color(255, 255, 255));
		add(pnContent, BorderLayout.CENTER);
		pnContent.setLayout(new BorderLayout(0, 0));
		
		JPanel pnHeading = new JPanel();
		pnHeading.setBackground(new Color(255, 255, 255));
		pnHeading.setPreferredSize(new Dimension(10, 70));
		pnContent.add(pnHeading, BorderLayout.NORTH);
		pnHeading.setLayout(null);
		
		tf_timKiem = new JTextField();
		tf_timKiem.setBounds(259, 21, 518, 27);
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
        tf_timKiem.setForeground(Color.GRAY); // Đặt màu xám cho văn bản placeholder
		
		JButton btn_Search = new JButton(new FlatSVGIcon("gui/icon/search_123.svg",0.6f));
		btn_Search.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String searchText = tf_timKiem.getText().trim();
		        if(searchText.equals("") || searchText.equals("Nhập Tên Hoặc Mã Sản Phẩm")) {
		        	docDuLieuVaoTable();
		        }
		        else {
		        	dftb_SanPham.setRowCount(0); // Clear the current table model
		        List<SanPham_entity> filteredProducts = sp_dao.timKiemSanPham(searchText); // Assume this method exists
		        for (SanPham_entity product : filteredProducts) {
		            dftb_SanPham.addRow(new Object[]{
		                product.getMaSP(),
		                product.getTenSP(),
		                product.getSoLuong(),
		                product.getNgaySanXuat(),
		                product.getNgayHetHan(),
		                product.getKhoiLuong(),
		                product.getDonViTinh(),
		                product.getNhaCungCap(),
		                product.getGia(),
		                product.getCongDung(),
		                product.getHinhAnhSP(),
		                product.getLoaiSanPham().getMaLoaiSP(),
		                // Add actions for Update and Delete as necessary
		            });
		        }
		        }
		        
		    }
		});

		btn_Search.setBounds(787, 16, 47, 32);
		pnHeading.add(btn_Search);
		
		btn_Add = new JButton(new FlatSVGIcon("gui/icon/add.svg",0.03f));
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
		btn_Add.setBounds(1405, 16, 58, 32);
		pnHeading.add(btn_Add);
		
		JPanel pnCenter = new JPanel();
		pnCenter.setBackground(new Color(255, 255, 255));
		pnContent.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(null);
		
		String []columnNames = {"Mã Sản Phẩm","Tên Sản Phẩm","Số Lượng","Ngày Sản Xuất","Ngày Hết Hạn","Khối Lượng","Đơn Vị Tính","Nhà Cung Cấp","Giá","Công Dụng","Hình Ảnh","Loại Sản Phẩm","Cập Nhật","Xóa"};

		dftb_SanPham = new DefaultTableModel(columnNames, 0); // columnNames là mảng chứa tên cột
		tb_SanPham = new JTable(dftb_SanPham);
		tb_SanPham.setModel(dftb_SanPham);
		
		tb_SanPham.setBackground(new Color(255, 255, 255));

		tb_SanPham.getTableHeader().setReorderingAllowed(false);
		tb_SanPham.setRowHeight(60);
		
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
            tb_SanPham.getColumnModel().getColumn(10).setPreferredWidth(80);
            tb_SanPham.getColumnModel().getColumn(11).setResizable(false);
            tb_SanPham.getColumnModel().getColumn(11).setPreferredWidth(60);
            tb_SanPham.getColumnModel().getColumn(12).setResizable(false);
            tb_SanPham.getColumnModel().getColumn(12).setPreferredWidth(40);
            tb_SanPham.getColumnModel().getColumn(13).setResizable(false);
            tb_SanPham.getColumnModel().getColumn(13).setPreferredWidth(40);
        }

		JScrollPane scp_SanPham = new JScrollPane(tb_SanPham);
		scp_SanPham.setBounds(23, 20, 1107, 688);
		pnCenter.add(scp_SanPham);
		
		// đưa dữ liệu từ database vào table
		docDuLieuVaoTable();
		
		JPanel pnLoc = new JPanel();
		pnLoc.setBackground(new Color(255, 255, 255));
		pnLoc.setBorder(new TitledBorder(null, "L\u1ECDc S\u1EA3n Ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnLoc.setBounds(1167, 22, 307, 698);
		pnCenter.add(pnLoc);
		pnLoc.setLayout(null);
		
		JLabel lbLocTheoLoai = new JLabel("Loại Sản Phẩm");
		lbLocTheoLoai.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lbLocTheoLoai.setBounds(10, 47, 106, 28);
		pnLoc.add(lbLocTheoLoai);
		
		cb_LocTheoLoai = new JComboBox();
		cb_LocTheoLoai.addItem("Tất Cả");
		cb_LocTheoLoai.addItem("Thuoc");
		cb_LocTheoLoai.addItem("TPCN");
		cb_LocTheoLoai.addItem("TBYT");
		cb_LocTheoLoai.setBounds(126, 47, 106, 28);
		cb_LocTheoLoai.setBorder(bottomBorder);
		
		pnLoc.add(cb_LocTheoLoai);
		
		ButtonGroup group_ten = new ButtonGroup();
		


		
		JPanel pn_SapXepTheoGia = new JPanel();
		pn_SapXepTheoGia.setBackground(new Color(255, 255, 255));
		pn_SapXepTheoGia.setBorder(new TitledBorder(null, "G\u00EDa S\u1EA3n Ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_SapXepTheoGia.setBounds(10, 189, 275, 125);
		pnLoc.add(pn_SapXepTheoGia);
		pn_SapXepTheoGia.setLayout(null);
		
		radio_giaTuThapDenCao = new JRadioButton("Từ Thấp Đến Cao");
		radio_giaTuThapDenCao.setBackground(new Color(255, 255, 255));
		radio_giaTuThapDenCao.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_giaTuThapDenCao.setBounds(19, 34, 139, 21);
		pn_SapXepTheoGia.add(radio_giaTuThapDenCao);
		
		radio_giaTuCaoDenThap = new JRadioButton("Từ Cao Đến Thấp");
		radio_giaTuCaoDenThap.setBackground(new Color(255, 255, 255));
		radio_giaTuCaoDenThap.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_giaTuCaoDenThap.setBounds(19, 80, 139, 21);
		pn_SapXepTheoGia.add(radio_giaTuCaoDenThap);
		
		ButtonGroup group_gia = new ButtonGroup();
		group_gia.add(radio_giaTuThapDenCao);
		group_gia.add(radio_giaTuCaoDenThap);
		radio_giaTuThapDenCao.setSelected(true);
		
		
		JPanel pn_NgaySanXuat = new JPanel();
		pn_NgaySanXuat.setBackground(new Color(255, 255, 255));
		pn_NgaySanXuat.setBorder(new TitledBorder(null, "Ng\u00E0y S\u1EA3n Xu\u1EA5t", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_NgaySanXuat.setBounds(10, 358, 275, 125);
		pnLoc.add(pn_NgaySanXuat);
		pn_NgaySanXuat.setLayout(null);
		
		radio_NsxTangdan = new JRadioButton("Tăng Dần");
		radio_NsxTangdan.setBounds(23, 28, 133, 25);
		radio_NsxTangdan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_NsxTangdan.setBackground(Color.WHITE);
		pn_NgaySanXuat.add(radio_NsxTangdan);
		
		radio_NsxGiamdan = new JRadioButton("Giảm Dần");
		radio_NsxGiamdan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_NsxGiamdan.setBackground(Color.WHITE);
		radio_NsxGiamdan.setBounds(23, 79, 139, 21);
		pn_NgaySanXuat.add(radio_NsxGiamdan);
		
		ButtonGroup group_ngsx = new ButtonGroup();
		group_ngsx.add(radio_NsxTangdan);
		group_ngsx.add(radio_NsxGiamdan);
		radio_NsxTangdan.setSelected(true);
		
		JPanel pn_NgayHetHan = new JPanel();
		pn_NgayHetHan.setBorder(new TitledBorder(null, "Ng\u00E0y H\u1EBFt H\u1EA1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_NgayHetHan.setBackground(new Color(255, 255, 255));
		pn_NgayHetHan.setBounds(10, 529, 275, 128);
		pnLoc.add(pn_NgayHetHan);
		pn_NgayHetHan.setLayout(null);
		
		radio_NhhTangdan = new JRadioButton("Tăng Dần");
		radio_NhhTangdan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_NhhTangdan.setBackground(Color.WHITE);
		radio_NhhTangdan.setBounds(25, 32, 133, 25);
		pn_NgayHetHan.add(radio_NhhTangdan);
		

		
		radio_NhhGiamdan = new JRadioButton("Giảm Dần");
		radio_NhhGiamdan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_NhhGiamdan.setBackground(Color.WHITE);
		radio_NhhGiamdan.setBounds(25, 71, 139, 21);
		pn_NgayHetHan.add(radio_NhhGiamdan);
		
		
		radio_giaTuThapDenCao.addActionListener(e -> locSanPham());
		radio_giaTuCaoDenThap.addActionListener(e -> locSanPham());
		radio_NsxTangdan.addActionListener(e -> locSanPham());
		radio_NsxGiamdan.addActionListener(e -> locSanPham());
		radio_NhhTangdan.addActionListener(e -> locSanPham());
		radio_NhhGiamdan.addActionListener(e -> locSanPham());
		
		ButtonGroup group_nghh = new ButtonGroup();
		group_nghh.add(radio_NhhTangdan);
		group_nghh.add(radio_NhhGiamdan);
		radio_NhhTangdan.setSelected(true);
		
		JLabel lb_LocCongDung = new JLabel("Công Dụng");
		lb_LocCongDung.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lb_LocCongDung.setBounds(10, 120, 106, 20);
		pnLoc.add(lb_LocCongDung);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(126, 114, 106, 28);
		pnLoc.add(comboBox);
		cb_LocTheoLoai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				locSanPham();
				
			}
		});
		
	}
  
	// Phương thức thêm dòng vào bảng
	public void addRowTable(SanPham_entity sp) {

	    // Thêm dòng mới vào mô hình bảng

		dftb_SanPham.addRow(new Object[] {sp.getMaSP(),sp.getTenSP(),sp.getSoLuong(),sp.getNgaySanXuat(),sp.getNgayHetHan(),sp.getKhoiLuong(),sp.getDonViTinh(),sp.getNhaCungCap(),sp.getGia(),sp.getCongDung(),sp.getHinhAnhSP(),sp.getLoaiSanPham().getMaLoaiSP()});
	}
	//Phương thức thêm tất cả dữ liệu vào bảng
	private void docDuLieuVaoTable() {
		List<SanPham_entity> dssp = sp_dao.getAllSanPham();
		for (SanPham_entity sp : dssp) {
			dftb_SanPham.addRow(new Object[] {sp.getMaSP(),sp.getTenSP(),sp.getSoLuong(),sp.getNgaySanXuat(),sp.getNgayHetHan(),sp.getKhoiLuong(),sp.getDonViTinh(),sp.getNhaCungCap(),sp.getGia(),sp.getCongDung(),sp.getHinhAnhSP(),sp.getLoaiSanPham().getMaLoaiSP()});
		}
	}


	private void locSanPham() {
	    // Xóa dữ liệu hiện tại trong bảng
	    dftb_SanPham.setRowCount(0);

	    // Lấy loại sản phẩm từ JComboBox
	    String loaiSanPham = cb_LocTheoLoai.getSelectedItem().toString();
	    
	    // Lấy danh sách sản phẩm từ cơ sở dữ liệu
	    List<SanPham_entity> danhSachSanPham = sp_dao.getAllSanPham();
	    
	    // Lọc theo loại sản phẩm nếu không phải "Tất Cả"
	    if (!loaiSanPham.equals("Tất Cả")) {
	        danhSachSanPham.removeIf(sp -> !sp.getLoaiSanPham().getMaLoaiSP().equals(loaiSanPham));
	    }

	    
	    // Sắp xếp theo giá sản phẩm
	    if (radio_giaTuThapDenCao.isSelected()) {
	        danhSachSanPham.sort(Comparator.comparingDouble(SanPham_entity::getGia));
	    } else if (radio_giaTuCaoDenThap.isSelected()) {
	        danhSachSanPham.sort(Comparator.comparingDouble(SanPham_entity::getGia).reversed());
	    }
	    // Sắp xếp theo ngày sản xuất
	    if (radio_NsxTangdan.isSelected()) {
	        danhSachSanPham.sort(Comparator.comparing(SanPham_entity::getNgaySanXuat));
	    } else if (radio_NsxGiamdan.isSelected()) {
	        danhSachSanPham.sort(Comparator.comparing(SanPham_entity::getNgaySanXuat).reversed());
	    }
	    // Sắp xếp theo ngày hết hạn
	    if (radio_NhhTangdan.isSelected()) {
	        danhSachSanPham.sort(Comparator.comparing(SanPham_entity::getNgayHetHan));
	    } else if (radio_NhhGiamdan.isSelected()) {
	        danhSachSanPham.sort(Comparator.comparing(SanPham_entity::getNgayHetHan).reversed());
	    }
	    
	    // Thêm các sản phẩm vào bảng
	    for (SanPham_entity sp : danhSachSanPham) {
	        dftb_SanPham.addRow(new Object[] {
	            sp.getMaSP(),
	            sp.getTenSP(),
	            sp.getSoLuong(),
	            sp.getNgaySanXuat(),
	            sp.getNgayHetHan(),
	            sp.getKhoiLuong(),
	            sp.getDonViTinh(),
	            sp.getNhaCungCap(),
	            sp.getGia(),
	            sp.getCongDung(),
	            sp.getHinhAnhSP(),
	            sp.getLoaiSanPham().getMaLoaiSP()
	        });
	    }
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
