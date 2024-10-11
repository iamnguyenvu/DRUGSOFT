/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author HP
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.connectDB;

public class DangNhap_DAO {
	public Integer getRole(String username, String password) {
//		Connection con = connectDB.accessDataBase();
//		if(con == null) return null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//        Integer role = null;
//        try {
//            ps = con.prepareStatement("SELECT phanQuyen FROM TaiKhoan WHERE username = ? AND password = ? AND trangThai = 1");
//            ps.setString(1, username);
//            ps.setString(2, password);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                role = rs.getBoolean("phanQuyen") ? 1: 0;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        
//        return role;
		return 1;
	}
	
	public String getHoTen(String username) {
//		Connection con = connectDB.accessDataBase();
//		if(con == null) return null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//        String hoTen = null;
//        try {
//            ps = con.prepareStatement("SELECT CONCAT(hoNV, ' ', tenNV) AS HoTen FROM NhanVien WHERE maNV = ? AND trangThai = 1");
//            ps.setString(1, username);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//            	hoTen = rs.getString(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        
//        return hoTen;
		return "Nguyên Vũ";
	}
	
	public String getAvatar(String username) {
//		Connection con = connectDB.accessDataBase();
//		if(con == null) return null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//        String hinhAnh = null;
//        try {
//            ps = con.prepareStatement("SELECT hinhAnh FROM NhanVien WHERE maNV = ? AND trangThai = 1");
//            ps.setString(1, username);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//            	hinhAnh = rs.getString(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (ps != null) ps.close();
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        
//        return hinhAnh;
		return "/nguyenvu/resources/image/profile.png";
	}
        
        
        public String getRoleName(String username) {
            return "Chức vụ: ";
        }
	
}
