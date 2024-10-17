package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.connectDB;

public class KhachHang_DAO {

    // Get full customer details by maKH
    public String getKhachHangInfo(String maKH) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String info = null;
//        try {
//            ps = con.prepareStatement("SELECT tenKH, SDT, diemThuong, gioiTinh FROM KhachHang WHERE maKH = ?");
//            ps.setString(1, maKH);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                String gioiTinh = convertGioiTinh(rs.getString("gioiTinh"));
//                info = "Mã KH: " + maKH + 
//                        ", Tên: " + rs.getString("tenKH") + 
//                        ", SĐT: " + rs.getString("SDT") + 
//                        ", Điểm thưởng: " + rs.getInt("diemThuong") + 
//                        ", Giới tính: " + gioiTinh;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return info;
        return "Mã KH: " + maKH + ", Tên: Nguyễn Văn A, SĐT: 0909123456, Điểm thưởng: 100, Giới tính: Nam"; // Hardcoded example
    }

    // Convert gioiTinh values from database (e.g., 1, 0) into text format ("Nam", "Nữ", "Khác")
    private String convertGioiTinh(String gioiTinhValue) {
        switch (gioiTinhValue) {
            case "0":
                return "Nữ";
            case "1":
                return "Nam";
            default:
                return "Khác";
        }
    }

    // Get customer reward points (diemThuong) by maKH
    public int getDiemThuong(String maKH) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return 0;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        int diemThuong = 0;
//        try {
//            ps = con.prepareStatement("SELECT diemThuong FROM KhachHang WHERE maKH = ?");
//            ps.setString(1, maKH);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                diemThuong = rs.getInt("diemThuong");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return diemThuong;
        return 100; // Hardcoded example
    }

    // Get customer gender (gioiTinh) by maKH
    public String getGioiTinh(String maKH) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String gioiTinh = null;
//        try {
//            ps = con.prepareStatement("SELECT gioiTinh FROM KhachHang WHERE maKH = ?");
//            ps.setString(1, maKH);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                gioiTinh = convertGioiTinh(rs.getString("gioiTinh"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return gioiTinh;
        return "Nam"; // Hardcoded example
    }

    // Get customer phone number (SDT) by maKH
    public String getSDT(String maKH) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String SDT = null;
//        try {
//            ps = con.prepareStatement("SELECT SDT FROM KhachHang WHERE maKH = ?");
//            ps.setString(1, maKH);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                SDT = rs.getString("SDT");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return SDT;
        return "0909123456"; // Hardcoded example
    }
}
