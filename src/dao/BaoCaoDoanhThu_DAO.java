/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectDB;
import entity.BaoCaoDoanhThu_enity;
import entity.LoaiSanPham_entity;
import entity.SanPham_entity;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.PreparedStatement;

/**
 *
 * @author Vovan
 */
public class BaoCaoDoanhThu_DAO {
    public ArrayList<BaoCaoDoanhThu_enity> getAllBaoCaoDoanhThu7Ngay() {
	    ArrayList<BaoCaoDoanhThu_enity> dsBC = new ArrayList<BaoCaoDoanhThu_enity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT \n" +
                "    CAST(ngayLapHD AS DATE) AS thoiGian,\n" +
                "    SUM(tongTien) AS tongDoanhThu,\n" +
                "    SUM(sp.giaNhap * soLuong * (1 + thue / 100)) AS tongChiPhi,\n" +
                "    (SUM(tongTien) - SUM(sp.giaNhap * soLuong * (1 + thue / 100))) AS loiNhuan,\n" +
                "    COUNT(DISTINCT hd.maHD) AS tongSoLuongGiaoDich\n" +
                "FROM \n" +
                "    HoaDon hd\n" +
                "JOIN \n" +
                "    chiTietHoaDon cthd ON hd.maHD = cthd.maHD\n" +
                "JOIN \n" +
                "    SanPham sp ON cthd.maSP = sp.maSP\n" +
                "WHERE \n" +
                "    ngayLapHD >= DATEADD(DAY, -7, GETDATE())  \n" +
                "GROUP BY \n" +
                "    CAST(ngayLapHD AS DATE)\n" +
                "ORDER BY \n" +
                "    thoiGian;";
	    try {
	        java.sql.Statement st = con.createStatement();  
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String thoiGian = rs.getString("thoiGian");
	            double doanhThu = rs.getDouble("tongDoanhThu");
	            double chiPhi = rs.getDouble("tongChiPhi");
	            double loiNhuan = rs.getDouble("loiNhuan");
	            int soLuongGD = rs.getInt("tongSoLuongGiaoDich");
	            BaoCaoDoanhThu_enity bc = new BaoCaoDoanhThu_enity(thoiGian, doanhThu, chiPhi, loiNhuan, soLuongGD);
	            dsBC.add(bc);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsBC;
	}
    
    public ArrayList<BaoCaoDoanhThu_enity> getAllBaoCaoDoanhThu30Ngay() {
	    ArrayList<BaoCaoDoanhThu_enity> dsBC = new ArrayList<BaoCaoDoanhThu_enity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT \n" +
                "    CAST(ngayLapHD AS DATE) AS thoiGian,\n" +
                "    SUM(tongTien) AS tongDoanhThu,\n" +
                "    SUM(sp.giaNhap * soLuong * (1 + thue / 100)) AS tongChiPhi,\n" +
                "    (SUM(tongTien) - SUM(sp.giaNhap * soLuong * (1 + thue / 100))) AS loiNhuan,\n" +
                "    COUNT(DISTINCT hd.maHD) AS tongSoLuongGiaoDich\n" +
                "FROM \n" +
                "    HoaDon hd\n" +
                "JOIN \n" +
                "    chiTietHoaDon cthd ON hd.maHD = cthd.maHD\n" +
                "JOIN \n" +
                "    SanPham sp ON cthd.maSP = sp.maSP\n" +
                "WHERE \n" +
                "    ngayLapHD >= DATEADD(DAY, -30, GETDATE())  \n" +
                "GROUP BY \n" +
                "    CAST(ngayLapHD AS DATE)\n" +
                "ORDER BY \n" +
                "    thoiGian;";
	    try {
	        java.sql.Statement st = con.createStatement();  
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String thoiGian = rs.getString("thoiGian");
	            double doanhThu = rs.getDouble("tongDoanhThu");
	            double chiPhi = rs.getDouble("tongChiPhi");
	            double loiNhuan = rs.getDouble("loiNhuan");
	            int soLuongGD = rs.getInt("tongSoLuongGiaoDich");
	            BaoCaoDoanhThu_enity bc = new BaoCaoDoanhThu_enity(thoiGian, doanhThu, chiPhi, loiNhuan, soLuongGD);
	            dsBC.add(bc);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsBC;
	}
    
    public ArrayList<BaoCaoDoanhThu_enity> getAllBaoCaoDoanhThu90Ngay() {
	    ArrayList<BaoCaoDoanhThu_enity> dsBC = new ArrayList<BaoCaoDoanhThu_enity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT \n" +
                "    CAST(ngayLapHD AS DATE) AS thoiGian,\n" +
                "    SUM(tongTien) AS tongDoanhThu,\n" +
                "    SUM(sp.giaNhap * soLuong * (1 + thue / 100)) AS tongChiPhi,\n" +
                "    (SUM(tongTien) - SUM(sp.giaNhap * soLuong * (1 + thue / 100))) AS loiNhuan,\n" +
                "    COUNT(DISTINCT hd.maHD) AS tongSoLuongGiaoDich\n" +
                "FROM \n" +
                "    HoaDon hd\n" +
                "JOIN \n" +
                "    chiTietHoaDon cthd ON hd.maHD = cthd.maHD\n" +
                "JOIN \n" +
                "    SanPham sp ON cthd.maSP = sp.maSP\n" +
                "WHERE \n" +
                "    ngayLapHD >= DATEADD(DAY, -90, GETDATE())  \n" +
                "GROUP BY \n" +
                "    CAST(ngayLapHD AS DATE)\n" +
                "ORDER BY \n" +
                "    thoiGian;";
	    try {
	        java.sql.Statement st = con.createStatement();  
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String thoiGian = rs.getString("thoiGian");
	            double doanhThu = rs.getDouble("tongDoanhThu");
	            double chiPhi = rs.getDouble("tongChiPhi");
	            double loiNhuan = rs.getDouble("loiNhuan");
	            int soLuongGD = rs.getInt("tongSoLuongGiaoDich");
	            BaoCaoDoanhThu_enity bc = new BaoCaoDoanhThu_enity(thoiGian, doanhThu, chiPhi, loiNhuan, soLuongGD);
	            dsBC.add(bc);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsBC;
	}
    
    public ArrayList<BaoCaoDoanhThu_enity> getAllBaoCaoDoanhThu365Ngay() {
	    ArrayList<BaoCaoDoanhThu_enity> dsBC = new ArrayList<BaoCaoDoanhThu_enity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT \n" +
                "    CAST(ngayLapHD AS DATE) AS thoiGian,\n" +
                "    SUM(tongTien) AS tongDoanhThu,\n" +
                "    SUM(sp.giaNhap * soLuong * (1 + thue / 100)) AS tongChiPhi,\n" +
                "    (SUM(tongTien) - SUM(sp.giaNhap * soLuong * (1 + thue / 100))) AS loiNhuan,\n" +
                "    COUNT(DISTINCT hd.maHD) AS tongSoLuongGiaoDich\n" +
                "FROM \n" +
                "    HoaDon hd\n" +
                "JOIN \n" +
                "    chiTietHoaDon cthd ON hd.maHD = cthd.maHD\n" +
                "JOIN \n" +
                "    SanPham sp ON cthd.maSP = sp.maSP\n" +
                "WHERE \n" +
                "    ngayLapHD >= DATEADD(DAY, -365, GETDATE())  \n" +
                "GROUP BY \n" +
                "    CAST(ngayLapHD AS DATE)\n" +
                "ORDER BY \n" +
                "    thoiGian;";
	    try {
	        java.sql.Statement st = con.createStatement();  
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String thoiGian = rs.getString("thoiGian");
	            double doanhThu = rs.getDouble("tongDoanhThu");
	            double chiPhi = rs.getDouble("tongChiPhi");
	            double loiNhuan = rs.getDouble("loiNhuan");
	            int soLuongGD = rs.getInt("tongSoLuongGiaoDich");
	            BaoCaoDoanhThu_enity bc = new BaoCaoDoanhThu_enity(thoiGian, doanhThu, chiPhi, loiNhuan, soLuongGD);
	            dsBC.add(bc);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsBC;
	}
    
    public ArrayList<BaoCaoDoanhThu_enity> getAllBaoCaoDoanhThuToanTG() {
	    ArrayList<BaoCaoDoanhThu_enity> dsBC = new ArrayList<BaoCaoDoanhThu_enity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT \n" +
                "    CAST(ngayLapHD AS DATE) AS thoiGian,\n" +
                "    SUM(tongTien) AS tongDoanhThu,\n" +
                "    SUM(sp.giaNhap * soLuong * (1 + thue / 100)) AS tongChiPhi,\n" +
                "    (SUM(tongTien) - SUM(sp.giaNhap * soLuong * (1 + thue / 100))) AS loiNhuan,\n" +
                "    COUNT(DISTINCT hd.maHD) AS tongSoLuongGiaoDich\n" +
                "FROM \n" +
                "    HoaDon hd\n" +
                "JOIN \n" +
                "    chiTietHoaDon cthd ON hd.maHD = cthd.maHD\n" +
                "JOIN \n" +
                "    SanPham sp ON cthd.maSP = sp.maSP\n" +
                "GROUP BY \n" +
                "    CAST(ngayLapHD AS DATE)\n" +
                "ORDER BY \n" +
                "    thoiGian;";
	    try {
	        java.sql.Statement st = con.createStatement();  
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String thoiGian = rs.getString("thoiGian");
	            double doanhThu = rs.getDouble("tongDoanhThu");
	            double chiPhi = rs.getDouble("tongChiPhi");
	            double loiNhuan = rs.getDouble("loiNhuan");
	            int soLuongGD = rs.getInt("tongSoLuongGiaoDich");
	            BaoCaoDoanhThu_enity bc = new BaoCaoDoanhThu_enity(thoiGian, doanhThu, chiPhi, loiNhuan, soLuongGD);
	            dsBC.add(bc);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsBC;
	}
    
    public ArrayList<BaoCaoDoanhThu_enity> getAllBaoCaoDoanhThuTuyChinh(java.util.Date from, java.util.Date to) {
    ArrayList<BaoCaoDoanhThu_enity> dsBC = new ArrayList<>();
    Connection con = connectDB.accessDataBase();
    if (con == null) {
        return null;
    }
    String sql = "SELECT \n" +
            "    CAST(ngayLapHD AS DATE) AS thoiGian,\n" +
            "    SUM(tongTien) AS tongDoanhThu,\n" +
            "    SUM(sp.giaNhap * soLuong * (1 + thue / 100)) AS tongChiPhi,\n" +
            "    (SUM(tongTien) - SUM(sp.giaNhap * soLuong * (1 + thue / 100))) AS loiNhuan,\n" +
            "    COUNT(DISTINCT hd.maHD) AS tongSoLuongGiaoDich\n" +
            "FROM \n" +
            "    HoaDon hd\n" +
            "JOIN \n" +
            "    chiTietHoaDon cthd ON hd.maHD = cthd.maHD\n" +
            "JOIN \n" +
            "    SanPham sp ON cthd.maSP = sp.maSP\n" +
            "WHERE \n" +
            "    ngayLapHD BETWEEN ? AND ?\n" +
            "GROUP BY \n" +
            "    CAST(ngayLapHD AS DATE)\n" +
            "ORDER BY \n" +
            "    thoiGian;";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setDate(1, new java.sql.Date(from.getTime()));
        ps.setDate(2, new java.sql.Date(to.getTime()));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String thoiGian = rs.getString("thoiGian");
            double doanhThu = rs.getDouble("tongDoanhThu");
            double chiPhi = rs.getDouble("tongChiPhi");
            double loiNhuan = rs.getDouble("loiNhuan");
            int soLuongGD = rs.getInt("tongSoLuongGiaoDich");
            BaoCaoDoanhThu_enity bc = new BaoCaoDoanhThu_enity(thoiGian, doanhThu, chiPhi, loiNhuan, soLuongGD);
            dsBC.add(bc);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return dsBC;
}

}
