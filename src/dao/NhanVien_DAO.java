package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.connectDB;

public class NhanVien_DAO {

    // Get full employee details by maNV
    public String getNhanVienInfo(String maNV) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String info = null;
//        try {
//            ps = con.prepareStatement("SELECT hoNV, tenNV,, sdt, cccd, chucVu, diaChi, ngaySinh, trangThai, ngayVaoLam, hinhAnhNV, tenDangNhap, maLoaiNV FROM NhanVien WHERE maNV = ?");
//            ps.setString(1, maNV);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                String trangThai = rs.getBoolean("trangThai") ? "Đang làm" : "Đã nghỉ việc";
//                info = "Mã NV: " + maNV + 
//                        ", Họ: " + rs.getString("hoNV") + 
//                        ", Tên: " + rs.getString("tenNV") + 
//                        ", SĐT: " + rs.getString("sdt") + 
//                        ", CCCD: " + rs.getString("cccd") + 
//                        ", Chức vụ: " + rs.getString("chucVu") + 
//                        ", Địa chỉ: " + rs.getString("diaChi") + 
//                        ", Ngày sinh: " + rs.getDate("ngaySinh") + 
//                        ", Trạng thái: " + trangThai + 
//                        ", Ngày vào làm: " + rs.getDate("ngayVaoLam") + 
//                        ", Hình ảnh: " + rs.getString("hinhAnhNV") + 
//                        ", Tên đăng nhập: " + rs.getString("tenDangNhap") + 
//                        ", Mã loại NV: " + rs.getString("maLoaiNV");
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
//        return info;
        return "Mã NV: " + maNV + ", Họ và tên: Nguyên Vũ, SĐT: 0909123456, CCCD: 123456789, Chức vụ: Nhân viên, Địa chỉ: TP HCM, Ngày sinh: 1990-01-01, Trạng thái: Đang làm, Ngày vào làm: 2020-05-01, Hình ảnh: /resources/images/nhanvien/default_avatar.png, Tên đăng nhập: nguyenvu, Mã loại NV: NV";
    }

    // Get specific field: Chức vụ by maNV
    public String getChucVu(String maNV) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String chucVu = null;
//        try {
//            ps = con.prepareStatement("SELECT chucVu FROM NhanVien WHERE maNV = ?");
//            ps.setString(1, maNV);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                chucVu = rs.getString("chucVu");
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
//        return chucVu;
        return "Nhân viên"; // Hardcoded value
    }

    // Get employee's status: 1 = Đang làm, 0 = Đã nghỉ việc
    public String getTrangThai(String maNV) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String trangThai = null;
//        try {
//            ps = con.prepareStatement("SELECT trangThai FROM NhanVien WHERE maNV = ?");
//            ps.setString(1, maNV);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                trangThai = rs.getBoolean("trangThai") ? "Đang làm" : "Đã nghỉ việc";
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
        return "Đang làm"; // Hardcoded example
    }

    // Get employee avatar
    public String getAvatar(String maNV) {
//        Connection con = connectDB.accessDataBase();
//        if (con == null) return null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String hinhAnhNV = null;
//        try {
//            ps = con.prepareStatement("SELECT hinhAnhNV FROM NhanVien WHERE maNV = ?");
//            ps.setString(1, maNV);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                hinhAnhNV = rs.getString("hinhAnhNV");
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
//        return hinhAnhNV;
        return "/resources/images/nhanvien/default_avatar.png"; // Hardcoded value
    }

}
