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
import entity.HoaDon_entity;
import entity.KhachHang_entity;
import entity.LoaiHoaDon_entity;
import entity.NhanVien_entity;
import java.util.List;

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
	            Timestamp ngayLapHD = rs.getTimestamp("ngayLapHD");
	            LocalDateTime lcNgayLapHD = (ngayLapHD != null) ? ngayLapHD.toLocalDateTime() : null;
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
		        	 Timestamp ngayLapHD = rs.getTimestamp("ngayLapHD");
			            LocalDateTime lcNgayLapHD = (ngayLapHD != null) ? ngayLapHD.toLocalDateTime() : null;
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

    public List<Object[]> getDoanhThuData(String timeRange, String reportType) {
        List<Object[]> data = new ArrayList<>();
        String query = "";
        
        // Xây dựng câu lệnh SQL dựa trên các tham số (timeRange, reportType)
        if (timeRange.equals("Trong ngày")) {
            query = "SELECT * FROM HoaDon WHERE DATEDIFF(day, NgayBan, GETDATE()) = 0"; // ví dụ
        } else if (timeRange.equals("Trong tuần")) {
            query = "SELECT * FROM HoaDon WHERE DATEDIFF(week, NgayBan, GETDATE()) = 0"; // ví dụ
        } else if (timeRange.equals("Trong tháng")) {
            query = "SELECT * FROM HoaDon WHERE DATEDIFF(month, NgayBan, GETDATE()) = 0"; // ví dụ
        }
        // Thêm điều kiện cho các kiểu báo cáo
        // Ví dụ: Doanh thu theo sản phẩm, theo nhân viên, v.v.
        if (reportType.equals("Doanh thu tổng quan")) {
            query += " ORDER BY NgayBan"; // Đặt thêm điều kiện sắp xếp
        } else if (reportType.equals("Doanh thu theo sản phẩm")) {
            query += " ORDER BY SanPham"; // Sắp xếp theo sản phẩm
        } else if (reportType.equals("Doanh thu theo nhân viên")) {
            query += " ORDER BY NhanVien"; // Sắp xếp theo nhân viên
        }
        
        try (Connection con = connectDB.accessDataBase();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                Object[] row = new Object[10];
                row[0] = rs.getInt("STT"); // Số thứ tự
                row[1] = rs.getDate("NgayBan"); // Ngày bán
                row[2] = rs.getString("NhanVien"); // Nhân viên
                row[3] = rs.getString("MaHoaDon"); // Mã hóa đơn
                row[4] = rs.getString("KhachHang"); // Khách hàng
                row[5] = rs.getDouble("ThanhTien"); // Thành tiền
                row[6] = rs.getDouble("ThueVAT"); // Thuế VAT
                row[7] = rs.getDouble("TongTien"); // Tổng tiền
                row[8] = rs.getDouble("GiaVon"); // Giá vốn
                row[9] = rs.getDouble("LoiNhuan"); // Lợi nhuận
                
                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}

    

