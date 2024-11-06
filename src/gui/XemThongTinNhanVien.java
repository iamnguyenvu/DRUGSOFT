package gui;

import dao.NhanVien_DAO;
import entity.NhanVien_entity;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class XemThongTinNhanVien extends JFrame {
    private static final long serialVersionUID = 1L;

    // Define maximum lengths based on database schema
    private static final int MAX_MA_NV = 10;
    private static final int MAX_HO_TEN = 100;
    private static final int MAX_GIOI_TINH = 10;
    private static final int MAX_SDT = 15;
    private static final int MAX_CCCD = 20;
    private static final int MAX_DIA_CHI = 200;
    private static final int MAX_HINH_ANH = 255;
    private static final int MAX_MA_LOAI_NV = 10;

    private JTextField txtFullName, txtAddress, txtBirthDate, txtJoinDate, txtPhone, txtStatus, txtCCCD, employeeType, txtMaNV;
    private JButton btnClose;
    private JLabel lblPhoto;
    private JRadioButton rbtnMale, rbtnFemale;

    private NhanVien_DAO nv_DAO;

    public XemThongTinNhanVien(String maNV, String hoTenNV, String ngaySinh, String diaChi, String sdt, String cccd,
                               String ngayVaoLam, boolean trangThai, String gioiTinh, String hinhAnhNV, String maLoaiNV) {
        setTitle("Thông Tin Nhân Viên");
        setSize(915, 702);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());

        nv_DAO = new NhanVien_DAO(); // Initialize DAO

        JPanel searchPanel = createSearchPanel();
        JPanel formPanel = createFormPanel(maNV, hoTenNV, ngaySinh, diaChi, sdt, cccd, ngayVaoLam, trangThai, gioiTinh, hinhAnhNV, maLoaiNV);

        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().add(formPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBackground(new Color(70, 130, 180));
        searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblSearchHeader = new JLabel("XEM CHI TIẾT NHÂN VIÊN");
        lblSearchHeader.setFont(new Font("Arial", Font.BOLD, 22));
        lblSearchHeader.setForeground(Color.WHITE);
        lblSearchHeader.setAlignmentX(Component.CENTER_ALIGNMENT);

        searchPanel.add(lblSearchHeader);
        return searchPanel;
    }

    private JPanel createFormPanel(String maNV, String hoTenNV, String ngaySinh, String diaChi, String sdt, String cccd,
                                   String ngayVaoLam, boolean trangThai, String gioiTinh, String hinhAnhNV, String maLoaiNV) {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.WHITE);

        // Photo Label
        lblPhoto = new JLabel();
        lblPhoto.setBounds(227, 39, 132, 137);
        lblPhoto.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        formPanel.add(lblPhoto);

        // Initialize Text Fields
        txtMaNV = createTextField(128, 200, MAX_MA_NV);
        txtMaNV.setEditable(false);
        txtFullName = createTextField(128, 300, MAX_HO_TEN);
        txtBirthDate = createTextField(128, 450, 10); // Adjust as needed
        txtJoinDate = createTextField(128, 350, 10); // Adjust as needed
        txtAddress = createTextField(560, 250, MAX_DIA_CHI);
        txtPhone = createTextField(560, 300, MAX_SDT);
        txtStatus = createTextField(128, 400, 20); // Adjust as needed
        txtCCCD = createTextField(560, 400, MAX_CCCD);
        employeeType = createTextField(560, 350, MAX_MA_LOAI_NV);

        addComponentsToFormPanel(formPanel);

        // Close Button
        btnClose = new JButton("Đóng");
        btnClose.setBounds(680, 528, 150, 50);
        btnClose.setBackground(new Color(220, 53, 69));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("Arial", Font.BOLD, 14));
        btnClose.addActionListener(e -> dispose());
        formPanel.add(btnClose);

