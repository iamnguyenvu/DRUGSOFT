package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import connectDB.connectDB;
import entity.KhachHang_entity;
import javax.swing.JOptionPane;

public class KhachHang_DAO {

    // Lấy tất cả khách hàng từ cơ sở dữ liệu
    public List<KhachHang_entity> getTatCaKhachHang() {
        List<KhachHang_entity> danhSachKhachHang = new ArrayList<>();
        String query = "SELECT tenKH, sdtKH, diemThuong, gioiTinh FROM KhachHang";

        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tenKH = rs.getString("tenKH");
                String sdtKH = rs.getString("sdtKH");
                int diemThuong = rs.getInt("diemThuong");
                String gioiTinh = rs.getString("gioiTinh");

                KhachHang_entity kh = new KhachHang_entity(tenKH, sdtKH, diemThuong, gioiTinh);
                danhSachKhachHang.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu khách hàng từ cơ sở dữ liệu!");
        }

        return danhSachKhachHang;
    }



    // Lấy thông tin khách hàng theo mã khách hàng
    public KhachHang_entity timKhachHangTheoSDT(String soDT) {
        String query = "SELECT tenKH, sdtKH, diemThuong, gioiTinh FROM KhachHang WHERE sdtKH = ?";

        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, soDT);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new KhachHang_entity(
                        rs.getString("tenKH"),
                        rs.getString("sdtKH"),
                        rs.getInt("diemThuong"),
                        rs.getString("gioiTinh")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }
    
    public List<KhachHang_entity> timKhachHangTheoTen(String tenKH) {
        List<KhachHang_entity> danhSachKhachHang = new ArrayList<>();
        String query = "SELECT tenKH, sdtKH, diemThuong, gioiTinh FROM KhachHang WHERE tenKH LIKE ?";

        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, "%" + tenKH + "%"); // Sử dụng LIKE với wildcard để tìm kiếm theo tên

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    danhSachKhachHang.add(new KhachHang_entity(
                        rs.getString("tenKH"),
                        rs.getString("sdtKH"),
                        rs.getInt("diemThuong"),
                        rs.getString("gioiTinh")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachKhachHang;
    }

    // Lấy điểm thưởng theo mã khách hàng
    public int getDiemThuong(String maKH) {
        String query = "SELECT diemThuong FROM KhachHang WHERE maKH = ?";
        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, maKH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("diemThuong");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Lấy giới tính theo mã khách hàng
    public String getGioiTinh(String maKH) {
        String query = "SELECT gioiTinh FROM KhachHang WHERE maKH = ?";
        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, maKH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return convertGioiTinh(rs.getInt("gioiTinh"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Khác";
    }

    // Lấy số điện thoại theo mã khách hàng
    public String getSDT(String maKH) {
        String query = "SELECT SDT FROM KhachHang WHERE maKH = ?";
        try (Connection con = connectDB.accessDataBase();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, maKH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("SDT");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    // Chuyển đổi giới tính từ giá trị số thành chuỗi
    private String convertGioiTinh(int gioiTinhValue) {
        return switch (gioiTinhValue) {
            case 0 -> "Nữ";
            case 1 -> "Nam";
            default -> throw new IllegalArgumentException("Giới tính không hợp lệ trong cơ sở dữ liệu.");
        };
    }
    
    public boolean capNhatKhachHang(KhachHang_entity kh, String soDTGoc) throws SQLException {
        String query = "UPDATE KhachHang SET tenKH = ?, gioiTinh = ?, sdtKH = ? WHERE sdtKH = ?";
        try (Connection conn = connectDB.accessDataBase();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Giới hạn độ dài các trường trước khi gán vào PreparedStatement
            stmt.setString(1, kh.getTenKH().trim().substring(0, Math.min(kh.getTenKH().length(), 50))); // tối đa 50 ký tự
            stmt.setString(2, kh.getGioiTinh().trim());  // giới tính đã được giới hạn là "Nam" hoặc "Nữ"
            stmt.setString(3, kh.getsdtKH().trim().substring(0, Math.min(kh.getsdtKH().length(), 10))); // tối đa 10 ký tự
            stmt.setString(4, soDTGoc);  // Điều kiện WHERE

            return stmt.executeUpdate() > 0;
        }
    }


    
    public boolean xoaKhachHang(String soDT) throws SQLException {
        String query = "DELETE FROM KhachHang WHERE sdtKH = ?";
        try (Connection conn = connectDB.accessDataBase();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, soDT);  // Xóa khách hàng dựa trên số điện thoại
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean themKhachHang(KhachHang_entity kh) {
        String query = "INSERT INTO KhachHang (tenKH, sdtKH, diemThuong, gioiTinh) VALUES (?, ?, ?, ?)";

        try (Connection conn = connectDB.accessDataBase();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Đặt các giá trị cho các tham số truy vấn
            stmt.setString(1, kh.getTenKH().trim().substring(0, Math.min(kh.getTenKH().length(), 50))); // Tên KH, tối đa 50 ký tự
            stmt.setString(2, kh.getsdtKH().trim().substring(0, Math.min(kh.getsdtKH().length(), 10))); // Số điện thoại, tối đa 10 ký tự
            stmt.setInt(3, 0);  // Điểm thưởng mặc định là 0 cho khách hàng mới
            stmt.setString(4, kh.getGioiTinh().trim()); // Giới tính, chỉ "Nam" hoặc "Nữ"

            // Thực hiện truy vấn
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm khách hàng vào cơ sở dữ liệu!");
            return false;
        }
    }

}
