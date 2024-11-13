/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

//import java.security.Timestamp;
import connectDB.connectDB;
import entity.HoaDon_entity;
import entity.KhachHang_entity;
import entity.SanPham_entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author HP
 */
public class BanHang_DAO {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    
    public ArrayList<SanPham_entity> searchSanPham(String key) {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SanPham_entity> listSP = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT TOP 8 * FROM SanPham WHERE tenSP LIKE ? OR maSP LIKE ?");
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham_entity sp = new SanPham_entity(rs.getString("maSP"), rs.getString("tenSP"), rs.getString("donViTinh"), rs.getDouble("gia"), rs.getString("hinhAnhSP"), rs.getInt("soLuong")); 
                listSP.add(sp);
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
        
        return listSP;
    }
    
    public ArrayList<KhachHang_entity> searchKhachHang(String sdt) {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<KhachHang_entity> listKH = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT TOP 8 * FROM KhachHang WHERE sdtKH LIKE ?");
            ps.setString(1, sdt + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang_entity kh = new KhachHang_entity(rs.getString("tenKH"), rs.getString("sdtKH"), rs.getInt("diemThuong"), rs.getString("gioiTinh")); 
                listKH.add(kh);
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
        
        return listKH;
    }
    
    
    
    public KhachHang_entity getKhachHang(String sdt) {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM KhachHang WHERE sdtKH LIKE ?");
            ps.setString(1, sdt);
            rs = ps.executeQuery();
            if (rs.next()) {
                KhachHang_entity kh = new KhachHang_entity(rs.getString("tenKH"), rs.getString("sdtKH"), 
                        rs.getInt("diemThuong"), rs.getString("gioiTinh")); 
                return kh;
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
        return null;
    }
    
    public boolean createHD(HoaDon_entity hd) {
        Connection con = connectDB.accessDataBase();
        PreparedStatement stmt = null;
        int n = 0;
        try {
                stmt = con.prepareStatement("INSERT INTO HoaDon VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, hd.getMaHD());
                stmt.setTimestamp(2, Timestamp.valueOf(hd.getNgayLapHD()));
                stmt.setDouble(3, hd.getTongTien());
                stmt.setDouble(4, hd.getTienGiam());
                stmt.setString(5, hd.getHinhThucThanhToan());
                stmt.setBoolean(6, true);
                stmt.setString(7, hd.getGhiChu());
                stmt.setString(8, hd.getSdtKH());
                stmt.setString(9, hd.getMaNV());
                stmt.setString(10, hd.getMaLoaiHoaDon());
                n = stmt.executeUpdate();
        } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
        } finally {
                 try {
             stmt.close();
         } catch (SQLException e) {
              e.printStackTrace();
         }
        }
        return n>0;
    }
    
    public SanPham_entity getSP(String maSP) {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM SanPham WHERE maSP LIKE ?");
            ps.setString(1, maSP);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new SanPham_entity(rs.getString("maSP"), rs.getString("tenSP"), 
                        rs.getString("donViTinh"), rs.getDouble("gia"), rs.getString("hinhAnhSP")); 
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
        return null;
    }
    
    public static String generateInvoiceCode() throws SQLException {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        String currentDate = dateFormat.format(new Date());
        
        String query = "SELECT MAX(maHD) FROM HoaDon WHERE maHD LIKE ?";
        String invoicePrefix = "HD" + currentDate;
        
        try (
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setString(1, invoicePrefix + "%");
            ResultSet rs = stmt.executeQuery();
            
            // Lấy số thứ tự hóa đơn cao nhất
            int counter = 0;
            if (rs.next()) {
                String maxInvoiceCode = rs.getString(1);
                if (maxInvoiceCode != null) {
                    // Trích xuất phần số thứ tự (xxxxx) từ mã hóa đơn
                    String counterStr = maxInvoiceCode.substring(8);  // Từ vị trí 8 trở đi là số thứ tự
                    try {
                        counter = Integer.parseInt(counterStr);  // Chuyển sang kiểu số nguyên
                    } catch (NumberFormatException e) {
                        counter = 0;  // Nếu không parse được (không có mã hóa đơn nào), thì bắt đầu từ 0
                    }
                }
            }
            
            // Tăng số thứ tự lên 1
            counter++;
            
            // Đảm bảo số thứ tự có đủ 5 chữ số
            String counterPart = String.format("%05d", counter);
            
            // Tạo mã hóa đơn mới
            return invoicePrefix + counterPart;
        }
    }
    
    
    
}
