package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

import connectDB.connectDB;
import dao.NhanVien_DAO;
import entity.NhanVien_entity;

public class themNV extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtFullName, txtAddress,
            txtPhone, txtCCCD, txtEmail;
    private JComboBox<String> cboEmployeeType; // maLoaiNV dropdown
    private Map<String, Integer> statusMap;

    private JButton btnSave, btnUploadImage; // Added button for image upload
    private JLabel lblPhoto; // Added label for displaying employee ID
    private JRadioButton rbtnMale, rbtnFemale; // Gender radio buttons
    private JDateChooser birthDateChooser; // Khai báo JDateChooser
    private JDateChooser joinDateChooser; // Khai báo JDateChooser

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new themNV().setVisible(true);
        });
    }

    public themNV() {
        setSize(915, 702);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBackground(new Color(70, 130, 180));
        JLabel lblSearchHeader = new JLabel("THÊM NHÂN VIÊN");
        lblSearchHeader.setFont(new Font("Arial", Font.BOLD, 22));
        lblSearchHeader.setForeground(Color.WHITE);
        lblSearchHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(lblSearchHeader);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.WHITE);

        lblPhoto = new JLabel(); // Initialize the label for photo display
        lblPhoto.setBounds(400, 39, 132, 137);
        lblPhoto.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        formPanel.add(lblPhoto);

        // Upload Image Button
        btnUploadImage = new JButton("Tải ảnh");
        btnUploadImage.setBackground(new Color(70, 130, 180));
        btnUploadImage.setBounds(400, 185, 132, 30);
        btnUploadImage.addActionListener(this::uploadImage);
        formPanel.add(btnUploadImage);

        // Defining text fields
        txtFullName = createTextField(128, 300, 270, 40);
        // txtBirthDate = createTextField(128, 450, 270, 40);
        birthDateChooser = new JDateChooser();
        birthDateChooser.setBounds(128, 400, 270, 40); // Vị trí và kích thước cho JDateChooser
        birthDateChooser.setDateFormatString("dd/MM/yyyy"); // Định dạng ngày

        Calendar maxDate = Calendar.getInstance();

        joinDateChooser = new JDateChooser();
        joinDateChooser.setBounds(128, 350, 270, 40); // Vị trí và kích thước cho JDateChooser
        joinDateChooser.setDateFormatString("dd/MM/yyyy"); // Định dạng ngày
        joinDateChooser.setMaxSelectableDate(maxDate.getTime());

        txtAddress = createTextField(560, 250, 270, 40);
        txtPhone = createTextField(560, 300, 270, 40);
        txtEmail = createTextField(560, 450, 270, 40);
        txtCCCD = createTextField(560, 400, 270, 40);

        // ComboBox for maLoaiNV (employee type)
        cboEmployeeType = new JComboBox<>(new String[] { "NV - Nhân viên", "QL - Quản lý" });
        cboEmployeeType.setBounds(560, 350, 270, 40);
        cboEmployeeType.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(cboEmployeeType);

        // Create Gender Label
        formPanel.add(createLabel("Giới tính:", 34, 250, 84, 40));

        // Create Radio Buttons for "Nam" (Male) and "Nữ" (Female)
        rbtnMale = new JRadioButton("Nam");
        rbtnFemale = new JRadioButton("Nữ");

        // Set bounds for each radio button
        rbtnMale.setBounds(150, 250, 60, 40); // Adjust coordinates as necessary
        rbtnFemale.setBounds(220, 250, 60, 40);

        // Labels for employee information
        formPanel.add(createLabel("Họ và tên:", 34, 300, 84, 40));
        formPanel.add(txtFullName);
        formPanel.add(createLabel("Ngày sinh:", 34, 400, 100, 40));
        formPanel.add(birthDateChooser);

        formPanel.add(createLabel("Ngày vào làm:", 34, 350, 100, 40));
        formPanel.add(joinDateChooser);

        formPanel.add(createLabel("Địa chỉ:", 480, 250, 100, 40));
        formPanel.add(txtAddress);
        formPanel.add(createLabel("SĐT:", 480, 300, 100, 40));
        formPanel.add(txtPhone);
        
        formPanel.add(createLabel("Email:", 480, 450, 100, 40));
        formPanel.add(txtEmail);

        statusMap = new HashMap<>();
        statusMap.put("Đang làm", 1);
        statusMap.put("Đã nghỉ việc", 0);

        formPanel.add(createLabel("Mã Loại NV:", 480, 350, 100, 40));
        formPanel.add(cboEmployeeType);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbtnMale);
        genderGroup.add(rbtnFemale);

        // Add the radio buttons to the form panel
        formPanel.add(rbtnMale);
        formPanel.add(rbtnFemale);
        formPanel.add(createLabel("CCCD:", 480, 400, 100, 40));
        formPanel.add(txtCCCD);

        // Save button
        btnSave = new JButton("Lưu");
        btnSave.setBounds(680, 550, 140, 45);
        btnSave.setBackground(new Color(11, 101, 136));
        btnSave.setForeground(Color.WHITE); // Để chữ trên nút dễ nhìn
        btnSave.setFont(new Font("Arial", Font.BOLD, 18));
        formPanel.add(btnSave);
