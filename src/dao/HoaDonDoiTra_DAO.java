/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectDB;
import entity.HoaDonDoiTra_entity;
import entity.HoaDon_entity;
import entity.NhapHang_entity;
import entity.SanPhamDoiTra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class HoaDonDoiTra_DAO {
    //Phân trang
    public static ArrayList<HoaDon_entity> getPagedData(int limit, int offset) {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<HoaDon_entity> res = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM HoaDon LIMIT ? OFFSET ? AND maLoaiHD IN ('DoiSanPham', 'TraSanPham')");
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            rs = ps.executeQuery();
            while (rs.next()) {
//                res.add(new HoaDon_entity());
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
        
        return res;
    }
    public ArrayList<HoaDonDoiTra_entity> getALLHDDT() {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<HoaDonDoiTra_entity> listSP = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM HoaDonDoiTra\r\n"
            		+ "order by ngayDoiTra");
            rs = ps.executeQuery();
            while (rs.next()) {
            	String maDT = rs.getString("maDT");
            	String maHD = rs.getString("maHD");
            	LocalDateTime ngayDoiTra = rs.getTimestamp("ngayDoiTra").toLocalDateTime();
	            double  tienTraLai = rs.getDouble("tienTraLai");
	            double  tienKhachTraThem = rs.getDouble("tienKhachTraThem");

	            HoaDonDoiTra_entity hd = new HoaDonDoiTra_entity(maDT, maHD, ngayDoiTra, tienTraLai, tienKhachTraThem);
                listSP.add(hd);
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
    public HoaDonDoiTra_entity timKiem(String key) {
        if (key == null || key.trim().isEmpty()) return null;

        String sql = "SELECT * FROM HoaDonDoiTra WHERE maDT = ?";
        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, key);

            try (ResultSet rs = ps.executeQuery()) {
            	while (rs.next()) {
                    String maDT = rs.getString("maDT");
                    String maHD = rs.getString("maHD");
                    LocalDateTime ngayDoiTra = rs.getTimestamp("ngayDoiTra").toLocalDateTime();
                    double tienTraLai = rs.getDouble("tienTraLai");
                    double tienKhachTraThem = rs.getDouble("tienKhachTraThem");

                    return new HoaDonDoiTra_entity(maDT, maHD, ngayDoiTra, tienTraLai, tienKhachTraThem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }
    public ArrayList<SanPhamDoiTra> timKiemSPDT(String key) {
        ArrayList<SanPhamDoiTra> listSP = new ArrayList<>();
        
        String sql = "SELECT spdt.maDT, tenSP,spdt.soLuong,ngayDoiTra,chietKhau,thanhTien,loaiDoiTra,trangThai\r\n"
        		+ "FROM SanPhamDoiTra spdt JOIN HoaDonDoiTra hddt on spdt.maDT = hddt.maDT \r\n"
        		+ "JOIN SanPham sp on sp.maSP = spdt.maSP\r\n"
        		+ "where spdt.maDT = ?";
        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, key);

            try (ResultSet rs = ps.executeQuery()) {
            	while (rs.next()) {
                    String maDT = rs.getString("maDT");
                    String maSP = rs.getString("tenSP");
                    int soLuong = rs.getInt("soLuong");
                    LocalDateTime ngayDoiTra = rs.getTimestamp("ngayDoiTra").toLocalDateTime();
                    double chietKhau = rs.getDouble("chietKhau");
                    double thanhTien = rs.getDouble("thanhTien");
                    String loaiDoiTra = rs.getString("loaiDoiTra");
                    String trangThai = rs.getString("trangThai");

                    SanPhamDoiTra spdt = new SanPhamDoiTra(maDT, soLuong, maSP, trangThai, chietKhau, thanhTien, loaiDoiTra);
                    listSP.add(spdt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return listSP;
    }

    public int soLuongSPTra(String key) {

    	int tongSoLuong = 0;
        String sql = "SELECT SUM(soLuong) AS tongSoLuong\r\n"
        		+ "FROM SanPhamDoiTra\r\n"
        		+ "WHERE [loaiDoiTra]  = 'TraSanPham' AND maDT = ?";
        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, key);

            try (ResultSet rs = ps.executeQuery()) {
            	while (rs.next()) {
                   tongSoLuong = rs.getInt("tongSoLuong");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return tongSoLuong;
    }
    public int soLuongSPMua(String key) {

    	int tongSoLuong = 0;
        String sql = "SELECT SUM(soLuong) AS tongSoLuong\r\n"
        		+ "FROM SanPhamDoiTra\r\n"
        		+ "WHERE [loaiDoiTra]  = 'MuaSanPham' AND maDT = ?";
        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, key);

            try (ResultSet rs = ps.executeQuery()) {
            	while (rs.next()) {
                   tongSoLuong = rs.getInt("tongSoLuong");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return tongSoLuong;
    }
}
