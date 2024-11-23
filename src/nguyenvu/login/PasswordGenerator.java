package nguyenvu.login;

import java.security.SecureRandom;

public class PasswordGenerator {
    public static String generatePassword() {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*(),.?\":{}|<>";
        String allChars = upperCase + lowerCase + digits + specialChars;
        SecureRandom random = new SecureRandom(); // Dùng SecureRandom để tăng cường bảo mật
        
        // Chọn độ dài ngẫu nhiên từ 8 đến 16 ký tự
        int passwordLength = 8 + random.nextInt(9);

        StringBuilder password = new StringBuilder();
        password.append(upperCase.charAt(random.nextInt(upperCase.length())));
        password.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));

        // Thêm các ký tự còn lại ngẫu nhiên để đạt được độ dài mong muốn
        for (int i = 4; i < passwordLength; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        return password.toString();
    }
}
