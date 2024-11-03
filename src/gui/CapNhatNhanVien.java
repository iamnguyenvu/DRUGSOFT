package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
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

import com.toedter.calendar.JDateChooser;

import dao.NhanVien_DAO;
import entity.NhanVien_entity;

public class CapNhatNhanVien extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtFullName, txtAddress,
            txtPhone, txtCCCD, txtMaNV;
    private JComboBox<String> cboEmployeeType; // maLoaiNV dropdown
    private JButton btnUploadImage; // Button for image upload
    private JLabel lblPhoto, lblEmployeeId; // Label for displaying employee ID
    private JDateChooser birthDateChooser; // Khai báo JDateChooser
    private JDateChooser joinDateChooser; // Khai báo JDateChooser
    private Map<String, Integer> statusMap;
    private JComboBox<String> cboStatus;
    private JRadioButton rbtnMale, rbtnFemale; // Gender radio buttons
    private JButton btnUpdate;
    private String hinhAnhNV;

    public static void main(String[] args) {
        new CapNhatNhanVien("NV24119212", "dfdfhfdhfdjf", "01/01/1990", "123 Đường ABC", "0935201508", "123456789012",
                "01/01/2020", true, "Nam", "D:\\Anh1.jpg", "NV");
    }

    public CapNhatNhanVien(String maNV, String hoTenNV, String ngaySinh, String diaChi, String sdt, String cccd,
            String ngayVaoLam, boolean trangThai, String gioiTinh, String hinhAnh, String maLoaiNV) {
        setSize(915, 702);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBackground(new Color(70, 130, 180));
        JLabel lblSearchHeader = new JLabel("CẬP NHẬT THÔNG TIN NHÂN VIÊN");
        lblSearchHeader.setFont(new Font("Arial", Font.BOLD, 25));
        lblSearchHeader.setForeground(Color.WHITE);
        lblSearchHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(lblSearchHeader);
        searchPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.WHITE);

        lblPhoto = new JLabel(); // Initialize the label for photo display
        lblPhoto.setBounds(227, 39, 132, 137);
        lblPhoto.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        formPanel.add(lblPhoto);

        // Upload Image Button
        btnUploadImage = new JButton("Tải ảnh");
        btnUploadImage.setBounds(227, 185, 132, 30);
        btnUploadImage.addActionListener(this::uploadImage);
        formPanel.add(btnUploadImage);

        // MaNV text field
        txtMaNV = createTextField(128, 250, 270, 40);
        txtMaNV.setEditable(false);
        formPanel.add(txtMaNV);

        // Defining text fields
        txtFullName = createTextField(128, 350, 270, 40);
        birthDateChooser = new JDateChooser();
        birthDateChooser.setBounds(128, 450, 270, 40);
        birthDateChooser.setDateFormatString("dd/MM/yyyy");

        txtAddress = createTextField(560, 250, 270, 40);
        txtPhone = createTextField(560, 300, 270, 40);
        txtCCCD = createTextField(560, 400, 270, 40);

        // ComboBox for maLoaiNV (employee type)
        cboEmployeeType = new JComboBox<>(new String[] { "NV - Nhân viên", "QL - Quản lý" });
        cboEmployeeType.setBounds(560, 350, 270, 40);
        cboEmployeeType.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(cboEmployeeType);

        // Labels for employee information
        lblEmployeeId = createLabel("Mã nhân viên:", 34, 250, 100, 40);
        formPanel.add(lblEmployeeId);
        formPanel.add(createLabel("Họ và tên:", 34, 350, 84, 40));
        formPanel.add(txtFullName);

        formPanel.add(createLabel("Giới tính:", 34, 300, 84, 40));
        rbtnMale = new JRadioButton("Nam");
        rbtnFemale = new JRadioButton("Nữ");
        rbtnMale.setBounds(150, 300, 60, 40); // Adjust coordinates as necessary
        rbtnFemale.setBounds(220, 300, 60, 40);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbtnMale);
        genderGroup.add(rbtnFemale);

        formPanel.add(rbtnMale);
        formPanel.add(rbtnFemale);

        formPanel.add(createLabel("Ngày sinh:", 34, 500, 100, 40));
        birthDateChooser = new JDateChooser();
        birthDateChooser.setBounds(128, 500, 270, 40); // Vị trí và kích thước cho JDateChooser
        birthDateChooser.setDateFormatString("dd/MM/yyyy"); // Định dạng ngày

        formPanel.add(birthDateChooser);

        formPanel.add(createLabel("Ngày vào làm:", 34, 400, 100, 40));
        Calendar maxDate = Calendar.getInstance();
        joinDateChooser = new JDateChooser();
        joinDateChooser.setBounds(128, 400, 270, 40); // Vị trí và kích thước cho JDateChooser
        joinDateChooser.setDateFormatString("dd/MM/yyyy"); // Định dạng ngày
        joinDateChooser.setMaxSelectableDate(maxDate.getTime());

        formPanel.add(joinDateChooser);

        formPanel.add(createLabel("Địa chỉ:", 480, 250, 100, 40));
        formPanel.add(txtAddress);
        formPanel.add(createLabel("SĐT:", 480, 300, 100, 40));
        formPanel.add(txtPhone);

        statusMap = new HashMap<>();
        statusMap.put("Đang làm", 1);
        statusMap.put("Đã nghỉ việc", 0);
        formPanel.add(createLabel("Trạng thái:", 34, 450, 100, 40));
        cboStatus = new JComboBox<>(statusMap.keySet().toArray(new String[0]));
        cboStatus.setSelectedItem("Đang làm");

        cboStatus.setBounds(128, 450, 270, 40);
        cboStatus.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(cboStatus);

        formPanel.add(createLabel("Mã Loại NV:", 480, 350, 100, 40));
        formPanel.add(cboEmployeeType);
        formPanel.add(createLabel("CCCD:", 480, 400, 100, 40));
        formPanel.add(txtCCCD);

        // Update button
        btnUpdate = new JButton("Cập nhật");
        btnUpdate.setBounds(680, 450, 132, 40);
        btnUpdate.addActionListener(this::saveEmployee);
        formPanel.add(btnUpdate);

        // Add panels to main container
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().add(formPanel, BorderLayout.CENTER);

        loadData(maNV, hoTenNV, ngaySinh, diaChi, sdt, cccd, ngayVaoLam, trangThai, gioiTinh, hinhAnh, maLoaiNV);
        setVisible(true);

    }

    public CapNhatNhanVien() {
		// TODO Auto-generated constructor stub
	}

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

    private void loadData(String maNV, String hoTenNV, String ngaySinh, String diaChi, String sdt, String cccd,
            String ngayVaoLam, boolean trangThai, String gioiTinh, String hinhAnh, String maLoaiNV) {

        txtMaNV.setText(maNV);
        txtFullName.setText(hoTenNV);
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            birthDateChooser.setDate(sdf.parse(ngaySinh));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            joinDateChooser.setDate(sdf.parse(ngayVaoLam));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        txtAddress.setText(diaChi);
        txtPhone.setText(sdt);
        // txtStatus.setText(trangThai ? "Đang làm" : "Nghỉ việc"); // Simplified status
        cboStatus.setSelectedItem(trangThai ? "Đang làm" : "Đã nghỉ việc");
        txtCCCD.setText(cccd);
        cboEmployeeType.setSelectedItem(maLoaiNV);

        hinhAnhNV = hinhAnh;

        if ("Nam".equals(gioiTinh)) {
            rbtnMale.setSelected(true);
        } else {
            rbtnFemale.setSelected(true);
        }

        // Load the image
        loadImage(hinhAnhNV);
    }

    private void loadImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image originalImage = imageIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(132, 137, Image.SCALE_SMOOTH);
        lblPhoto.setIcon(new ImageIcon(resizedImage));
    }

    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(70, 130, 180));
        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField(10);
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));
        return textField;
    }

    private void saveEmployee(ActionEvent event) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Collecting input data
        String fullName = txtFullName.getText();
        String birthDateStr = birthDateChooser.getDate() != null ? dateFormat.format(birthDateChooser.getDate()) : "";
        String joinDateStr = joinDateChooser.getDate() != null ? dateFormat.format(joinDateChooser.getDate()) : "";
        String address = txtAddress.getText();
        String phone = txtPhone.getText();
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
        if (!isValidInput(fullName, birthDateStr, joinDateStr, address, phone, cccd)) {
            return; // Exit if validation fails
        }

        if (hinhAnhNV == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ảnh!");
            return;
        }

        // Parsing dates
        LocalDate birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate joinDate = LocalDate.parse(joinDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Creating employee entity
        NhanVien_entity employee = new NhanVien_entity();
        employee.setMaNV(txtMaNV.getText());
        employee.setHoTenNV(fullName);
        employee.setGioiTinh(gioiTinh); // Set gender
        employee.setNgaySinh(birthDate);
        employee.setNgayVaoLam(joinDate);
        employee.setDiaChi(address);
        employee.setSdt(phone);
        employee.setCccd(cccd);
        employee.setTrangThai(true);
        employee.setMaLoaiNV(maLoaiNV.substring(0, 2));
        employee.setHinhAnhNV(hinhAnhNV);
        employee.setTrangThai(statusMap.get(cboStatus.getSelectedItem()) == 1);
        NhanVien_DAO dao = new NhanVien_DAO();
        if (dao.updateNhanVien(employee)) {
            JOptionPane.showMessageDialog(this, "Lưu nhân viên thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Lưu nhân viên thất bại!");
        }
    }

    private boolean isValidInput(String fullName, String birthDateStr, String joinDateStr, String address, String phone,
            String cccd) {
        if (fullName.isEmpty() || !Pattern.matches("^[\\p{L}\\s]+$", fullName)) {
            JOptionPane.showMessageDialog(this, "Họ tên không hợp lệ!");
            return false;
        }

        if (birthDateStr.isEmpty() || !Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", birthDateStr)) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ!");
            return false;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = dateFormat.parse(birthDateStr);

            Calendar currentDate = Calendar.getInstance();
            Calendar birthDateCal = Calendar.getInstance();
            birthDateCal.setTime(birthDate);

            int age = currentDate.get(Calendar.YEAR) - birthDateCal.get(Calendar.YEAR);

            if (currentDate.get(Calendar.DAY_OF_YEAR) < birthDateCal.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }

            if (age < 18) {
                JOptionPane.showMessageDialog(this, "Nhân viên phải đủ 18 tuổi!");
                return false;
            }

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Lỗi trong việc xử lý ngày sinh!");
            return false;
        }

        if (joinDateStr.isEmpty() || !Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", joinDateStr)) {
            JOptionPane.showMessageDialog(this, "Ngày vào làm không hợp lệ!");
            return false;
        }
        if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!");
            return false;
        }
        if (phone.isEmpty() || !Pattern.matches("^\\d{10}$", phone)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!");
            return false;
        }
        if (!Pattern.matches("^\\d{12}$", cccd)) {
            JOptionPane.showMessageDialog(this, "CCCD phải bao gồm 12 chữ số!");
            return false;
        }

        return true;
    }

}
