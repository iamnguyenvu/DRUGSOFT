/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectDB;
import entity.BaoCaoSanPhamBanChay_enity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Vovan
 */
public class BaoCaoSanPhamBanCham_DAO {
    public ArrayList<BaoCaoSanPhamBanChay_enity> getAllBaoCaoSanPhamBanCham( int select) {
    ArrayList<BaoCaoSanPhamBanChay_enity> list = new ArrayList<>();
    Connection connection = null;
    java.sql.Statement st = null;
    ResultSet rs = null;
    
    if (select == 0) {
        try {
            // Kết nối cơ sở dữ liệu
            connection = connectDB.accessDataBase();
            
            // Câu truy vấn SQL
            String sql = """
                    SELECT 
                        sp.maSP AS maSanPham,
                        sp.tenSP AS tenSanPham,
                        lsp.tenLoaiSP AS loaiSanPham,
                        sp.gia AS gia,
                        SUM(cthd.soLuongSanPham) AS tongSoLuongBan
                    FROM 
                        SanPham sp
                    JOIN 
                        LoaiSanPham lsp ON sp.maLoaiSP = lsp.maLoaiSP
                    JOIN 
                        ChiTietHoaDon cthd ON sp.maSP = cthd.maSP
                    GROUP BY 
                        sp.maSP, sp.tenSP, lsp.tenLoaiSP, sp.gia
                    ORDER BY 
                        tongSoLuongBan ;
            """;

            // Tạo statement và thực thi truy vấn
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            // Xử lý kết quả
            while (rs.next()) {
                String maSP = rs.getString("maSanPham");
                String tenSP = rs.getString("tenSanPham");
                String loaiSP = rs.getString("loaiSanPham");
                double gia = rs.getDouble("gia");
                int tongSoLuongBan = rs.getInt("tongSoLuongBan");
                
                // Tạo đối tượng BaoCaoSanPhamBanChay_enity và thêm vào danh sách
                BaoCaoSanPhamBanChay_enity bc = new BaoCaoSanPhamBanChay_enity(maSP, tenSP, loaiSP, gia, tongSoLuongBan);
                list.add(bc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    else if (select == 7) {
        try {
            // Kết nối cơ sở dữ liệu
            connection = connectDB.accessDataBase();
            
            // Câu truy vấn SQL
            String sql = """
                    SELECT 
                        sp.maSP AS maSanPham,
                        sp.tenSP AS tenSanPham,
                        lsp.tenLoaiSP AS loaiSanPham,
                        sp.gia AS gia,
                        SUM(cthd.soLuongSanPham) AS tongSoLuongBan
                    FROM 
                        SanPham sp
                    JOIN 
                        LoaiSanPham lsp ON sp.maLoaiSP = lsp.maLoaiSP
                    JOIN 
                        ChiTietHoaDon cthd ON sp.maSP = cthd.maSP
                    Join 
                    	HoaDon hd ON hd.maHD = cthd.maHD
                    WHERE ngayLapHD >= DATEADD(DAY, -7, GETDATE())
                    GROUP BY 
                        sp.maSP, sp.tenSP, lsp.tenLoaiSP, sp.gia
                    ORDER BY 
                        tongSoLuongBan ;
            """;

            // Tạo statement và thực thi truy vấn
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            // Xử lý kết quả
            while (rs.next()) {
                String maSP = rs.getString("maSanPham");
                String tenSP = rs.getString("tenSanPham");
                String loaiSP = rs.getString("loaiSanPham");
                double gia = rs.getDouble("gia");
                int tongSoLuongBan = rs.getInt("tongSoLuongBan");
                
                // Tạo đối tượng BaoCaoSanPhamBanChay_enity và thêm vào danh sách
                BaoCaoSanPhamBanChay_enity bc = new BaoCaoSanPhamBanChay_enity(maSP, tenSP, loaiSP, gia, tongSoLuongBan);
                list.add(bc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
    else if (select == 30) {
        try {
            // Kết nối cơ sở dữ liệu
            connection = connectDB.accessDataBase();
            
            // Câu truy vấn SQL
            String sql = """
                    SELECT 
                        sp.maSP AS maSanPham,
                        sp.tenSP AS tenSanPham,
                        lsp.tenLoaiSP AS loaiSanPham,
                        sp.gia AS gia,
                        SUM(cthd.soLuongSanPham) AS tongSoLuongBan
                    FROM 
                        SanPham sp
                    JOIN 
                        LoaiSanPham lsp ON sp.maLoaiSP = lsp.maLoaiSP
                    JOIN 
                        ChiTietHoaDon cthd ON sp.maSP = cthd.maSP
                    Join 
                    	HoaDon hd ON hd.maHD = cthd.maHD
                    WHERE ngayLapHD >= DATEADD(DAY, -30, GETDATE())
                    GROUP BY 
                        sp.maSP, sp.tenSP, lsp.tenLoaiSP, sp.gia
                    ORDER BY 
                        tongSoLuongBan ;
            """;

            // Tạo statement và thực thi truy vấn
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            // Xử lý kết quả
            while (rs.next()) {
                String maSP = rs.getString("maSanPham");
                String tenSP = rs.getString("tenSanPham");
                String loaiSP = rs.getString("loaiSanPham");
                double gia = rs.getDouble("gia");
                int tongSoLuongBan = rs.getInt("tongSoLuongBan");
                
                // Tạo đối tượng BaoCaoSanPhamBanChay_enity và thêm vào danh sách
                BaoCaoSanPhamBanChay_enity bc = new BaoCaoSanPhamBanChay_enity(maSP, tenSP, loaiSP, gia, tongSoLuongBan);
                list.add(bc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
    else if (select == 90) {
        try {
            // Kết nối cơ sở dữ liệu
            connection = connectDB.accessDataBase();
            
            // Câu truy vấn SQL
            String sql = """
                    SELECT 
                        sp.maSP AS maSanPham,
                        sp.tenSP AS tenSanPham,
                        lsp.tenLoaiSP AS loaiSanPham,
                        sp.gia AS gia,
                        SUM(cthd.soLuongSanPham) AS tongSoLuongBan
                    FROM 
                        SanPham sp
                    JOIN 
                        LoaiSanPham lsp ON sp.maLoaiSP = lsp.maLoaiSP
                    JOIN 
                        ChiTietHoaDon cthd ON sp.maSP = cthd.maSP
                    Join 
                    	HoaDon hd ON hd.maHD = cthd.maHD
                    WHERE ngayLapHD >= DATEADD(DAY, -90, GETDATE())
                    GROUP BY 
                        sp.maSP, sp.tenSP, lsp.tenLoaiSP, sp.gia
                    ORDER BY 
                        tongSoLuongBan ;
            """;

            // Tạo statement và thực thi truy vấn
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            // Xử lý kết quả
            while (rs.next()) {
                String maSP = rs.getString("maSanPham");
                String tenSP = rs.getString("tenSanPham");
                String loaiSP = rs.getString("loaiSanPham");
                double gia = rs.getDouble("gia");
                int tongSoLuongBan = rs.getInt("tongSoLuongBan");
                
                // Tạo đối tượng BaoCaoSanPhamBanChay_enity và thêm vào danh sách
                BaoCaoSanPhamBanChay_enity bc = new BaoCaoSanPhamBanChay_enity(maSP, tenSP, loaiSP, gia, tongSoLuongBan);
                list.add(bc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
    else if (select == 365) {
        try {
            // Kết nối cơ sở dữ liệu
            connection = connectDB.accessDataBase();
            
            // Câu truy vấn SQL
            String sql = """
                    SELECT 
                        sp.maSP AS maSanPham,
                        sp.tenSP AS tenSanPham,
                        lsp.tenLoaiSP AS loaiSanPham,
                        sp.gia AS gia,
                        SUM(cthd.soLuongSanPham) AS tongSoLuongBan
                    FROM 
                        SanPham sp
                    JOIN 
                        LoaiSanPham lsp ON sp.maLoaiSP = lsp.maLoaiSP
                    JOIN 
                        ChiTietHoaDon cthd ON sp.maSP = cthd.maSP
                    Join 
                    	HoaDon hd ON hd.maHD = cthd.maHD
                    WHERE ngayLapHD >= DATEADD(DAY, -365, GETDATE())
                    GROUP BY 
                        sp.maSP, sp.tenSP, lsp.tenLoaiSP, sp.gia
                    ORDER BY 
                        tongSoLuongBan ;
            """;

            // Tạo statement và thực thi truy vấn
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            // Xử lý kết quả
            while (rs.next()) {
                String maSP = rs.getString("maSanPham");
                String tenSP = rs.getString("tenSanPham");
                String loaiSP = rs.getString("loaiSanPham");
                double gia = rs.getDouble("gia");
                int tongSoLuongBan = rs.getInt("tongSoLuongBan");
                
                // Tạo đối tượng BaoCaoSanPhamBanChay_enity và thêm vào danh sách
                BaoCaoSanPhamBanChay_enity bc = new BaoCaoSanPhamBanChay_enity(maSP, tenSP, loaiSP, gia, tongSoLuongBan);
                list.add(bc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    } 

    return list;  // Trả về danh sách kết quả
}
    
    public ArrayList<BaoCaoSanPhamBanChay_enity> getSanPhamBanChamTuyChinh(Date from, Date to) {
        ArrayList<BaoCaoSanPhamBanChay_enity> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Kết nối cơ sở dữ liệu
            connection = connectDB.accessDataBase();

            // Câu truy vấn SQL
            String sql = """
                    SELECT 
                        sp.maSP AS maSanPham,
                        sp.tenSP AS tenSanPham,
                        lsp.tenLoaiSP AS loaiSanPham,
                        sp.gia AS gia,
                        SUM(cthd.soLuongSanPham) AS tongSoLuongBan
                    FROM 
                        SanPham sp
                    JOIN 
                        LoaiSanPham lsp ON sp.maLoaiSP = lsp.maLoaiSP
                    JOIN 
                        ChiTietHoaDon cthd ON sp.maSP = cthd.maSP
                    JOIN 
                        HoaDon hd ON hd.maHD = cthd.maHD
                    WHERE 
                        hd.ngayLapHD BETWEEN ? AND ?
                    GROUP BY 
                        sp.maSP, sp.tenSP, lsp.tenLoaiSP, sp.gia
                    ORDER BY 
                        tongSoLuongBan ;
            """;

            // Tạo PreparedStatement và truyền tham số
            ps = connection.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(from.getTime()));
            ps.setDate(2, new java.sql.Date(to.getTime()));

            // Thực thi truy vấn
            rs = ps.executeQuery();

            // Xử lý kết quả
            while (rs.next()) {
                String maSP = rs.getString("maSanPham");
                String tenSP = rs.getString("tenSanPham");
                String loaiSP = rs.getString("loaiSanPham");
                double gia = rs.getDouble("gia");
                int tongSoLuongBan = rs.getInt("tongSoLuongBan");

                // Tạo đối tượng BaoCaoSanPhamBanChay_enity và thêm vào danh sách
                BaoCaoSanPhamBanChay_enity bc = new BaoCaoSanPhamBanChay_enity(maSP, tenSP, loaiSP, gia, tongSoLuongBan);
                list.add(bc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
    }