//        // Update Button
//        btnCapNhat = new JButton("Cập nhật");
//        btnCapNhat.setBounds(680, 450, 150, 50);
//        btnCapNhat.setBackground(new Color(40, 167, 69));
//        btnCapNhat.setForeground(Color.WHITE);
//        btnCapNhat.setFont(new Font("Arial", Font.BOLD, 14));
//        btnCapNhat.addActionListener(new UpdateActionListener());
//        formPanel.add(btnCapNhat);

        // Load Data into Fields
        loadData(maNV, hoTenNV, ngaySinh, diaChi, sdt, cccd, ngayVaoLam, trangThai, gioiTinh, hinhAnhNV, maLoaiNV);

        return formPanel;
    }

    private JTextField createTextField(int x, int y, int maxLength) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 270, 40);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        // Optional: Add a DocumentFilter to enforce maxLength
        return textField;
    }

    private void addComponentsToFormPanel(JPanel formPanel) {
        // Labels and Inputs
        formPanel.add(createLabel("Giới tính:", 34, 250));
        rbtnMale = new JRadioButton("Nam");
        rbtnFemale = new JRadioButton("Nữ");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbtnMale);
        genderGroup.add(rbtnFemale);
        rbtnMale.setBounds(150, 250, 60, 40);
        rbtnFemale.setBounds(220, 250, 60, 40);
        formPanel.add(rbtnMale);
        formPanel.add(rbtnFemale);

        formPanel.add(createLabel("Mã NV:", 34, 200));
        formPanel.add(txtMaNV);

        formPanel.add(createLabel("Họ và tên:", 34, 300));
        formPanel.add(txtFullName);

        formPanel.add(createLabel("Ngày sinh:", 34, 450));
        formPanel.add(txtBirthDate);

        formPanel.add(createLabel("Ngày vào làm:", 34, 350));
        formPanel.add(txtJoinDate);

        formPanel.add(createLabel("Địa chỉ:", 480, 250));
        formPanel.add(txtAddress);

        formPanel.add(createLabel("SĐT:", 480, 300));
        formPanel.add(txtPhone);

        formPanel.add(createLabel("Trạng thái:", 34, 400));
        formPanel.add(txtStatus);

        formPanel.add(createLabel("Mã Loại NV:", 480, 350));
        formPanel.add(employeeType);

        formPanel.add(createLabel("CCCD:", 480, 400));
        formPanel.add(txtCCCD);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 120, 40);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

    private void loadData(String maNV, String hoTenNV, String ngaySinh, String diaChi, String sdt, String cccd,
                          String ngayVaoLam, boolean trangThai, String gioiTinh, String hinhAnhNV, String maLoaiNV) {
        txtMaNV.setText(maNV);
        txtFullName.setText(hoTenNV);
        txtBirthDate.setText(ngaySinh);
        txtJoinDate.setText(ngayVaoLam);
        txtAddress.setText(diaChi);
        txtPhone.setText(sdt);
        txtStatus.setText(trangThai ? "Đang làm" : "Nghỉ việc");
        txtCCCD.setText(cccd);
        employeeType.setText(maLoaiNV.equals("NV") ? "NV - Nhân viên" : "QL - Quản lý");

        if ("Nam".equalsIgnoreCase(gioiTinh)) {
            rbtnMale.setSelected(true);
        } else {
            rbtnFemale.setSelected(true);
        }

        loadImage(hinhAnhNV);
    }

    private void loadImage(String imagePath) {
        try {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image originalImage = imageIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(132, 137, Image.SCALE_SMOOTH);
            lblPhoto.setIcon(new ImageIcon(resizedImage));
        } catch (Exception e) {
            lblPhoto.setIcon(null);
            System.err.println("Error loading image: " + e.getMessage());
        }
    }

    // Helper method to trim strings to a maximum length
    private String trimToLength(String input, int maxLength) {
        if (input == null) return null;
        return input.length() > maxLength ? input.substring(0, maxLength) : input;
    }

    private class UpdateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Collecting input data
            String fullName = txtFullName.getText();
            String birthDateStr = txtBirthDate.getText();
            String joinDateStr = txtJoinDate.getText();
            String address = txtAddress.getText();
            String phone = txtPhone.getText();
            String cccd = txtCCCD.getText();
            String maLoaiNV = (String) employeeType.getText();
            String hinhAnhNV = lblPhoto.getIcon() != null ? lblPhoto.getIcon().toString() : null; // Assuming lblPhoto holds the image

            // Gender check
            String gioiTinh = null;
            if (rbtnMale.isSelected()) {
                gioiTinh = "Nam"; // Male
            } else if (rbtnFemale.isSelected()) {
                gioiTinh = "Nữ"; // Female
            }
            // Assuming `maNV` is the unique identifier for the employee to be updated
            String maNV = txtMaNV.getText(); // Get maNV from the input field or elsewhere

            // Parsing dates
            LocalDate birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate joinDate = LocalDate.parse(joinDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Creating employee entity
            NhanVien_entity employee = new NhanVien_entity();
            employee.setMaNV(maNV);
            employee.setHoTenNV(fullName);
            employee.setGioiTinh(gioiTinh); // Set gender
            employee.setNgaySinh(birthDate);
            employee.setNgayVaoLam(joinDate);
            employee.setDiaChi(address);
            employee.setSdt(phone);
            employee.setCccd(cccd);
            employee.setTrangThai(true);
            employee.setMaLoaiNV(maLoaiNV.substring(0, 2)); // Get the first 2 characters (e.g., NV or QL)
            employee.setHinhAnhNV(hinhAnhNV);

            // DAO to update employee
            NhanVien_DAO dao = new NhanVien_DAO();
            dao.updateNhanVien(employee);
          
        }
    }

    // Methods to enable or disable editing fields
    private void moDaTa() {
        txtFullName.setEditable(true);
        txtAddress.setEditable(true);
        txtBirthDate.setEditable(true);
        txtJoinDate.setEditable(true);
        txtPhone.setEditable(true);
        txtStatus.setEditable(true);
        txtCCCD.setEditable(true);
        employeeType.setEditable(true);
        rbtnFemale.setEnabled(true);
        rbtnMale.setEnabled(true);
    }

    private void khoaDaTa() {
        txtFullName.setEditable(false);
        txtAddress.setEditable(false);
        txtBirthDate.setEditable(false);
        txtJoinDate.setEditable(false);
        txtPhone.setEditable(false);
        txtStatus.setEditable(false);
        txtCCCD.setEditable(false);
        employeeType.setEditable(false);
        rbtnFemale.setEnabled(false);
        rbtnMale.setEnabled(false);
    }

}
