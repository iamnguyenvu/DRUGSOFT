package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.connectDB;

public class LoaiNhanVien_DAO {

    // Get full details of LoaiNhanVien by maLoaiNV (chỉ "NV" hoặc "QL")
    public String getLoaiNhanVienInfo(String maLoaiNV) {
        if (!maLoaiNV.equals("NV") && !maLoaiNV.equals("QL")) {
            throw new IllegalArgumentException("Mã Loại NV không hợp lệ. Chỉ chấp nhận 'NV' hoặc 'QL'.");
        }

//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String info = null;
//        try {
//            ps = con.prepareStatement("SELECT * FROM LoaiNhanVien WHERE maLoaiNV = ?");
//            ps.setString(1, maLoaiNV);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                info = "Mã Loại NV: " + maLoaiNV + 
//                       ", Tên Loại: " + rs.getString("tenLoaiNV");
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
        return "Mã Loại NV: " + maLoaiNV + ", Tên Loại: " + (maLoaiNV.equals("NV") ? "Nhân viên" : "Quản lý"); // Hardcoded example
    }

    // Get all types of employees (LoaiNhanVien)
    public String getAllLoaiNhanVien() {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        StringBuilder allInfo = new StringBuilder();
//        try {
//            ps = con.prepareStatement("SELECT * FROM LoaiNhanVien WHERE maLoaiNV IN ('NV', 'QL')");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                allInfo.append("Mã Loại NV: ").append(rs.getString("maLoaiNV"))
//                        .append(", Tên Loại: ").append(rs.getString("tenLoaiNV")).append("\n");
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
        return "Mã Loại NV: NV, Tên Loại: Nhân viên\nMã Loại NV: QL, Tên Loại: Quản lý"; // Hardcoded example
    }

    // Add a new LoaiNhanVien (Chỉ chấp nhận "NV" hoặc "QL")
    public boolean addLoaiNhanVien(String maLoaiNV, String tenLoaiNV) {
        if (!maLoaiNV.equals("NV") && !maLoaiNV.equals("QL")) {
            throw new IllegalArgumentException("Mã Loại NV không hợp lệ. Chỉ chấp nhận 'NV' hoặc 'QL'.");
        }

//        Connection con = connectDB.accessDataBase();
//        if (con == null) return false;
//        PreparedStatement ps = null;
//        try {
//            ps = con.prepareStatement("INSERT INTO LoaiNhanVien (maLoaiNV, tenLoaiNV) VALUES (?, ?)");
//            ps.setString(1, maLoaiNV);
//            ps.setString(2, tenLoaiNV);
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

    // Update LoaiNhanVien by maLoaiNV (Chỉ chấp nhận "NV" hoặc "QL")
    public boolean updateLoaiNhanVien(String maLoaiNV, String tenLoaiNV) {
        if (!maLoaiNV.equals("NV") && !maLoaiNV.equals("QL")) {
            throw new IllegalArgumentException("Mã Loại NV không hợp lệ. Chỉ chấp nhận 'NV' hoặc 'QL'.");
        }

//        Connection con = connectDB.accessDataBase();
//        if (con == null) return false;
//        PreparedStatement ps = null;
//        try {
//            ps = con.prepareStatement("UPDATE LoaiNhanVien SET tenLoaiNV = ? WHERE maLoaiNV = ?");
//            ps.setString(1, tenLoaiNV);
//            ps.setString(2, maLoaiNV);
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

    // Delete LoaiNhanVien by maLoaiNV (Chỉ chấp nhận "NV" hoặc "QL")
    public boolean deleteLoaiNhanVien(String maLoaiNV) {
        if (!maLoaiNV.equals("NV") && !maLoaiNV.equals("QL")) {
            throw new IllegalArgumentException("Mã Loại NV không hợp lệ. Chỉ chấp nhận 'NV' hoặc 'QL'.");
        }

//        Connection con = connectDB.accessDataBase();
//        if (con == null) return false;
//        PreparedStatement ps = null;
//        try {
//            ps = con.prepareStatement("DELETE FROM LoaiNhanVien WHERE maLoaiNV = ?");
//            ps.setString(1, maLoaiNV);
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
