package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connectDB.connectDB;
import entity.ChiTietNhapHang_entity;

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

}
