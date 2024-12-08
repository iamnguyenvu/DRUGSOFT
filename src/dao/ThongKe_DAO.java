package dao;

import java.sql.CallableStatement;
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
import nguyenvu.model.ModelData;
import nguyenvu.model.ModelDataSP;
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

	public ArrayList<SanPham_entity> getAllSanPhamHetHan() {
	    ArrayList<SanPham_entity> dssp = new ArrayList<SanPham_entity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT sp.maSP,tenSP,ctnh.soLuong,khoiLuong,donViTinh,nhaCungCap,gia,congDung,hinhAnhSP,maLoaiSP,thue,giaNhap\r\n"
	    		+ "FROM SanPham sp join ChiTietNhapHang ctnh on sp.maSP = ctnh.maSP\r\n"
	    		+ "WHERE ctnh.ngayHetHan < GETDATE()";
	    try {
	        java.sql.Statement st = con.createStatement();  
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String masp = rs.getString("maSP");
	            String tensp = rs.getString("tenSP");
	            int soLuong = rs.getInt("soLuong");

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

	            SanPham_entity sp = new SanPham_entity(masp, tensp, khoiLuong, donViTinh, Nhacc, gia, thanhPhan, congDung, hinhAnhsp, loaisp, soLuong,thue,giaNhap);
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
	    String sql = "SELECT sp.maSP,tenSP,ctnh.soLuong,khoiLuong,donViTinh,nhaCungCap,gia,congDung,hinhAnhSP,maLoaiSP,thue,giaNhap FROM SanPham sp join ChiTietNhapHang ctnh on sp.maSP = ctnh.maSP WHERE DATEDIFF(DAY, GETDATE(), ngayHetHan) < 30";
	    try {
	        java.sql.Statement st = con.createStatement();  
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String masp = rs.getString("maSP");
	            String tensp = rs.getString("tenSP");
	            int soLuong = rs.getInt("soLuong");
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

	            SanPham_entity sp = new SanPham_entity(masp, tensp, khoiLuong, donViTinh, Nhacc, gia, thanhPhan, congDung, hinhAnhsp, loaisp, soLuong,thue,giaNhap);
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

	            SanPham_entity sp = new SanPham_entity(masp, tensp, khoiLuong, donViTinh, Nhacc, gia, thanhPhan, congDung, hinhAnhsp, loaisp, soLuong,thue,giaNhap);
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

	            SanPham_entity sp = new SanPham_entity(masp, tensp, khoiLuong, donViTinh, Nhacc, gia, thanhPhan, congDung, hinhAnhsp, loaisp, soLuong,thue,giaNhap);
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

	            double khoiLuong = rs.getDouble("khoiLuong");
	            String donViTinh = rs.getString("donViTinh");
	            String Nhacc = rs.getString("nhaCungCap");
	            double gia = rs.getDouble("gia");
	            String congDung = rs.getString("congDung");
	            String thanhPhan = rs.getString("thanhPhan");
	            String hinhAnhsp = rs.getString("hinhAnhSP");
	            double thue = rs.getDouble("thue");
	            String maLoaiSP = rs.getString("maLoaiSP");
	            double giaNhap = rs.getDouble("giaNhap");
	            LoaiSanPham_entity loaisp = new LoaiSanPham_entity(maLoaiSP);

	            SanPham_entity sp = new SanPham_entity(masp, tensp, khoiLuong, donViTinh, Nhacc, gia, thanhPhan, congDung, hinhAnhsp, loaisp, soLuong,thue,giaNhap);
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
	    ArrayList<DoanhSoBanHangModalData> list = new ArrayList<>();
	    Connection connection = null;

	    try {
	        connection = connectDB.accessDataBase();
	        String sql = buildQueryByTime(time);
	        if (sql == null) {
	            throw new IllegalArgumentException("Thời gian không hợp lệ!");
	        }

	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                String ngay = rs.getString(1);
	                int tongDoanhThu = rs.getInt(2);
	                int tongChiPhi = rs.getInt(3);
	                int loiNhuan = rs.getInt(4);

	                DoanhSoBanHangModalData dsbh = new DoanhSoBanHangModalData(ngay, tongDoanhThu, tongChiPhi, loiNhuan);
	                list.add(dsbh);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return list;
	}

	public ArrayList<DoanhSoBanHangModalData> layDoanhSoBanHangTQTheoThoiGian(java.util.Date startDate, java.util.Date endDate) {
	    ArrayList<DoanhSoBanHangModalData> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();

	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT FORMAT(ngayLapHD, 'MM/yyyy') AS thang,SUM(tongTien) AS tongDoanhThu,SUM(sp.giaNhap * soLuong) AS tongChiPhi,(SUM(tongTien) - SUM(sp.giaNhap * soLuong)) AS loiNhuan ");
	    sql.append("FROM HoaDon hd Join ChiTietHoaDon cthd on hd.maHD = cthd.maHD Join SanPham sp on cthd.maSP = sp.maSP ");
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

                DoanhSoBanHangModalData dsbh = new DoanhSoBanHangModalData(thang, tongDoanhThu, tongChiPhi, loiNhuan);
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

	private String buildQueryByTime(int time) {
	    switch (time) {
	        case 7:
	            return "{CALL GetDoanhThuChiPhiLoiNhuanTrong7ngayTruoc}";

	        case 30:
	        	 return "{CALL GetDoanhThuChiPhiLoiNhuanTheoTuanTrong30Ngay}";

	        case 90:
	        	return "{CALL GetDoanhThuChiPhiLoiNhuanTrong90Ngay}";

	        case 365:
	        	return "{CALL GetDoanhThuChiPhiLoiNhuanTheo46NgayTrong365Ngay}";

	        case 1:
	        	return "{CALL GetDoanhThuChiPhiLoiNhuanTheoNam}";

	        case 2023:
	        	return "{CALL GetDoanhThuChiPhiLoiNhuanTheoNam2023}";

	        case 2024:
	            return "{CALL GetDoanhThuChiPhiLoiNhuan2024}";

	        default:
	            return null;
	    }
	}

	
	public ArrayList<ModelDataSP> SanPhamBanChay(int time) {
	    ArrayList<ModelDataSP> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();
	    
	    if(time == 2024) {
		    StringBuilder sql = new StringBuilder();
		    sql.append("SELECT TOP 7 sp.tenSP, SUM(cthd.soLuongSanPham) AS tongSoLuongBan FROM ChiTietHoaDon cthd ");
		    sql.append("JOIN SanPham sp ON cthd.maSP = sp.maSP JOIN HoaDon hd ON cthd.maHD = hd.maHD ");
		    sql.append("WHERE YEAR(ngayLapHD) = 2024 ");
		    sql.append("GROUP BY sp.maSP, sp.tenSP ");
		    sql.append("ORDER BY tongSoLuongBan DESC");


		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String tenSP = rs.getString("tenSP");
	                int tongSLBAN = rs.getInt("tongSoLuongBan");

	                ModelDataSP slbh = new ModelDataSP(tenSP, tongSLBAN);
	                List.add(slbh);
	            }

		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 2023) {
		    StringBuilder sql = new StringBuilder();
		    sql.append("SELECT TOP 7 sp.tenSP, SUM(cthd.soLuongSanPham) AS tongSoLuongBan FROM ChiTietHoaDon cthd ");
		    sql.append("JOIN SanPham sp ON cthd.maSP = sp.maSP JOIN HoaDon hd ON cthd.maHD = hd.maHD ");
		    sql.append("WHERE YEAR(ngayLapHD) = 2023 ");
		    sql.append("GROUP BY sp.maSP, sp.tenSP ");
		    sql.append("ORDER BY tongSoLuongBan DESC");


		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String tenSP = rs.getString("tenSP");
	                int tongSLBAN = rs.getInt("tongSoLuongBan");

	                ModelDataSP slbh = new ModelDataSP(tenSP, tongSLBAN);
	                List.add(slbh);
	            }

		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 0) {
			    StringBuilder sql = new StringBuilder();
			    sql.append("SELECT TOP 7 sp.tenSP, SUM(cthd.soLuongSanPham) AS tongSoLuongBan FROM ChiTietHoaDon cthd ");
			    sql.append("JOIN SanPham sp ON cthd.maSP = sp.maSP JOIN HoaDon hd ON cthd.maHD = hd.maHD ");
			    sql.append("GROUP BY sp.maSP, sp.tenSP ");
			    sql.append("ORDER BY tongSoLuongBan DESC");


			    try {
			    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

			        ResultSet rs = preparedStatement.executeQuery();

			        while (rs.next()) {
		                String tenSP = rs.getString("tenSP");
		                int tongSLBAN = rs.getInt("tongSoLuongBan");

		                ModelDataSP slbh = new ModelDataSP(tenSP, tongSLBAN);
		                List.add(slbh);
		            }

			        rs.close();
			        preparedStatement.close();
			        connection.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
	    }else {
		    StringBuilder sql = new StringBuilder();
		    sql.append("SELECT TOP 7 sp.tenSP, SUM(cthd.soLuongSanPham) AS tongSoLuongBan FROM ChiTietHoaDon cthd ");
		    sql.append("JOIN SanPham sp ON cthd.maSP = sp.maSP JOIN HoaDon hd ON cthd.maHD = hd.maHD ");
		    if(time == 2024) {
		    	 sql.append("WHERE YEAR(ngayLapHD) = 2024 ");
		    }else if(time == 2023) {
		    	 sql.append("WHERE YEAR(ngayLapHD) = 2023 ");
		    }else {
		    	sql.append("WHERE hd.ngayLapHD >= DATEADD(DAY, -?, GETDATE()) ");
		    }
		    sql.append("GROUP BY sp.maSP, sp.tenSP ");
		    sql.append("ORDER BY tongSoLuongBan DESC");


		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		        preparedStatement.setInt(1, time);

		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String tenSP = rs.getString("tenSP");
	                int tongSLBAN = rs.getInt("tongSoLuongBan");

	                ModelDataSP slbh = new ModelDataSP(tenSP, tongSLBAN);
	                List.add(slbh);
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
	public ArrayList<ModelDataSP> SanPhamBanCham(int time) {
	    ArrayList<ModelDataSP> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();
	    
	    if(time == 2024) {
		    StringBuilder sql = new StringBuilder();
		    sql.append("SELECT TOP 7 sp.tenSP, SUM(cthd.soLuongSanPham) AS tongSoLuongBan FROM ChiTietHoaDon cthd ");
		    sql.append("JOIN SanPham sp ON cthd.maSP = sp.maSP JOIN HoaDon hd ON cthd.maHD = hd.maHD ");
		    sql.append("WHERE YEAR(ngayLapHD) = 2024 ");
		    sql.append("GROUP BY sp.maSP, sp.tenSP ");
		    sql.append("ORDER BY tongSoLuongBan");


		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String tenSP = rs.getString("tenSP");
	                int tongSLBAN = rs.getInt("tongSoLuongBan");

	                ModelDataSP slbh = new ModelDataSP(tenSP, tongSLBAN);
	                List.add(slbh);
	            }

		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 2023) {
		    StringBuilder sql = new StringBuilder();
		    sql.append("SELECT TOP 7 sp.tenSP, SUM(cthd.soLuongSanPham) AS tongSoLuongBan FROM ChiTietHoaDon cthd ");
		    sql.append("JOIN SanPham sp ON cthd.maSP = sp.maSP JOIN HoaDon hd ON cthd.maHD = hd.maHD ");
		    sql.append("WHERE YEAR(ngayLapHD) = 2023 ");
		    sql.append("GROUP BY sp.maSP, sp.tenSP ");
		    sql.append("ORDER BY tongSoLuongBan");


		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String tenSP = rs.getString("tenSP");
	                int tongSLBAN = rs.getInt("tongSoLuongBan");

	                ModelDataSP slbh = new ModelDataSP(tenSP, tongSLBAN);
	                List.add(slbh);
	            }

		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 0) {
			    StringBuilder sql = new StringBuilder();
			    sql.append("SELECT TOP 7 sp.tenSP, SUM(cthd.soLuongSanPham) AS tongSoLuongBan FROM ChiTietHoaDon cthd ");
			    sql.append("JOIN SanPham sp ON cthd.maSP = sp.maSP JOIN HoaDon hd ON cthd.maHD = hd.maHD ");
			    sql.append("GROUP BY sp.maSP, sp.tenSP ");
			    sql.append("ORDER BY tongSoLuongBan");


			    try {
			    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

			        ResultSet rs = preparedStatement.executeQuery();

			        while (rs.next()) {
		                String tenSP = rs.getString("tenSP");
		                int tongSLBAN = rs.getInt("tongSoLuongBan");

		                ModelDataSP slbh = new ModelDataSP(tenSP, tongSLBAN);
		                List.add(slbh);
		            }

			        rs.close();
			        preparedStatement.close();
			        connection.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
	    }else {
		    StringBuilder sql = new StringBuilder();
		    sql.append("SELECT TOP 7 sp.tenSP, SUM(cthd.soLuongSanPham) AS tongSoLuongBan FROM ChiTietHoaDon cthd ");
		    sql.append("JOIN SanPham sp ON cthd.maSP = sp.maSP JOIN HoaDon hd ON cthd.maHD = hd.maHD ");
		    if(time == 2024) {
		    	 sql.append("WHERE YEAR(ngayLapHD) = 2024 ");
		    }else if(time == 2023) {
		    	 sql.append("WHERE YEAR(ngayLapHD) = 2023 ");
		    }else {
		    	sql.append("WHERE hd.ngayLapHD >= DATEADD(DAY, -?, GETDATE()) ");
		    }
		    sql.append("GROUP BY sp.maSP, sp.tenSP ");
		    sql.append("ORDER BY tongSoLuongBan");


		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		        preparedStatement.setInt(1, time);

		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String tenSP = rs.getString("tenSP");
	                int tongSLBAN = rs.getInt("tongSoLuongBan");

	                ModelDataSP slbh = new ModelDataSP(tenSP, tongSLBAN);
	                List.add(slbh);
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
	public ArrayList<ModalDataSoLuongGiaoDich> SoLuongGiaoDich(int time) {
	    ArrayList<ModalDataSoLuongGiaoDich> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();
	    
	    if(time == 2024) {
		    StringBuilder sql = new StringBuilder();
		    sql.append("SELECT MONTH(ngayLapHD) AS Thang, COUNT(*) AS TongSoGiaoDich ");
		    sql.append("FROM HoaDon ");
		    sql.append("WHERE YEAR(ngayLapHD) = YEAR(GETDATE()) AND trangThai = 1 ");
		    sql.append("GROUP BY MONTH(ngayLapHD) ");
		    sql.append("ORDER BY Thang");


		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String thang = rs.getString("Thang");
	                int TongSoGiaoDich = rs.getInt("TongSoGiaoDich");

	                ModalDataSoLuongGiaoDich slbh = new ModalDataSoLuongGiaoDich(thang, TongSoGiaoDich);
	                List.add(slbh);
	            }

		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 2023) {
		    StringBuilder sql = new StringBuilder();
		    sql.append("SELECT MONTH(ngayLapHD) AS Thang, COUNT(*) AS TongSoGiaoDich ");
		    sql.append("FROM HoaDon ");
		    sql.append("WHERE YEAR(ngayLapHD) = YEAR(GETDATE())-1 AND trangThai = 1 ");
		    sql.append("GROUP BY MONTH(ngayLapHD) ");
		    sql.append("ORDER BY Thang");


		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String thang = rs.getString("Thang");
	                int TongSoGiaoDich = rs.getInt("TongSoGiaoDich");

	                ModalDataSoLuongGiaoDich slbh = new ModalDataSoLuongGiaoDich(thang, TongSoGiaoDich);
	                List.add(slbh);
	            }

		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 7) {
		    StringBuilder sql = new StringBuilder();
		    sql.append("SELECT CONVERT(DATE, ngayLapHD) AS Ngay, COUNT(*) AS TongSoGiaoDich ");
		    sql.append("FROM HoaDon ");
		    sql.append("WHERE ngayLapHD >= DATEADD(DAY, -7, GETDATE()) AND ngayLapHD < GETDATE()AND trangThai = 1 ");
		    sql.append("GROUP BY CONVERT(DATE, ngayLapHD) ");
		    sql.append("ORDER BY Ngay ASC;");


		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String thang = rs.getString("Ngay");
	                int TongSoGiaoDich = rs.getInt("TongSoGiaoDich");

	                ModalDataSoLuongGiaoDich slbh = new ModalDataSoLuongGiaoDich(thang, TongSoGiaoDich);
	                List.add(slbh);
	            }

		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 30) {
		    StringBuilder sql = new StringBuilder();
		    sql.append("WITH DateRanges AS (SELECT  ");
		    sql.append("DATEADD(DAY, (4 * (ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1)), DATEADD(DAY, -30, GETDATE())) AS StartDate, ");
		    sql.append("DATEADD(DAY, (4 * ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1), DATEADD(DAY, -30, GETDATE())) AS EndDate ");
		    sql.append("FROM master.dbo.spt_values WHERE type = 'P' AND number <= 7), ");
		    sql.append("WeeklyData AS (SELECT CONCAT(FORMAT(DateRanges.StartDate, 'dd/MM'), ' - ', FORMAT(DateRanges.EndDate, 'dd/MM')) AS Tuan,COUNT(*) AS TongSoGiaoDich,DateRanges.StartDate ");
		    sql.append("FROM HoaDon JOIN DateRanges  ");
		    sql.append("ON ngayLapHD >= DateRanges.StartDate AND ngayLapHD < DateRanges.EndDate ");
		    sql.append("WHERE trangThai = 1GROUP BY DateRanges.StartDate, DateRanges.EndDate) ");
		    sql.append("SELECT Tuan, TongSoGiaoDich FROM WeeklyData ORDER BY StartDate ASC; ");
		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String thang = rs.getString("Tuan");
	                int TongSoGiaoDich = rs.getInt("TongSoGiaoDich");

	                ModalDataSoLuongGiaoDich slbh = new ModalDataSoLuongGiaoDich(thang, TongSoGiaoDich);
	                List.add(slbh);
	            }

		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 90) {
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("WITH DateRanges AS ( ");
	    	sql.append("    SELECT ");
	    	sql.append("        DATEADD(DAY, (12 * (ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1)), DATEADD(DAY, -90, GETDATE())) AS StartDate, ");
	    	sql.append("        DATEADD(DAY, (12 * ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1), DATEADD(DAY, -90, GETDATE())) AS EndDate ");
	    	sql.append("    FROM master.dbo.spt_values ");
	    	sql.append("    WHERE type = 'P' AND number <= 7 ");
	    	sql.append("), ");
	    	sql.append("WeeklyData AS ( ");
	    	sql.append("    SELECT ");
	    	sql.append("        CONCAT(FORMAT(DateRanges.StartDate, 'dd/MM'), ' - ', FORMAT(DateRanges.EndDate, 'dd/MM')) AS Tuan, ");
	    	sql.append("        COUNT(*) AS TongSoGiaoDich, ");
	    	sql.append("        DateRanges.StartDate ");
	    	sql.append("    FROM HoaDon ");
	    	sql.append("    JOIN DateRanges ");
	    	sql.append("    ON ngayLapHD >= DateRanges.StartDate AND ngayLapHD < DateRanges.EndDate ");
	    	sql.append("    WHERE trangThai = 1 ");
	    	sql.append("    GROUP BY DateRanges.StartDate, DateRanges.EndDate ");
	    	sql.append(") ");
	    	sql.append("SELECT Tuan, TongSoGiaoDich ");
	    	sql.append("FROM WeeklyData ");
	    	sql.append("ORDER BY StartDate ASC; ");
		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String thang = rs.getString("Tuan");
	                int TongSoGiaoDich = rs.getInt("TongSoGiaoDich");

	                ModalDataSoLuongGiaoDich slbh = new ModalDataSoLuongGiaoDich(thang, TongSoGiaoDich);
	                List.add(slbh);
	            }

		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 365) {
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("WITH DateRanges AS ( ");
	    	sql.append("    SELECT ");
	    	sql.append("        DATEADD(DAY, (47 * (ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1)), DATEADD(DAY, -365, GETDATE())) AS StartDate, ");
	    	sql.append("        DATEADD(DAY, (47 * ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1), DATEADD(DAY, -365, GETDATE())) AS EndDate ");
	    	sql.append("    FROM master.dbo.spt_values ");
	    	sql.append("    WHERE type = 'P' AND number <= 12 ");
	    	sql.append("), ");
	    	sql.append("MonthlyData AS ( ");
	    	sql.append("    SELECT ");
	    	sql.append("        CONCAT(FORMAT(DateRanges.StartDate, 'dd/MM'), ' - ', FORMAT(DateRanges.EndDate, 'dd/MM')) AS Thang, ");
	    	sql.append("        COUNT(*) AS TongSoGiaoDich, ");
	    	sql.append("        DateRanges.StartDate ");
	    	sql.append("    FROM HoaDon ");
	    	sql.append("    JOIN DateRanges ");
	    	sql.append("        ON ngayLapHD >= DateRanges.StartDate AND ngayLapHD < DateRanges.EndDate ");
	    	sql.append("    WHERE trangThai = 1 ");
	    	sql.append("    GROUP BY DateRanges.StartDate, DateRanges.EndDate ");
	    	sql.append(") ");
	    	sql.append("SELECT Thang, TongSoGiaoDich ");
	    	sql.append("FROM MonthlyData ");
	    	sql.append("ORDER BY StartDate;");

		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String thang = rs.getString("Thang");
	                int TongSoGiaoDich = rs.getInt("TongSoGiaoDich");

	                ModalDataSoLuongGiaoDich slbh = new ModalDataSoLuongGiaoDich(thang, TongSoGiaoDich);
	                List.add(slbh);
	            }

		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 0) {
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("WITH YearlyData AS ( ");
	    	sql.append("    SELECT ");
	    	sql.append("        YEAR(ngayLapHD) AS Nam, ");
	    	sql.append("        COUNT(*) AS TongSoGiaoDich ");
	    	sql.append("    FROM HoaDon ");
	    	sql.append("    WHERE trangThai = 1 ");
	    	sql.append("    GROUP BY YEAR(ngayLapHD) ");
	    	sql.append(") ");
	    	sql.append("SELECT Nam, TongSoGiaoDich ");
	    	sql.append("FROM YearlyData ");
	    	sql.append("ORDER BY Nam ASC;");

		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String thang = rs.getString("Nam");
	                int TongSoGiaoDich = rs.getInt("TongSoGiaoDich");

	                ModalDataSoLuongGiaoDich slbh = new ModalDataSoLuongGiaoDich(thang, TongSoGiaoDich);
	                List.add(slbh);
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
	
	public ArrayList<ModalDataSoLuongGiaoDich> SoLuongGiaoDich(java.util.Date startDate, java.util.Date endDate) {
	    ArrayList<ModalDataSoLuongGiaoDich> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	    String formattedStartDate = sdf.format(startDate);
	    String formattedEndDate = sdf.format(endDate);

	    StringBuilder sql = new StringBuilder();    
	    sql.append("WITH DateRanges AS (\r\n"
	    		+ "    SELECT \r\n"
	    		+ "        DATEADD(DAY, ((DATEDIFF(DAY, ?, ?) / 8) * (ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1)), ?) AS StartDate,\r\n"
	    		+ "        DATEADD(DAY, ((DATEDIFF(DAY, ?, ?) / 8) * ROW_NUMBER() OVER (ORDER BY (SELECT NULL))), ?) AS EndDate\r\n"
	    		+ "    FROM master.dbo.spt_values\r\n"
	    		+ "    WHERE type = 'P' AND number <= 8\r\n"
	    		+ "),\r\n"
	    		+ "GroupedData AS (\r\n"
	    		+ "    SELECT \r\n"
	    		+ "        CONCAT(FORMAT(DateRanges.StartDate, 'dd/MM'), ' - ', FORMAT(DateRanges.EndDate, 'dd/MM')) AS KhoangThoiGian,\r\n"
	    		+ "        COUNT(*) AS TongSoGiaoDich,\r\n"
	    		+ "        DateRanges.StartDate\r\n"
	    		+ "    FROM HoaDon\r\n"
	    		+ "    JOIN DateRanges\r\n"
	    		+ "        ON ngayLapHD >= DateRanges.StartDate AND ngayLapHD < DateRanges.EndDate\r\n"
	    		+ "    WHERE trangThai = 1\r\n"
	    		+ "    GROUP BY DateRanges.StartDate, DateRanges.EndDate\r\n"
	    		+ ")\r\n"
	    		+ "SELECT KhoangThoiGian, TongSoGiaoDich\r\n"
	    		+ "FROM GroupedData\r\n"
	    		+ "ORDER BY StartDate ASC; ");
	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	        preparedStatement.setString(1, formattedStartDate);
	        preparedStatement.setString(2, formattedEndDate);
	        preparedStatement.setString(3, formattedStartDate);
	        preparedStatement.setString(4, formattedStartDate);
	        preparedStatement.setString(5, formattedEndDate);
	        preparedStatement.setString(6, formattedStartDate);

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String khoangThoiGian = rs.getString("KhoangThoiGian"); 
	            int tongSoGiaoDich = rs.getInt("TongSoGiaoDich");

	            ModalDataSoLuongGiaoDich slbh = new ModalDataSoLuongGiaoDich(khoangThoiGian, tongSoGiaoDich);
	            List.add(slbh);
	        }

	        rs.close();
	        preparedStatement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return List;
	}

	public ArrayList<ModelData> doanhSoBanHangNhanVien(int time){
	    ArrayList<ModelData> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();

	    if(time == 7 || time  == 30 || time ==90 || time == 365) {
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT TOP 7 [hotenNV], SUM([tongTien]) AS DoanhSo \r\n"
	    			+ "FROM HoaDon hd join NhanVien nv on hd.maNV = nv.maNV\r\n"
	    			+ "WHERE ngayLapHD >= DATEADD(DAY, -?, GETDATE())\r\n"
	    			+ "GROUP BY [hotenNV]\r\n"
	    			+ "ORDER BY SUM([tongTien])");
		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		    	preparedStatement.setInt(1, time);
		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String hotenNV = rs.getString("hotenNV");
	                int DoanhSo = rs.getInt("DoanhSo");

	                ModelData doanhSo = new ModelData(hotenNV,DoanhSo);
	                List.add(doanhSo);
	            }
		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 0) {
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT TOP 7 [hotenNV], SUM([tongTien]) AS DoanhSo \r\n"
	    			+ "FROM HoaDon hd join NhanVien nv on hd.maNV = nv.maNV\r\n"
	    			+ "GROUP BY [hotenNV]\r\n"
	    			+ "ORDER BY SUM([tongTien])");
		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String hotenNV = rs.getString("hotenNV");
	                int DoanhSo = rs.getInt("DoanhSo");

	                ModelData doanhSo = new ModelData(hotenNV,DoanhSo);
	                List.add(doanhSo);
	            }
		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 2024) {
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT TOP 7 [hotenNV], SUM([tongTien]) AS DoanhSo\r\n"
	    			+ "	    			FROM HoaDon hd join NhanVien nv on hd.maNV = nv.maNV\r\n"
	    			+ "	    			WHERE YEAR(ngayLapHD) = 2024\r\n"
	    			+ "	    			GROUP BY [hotenNV]\r\n"
	    			+ "	    			ORDER BY SUM([tongTien])");
		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String hotenNV = rs.getString("hotenNV");
	                int DoanhSo = rs.getInt("DoanhSo");

	                ModelData doanhSo = new ModelData(hotenNV,DoanhSo);
	                List.add(doanhSo);
	            }
		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 2023) {
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT TOP 7 [hotenNV], SUM([tongTien]) AS DoanhSo\r\n"
	    			+ "	    			FROM HoaDon hd join NhanVien nv on hd.maNV = nv.maNV\r\n"
	    			+ "	    			WHERE YEAR(ngayLapHD) = 2023\r\n"
	    			+ "	    			GROUP BY [hotenNV]\r\n"
	    			+ "	    			ORDER BY SUM([tongTien])");
		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String hotenNV = rs.getString("hotenNV");
	                int DoanhSo = rs.getInt("DoanhSo");

	                ModelData doanhSo = new ModelData(hotenNV,DoanhSo);
	                List.add(doanhSo);
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
	
	public ArrayList<ModelData> doanhSoBanHangNhanVien(java.util.Date startDate, java.util.Date endDate) {
	    ArrayList<ModelData> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	    String formattedStartDate = sdf.format(startDate);
	    String formattedEndDate = sdf.format(endDate);

	    StringBuilder sql = new StringBuilder();    
	    sql.append("SELECT TOP 7 [hotenNV], SUM([tongTien]) AS DoanhSo\r\n"
	    		+ "	    			FROM HoaDon hd join NhanVien nv on hd.maNV = nv.maNV\r\n"
	    		+ "	    			WHERE ngayLapHD BETWEEN ? AND ?\r\n"
	    		+ "	    			GROUP BY [hotenNV]\r\n"
	    		+ "	    			ORDER BY SUM([tongTien])");
	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	        preparedStatement.setString(1, formattedStartDate);
	        preparedStatement.setString(2, formattedEndDate);

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String hotenNV = rs.getString("hotenNV"); 
	            int DoanhSo = rs.getInt("DoanhSo");

	            ModelData dsbh = new ModelData(hotenNV, DoanhSo);
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
	public ArrayList<ModalDataSoLuongGiaoDich> soLuongGiaoDichNhanVien(int time){
	    ArrayList<ModalDataSoLuongGiaoDich> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();

	    if(time == 7 || time  == 30 || time ==90 || time == 365) {
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT TOP 10 nv.hotenNV, COUNT(hd.maHD) AS soLuongGiaoDich\r\n"
	    			+ "FROM HoaDon hd\r\n"
	    			+ "JOIN NhanVien nv ON hd.maNV = nv.maNV\r\n"
	    			+ "WHERE hd.ngayLapHD >= DATEADD(DAY, -?, CAST(GETDATE() AS DATE))\r\n"
	    			+ "GROUP BY hd.maNV, nv.hotenNV\r\n"
	    			+ "ORDER BY soLuongGiaoDich DESC;");
		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		    	preparedStatement.setInt(1, time);
		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String hotenNV = rs.getString("hotenNV");
	                int DoanhSo = rs.getInt("soLuongGiaoDich");

	                ModalDataSoLuongGiaoDich doanhSo = new ModalDataSoLuongGiaoDich(hotenNV,DoanhSo);
	                List.add(doanhSo);
	            }
		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 0) {
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT TOP 10 nv.hotenNV, COUNT(hd.maHD) AS soLuongGiaoDich\r\n"
	    			+ "FROM HoaDon hd\r\n"
	    			+ "JOIN NhanVien nv ON hd.maNV = nv.maNV\r\n"
	    			+ "GROUP BY hd.maNV, nv.hotenNV\r\n"
	    			+ "ORDER BY soLuongGiaoDich DESC;");
		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String hotenNV = rs.getString("hotenNV");
	                int DoanhSo = rs.getInt("soLuongGiaoDich");

	                ModalDataSoLuongGiaoDich doanhSo = new ModalDataSoLuongGiaoDich(hotenNV,DoanhSo);
	                List.add(doanhSo);
	            }
		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 2024) {
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT TOP 10 nv.hotenNV, COUNT(hd.maHD) AS soLuongGiaoDich\r\n"
	    			+ "FROM HoaDon hd\r\n"
	    			+ "JOIN NhanVien nv ON hd.maNV = nv.maNV\r\n"
	    			+ "WHERE year(ngayLapHD) = 2024\r\n"
	    			+ "GROUP BY hd.maNV, nv.hotenNV\r\n"
	    			+ "ORDER BY soLuongGiaoDich DESC;");
		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String hotenNV = rs.getString("hotenNV");
	                int DoanhSo = rs.getInt("soLuongGiaoDich");

	                ModalDataSoLuongGiaoDich doanhSo = new ModalDataSoLuongGiaoDich(hotenNV,DoanhSo);
	                List.add(doanhSo);
	            }
		        rs.close();
		        preparedStatement.close();
		        connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }else if(time == 2023) {
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT TOP 10 nv.hotenNV, COUNT(hd.maHD) AS soLuongGiaoDich\r\n"
	    			+ "FROM HoaDon hd\r\n"
	    			+ "JOIN NhanVien nv ON hd.maNV = nv.maNV\r\n"
	    			+ "WHERE year(ngayLapHD) = 2023\r\n"
	    			+ "GROUP BY hd.maNV, nv.hotenNV\r\n"
	    			+ "ORDER BY soLuongGiaoDich DESC;");
		    try {
		    	PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
	                String hotenNV = rs.getString("hotenNV");
	                int DoanhSo = rs.getInt("soLuongGiaoDich");

	                ModalDataSoLuongGiaoDich doanhSo = new ModalDataSoLuongGiaoDich(hotenNV,DoanhSo);
	                List.add(doanhSo);
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

	public ArrayList<ModalDataSoLuongGiaoDich> soLuongGiaoDichNhanVien(java.util.Date startDate, java.util.Date endDate) {
	    ArrayList<ModalDataSoLuongGiaoDich> List = new ArrayList<>();
	    Connection connection = connectDB.accessDataBase();
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	    String formattedStartDate = sdf.format(startDate);
	    String formattedEndDate = sdf.format(endDate);

	    StringBuilder sql = new StringBuilder();    
	    sql.append("SELECT TOP 10 nv.hotenNV, COUNT(hd.maHD) AS soLuongGiaoDich\r\n"
	    		+ "FROM HoaDon hd\r\n"
	    		+ "JOIN NhanVien nv ON hd.maNV = nv.maNV\r\n"
	    		+ "WHERE ngayLapHD BETWEEN ? AND ?\r\n"
	    		+ "GROUP BY hd.maNV, nv.hotenNV\r\n"
	    		+ "ORDER BY soLuongGiaoDich DESC;");
	    try {
	        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
	        preparedStatement.setString(1, formattedStartDate);
	        preparedStatement.setString(2, formattedEndDate);

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String hotenNV = rs.getString("hotenNV"); 
	            int DoanhSo = rs.getInt("soLuongGiaoDich");

	            ModalDataSoLuongGiaoDich dsbh = new ModalDataSoLuongGiaoDich(hotenNV, DoanhSo);
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
