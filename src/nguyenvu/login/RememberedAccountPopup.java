package nguyenvu.login;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class RememberedAccountPopup {
    private JPopupMenu popupMenu;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private RememberedAccountList rememberedAccountList;

    public RememberedAccountPopup(JTextField usernameField, JPasswordField passwordField) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.rememberedAccountList = new RememberedAccountList(); // Tái sử dụng danh sách tài khoản ghi nhớ
        popupMenu = new JPopupMenu();

        // Tải các tài khoản ghi nhớ vào popup
        loadPopupItems();

        // Xử lý sự kiện chuột để popup không làm gián đoạn
        usernameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Kiểm tra nếu có ít nhất một tài khoản ghi nhớ
                if (hasItems()) {
                    showPopup(e);  // Hiển thị popup nếu có tài khoản ghi nhớ
                }
            }
        });

        // Đảm bảo popup không chặn focus của trường văn bản
        popupMenu.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
                usernameField.requestFocusInWindow(); // Giữ focus
            }

            @Override
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {
                usernameField.requestFocusInWindow(); // Trả lại focus sau khi popup đóng
            }

            @Override
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {
                usernameField.requestFocusInWindow(); // Trả lại focus khi popup bị hủy
            }
        });
    }

    /**
     * Tải danh sách các tài khoản đã ghi nhớ và thêm vào popup menu.
     */
   private void loadPopupItems() {
        Map<String, String> accounts = rememberedAccountList.getRememberedAccounts();
        popupMenu.removeAll(); // Xóa các mục cũ trước khi thêm mới
        for (Map.Entry<String, String> entry : accounts.entrySet()) {
            String username = entry.getKey();
            JMenuItem menuItem = new JMenuItem(username);
            menuItem.addActionListener(e -> fillLoginFields(username));
            popupMenu.add(menuItem); // Thêm item vào popup
        }
    }

    /**
     * Điền thông tin đăng nhập (username và password) vào các trường văn bản.
     * 
     * @param username tên đăng nhập được chọn từ popup.
     */
    private void fillLoginFields(String username) {
        usernameField.setText(username);

        // Kiểm tra nếu có mật khẩu tương ứng
        String password = rememberedAccountList.getPassword(username);
        if (password != null) {
            passwordField.setText(password);
        }

        popupMenu.setVisible(false); // Đóng popup sau khi chọn
    }

    /**
     * Hiển thị popup tại vị trí chuột.
     * 
     * @param e sự kiện chuột để xác định vị trí hiển thị.
     */
    public void showPopup(MouseEvent e) {
        popupMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * Kiểm tra nếu popup có các mục để hiển thị.
     * 
     * @return true nếu popup có mục, ngược lại false.
     */
    public boolean hasItems() {
        return popupMenu.getComponentCount() > 0; // Kiểm tra nếu có mục trong popup
    }
}
