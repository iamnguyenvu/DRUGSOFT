package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

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

	            LocalDate lcNgaySX = (ngaySX != null) ? ngaySX.toLocalDate() : null; // Sử dụng toLocalDate()
	            LocalDate lcNgayHH = (ngayHH != null) ? ngayHH.toLocalDate() : null; // Sử dụng toLocalDate()

	            double khoiLuong = rs.getDouble("khoiLuong");
	            String donViTinh = rs.getString("donViTinh");
	            String Nhacc = rs.getString("nhaCungCap");
	            double gia = rs.getDouble("gia");
	            String congDung = rs.getString("congDung");
	            String hinhAnhsp = rs.getString("hinhAnhSP");
	            String maLoaiSP = rs.getString("maLoaiSP");
	            LoaiSanPham_entity loaisp = new LoaiSanPham_entity(maLoaiSP);

	            SanPham_entity sp = new SanPham_entity(masp, tensp, soLuong, lcNgaySX, lcNgayHH, khoiLuong, donViTinh, Nhacc, gia, congDung, hinhAnhsp, loaisp);
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
	            String hinhAnhsp = rs.getString("hinhAnhSP");
	            String maLoaiSP = rs.getString("maLoaiSP");
	            LoaiSanPham_entity loaisp = new LoaiSanPham_entity(maLoaiSP);

	            SanPham_entity sp = new SanPham_entity(masp, tensp, soLuong, lcNgaySX, lcNgayHH, khoiLuong, donViTinh, Nhacc, gia, congDung, hinhAnhsp, loaisp);
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
	            String hinhAnhsp = rs.getString("hinhAnhSP");
	            String maLoaiSP = rs.getString("maLoaiSP");
	            LoaiSanPham_entity loaisp = new LoaiSanPham_entity(maLoaiSP);

	            SanPham_entity sp = new SanPham_entity(masp, tensp, soLuong, lcNgaySX, lcNgayHH, khoiLuong, donViTinh, Nhacc, gia, congDung, hinhAnhsp, loaisp);
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
            ps = con.prepareStatement("SELECT * FROM SanPham WHERE maSP = ?");
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
