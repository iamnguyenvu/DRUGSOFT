package nguyenvu.login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RememberMeUtil {
    private static final String FILE_PATH = "src/nguyenvu/login/remembered_accounts.properties";

    // Kiểm tra sự tồn tại của tệp và tạo mới nếu không tồn tại
    public static void createFileIfNotExist() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile(); // Tạo file nếu chưa tồn tại
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // Lưu thông tin đăng nhập vào tệp tin
    public static void saveLoginInfo(String username, String password, boolean remember) {
    Properties prop = new Properties();

    // Bước 1: Tải tài khoản cũ từ file
    try (FileInputStream input = new FileInputStream(FILE_PATH)) {
        prop.load(input); // Tải các tài khoản cũ vào Properties
    } catch (IOException e) {
        // Không cần xử lý lỗi nếu file chưa tồn tại
    }

    // Bước 2: Thêm tài khoản mới
    if (remember) {
        prop.setProperty(username, password);
    }

    // Bước 3: Lưu lại toàn bộ vào file
    try (FileOutputStream output = new FileOutputStream(FILE_PATH)) {
        prop.store(output, null); // Ghi toàn bộ danh sách vào file
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    // Đọc danh sách tài khoản đã ghi nhớ từ tệp tin
    public static List<String[]> getRememberedAccounts() {
    createFileIfNotExist();
    List<String[]> accounts = new ArrayList<>();
    try (FileInputStream input = new FileInputStream(FILE_PATH)) {
        Properties prop = new Properties();
        prop.load(input);
        for (String key : prop.stringPropertyNames()) {
            accounts.add(new String[]{key, prop.getProperty(key)});
        }
    } catch (IOException e) {
        System.err.println("Không thể đọc tệp remembered_accounts.properties: " + e.getMessage());
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
