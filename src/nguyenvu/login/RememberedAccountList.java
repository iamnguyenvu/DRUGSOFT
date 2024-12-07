package nguyenvu.login;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;

public class RememberedAccountList {
    private static final String FILE_PATH = "src/nguyenvu/login/remembered_accounts.properties";  // Định nghĩa FILE_PATH
    private Map<String, String> rememberedAccountsMap;  // Sử dụng Map thay vì List để tìm kiếm nhanh hơn

    // Constructor: Tự động tải danh sách tài khoản đã ghi nhớ khi khởi tạo.
    public RememberedAccountList() {
        loadRememberedAccounts();
    }

    // Phương thức để tải lại danh sách tài khoản từ file vào Map.
    private void loadRememberedAccounts() {
        rememberedAccountsMap = new HashMap<>();
        List<String[]> accounts = RememberMeUtil.getRememberedAccounts();
        for (String[] account : accounts) {
            rememberedAccountsMap.put(account[0], account[1]);
        }
    }

    // Trả về danh sách tài khoản đã ghi nhớ.
    public Map<String, String> getRememberedAccounts() {
        Map<String, String> accounts = new HashMap<>();
        try (FileInputStream input = new FileInputStream(FILE_PATH)) {
            Properties prop = new Properties();
            prop.load(input);
            for (String key : prop.stringPropertyNames()) {
                accounts.put(key, prop.getProperty(key));  // Lưu key và value vào Map
            }
        } catch (IOException e) {
            e.printStackTrace(); // Nếu file không tồn tại hoặc lỗi đọc file
        }
        return accounts;
    }

    // Trả về mật khẩu tương ứng với tên tài khoản.
    public String getPassword(String username) {
        return rememberedAccountsMap.get(username);  // Trả về mật khẩu theo tài khoản
    }

    // Cập nhật mật khẩu của tài khoản đã ghi nhớ.
    public void updatePassword(String username, String newPassword) {
        if (rememberedAccountsMap.containsKey(username)) {
            rememberedAccountsMap.put(username, newPassword);
            RememberMeUtil.saveLoginInfo(username, newPassword, true);  // Lưu lại thông tin mới vào file
        }
    }

    // Phương thức xóa tài khoản khỏi danh sách đã ghi nhớ
    public void removeAccount(String username) {
        if (rememberedAccountsMap.containsKey(username)) {
            rememberedAccountsMap.remove(username);
            RememberMeUtil.clearLoginInfo(username);  // Xóa tài khoản khỏi file
        }
    }
}
