package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.connectDB;

public class ThongKe_DAO {
	public int soSanPhamSapHetHan() {
	    Connection con = connectDB.accessDataBase();
	    if (con == null) return 0;
	    
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int soSanPham = 0;
	    
	    try {
	        String sql = "SELECT COUNT(*) AS TongSanPhamSapHetHan FROM SanPham WHERE DATEDIFF(DAY, GETDATE(), ngayHetHan) < 30";
	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            soSanPham = rs.getInt("TongSanPhamSapHetHan");
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
	    
	    return soSanPham;
	}
	public int soSanPhamSapHetHang() {
	    Connection con = connectDB.accessDataBase();
	    if (con == null) return 0;
	    
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int soSanPham = 0;
	    
	    try {
	        String sql = "SELECT COUNT(*) AS TongSanPhamSapHetHang\r\n"
	        		+ "FROM SanPham\r\n"
	        		+ "WHERE soLuong < 50";
	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            soSanPham = rs.getInt("TongSanPhamSapHetHang");
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
	    
	    return soSanPham;
	}

	public int doangThuThangNay() {
	    Connection con = connectDB.accessDataBase();
	    if (con == null) return 0;
	    
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int doanhThuThang = 0;
	    
	    try {
	        String sql = "SELECT SUM([tongTien]) AS TongDoanhThu\r\n"
	        		+ "FROM HoaDon\r\n"
	        		+ "WHERE MONTH([ngayLapHD]) = MONTH(GETDATE()) AND YEAR([ngayLapHD]) = YEAR(GETDATE())";
	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	        	doanhThuThang = rs.getInt("TongDoanhThu");
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
	    
	    return doanhThuThang;
	}

}
