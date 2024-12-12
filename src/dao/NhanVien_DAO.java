package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.connectDB;
import entity.NhanVien_entity;

public class NhanVien_DAO {

    private Connection stmt;
	private Object conn;
	private String maLoaiNV;
	private String hoTenNV;

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
                "email = ?, " +
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
            pstmt.setString(4, nhanVien.getEmail());
            pstmt.setString(5, nhanVien.getCccd());
            pstmt.setString(6, nhanVien.getDiaChi());
            pstmt.setDate(7, Date.valueOf(nhanVien.getNgaySinh()));
            pstmt.setBoolean(8, nhanVien.isTrangThai());
            pstmt.setDate(9, Date.valueOf(nhanVien.getNgayVaoLam()));
            pstmt.setString(10, nhanVien.getHinhAnhNV());
            pstmt.setString(11, nhanVien.getMaLoaiNV());
            pstmt.setString(12, nhanVien.getMaNV());

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
        String query = "SELECT * FROM NhanVien WHERE 1=1"; // Phần này luôn đúng
        
        // Thêm điều kiện lọc loại nhân viên
        if (!loaiNV.equals("Tất cả")) {
            query += " AND maLoaiNV = '" + loaiNV + "'";
        }

        // Thêm điều kiện sắp xếp
        if (sortAsc) {
            query += " ORDER BY hoTenNV ASC"; // Sắp xếp tên theo A-Z
        } else {
            query += " ORDER BY hoTenNV DESC"; // Sắp xếp tên theo Z-A
        }

