 package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import connectDB.connectDB;
import dao.NhanVien_DAO;
import entity.NhanVien_entity;
import nguyenvu.components.PlaceholderTextField;
import nguyenvu.components.SimpleForm;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class NhanVien extends SimpleForm implements ActionListener {
    private static final long serialVersionUID = 1L;
    private PlaceholderTextField  textField_tim; // Search text field
    private JButton btn_tim; // Search button
    private themNV themNV; // Add employee dialog
    private XemThongTinNhanVien XemThongTinNhanVien;
    private XoaNV XoaNV;
    private CapNhatNhanVien CapNhatNhanVien; 
    
    private JTable tb_NhanVien; // JTable for displaying employee data
    private DefaultTableModel dftb_NhanVien; // Model for the JTable
    private NhanVien_DAO nv_dao; // Data Access Object for employee data
    private JComboBox<String> cb_LocTheoLoai; // Filter ComboBox
    private JRadioButton radio_NhhTangdan; // Sort ascending
    private JRadioButton radio_NhhGiamdan; // Sort descending
	private JTable table;

    public NhanVien() {
        connectDB.accessDataBase(); // Connect to database
        nv_dao = new NhanVien_DAO(); // Initialize DAO

        setPreferredSize(new Dimension(1500, 800));
        setLayout(new BorderLayout(0, 0));

        JPanel pnContent = createContentPanel(); // Create content panel
        add(pnContent, BorderLayout.CENTER);

        // Load data into table
        loadNhanVienData();
    }

    // Open add employee dialog
    private void openAddDialog() {
        themNV themNhanVienFrame = new themNV();
        themNhanVienFrame.setLocationRelativeTo(this); // Center the dialog on the screen
        themNhanVienFrame.setVisible(true);
    }
    private void openUpdateDialog() {
        CapNhatNhanVien capNhatNVFrame = new CapNhatNhanVien(); // Sửa kiểu và tên biến
        capNhatNVFrame.setLocationRelativeTo(this); // Căn giữa hộp thoại trên màn hình
        capNhatNVFrame.setVisible(true);
    }
    private JPanel createContentPanel() {
        JPanel pnContent = new JPanel();
        pnContent.setBackground(Color.WHITE);
        pnContent.setLayout(new BorderLayout(0, 0));
        pnContent.add(createCenterPanel(), BorderLayout.CENTER);
        return pnContent;
    }

    private JPanel createCenterPanel() {
        JPanel pnCenter = new JPanel();
        pnCenter.setBackground(Color.WHITE);
        pnCenter.setLayout(null);

        // Table setup
        String[] columnNames = {"Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Địa chỉ", "Số điện thoại","Email", "CCCD", "Ngày vào làm", "Trạng thái", "Giới tính", "Hình ảnh", "Mã loại NV"};
        dftb_NhanVien = new DefaultTableModel(columnNames, 0);
        tb_NhanVien = new JTable(dftb_NhanVien);
        //tb_NhanVien.setBackground(Color.WHITE);
        
     // Đổi màu nền tiêu đề thành màu RGB(11, 101, 136) và màu chữ thành trắng
        tb_NhanVien.getTableHeader().setBackground(new Color(11, 101, 136));  // Nền màu RGB(11, 101, 136)
        tb_NhanVien.getTableHeader().setForeground(Color.WHITE);  // Chữ màu trắng

        tb_NhanVien.setRowHeight(60);
        tb_NhanVien.getTableHeader().setReorderingAllowed(false);

        JScrollPane scp_NhanVien = new JScrollPane(tb_NhanVien);
        scp_NhanVien.setBounds(0, 22, 1100, 688);
        pnCenter.add(scp_NhanVien);



//        // Thêm MouseListener để mở thông tin chi tiết nhân viên khi nhấp đúp
//        tb_NhanVien.addMouseListener(new MouseAdapter() {
//            @Override
//            public void XemThongTinNhanVien(MouseEvent e) {
//                int selectedRow = tb_NhanVien.getSelectedRow();
//                
//                if (selectedRow != -1) {
//                    // Retrieve data from the selected row
//                    String maNV = tb_NhanVien.getValueAt(selectedRow, 0).toString();
//                    String hoTenNV = tb_NhanVien.getValueAt(selectedRow, 1).toString();
//                    String ngaySinh = tb_NhanVien.getValueAt(selectedRow, 2).toString();  // Convert LocalDate to String if needed
//                    String diaChi = tb_NhanVien.getValueAt(selectedRow, 3).toString();
//                   String sdt = tb_NhanVien.getValueAt(selectedRow, 4).toString();
//                    String cccd = tb_NhanVien.getValueAt(selectedRow, 5).toString();
//                    String ngayVaoLam = tb_NhanVien.getValueAt(selectedRow, 6).toString(); // Convert LocalDate to String if needed
//                    boolean trangThai = (boolean) tb_NhanVien.getValueAt(selectedRow, 7);  // Assuming this is a boolean
//                    String gioiTinh = tb_NhanVien.getValueAt(selectedRow, 8).toString();
//                    String hinhAnhNV = tb_NhanVien.getValueAt(selectedRow, 9).toString();
//                    String maLoaiNV = tb_NhanVien.getValueAt(selectedRow, 10).toString();
//
//                    // Open the XemThongTinNhanVien frame with the selected employee's details
//                    new XemThongTinNhanVien(maNV, hoTenNV, ngaySinh, diaChi, sdt, cccd, ngayVaoLam, trangThai, gioiTinh, hinhAnhNV, maLoaiNV);
//                }
//            }
//        });

        
        // Filter panel
        pnCenter.add(createFilterPanel());

        // Initialize search field
        textField_tim =  new PlaceholderTextField("");
        // set placeholder
        textField_tim.setPlaceholder("Nhập SĐT để tìm kiếm...");
        textField_tim.setForeground(Color.GRAY);
        textField_tim.setColumns(10);
        textField_tim.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        textField_tim.setBounds(1130, 451, 179, 27);
        textField_tim.setVisible(false);  // Initially hidden
        textField_tim.addActionListener(e -> searchNhanVien());
        pnCenter.add(textField_tim);

        // Search button
        btn_tim = new JButton("Tìm");
        btn_tim.setBounds(1318, 453, 89, 23);
        btn_tim.addActionListener(e -> searchNhanVien());
        btn_tim.setVisible(false);
        pnCenter.add(btn_tim);

        // Add action buttons
//        JButton btnNewButton = new JButton("Xem");
//        btnNewButton.setBounds(1130, 363, 276, 39);
//        btnNewButton.addActionListener(e -> {
//            textField_tim.setVisible(true);
//            btn_tim.setVisible(true);
//        });
//        pnCenter.add(btnNewButton);
       JButton btnNewButton = new JButton("Xem");
       btnNewButton.setBounds(1130, 363, 276, 39);
       btnNewButton.addActionListener(e -> {
    	   textField_tim.setVisible(true);
         btn_tim.setVisible(true);
    	    // Kiểm tra xem người dùng đã chọn hàng trong bảng chưa
    	    int selectedRow = tb_NhanVien.getSelectedRow(); // Sử dụng tb_NhanVien thay vì table
    	    if (selectedRow != -1) {
    	        // Lấy dữ liệu từ hàng được chọn
    	        String maNV = tb_NhanVien.getValueAt(selectedRow, 0).toString(); // Cột 0: Mã nhân viên
    	        String hoTenNV = tb_NhanVien.getValueAt(selectedRow, 1).toString(); // Cột 1: Họ tên
    	        String ngaySinh = tb_NhanVien.getValueAt(selectedRow, 2).toString(); // Cột 2: Ngày sinh
    	        String diaChi = tb_NhanVien.getValueAt(selectedRow, 3).toString(); // Cột 3: Địa chỉ
    	        String sdt = tb_NhanVien.getValueAt(selectedRow, 4).toString(); // Cột 4: SĐT
    	        String email = tb_NhanVien.getValueAt(selectedRow, 5).toString(); // Cột 5: Email
    	        String cccd = tb_NhanVien.getValueAt(selectedRow, 6).toString(); // Cột 6: CCCD
    	        String ngayVaoLam = tb_NhanVien.getValueAt(selectedRow, 7).toString(); // Cột 7: Ngày vào làm
    	        Boolean trangThai = (Boolean) tb_NhanVien.getValueAt(selectedRow, 8); // Cột 8: Trạng thái
    	        String gioiTinh = tb_NhanVien.getValueAt(selectedRow, 9).toString(); // Cột 9: Giới tính
    	        String hinhAnhNV = tb_NhanVien.getValueAt(selectedRow, 10).toString(); // Cột 10: Hình ảnh
    	        String maLoaiNV = tb_NhanVien.getValueAt(selectedRow, 11).toString(); // Cột 11: Mã loại NV

    	        // Hiển thị cửa sổ chi tiết
    	       // new XemThongTinNhanVien(maNV, hoTenNV, ngaySinh, diaChi, sdt, email, cccd, ngayVaoLam, trangThai, gioiTinh, hinhAnhNV, maLoaiNV);
    	        new XemThongTinNhanVien(maNV, hoTenNV, ngaySinh, diaChi, sdt, email, cccd, trangThai, ngayVaoLam, gioiTinh, hinhAnhNV, maLoaiNV); }
    	        else {
    	        // Thông báo nếu chưa chọn hàng
    	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên!", "Thông báo", JOptionPane.WARNING_MESSAGE);
    	    }
    	});
       pnCenter.add(btnNewButton);

        JButton btn_them = new JButton("Thêm");
        btn_them.setBounds(1130, 413, 89, 23);
        btn_them.addActionListener(e -> openAddDialog());
        pnCenter.add(btn_them);

        JButton btnXoa = new JButton("Xóa");
        btnXoa.setBounds(1221, 413, 89, 23);
        btnXoa.addActionListener(e -> {
            textField_tim.setVisible(true);
            btn_tim.setVisible(true);
            });
        pnCenter.add(btnXoa);

        JButton btnSua = new JButton("Sửa");
        btnSua.setBounds(1318, 413, 89, 23);
        btnSua.addActionListener(e -> {
        textField_tim.setVisible(true);
        btn_tim.setVisible(true);
       
     	    // Kiểm tra xem người dùng đã chọn hàng trong bảng chưa
     	    int selectedRow = tb_NhanVien.getSelectedRow(); // Sử dụng tb_NhanVien thay vì table
     	    if (selectedRow != -1) {
     	        // Lấy dữ liệu từ hàng được chọn
     	        String maNV = tb_NhanVien.getValueAt(selectedRow, 0).toString(); // Cột 0: Mã nhân viên
     	        String hoTenNV = tb_NhanVien.getValueAt(selectedRow, 1).toString(); // Cột 1: Họ tên
     	        String ngaySinh = tb_NhanVien.getValueAt(selectedRow, 2).toString(); // Cột 2: Ngày sinh
     	        String diaChi = tb_NhanVien.getValueAt(selectedRow, 3).toString(); // Cột 3: Địa chỉ
     	        String sdt = tb_NhanVien.getValueAt(selectedRow, 4).toString(); // Cột 4: SĐT
     	        String email = tb_NhanVien.getValueAt(selectedRow, 5).toString(); // Cột 5: Email
     	        String cccd = tb_NhanVien.getValueAt(selectedRow, 6).toString(); // Cột 6: CCCD
     	        String ngayVaoLam = tb_NhanVien.getValueAt(selectedRow, 7).toString(); // Cột 7: Ngày vào làm
     	        Boolean trangThai = (Boolean) tb_NhanVien.getValueAt(selectedRow, 8); // Cột 8: Trạng thái
     	        String gioiTinh = tb_NhanVien.getValueAt(selectedRow, 9).toString(); // Cột 9: Giới tính
     	        String hinhAnhNV = tb_NhanVien.getValueAt(selectedRow, 10).toString(); // Cột 10: Hình ảnh
     	        String maLoaiNV = tb_NhanVien.getValueAt(selectedRow, 11).toString(); // Cột 11: Mã loại NV

     	        // Hiển thị cửa sổ chi tiết
     	       // new XemThongTinNhanVien(maNV, hoTenNV, ngaySinh, diaChi, sdt, email, cccd, ngayVaoLam, trangThai, gioiTinh, hinhAnhNV, maLoaiNV);
     	       new CapNhatNhanVien(maNV, hoTenNV, ngaySinh, diaChi, sdt, email, cccd, ngayVaoLam, trangThai, gioiTinh, hinhAnhNV, maLoaiNV).setVisible(true);
 }
     	        else {
     	        // Thông báo nếu chưa chọn hàng
     	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên!", "Thông báo", JOptionPane.WARNING_MESSAGE);
     	    }
     	});
       
        pnCenter.add(btnSua);
        
        

        JButton btn_reset = new JButton("Reset");
        btn_reset.setBounds(1220, 506, 146, 23);
        btn_reset.addActionListener(e -> loadNhanVienData());
        pnCenter.add(btn_reset);

        return pnCenter;
    }

    private JPanel createFilterPanel() {
        JPanel pnLoc = new JPanel();
        pnLoc.setBackground(Color.WHITE);
        pnLoc.setBorder(new TitledBorder(null, "Lọc Nhân Viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnLoc.setBounds(1120, 22, 300, 341);
        pnLoc.setLayout(null);

        JLabel lbLocTheoLoai = new JLabel("Loại Nhân Viên");
        lbLocTheoLoai.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lbLocTheoLoai.setBounds(10, 70, 106, 28);
        pnLoc.add(lbLocTheoLoai);

        cb_LocTheoLoai = new JComboBox<>();
        cb_LocTheoLoai.addItem("Tất cả");
        cb_LocTheoLoai.addItem("Quản lý");
        cb_LocTheoLoai.addItem("Nhân viên");
        cb_LocTheoLoai.setBounds(126, 72, 106, 28);
        cb_LocTheoLoai.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        pnLoc.add(cb_LocTheoLoai);

        ButtonGroup group_ten = new ButtonGroup();
        JPanel pn_SapXepTheoGia = new JPanel();
        pn_SapXepTheoGia.setBackground(Color.WHITE);
        pn_SapXepTheoGia.setBorder(new TitledBorder(null, "Sắp Xếp Nhân Viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pn_SapXepTheoGia.setBounds(10, 154, 275, 125);
        pn_SapXepTheoGia.setLayout(null);

        radio_NhhTangdan = new JRadioButton("Tên A - Z");
        radio_NhhTangdan.setSelected(true);
        radio_NhhTangdan.setBounds(6, 16, 109, 23);
        pn_SapXepTheoGia.add(radio_NhhTangdan);
        group_ten.add(radio_NhhTangdan);

        radio_NhhGiamdan = new JRadioButton("Tên Z - A");
        radio_NhhGiamdan.setBounds(6, 48, 109, 23);
        pn_SapXepTheoGia.add(radio_NhhGiamdan);
        group_ten.add(radio_NhhGiamdan);

        pnLoc.add(pn_SapXepTheoGia);

        JButton btn_Loc = new JButton("Lọc");
        btn_Loc.setBounds(10, 288, 275, 39);
        btn_Loc.addActionListener(e -> filterNhanVien());
        pnLoc.add(btn_Loc);

        return pnLoc;
    }

    private void filterNhanVien() {
        // Lấy giá trị được chọn từ ComboBox
        String selectedLoai = (String) cb_LocTheoLoai.getSelectedItem();
        
        // Xóa dữ liệu hiện tại trong bảng
        dftb_NhanVien.setRowCount(0);
        
        // Gọi phương thức DAO để lấy danh sách nhân viên phù hợp
        List<NhanVien_entity> filteredNhanVienList;
        
        if (selectedLoai.equals("Tất cả")) {
            // Lấy tất cả nhân viên nếu chọn "Tất cả"
            filteredNhanVienList = nv_dao.getAllNhanVien();
        } else {
            // Lấy danh sách nhân viên theo loại (Quản lý hoặc Nhân viên)
            filteredNhanVienList = nv_dao.getNhanVienTheoLoai(selectedLoai, selectedLoai);
        }

        // Đổ dữ liệu vào bảng
        for (NhanVien_entity nv : filteredNhanVienList) {
            dftb_NhanVien.addRow(new Object[]{
                nv.getMaNV(),
                nv.getHoTenNV(),
                nv.getNgaySinh(),
                nv.getDiaChi(),
                nv.getSdt(),
                nv.getEmail(),
                nv.getCccd(),
                nv.getNgayVaoLam(),
                nv.isTrangThai(),
                nv.getGioiTinh(),
                nv.getHinhAnhNV(),
                nv.getMaLoaiNV()
            });
        }

        // Hiển thị thông báo nếu không có dữ liệu nào được tìm thấy
        if (filteredNhanVienList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên nào phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }


	private void loadNhanVienData() {
        dftb_NhanVien.setRowCount(0);
        List<NhanVien_entity> dsNhanVien = nv_dao.getAllNhanVien();

        for (NhanVien_entity nv : dsNhanVien) {
            dftb_NhanVien.addRow(new Object[]{
                nv.getMaNV(),
                nv.getHoTenNV(),
                nv.getNgaySinh(),
                nv.getDiaChi(),
                nv.getSdt(),
                nv.getEmail(),
                nv.getCccd(),
                nv.getNgayVaoLam(),
                nv.isTrangThai(),
                nv.getGioiTinh(),
                nv.getHinhAnhNV(),
                nv.getMaLoaiNV()
            });
        }
    }


	private void searchNhanVien() {
	    String searchText = textField_tim.getText().trim();

	    if (searchText.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại hoặc tên để tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
	        return;  // Exit method if no search text is entered
	    }

	    // Search for employee by phone number or name
	    List<NhanVien_entity> nhanVienList = nv_dao.timKiemNhanVienSDT(searchText); // Use search method in NhanVien_DAO
	    dftb_NhanVien.setRowCount(0); // Clear current data in the table

	    for (NhanVien_entity nv : nhanVienList) {
	        dftb_NhanVien.addRow(new Object[]{
	            nv.getMaNV(),
	            nv.getHoTenNV(),
	            nv.getNgaySinh(),
	            nv.getDiaChi(),
	            nv.getSdt(),
	            nv.getEmail(),
	            nv.getCccd(),
	            nv.getNgayVaoLam(),
	            nv.isTrangThai(),
	            nv.getGioiTinh(),
	            nv.getHinhAnhNV(),
	            nv.getMaLoaiNV()
	        });
	    }

	    // Display a message if no matching employee is found
	    if (nhanVienList.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Không tìm thấy số điện thoại phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    }
	

	


	    // Kiểm tra nếu chỉ có một kết quả được tìm thấy
	    if (nhanVienList.size() == 1) {
	        NhanVien_entity nv = nhanVienList.get(0);
	        // Mở frame XemThongTinNhanVien với thông tin của nhân viên
	        new CapNhatNhanVien(
	            nv.getMaNV(),
	            nv.getHoTenNV(),
	            nv.getNgaySinh().toString(),  // Chuyển LocalDate sang String nếu cần
	            nv.getDiaChi(),
	            nv.getSdt(),
	            nv.getEmail(),
	            nv.getCccd(),
	            nv.getNgayVaoLam().toString(), // Chuyển LocalDate sang String nếu cần
	            nv.isTrangThai(),
	            nv.getGioiTinh(),
	            nv.getHinhAnhNV(),
	            nv.getMaLoaiNV()
	        ).setVisible(true);
	    }
	}


//    private void searchNhanVien() {
//        String searchText = textField_tim.getText().trim();
//
//        if (searchText.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại hoặc tên để tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
//           return;  // Exit the method if empty
//        }
//
//        // Tìm kiếm nhân viên theo số điện thoại
//        List<NhanVien_entity> nhanVienList = nv_dao.timKiemNhanVienSDT(searchText); // Cập nhật phương thức timKiemNhanVien trong NhanVien_DAO
//        dftb_NhanVien.setRowCount(0); // Clear the current table
//        for (NhanVien_entity nv : nhanVienList) {
//            dftb_NhanVien.addRow(new Object[]{
//                nv.getMaNV(),
//                nv.getHoTenNV(),
//                nv.getNgaySinh(),
//                nv.getDiaChi(),
//                nv.getSdt(),
//                nv.getCccd(),
//                nv.getNgayVaoLam(),
//                nv.isTrangThai(),
//                nv.getGioiTinh(),
//                nv.getHinhAnhNV(),
//                nv.getMaLoaiNV()
//            });
//        }
//    }
//    
    
    
    private void deleteNhanVien() {
        int selectedRow = tb_NhanVien.getSelectedRow();
        if (selectedRow != -1) {
            String maNV = (String) dftb_NhanVien.getValueAt(selectedRow, 0);
            nv_dao.deleteNhanVien(maNV); // Assuming there's a method to delete
            loadNhanVienData(); // Reload data after deletion
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateNhanVien() {
        int selectedRow = tb_NhanVien.getSelectedRow();
        if (selectedRow != -1) {
            String maNV = (String) dftb_NhanVien.getValueAt(selectedRow, 0);
//             Open update dialog and pass employee ID
//             themNV themNhanVienFrame = new themNV(maNV);
//             themNhanVienFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void XemThongTinNhanVien() {
        int selectedRow = tb_NhanVien.getSelectedRow();
        if (selectedRow != -1) {
            String maNV = (String) dftb_NhanVien.getValueAt(selectedRow, 0);
//             Open update dialog and pass employee ID
//             themNV themNhanVienFrame = new themNV(maNV);
//             themNhanVienFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để xem!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle action events if needed
    }
}
