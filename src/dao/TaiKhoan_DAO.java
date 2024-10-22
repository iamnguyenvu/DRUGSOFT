package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connectDB.connectDB;
import entity.TaiKhoan_entity;

import java.util.ArrayList;

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
    // Get all TaiKhoan
    public ArrayList<TaiKhoan_entity> getAllTaiKhoan() {
        ArrayList<TaiKhoan_entity> dsTaiKhoan = new ArrayList<>();
        Connection con = connectDB.accessDataBase();  // Kết nối tới SQL Server
        if (con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("SELECT * FROM TaiKhoan");  // Truy vấn SQL
            rs = ps.executeQuery();

            while (rs.next()) {
                String tenTK = rs.getString("tenDangNhap");
                String mK = rs.getString("matKhau");
                boolean pQ = rs.getBoolean("phanQuyen");
                boolean tt = rs.getBoolean("trangThai");

                // Khởi tạo đối tượng TaiKhoan và thêm vào danh sách
                TaiKhoan_entity tk = new TaiKhoan_entity(tenTK, mK, pQ, tt);
                dsTaiKhoan.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Hiển thị lỗi nếu có
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return dsTaiKhoan;  // Trả về danh sách tài khoản
    }




    
    // Thêm tài khoản với đầy đủ thông tin
    public boolean addTaiKhoan(String tenDangNhap, String matKhau, boolean phanQuyen, boolean trangThai) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return false;
        PreparedStatement ps = null;
        try {
            // Thêm phanQuyen và trangThai vào câu lệnh SQL
            ps = con.prepareStatement(
                "INSERT INTO TaiKhoan (tenDangNhap, matKhau, phanQuyen, trangThai) VALUES (?, ?, ?, ?)");
            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);
            ps.setBoolean(3, phanQuyen);
            ps.setBoolean(4, trangThai);
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
