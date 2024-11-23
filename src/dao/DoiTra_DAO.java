/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon_entity;
import entity.KhachHang_entity;
import entity.NhanVien_entity;
import entity.SanPham_entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author HP
 */
public class DoiTra_DAO {
    public ArrayList<ChiTietHoaDon> searchCTHD(String key) {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ChiTietHoaDon> listCTHD = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM ChiTietHoaDon WHERE maHD LIKE ?");
            ps.setString(1, key);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon cthd = new ChiTietHoaDon(rs.getString("maHD"), 
                        rs.getString("maSP"), rs.getInt("soLuongSanPham"), rs.getDouble("thanhTien")); 
                listCTHD.add(cthd);
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
        
        return listCTHD;
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
                return new SanPham_entity(rs.getString("maSP"), rs.getString("tenSP"), rs.getDouble("gia"));
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
    
    public NhanVien_entity getNhanVien(String maHD) {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM NhanVien WHERE maNV = (SELECT maNV FROM HoaDon WHERE maHD LIKE ?)");
            ps.setString(1, maHD);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new NhanVien_entity(rs.getString("maNV"), rs.getString("hotenNV"), maHD, maHD, maHD, maHD, null, false, null, null, null, maHD, maHD);
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
    
    public KhachHang_entity getKhachHang(String maHD) {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM KhachHang WHERE sdtKH = (SELECT sdtKH FROM HoaDon WHERE maHD LIKE ?)");
            ps.setString(1, maHD);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new KhachHang_entity(rs.getString("tenKH"), rs.getString("sdtKH"));
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
    
    public HoaDon_entity getHoaDon(String maHD) {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM HoaDon WHERE maHD = ?");
            ps.setString(1, maHD);
            rs = ps.executeQuery();
            if (rs.next()) {

                return new HoaDon_entity(rs.getString("maHD"), rs.getTimestamp("ngayLapHD").toLocalDateTime(), rs.getString("hinhThucThanhToan"));
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
    
    public static String generateInvoiceCode() throws SQLException {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        String currentDate = dateFormat.format(new Date());
        
        // Truy vấn để tìm số thứ tự hóa đơn cao nhất trong ngày hiện tại
        String query = "SELECT MAX(maHD) FROM HoaDon WHERE maHD LIKE ?";
        String invoicePrefix = "HD" + currentDate;
        
        try (
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            // Đặt tham số để tìm các hóa đơn trong ngày hiện tại
            stmt.setString(1, invoicePrefix + "%");
            ResultSet rs = stmt.executeQuery();
            
            // Lấy số thứ tự hóa đơn cao nhất
            int counter = 0;
            if (rs.next()) {
                String maxInvoiceCode = rs.getString(1);
                if (maxInvoiceCode != null) {
                    // Trích xuất phần số thứ tự (xxxxx) từ mã hóa đơn
                    String counterStr = maxInvoiceCode.substring(8);  // Từ vị trí 8 trở đi là số thứ tự
                    counter = Integer.parseInt(counterStr);
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
