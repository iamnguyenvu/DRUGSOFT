package dao;

import java.sql.*;
import entity.TaiKhoan_entity;
import connectDB.connectDB;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TaiKhoan_DAO {

    // Get TaiKhoan by tenDangNhap
    public String getTaiKhoanInfo(String tenDangNhap) {
        String sql = "SELECT * FROM TaiKhoan WHERE tenDangNhap = ?";
        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tenDangNhap);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return "Tên Đăng Nhập: " + tenDangNhap + 
                           ", Mật Khẩu: " + rs.getString("matKhau");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all TaiKhoan
    public ArrayList<TaiKhoan_entity> getAllTaiKhoan() {
        ArrayList<TaiKhoan_entity> dsTaiKhoan = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan";

        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tenTK = rs.getString("tenDangNhap");
                String mK = rs.getString("matKhau");
                boolean pQ = rs.getBoolean("phanQuyen");
                boolean tt = rs.getBoolean("trangThai");

                dsTaiKhoan.add(new TaiKhoan_entity(tenTK, mK, pQ, tt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsTaiKhoan;
    }

    // Thêm tài khoản
    public boolean addTaiKhoan(String tenDangNhap, String matKhau, boolean phanQuyen, boolean trangThai) {
        if (isTaiKhoanExists(tenDangNhap)) {
            JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại!");
            return false;
        }

        String sql = "INSERT INTO TaiKhoan (tenDangNhap, matKhau, phanQuyen, trangThai) VALUES (?, ?, ?, ?)";
        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);
            ps.setBoolean(3, phanQuyen);
            ps.setBoolean(4, trangThai);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật tài khoản
    public boolean updateTaiKhoan(String tenTaiKhoan, String matKhau, boolean phanQuyen, boolean trangThai) {
        if (matKhau.length() < 8 || matKhau.length() > 16 ||
            !matKhau.matches(".*[0-9].*") || !matKhau.matches(".*[A-Z].*") ||
            !matKhau.matches(".*[a-z].*") || !matKhau.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {

            JOptionPane.showMessageDialog(null, "Mật khẩu không hợp lệ!");
            return false;
        }

        String sql = "UPDATE TaiKhoan SET matKhau = ?, phanQuyen = ?, trangThai = ? WHERE tenDangNhap = ?";
        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matKhau);
            ps.setBoolean(2, phanQuyen);
            ps.setBoolean(3, trangThai);
            ps.setString(4, tenTaiKhoan);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa tài khoản
    public boolean deleteTaiKhoan(String tenTaiKhoan) {
        String sql = "DELETE FROM TaiKhoan WHERE tenDangNhap = ?";
        try (Connection conn = connectDB.accessDataBase(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenTaiKhoan);
            int affectedRows = stmt.executeUpdate();  // Thực hiện lệnh DELETE.

            return affectedRows > 0;  // Trả về true nếu xóa thành công.
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Trả về false nếu có lỗi xảy ra.
        }
    }

    // Kiểm tra tài khoản đã tồn tại chưa
    private boolean isTaiKhoanExists(String tenDangNhap) {
        String sql = "SELECT COUNT(*) FROM TaiKhoan WHERE tenDangNhap = ?";
        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tenDangNhap);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public String getTenDangNhapByEmail(String email) {
        String sql = "SELECT tk.tenDangNhap " +
                     "FROM TaiKhoan tk " +
                     "JOIN NhanVien nv ON tk.tenDangNhap = nv.maNV " +
                     "WHERE nv.email = ?";
        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tenDangNhap");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức cập nhật mật khẩu trong cơ sở dữ liệu
    public boolean updatePassword(String tenDangNhap, String newPassword) {
        String sql = "UPDATE TaiKhoan SET matKhau = ? WHERE tenDangNhap = ?";
        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newPassword);  // Gán mật khẩu mới
            ps.setString(2, tenDangNhap);  // Gán tên đăng nhập
            int rowsUpdated = ps.executeUpdate();  // Thực hiện cập nhật

            return rowsUpdated > 0;  // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Trả về false nếu có lỗi
        }
    }

}