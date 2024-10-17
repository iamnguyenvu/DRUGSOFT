package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.connectDB;

public class LoaiHoaDon_DAO {

    // Get details of LoaiHoaDon by maLoaiHD
    public String getLoaiHoaDonInfo(String maLoaiHD) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String info = null;
//        try {
//            ps = con.prepareStatement("SELECT * FROM LoaiHoaDon WHERE maLoaiHD = ?");
//            ps.setString(1, maLoaiHD);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                info = "Mã Loại HD: " + maLoaiHD + 
//                       ", Tên Loại HD: " + rs.getString("tenLoaiHD");
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
        return "Mã Loại HD: " + maLoaiHD + ", Tên Loại HD: Bán Thuốc"; // Hardcoded example
    }

    // Get all LoaiHoaDon
    public String getAllLoaiHoaDon() {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        StringBuilder allInfo = new StringBuilder();
//        try {
//            ps = con.prepareStatement("SELECT * FROM LoaiHoaDon");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                allInfo.append("Mã Loại HD: ").append(rs.getString("maLoaiHD"))
//                        .append(", Tên Loại HD: ").append(rs.getString("tenLoaiHD")).append("\n");
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
//        return allInfo.toString();
        return "Mã Loại HD: BanThuoc, Tên Loại HD: Bán Thuốc\n" + 
               "Mã Loại HD: NhapThuoc, Tên Loại HD: Nhập Thuốc\n"; // Hardcoded example
    }

    // Add a new LoaiHoaDon
    public boolean addLoaiHoaDon(String maLoaiHD, String tenLoaiHD) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return false;
//        PreparedStatement ps = null;
//        try {
//            ps = con.prepareStatement("INSERT INTO LoaiHoaDon (maLoaiHD, tenLoaiHD) VALUES (?, ?)");
//            ps.setString(1, maLoaiHD);
//            ps.setString(2, tenLoaiHD);
//            int result = ps.executeUpdate();
//            return result > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return false;
        return true; // Hardcoded result for example purposes
    }

    // Update LoaiHoaDon by maLoaiHD
    public boolean updateLoaiHoaDon(String maLoaiHD, String tenLoaiHD) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return false;
//        PreparedStatement ps = null;
//        try {
//            ps = con.prepareStatement("UPDATE LoaiHoaDon SET tenLoaiHD = ? WHERE maLoaiHD = ?");
//            ps.setString(1, tenLoaiHD);
//            ps.setString(2, maLoaiHD);
//            int result = ps.executeUpdate();
//            return result > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return false;
        return true; // Hardcoded result for example purposes
    }

    // Delete LoaiHoaDon by maLoaiHD
    public boolean deleteLoaiHoaDon(String maLoaiHD) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return false;
//        PreparedStatement ps = null;
//        try {
//            ps = con.prepareStatement("DELETE FROM LoaiHoaDon WHERE maLoaiHD = ?");
//            ps.setString(1, maLoaiHD);
//            int result = ps.executeUpdate();
//            return result > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return false;
        return true; // Hardcoded result for example purposes
    }
}
