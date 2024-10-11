package nguyenvu.login;

import com.formdev.flatlaf.FlatClientProperties;
import dao.DangNhap_DAO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import raven.alerts.MessageAlerts;
import nguyenvu.menu.FormManager;
import nguyenvu.model.ModelUser;

/**
 *
 * @author nguyenvu
 */
public class Login extends JPanel {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JCheckBox chRememberMe;
    private JButton cmdLogin;

    public Login() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        chRememberMe = new JCheckBox("Remember me");
        cmdLogin = new JButton("Login");
        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "[light]background:darken(@background,3%);"
                + "[dark]background:lighten(@background,3%)");

        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]background:darken(@background,10%);"
                + "[dark]background:lighten(@background,10%);"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");

        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập mã nhân viên");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập mật khẩu");

        JLabel lbTitle = new JLabel("Welcome back!");
        JLabel description = new JLabel("Please sign in to access your account");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]foreground:lighten(@foreground,30%);"
                + "[dark]foreground:darken(@foreground,30%)");

        panel.add(lbTitle);
        panel.add(description);
        panel.add(new JLabel("Tên đăng nhập"), "gapy 8");
        panel.add(txtUsername);
        panel.add(new JLabel("Mật khẩu"), "gapy 8");
        panel.add(txtPassword);
        panel.add(chRememberMe, "grow 0");
        panel.add(cmdLogin, "gapy 10");
        add(panel);

        // event
        cmdLogin.addActionListener((e) -> {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
//	        JOptionPane.showMessageDialog(this, "Tên đăng nhập và mật khẩu không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                MessageAlerts.getInstance().showMessage("Lỗi", "Tên đăng nhập hoặc mật khẩu không được để trống!");
                return;
            }

            DangNhap_DAO dao = new DangNhap_DAO();
            Integer role = dao.getRole(username, password);

            if(role != null) {
                String avatarPath = dao.getAvatar(username);
                String hoTen = dao.getHoTen(username);
                ModelUser user = new ModelUser(username, role, avatarPath, hoTen);
                FormManager.login(user);
            }
            else {
                MessageAlerts.getInstance().showMessage("Lỗi", "Sai mật khẩu hoặc tên đăng nhập");
                txtUsername.setText("");
                txtPassword.setText("");
            }

        });
    }
}
