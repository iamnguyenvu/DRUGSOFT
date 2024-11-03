package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

public class CapNhatNV extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtFullName, txtBirthDate, txtJoinDate, txtAddress,
            txtPhone, txtStatus, txtCCCD;
    private JComboBox<String> cboEmployeeType; // maLoaiNV dropdown
    private JButton btnUploadImage; // Button for image upload
    private JLabel lblPhoto, lblEmployeeId; // Label for displaying employee ID
    private JComboBox<String> cboEmployeeList; // ComboBox to select an employee
    private JDateChooser birthDateChooser; // Khai báo JDateChooser
    private JDateChooser joinDateChooser; // Khai báo JDateChooser
    private Map<String, Integer> statusMap;
    private JComboBox<String> cboStatus;
    private JRadioButton rbtnMale, rbtnFemale; // Gender radio buttons

    // Sample employee data (replace with real data source)
    private Map<String, Employee> employeeData;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CapNhatNV().setVisible(true);
        });
    }

    public CapNhatNV() {
        setSize(915, 702);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());

        // Initialize employee data (replace this with a database call in real
        // application)
        employeeData = new HashMap<>();
        loadSampleData();

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

        // ComboBox for selecting employees
        cboEmployeeList = new JComboBox<>(employeeData.keySet().toArray(new String[0]));
        cboEmployeeList.addActionListener(new EmployeeSelectionListener());
        searchPanel.add(cboEmployeeList);

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
        formPanel.add(btnUploadImage);

        // Defining text fields
        txtFullName = createTextField(128, 300, 270, 40);
        birthDateChooser = new JDateChooser();
        birthDateChooser.setBounds(128, 450, 270, 40);
        birthDateChooser.setDateFormatString("dd/MM/yyyy");

        txtJoinDate = createTextField(128, 298, 270, 40);
        txtAddress = createTextField(560, 250, 270, 40);
        txtPhone = createTextField(560, 300, 270, 40);
        txtStatus = createTextField(128, 383, 270, 40);
        txtCCCD = createTextField(560, 400, 270, 40);

        // ComboBox for maLoaiNV (employee type)
        cboEmployeeType = new JComboBox<>(new String[] { "NV - Nhân viên", "QL - Quản lý" });
        cboEmployeeType.setBounds(560, 350, 270, 40);
        cboEmployeeType.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(cboEmployeeType);

        // Labels for employee information
        lblEmployeeId = createLabel("Mã nhân viên:", 405, 39, 100, 40);
        formPanel.add(lblEmployeeId);
        formPanel.add(createLabel("Họ và tên:", 34, 300, 84, 40));
        formPanel.add(txtFullName);

        formPanel.add(createLabel("Giới tính:", 34, 250, 84, 40));
        rbtnMale = new JRadioButton("Nam");
        rbtnFemale = new JRadioButton("Nữ");
        rbtnMale.setBounds(150, 250, 60, 40); // Adjust coordinates as necessary
        rbtnFemale.setBounds(220, 250, 60, 40);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbtnMale);
        genderGroup.add(rbtnFemale);

        formPanel.add(rbtnMale);
        formPanel.add(rbtnFemale);

        formPanel.add(createLabel("Ngày sinh:", 34, 450, 100, 40));
        birthDateChooser = new JDateChooser();
        birthDateChooser.setBounds(128, 450, 270, 40); // Vị trí và kích thước cho JDateChooser
        birthDateChooser.setDateFormatString("dd/MM/yyyy"); // Định dạng ngày

        formPanel.add(birthDateChooser);

        formPanel.add(createLabel("Ngày vào làm:", 34, 350, 100, 40));
        Calendar maxDate = Calendar.getInstance();
        joinDateChooser = new JDateChooser();
        joinDateChooser.setBounds(128, 350, 270, 40); // Vị trí và kích thước cho JDateChooser
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
        formPanel.add(createLabel("Trạng thái:", 34, 400, 100, 40));
        cboStatus = new JComboBox<>(statusMap.keySet().toArray(new String[0]));
        cboStatus.setSelectedItem("Đang làm");

        cboStatus.setBounds(128, 400, 270, 40);
        cboStatus.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(cboStatus);

        formPanel.add(createLabel("Mã Loại NV:", 480, 350, 100, 40));
        formPanel.add(cboEmployeeType);
        formPanel.add(createLabel("CCCD:", 480, 400, 100, 40));
        formPanel.add(txtCCCD);

        // Add panels to main container
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().add(formPanel, BorderLayout.CENTER);
    }

    private void loadSampleData() {
        // Sample data for demonstration purposes
        employeeData.put("EMP001", new Employee("EMP001", "Nguyen Van A", "01/01/1990", "01/01/2020", "123 ABC St",
                "0123456789", "Hoạt động", "CCCD001", "NV"));
        employeeData.put("EMP002", new Employee("EMP002", "Tran Thi B", "02/02/1992", "02/02/2021", "456 DEF St",
                "0987654321", "Hoạt động", "CCCD002", "QL"));
        // Add more employees as needed
    }

    private class EmployeeSelectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedEmployeeId = (String) cboEmployeeList.getSelectedItem();
            if (selectedEmployeeId != null) {
                Employee employee = employeeData.get(selectedEmployeeId);
                if (employee != null) {
                    populateFields(employee);
                }
            }
        }
    }

    private void populateFields(Employee employee) {
        txtFullName.setText(employee.getFullName());
        txtBirthDate.setText(employee.getBirthDate());
        txtJoinDate.setText(employee.getJoinDate());
        txtAddress.setText(employee.getAddress());
        txtPhone.setText(employee.getPhone());
        txtStatus.setText(employee.getStatus());
        txtCCCD.setText(employee.getCCCD());
        cboEmployeeType.setSelectedItem(employee.getEmployeeType());
        lblEmployeeId.setText("Mã nhân viên: " + employee.getId());
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

    // Employee class to hold employee information
    private class Employee {
        private String id;
        private String fullName;
        private String birthDate;
        private String joinDate;
        private String address;
        private String phone;
        private String status;
        private String CCCD;
        private String employeeType;

        public Employee(String id, String fullName, String birthDate, String joinDate, String address, String phone,
                String status, String CCCD, String employeeType) {
            this.id = id;
            this.fullName = fullName;
            this.birthDate = birthDate;
            this.joinDate = joinDate;
            this.address = address;
            this.phone = phone;
            this.status = status;
            this.CCCD = CCCD;
            this.employeeType = employeeType;
        }

        public String getId() {
            return id;
        }

        public String getFullName() {
            return fullName;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public String getJoinDate() {
            return joinDate;
        }

        public String getAddress() {
            return address;
        }

        public String getPhone() {
            return phone;
        }

        public String getStatus() {
            return status;
        }

        public String getCCCD() {
            return CCCD;
        }

        public String getEmployeeType() {
            return employeeType;
        }
    }
}
