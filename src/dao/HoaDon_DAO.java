package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.HoaDon_entity;
import entity.KhachHang_entity;
import entity.LoaiHoaDon_entity;
import entity.NhanVien_entity;

public class HoaDon_DAO {
	
		public ArrayList<HoaDon_entity> getALLHoaDon(){
		ArrayList<HoaDon_entity> dshd = new ArrayList<HoaDon_entity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT * FROM HoaDon";
	    try {
	        java.sql.Statement st = con.createStatement();  
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String mahd = rs.getString("maHD");
	            Date ngayLapHD  = rs.getDate("ngayLapHD");
	            LocalDate lcNgayLapHD = (ngayLapHD != null) ? ngayLapHD.toLocalDate() : null; // Sử dụng toLocalDate()
	            double tongTien = rs.getDouble("tongTien");
	            double tienGiam = rs.getDouble("tienGiam");
	            String hinhThucThanhToan = rs.getString("hinhThucThanhToan");
	            boolean trangThai = rs.getBoolean("trangThai");
	            String maKH = rs.getString("sdtKH");
	            String maNV = rs.getString("maNV");
	            String maLoaiHD = rs.getString("maLoaiHD");
	            
	            KhachHang_entity kh = new KhachHang_entity(maKH);
	            NhanVien_entity nv = new NhanVien_entity(maNV);
	            LoaiHoaDon_entity lhd = new LoaiHoaDon_entity(maLoaiHD);
	            
	            HoaDon_entity hd = new HoaDon_entity(mahd, lcNgayLapHD, tongTien, tienGiam, hinhThucThanhToan, trangThai, kh.getSdtKH(), nv.getMaNV(), lhd.getMaLoaiHD());
	            dshd.add(hd);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dshd;
	}
	
		public ArrayList<HoaDon_entity> timKiem(String searchKey) {
		    ArrayList<HoaDon_entity> dssp = new ArrayList<HoaDon_entity>();
		    Connection con = connectDB.accessDataBase();
		    if (con == null) {
		        return null;
		    }
		    String sql = "SELECT * FROM HoaDon WHERE maHD LIKE ?";
		    try {
		        PreparedStatement ps = con.prepareStatement(sql);
		        ps.setString(1,searchKey);
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		        	 String mahd = rs.getString("maHD");
			            Date ngayLapHD  = rs.getDate("ngayLapHD");
			            LocalDate lcNgayLapHD = (ngayLapHD != null) ? ngayLapHD.toLocalDate() : null; // Sử dụng toLocalDate()
			            double tongTien = rs.getDouble("tongTien");
			            double tienGiam = rs.getDouble("tienGiam");
			            String hinhThucThanhToan = rs.getString("hinhThucThanhToan");
			            boolean trangThai = rs.getBoolean("trangThai");
			            String maKH = rs.getString("sdtKH");
			            String maNV = rs.getString("maNV");
			            String maLoaiHD = rs.getString("maLoaiHD");
			            
			            KhachHang_entity kh = new KhachHang_entity(maKH);
			            NhanVien_entity nv = new NhanVien_entity(maNV);
			            LoaiHoaDon_entity lhd = new LoaiHoaDon_entity(maLoaiHD);
			            
			            HoaDon_entity hd = new HoaDon_entity(mahd, lcNgayLapHD, tongTien, tienGiam, hinhThucThanhToan, trangThai, kh, nv, lhd);
			            dssp.add(hd);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (con != null) con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return dssp;
		}

    // Get the status (trangThai) of the invoice by maHD
    public int getTrangThai(String maHD) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return 0;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        int trangThai = 0;
//        try {
//            ps = con.prepareStatement("SELECT trangThai FROM HoaDon WHERE maHD = ?");
//            ps.setString(1, maHD);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                trangThai = rs.getInt("trangThai");
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
//        return trangThai;
        return 1; // Hardcoded example (1 for "Hoàn thành")
    }

    // Get the total amount (tongTien) by maHD
    public double getTongTien(String maHD) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return 0;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        double tongTien = 0;
//        try {
//            ps = con.prepareStatement("SELECT tongTien FROM HoaDon WHERE maHD = ?");
//            ps.setString(1, maHD);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                tongTien = rs.getDouble("tongTien");
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
//        return tongTien;
        return 1000000; // Hardcoded example
    }
}
