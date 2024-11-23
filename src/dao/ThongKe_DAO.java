package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.DoanhSoBanHangNV;
import entity.HoaDon_entity;
import entity.KhachHang_entity;
import entity.LoaiHoaDon_entity;
import entity.LoaiSanPham_entity;
import entity.NhanVien_entity;
import entity.SanPham_entity;
import nguyenvu.model.DoanhSoBanHangModalData;
import nguyenvu.model.ModalDataSoLuongGiaoDich;
import nguyenvu.model.SoLuongGiaoDichNV;

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
	public int soSanPhamdaHetHan() {
	    Connection con = connectDB.accessDataBase();
	    if (con == null) return 0;
	    
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int soSanPham = 0;
	    
	    try {
	        String sql = "SELECT COUNT(*) AS TongSanPhamSapHetHan FROM SanPham WHERE ngayHetHan < GETDATE()";
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
	public int soSanPhamDaHetHang() {
	    Connection con = connectDB.accessDataBase();
	    if (con == null) return 0;
	    
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int soSanPham = 0;
	    
	    try {
	        String sql = "SELECT COUNT(*) AS TongSanPhamSapHetHang\r\n"
	        		+ "FROM SanPham\r\n"
	        		+ "WHERE soLuong = 0";
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
	// Hàm lấy danh sách sản phẩm bán chạy
	public ArrayList<SanPham_entity> layDanhSachSanPham(java.util.Date startDate, java.util.Date endDate, String isBanChay) throws SQLException {
	    ArrayList<SanPham_entity> productList = new ArrayList<>();
	    
	    Connection connection = connectDB.accessDataBase();

	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT cthd.maSP, sp.tenSP, sp.soLuong, sp.ngaySanXuat, ");
	    sql.append("sp.ngayHetHan, sp.khoiLuong, sp.donViTinh, sp.nhaCungCap, ");
	    sql.append("sp.gia, sp.thanhPhan, sp.congDung, sp.hinhAnhSP, sp.maLoaiSP, ");
	    sql.append("SUM(cthd.soLuongSanPham) AS tongSoLuongBan ");
	    sql.append("FROM ChiTietHoaDon cthd ");
	    sql.append("JOIN SanPham sp ON cthd.maSP = sp.maSP ");
	    sql.append("JOIN HoaDon hd ON cthd.maHD = hd.maHD ");
	    sql.append("WHERE hd.ngayLapHD BETWEEN ? AND ? ");
	    sql.append("GROUP BY cthd.maSP, sp.tenSP, sp.soLuong, sp.ngaySanXuat, ");
	    sql.append("sp.ngayHetHan, sp.khoiLuong, sp.donViTinh, sp.nhaCungCap, ");
	    sql.append("sp.gia, sp.thanhPhan, sp.congDung, sp.hinhAnhSP, sp.maLoaiSP ");

	    // Thay đổi ORDER BY dựa trên lựa chọn
	    if (isBanChay.equals("Sản Phẩm Bán Chạy")) {
	        sql.append("ORDER BY tongSoLuongBan DESC;");
	    } else {
	        sql.append("ORDER BY tongSoLuongBan;");
	    }

	    PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	    if(startDate == null && endDate == null ) {
	    	getAllSanPham();
	    }
	    preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
	    preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));

	    ResultSet resultSet = preparedStatement.executeQuery();

	    // Lấy kết quả từ ResultSet
	    while (resultSet.next()) {
	        SanPham_entity product = new SanPham_entity();
	        product.setMaSP(resultSet.getString("maSP"));
	        product.setTenSP(resultSet.getString("tenSP"));
	        product.setSoLuong(resultSet.getInt("soLuong"));
	        // Chuyển đổi từ java.sql.Date sang LocalDate
	        product.setNgaySanXuat(resultSet.getDate("ngaySanXuat").toLocalDate());
	        product.setNgayHetHan(resultSet.getDate("ngayHetHan").toLocalDate());
	        product.setKhoiLuong(resultSet.getDouble("khoiLuong"));
	        product.setDonViTinh(resultSet.getString("donViTinh"));
	        product.setNhaCungCap(resultSet.getString("nhaCungCap"));
	        product.setGia(resultSet.getDouble("gia"));
	        product.setThanhPhan(resultSet.getString("thanhPhan"));
	        product.setCongDung(resultSet.getString("congDung"));
	        product.setHinhAnhSP(resultSet.getString("hinhAnhSP"));
	        
	        String maLoaiSP = resultSet.getString("maLoaiSP");
	        LoaiSanPham_entity lsp = new LoaiSanPham_entity(maLoaiSP);
	        product.setLoaiSanPham(lsp);
	        productList.add(product);
	    }

	    // Đóng kết nối
	    resultSet.close();
	    preparedStatement.close();
	    connection.close();

	    return productList;
	}
	public ArrayList<SanPham_entity> getAllSanPhamHetHan() {
	    ArrayList<SanPham_entity> dssp = new ArrayList<SanPham_entity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT * FROM SanPham\r\n"
	    		+ "WHERE [ngayHetHan] < GETDATE()\r\n"
	    		+ "ORDER BY tenSP";
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
	            LoaiSanPham_entity loaisp = new LoaiSanPham_entity(maLoaiSP);

	            SanPham_entity sp = new SanPham_entity(masp, tensp, lcNgaySX, lcNgayHH, khoiLuong, donViTinh, Nhacc, gia, thanhPhan, congDung, hinhAnhsp, loaisp, soLuong,thue);
	            dssp.add(sp);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dssp;
	}
	public ArrayList<SanPham_entity> getAllSanPhamSapHetHan() {
	    ArrayList<SanPham_entity> dssp = new ArrayList<SanPham_entity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT * FROM SanPham WHERE DATEDIFF(DAY, GETDATE(), ngayHetHan) < 30";
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
	            LoaiSanPham_entity loaisp = new LoaiSanPham_entity(maLoaiSP);

	            SanPham_entity sp = new SanPham_entity(masp, tensp, lcNgaySX, lcNgayHH, khoiLuong, donViTinh, Nhacc, gia, thanhPhan, congDung, hinhAnhsp, loaisp, soLuong,thue);
	            dssp.add(sp);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dssp;
	}
	public ArrayList<SanPham_entity> getAllSanPhamSapHetHang() {
	    ArrayList<SanPham_entity> dssp = new ArrayList<SanPham_entity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT * FROM SanPham WHERE soLuong < 50";
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
	            LoaiSanPham_entity loaisp = new LoaiSanPham_entity(maLoaiSP);

	            SanPham_entity sp = new SanPham_entity(masp, tensp, lcNgaySX, lcNgayHH, khoiLuong, donViTinh, Nhacc, gia, thanhPhan, congDung, hinhAnhsp, loaisp, soLuong,thue);
	            dssp.add(sp);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dssp;
	}
	public ArrayList<SanPham_entity> getAllSanPham() {
	    ArrayList<SanPham_entity> dssp = new ArrayList<SanPham_entity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT * FROM SanPham\r\n"
	    		+ "ORDER BY tenSP";
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
	            LoaiSanPham_entity loaisp = new LoaiSanPham_entity(maLoaiSP);

	            SanPham_entity sp = new SanPham_entity(masp, tensp, lcNgaySX, lcNgayHH, khoiLuong, donViTinh, Nhacc, gia, thanhPhan, congDung, hinhAnhsp, loaisp, soLuong,thue);
	            dssp.add(sp);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dssp;
	}
	public ArrayList<SanPham_entity> getAllSanPhamHetHang() {
	    ArrayList<SanPham_entity> dssp = new ArrayList<SanPham_entity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT * FROM SanPham WHERE [soLuong] = 0 ORDER BY tenSP";
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
	            double thue = rs.getDouble("thue");
	            String maLoaiSP = rs.getString("maLoaiSP");
	            LoaiSanPham_entity loaisp = new LoaiSanPham_entity(maLoaiSP);

	            SanPham_entity sp = new SanPham_entity(masp, tensp, lcNgaySX, lcNgayHH, khoiLuong, donViTinh, Nhacc, gia, thanhPhan, congDung, hinhAnhsp, loaisp, soLuong,thue);
	            dssp.add(sp);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dssp;
	}
	public ArrayList<HoaDon_entity> layDanhSachHoaDon(java.util.Date startDate, java.util.Date endDate) {
	    ArrayList<HoaDon_entity> hdList = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();

	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT [maHD], [ngayLapHD], [tongTien], [tienGiam], [hinhThucThanhToan], [trangThai], [sdtKH], [maNV], [maLoaiHD] ");
	    sql.append("FROM [dbo].[HoaDon] ");
	    sql.append("WHERE [ngayLapHD] BETWEEN ? AND ?");

	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	        preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
	        preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String mahd = rs.getString("maHD");
	            Date ngayLapHD = rs.getDate("ngayLapHD");
	            LocalDateTime lcNgayLapHD = (ngayLapHD != null) ? ngayLapHD.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
	            double tongTien = rs.getDouble("tongTien");
	            double tienGiam = rs.getDouble("tienGiam");
	            String hinhThucThanhToan = rs.getString("hinhThucThanhToan");
	            boolean trangThai = rs.getBoolean("trangThai");
	            String maKH = rs.getString("sdtKH");
	            String maNV = rs.getString("maNV");
	            String maLoaiHD = rs.getString("maLoaiHD");
	            String ghiChu = rs.getString("ghiChu");
	            KhachHang_entity kh = new KhachHang_entity(maKH);
	            NhanVien_entity nv = new NhanVien_entity(maNV);
	            LoaiHoaDon_entity lhd = new LoaiHoaDon_entity(maLoaiHD);

	            HoaDon_entity hd = new HoaDon_entity(mahd, lcNgayLapHD, tongTien, tienGiam, hinhThucThanhToan, trangThai, maKH, maNV, maLoaiHD, ghiChu);
	            hdList.add(hd);
	        }

	        rs.close();
	        preparedStatement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return hdList;
	}
	public ArrayList<DoanhSoBanHangNV> layDoanhSOBanHangNV(java.util.Date startDate, java.util.Date endDate) {
	    ArrayList<DoanhSoBanHangNV> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();

	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT nv.maNV, hotenNV, ngayLapHD, hinhThucThanhToan, hd.trangThai, ghiChu, SUM(tongTien) AS DoanhSo");
	    sql.append(" FROM HoaDon hd JOIN NhanVien nv ON hd.maNV = nv.maNV");
	    sql.append(" WHERE ngayLapHD BETWEEN ? AND ?");
	    sql.append(" GROUP BY nv.maNV, hotenNV, ngayLapHD, hinhThucThanhToan, hd.trangThai, ghiChu");
	    sql.append(" ORDER BY hotenNV;");

	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	        preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
	        preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String maNV = rs.getString("maNV");
	            String hotenNV = rs.getString("hotenNV");
	            Timestamp ngayLapHD = rs.getTimestamp("ngayLapHD");
	            String hinhThucThanhToan = rs.getString("hinhThucThanhToan");
	            boolean trangThai = rs.getBoolean("trangThai");
	            String ghiChu = rs.getString("ghiChu");
	            double doanhSo = rs.getDouble("DoanhSo");
	           
	            DoanhSoBanHangNV ds = new DoanhSoBanHangNV(maNV, hotenNV, ngayLapHD, hinhThucThanhToan, trangThai, ghiChu, doanhSo);
	            List.add(ds);
	        }

	        rs.close();
	        preparedStatement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return List;
	}
	public ArrayList<DoanhSoBanHangNV> layDoanhSOBanHangNV() {
	    ArrayList<DoanhSoBanHangNV> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();

	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT nv.maNV, hotenNV, ngayLapHD, hinhThucThanhToan, hd.trangThai, ghiChu, SUM(tongTien) AS DoanhSo");
	    sql.append(" FROM HoaDon hd JOIN NhanVien nv ON hd.maNV = nv.maNV");
	    sql.append(" WHERE YEAR(ngayLapHD) = YEAR(getdate())");
	    sql.append(" GROUP BY nv.maNV, hotenNV, ngayLapHD, hinhThucThanhToan, hd.trangThai, ghiChu");
	    sql.append(" ORDER BY hotenNV;");


	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String maNV = rs.getString("maNV");
	            String hotenNV = rs.getString("hotenNV");
	            Timestamp ngayLapHD = rs.getTimestamp("ngayLapHD");
	            String hinhThucThanhToan = rs.getString("hinhThucThanhToan");
	            boolean trangThai = rs.getBoolean("trangThai");
	            String ghiChu = rs.getString("ghiChu");
	            double doanhSo = rs.getDouble("DoanhSo");
	           
	            DoanhSoBanHangNV ds = new DoanhSoBanHangNV(maNV, hotenNV, ngayLapHD, hinhThucThanhToan, trangThai, ghiChu, doanhSo);
	            List.add(ds);
	        }

	        rs.close();
	        preparedStatement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return List;
	}
	public ArrayList<SoLuongGiaoDichNV> laySoLuongGiaoDichNV() {
	    ArrayList<SoLuongGiaoDichNV> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();

	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT hd.maNV, nv.hotenNV, COUNT(hd.maHD) AS soLuongGiaoDich ");
	    sql.append("FROM HoaDon hd JOIN NhanVien nv ON hd.maNV = nv.maNV ");
	    sql.append("WHERE YEAR(ngayLapHD) = YEAR(getdate()) ");
	    sql.append("GROUP BY hd.maNV, nv.hotenNV ");
	    sql.append("ORDER BY soLuongGiaoDich DESC;");

	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String maNV = rs.getString("maNV");
	            String hotenNV = rs.getString("hotenNV");
	            int soLuongDD = rs.getInt("soLuongGiaoDich");

	            SoLuongGiaoDichNV ds = new SoLuongGiaoDichNV(maNV, hotenNV, soLuongDD);
	            List.add(ds);
	        }

	        rs.close();
	        preparedStatement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return List;
	}

	public ArrayList<SoLuongGiaoDichNV> laySoLuongGiaoDichNV(java.util.Date startDate, java.util.Date endDate) {
	    ArrayList<SoLuongGiaoDichNV> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();

	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT hd.maNV, nv.hotenNV, COUNT(hd.maHD) AS soLuongGiaoDich ");
	    sql.append("FROM HoaDon hd JOIN NhanVien nv ON hd.maNV = nv.maNV ");
	    sql.append("where ngayLapHD BETWEEN ? AND ? ");
	    sql.append("GROUP BY hd.maNV, nv.hotenNV ");
	    sql.append("ORDER BY soLuongGiaoDich DESC;");


	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	        preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
	        preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String maNV = rs.getString("maNV");
	            String hotenNV = rs.getString("hotenNV");
	            int soLuongDD = rs.getInt("soLuongGiaoDich");
	           
	            SoLuongGiaoDichNV ds = new SoLuongGiaoDichNV(maNV, hotenNV, soLuongDD);
	            List.add(ds);
	        }

	        rs.close();
	        preparedStatement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return List;
	}

	public ArrayList<ModalDataSoLuongGiaoDich> laySoluongGDTQ(java.util.Date startDate, java.util.Date endDate) {
	    ArrayList<ModalDataSoLuongGiaoDich> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();

	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT MONTH(ngayLapHD) AS Thang, COUNT(*) AS TongSoGiaoDich ");
	    sql.append("FROM HoaDon ");
	    sql.append("WHERE ngayLapHD BETWEEN ? AND ? AND trangThai = 1 ");
	    sql.append("GROUP BY MONTH(ngayLapHD) ");
	    sql.append("ORDER BY Thang;");


	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	        preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
	        preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String thang = rs.getString("Thang");
	            int soLuongDD = rs.getInt("TongSoGiaoDich");
	           
	            ModalDataSoLuongGiaoDich ds = new ModalDataSoLuongGiaoDich(thang, soLuongDD);
	            List.add(ds);
	        }

	        rs.close();
	        preparedStatement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return List;
	}
	public ArrayList<ModalDataSoLuongGiaoDich> laySoluongGDTQ() {
	    ArrayList<ModalDataSoLuongGiaoDich> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();

	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT MONTH(ngayLapHD) AS Thang, COUNT(*) AS TongSoGiaoDich ");
	    sql.append("FROM HoaDon ");
	    sql.append("WHERE YEAR(ngayLapHD) = YEAR(GETDATE()) AND trangThai = 1 ");
	    sql.append("GROUP BY MONTH(ngayLapHD) ");
	    sql.append("ORDER BY Thang;");


	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String thang = rs.getString("Thang");
	            int soLuongDD = rs.getInt("TongSoGiaoDich");
	           
	            ModalDataSoLuongGiaoDich ds = new ModalDataSoLuongGiaoDich(thang, soLuongDD);
	            List.add(ds);
	        }

	        rs.close();
	        preparedStatement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return List;
	}
	public ArrayList<DoanhSoBanHangModalData> layDoanhSoBanHangTQTheoThoiGian(int time) {
	    ArrayList<DoanhSoBanHangModalData> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();

	    // Trường hợp time == 7
	    if (time == 7) {
	        StringBuilder sql = new StringBuilder();
	        sql.append("SELECT CAST(ngayLapHD AS DATE) AS ngay, ");
	        sql.append("SUM(tongTien) AS tongDoanhThu, ");
	        sql.append("SUM(tienGiam) AS tongChiPhi, ");
	        sql.append("(SUM(tongTien) - SUM(tienGiam)) AS loiNhuan ");
	        sql.append("FROM HoaDon ");
	        sql.append("WHERE ngayLapHD >= DATEADD(DAY, -7, GETDATE()) ");  // Truy vấn 7 ngày
	        sql.append("GROUP BY CAST(ngayLapHD AS DATE) ");
	        sql.append("ORDER BY ngay;");

	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                String ngay = rs.getString("ngay");
	                int tongDoanhThu = rs.getInt("tongDoanhThu");
	                int tongChiPhi = rs.getInt("tongChiPhi");
	                int loiNhuan = rs.getInt("loiNhuan");

	                DoanhSoBanHangModalData dsbh = new DoanhSoBanHangModalData(ngay, tongDoanhThu, tongChiPhi, loiNhuan);
	                List.add(dsbh);
	            }
	            rs.close();
	            preparedStatement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    // Trường hợp time == 30
	    else if (time == 30) {
	        StringBuilder sql = new StringBuilder();
	        sql.append("WITH DateRanges AS (SELECT DATEADD(DAY, (6 * (ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1)), GETDATE() - 30) AS startDate,DATEADD(DAY, (6 * ROW_NUMBER() OVER (ORDER BY (SELECT NULL))), GETDATE() - 30) AS endDate ");
	        sql.append("FROM master.dbo.spt_values WHERE type = 'P' AND number <= 7), ");
	        sql.append("WeeklyData AS (SELECT CONCAT(FORMAT(DateRanges.startDate, 'dd/MM'), ' - ', FORMAT(DateRanges.endDate, 'dd/MM')) AS tuan, ");
	        sql.append("SUM(tongTien) AS tongDoanhThu,SUM(tienGiam) AS tongChiPhi,(SUM(tongTien) - SUM(tienGiam)) AS loiNhuan,DateRanges.startDate ");
	        sql.append("FROM HoaDon JOIN DateRanges ON ngayLapHD >= DateRanges.startDate AND ngayLapHD < DateRanges.endDate GROUP BY DateRanges.startDate, DateRanges.endDate) ");
	        sql.append("SELECT tuan, tongDoanhThu, tongChiPhi, loiNhuan FROM WeeklyData ORDER BY startDate ASC;");

	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                String ngay = rs.getString("tuan");
	                int tongDoanhThu = rs.getInt("tongDoanhThu");
	                int tongChiPhi = rs.getInt("tongChiPhi");
	                int loiNhuan = rs.getInt("loiNhuan");

	                DoanhSoBanHangModalData dsbh = new DoanhSoBanHangModalData(ngay, tongDoanhThu, tongChiPhi, loiNhuan);
	                List.add(dsbh);
	            }
	            rs.close();
	            preparedStatement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 // Trường hợp time == 90
	    else if (time == 90) {
	        StringBuilder sql = new StringBuilder();
	        sql.append("WITH DateRanges AS (SELECT DATEADD(DAY, (12 * (ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1)), GETDATE() - 90) AS startDate,DATEADD(DAY, (12 * ROW_NUMBER() OVER (ORDER BY (SELECT NULL))), GETDATE() - 90) AS endDate ");
	        sql.append("FROM master.dbo.spt_values ");
	        sql.append("WHERE type = 'P' AND number <= 7 ), ");
	        sql.append("WeeklyData AS (SELECT CONCAT(FORMAT(DateRanges.startDate, 'dd/MM'), ' - ', FORMAT(DateRanges.endDate, 'dd/MM')) AS tuan,SUM(tongTien) AS tongDoanhThu,SUM(tienGiam) AS tongChiPhi,(SUM(tongTien) - SUM(tienGiam)) AS loiNhuan,DateRanges.startDate ");
	        sql.append("FROM HoaDon JOIN DateRanges ON ngayLapHD >= DateRanges.startDate AND ngayLapHD < DateRanges.endDate GROUP BY DateRanges.startDate, DateRanges.endDate) ");
	        sql.append("SELECT tuan, tongDoanhThu, tongChiPhi, loiNhuan FROM WeeklyData ORDER BY startDate ASC;");
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                String ngay = rs.getString("tuan");
	                int tongDoanhThu = rs.getInt("tongDoanhThu");
	                int tongChiPhi = rs.getInt("tongChiPhi");
	                int loiNhuan = rs.getInt("loiNhuan");

	                DoanhSoBanHangModalData dsbh = new DoanhSoBanHangModalData(ngay, tongDoanhThu, tongChiPhi, loiNhuan);
	                List.add(dsbh);
	            }
	            rs.close();
	            preparedStatement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 // Trường hợp time == 365
	    else if (time == 365) {
	        StringBuilder sql = new StringBuilder();
	        sql.append("WITH DateRanges AS (SELECT DATEADD(DAY, (46 * (ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1)), GETDATE() - 365) AS startDate,DATEADD(DAY, (46 * ROW_NUMBER() OVER (ORDER BY (SELECT NULL))), GETDATE() - 365) AS endDate ");
	        sql.append("FROM master.dbo.spt_values WHERE type = 'P' AND number <= 30), ");
	        sql.append("WeeklyData AS (SELECT CONCAT(FORMAT(DateRanges.startDate, 'dd/MM'), ' - ', FORMAT(DateRanges.endDate, 'dd/MM')) AS tuan, SUM(tongTien) AS tongDoanhThu,SUM(tienGiam) AS tongChiPhi,(SUM(tongTien) - SUM(tienGiam)) AS loiNhuan,DateRanges.startDate ");
	        sql.append("FROM HoaDon JOIN DateRanges ON ngayLapHD >= DateRanges.startDate AND ngayLapHD < DateRanges.endDate GROUP BY DateRanges.startDate, DateRanges.endDate) ");
	        sql.append("SELECT tuan, tongDoanhThu, tongChiPhi, loiNhuan FROM WeeklyData ORDER BY startDate ASC;");
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                String ngay = rs.getString("tuan");
	                int tongDoanhThu = rs.getInt("tongDoanhThu");
	                int tongChiPhi = rs.getInt("tongChiPhi");
	                int loiNhuan = rs.getInt("loiNhuan");

	                DoanhSoBanHangModalData dsbh = new DoanhSoBanHangModalData(ngay, tongDoanhThu, tongChiPhi, loiNhuan);
	                List.add(dsbh);
	            }
	            rs.close();
	            preparedStatement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 // Trường hợp time == TOÀN THỜI GIAN
	    else if (time == 1) {
	        StringBuilder sql = new StringBuilder();
	        sql.append("SELECT YEAR(ngayLapHD) AS NAM,SUM(tongTien) AS tongDoanhThu,SUM(tienGiam) AS tongChiPhi,(SUM(tongTien) - SUM(tienGiam)) AS loiNhuan ");
	        sql.append("FROM HoaDon WHERE ngayLapHD >= DATEADD(YEAR, -5, GETDATE()) ");
	        sql.append("GROUP BY YEAR(ngayLapHD) ");
	        sql.append("ORDER BY NAM;");
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                String ngay = rs.getString("NAM");
	                int tongDoanhThu = rs.getInt("tongDoanhThu");
	                int tongChiPhi = rs.getInt("tongChiPhi");
	                int loiNhuan = rs.getInt("loiNhuan");

	                DoanhSoBanHangModalData dsbh = new DoanhSoBanHangModalData(ngay, tongDoanhThu, tongChiPhi, loiNhuan);
	                List.add(dsbh);
	            }
	            rs.close();
	            preparedStatement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    else if (time == 2024) {
	        StringBuilder sql = new StringBuilder();
	        sql.append("SELECT YEAR(ngayLapHD) AS NAM, ")
	           .append("MONTH(ngayLapHD) AS THANG, ")
	           .append("SUM(tongTien) AS tongDoanhThu, ")
	           .append("SUM(tienGiam) AS tongChiPhi, ")
	           .append("(SUM(tongTien) - SUM(tienGiam)) AS loiNhuan ")
	           .append("FROM HoaDon ")
	           .append("WHERE YEAR(ngayLapHD) = 2024 ")
	           .append("GROUP BY YEAR(ngayLapHD), MONTH(ngayLapHD) ")  // Nhóm theo năm và tháng
	           .append("ORDER BY YEAR(ngayLapHD), MONTH(ngayLapHD);");  // Sắp xếp theo năm và tháng

	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                String nam = rs.getString("NAM");
	                String thang = rs.getString("THANG");
	                int tongDoanhThu = rs.getInt("tongDoanhThu");
	                int tongChiPhi = rs.getInt("tongChiPhi");
	                int loiNhuan = rs.getInt("loiNhuan");

	                DoanhSoBanHangModalData dsbh = new DoanhSoBanHangModalData("Tháng " + thang + "/" + nam, tongDoanhThu, tongChiPhi, loiNhuan);
	                List.add(dsbh);
	            }
	            rs.close();
	            preparedStatement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	 // Trường hợp time == 2023
	    else if (time == 2023) {
	        StringBuilder sql = new StringBuilder();
	        sql.append("SELECT YEAR(ngayLapHD) AS NAM, ")
	           .append("MONTH(ngayLapHD) AS THANG, ")
	           .append("SUM(tongTien) AS tongDoanhThu, ")
	           .append("SUM(tienGiam) AS tongChiPhi, ")
	           .append("(SUM(tongTien) - SUM(tienGiam)) AS loiNhuan ")
	           .append("FROM HoaDon ")
	           .append("WHERE YEAR(ngayLapHD) = 2023 ")
	           .append("GROUP BY YEAR(ngayLapHD), MONTH(ngayLapHD) ")  // Nhóm theo năm và tháng
	           .append("ORDER BY YEAR(ngayLapHD), MONTH(ngayLapHD);");  // Sắp xếp theo năm và tháng

	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                String nam = rs.getString("NAM");
	                String thang = rs.getString("THANG");
	                int tongDoanhThu = rs.getInt("tongDoanhThu");
	                int tongChiPhi = rs.getInt("tongChiPhi");
	                int loiNhuan = rs.getInt("loiNhuan");

	                DoanhSoBanHangModalData dsbh = new DoanhSoBanHangModalData("Tháng " + thang + "/" + nam, tongDoanhThu, tongChiPhi, loiNhuan);
	                List.add(dsbh);
	            }
	            rs.close();
	            preparedStatement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


	    return List;
	}
	public ArrayList<DoanhSoBanHangModalData> layDoanhSoBanHangTQTheoThoiGian(java.util.Date startDate, java.util.Date endDate) {
	    ArrayList<DoanhSoBanHangModalData> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();

	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT FORMAT(ngayLapHD, 'MM/yyyy') AS thang,SUM(tongTien) AS tongDoanhThu,SUM(tienGiam) AS tongChiPhi,(SUM(tongTien) - SUM(tienGiam)) AS loiNhuan ");
	    sql.append("FROM HoaDon ");
	    sql.append("WHERE ngayLapHD >= ? AND ngayLapHD <= ? ");
	    sql.append("GROUP BY FORMAT(ngayLapHD, 'MM/yyyy') ");
	    sql.append("ORDER BY FORMAT(ngayLapHD, 'MM/yyyy');");


	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	        preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
	        preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
                String thang = rs.getString("THANG");
                int tongDoanhThu = rs.getInt("tongDoanhThu");
                int tongChiPhi = rs.getInt("tongChiPhi");
                int loiNhuan = rs.getInt("loiNhuan");

                DoanhSoBanHangModalData dsbh = new DoanhSoBanHangModalData("Tháng " + thang, tongDoanhThu, tongChiPhi, loiNhuan);
                List.add(dsbh);
            }

	        rs.close();
	        preparedStatement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return List;
	}


}