//        SU KIEN 
        btnSave.addActionListener(e -> saveEmployee(e));

        btnUploadImage.setBackground(new Color(11, 101, 136));
        btnUploadImage.setForeground(Color.WHITE);
        btnUploadImage.setFont(new Font("Arial", Font.BOLD, 14));

        // Adding panels to the main frame
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().add(formPanel, BorderLayout.CENTER);

    }

    // Method to create text fields
    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }
   // private String email;
    // Method to create labels
    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

    private String hinhAnhNV; // Add this to your class to store the image path or data

    private void uploadImage(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Images", "jpg", "jpeg", "png"));
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(selectedFile.getPath());

            // Resize the image to fit the label dimensions
            Image scaledImage = imageIcon.getImage().getScaledInstance(lblPhoto.getWidth(), lblPhoto.getHeight(),
                    Image.SCALE_SMOOTH);
            lblPhoto.setIcon(new ImageIcon(scaledImage));

            hinhAnhNV = selectedFile.getPath(); // Store the image path or convert to byte array

        }
    }
/*
    private void saveEmployee(ActionEvent event) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Collecting input data
        String fullName = txtFullName.getText();
        String birthDateStr = birthDateChooser.getDate() != null ? dateFormat.format(birthDateChooser.getDate()) : "";
        String joinDateStr = joinDateChooser.getDate() != null ? dateFormat.format(joinDateChooser.getDate()) : "";
        String address = txtAddress.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String cccd = txtCCCD.getText();
        String maLoaiNV = (String) cboEmployeeType.getSelectedItem();

        // Gender check
        String gioiTinh;
        if (rbtnMale.isSelected()) {
            gioiTinh = "Nam"; // Male
        } else if (rbtnFemale.isSelected()) {
            gioiTinh = "Nữ"; // Female
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính!");
            return; // Exit the method if no gender is selected
        }

        // Validate input fields
        if (!isValidInput(fullName, birthDateStr, joinDateStr, address, phone,email, cccd)) {
            return; // Exit if validation fails
        }

        if (hinhAnhNV == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ảnh!");
            return;
        }

        // Generate maNV
        String maNV = generateMaNV();

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
        employee.setEmail(email);
        employee.setCccd(cccd);
        employee.setTrangThai(true);
        employee.setMaLoaiNV(maLoaiNV.substring(0, 2)); // Get the first 2 characters (e.g., NV or QL)
        employee.setHinhAnhNV(hinhAnhNV);
       
        // DAO to save employee
        NhanVien_DAO dao = new NhanVien_DAO();
        if (dao.saveEmployee(employee)) {
            JOptionPane.showMessageDialog(this, "Lưu nhân viên thành công!");
            clearFields(); // Clear fields after saving
        } else {
            JOptionPane.showMessageDialog(this, "Lưu nhân viên thất bại!");
        }
    }

    */
    private void saveEmployee(ActionEvent event) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Lấy thông tin nhập từ giao diện
        String fullName = txtFullName.getText();
        String birthDateStr = birthDateChooser.getDate() != null ? dateFormat.format(birthDateChooser.getDate()) : "";
        String joinDateStr = joinDateChooser.getDate() != null ? dateFormat.format(joinDateChooser.getDate()) : "";
        String address = txtAddress.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String cccd = txtCCCD.getText();
        String maLoaiNV = (String) cboEmployeeType.getSelectedItem();

        // Gender check
        String gioiTinh;
        if (rbtnMale.isSelected()) {
            gioiTinh = "Nam"; // Male
        } else if (rbtnFemale.isSelected()) {
            gioiTinh = "Nữ"; // Female
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính!");
            return; // Exit the method if no gender is selected
        }

        // Validate input fields
        if (!isValidInput(fullName, birthDateStr, joinDateStr, address, phone, email, cccd)) {
            return; // Exit if validation fails
        }

        if (hinhAnhNV == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ảnh!");
            return;
        }

        // Generate maNV
        String maNV = generateMaNV();

        // Parsing dates
        LocalDate birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate joinDate = LocalDate.parse(joinDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Default TrangThai value (if not selected, set to "Đang làm")
        boolean trangThai = true; // Default to "Đang làm" (Active)

        // Creating employee entity
        NhanVien_entity employee = new NhanVien_entity();
        employee.setMaNV(maNV);
        employee.setHoTenNV(fullName);
        employee.setGioiTinh(gioiTinh); // Set gender
        employee.setNgaySinh(birthDate);
        employee.setNgayVaoLam(joinDate);
        employee.setDiaChi(address);
        employee.setSdt(phone);
        employee.setEmail(email);
        employee.setCccd(cccd);
        employee.setTrangThai(trangThai); // Ensure TrangThai is not null
        employee.setMaLoaiNV(maLoaiNV.substring(0, 2)); // Get the first 2 characters (e.g., NV or QL)
        employee.setHinhAnhNV(hinhAnhNV);

        // DAO to save employee
        NhanVien_DAO dao = new NhanVien_DAO();
        if (dao.saveEmployee(employee)) {
            JOptionPane.showMessageDialog(this, "Lưu nhân viên thành công!");
            clearFields(); // Clear fields after saving
        } else {
            JOptionPane.showMessageDialog(this, "Lưu nhân viên thất bại!");
        }
    }


    private String generateMaNV() {
        String prefix = "NV";
        Date today = new Date();

        String datePart = joinDateChooser.getDate() != null
                ? new SimpleDateFormat("yyMM").format(joinDateChooser.getDate())
                : new SimpleDateFormat("yyMM").format(today);

        Random random = new Random();
        int randomNum = 1000 + random.nextInt(9000);

        return prefix + datePart + randomNum;
    }

    // Validate input fields
    private boolean isValidInput(String fullName, String birthDateStr, String joinDateStr, String address, String phone,
                                  String email, String cccd) {
        // Validate Full Name: It must only contain letters and spaces
        if (fullName.isEmpty() || !Pattern.matches("^[\\p{L}\\s]+$", fullName)) {
            JOptionPane.showMessageDialog(this, "Họ tên không hợp lệ!");
            return false;
        }

        // Validate Birth Date
        if (birthDateStr.isEmpty() || !Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", birthDateStr)) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ!");
            return false;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = dateFormat.parse(birthDateStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ!");
            return false;
        }

        // Validate Join Date
        if (joinDateStr.isEmpty() || !Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", joinDateStr)) {
            JOptionPane.showMessageDialog(this, "Ngày vào làm không hợp lệ!");
            return false;
        }

        // Validate Address (optional, check for non-empty value)
        if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!");
            return false;
        }

        // Validate CCCD (check for valid CCCD number format)
        if (cccd.isEmpty() || !Pattern.matches("^[0-9]{12}$", cccd)) {
            JOptionPane.showMessageDialog(this, "CCCD không hợp lệ!");
            return false;
        }

        // Kiểm tra số điện thoại đã tồn tại trong cơ sở dữ liệu
        String sqlCheckCCCD = "SELECT COUNT(*) FROM NhanVien WHERE cccd = ?";
        try (Connection conn = connectDB.accessDataBase(); 
             PreparedStatement stmt = conn.prepareStatement(sqlCheckCCCD)) {
            stmt.setString(1, phone);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this, "CCCD đã tồn tại!");
                    return false; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi kiểm tra CCCD!");
            
            return false;
        }
        // Validate Phone Number (optional, check for numeric value)
        if (phone.isEmpty() || !Pattern.matches("^[0-9]{10}$", phone)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!");
            return false;
        }
     // Kiểm tra số điện thoại đã tồn tại trong cơ sở dữ liệu
        String sqlCheckPhone = "SELECT COUNT(*) FROM NhanVien WHERE sdt = ?";
        try (Connection conn = connectDB.accessDataBase(); 
             PreparedStatement stmt = conn.prepareStatement(sqlCheckPhone)) {
            stmt.setString(1, phone);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại!");
                    return false; // Không thêm nhân viên nếu số điện thoại đã tồn tại
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi kiểm tra số điện thoại!");
            
            return false;
        }

        // Validate Email
        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không được để trống!");
            return false;
        }

        if (!Pattern.matches("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$", email.trim())) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ! Định dạng đúng: example@domain.com");
            return false;
        }

        return true; // All input is valid
    }

    // Method to clear all fields after successful save
    private void clearFields() {
        // Xóa các trường văn bản
        txtFullName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtCCCD.setText("");
        
        // Đặt lại loại nhân viên
        cboEmployeeType.setSelectedIndex(0);
        
        // Xóa lựa chọn giới tính
        ButtonGroup genderGroup = new ButtonGroup(); // Đảm bảo bạn đã thêm các RadioButton vào ButtonGroup
        genderGroup.add(rbtnMale);
        genderGroup.add(rbtnFemale);
        genderGroup.clearSelection(); // Xóa lựa chọn giới tính
        
        // Đặt lại ngày tháng
        birthDateChooser.setDate(null);
        joinDateChooser.setDate(null);
        
        // Xóa hình ảnh hiển thị
        lblPhoto.setIcon(null); // Đảm bảo bạn có JLabel lblHinhAnh để hiển thị hình ảnh
        hinhAnhNV = null;  // Xóa dữ liệu hình ảnh
    }


        // Validate Email
       
}
