package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import connectDB.connectDB;
import entity.NhanVien_entity;

public class NhanVien_DAO {

    // Lấy thông tin nhân viên đầy đủ dựa trên mã nhân viên
    public NhanVien_entity getNhanVienInfo(String maNV) {
        NhanVien_entity nhanVien = null;
        String sql = "SELECT * FROM NhanVien WHERE maNV = ?";

        try (Connection con = connectDB.accessDataBase();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maNV);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nhanVien = mapResultSetToNhanVien(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanVien;
    }

    // Lấy tất cả nhân viên
    public List<NhanVien_entity> getAllNhanVien() {
        List<NhanVien_entity> nhanVienList = new ArrayList<>();
        String query = "SELECT * FROM NhanVien"; // Change to your actual table name

        try (Connection con = connectDB.accessDataBase();
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                nhanVienList.add(mapResultSetToNhanVien(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanVienList;
    }

    // Thêm nhân viên mới
    // public boolean addNhanVien(NhanVien_entity nhanVien) {
    // String sql = "INSERT INTO NhanVien(maNV, hoTenNV, ngaySinh, diaChi, sdt,
    // cccd, ngayVaoLam, chucVu, trangThai, gioiTinh, hinhAnhNV, maLoaiNV) VALUES
    // (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    // return executeInsert(nhanVien, sql);
    // }
    
    // Cập nhật thông tin nhân viên
//    public boolean updateNhanVien(NhanVien_entity nhanVien) {
//        String sql = "UPDATE NhanVien SET " +
//                "hoTenNV = ?, " +
//                "ngaySinh = ?, " +
//                "diaChi = ?, " +
//                "sdt = ?, " +
//                "cccd = ?, " +
//                "ngayVaoLam = ?, " +
//                "trangThai = ?, " +
//                "gioiTinh = ?, " +
//                "hinhAnhNV = ?, " +
//                "maLoaiNV = ? " +
//                "WHERE maNV = ?";
//        return executeUpdate(nhanVien, sql);
//    }
    public boolean updateNhanVien(NhanVien_entity nhanVien) {
        String sql = "UPDATE NhanVien SET " +
                "hoTenNV = ?, " +
                "gioiTinh = ?, " +
                "sdt = ?, " +
                "cccd = ?, " +
                "diaChi = ?, " +
                "ngaySinh = ?, " +
                "trangThai = ?, " +
                "ngayVaoLam = ?, " +
                "hinhAnhNV = ?, " +
                "maLoaiNV = ? " +
                "WHERE maNV = ?";

        try (Connection conn = connectDB.accessDataBase();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nhanVien.getHoTenNV());
            pstmt.setString(2, nhanVien.getGioiTinh());
            pstmt.setString(3, nhanVien.getSdt());
            pstmt.setString(4, nhanVien.getCccd());
            pstmt.setString(5, nhanVien.getDiaChi());
            pstmt.setDate(6, Date.valueOf(nhanVien.getNgaySinh()));
            pstmt.setBoolean(7, nhanVien.isTrangThai());
            pstmt.setDate(8, Date.valueOf(nhanVien.getNgayVaoLam()));
            pstmt.setString(9, nhanVien.getHinhAnhNV());
            pstmt.setString(10, nhanVien.getMaLoaiNV());
            pstmt.setString(11, nhanVien.getMaNV());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Integrity Constraint Violation: " + e.getMessage());
        } catch (SQLDataException e) {
            System.err.println("Data Exception: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }

        return false;
    }
    // Xóa nhân viên dựa trên mã nhân viên
    public boolean deleteNhanVien(String maNV) {
        String sql = "DELETE FROM NhanVien WHERE maNV = ?";

        try (Connection con = connectDB.accessDataBase();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maNV);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm nhân viên theo SDT
    public List<NhanVien_entity> timKiemNhanVienSDT(String searchText) {
        List<NhanVien_entity> result = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE sdt = ?";

        try (Connection con = connectDB.accessDataBase();
                PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, searchText); // Tìm kiếm theo số điện thoại

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(mapResultSetToNhanVien(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<NhanVien_entity> locNhanVien(String loaiNV, boolean sortAsc) {
        List<NhanVien_entity> nhanVienList = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM NhanVien");

        // Filter by type if not "Tất cả"
        if (!"Tất cả".equals(loaiNV)) {
            query.append(" WHERE maLoaiNV = ?");
        }

        // Add ordering
        query.append(" ORDER BY hoTenNV ");
        query.append(sortAsc ? "ASC" : "DESC");

        try (Connection con = connectDB.accessDataBase();
                PreparedStatement pstmt = con.prepareStatement(query.toString())) {

            // Set parameter if needed
            if (!"Tất cả".equals(loaiNV)) {
                pstmt.setString(1, loaiNV);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    nhanVienList.add(mapResultSetToNhanVien(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print exception for debugging
        }

        return nhanVienList;
    }

    // Ánh xạ ResultSet tới NhanVien_entity
    private NhanVien_entity mapResultSetToNhanVien(ResultSet rs) throws SQLException {
        NhanVien_entity nhanVien = new NhanVien_entity();
        nhanVien.setMaNV(rs.getString("maNV"));
        nhanVien.setHoTenNV(rs.getString("hoTenNV"));
        nhanVien.setNgaySinh(rs.getDate("ngaySinh") != null ? rs.getDate("ngaySinh").toLocalDate() : null);
        nhanVien.setDiaChi(rs.getString("diaChi"));
        nhanVien.setSdt(rs.getString("sdt"));
        nhanVien.setCccd(rs.getString("cccd"));
        nhanVien.setNgayVaoLam(rs.getDate("ngayVaoLam") != null ? rs.getDate("ngayVaoLam").toLocalDate() : null);
        nhanVien.setTrangThai(rs.getBoolean("trangThai"));
        nhanVien.setGioiTinh(rs.getString("gioiTinh"));
        nhanVien.setHinhAnhNV(rs.getString("hinhAnhNV"));
        nhanVien.setMaLoaiNV(rs.getString("maLoaiNV"));
        return nhanVien;
    }

    // Thiết lập tham số cho các câu truy vấn insert và update
    private void setInsertUpdateParams(PreparedStatement ps, NhanVien_entity nhanVien) throws SQLException {
        ps.setString(1, nhanVien.getHoTenNV()); // 1. hoTenNV
        ps.setDate(2, Date.valueOf(nhanVien.getNgaySinh())); // 2. ngaySinh
        ps.setString(3, nhanVien.getDiaChi()); // 3. diaChi
        ps.setString(4, nhanVien.getSdt()); // 4. sdt
        ps.setString(5, nhanVien.getCccd()); // 5. cccd
        ps.setDate(6, Date.valueOf(nhanVien.getNgayVaoLam())); // 6. ngayVaoLam
        ps.setBoolean(7, nhanVien.isTrangThai()); // 7. trangThai
        ps.setString(8, nhanVien.getGioiTinh()); // 8. gioiTinh
        ps.setString(9, nhanVien.getHinhAnhNV()); // 9. hinhAnhNV
        ps.setString(10, nhanVien.getMaLoaiNV()); // 10. maLoaiNV
        ps.setString(11, nhanVien.getMaNV()); // 11. maNV (trong WHERE)
    }

    //
    // // Thực hiện thêm nhân viên
    // private boolean executeInsert(NhanVien_entity nhanVien, String sql) {
    // try (Connection con = connectDB.accessDataBase();
    // PreparedStatement ps = con.prepareStatement(sql)) {
    //
    // setInsertUpdateParams(ps, nhanVien);
    // return ps.executeUpdate() > 0; // Trả về true nếu có ít nhất 1 bản ghi được
    // thêm
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return false;
    // }

    // Thực hiện cập nhật nhân viên
    private boolean executeUpdate(NhanVien_entity nhanVien, String sql) {
        try (Connection con = connectDB.accessDataBase();
                PreparedStatement ps = con.prepareStatement(sql)) {

            setInsertUpdateParams(ps, nhanVien);
            ps.setString(11, nhanVien.getMaNV()); // Cập nhật mã nhân viên cho câu truy vấn
            return ps.executeUpdate() > 0; // Trả về true nếu có ít nhất 1 bản ghi được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Phương thức lấy danh sách nhân viên theo mã nhân viên
    public List<NhanVien_entity> getNhanVienByMaNV(String maNV) {
        List<NhanVien_entity> result = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE maNV = ?";

        try (Connection con = connectDB.accessDataBase();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, maNV);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.add(mapResultSetToNhanVien(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public ArrayList<String> getAllMaNhanVien() {
        ArrayList<String> dsMaNV = new ArrayList<>();
        String sql = "SELECT maNV FROM NhanVien";

        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                dsMaNV.add(rs.getString("maNV"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsMaNV;
    }

    
    // public boolean insertEmployee(NhanVien_entity nhanVien) {
    // // TODO Auto-generated method stub
    // String sql = "INSERT INTO NhanVien(maNV, hotenNV, gioiTinh, sdt, cccd,
    // diaChi, ngaySinh, trangThai, ngayVaoLam, hinhAnhNV) " +
    // "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //
    // try (Connection conn = connectDB.accessDataBase();
    // PreparedStatement pstmt = conn.prepareStatement(sql)) {
    //
    // pstmt.setString(1, nhanVien.getMaNV());
    // pstmt.setString(2, nhanVien.getHoTenNV());
    // pstmt.setString(3, nhanVien.getGioiTinh());
    // pstmt.setString(4, nhanVien.getSdt());
    // pstmt.setString(5, nhanVien.getCccd());
    // pstmt.setString(6, nhanVien.getDiaChi());
    // pstmt.setDate(7, java.sql.Date.valueOf(nhanVien.getNgaySinh()));
    // pstmt.setBoolean(8, nhanVien.isTrangThai());
    // pstmt.setDate(9, java.sql.Date.valueOf(nhanVien.getNgayVaoLam()));
    // pstmt.setString(10, nhanVien.getHinhAnhNV());
    //
    // return pstmt.executeUpdate() > 0;
    // } catch (SQLException e) {
    // e.printStackTrace();
    // return false;
    // }
    // }
    public boolean saveEmployee(NhanVien_entity employee) {
        String sql = "INSERT INTO NhanVien (maNV, hoTenNV, gioiTinh, sdt, cccd, diaChi, ngaySinh, trangThai, ngayVaoLam, hinhAnhNV, maLoaiNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectDB.accessDataBase();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getMaNV());
            pstmt.setString(2, employee.getHoTenNV());
            pstmt.setString(3, employee.getGioiTinh());
            pstmt.setString(4, employee.getSdt());
            pstmt.setString(5, employee.getCccd());
            pstmt.setString(6, employee.getDiaChi());
            pstmt.setDate(7, java.sql.Date.valueOf(employee.getNgaySinh()));
            pstmt.setBoolean(8, employee.isTrangThai());
            pstmt.setDate(9, java.sql.Date.valueOf(employee.getNgayVaoLam()));
            pstmt.setString(10, employee.getHinhAnhNV());
            pstmt.setString(11, employee.getMaLoaiNV());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0; // Return true if at least one row was inserted
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an exception occurs
        }
    }
}
