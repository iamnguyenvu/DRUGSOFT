package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.ChiTietHoaDonDoiTra_entity;
import entity.LoaiSanPham_entity;
import entity.SanPhamDoiTra_entity;
import entity.SanPham_entity;

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


    public SanPhamDoiTra_entity getSanPhamDoiTraByID(String maDT) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SanPhamDoiTra_entity sp = null;
        try {
            ps = con.prepareStatement("select maDT,spdt.maSP,tenSP,spdt.soLuong,spdt.thanhTien,trangThai from SanPhamDoiTra spdt join SanPham sp on spdt.maSP = sp.maSP WHERE maDT = ?");
            ps.setString(1, maDT);
            rs = ps.executeQuery();
            if (rs.next()) {

            	String maSP = rs.getString("maSP");
            	int soLuong = rs.getInt("soLuong");
            	double thanhTien = rs.getDouble("thanhTien");
            	String trangThai = rs.getString("trangThai");
            	String tenSP = rs.getString("tenSP");
	            sp = new SanPhamDoiTra_entity(maDT, maSP, soLuong, thanhTien, trangThai, tenSP);
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
    // Method to add a return product to the database
//    public boolean addSanPhamDoiTra(ChiTietHoaDonDoiTra_entity sanPhamDoiTra) {
//        String query = "INSERT INTO SanPhamDoiTra ( MaDT, maSP, soLuong, chietKhau, thanhTien, loaiDoiTra, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement ps = connection.prepareStatement(query)) {
//        	 ps.setString(1, sanPhamDoiTra.getMaDT());
//            ps.setString(2, sanPhamDoiTra.getMaSP());
//            ps.setInt(3, sanPhamDoiTra.getSoLuong());
//            ps.setDouble(4, sanPhamDoiTra.getChietKhau());
//            ps.setDouble(5, sanPhamDoiTra.getThanhTien());
//            ps.setString(6, sanPhamDoiTra.getLoaiDoiTra());
//            ps.setString(7, sanPhamDoiTra.getTrangThai());
////
//            return ps.executeUpdate() > 0;
//        } catch (SQLException e) {
//            logger.log(Level.SEVERE, "Error adding return product", e);
//        }
//        return false;
//    }

    
    
	public ArrayList<SanPhamDoiTra_entity> getAllSanPhamDoiTra() {
	    ArrayList<SanPhamDoiTra_entity> dssp = new ArrayList<SanPhamDoiTra_entity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "select maDT,spdt.maSP,tenSP,spdt.soLuong,spdt.thanhTien,trangThai from SanPhamDoiTra spdt join SanPham sp on spdt.maSP = sp.maSP";
	    try {
	        java.sql.Statement st = con.createStatement();  
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
            	SanPhamDoiTra_entity spdt = new SanPhamDoiTra_entity();

                spdt.setMaDT(rs.getString("MaDT"));
                spdt.setMaSP(rs.getString("maSP"));
                spdt.setSoLuong(rs.getInt("soLuong"));
                spdt.setThanhTien(rs.getDouble("thanhTien"));
                spdt.setTrangThai(rs.getString("trangThai"));
                spdt.setTenSP(rs.getString("tenSP"));

                dssp.add(spdt);
            }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dssp;
	}

    // Method to get a specific return product by ID
    public ChiTietHoaDonDoiTra_entity getSanPhamDoiTraByID(String maSP, String MaDT) {
        String query = "SELECT * FROM SanPhamDoiTra WHERE maSP = ? AND MaDT = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, MaDT);
            ps.setString(2, maSP);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ChiTietHoaDonDoiTra_entity spdt = new ChiTietHoaDonDoiTra_entity();
                    spdt.setMaDT(rs.getString("MaDT"));
                    spdt.setMaSP(rs.getString("MaSP"));
                    spdt.setSoLuong(rs.getInt("soLuong"));
                    spdt.setChietKhau(rs.getDouble("chietKhau"));
                    spdt.setThanhTien(rs.getDouble("thanhTien"));
                    spdt.setLoaiDoiTra(rs.getString("loaiDoiTra"));
                    return spdt;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching return product by ID", e);
        }
        return null;
    }
    public String getTenSanPhamByMaSP(String maSP) {
        String tenSP = null;
        String query = "SELECT tenSP FROM SanPham WHERE maSP = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, maSP);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tenSP = rs.getString("tenSP");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching product name for maSP: " + maSP, e);
        }
        return tenSP;
    }


    // Method to update the status of a return product
    public boolean updateTrangThai(String maSP, String MaDT, String trangThaiMoi) {
        String query = "UPDATE SanPhamDoiTra SET loaiDoiTra = ? WHERE maSP = ? AND MaDT = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, trangThaiMoi);
            ps.setString(2, MaDT);
            ps.setString(3, maSP);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating status for product return", e);
        }
        return false;
    }

    // Method to delete a return product
    public boolean deleteSanPhamDoiTra(String maSP, String MaDT) {
        String query = "DELETE FROM SanPhamDoiTra WHERE maSP = ? AND MaDT = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
           ps.setString(1, MaDT);
            ps.setString(2, maSP);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting return product", e);
        }
        return false;
    }


    public boolean processSanPhamDoiTraTransaction(List<ChiTietHoaDonDoiTra_entity> sanPhamDoiTraList) {
        String query = "INSERT INTO SanPhamDoiTra (maSP, MaDT, soLuong, chietKhau, thanhTien, loaiDoiTra) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false); // Start transaction
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                for (ChiTietHoaDonDoiTra_entity spdt : sanPhamDoiTraList) {

                	ps.setString(1, spdt.getMaDT());
                    ps.setString(2, spdt.getMaSP());
                    ps.setDouble(3, spdt.getChietKhau());
                    ps.setDouble(4, spdt.getThanhTien());
                    ps.setString(6, spdt.getLoaiDoiTra());
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
        // Lấy danh sách sản phẩm đổi trả từ DAO
        List<SanPhamDoiTra_entity> list = getAllSanPhamDoiTra();

        // Lấy mô hình bảng từ JTable
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa tất cả các dòng hiện có

        // Duyệt qua danh sách và thêm từng dòng vào bảng
        for (SanPhamDoiTra_entity spdt : list) {
            Object[] rowData = {
            	spdt.getMaDT(),
                spdt.getMaSP(),
                spdt.getTenSP(),
                spdt.getSoLuong(),
                spdt.getTrangThai(),
                spdt.getThanhTien(),
            };
            model.addRow(rowData);
        }
    }

    public ArrayList<SanPhamDoiTra_entity> timKiemSanPham(String searchKey) {
        ArrayList<SanPhamDoiTra_entity> dssp = new ArrayList<SanPhamDoiTra_entity>();
        Connection con = connectDB.accessDataBase();
        if (con == null) {
            return null;
        }
        String sql = "select maDT, spdt.maSP, tenSP, spdt.soLuong, spdt.thanhTien, trangThai from SanPhamDoiTra spdt join SanPham sp on spdt.maSP = sp.maSP WHERE maDT LIKE ? OR tenSP LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchKey + "%");
            ps.setString(2, "%" + searchKey + "%");
            
            // Corrected line:
            ResultSet rs = ps.executeQuery(); // Remove the sql parameter here
            
            while (rs.next()) {
                SanPhamDoiTra_entity spdt = new SanPhamDoiTra_entity();
                spdt.setMaDT(rs.getString("MaDT"));
                spdt.setMaSP(rs.getString("maSP"));
                spdt.setSoLuong(rs.getInt("soLuong"));
                spdt.setThanhTien(rs.getDouble("thanhTien"));
                spdt.setTrangThai(rs.getString("trangThai"));
                spdt.setTenSP(rs.getString("tenSP"));

                dssp.add(spdt);
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

    public List<ChiTietHoaDonDoiTra_entity> searchSanPhamByMaSP(String maSP) {
        List<ChiTietHoaDonDoiTra_entity> result = new ArrayList<>();
        // Perform query with the product code (maSP)
        String sql = "SELECT * FROM SanPhamDoiTra WHERE maSP LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + maSP + "%");  // Search with pattern (the % allows substring matching)
            try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ChiTietHoaDonDoiTra_entity sp = new ChiTietHoaDonDoiTra_entity();
					sp.setMaDT(rs.getString("MaDT"));
					sp.setMaSP(rs.getString("maSP"));
					
					sp.setSoLuong(rs.getInt("soLuong"));
					// sp.setVanDe(rs.getString("vanDe"));
					sp.setThanhTien(rs.getDouble("thanhTien"));
                	

//                    );
                    result.add(sp);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error searching return product by product code", ex);
        }
        return result;
    }
	public boolean capNhatSanPham(String trangThai,String maSP ,String maDT) {
	    Connection con = connectDB.accessDataBase();

	    String sql = "UPDATE SanPhamDoiTra  SET trangThai = ? WHERE maSP = ? AND maDT = ?";
	    
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setString(1, trangThai);
	        stmt.setString(2, maSP);
	        stmt.setString(3, maDT);


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


}
