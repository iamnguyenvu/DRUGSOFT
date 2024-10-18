package gui;

import javax.swing.JPanel;


import nguyenvu.components.SimpleForm;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import java.awt.Color;

public class SanPham extends SimpleForm {

	private static final long serialVersionUID = 1L;
	private JTextField tf_timKiem;
	private JTable tb_SanPham;
	private DefaultTableModel dftb_SanPham;

	/**
	 * Create the panel.
	 */
	public SanPham() {
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
		tf_timKiem.setBounds(259, 21, 474, 27);
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
		
		JButton btn_Search = new JButton(new FlatSVGIcon("gui/icon/search.svg",0.06f));
		btn_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Search.setBounds(743, 16, 47, 32);
		pnHeading.add(btn_Search);
		
		JButton btn_Add = new JButton(new FlatSVGIcon("gui/icon/add.svg",0.03f));
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tạo JDialog chứa formThemSanPham
		        JDialog dialog = new JDialog();
		        
		        // Thêm formThemSanPham vào dialog
		        formThemSanPham themSanPhamPanel = new formThemSanPham();
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
		
		tb_SanPham = new JTable();
		tb_SanPham.setBackground(new Color(255, 255, 255));
		dftb_SanPham = new DefaultTableModel(
				new Object[][] {
					{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
					{null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"M\u00E3 S\u1EA3n Ph\u1EA9m", "T\u00EAn S\u1EA3n Ph\u1EA9m", "Ng\u00E0y S\u1EA3n Xu\u1EA5t", "Ng\u00E0y H\u1EBFt H\u1EA1n", "Nh\u00E0 Cung C\u1EA5p", "G\u00EDa", "C\u00F4ng D\u1EE5ng", "H\u00ECnh \u1EA2nh", "Lo\u1EA1i S\u1EA3n Ph\u1EA9m", "X\u00F3a"
				}
			);
		tb_SanPham.setModel(dftb_SanPham);
		
		
        
		
		JScrollPane scp_SanPham = new JScrollPane(tb_SanPham);
		scp_SanPham.setBounds(23, 20, 1107, 700);
		pnCenter.add(scp_SanPham);
		
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
		
		JComboBox cb_LocTheoLoai = new JComboBox();
		cb_LocTheoLoai.addItem("Thuốc");
		cb_LocTheoLoai.addItem("Thực Phẩm Chức Năng");
		cb_LocTheoLoai.addItem("Thiết Bị Y Tế");
		cb_LocTheoLoai.setBounds(126, 47, 106, 28);
		cb_LocTheoLoai.setBorder(bottomBorder);
		
		pnLoc.add(cb_LocTheoLoai);
		
		JPanel pn_SapXepTheoTen = new JPanel();
		pn_SapXepTheoTen.setBackground(new Color(255, 255, 255));
		pn_SapXepTheoTen.setBorder(new TitledBorder(null, "T\u00EAn S\u1EA3n Ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_SapXepTheoTen.setBounds(10, 106, 275, 125);
		pnLoc.add(pn_SapXepTheoTen);
		pn_SapXepTheoTen.setLayout(null);
		
		JRadioButton radio_tenTuAZ = new JRadioButton("Tên Từ A - Z");
		radio_tenTuAZ.setBackground(new Color(255, 255, 255));
		radio_tenTuAZ.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_tenTuAZ.setBounds(18, 28, 139, 21);
		pn_SapXepTheoTen.add(radio_tenTuAZ);
		
		JRadioButton radio_tenTuZA = new JRadioButton("Tên Từ Z - A");
		radio_tenTuZA.setBackground(new Color(255, 255, 255));
		radio_tenTuZA.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_tenTuZA.setBounds(18, 70, 139, 21);
		pn_SapXepTheoTen.add(radio_tenTuZA);
		
		JPanel pn_SapXepTheoGia = new JPanel();
		pn_SapXepTheoGia.setBackground(new Color(255, 255, 255));
		pn_SapXepTheoGia.setBorder(new TitledBorder(null, "G\u00EDa S\u1EA3n Ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_SapXepTheoGia.setBounds(10, 252, 275, 125);
		pnLoc.add(pn_SapXepTheoGia);
		pn_SapXepTheoGia.setLayout(null);
		
		JRadioButton radio_giaTuThapDenCao = new JRadioButton("Từ Thấp Đến Cao");
		radio_giaTuThapDenCao.setBackground(new Color(255, 255, 255));
		radio_giaTuThapDenCao.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_giaTuThapDenCao.setBounds(19, 34, 139, 21);
		pn_SapXepTheoGia.add(radio_giaTuThapDenCao);
		
		JRadioButton radio_giaTuCaoDenThap = new JRadioButton("Từ Cao Đến Thấp");
		radio_giaTuCaoDenThap.setBackground(new Color(255, 255, 255));
		radio_giaTuCaoDenThap.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_giaTuCaoDenThap.setBounds(19, 80, 139, 21);
		pn_SapXepTheoGia.add(radio_giaTuCaoDenThap);
		
		JPanel pn_NgaySanXuat = new JPanel();
		pn_NgaySanXuat.setBackground(new Color(255, 255, 255));
		pn_NgaySanXuat.setBorder(new TitledBorder(null, "Ng\u00E0y S\u1EA3n Xu\u1EA5t", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_NgaySanXuat.setBounds(10, 408, 275, 125);
		pnLoc.add(pn_NgaySanXuat);
		pn_NgaySanXuat.setLayout(null);
		
		JRadioButton radio_NsxTangdan = new JRadioButton("Tăng Dần");
		radio_NsxTangdan.setBounds(23, 28, 133, 25);
		radio_NsxTangdan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_NsxTangdan.setBackground(Color.WHITE);
		pn_NgaySanXuat.add(radio_NsxTangdan);
		
		JRadioButton radio_NsxGiamdan = new JRadioButton("Giảm Dần");
		radio_NsxGiamdan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_NsxGiamdan.setBackground(Color.WHITE);
		radio_NsxGiamdan.setBounds(23, 79, 139, 21);
		pn_NgaySanXuat.add(radio_NsxGiamdan);
		
		JPanel pn_NgayHetHan = new JPanel();
		pn_NgayHetHan.setBorder(new TitledBorder(null, "Ng\u00E0y H\u1EBFt H\u1EA1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_NgayHetHan.setBackground(new Color(255, 255, 255));
		pn_NgayHetHan.setBounds(10, 560, 275, 128);
		pnLoc.add(pn_NgayHetHan);
		pn_NgayHetHan.setLayout(null);
		
		JRadioButton radio_NhhTangdan = new JRadioButton("Tăng Dần");
		radio_NhhTangdan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_NhhTangdan.setBackground(Color.WHITE);
		radio_NhhTangdan.setBounds(25, 32, 133, 25);
		pn_NgayHetHan.add(radio_NhhTangdan);
		
		JRadioButton radio_NhhGiamdan = new JRadioButton("Giảm Dần");
		radio_NhhGiamdan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		radio_NhhGiamdan.setBackground(Color.WHITE);
		radio_NhhGiamdan.setBounds(25, 71, 139, 21);
		pn_NgayHetHan.add(radio_NhhGiamdan);

	}

    public SanPham(String string, String string0, String string1, String string2, int aInt, double aDouble) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
