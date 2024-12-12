package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietNhapHang_DAO;
import dao.SanPhamDoiTra_DAO;
import dao.SanPham_DAO;
import entity.ChiTietNhapHang_entity;
import entity.LoaiSanPham_entity;
import entity.NhapHang_entity;
import entity.ChiTietHoaDonDoiTra_entity;
import entity.SanPham_entity;
import nguyenvu.components.SimpleForm;
import nguyenvu.utils.HeaderRenderer;
import nguyenvu.utils.RoundedPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.Icon;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class FormCapNhapSanPhamDoiTra extends SimpleForm implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Border border;
	private SanPham sanPham;
	private String maSP;
	public SanPham_entity spCapNhat;
	private DefaultTableModel dftb;
	private ChiTietNhapHang_DAO ctnh_DAO;
	private JTextField tf_Masp;
	private JTextField tf_Madt;
	private JTextField tf_sldt;
	private JTextField tf_ThanhTien;
	private JRadioButton rdoCho;
	private JRadioButton rdoXacNhan;
	private JRadioButton rdotuChoi;
	private JButton btnCapNhat;
	private SanPhamDoiTra_DAO spdt_DAO;
	private gui.SanPhamDoiTra spdt;
	private final Action action = new SwingAction();
	private JTextField tfTinhTrang;
	private JTextField tfVanDe;
	public void tat() {
		this.setVisible(false);
	}

	public FormCapNhapSanPhamDoiTra() {
		spdt = new gui.SanPhamDoiTra();
		ctnh_DAO = new ChiTietNhapHang_DAO();
		spdt_DAO = new SanPhamDoiTra_DAO();
		sanPham = new SanPham();
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(846, 553));

		JPanel pnContent = new JPanel();
		pnContent.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnContent.setBounds(10, 10, 813, 527);
		pnContent.setPreferredSize(new Dimension(1150, 800));
		Color color_pnContent = Color.decode("#f4f6f8");
		setLayout(null);
		pnContent.setBackground(color_pnContent);
		add(pnContent);
		pnContent.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(36, 82, 748, 423);
		pnContent.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã Sản Phẩm");
		lblNewLabel.setBounds(10, 28, 132, 13);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("Mã Đổi Trả:");
		lblNewLabel_1.setBounds(10, 82, 90, 13);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 15));
		
		JLabel lblNewLabel_3 = new JLabel("Số Lượng đổi trả:");
		lblNewLabel_3.setBounds(372, 28, 126, 23);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Serif", Font.PLAIN, 15));

		
						
						JPanel panel_3 = new JPanel();
						panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_3.setBounds(10, 138, 735, 2);
						panel.add(panel_3);
						
						JPanel panel_4 = new JPanel();
						panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_4.setBounds(306, 10, 1, 118);
						panel.add(panel_4);
						
						JPanel panel_1 = new JPanel();
						panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_1.setBackground(new Color(255, 255, 255));
						panel_1.setBounds(10, 155, 366, 139);
						panel.add(panel_1);
						panel_1.setLayout(null);
						
						JLabel lblTngTin = new JLabel("Thành Tiền:");
						lblTngTin.setFont(new Font("Serif", Font.PLAIN, 15));
						lblTngTin.setBounds(10, 98, 132, 13);
						panel_1.add(lblTngTin);
						
						tf_ThanhTien = new JTextField();
						tf_ThanhTien.setColumns(10);
						tf_ThanhTien.setBounds(152, 94, 163, 26);
						panel_1.add(tf_ThanhTien);
						
						JLabel lblNewLabel_2 = new JLabel("Tình Trạng:");
						lblNewLabel_2.setBounds(10, 25, 109, 23);
						panel_1.add(lblNewLabel_2);
						lblNewLabel_2.setFont(new Font("Serif", Font.PLAIN, 17));
						
						tfTinhTrang = new JTextField();
						tfTinhTrang.setColumns(10);
						tfTinhTrang.setBounds(152, 22, 163, 26);
						panel_1.add(tfTinhTrang);
						
						JPanel panel_2 = new JPanel();
						panel_2.setBorder(new TitledBorder(null, "C\u1EADp Nh\u1EADt Tr\u1EA1ng Th\u00E1i", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						panel_2.setBackground(new Color(255, 255, 255));
						panel_2.setBounds(393, 155, 326, 162);
						panel.add(panel_2);
						panel_2.setLayout(null);
						
						rdoCho = new JRadioButton("Chờ Xác Nhận");
						rdoCho.setFont(new Font("Serif", Font.PLAIN, 15));
						rdoCho.setBackground(new Color(255, 255, 255));
						rdoCho.setBounds(18, 54, 151, 21);
						panel_2.add(rdoCho);
						
						rdoXacNhan = new JRadioButton("Xác Nhận");
						rdoXacNhan.setFont(new Font("Serif", Font.PLAIN, 15));
						rdoXacNhan.setBackground(new Color(255, 255, 255));
						rdoXacNhan.setBounds(18, 116, 103, 21);
						panel_2.add(rdoXacNhan);
						
						rdotuChoi = new JRadioButton("Từ Chối");
						rdotuChoi.setFont(new Font("Serif", Font.PLAIN, 15));
						rdotuChoi.setBackground(new Color(255, 255, 255));
						rdotuChoi.setBounds(191, 54, 103, 21);
						panel_2.add(rdotuChoi);
						ButtonGroup bg = new ButtonGroup();
						rdoCho.setSelected(true);
						bg.add(rdoCho);
						bg.add(rdoXacNhan);
						bg.add(rdotuChoi);
						
						btnCapNhat = new JButton((Icon) null);
						btnCapNhat.setAction(action);
						btnCapNhat.setText("Cập Nhật");
						btnCapNhat.setForeground(Color.WHITE);
						btnCapNhat.setBackground(new Color(51, 204, 0));
						btnCapNhat.setBounds(574, 356, 120, 40);
						panel.add(btnCapNhat);
						btnCapNhat.addActionListener(new ActionListener() {
						    public void actionPerformed(ActionEvent e) {
						        // Disable các radio buttons
						        rdoXacNhan.setEnabled(false);
						        rdoCho.setEnabled(false);
						        rdotuChoi.setEnabled(false);

						        // Biến để kiểm tra cập nhật thành công
						        boolean isUpdated = false;

						        // Xử lý dựa trên lựa chọn
						        if (rdoXacNhan.isSelected()) {
						            isUpdated = spdt_DAO.capNhatSanPham("Xác Nhận", tf_Masp.getText(), tf_Madt.getText());
						            rdoXacNhan.setSelected(true);
						        }
						        if (rdotuChoi.isSelected()) {
						            isUpdated = spdt_DAO.capNhatSanPham("Từ Chối", tf_Masp.getText(), tf_Madt.getText());
						            rdotuChoi.setSelected(true);
						        }

						        // Làm mới dữ liệu trong bảng nếu cập nhật thành công
						        if (isUpdated) {
						            JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						        } else {
						            JOptionPane.showMessageDialog(null, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
						        }
						        
						    }
						});



						
						tf_Masp = new JTextField();
						tf_Masp.setBounds(142, 18, 154, 28);
						panel.add(tf_Masp);
						tf_Masp.setColumns(10);
						
						tf_Madt = new JTextField();
						tf_Madt.setColumns(10);
						tf_Madt.setBounds(142, 72, 154, 28);
						panel.add(tf_Madt);
						
						tf_sldt = new JTextField();
						tf_sldt.setColumns(10);
						tf_sldt.setBounds(514, 23, 165, 28);
						panel.add(tf_sldt);
						
						JLabel lblNewLabel_2_1 = new JLabel("Vấn Đề:");
						lblNewLabel_2_1.setFont(new Font("Serif", Font.PLAIN, 16));
						lblNewLabel_2_1.setBounds(372, 76, 109, 23);
						panel.add(lblNewLabel_2_1);
						
						tfVanDe = new JTextField();
						tfVanDe.setColumns(10);
						tfVanDe.setBounds(516, 78, 163, 26);
						panel.add(tfVanDe);
						
						JPanel pnHeading = new JPanel();
						pnHeading.setLayout(null);
						pnHeading.setBackground(new Color(11, 101, 136));
						pnHeading.setBounds(0, 0, 813, 45);
						pnContent.add(pnHeading);
						
								JLabel lb_ThemMoiSP = new JLabel("Cập Nhật Sản Phẩm Đổi Trả:");
								lb_ThemMoiSP.setBounds(10, 0, 325, 45);
								pnHeading.add(lb_ThemMoiSP);
								lb_ThemMoiSP.setForeground(new Color(255, 255, 255));
								lb_ThemMoiSP.setBackground(new Color(0, 0, 0));
								lb_ThemMoiSP.setFont(new Font("Serif", Font.PLAIN, 24));
		border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void AddDataToLable(entity.SanPhamDoiTra_entity spdt) {
		tf_Madt.setText(spdt.getMaDT());
		tf_Masp.setText(spdt.getMaSP()+"");
		tf_sldt.setText(spdt.getSoLuong()+"");
		tfTinhTrang.setText(spdt.getTinhTrang()+"");
		tf_ThanhTien.setText(spdt.getDonGia()+"");
		tfVanDe.setText(spdt.getVanDe());
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}