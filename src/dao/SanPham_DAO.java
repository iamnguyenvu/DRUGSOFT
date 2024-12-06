package dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import connectDB.connectDB;
import entity.LoaiSanPham_entity;
import entity.SanPham_entity;



public class SanPham_DAO {
	
	public ArrayList<SanPham_entity> getAllSanPham() {
	    ArrayList<SanPham_entity> dssp = new ArrayList<SanPham_entity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT * FROM SanPham order by tenSP";
	    try {
	        java.sql.Statement st = con.createStatement();  
	        ResultSet rs = st.executeQuery(sql);
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
	            dssp.add(sp);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dssp;
	}

	public ArrayList<SanPham_entity> timKiemSanPham(String searchKey) {
	    ArrayList<SanPham_entity> dssp = new ArrayList<SanPham_entity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT * FROM SanPham WHERE maSP LIKE ? OR tenSP LIKE ?";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, "%" + searchKey + "%");
	        ps.setString(2, "%" + searchKey + "%");
	        ResultSet rs = ps.executeQuery();
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
	            dssp.add(sp);
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

	// Phương thức để lấy danh sách sản phẩm theo loại và sắp xếp
	public ArrayList<SanPham_entity> laySanPhamTheoLoaiVaSapXep(String loaiSanPham, boolean sapXepTen, boolean sapXepNgaySXTang) {
	    ArrayList<SanPham_entity> dssp = new ArrayList<>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    
	    // Lọc theo loại sản phẩm và sắp xếp theo tên và ngày sản xuất
	    String sql = "SELECT * FROM SanPham WHERE (? = 'Tất cả' OR MaLoaiSP = ?) ORDER BY tenSP " + (sapXepTen ? "ASC" : "DESC") 
	        + ", ngaySanXuat " + (sapXepNgaySXTang ? "ASC" : "DESC");
	    
	    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	        // Cài đặt giá trị cho câu truy vấn
	        pstmt.setString(1, loaiSanPham); // Loại sản phẩm
	        pstmt.setString(2, loaiSanPham); // Điều kiện cho tất cả sản phẩm

	        ResultSet rs = pstmt.executeQuery();

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
	            dssp.add(sp);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dssp;
	}

	public boolean themSanPham(SanPham_entity sp) {
	    Connection con = connectDB.accessDataBase();

	    String sql = "INSERT INTO SanPham (maSP, tenSP, ngaySanXuat, ngayHetHan, nhaCungCap, gia, thanhPhan, congDung, hinhAnhSP, maLoaiSP, soLuong, khoiLuong, donViTinh) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	    try {
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, sp.getMaSP());
	        pst.setString(2, sp.getTenSP());
	        pst.setDate(3, java.sql.Date.valueOf(sp.getNgaySanXuat())); // Chuyển đổi LocalDate sang sql.Date
	        pst.setDate(4, java.sql.Date.valueOf(sp.getNgayHetHan()));
	        pst.setString(5, sp.getNhaCungCap());
	        pst.setDouble(6, sp.getGia());
	        pst.setString(7, sp.getThanhPhan());
	        pst.setString(8, sp.getCongDung());
	        pst.setString(9, sp.getHinhAnhSP());
	        pst.setString(10, sp.getLoaiSanPham().getMaLoaiSP());
	        pst.setInt(11, sp.getSoLuong());
	        pst.setDouble(12, sp.getKhoiLuong());
	        pst.setString(13, sp.getDonViTinh());

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
	public boolean capNhatSanPham(SanPham_entity sp) {
	    Connection con = connectDB.accessDataBase();

	    String sql = "UPDATE SanPham SET tenSP = ?, soLuong = ?, ngaySanXuat = ?, ngayHetHan = ?, khoiLuong = ?, donViTinh = ?, nhaCungCap = ?, gia = ?, thanhPhan = ?, congDung = ?, hinhAnhSP = ?, maLoaiSP = ?,thue = ? WHERE maSP = ?";
	    
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setString(1, sp.getTenSP());
	        stmt.setInt(2, sp.getSoLuong());
	        
	        // Chuyển đổi LocalDate sang java.sql.Date
	        stmt.setDate(3, Date.valueOf(sp.getNgaySanXuat())); // Ngày sản xuất
	        stmt.setDate(4, Date.valueOf(sp.getNgayHetHan())); // Ngày hết hạn

	        stmt.setDouble(5, sp.getKhoiLuong());
	        stmt.setString(6, sp.getDonViTinh());
	        stmt.setString(7, sp.getNhaCungCap());
	        stmt.setDouble(8, sp.getGia());
	        stmt.setString(9, sp.getThanhPhan());
	        stmt.setString(10, sp.getCongDung());
	        stmt.setString(11, sp.getHinhAnhSP());
	        stmt.setString(12, sp.getLoaiSanPham().getMaLoaiSP());
	        stmt.setDouble(13, sp.getThue());
	        stmt.setString(14, sp.getMaSP()); // Điều kiện WHERE

	        int rowUpdated = stmt.executeUpdate();
	        return rowUpdated > 0; // Trả về true nếu có dòng được cập nhật
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // Trả về false nếu có lỗi xảy ra
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

	

    // Get product price by maSP
    public SanPham_entity getThongTinSP(String maSP) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SanPham_entity sp = null;
        try {
            ps = con.prepareStatement("SELECT * FROM SanPham WHERE maSP = ?");
            ps.setString(1, maSP);
            rs = ps.executeQuery();
            if (rs.next()) {
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

	            sp = new SanPham_entity(masp, tensp, lcNgaySX, lcNgayHH, khoiLuong, donViTinh, Nhacc, gia, thanhPhan, congDung, hinhAnhsp, loaisp, soLuong,thue,giaNhap);
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
        return sp;
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

	public boolean xoaSanPham(String maSP) {
		    Connection con = connectDB.accessDataBase();
		    String sql = "DELETE FROM SanPham WHERE maSP = ?";
		    try {
		        PreparedStatement pst = con.prepareStatement(sql);
		        pst.setString(1, maSP);
		        int rowDelete = pst.executeUpdate();
		        
		        pst.close();
		        con.close();
		        
		        // Trả về true nếu có ít nhất một dòng bị xóa
		        return rowDelete > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return false;
		}
}
