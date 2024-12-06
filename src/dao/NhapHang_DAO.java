package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.LoaiSanPham_entity;
import entity.NhapHang_entity;
import entity.SanPham_entity;

public class NhapHang_DAO {
    public ArrayList<SanPham_entity> searchSanPham(String key) {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SanPham_entity> listSP = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT TOP 8 * FROM SanPham WHERE tenSP COLLATE Latin1_General_CI_AI LIKE ? OR maSP LIKE ?");
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
            	String masp = rs.getString("maSP");
	            String tensp = rs.getString("tenSP");
	            int soLuong = rs.getInt("soLuong");
	            Date ngaySX = rs.getDate("ngaySanXuat");
	            Date ngayHH = rs.getDate("ngayHetHan");

	            LocalDate lcNgaySX = (ngaySX != null) ? ngaySX.toLocalDate() : null;
	            LocalDate lcNgayHH = (ngayHH != null) ? ngayHH.toLocalDate() : null;

	            double khoiLuong = rs.getDouble("khoiLuong");
	            String donViTinh = rs.getString("donViTinh");
	            String Nhacc = rs.getString("nhaCungCap");
	            double gia = rs.getDouble("gia");
	            String congDung = rs.getString("congDung");
	            String thanhPhan = rs.getString("thanhPhan");
	            String hinhAnhsp = rs.getString("hinhAnhSP");
	            String maLoaiSP = rs.getString("maLoaiSP");
	            double thue = rs.getDouble("thue");
	            double giaNhap = rs.getDouble("giaNhap");
	            LoaiSanPham_entity loaisp = new LoaiSanPham_entity(maLoaiSP);

	            SanPham_entity sp = new SanPham_entity(masp, tensp, lcNgaySX, lcNgayHH, khoiLuong, donViTinh, Nhacc, gia, thanhPhan, congDung, hinhAnhsp, loaisp, soLuong,thue,giaNhap);
                listSP.add(sp);
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
    public ArrayList<SanPham_entity> timKiemChiTietSanPham(String key) {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SanPham_entity> listSP = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM SanPham WHERE tenSP COLLATE Latin1_General_CI_AI LIKE ? OR maSP LIKE ?");
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
            	String masp = rs.getString("maSP");
	            String tensp = rs.getString("tenSP");
	            int soLuong = rs.getInt("soLuong");
	            Date ngaySX = rs.getDate("ngaySanXuat");
	            Date ngayHH = rs.getDate("ngayHetHan");

	            LocalDate lcNgaySX = (ngaySX != null) ? ngaySX.toLocalDate() : null;
	            LocalDate lcNgayHH = (ngayHH != null) ? ngayHH.toLocalDate() : null;

	            double khoiLuong = rs.getDouble("khoiLuong");
	            String donViTinh = rs.getString("donViTinh");
	            String Nhacc = rs.getString("nhaCungCap");
	            double gia = rs.getDouble("gia");
	            String congDung = rs.getString("congDung");
	            String thanhPhan = rs.getString("thanhPhan");
	            String hinhAnhsp = rs.getString("hinhAnhSP");
	            String maLoaiSP = rs.getString("maLoaiSP");
	            double thue = rs.getDouble("thue");
	            double giaNhap = rs.getDouble("giaNhap");
	            LoaiSanPham_entity loaisp = new LoaiSanPham_entity(maLoaiSP);

	            SanPham_entity sp = new SanPham_entity(masp, tensp, lcNgaySX, lcNgayHH, khoiLuong, donViTinh, Nhacc, gia, thanhPhan, congDung, hinhAnhsp, loaisp, soLuong,thue,giaNhap);
                listSP.add(sp);
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
	public boolean insert(NhapHang_entity nh) {
	    Connection con = connectDB.accessDataBase();

	    String sql = "INSERT INTO NhapHang (maNhapHang, ngayNhapHang, tongTien, ghiChu, trangThai,phuongThucThanhToan) VALUES (?,?,?,?,?,?)";

	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, nh.getMaNhapHang());
	        pst.setDate(2, java.sql.Date.valueOf(nh.getNgayNhapHang()));
	        pst.setDouble(3, nh.getTongTien());
	        pst.setString(4, nh.getGhiChu());
	        pst.setString(5, nh.getTrangThai());
	        pst.setString(6, nh.getHinhThucThanhToan());
	        int rowInserted = pst.executeUpdate();

	        pst.close();
	        con.close();

	        return rowInserted > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	// Phương thức cập nhật sản phẩm
	public boolean UpdateQuantity(String maSP, int soLuongNhap) {
	    Connection con = connectDB.accessDataBase();

	    String sql = "UPDATE SanPham SET soLuong = soLuong +? WHERE maSP = ?";
	    
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setInt(1, soLuongNhap);
	        stmt.setString(2, maSP);
	        

	        int rowUpdated = stmt.executeUpdate();
	        return rowUpdated > 0; // Trả về true nếu có dòng được cập nhật
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; 
	    } finally {
	        try {
	            if (con != null && !con.isClosed()) {
	                con.close(); // Đóng kết nối nếu nó không null
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


}
