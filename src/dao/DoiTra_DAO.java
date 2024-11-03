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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

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
                return new KhachHang_entity(rs.getString("sdtKH"), rs.getString("tenKH"));
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
                return new HoaDon_entity(rs.getString("maHD"), rs.getTimestamp("ngayLapHD").toLocalDateTime(), maHD);
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
    
    public boolean createHDDT(HoaDon_entity hddt) {
        return false;
    }
}
