package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.ChiTietNhapHang_entity;
import entity.LoaiSanPham_entity;
import entity.NhapHang_entity;
import entity.SanPham_entity;

public class ChiTietNhapHang_DAO {
	public boolean insert(ChiTietNhapHang_entity ctnh) {
	    Connection con = connectDB.accessDataBase();

	    String sql = "INSERT INTO ChiTietNhapHang VALUES (?,?,?,?,?,?)";

	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, ctnh.getNhapHang().getMaNhapHang());
	        pst.setString(2, ctnh.getSanPham().getMaSP());
	        pst.setDate(3, Date.valueOf(ctnh.getNgaySanXuat()));
	        pst.setDate(4, Date.valueOf(ctnh.getNgayHetHan())); 
	        pst.setDouble(5, ctnh.getSoLuong());
	        pst.setDouble(6, ctnh.getThanhTien());
	        int rowInserted = pst.executeUpdate();

	        pst.close();
	        con.close();

	        return rowInserted > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
    public ArrayList<ChiTietNhapHang_entity> searchChiTietNhapHang(String key) {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ChiTietNhapHang_entity> list = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT [maNhapHang],sp.[maSP],tenSP,[ngaySanXuat],[ngayHetHan],ctnh.soLuong,[thanhTien]\r\n"
            		+ "FROM ChiTietNhapHang ctnh join SanPham sp on ctnh.maSP = sp.maSP WHERE maNhapHang = ?");
            ps.setString(1, key);
            rs = ps.executeQuery();
            while (rs.next()) {
            	String maNH = rs.getString("maNhapHang");
            	String masp = rs.getString("maSP");
            	String tenSP = rs.getString("tenSP");
            	LocalDate ngaySanXuat = rs.getDate("ngaySanXuat").toLocalDate();
            	LocalDate ngayHetHan = rs.getDate("ngayHetHan").toLocalDate();
            	int soLuong = rs.getInt("soLuong");
            	double thanhTien = rs.getDouble("thanhTien");

            	NhapHang_entity nh = new NhapHang_entity(maNH);
            	SanPham_entity sp = new SanPham_entity(masp,tenSP);
	            ChiTietNhapHang_entity ctnh = new ChiTietNhapHang_entity(nh, sp, ngaySanXuat, ngayHetHan, soLuong, thanhTien);
	            list.add(ctnh);
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
    public int TongSoLuongNhapHang(String maNH) {
        Connection con = connectDB.accessDataBase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int tongSoLuong = 0;
        try {
            ps = con.prepareStatement("SELECT sum(soLuong) AS tongSoLuong FROM ChiTietNhapHang WHERE maNhapHang = ?");
            ps.setString(1, maNH);
            rs = ps.executeQuery();
            while (rs.next()) {
            	tongSoLuong = rs.getInt("tongSoLuong");
            	
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
        
        return tongSoLuong;
    }

}
