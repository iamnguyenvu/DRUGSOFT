/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectDB;
import entity.SanPham_entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import nguyenvu.model.ModelDoanhThu;
import nguyenvu.model.ModelSellTransaction;
import nguyenvu.model.ModelTransaction;

/**
 *
 * @author HP
 */
public class TrangChu_DAO {
    public static ArrayList<ModelTransaction> getListLastTransaction() {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ModelTransaction> list = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT TOP 10 LNV.tenLoaiNV, NV.hotenNV, thanhTien = tongTien - tienGiam \n" +
                "FROM HoaDon HD JOIN NhanVien NV ON HD.maNV = NV.maNV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV\n" +
                "ORDER BY ngayLapHD DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ModelTransaction(rs.getString("tenLoaiNV") ,rs.getString("hotenNV"), rs.getDouble("thanhTien"))); 
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
        return list;
    }
    
    public static ArrayList<ModelSellTransaction> getListLastSellTransaction() {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ModelSellTransaction> list = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT TOP 10 LNV.tenLoaiNV, NV.hotenNV, thanhTien = tongTien - tienGiam, HD.ngayLapHD, HD.maLoaiHD\n" +
                "FROM HoaDon HD JOIN NhanVien NV ON HD.maNV = NV.maNV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV\n" +
                "ORDER BY ngayLapHD DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ModelSellTransaction(rs.getString("tenLoaiNV") ,rs.getString("hotenNV"), rs.getString("maLoaiHD"), rs.getDouble("thanhTien"), rs.getTimestamp("ngayLapHD").toLocalDateTime())); 
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
        return list;
    }
    
    public static ArrayList<SanPham_entity> getListSPSapHetHang() {
        Connection con = connectDB.accessDataBase();
        if (con == null) return null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SanPham_entity> listSP = new ArrayList<>();

        try {
            ps = con.prepareStatement("select * from SanPham\n" +
                "where\n" +
                "	(maLoaiSP = 'Thuoc' and soLuong < 50) \n" +
                "	or (maLoaiSP = 'TPCN' and soLuong < 30)\n" +
                "	or (maLoaiSP = 'TBYT' and soLuong < 10)");
            rs = ps.executeQuery();

            while (rs.next()) {
                listSP.add(new SanPham_entity(rs.getString("maSP"), rs.getString("tenSP"), 
                        rs.getString("donViTinh"), rs.getDouble("gia"), rs.getString("hinhAnhSP")));
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
    
    public static ArrayList<SanPham_entity> getListSPSapHetHan() {
        Connection con = connectDB.accessDataBase();
        if (con == null) return null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SanPham_entity> listSP = new ArrayList<>();

        try {
            ps = con.prepareStatement("select sp.maSP,tenSP,donViTinh,gia,hinhAnhSP FROM SanPham sp join ChiTietNhapHang ctnh on sp.maSP = ctnh.maSP\n" +
                "where DATEDIFF(DAY, GETDATE(), ngayHetHan) < 30");
            rs = ps.executeQuery();

            while (rs.next()) {
                listSP.add(new SanPham_entity(rs.getString("maSP"), rs.getString("tenSP"), 
                        rs.getString("donViTinh"), rs.getDouble("gia"), rs.getString("hinhAnhSP")));
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
    
    public static ModelDoanhThu getDoanhThuThang() {
        Connection con = connectDB.accessDataBase();
        if (con == null) return null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("select count(*) as soGiaoDich, sum(tongTien - tienGiam) as doanhThu from HoaDon\n" +
                        "where MONTH(ngayLapHD) = MONTH(getdate())");
            rs = ps.executeQuery();

            if (rs.next()) {
                return new ModelDoanhThu(rs.getDouble("doanhThu"), rs.getInt("soGiaoDich"));
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
