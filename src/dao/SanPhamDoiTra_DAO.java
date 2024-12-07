package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.SanPhamDoiTra;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connectDB.connectDB;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamDoiTra_DAO {
    private Connection connection;
    private static final Logger logger = Logger.getLogger(SanPhamDoiTra_DAO.class.getName());

    // Constructor to set up database connection
    public SanPhamDoiTra_DAO(Connection connection) {
        this.connection = connection;
    }
    
    public SanPhamDoiTra_DAO() {
    	this.connection =  connectDB.accessDataBase();
    }

    // Method to add a return product to the database
    public boolean addSanPhamDoiTra(SanPhamDoiTra sanPhamDoiTra) {
        String query = "INSERT INTO SanPhamDoiTra (maSP, maHD, soLuong, vanDe, ngayDoiTra, trangThai) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, sanPhamDoiTra.getMaSP());
            ps.setString(2, sanPhamDoiTra.getMaHD());
            ps.setInt(3, sanPhamDoiTra.getSoLuong());
            ps.setString(4, sanPhamDoiTra.getVanDe());
            ps.setDate(5, java.sql.Date.valueOf(sanPhamDoiTra.getNgayDoiTra()));
            ps.setString(6, sanPhamDoiTra.isTrangThai() ? "Xác nhận" : "Đang chờ duyệt");

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding return product", e);
        }
        return false;
    }

    // Method to get all return products
    public List<SanPhamDoiTra> getAllSanPhamDoiTra() {
        List<SanPhamDoiTra> list = new ArrayList<>();
        String query = "SELECT * FROM SanPhamDoiTra";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                SanPhamDoiTra spdt = new SanPhamDoiTra();
                spdt.setMaSP(rs.getString("maSP"));
                spdt.setMaHD(rs.getString("maHD"));
                spdt.setSoLuong(rs.getInt("soLuong"));
                spdt.setVanDe(rs.getString("vanDe"));
                spdt.setNgayDoiTra(rs.getDate("ngayDoiTra").toLocalDate());
                spdt.setTrangThai(rs.getString("trangThai").equals("Xác nhận"));
                list.add(spdt);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching return products", e);
        }
        return list;
    }

    // Method to get a specific return product by ID
    public SanPhamDoiTra getSanPhamDoiTraByID(String maSP, String maHD) {
        String query = "SELECT * FROM SanPhamDoiTra WHERE maSP = ? AND maHD = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, maSP);
            ps.setString(2, maHD);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SanPhamDoiTra spdt = new SanPhamDoiTra();
                    spdt.setMaSP(rs.getString("maSP"));
                    spdt.setMaHD(rs.getString("maHD"));
                    spdt.setSoLuong(rs.getInt("soLuong"));
                    spdt.setVanDe(rs.getString("vanDe"));
                    spdt.setNgayDoiTra(rs.getDate("ngayDoiTra").toLocalDate());
                    spdt.setTrangThai(rs.getString("trangThai").equals("Xác nhận"));
                    return spdt;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching return product by ID", e);
        }
        return null;
    }

    // Method to update the status of a return product
    public boolean updateTrangThai(String maSP, String maHD, String trangThaiMoi) {
        String query = "UPDATE SanPhamDoiTra SET trangThai = ? WHERE maSP = ? AND maHD = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, trangThaiMoi);
            ps.setString(2, maSP);
            ps.setString(3, maHD);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating status for product return", e);
        }
        return false;
    }

    // Method to delete a return product
    public boolean deleteSanPhamDoiTra(String maSP, String maHD) {
        String query = "DELETE FROM SanPhamDoiTra WHERE maSP = ? AND maHD = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, maSP);
            ps.setString(2, maHD);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting return product", e);
        }
        return false;
    }

    // Method to start a transaction for multiple updates
    public boolean processSanPhamDoiTraTransaction(List<SanPhamDoiTra> sanPhamDoiTraList) {
        String query = "INSERT INTO SanPhamDoiTra (maSP, maHD, soLuong, vanDe, ngayDoiTra, trangThai) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false); // Start transaction
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                for (SanPhamDoiTra spdt : sanPhamDoiTraList) {
                    ps.setString(1, spdt.getMaSP());
                    ps.setString(2, spdt.getMaHD());
                    ps.setInt(3, spdt.getSoLuong());
                    ps.setString(4, spdt.getVanDe());
                    ps.setDate(5, java.sql.Date.valueOf(spdt.getNgayDoiTra()));
                    ps.setString(6, spdt.isTrangThai() ? "Xác nhận" : "Đang chờ duyệt");
                    ps.addBatch();
                }
                int[] results = ps.executeBatch();
                connection.commit(); // Commit transaction

                return results.length == sanPhamDoiTraList.size();
            } catch (SQLException e) {
                connection.rollback(); // Rollback if error occurs
                logger.log(Level.SEVERE, "Error during transaction", e);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error starting transaction", e);
        } finally {
            try {
                connection.setAutoCommit(true); // Reset autocommit to true
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error resetting auto commit", e);
            }
        }
        return false;
    }

    // Method to load SanPhamDoiTra data to JTable
    public void loadSanPhamDoiTraToTable(JTable table) {
        // Get all return products
        List<SanPhamDoiTra> sanPhamDoiTraList = getAllSanPhamDoiTra();
        
        // Create DefaultTableModel to set data into the table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        // Clear previous data in the table
        model.setRowCount(0);
        
        // Add data from the list into the table
        for (SanPhamDoiTra spdt : sanPhamDoiTraList) {
            Object[] rowData = new Object[] {
                spdt.getMaHD(), // Mã hóa đơn
                spdt.getMaSP(), // Mã sản phẩm
                spdt.getSoLuong(), // Số lượng
                spdt.getVanDe(), // Vấn đề
                spdt.getNgayDoiTra(), // Ngày đổi trả
                spdt.isTrangThai() ? "Xác nhận" : "Đang chờ duyệt", // Trạng thái
                "Thao tác" // Thao tác (you can add buttons or actions here)
            };
            model.addRow(rowData);
        }
    }

    public List<SanPhamDoiTra> searchSanPhamByMaSP(String maSP) {
        List<SanPhamDoiTra> result = new ArrayList<>();
        // Perform query with the product code (maSP)
        String sql = "SELECT * FROM SanPhamDoiTra WHERE maSP LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + maSP + "%");  // Search with pattern (the % allows substring matching)
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SanPhamDoiTra sp = new SanPhamDoiTra(
                        rs.getString("maHD"),
                      //  rs.getString("hinhAnh"),
                        rs.getString("maSP"),
                        rs.getInt("soLuong"),
                        rs.getString("vanDe"),
                        rs.getDate("ngayDoiTra").toLocalDate(),
                        rs.getString("trangThai").equals("Xác nhận")
                    );
                    result.add(sp);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error searching return product by product code", ex);
        }
        return result;
    }


}
