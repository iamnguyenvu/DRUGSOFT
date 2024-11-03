package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class XoaNV extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtFullName, txtAddress, txtBirthDate, txtJoinDate,
            txtPhone, txtStatus, txtCCCD, employeeType, txtMaNV;
    private JButton btnClose;
    private JLabel lblPhoto;
    private JRadioButton rbtnMale, rbtnFemale;

    public XoaNV(String maNV, String hoTenNV, String ngaySinh, String diaChi, String sdt, String cccd,
            String ngayVaoLam, boolean trangThai, String gioiTinh, String hinhAnhNV, String maLoaiNV) {
        setTitle("Thông Tin Nhân Viên");
        setSize(915, 702);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());

        JPanel searchPanel = createSearchPanel();
        JPanel formPanel = createFormPanel(maNV, hoTenNV, ngaySinh, diaChi, sdt, cccd, ngayVaoLam, trangThai, gioiTinh,
                hinhAnhNV, maLoaiNV);

        // Adding panels to the main frame
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().add(formPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBackground(new Color(70, 130, 180));
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

        lblPhoto = new JLabel();
        lblPhoto.setBounds(227, 39, 132, 137);
        lblPhoto.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        formPanel.add(lblPhoto);

        txtMaNV = createTextField(128, 200);
        txtMaNV.setEditable(false);

        txtFullName = createTextField(128, 300);
        txtBirthDate = createTextField(128, 450);
        txtJoinDate = createTextField(128, 350);
        txtAddress = createTextField(560, 250);
        txtPhone = createTextField(560, 300);
        txtStatus = createTextField(128, 400);
        txtCCCD = createTextField(560, 400);
        employeeType = createTextField(560, 350);

        
        addComponentsToFormPanel(formPanel);
     // Close button
        btnClose = new JButton("Đóng");
        btnClose.setBounds(680, 450, 150, 50);
        btnClose.addActionListener(e -> dispose()); // Close action
        formPanel.add(btnClose);
        

        // Load data
        loadData(maNV, hoTenNV, ngaySinh, diaChi, sdt, cccd, ngayVaoLam, trangThai, gioiTinh, hinhAnhNV, maLoaiNV);

        // Close button
        btnClose = new JButton("Đóng");
        btnClose.setBounds(680, 450, 150, 50);
        formPanel.add(btnClose);

        return formPanel;
    }
    
    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 270, 40);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 100, 40);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

    private void addComponentsToFormPanel(JPanel formPanel) {
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

    private void loadData(String maNV, String hoTenNV, String ngaySinh, String diaChi, String sdt, String cccd,
            String ngayVaoLam, boolean trangThai, String gioiTinh, String hinhAnhNV, String maLoaiNV) {

        txtMaNV.setText(maNV);

        txtFullName.setText(hoTenNV);
        txtBirthDate.setText(ngaySinh);
        txtJoinDate.setText(ngayVaoLam);
        txtAddress.setText(diaChi);
        txtPhone.setText(sdt);
        txtStatus.setText(trangThai ? "Đang làm" : "Nghỉ việc"); // Simplified status
        txtCCCD.setText(cccd);
        employeeType.setText(maLoaiNV == "NV" ? "NV - Nhân viên" : "QL - Quản lý");

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

    public static void main(String[] args) {
        new XoaNV("NV01", "Nguyen Van A", "01/01/1990", "Hà Nội", "0123456789", "123456789",
                "01/01/2020", true, "Nam", "D:\\Anh1.jpg", "NV");
    }
}
