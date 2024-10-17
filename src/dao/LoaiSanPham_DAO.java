package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.connectDB;

public class LoaiSanPham_DAO {

    // Get full details of LoaiSanPham by maLoaiSP (chỉ nhận "TH", "TPCN", "TBYT")
    public String getLoaiSanPhamInfo(String maLoaiSP) {
        if (!maLoaiSP.equals("Thuoc") && !maLoaiSP.equals("TPCN") && !maLoaiSP.equals("TBYT")) {
            throw new IllegalArgumentException("Mã Loại SP không hợp lệ. Chỉ chấp nhận 'TH', 'TPCN' hoặc 'TBYT'.");
        }

//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String info = null;
//        try {
//            ps = con.prepareStatement("SELECT * FROM LoaiSanPham WHERE maLoaiSP = ?");
//            ps.setString(1, maLoaiSP);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                info = "Mã Loại SP: " + maLoaiSP + 
//                       ", Tên Loại: " + rs.getString("tenLoaiSP");
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
        return "Mã Loại SP: " + maLoaiSP + ", Tên Loại: " + getLoaiTen(maLoaiSP); // Hardcoded example
    }

    // Get all types of products (LoaiSanPham)
    public String getAllLoaiSanPham() {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        StringBuilder allInfo = new StringBuilder();
//        try {
//            ps = con.prepareStatement("SELECT * FROM LoaiSanPham WHERE maLoaiSP IN ('TH', 'TPCN', 'TBYT')");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                allInfo.append("Mã Loại SP: ").append(rs.getString("maLoaiSP"))
//                        .append(", Tên Loại: ").append(rs.getString("tenLoaiSP")).append("\n");
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
        return "Mã Loại SP: TH, Tên Loại: Thuốc\nMã Loại SP: TPCN, Tên Loại: Thực phẩm chức năng\nMã Loại SP: TBYT, Tên Loại: Thiết bị y tế"; // Hardcoded example
    }

    // Add a new LoaiSanPham (Chỉ chấp nhận "TH", "TPCN", "TBYT")
    public boolean addLoaiSanPham(String maLoaiSP, String tenLoaiSP) {
        if (!maLoaiSP.equals("TH") && !maLoaiSP.equals("TPCN") && !maLoaiSP.equals("TBYT")) {
            throw new IllegalArgumentException("Mã Loại SP không hợp lệ. Chỉ chấp nhận 'TH', 'TPCN' hoặc 'TBYT'.");
        }

//        Connection con = connectDB.accessDataBase();
//        if (con == null) return false;
//        PreparedStatement ps = null;
//        try {
//            ps = con.prepareStatement("INSERT INTO LoaiSanPham (maLoaiSP, tenLoaiSP) VALUES (?, ?)");
//            ps.setString(1, maLoaiSP);
//            ps.setString(2, tenLoaiSP);
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

    // Update LoaiSanPham by maLoaiSP (Chỉ chấp nhận "TH", "TPCN", "TBYT")
    public boolean updateLoaiSanPham(String maLoaiSP, String tenLoaiSP) {
        if (!maLoaiSP.equals("TH") && !maLoaiSP.equals("TPCN") && !maLoaiSP.equals("TBYT")) {
            throw new IllegalArgumentException("Mã Loại SP không hợp lệ. Chỉ chấp nhận 'TH', 'TPCN' hoặc 'TBYT'.");
        }

//        Connection con = connectDB.accessDataBase();
//        if (con == null) return false;
//        PreparedStatement ps = null;
//        try {
//            ps = con.prepareStatement("UPDATE LoaiSanPham SET tenLoaiSP = ? WHERE maLoaiSP = ?");
//            ps.setString(1, tenLoaiSP);
//            ps.setString(2, maLoaiSP);
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

    // Delete LoaiSanPham by maLoaiSP (Chỉ chấp nhận "TH", "TPCN", "TBYT")
    public boolean deleteLoaiSanPham(String maLoaiSP) {
        if (!maLoaiSP.equals("TH") && !maLoaiSP.equals("TPCN") && !maLoaiSP.equals("TBYT")) {
            throw new IllegalArgumentException("Mã Loại SP không hợp lệ. Chỉ chấp nhận 'TH', 'TPCN' hoặc 'TBYT'.");
        }

//        Connection con = connectDB.accessDataBase();
//        if (con == null) return false;
//        PreparedStatement ps = null;
//        try {
//            ps = con.prepareStatement("DELETE FROM LoaiSanPham WHERE maLoaiSP = ?");
//            ps.setString(1, maLoaiSP);
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

    // Helper method to return the name of the product type
    private String getLoaiTen(String maLoaiSP) {
        switch (maLoaiSP) {
            case "TH":
                return "Thuốc";
            case "TPCN":
                return "Thực phẩm chức năng";
            case "TBYT":
                return "Thiết bị y tế";
            default:
                return "Không xác định";
        }
    }
}