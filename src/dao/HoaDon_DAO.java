package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.connectDB;

public class HoaDon_DAO {

    // Get full invoice details by maHD
    public String getHoaDonInfo(String maHD) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String info = null;
//        try {
//            ps = con.prepareStatement("SELECT ngayLapHD, tongTien, tienKhachTra, hinhThucThanhToan, trangThai, maKH, maNV, maLoaiHD FROM HoaDon WHERE maHD = ?");
//            ps.setString(1, maHD);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                String trangThai = (rs.getInt("trangThai") == 1) ? "Hoàn thành" : "Chưa hoàn thành (Đang tạm lưu)";
//                info = "Mã HD: " + maHD + 
//                        ", Ngày lập: " + rs.getDate("ngayLapHD") + 
//                        ", Tổng tiền: " + rs.getDouble("tongTien") + 
//                        ", Tiền khách trả: " + rs.getDouble("tienKhachTra") + 
//                        ", Hình thức thanh toán: " + rs.getString("hinhThucThanhToan") + 
//                        ", Trạng thái: " + trangThai + 
//                        ", Mã KH: " + rs.getString("maKH") + 
//                        ", Mã NV: " + rs.getString("maNV") + 
//                        ", Mã loại HD: " + rs.getString("maLoaiHD");
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
        return "Mã HD: " + maHD + ", Ngày lập: 2024-10-17, Tổng tiền: 1000000, Tiền khách trả: 1000000, Hình thức thanh toán: Tiền mặt, Trạng thái: 1, Mã KH: KH23071456789, Mã NV: NV23012345, Mã loại HD: BanThuoc"; // Hardcoded value
    }

    // Get the status (trangThai) of the invoice by maHD
    public int getTrangThai(String maHD) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return 0;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        int trangThai = 0;
//        try {
//            ps = con.prepareStatement("SELECT trangThai FROM HoaDon WHERE maHD = ?");
//            ps.setString(1, maHD);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                trangThai = rs.getInt("trangThai");
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
//        return trangThai;
        return 1; // Hardcoded example (1 for "Hoàn thành")
    }

    // Get the total amount (tongTien) by maHD
    public double getTongTien(String maHD) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return 0;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        double tongTien = 0;
//        try {
//            ps = con.prepareStatement("SELECT tongTien FROM HoaDon WHERE maHD = ?");
//            ps.setString(1, maHD);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                tongTien = rs.getDouble("tongTien");
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
//        return tongTien;
        return 1000000; // Hardcoded example
    }
}
