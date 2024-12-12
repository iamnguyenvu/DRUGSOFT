
package nguyenvu.login;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import dao.DangNhap_DAO;
import dao.TaiKhoan_DAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;
import nguyenvu.menu.FormManager;
import nguyenvu.model.ModelUser;
import raven.alerts.MessageAlerts;

public class Login extends JPanel implements ActionListener, ItemListener, MouseListener {
    private static final long serialVersionUID = 1L;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnExit;
    private JButton btnMinimize;
    private JButton btnSetting;
    private JButton btnLogin;
    private JCheckBox chRememberMe;  // Checkbox ghi nhớ mật khẩu
    private JButton btnForgotPassword; // Button quên mật khẩu

    private int xMouse;
    private int yMouse;
    private boolean isSettingsVisible = false;
    private JDialog forgotDialog;
    private JButton sendButton;
    private JButton backButton;
    
    public String ygetTenDangNhap(){
        return txtUsername.getText();
    }

    public Login() {
        setPreferredSize(new Dimension(1000, 600));
        setLayout(new MigLayout("fill", "push[center][center]push", "push[center]push")); // Căn giữa panel chính
        init();
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - xMouse, y - yMouse);
            }
        });
    }

    private void init() {
        // dialog forgot
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.decode("#E0E0E0"));
        backButton.setIcon(new FlatSVGIcon("gui/icon/back.svg",0.02f));
        
        JButton sendButton = new JButton("Gửi mật khẩu mới");
        sendButton.setBackground(Color.decode("#28A745"));
        
        forgotDialog = new JDialog();
        forgotDialog.setTitle("Quên Mật Khẩu");
        forgotDialog.setSize(400, 200);
        forgotDialog.setLayout(new MigLayout("wrap 2", "[][grow]"));
        forgotDialog.setModal(true);
        forgotDialog.setUndecorated(true);

        JLabel forgotLabel = new JLabel("Quên Mật Khẩu", JLabel.CENTER);

        // Thiết lập font chữ
        forgotLabel.setFont(new Font("Open Sans", Font.BOLD,24)); // Font đậm, kích thước 18

        // Thiết lập màu chữ
        forgotLabel.setForeground(Color.decode("#007BFF")); // Màu xanh dương

        // Thiết lập kích thước và căn giữa
        forgotLabel.setHorizontalAlignment(JLabel.CENTER);
        forgotLabel.setVerticalAlignment(JLabel.CENTER);

        // Đặt padding hoặc khoảng trống
        forgotLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding xung quanh
        forgotDialog.add(forgotLabel, "span, center, wrap 20");

        JLabel emailLabel = new JLabel("Nhập email của bạn:");
        JTextField emailField = new JTextField(20);

        forgotDialog.add(emailLabel);
        forgotDialog.add(emailField, "growx");
        forgotDialog.add(sendButton, "span, center");
        forgotDialog.add(backButton);

        forgotDialog.pack();
        backButton.addActionListener((e) -> {
            forgotDialog.dispose();
        });
        sendButton.addActionListener((e) -> {
            String email = emailField.getText();
	    	    TaiKhoan_DAO taiKhoanDAO = new TaiKhoan_DAO();

    	    // Lấy tên đăng nhập từ email
    	    String tenDangNhap = taiKhoanDAO.getTenDangNhapByEmail(email);
    	    if (tenDangNhap != null) {
    	        // Tạo mật khẩu mới
    	        String newPassword = PasswordGenerator.generatePassword();

    	        // Gửi email với mật khẩu mới
    	        String subject = "Mật khẩu mới của bạn";
    	        String body = "Mật khẩu mới của bạn là: " + newPassword;
    	        boolean emailSent = EmailSender.sendEmail(email, subject, body);

    	        if (emailSent) {
    	            // Nếu email được gửi thành công, cập nhật mật khẩu trong cơ sở dữ liệu
    	            boolean isUpdated = taiKhoanDAO.updatePassword(tenDangNhap, newPassword);
    	            if (isUpdated) {
    	                JOptionPane.showMessageDialog(this, "Mật khẩu mới đã được gửi đến email của bạn!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    	            } else {
    	                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật mật khẩu trong cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	            }
    	        } else {
    	            JOptionPane.showMessageDialog(this, "Lỗi khi gửi email. Vui lòng thử lại sau!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	        }
    	    } else {
    	        JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản với email này!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    	    }
        });
        // Left Panel
        JPanel pnLeft = new JPanel(new BorderLayout());
        pnLeft.setPreferredSize(new Dimension(500, 600));
        pnLeft.setMinimumSize(new Dimension(500, 600));
        pnLeft.setMaximumSize(new Dimension(500, 600));

        // Tải ảnh và resize với chất lượng cao
        try {
    ImageIcon iiLogin = new ImageIcon(getClass().getResource("/img/loginbg1.jpg"));
    Image originalImage = iiLogin.getImage();
    Image scaledImage = getHighQualityScaledImage(originalImage, 500, 600);

    // Tạo JLabel hiển thị ảnh nền
    JLabel lblLogin = new JLabel(new ImageIcon(scaledImage));
    lblLogin.setLayout(new BorderLayout()); // Cho phép thêm các thành phần con

    // Tạo JLabel chứa text với phong cách Drugsoft
    JLabel lblDrugsoft = new JLabel("<html><div style='text-align: center;'>"
        + "<span style='font-size: 28px; font-weight: bold;'>DRUGSOFT</span><br>"
        + "<span style='font-size: 12px;'>Powered by N13 - 2024</span>"
        + "</div></html>");

    lblDrugsoft.setHorizontalAlignment(JLabel.RIGHT); // Căn lề phải
    lblDrugsoft.setVerticalAlignment(JLabel.TOP);     // Căn lề trên
    lblDrugsoft.setForeground(Color.decode("#003366"));           // Màu chữ
    lblDrugsoft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Khoảng cách padding

    // Thêm JLabel text vào góc trên phải của ảnh
    lblLogin.add(lblDrugsoft, BorderLayout.NORTH);

    // Thêm JLabel vào panel bên trái
    pnLeft.add(lblLogin, BorderLayout.CENTER);
} catch (Exception ex) {
    ex.printStackTrace();
    JLabel lblError = new JLabel("Không thể tải hình ảnh");
    lblError.setHorizontalAlignment(JLabel.CENTER);
    pnLeft.add(lblError, BorderLayout.CENTER);
}



        // Right Panel
        JPanel pnRight = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, Color.decode("#0B6588"), 0, getHeight(), Color.decode("#13283d"));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        pnRight.setPreferredSize(new Dimension(500, 600));
        pnRight.setLayout(new MigLayout("wrap, fillx, insets 40 40 40 40", "[grow]", "[]20[]20[]20[]20[]40[]"));

        // Title
        
        JLabel lblTitle = new JLabel("ĐĂNG NHẬP VÀO HỆ THỐNG", JLabel.CENTER);

        // Thiết lập font chữ
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30)); // Font đậm, kích thước lớn

        // Thiết lập màu sắc
        lblTitle.setForeground(Color.WHITE); // Màu trắng

        // Thiết lập căn giữa và padding
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setVerticalAlignment(JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Khoảng cách trên và dưới
        pnRight.add(lblTitle, "wrap, center");

        // Username Label and Input Field
        JLabel lblUsername = createLabel("Tên đăng nhập");
        pnRight.add(lblUsername, "wrap, left");

        txtUsername = new JTextField();
        txtUsername.setOpaque(false);
        txtUsername.setBorder(new RoundedBorder(10));
        txtUsername.setForeground(Color.WHITE);
        txtUsername.setPreferredSize(new Dimension(360, 50)); // Tăng kích thước
        pnRight.add(txtUsername, "growx, wrap");

        // Password Label and Input Field
// Panel chứa JPasswordField và nút toggle
JPanel passwordPanel = new JPanel();
passwordPanel.setOpaque(false);
passwordPanel.setLayout(new BorderLayout());

// Tạo JPasswordField
txtPassword = new JPasswordField();
txtPassword.setOpaque(false);
txtPassword.setBorder(new RoundedBorder(10));
txtPassword.setForeground(Color.WHITE);
txtPassword.setPreferredSize(new Dimension(320, 50)); // Tăng kích thước

// Tạo nút toggle icon
JButton btnTogglePassword = new JButton(new FlatSVGIcon("gui/icon/white_eye_hide_vi.svg", 0.05f));
btnTogglePassword.setOpaque(false);
btnTogglePassword.setContentAreaFilled(false);
btnTogglePassword.setFocusPainted(false);
btnTogglePassword.setBorder(BorderFactory.createEmptyBorder());
btnTogglePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

// Xử lý sự kiện hiển thị/ẩn mật khẩu
btnTogglePassword.addActionListener(e -> {
    if (txtPassword.getEchoChar() != '\0') {
        txtPassword.setEchoChar('\0'); // Hiển thị mật khẩu
        btnTogglePassword.setIcon(new FlatSVGIcon("gui/icon/white_eye_vi.svg", 0.05f));
    } else {
        txtPassword.setEchoChar('*'); // Ẩn mật khẩu
        btnTogglePassword.setIcon(new FlatSVGIcon("gui/icon/white_eye_hide_vi.svg", 0.05f));
    }
});

// Thêm JPasswordField vào lớp phủ
passwordPanel.add(txtPassword, BorderLayout.CENTER);

// Thêm nút toggle vào lớp phủ với kích thước phù hợp
JPanel togglePanel = new JPanel();
togglePanel.setOpaque(false);
togglePanel.setLayout(new BorderLayout());
togglePanel.add(btnTogglePassword, BorderLayout.EAST);
togglePanel.setPreferredSize(new Dimension(50, 50)); // Đảm bảo kích thước nút toggle vừa vặn

// Thêm lớp phủ vào layout
passwordPanel.add(togglePanel, BorderLayout.EAST);

// Thêm vào layout của panel chính
pnRight.add(passwordPanel, "growx, wrap");


        // Remember me Checkbox and Forgot Password Button
        JPanel pnOptions = new JPanel(new MigLayout("insets 0", "[]push[]", "[]"));
        pnOptions.setOpaque(false);

        chRememberMe = new JCheckBox("Ghi nhớ mật khẩu");
        chRememberMe.setForeground(Color.WHITE);
        chRememberMe.setOpaque(false);
        chRememberMe.setFocusPainted(false);
        chRememberMe.addItemListener(this);
        pnOptions.add(chRememberMe, "left"); // Checkbox căn trái

        btnForgotPassword = new JButton("Quên mật khẩu?");
        btnForgotPassword.setBackground(Color.decode("#808080"));
        btnForgotPassword.setForeground(Color.WHITE);
        btnForgotPassword.setFocusPainted(false);
        pnOptions.add(btnForgotPassword, "alignx right"); // Nút căn phải


        //sử lý sự kiện Quên mật khẩu
        btnForgotPassword.addActionListener(e -> {
            forgotDialog.setLocationRelativeTo(this); // Đặt vị trí dialog tại trung tâm màn hình
            forgotDialog.setVisible(true); // Hiển thị dialog
        });

        pnRight.add(pnOptions, "growx, wrap");

        // Login Button
        btnLogin = new JButton("ĐĂNG NHẬP");
        btnLogin.setBackground(Color.decode("#28A745"));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new Dimension(65,50));
        btnLogin.setAlignmentX(JButton.CENTER_ALIGNMENT);
        btnLogin.setAlignmentY(JButton.CENTER_ALIGNMENT);
        pnRight.add(btnLogin, "growx, center");

        btnLogin.addActionListener(e -> LogInAction());

        // Add panels to the main panel
        add(pnLeft, "grow");
        add(pnRight, "grow");
        
        RememberedAccountPopup popup = new RememberedAccountPopup(txtUsername, txtPassword);
        
        txtUsername.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    RememberedAccountPopup popup = new RememberedAccountPopup(txtUsername, txtPassword);
                    if (popup.hasItems()) {
                        // Hiển thị popup mà không cản trở việc chỉnh sửa txtUsername
                        popup.showPopup(e);
                    }
                }
            }
        });

        // Khi người dùng click hoặc chỉnh sửa txtUsername, popup không ảnh hưởng
        txtUsername.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Đảm bảo rằng popup không chiếm quyền điều khiển chuột
                txtUsername.setCaretPosition(txtUsername.getText().length());
            }
        });
        
        chRememberMe.addItemListener(e -> {
            boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
            handleRememberMe(txtUsername.getText(), new String(txtPassword.getPassword()), isSelected);
        });

    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.PLAIN, 16));
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private void LogInAction() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            MessageAlerts.getInstance().showMessage("Đăng nhập thất bại", "Tên đăng nhập hoặc mật khẩu không được để trống!", MessageAlerts.MessageType.ERROR);
            return;
        }

        DangNhap_DAO dao = new DangNhap_DAO();
        Integer role = dao.getRole(username, password);

        if (role != null) {
            CurrentUser.setUsername(username); // Lưu tên đăng nhập vào CurrentUser
            String avatarPath = dao.getAvatar(username);
            String hoTen = dao.getHoTen(username);
            ModelUser user = new ModelUser(username, role, avatarPath, hoTen);
            FormManager.login(user);

            // Gọi phương thức handleRememberMe để lưu thông tin đăng nhập nếu cần
            handleRememberMe(username, password, chRememberMe.isSelected());
        } else {
            MessageAlerts.getInstance().showMessage("Đăng nhập thất bại", "Sai mật khẩu hoặc tên đăng nhập!", MessageAlerts.MessageType.ERROR);
            txtUsername.setText("");
            txtPassword.setText("");
        }
    }
    
    private Image getHighQualityScaledImage(Image srcImg, int width, int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(srcImg, 0, 0, width, height, null);
        g2.dispose();
        return resizedImg;
    }
    
    
    // ghi nhớ mật khẩu
    private void handleRememberMe(String username, String password, boolean rememberMe) {
        // Lưu thông tin đăng nhập vào tệp nếu 'Ghi nhớ đăng nhập' được chọn
        RememberMeUtil.saveLoginInfo(username, password, rememberMe);
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    // Custom Border for rounded corners
    class RoundedBorder extends AbstractBorder {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(c.getBackground());
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
