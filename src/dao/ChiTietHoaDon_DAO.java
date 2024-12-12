package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.connectDB;
import entity.ChiTietHoaDon;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ChiTietHoaDon_DAO {
    
    public List<Object[]> getBaoCaoDoanhThu() {
    List<Object[]> danhSach = new ArrayList<>();

    String sql = "SELECT \n" +
                 "    hd.ngayLapHD AS NgayLapHoaDon,\n" +
                 "    nv.hoTenNV AS TenNhanVien,\n" +
                 "    cthd.maHD AS MaHoaDon,\n" +
                 "    kh.tenKH AS TenKhachHang,\n" +
                 "    cthd.thanhTien AS ThanhTien,\n" +
                 "    SUM(cthd.soLuongSanPham * sp.gia * sp.thue / 100) AS TienThue,\n" +
                 "    hd.tongTien AS TongTien,\n" +
                 "    SUM(cthd.soLuongSanPham * sp.giaNhap) AS GiaVon,\n" +
                 "    (SUM(cthd.soLuongSanPham * sp.gia) - SUM(cthd.soLuongSanPham * sp.giaNhap)) AS LoiNhuan\n" +
                 "FROM ChiTietHoaDon cthd\n" +
                 "JOIN HoaDon hd ON cthd.maHD = hd.maHD\n" +
                 "JOIN SanPham sp ON cthd.maSP = sp.maSP\n" +
                 "JOIN NhanVien nv ON hd.maNV = nv.maNV\n" +
                 "JOIN KhachHang kh ON hd.sdtKH = kh.sdtKH\n" +
                 "GROUP BY \n" +
                 "    nv.hoTenNV, \n" +
                 "    kh.tenKH, \n" +
                 "    hd.ngayLapHD, \n" +
                 "    sp.thue, \n" +
                 "    hd.tongTien,\n" +
                 "    cthd.thanhTien,\n" +
                 "    cthd.maHD\n" +
                 "ORDER BY hd.ngayLapHD DESC;";

    try (Connection conn = connectDB.accessDataBase();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            // Thêm từng hàng dữ liệu vào danh sách
            danhSach.add(new Object[] {
                rs.getString("NgayLapHoaDon"),
                rs.getString("TenNhanVien"),
                rs.getString("MaHoaDon"),
                rs.getString("TenKhachHang"),
                rs.getDouble("ThanhTien"),
                rs.getDouble("TienThue"),
                rs.getDouble("TongTien"),
                rs.getDouble("GiaVon"),
                rs.getDouble("LoiNhuan")
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return danhSach;
}



    
    public void loadBaoCaoDoanhThu(DefaultTableModel tableModel) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(
                "SELECT hd.ngayLap, nv.tenNV, hd.maHD, kh.tenKH, hd.thanhTien, hd.thueVAT, hd.tongTien, hd.giaVon, (hd.tongTien - hd.giaVon) AS loiNhuan " +
                "FROM HoaDon hd " +
                "JOIN NhanVien nv ON hd.maNV = nv.maNV " +
                "JOIN KhachHang kh ON hd.maKH = kh.maKH");
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] rowData = {
                    tableModel.getRowCount() + 1, // STT tự động tăng
                    rs.getDate("ngayLap"),
                    rs.getString("tenNV"),
                    rs.getString("maHD"),
                    rs.getString("tenKH"),
                    rs.getDouble("thanhTien"),
                    rs.getDouble("thueVAT"),
                    rs.getDouble("tongTien"),
                    rs.getDouble("giaVon"),
                    rs.getDouble("loiNhuan")
                };
                tableModel.addRow(rowData);
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
    }
    
    // Get details of ChiTietHoaDon by maCTHD
    public String getChiTietHoaDonInfo(String maCTHD) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String info = null;
//        try {
//            ps = con.prepareStatement("SELECT * FROM ChiTietHoaDon WHERE maCTHD = ?");
//            ps.setString(1, maCTHD);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                info = "Mã CTHD: " + maCTHD + 
//                       ", Số lượng sản phẩm: " + rs.getInt("soLuongSanPham") +
//                       ", Tổng tiền: " + rs.getDouble("tongTien") +
//                       ", Mã HD: " + rs.getString("maHD") +
//                       ", Mã SP: " + rs.getString("maSP");
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
        return "Mã CTHD: " + maCTHD + ", Số lượng sản phẩm: 10, Tổng tiền: 500000, Mã HD: HD123456, Mã SP: SP202310010001"; // Hardcoded example
    }

    // Get all ChiTietHoaDon
    public String getAllChiTietHoaDon() {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        StringBuilder allInfo = new StringBuilder();
//        try {
//            ps = con.prepareStatement("SELECT * FROM ChiTietHoaDon");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                allInfo.append("Mã CTHD: ").append(rs.getString("maCTHD"))
//                        .append(", Số lượng sản phẩm: ").append(rs.getInt("soLuongSanPham"))
//                        .append(", Tổng tiền: ").append(rs.getDouble("tongTien"))
//                        .append(", Mã HD: ").append(rs.getString("maHD"))
//                        .append(", Mã SP: ").append(rs.getString("maSP")).append("\n");
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
        return "Mã CTHD: CTHD001, Số lượng sản phẩm: 10, Tổng tiền: 500000, Mã HD: HD123456, Mã SP: SP202310010001\n" + 
               "Mã CTHD: CTHD002, Số lượng sản phẩm: 5, Tổng tiền: 250000, Mã HD: HD123457, Mã SP: SP202310010002\n"; // Hardcoded example
    }

    // Add a new ChiTietHoaDon
    public boolean addChiTietHoaDon(String maCTHD, int soLuongSanPham, double tongTien, String maHD, String maSP) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return false;
//        PreparedStatement ps = null;
//        try {
//            ps = con.prepareStatement("INSERT INTO ChiTietHoaDon (maCTHD, soLuongSanPham, tongTien, maHD, maSP) VALUES (?, ?, ?, ?, ?)");
//            ps.setString(1, maCTHD);
//            ps.setInt(2, soLuongSanPham);
//            ps.setDouble(3, tongTien);
//            ps.setString(4, maHD);
//            ps.setString(5, maSP);
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

    // Update ChiTietHoaDon by maCTHD
    public boolean updateChiTietHoaDon(String maCTHD, int soLuongSanPham, double tongTien, String maHD, String maSP) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return false;
//        PreparedStatement ps = null;
//        try {
//            ps = con.prepareStatement("UPDATE ChiTietHoaDon SET soLuongSanPham = ?, tongTien = ?, maHD = ?, maSP = ? WHERE maCTHD = ?");
//            ps.setInt(1, soLuongSanPham);
//            ps.setDouble(2, tongTien);
//            ps.setString(3, maHD);
//            ps.setString(4, maSP);
//            ps.setString(5, maCTHD);
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

    // Delete ChiTietHoaDon by maCTHD
    public boolean deleteChiTietHoaDon(String maCTHD) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return false;
//        PreparedStatement ps = null;
//        try {
//            ps = con.prepareStatement("DELETE FROM ChiTietHoaDon WHERE maCTHD = ?");
//            ps.setString(1, maCTHD);
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
