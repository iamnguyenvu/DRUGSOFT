package dao;

import javax.swing.*;
import nguyenvu.components.SimpleForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemNhanVien extends SimpleForm {
    // Declare components
    private JTextField txtPhoneSearch, txtEmployeeId, txtFullName, txtBirthDate, txtJoinDate, txtAddress,
            txtPhone, txtStatus, txtPosition;
    private JButton btnSearch, btnAdd;
    private JLabel lblPhoto;

    public ThemNhanVien() {
        // Initialize frame
//        setTitle("Employee Management");
//        setSize(915, 702);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        
//        // Main panel layout
//        getContentPane().setLayout(new BorderLayout());

        // Search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment
        
        // Header label
        JLabel lblSearchHeader = new JLabel("Tìm kiếm số điện thoại");
        lblSearchHeader.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and size
        lblSearchHeader.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the header
        searchPanel.add(lblSearchHeader);
        searchPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between header and search field

        txtPhoneSearch = new JTextField(15);
        btnSearch = new JButton("Tìm kiếm");
        JPanel searchFieldPanel = new JPanel();
        searchFieldPanel.add(new JLabel("Nhập số điện thoại:"));
        searchFieldPanel.add(txtPhoneSearch);
        searchFieldPanel.add(btnSearch);
        searchPanel.add(searchFieldPanel); // Add search field panel to the search panel

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        lblPhoto = new JLabel(new ImageIcon("path/to/photo.jpg"));
        lblPhoto.setBackground(new Color(0, 128, 255));
        lblPhoto.setBounds(227, 39, 132, 137);
        lblPhoto.setForeground(new Color(0, 0, 128));
        formPanel.add(lblPhoto);

        txtEmployeeId = new JTextField(10);
        txtEmployeeId.setBounds(507, 56, 270, 46);
        txtFullName = new JTextField(10);
        txtFullName.setBounds(128, 221, 270, 46);
        txtBirthDate = new JTextField(10);
        txtBirthDate.setBounds(507, 130, 270, 46);
        txtJoinDate = new JTextField(10);
        txtJoinDate.setBounds(128, 298, 270, 46);
        txtAddress = new JTextField(10);
        txtAddress.setBounds(574, 221, 270, 46);
        txtPhone = new JTextField(10);
        txtPhone.setBounds(574, 311, 270, 44);
        txtStatus = new JTextField(10);
        txtStatus.setBounds(128, 383, 270, 46);
        txtPosition = new JTextField(10);
        txtPosition.setBounds(574, 386, 270, 41);

        JLabel label = new JLabel("Mã nhân viên:");
        label.setBounds(405, 39, 192, 80);
        formPanel.add(label);
        formPanel.add(txtEmployeeId);
        JLabel label_1 = new JLabel("Họ và tên:");
        label_1.setBounds(405, 121, 84, 55);
        formPanel.add(label_1);
        formPanel.add(txtFullName);

        JLabel label_2 = new JLabel("Ngày sinh:");
        label_2.setBounds(34, 225, 84, 38);
        formPanel.add(label_2);
        formPanel.add(txtBirthDate);
        JLabel label_3 = new JLabel("Ngày vào làm:");
        label_3.setBounds(480, 204, 84, 80);
        formPanel.add(label_3);
        formPanel.add(txtJoinDate);

        JLabel label_4 = new JLabel("Địa chỉ:");
        label_4.setBounds(34, 281, 90, 80);
        formPanel.add(label_4);
        formPanel.add(txtAddress);
        JLabel label_5 = new JLabel("Trạng thái:");
        label_5.setBounds(480, 281, 99, 80);
        formPanel.add(label_5);
        formPanel.add(txtStatus);

        JLabel label_6 = new JLabel("Số điện thoại:");
        label_6.setBounds(34, 379, 84, 55);
        formPanel.add(label_6);
        formPanel.add(txtPhone);

        JLabel label_8 = new JLabel("CCCD:");
        label_8.setBounds(480, 372, 84, 59);
        formPanel.add(label_8);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(0, 11, 951, 38);
        formPanel.add(lblNewLabel);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnAdd = new JButton("Thêm");
        buttonPanel.add(btnAdd);

        // Add panels to the frame
//        getContentPane().add(searchPanel, BorderLayout.NORTH);
//        getContentPane().add(formPanel, BorderLayout.CENTER);
//        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Add button actions
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchEmployee();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });
    }

    private void searchEmployee() {
        String phone = txtPhoneSearch.getText();
        // Use ThemNV_DAO to search by phone and populate fields
    }

    private void addEmployee() {
        // Use ThemNV_DAO to add a new employee using the data from input fields
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ThemNhanVien().setVisible(true);
        });
    }
}
