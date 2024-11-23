package nguyenvu.login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RememberMeUtil {
    private static final String FILE_PATH = "remembered_accounts.properties";

    // Kiểm tra sự tồn tại của tệp và tạo mới nếu không tồn tại
    private static void createFileIfNotExist() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Lưu thông tin đăng nhập vào tệp tin
    public static void saveLoginInfo(String username, String password, boolean rememberMe) {
    if (rememberMe) {
        try (FileOutputStream output = new FileOutputStream(FILE_PATH, true)) {  // Mở file ở chế độ thêm
            Properties prop = new Properties();
            prop.load(new FileInputStream(FILE_PATH));  // Đọc các dữ liệu đã có trong file
            prop.setProperty(username, password);  // Thêm thông tin tài khoản mới
            prop.store(output, "Remembered Accounts");  // Lưu lại thông tin vào file
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        // Nếu không chọn "Ghi nhớ đăng nhập", xóa thông tin khỏi file
        clearLoginInfo(username);
    }
}


    // Đọc danh sách tài khoản đã ghi nhớ từ tệp tin
    public static List<String[]> getRememberedAccounts() {
        createFileIfNotExist(); // Đảm bảo tệp tồn tại trước khi thao tác

        List<String[]> accounts = new ArrayList<>();
        try (FileInputStream input = new FileInputStream(FILE_PATH)) {
            Properties prop = new Properties();
            prop.load(input);
            for (String key : prop.stringPropertyNames()) {
                accounts.add(new String[]{key, prop.getProperty(key)});
            }
        } catch (IOException e) {
            e.printStackTrace(); // Nếu file không tồn tại hoặc lỗi đọc file
        }
        return accounts;
    }

    // Xóa thông tin đăng nhập khỏi tệp tin
    public static void clearLoginInfo(String username) {
        createFileIfNotExist(); // Đảm bảo tệp tồn tại trước khi thao tác

        try (FileInputStream input = new FileInputStream(FILE_PATH);
             FileOutputStream output = new FileOutputStream(FILE_PATH)) {

            Properties prop = new Properties();
            prop.load(input);
            prop.remove(username); // Xóa thông tin đăng nhập
            prop.store(output, "Updated Remembered Accounts");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
