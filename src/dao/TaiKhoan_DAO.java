package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.connectDB;

public class TaiKhoan_DAO {

    // Get TaiKhoan by tenDangNhap
    public String getTaiKhoanInfo(String tenDangNhap) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String info = null;
        try {
            ps = con.prepareStatement("SELECT * FROM TaiKhoan WHERE tenDangNhap = ?");
            ps.setString(1, tenDangNhap);
            rs = ps.executeQuery();
            if (rs.next()) {
                info = "Tên Đăng Nhập: " + tenDangNhap + 
                       ", Mật Khẩu: " + rs.getString("matKhau");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return info;
    }

    // Get all TaiKhoan
    public String getAllTaiKhoan() {
        Connection con = connectDB.accessDataBase();
        if (con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder allInfo = new StringBuilder();
        try {
            ps = con.prepareStatement("SELECT * FROM TaiKhoan");
            rs = ps.executeQuery();
            while (rs.next()) {
                allInfo.append("Tên Đăng Nhập: ").append(rs.getString("tenDangNhap"))
                       .append(", Mật Khẩu: ").append(rs.getString("matKhau")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allInfo.toString();
    }

    // Add a new TaiKhoan
    public boolean addTaiKhoan(String tenDangNhap, String matKhau) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("INSERT INTO TaiKhoan (tenDangNhap, matKhau) VALUES (?, ?)");
            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Update TaiKhoan by tenDangNhap
    public boolean updateTaiKhoan(String tenDangNhap, String matKhau) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE TaiKhoan SET matKhau = ? WHERE tenDangNhap = ?");
            ps.setString(1, matKhau);
            ps.setString(2, tenDangNhap);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Delete TaiKhoan by tenDangNhap
    public boolean deleteTaiKhoan(String tenDangNhap) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM TaiKhoan WHERE tenDangNhap = ?");
            ps.setString(1, tenDangNhap);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
