/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectDB;
import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonDoiTra_entity;
import entity.HoaDonDoiTra_entity;
import entity.HoaDon_entity;
import entity.KhachHang_entity;
import entity.NhanVien_entity;
import entity.SanPhamDoiTra_entity;
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
            ps = con.prepareStatement("SELECT maHD, maSP, soLuongSanPham, gia FROM ChiTietHoaDon WHERE maHD LIKE ?");
            ps.setString(1, key);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon cthd = new ChiTietHoaDon(rs.getString("maHD"), 
                        rs.getString("maSP"), rs.getInt("soLuongSanPham"), rs.getDouble("gia")); 
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
            ps = con.prepareStatement("SELECT maSP, tenSP, donViTinh, gia, hinhAnhSP FROM SanPham WHERE maSP LIKE ?");
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
    
    public NhanVien_entity getNhanVien(String maHD) {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT maNV, hotenNV FROM NhanVien WHERE maNV = (SELECT maNV FROM HoaDon WHERE maHD LIKE ?)");
            ps.setString(1, maHD);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new NhanVien_entity(rs.getString("maNV"), rs.getString("hotenNV"));
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
            ps = con.prepareStatement("SELECT tenKH, sdtKH FROM KhachHang WHERE sdtKH = (SELECT sdtKH FROM HoaDon WHERE maHD LIKE ?)");
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
            ps = con.prepareStatement("SELECT maHD, ngayLapHD, hinhThucThanhToan FROM HoaDon WHERE maHD = ?");
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
    
    public boolean createHDDT(HoaDonDoiTra_entity hd) {
        Connection con = connectDB.accessDataBase();
        PreparedStatement stmt = null;
        int n = 0;
        try {
                stmt = con.prepareStatement("INSERT INTO HoaDonDoiTra VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, hd.getMaDT());
                stmt.setString(2, hd.getMaHD());
                stmt.setTimestamp(3, Timestamp.valueOf(hd.getNgayDoiTra()));
                stmt.setDouble(4, hd.getTienTraLai());
                stmt.setDouble(5, hd.getTienKhachtraThem());
                stmt.setString(6, hd.getHinhThucThanhToan());
                stmt.setString(7, hd.getGhiChu());
                stmt.setString(8, hd.getMaNV());
                stmt.setDouble(9, hd.getTienGiam());
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
        
        String query = "SELECT MAX(maHD) FROM HoaDon WHERE maHD LIKE ?";
        String invoicePrefix = "HD" + currentDate;
        
        try (
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setString(1, invoicePrefix + "%");
            ResultSet rs = stmt.executeQuery();
            
            int counter = 0;
            if (rs.next()) {
                String maxInvoiceCode = rs.getString(1);
                if (maxInvoiceCode != null) {
                    String counterStr = maxInvoiceCode.substring(8); 
                    counter = Integer.parseInt(counterStr);
                }
            }
            
            counter++;
            
            String counterPart = String.format("%05d", counter);
            
            return invoicePrefix + counterPart;
        }
    }
    
    public static String generateExchangeInvoiceCode() throws SQLException {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        String currentDate = dateFormat.format(new Date());
        
        String query = "SELECT MAX(maDT) FROM HoaDonDoiTra WHERE maDT LIKE ?";
        String invoicePrefix = "DT" + currentDate;
        
        try (
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setString(1, invoicePrefix + "%");
            ResultSet rs = stmt.executeQuery();
            
            int counter = 0;
            if (rs.next()) {
                String maxInvoiceCode = rs.getString(1);
                if (maxInvoiceCode != null) {
                    String counterStr = maxInvoiceCode.substring(8); 
                    counter = Integer.parseInt(counterStr);
                }
            }
            
            counter++;
            
            String counterPart = String.format("%05d", counter);
            
            return invoicePrefix + counterPart;
        }
    }
    
    public boolean updateSLSP(String maSP, int soLuong) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return false; 
        PreparedStatement ps = null;

        try {
            String sql = "UPDATE SanPham SET soLuong = soLuong - ? WHERE maSP = ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, soLuong);
            ps.setString(2, maSP); 

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
            }
        }

        return false; 
    }

    public boolean insertSPDT(SanPhamDoiTra_entity sp) {
        Connection con = connectDB.accessDataBase();
        PreparedStatement stmt = null;
        int n = 0;
        try {
                stmt = con.prepareStatement("INSERT INTO SanPhamDoiTra VALUES(?, ?, ?, ?, ?)");
                stmt.setString(1, sp.getMaDT());
                stmt.setString(2, sp.getMaSP());
                stmt.setInt(3, sp.getSoLuong());
                stmt.setDouble(4, sp.getDonGia());
                stmt.setString(5, sp.getTrangThai());
                stmt.setString(6, sp.getVanDe());
                stmt.setDouble(7, sp.getTinhTrang());
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

    public boolean insertCTHDDT(ChiTietHoaDonDoiTra_entity hd) {
        Connection con = connectDB.accessDataBase();
        PreparedStatement stmt = null;
        int n = 0;
        try {
                stmt = con.prepareStatement("INSERT INTO ChiTietHoaDonDoiTra VALUES(?, ?, ?, ?, ?, ?)");
                stmt.setString(1, hd.getMaSP());
                stmt.setString(2, hd.getMaDT());
                stmt.setInt(3, hd.getSoLuong());
                stmt.setDouble(4, hd.getChietKhau());
                stmt.setDouble(5, hd.getGia());
                stmt.setString(6, hd.getLoaiDoiTra());
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
}
