/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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
                KhachHang_entity kh = new KhachHang_entity(rs.getString("sdtKH"), rs.getString("tenKH"), rs.getInt("diemThuong"), rs.getString("gioiTinh")); 
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
                KhachHang_entity kh = new KhachHang_entity(rs.getString("tenKH"), rs.getString("sdtKH"), rs.getInt("diemThuong"), rs.getString("gioiTinh")); 
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
    
}
