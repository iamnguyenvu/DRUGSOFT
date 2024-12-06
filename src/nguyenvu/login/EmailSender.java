package nguyenvu.login;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    public static boolean sendEmail(String to, String subject, String body) {
        final String from = "Vibluepro@gmail.com";
        final String password = "gwrf lwof bqwk obnw";

        // Thiết lập các thuộc tính kết nối với SMTP server của Gmail
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // Cổng TLS
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Tạo phiên làm việc với Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        // Tạo phiên làm việc với thông tin xác thực
        Session session = Session.getInstance(props, auth);

        try {
            // Tạo một tin nhắn email
            MimeMessage msg = new MimeMessage(session);

            // Đặt các tiêu đề cho email
            msg.setHeader("Content-type", "text/html; charset=UTF-8");
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject, "UTF-8");
            msg.setSentDate(new Date());

            // Đặt nội dung email
            msg.setText(body, "UTF-8");

            // Gửi email
            Transport.send(msg);

            return true; // Gửi thành công
        } catch (MessagingException e) {
            e.printStackTrace();
            return false; // Lỗi khi gửi email
        }
    }
}