        // Kết nối và thực hiện truy vấn SQL (Ví dụ sử dụng JDBC)
        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=DRUGSOFT;", 
                "sa", 
                "159357");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                NhanVien_entity nv = new NhanVien_entity();
                nv.setMaNV(rs.getString("maNV"));
                nv.setHoTenNV(rs.getString("hoTenNV"));
                nv.setSdt(rs.getString("sdt"));
                nv.setEmail(rs.getString("email"));
                nv.setCccd(rs.getString("cccd"));
                nv.setDiaChi(rs.getString("diaChi"));
                nv.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
                nv.setTrangThai(rs.getBoolean("trangThai"));
                nv.setMaLoaiNV(rs.getString("maLoaiNV"));

                nhanVienList.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("SQL State: " + e.getSQLState());
        }

        return nhanVienList;
    }

    // Ánh xạ ResultSet tới NhanVien_entity
    private NhanVien_entity mapResultSetToNhanVien(ResultSet rs) throws SQLException {
        NhanVien_entity nhanVien = new NhanVien_entity();
       // nhanVien.setMaNV(rs.getString("maNV"));
        nhanVien.setMaNV(rs.getString("maNV"));
        nhanVien.setHoTenNV(rs.getString("hoTenNV"));
        nhanVien.setNgaySinh(rs.getDate("ngaySinh") != null ? rs.getDate("ngaySinh").toLocalDate() : null);
        nhanVien.setDiaChi(rs.getString("diaChi"));
        nhanVien.setSdt(rs.getString("sdt"));
        nhanVien.setEmail(rs.getString("email"));
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
        ps.setString(5, nhanVien.getEmail()); // 5. cccd
        ps.setString(6, nhanVien.getCccd()); // 5. cccd
        ps.setDate(7, Date.valueOf(nhanVien.getNgayVaoLam())); // 6. ngayVaoLam
        ps.setBoolean(8, nhanVien.isTrangThai()); // 7. trangThai
        ps.setString(9, nhanVien.getGioiTinh()); // 8. gioiTinh
        ps.setString(10, nhanVien.getHinhAnhNV()); // 9. hinhAnhNV
        ps.setString(11, nhanVien.getMaLoaiNV()); // 10. maLoaiNV
        ps.setString(12, nhanVien.getMaNV()); // 11. maNV (trong WHERE)
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
    
 // Lấy danh sách nhân viên theo loại
    public List<NhanVien_entity> getNhanVienTheoLoai(String loai, String sapXep) {
        List<NhanVien_entity> danhSachNhanVien = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE maLoaiNV = ? ";

        // Kiểm tra giá trị 'loai' và chuẩn bị tham số
        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.println("Loại nhân viên: " + loai); // Kiểm tra giá trị 'loai'

            // Kiểm tra giá trị loai
            if ("Quản lý".equals(loai)) {
                ps.setString(1, "QL");
            } else if ("Nhân viên".equals(loai)) {
                ps.setString(1, "NV");
            } else {
                throw new IllegalArgumentException("Loại nhân viên không hợp lệ");
            }

          
            // Cập nhật PreparedStatement với câu lệnh SQL đã được thay đổi
            ps.close();  // Đóng PreparedStatement hiện tại trước khi tạo lại
            try (PreparedStatement newPs = con.prepareStatement(sql)) {
                if ("Quản lý".equals(loai)) {
                    newPs.setString(1, "QL");
                } else if ("Nhân viên".equals(loai)) {
                    newPs.setString(1, "NV");
                }

                // Thực thi truy vấn
                try (ResultSet rs = newPs.executeQuery()) {
                    while (rs.next()) {
                        danhSachNhanVien.add(mapResultSetToNhanVien(rs));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return danhSachNhanVien;
    }
   
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
    
    /*
    public boolean saveEmployee(NhanVien_entity employee) {
        String sql = "INSERT INTO NhanVien (maNV, hoTenNV, gioiTinh, sdt, email, cccd, diaChi, ngaySinh, trangThai, ngayVaoLam, hinhAnhNV, maLoaiNV) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?)";

        try (Connection conn = connectDB.accessDataBase();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getMaNV());
            pstmt.setString(2, employee.getHoTenNV());
            pstmt.setString(3, employee.getGioiTinh());
            pstmt.setString(4, employee.getSdt());
            pstmt.setString(5, employee.getEmail());
            pstmt.setString(6, employee.getCccd());
            pstmt.setString(7, employee.getDiaChi());
            pstmt.setDate(8, java.sql.Date.valueOf(employee.getNgaySinh()));
            pstmt.setBoolean(9, employee.isTrangThai());
            pstmt.setDate(10, java.sql.Date.valueOf(employee.getNgayVaoLam()));
            pstmt.setString(11, employee.getHinhAnhNV());
            pstmt.setString(12, employee.getMaLoaiNV());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0; // Return true if at least one row was inserted
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an exception occurs
        }
    }
    */
    public boolean saveEmployee(NhanVien_entity employee) {
        String sql = "INSERT INTO NhanVien (maNV, hoTenNV, gioiTinh, ngaySinh, ngayVaoLam, diaChi, sdt, email, cccd, trangThai, maLoaiNV, hinhAnhNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectDB.accessDataBase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getMaNV());
            stmt.setString(2, employee.getHoTenNV());
            stmt.setString(3, employee.getGioiTinh());
            stmt.setDate(4, Date.valueOf(employee.getNgaySinh()));
            stmt.setDate(5, Date.valueOf(employee.getNgayVaoLam()));
            stmt.setString(6, employee.getDiaChi());
            stmt.setString(7, employee.getSdt());
           
            stmt.setString(8, employee.getEmail());
            stmt.setString(9, employee.getCccd());
            stmt.setBoolean(10, employee.isTrangThai());
            stmt.setString(11, employee.getMaLoaiNV());
            stmt.setString(12, employee.getHinhAnhNV());

            int result = stmt.executeUpdate();
            return result > 0; // Trả về true nếu thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


//    truyễn manv, lấy ho ten, sdt
	public NhanVien_entity getNhanVien(String maNV) {
		NhanVien_entity nhanVien = null;
		String sql = "SELECT hoTenNV, sdt FROM NhanVien WHERE maNV = ?";

		try (Connection con = connectDB.accessDataBase(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, maNV);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					nhanVien = new NhanVien_entity();
					nhanVien.setHoTenNV(rs.getString("hoTenNV"));
					nhanVien.setSdt(rs.getString("sdt"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nhanVien;
	}

	public NhanVien_entity getNhanVienByMa(String maNV) {
        NhanVien_entity nhanVien = null;
        String sql = "SELECT * FROM NhanVien WHERE maNV = ?";

        try (Connection con = connectDB.accessDataBase();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maNV);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nhanVien = new NhanVien_entity(rs.getString("maNV"), rs.getString("hoTenNV"), rs.getString("gioiTinh"), rs.getString("sdt"), rs.getString("cccd"), rs.getString("diaChi"), rs.getDate("ngaySinh").toLocalDate(), rs.getBoolean("trangThai"), rs.getDate("ngayVaoLam").toLocalDate(), null, null, rs.getString("hinhAnhNV"), rs.getString("maLoaiNV"), rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanVien;
    }

	public static boolean updateEmployee(NhanVien_entity employee) {
		// TODO Auto-generated method stub
		return false;
	}

//	public List<NhanVien_entity> locNhanVienTheoTen(boolean sortAsc) {
//		
//		        List<NhanVien_entity> danhSachNhanVien = new ArrayList<>();
//		        
//		        // Câu lệnh SQL cơ bản với điều kiện lọc theo loại và tên nhân viên
//		        String sql = "SELECT * FROM NhanVien WHERE maLoaiNV = ? ";
//		        
//		        // Nếu có yêu cầu lọc thêm theo tên nhân viên
//		        if (hoTenNV != null && !hoTenNV.isEmpty()) {
//		            sql += "AND hoTenNV LIKE ? ";
//		        }
//
//		        // Thêm điều kiện sắp xếp nếu có
//		        if ("A-Z".equals(sortAsc)) {
//		            sql += "ORDER BY hoTenNV ASC";
//		        } else if ("Z-A".equals(sortAsc)) {
//		            sql += "ORDER BY hoTenNV DESC";
//		        }
//
//		        // Kiểm tra giá trị 'loai' và chuẩn bị tham số
//		        try (Connection con = connectDB.accessDataBase();
//		             PreparedStatement ps = con.prepareStatement(sql)) {
//
////		            // Kiểm tra giá trị loai
////		            if ("Quản lý".equals(maLoaiNV)) {
////		                ps.setString(1, "QL");
////		            } else if ("Nhân viên".equals(maLoaiNV)) {
////		                ps.setString(1, "NV");
////		            } else {
////		                throw new IllegalArgumentException("Loại nhân viên không hợp lệ");
////		            }
////
////		            // Nếu có yêu cầu lọc theo tên nhân viên
////		            if (hoTenNV != null && !hoTenNV.isEmpty()) {
////		                ps.setString(2, "%" + hoTenNV + "%");
////		            }
////
////		            // Thực thi truy vấn
////		            try (ResultSet rs = ps.executeQuery()) {
////		                while (rs.next()) {
////		                    danhSachNhanVien.add(mapResultSetToNhanVien(rs));
////		                }
////		            }
//
//		        } catch (SQLException e) {
//		            e.printStackTrace();
//		        } catch (IllegalArgumentException e) {
//		            System.out.println(e.getMessage());
//		        }
//		        
//		        return danhSachNhanVien;
//		    }
//
//	
//
//            
                }
