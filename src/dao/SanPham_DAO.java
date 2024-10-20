package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.LoaiSanPham;
import entity.SanPham;



public class SanPham_DAO {
	
	public ArrayList<SanPham> getAllSanPham() {
	    ArrayList<SanPham> dssp = new ArrayList<SanPham>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT * FROM SanPham";
	    try {
	        java.sql.Statement st = con.createStatement();  
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String masp = rs.getString("maSP");
	            String tensp = rs.getString("tenSP");
	            int soLuong = rs.getInt("soLuong");
	            Date ngaySX = rs.getDate("ngaySanXuat");
	            Date ngayHH = rs.getDate("ngayHetHan");

	            LocalDate lcNgaySX = (ngaySX != null) ? ngaySX.toLocalDate() : null; // Sử dụng toLocalDate()
	            LocalDate lcNgayHH = (ngayHH != null) ? ngayHH.toLocalDate() : null; // Sử dụng toLocalDate()

	            double khoiLuong = rs.getDouble("khoiLuong");
	            String donViTinh = rs.getString("donViTinh");
	            String Nhacc = rs.getString("nhaCungCap");
	            double gia = rs.getDouble("gia");
	            String congDung = rs.getString("congDung");
	            String hinhAnhsp = rs.getString("hinhAnhSP");
	            String maLoaiSP = rs.getString("maLoaiSP");
	            LoaiSanPham loaisp = new LoaiSanPham(maLoaiSP);

	            SanPham sp = new SanPham(masp, tensp, lcNgaySX, lcNgayHH, khoiLuong, donViTinh, Nhacc, gia, congDung, hinhAnhsp, loaisp, soLuong);
	            dssp.add(sp);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dssp;
	}


    // Get full product details by maSP
    public String getSanPhamInfo(String maSP) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String info = null;
        try {
            ps = con.prepareStatement("SELECT tenSP, ngaySanXuat, ngayHetHan,khoiLuong,donViTinh, nhaCungCap, gia, congDung, hinhAnhSP, maLoaiSP FROM SanPham WHERE maSP = ?");
            ps.setString(1, maSP);
            rs = ps.executeQuery();
            if (rs.next()) {
                info = "Mã SP: " + maSP +
                       ", Tên: " + rs.getString("tenSP") +
                       ", Ngày sản xuất: " + rs.getDate("ngaySanXuat") +
                       ", Ngày hết hạn: " + rs.getDate("ngayHetHan") +
      				 ", Khối lượng: " + rs.getDouble("khoiLuong") +
		 				 ", Số Lượng: " + rs.getDouble("khoiLuong") +
      				 ", Đơn vị tính: " + rs.getString("donViTinh") +
                       ", Nhà cung cấp: " + rs.getString("nhaCungCap") +
                       ", Giá: " + rs.getDouble("gia") +
                       ", Công dụng: " + rs.getString("congDung") +
                       ", Hình ảnh: " + rs.getString("hinhAnhSP") +
                       ", Mã loại SP: " + rs.getString("maLoaiSP");
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
        return info;
    }

    // Get product price by maSP
    public double getGia(String maSP) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return 0;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        double gia = 0;
//        try {
//            ps = con.prepareStatement("SELECT gia FROM SanPham WHERE maSP = ?");
//            ps.setString(1, maSP);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                gia = rs.getDouble("gia");
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
//        return gia;
        return 100000.0; // Hardcoded example
    }

    // Get product supplier by maSP
    public String getNhaCungCap(String maSP) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String nhaCungCap = null;
//        try {
//            ps = con.prepareStatement("SELECT nhaCungCap FROM SanPham WHERE maSP = ?");
//            ps.setString(1, maSP);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                nhaCungCap = rs.getString("nhaCungCap");
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
//        return nhaCungCap;
        return "CungCap A"; // Hardcoded example
    }

    // Get product usage (congDung) by maSP
    public String getCongDung(String maSP) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String congDung = null;
//        try {
//            ps = con.prepareStatement("SELECT congDung FROM SanPham WHERE maSP = ?");
//            ps.setString(1, maSP);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                congDung = rs.getString("congDung");
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
//        return congDung;
        return "Chăm sóc da"; // Hardcoded example
    }

    // Get product image (hinhAnhSP) by maSP
    public String getHinhAnh(String maSP) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String hinhAnh = null;
//        try {
//            ps = con.prepareStatement("SELECT hinhAnhSP FROM SanPham WHERE maSP = ?");
//            ps.setString(1, maSP);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                hinhAnh = rs.getString("hinhAnhSP");
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
//        return hinhAnh;
        return "/images/spa.png"; // Hardcoded example
    }
}
