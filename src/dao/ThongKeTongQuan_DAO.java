/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectDB;
import entity.KhachHang_entity;
import entity.SanPham_entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import nguyenvu.model.ModelKhachHangMoi;
import nguyenvu.model.ModelLineChart;
import nguyenvu.model.ModelTopKhachHang;
import nguyenvu.model.ModelTopNhanVien;
import nguyenvu.model.ModelTopSanPham;

/**
 *
 * @author HP
 */
public class ThongKeTongQuan_DAO {
    public static enum DateRange {
        LAST_7_DAYS,
        LAST_30_DAYS,
        THIS_MONTH,
        LAST_MONTH,
        THIS_YEAR,
        LAST_YEAR,
        CUSTOM
    }

    
    public static ArrayList<ModelLineChart> getLineChartData(DateRange dateRange, LocalDate startDate, LocalDate endDate) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ModelLineChart> listData = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT \n" +
                "    ngay,\n" +
                "    SUM( (tongTien - tienGiam)) AS doanhThu,\n" +
                "    SUM(chiPhi) AS chiPhi,\n" +
                "    SUM(tongThue) AS tongThue,\n" +
                "    slDonBan,\n" +
                "    SUM(slDonDoiTra) as slDonDoiTra,\n" +
                "    SUM(tongSLSP) AS tongSoSPDaBan\n" +
                "FROM (\n" +
                "    SELECT \n" +
                "        FORMAT(ngayLapHD, 'yyyy-MM-dd') as ngay, \n" +
                "		count(distinct HD.maHD) as slDonBan ,\n" +
                "		slDonDoiTra = 0,\n" +
                "        sum(CTHD.soLuongSanPham * CTHD.gia) as tongTien, \n" +
                "		sum(distinct tienGiam) as tienGiam,\n" +
                "		SUM(giaNhap * soLuongSanPham) as chiPhi,\n" +
                "        SUM((SP.thue * SP.gia / 100) * soLuongSanPham) as tongThue, \n" +
                "        SUM(CTHD.soLuongSanPham) as tongSLSP, \n" +
                "        'Ban' AS LoaiHoaDon\n" +
                "    FROM HoaDon HD\n" +
                "    JOIN ChiTietHoaDon CTHD ON HD.maHD = CTHD.maHD\n" +
                "    JOIN SanPham SP ON CTHD.maSP = SP.maSP\n" +
                "    WHERE ngayLapHD BETWEEN ? AND ?\n" +
                "	GROUP BY FORMAT(ngayLapHD, 'yyyy-MM-dd')\n" +
                "    \n" +
                "    UNION ALL\n" +
                "\n" +
                "	SELECT\n" +
                "        FORMAT(ngayDoiTra, 'yyyy-MM-dd') as ngay, \n" +
                "		slDonBan = 0,\n" +
                "		count(distinct HDDT.maDT) as slDonDoiTra,\n" +
                "		SUM(CASE \n" +
                "			WHEN CTHDDT.loaiDoiTra = 'Mua' AND HDDT.tienKhachTraThem > 0 THEN (CTHDDT.soLuong * SP.gia) + HDDT.tienKhachTraThem\n" +
                "			WHEN CTHDDT.loaiDoiTra = 'Mua' AND HDDT.tienKhachTraThem = 0 THEN 0\n" +
                "			WHEN CTHDDT.loaiDoiTra = 'Tra' THEN - (CTHDDT.soLuong * SP.gia) - HDDT.tienTraLai\n" +
                "			ELSE 0\n" +
                "		END) AS tongTien,\n" +
                "		SUM(CASE \n" +
                "			WHEN CTHDDT.loaiDoiTra = 'Mua' THEN HDDT.tienGiam\n" +
                "			ELSE 0\n" +
                "		END) AS tienGiam,\n" +
                "		SUM(CASE\n" +
                "			WHEN CTHDDT.loaiDoiTra = 'Mua' THEN (CTHDDT.soLuong * SP.giaNhap)\n" +
                "			WHEN CTHDDT.loaiDoiTra = 'Tra' THEN 0\n" +
                "			ELSE 0\n" +
                "		END) AS chiPhi,\n" +
                "		SUM(CASE\n" +
                "				WHEN CTHDDT.loaiDoiTra = 'Mua' THEN CTHDDT.soLuong * SP.giaNhap * sp.thue / 100 \n" +
                "				ELSE 0\n" +
                "			END)\n" +
                "		AS tongThue,\n" +
                "		SUM(CTHDDT.soLuong) as tongSLSP,\n" +
                "		'DoiTra' AS LoaiHoaDon\n" +
                "	FROM\n" +
                "		HoaDonDoiTra HDDT\n" +
                "		JOIN ChiTietHoaDonDoiTra CTHDDT ON HDDT.maDT = CTHDDT.maDT\n" +
                "		JOIN SanPham SP ON CTHDDT.maSP = SP.maSP\n" +
                "	WHERE HDDT.ngayDoiTra BETWEEN ? AND ?\n" +
                "	GROUP BY\n" +
                "		FORMAT(ngayDoiTra, 'yyyy-MM-dd')\n" +
                ")AS combined_results\n" +
                "GROUP BY ngay, \n" +
                "    slDonBan,\n" +
                "    slDonDoiTra;");
            
            LocalDate finalStartDate = startDate;
            LocalDate finalEndDate = endDate;

            switch (dateRange) {
                case LAST_7_DAYS:
                    finalStartDate = LocalDate.now().minusDays(7);
                    finalEndDate = LocalDate.now();
                    break;
                case LAST_30_DAYS:
                    finalStartDate = LocalDate.now().minusDays(30);
                    finalEndDate = LocalDate.now();
                    break;
                case THIS_MONTH:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
                    finalEndDate = LocalDate.now();
                    break;
                case LAST_MONTH:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1)
                            .minusMonths(1);
                    finalEndDate = finalStartDate.withDayOfMonth(finalStartDate.lengthOfMonth());
                    break;
                case THIS_YEAR:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
                    finalEndDate = LocalDate.now();
                    break;
                case LAST_YEAR:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear() - 1, 1, 1);
                    finalEndDate = LocalDate.of(LocalDate.now().getYear() - 1, 12, 31);
                    break;
                case CUSTOM:
                    break; 
                default:
                    throw new IllegalArgumentException("Invalid date range");
            }
            
            ps.setDate(1, java.sql.Date.valueOf(finalStartDate)); 
            ps.setDate(2, java.sql.Date.valueOf(finalEndDate));
            ps.setDate(3, java.sql.Date.valueOf(finalStartDate)); 
            ps.setDate(4, java.sql.Date.valueOf(finalEndDate));
            rs = ps.executeQuery();
            
            while (rs.next()) {
                listData.add(new ModelLineChart(
                        rs.getDate("ngay").toLocalDate(), 
                        rs.getDouble("doanhThu"), 
                        rs.getDouble("chiPhi"), 
                        rs.getDouble("tongThue"), 
                        rs.getInt("slDonBan"), 
                        rs.getInt("slDonDoiTra"),
                        rs.getInt("tongSoSPDaBan")
                ));
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

        return listData;
    }
    
    public static ArrayList<ModelLineChart> getLineChartPrevData(ThongKeTongQuan_DAO.DateRange dateRange, LocalDate startDate, LocalDate endDate) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ModelLineChart> listData = new ArrayList<>();

        try {
           ps = con.prepareStatement("SELECT \n" +
                "    ngay,\n" +
                "    SUM( (tongTien - tienGiam)) AS doanhThu,\n" +
                "    SUM(chiPhi) AS chiPhi,\n" +
                "    SUM(tongThue) AS tongThue,\n" +
                "    slDonBan,\n" +
                "    SUM(slDonDoiTra) as slDonDoiTra,\n" +
                "    SUM(tongSLSP) AS tongSoSPDaBan\n" +
                "FROM (\n" +
                "    SELECT \n" +
                "        FORMAT(ngayLapHD, 'yyyy-MM-dd') as ngay, \n" +
                "		count(distinct HD.maHD) as slDonBan ,\n" +
                "		slDonDoiTra = 0,\n" +
                "        sum(CTHD.soLuongSanPham * CTHD.gia) as tongTien, \n" +
                "		sum(distinct tienGiam) as tienGiam,\n" +
                "		SUM(giaNhap * soLuongSanPham) as chiPhi,\n" +
                "        SUM((SP.thue * SP.gia / 100) * soLuongSanPham) as tongThue, \n" +
                "        SUM(CTHD.soLuongSanPham) as tongSLSP, \n" +
                "        'Ban' AS LoaiHoaDon\n" +
                "    FROM HoaDon HD\n" +
                "    JOIN ChiTietHoaDon CTHD ON HD.maHD = CTHD.maHD\n" +
                "    JOIN SanPham SP ON CTHD.maSP = SP.maSP\n" +
                "    WHERE ngayLapHD BETWEEN ? AND ?\n" +
                "	GROUP BY FORMAT(ngayLapHD, 'yyyy-MM-dd')\n" +
                "    \n" +
                "    UNION ALL\n" +
                "\n" +
                "	SELECT\n" +
                "        FORMAT(ngayDoiTra, 'yyyy-MM-dd') as ngay, \n" +
                "		slDonBan = 0,\n" +
                "		count(distinct HDDT.maDT) as slDonDoiTra,\n" +
                "		SUM(CASE \n" +
                "			WHEN CTHDDT.loaiDoiTra = 'Mua' AND HDDT.tienKhachTraThem > 0 THEN (CTHDDT.soLuong * SP.gia) + HDDT.tienKhachTraThem\n" +
                "			WHEN CTHDDT.loaiDoiTra = 'Mua' AND HDDT.tienKhachTraThem = 0 THEN 0\n" +
                "			WHEN CTHDDT.loaiDoiTra = 'Tra' THEN - (CTHDDT.soLuong * SP.gia) - HDDT.tienTraLai\n" +
                "			ELSE 0\n" +
                "		END) AS tongTien,\n" +
                "		SUM(CASE \n" +
                "			WHEN CTHDDT.loaiDoiTra = 'Mua' THEN HDDT.tienGiam\n" +
                "			ELSE 0\n" +
                "		END) AS tienGiam,\n" +
                "		SUM(CASE\n" +
                "			WHEN CTHDDT.loaiDoiTra = 'Mua' THEN (CTHDDT.soLuong * SP.giaNhap)\n" +
                "			WHEN CTHDDT.loaiDoiTra = 'Tra' THEN 0\n" +
                "			ELSE 0\n" +
                "		END) AS chiPhi,\n" +
                "		SUM(CASE\n" +
                "				WHEN CTHDDT.loaiDoiTra = 'Mua' THEN CTHDDT.soLuong * SP.giaNhap * sp.thue / 100 \n" +
                "				ELSE 0\n" +
                "			END)\n" +
                "		AS tongThue,\n" +
                "		SUM(CTHDDT.soLuong) as tongSLSP,\n" +
                "		'DoiTra' AS LoaiHoaDon\n" +
                "	FROM\n" +
                "		HoaDonDoiTra HDDT\n" +
                "		JOIN ChiTietHoaDonDoiTra CTHDDT ON HDDT.maDT = CTHDDT.maDT\n" +
                "		JOIN SanPham SP ON CTHDDT.maSP = SP.maSP\n" +
                "	WHERE HDDT.ngayDoiTra BETWEEN ? AND ?\n" +
                "	GROUP BY\n" +
                "		FORMAT(ngayDoiTra, 'yyyy-MM-dd')\n" +
                ")AS combined_results\n" +
                "GROUP BY ngay, \n" +
                "    slDonBan,\n" +
                "    slDonDoiTra;");
            
            LocalDate prevStartDate = LocalDate.now();
            LocalDate prevEndDate = endDate != null ? endDate : LocalDate.now();

            if (startDate == null && endDate == null) {
                switch (dateRange) {
                    case LAST_7_DAYS:
                        prevStartDate = prevEndDate.minusDays(14);  // 14 days before the last 7 days
                        prevEndDate = prevEndDate.minusDays(7);    // Last 7 days
                        System.out.println("dao.ThongKeTongQuan_DAO.getLineChartPrevData()");
                        break;
                    case LAST_30_DAYS:
                        prevStartDate = prevEndDate.minusDays(60); // 60 days before the last 30 days
                        prevEndDate = prevEndDate.minusDays(30);  // Last 30 days
                        System.out.println("dao.ThongKeTongQuan_DAO.getLineChartPrevData()");
                        break;
                    case THIS_MONTH:
                        prevStartDate = prevEndDate.withDayOfMonth(1).minusMonths(1); // First day of the last month
                        prevEndDate = prevStartDate.withDayOfMonth(prevStartDate.lengthOfMonth()); // Last day of the last month
                        System.out.println("dao.ThongKeTongQuan_DAO.getLineChartPrevData()");
                        break;
                    case LAST_MONTH:
                        prevStartDate = prevEndDate.minusMonths(2).withDayOfMonth(1); // First day of the month before last month
                        prevEndDate = prevStartDate.withDayOfMonth(prevStartDate.lengthOfMonth()); // Last day of the month before last month
                        System.out.println("dao.ThongKeTongQuan_DAO.getLineChartPrevData()");
                        break;
                    case THIS_YEAR:
                        prevStartDate = LocalDate.of(prevEndDate.getYear() - 1, 1, 1); // First day of last year
                        prevEndDate = LocalDate.of(prevEndDate.getYear() - 1, 12, 31); // Last day of last year
                        System.out.println("dao.ThongKeTongQuan_DAO.getLineChartPrevData()");
                        break;
                    case LAST_YEAR:
                        prevStartDate = LocalDate.of(prevEndDate.getYear() - 2, 1, 1); // First day of the year before last year
                        prevEndDate = LocalDate.of(prevEndDate.getYear() - 2, 12, 31); // Last day of the year before last year
                        System.out.println("dao.ThongKeTongQuan_DAO.getLineChartPrevData()");
                        break;
                    case CUSTOM:
                        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
                        prevStartDate = startDate.minusDays(daysBetween);
                        prevEndDate = endDate.minusDays(daysBetween);
                        System.out.println("Days Between: " + daysBetween);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid date range");
                }
            } else {
                long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
                        prevStartDate = startDate.minusDays(daysBetween);
                        prevEndDate = endDate.minusDays(daysBetween);
            }
            
            
                    System.out.println("Prev Start Date: " + prevStartDate);
                    System.out.println("Prev End Date: " + prevEndDate);

            ps.setDate(1, java.sql.Date.valueOf(prevStartDate));
            ps.setDate(2, java.sql.Date.valueOf(prevEndDate));
            ps.setDate(3, java.sql.Date.valueOf(prevStartDate));
            ps.setDate(4, java.sql.Date.valueOf(prevEndDate));
            rs = ps.executeQuery();
            
            while (rs.next()) {
                listData.add(new ModelLineChart(
                        rs.getDate("ngay").toLocalDate(), 
                        rs.getDouble("doanhThu"), 
                        rs.getDouble("chiPhi"), 
                        rs.getDouble("tongThue"), 
                        rs.getInt("slDonBan"), 
                        rs.getInt("slDonDoiTra"),
                        rs.getInt("tongSoSPDaBan")
                ));
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

        return listData;
    }
    
    public static ArrayList<ModelTopSanPham> getChartDataTopSanPham(DateRange dateRange, LocalDate startDate, LocalDate endDate) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ModelTopSanPham> listData = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT TOP 10 \n" +
                "    COUNT(CTHD.maSP) AS soLuong, \n" +
                "    CTHD.maSP, \n" +
                "    SP.tenSP\n" +
                "FROM HoaDon HD\n" +
                "JOIN ChiTietHoaDon CTHD ON HD.maHD = CTHD.maHD\n" +
                "JOIN SanPham SP ON CTHD.maSP = SP.maSP\n" +
                "WHERE HD.ngayLapHD BETWEEN ? AND ?\n" +
                "  AND HD.maHD IS NOT NULL\n" +
                "GROUP BY CTHD.maSP, SP.tenSP\n" +
                "ORDER BY soLuong DESC");
            
            LocalDate finalStartDate = startDate;
            LocalDate finalEndDate = endDate;

            switch (dateRange) {
                case LAST_7_DAYS:
                    finalStartDate = LocalDate.now().minusDays(7);
                    finalEndDate = LocalDate.now();
                    break;
                case LAST_30_DAYS:
                    finalStartDate = LocalDate.now().minusDays(30);
                    finalEndDate = LocalDate.now();
                    break;
                case THIS_MONTH:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
                    finalEndDate = LocalDate.now();
                    break;
                case LAST_MONTH:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1)
                            .minusMonths(1);
                    finalEndDate = finalStartDate.withDayOfMonth(finalStartDate.lengthOfMonth());
                    break;
                case THIS_YEAR:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
                    finalEndDate = LocalDate.now();
                    break;
                case LAST_YEAR:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear() - 1, 1, 1);
                    finalEndDate = LocalDate.of(LocalDate.now().getYear() - 1, 12, 31);
                    break;
                case CUSTOM:
                    break; 
                default:
                    throw new IllegalArgumentException("Invalid date range");
            }
            
            ps.setDate(1, java.sql.Date.valueOf(finalStartDate)); 
            ps.setDate(2, java.sql.Date.valueOf(finalEndDate));
            rs = ps.executeQuery();
            
            while (rs.next()) {
                listData.add(new ModelTopSanPham(
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getInt("soLuong")
                ));
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

        return listData;
    }
    
    public static ArrayList<ModelTopNhanVien> getChartDataTopNhanVien(DateRange dateRange, LocalDate startDate, LocalDate endDate) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ModelTopNhanVien> listData = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT TOP 10 \n" +
                "    NV.maNV, \n" +
                "    NV.hotenNV,\n" +
                "    SUM(distinct HD.tongTien - HD.tienGiam) AS doanhSo\n" +
                "FROM HoaDon HD \n" +
                "JOIN NhanVien NV ON HD.maNV = NV.maNV\n" +
                "WHERE HD.ngayLapHD BETWEEN ? AND ?\n" +
                "GROUP BY NV.maNV, NV.hotenNV\n" +
                "ORDER BY doanhSo DESC");

            LocalDate finalStartDate = startDate;
            LocalDate finalEndDate = endDate;

            switch (dateRange) {
                case LAST_7_DAYS:
                    finalStartDate = LocalDate.now().minusDays(7);
                    finalEndDate = LocalDate.now();
                    break;
                case LAST_30_DAYS:
                    finalStartDate = LocalDate.now().minusDays(30);
                    finalEndDate = LocalDate.now();
                    break;
                case THIS_MONTH:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
                    finalEndDate = LocalDate.now();
                    break;
                case LAST_MONTH:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1)
                            .minusMonths(1);
                    finalEndDate = finalStartDate.withDayOfMonth(finalStartDate.lengthOfMonth());
                    break;
                case THIS_YEAR:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
                    finalEndDate = LocalDate.now();
                    break;
                case LAST_YEAR:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear() - 1, 1, 1);
                    finalEndDate = LocalDate.of(LocalDate.now().getYear() - 1, 12, 31);
                    break;
                case CUSTOM:
                    break; 
                default:
                    throw new IllegalArgumentException("Invalid date range");
            }
            
            ps.setDate(1, java.sql.Date.valueOf(finalStartDate)); 
            ps.setDate(2, java.sql.Date.valueOf(finalEndDate));
            rs = ps.executeQuery();
            
            while (rs.next()) {
                listData.add(new ModelTopNhanVien(
                        rs.getString("maNV"),
                        rs.getString("hotenNV"),
                        rs.getInt("doanhSo")
                ));
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

        return listData;
    }
  
    public static ArrayList<ModelTopKhachHang> getChartDataTopKhachHang(DateRange dateRange, LocalDate startDate, LocalDate endDate) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ModelTopKhachHang> listData = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT TOP 10 \n" +
                "    KH.sdtKH, \n" +
                "    KH.tenKH,\n" +
                "    SUM(distinct HD.tongTien - HD.tienGiam) AS tongTienMua\n" +
                "FROM HoaDon HD \n" +
                "JOIN KhachHang KH ON HD.sdtKH = KH.sdtKH\n" +
                "WHERE HD.ngayLapHD BETWEEN ? AND ?\n" +
                "GROUP BY KH.sdtKH, KH.tenKH\n" +
                "ORDER BY tongTienMua DESC");

            LocalDate finalStartDate = startDate;
            LocalDate finalEndDate = endDate;

            switch (dateRange) {
                case LAST_7_DAYS:
                    finalStartDate = LocalDate.now().minusDays(7);
                    finalEndDate = LocalDate.now();
                    break;
                case LAST_30_DAYS:
                    finalStartDate = LocalDate.now().minusDays(30);
                    finalEndDate = LocalDate.now();
                    break;
                case THIS_MONTH:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
                    finalEndDate = LocalDate.now();
                    break;
                case LAST_MONTH:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1)
                            .minusMonths(1);
                    finalEndDate = finalStartDate.withDayOfMonth(finalStartDate.lengthOfMonth());
                    break;
                case THIS_YEAR:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
                    finalEndDate = LocalDate.now();
                    break;
                case LAST_YEAR:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear() - 1, 1, 1);
                    finalEndDate = LocalDate.of(LocalDate.now().getYear() - 1, 12, 31);
                    break;
                case CUSTOM:
                    break; 
                default:
                    throw new IllegalArgumentException("Invalid date range");
            }
            
            ps.setDate(1, java.sql.Date.valueOf(finalStartDate)); 
            ps.setDate(2, java.sql.Date.valueOf(finalEndDate));
            rs = ps.executeQuery();
            
            while (rs.next()) {
                listData.add(new ModelTopKhachHang(
                        rs.getString("sdtKH"),
                        rs.getString("tenKH"),
                        rs.getInt("tongTienMua")
                ));
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

        return listData;
    }
    
    public static ArrayList<ModelKhachHangMoi> getChartDataKhachHangMoi(DateRange dateRange, LocalDate startDate, LocalDate endDate) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ModelKhachHangMoi> listData = new ArrayList<>();

        try {
            ps = con.prepareStatement("select FORMAT(ngayThemKH, 'yyyy-MM-dd') as ngay,\n" +
                "	count(sdtKH) as soLuong\n" +
                "from KhachHang\n" +
                "where ngayThemKH between ? and ?\n" +
                "GROUP BY FORMAT(ngayThemKH, 'yyyy-MM-dd')");
            
            LocalDate finalStartDate = startDate;
            LocalDate finalEndDate = endDate;

            switch (dateRange) {
                case LAST_7_DAYS:
                    finalStartDate = LocalDate.now().minusDays(7);
                    finalEndDate = LocalDate.now();
                    break;
                case LAST_30_DAYS:
                    finalStartDate = LocalDate.now().minusDays(30);
                    finalEndDate = LocalDate.now();
                    break;
                case THIS_MONTH:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
                    finalEndDate = LocalDate.now();
                    break;
                case LAST_MONTH:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1)
                            .minusMonths(1);
                    finalEndDate = finalStartDate.withDayOfMonth(finalStartDate.lengthOfMonth());
                    break;
                case THIS_YEAR:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
                    finalEndDate = LocalDate.now();
                    break;
                case LAST_YEAR:
                    finalStartDate = LocalDate.of(LocalDate.now().getYear() - 1, 1, 1);
                    finalEndDate = LocalDate.of(LocalDate.now().getYear() - 1, 12, 31);
                    break;
                case CUSTOM:
                    break; 
                default:
                    throw new IllegalArgumentException("Invalid date range");
            }
            
            ps.setDate(1, java.sql.Date.valueOf(finalStartDate)); 
            ps.setDate(2, java.sql.Date.valueOf(finalEndDate));
            rs = ps.executeQuery();
            
            while (rs.next()) {
                listData.add(new ModelKhachHangMoi(
                        rs.getDate("ngay").toLocalDate(),
                        rs.getInt("soLuong")
                ));
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

        return listData;
    }

    public static ArrayList<ModelKhachHangMoi> getChartPrevDataKhachHangMoi(DateRange dateRange, LocalDate startDate, LocalDate endDate) {
        Connection con = connectDB.accessDataBase();
        if (con == null) return null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ModelKhachHangMoi> listData = new ArrayList<>();

        try {
            ps = con.prepareStatement("select FORMAT(ngayThemKH, 'yyyy-MM-dd') as ngay,\n" +
                "	count(sdtKH) as soLuong\n" +
                "from KhachHang\n" +
                "where ngayThemKH  between ? and ?\n" +
                "GROUP BY FORMAT(ngayThemKH, 'yyyy-MM-dd')");
            
            LocalDate prevStartDate = LocalDate.now();
            LocalDate prevEndDate = endDate != null ? endDate : LocalDate.now();

            if (startDate == null && endDate == null) {
                switch (dateRange) {
                    case LAST_7_DAYS:
                        prevStartDate = prevEndDate.minusDays(14);  // 14 days before the last 7 days
                        prevEndDate = prevEndDate.minusDays(7);    // Last 7 days
                        System.out.println("dao.ThongKeTongQuan_DAO.getLineChartPrevData()");
                        break;
                    case LAST_30_DAYS:
                        prevStartDate = prevEndDate.minusDays(60); // 60 days before the last 30 days
                        prevEndDate = prevEndDate.minusDays(30);  // Last 30 days
                        System.out.println("dao.ThongKeTongQuan_DAO.getLineChartPrevData()");
                        break;
                    case THIS_MONTH:
                        prevStartDate = prevEndDate.withDayOfMonth(1).minusMonths(1); // First day of the last month
                        prevEndDate = prevStartDate.withDayOfMonth(prevStartDate.lengthOfMonth()); // Last day of the last month
                        System.out.println("dao.ThongKeTongQuan_DAO.getLineChartPrevData()");
                        break;
                    case LAST_MONTH:
                        prevStartDate = prevEndDate.minusMonths(2).withDayOfMonth(1); // First day of the month before last month
                        prevEndDate = prevStartDate.withDayOfMonth(prevStartDate.lengthOfMonth()); // Last day of the month before last month
                        System.out.println("dao.ThongKeTongQuan_DAO.getLineChartPrevData()");
                        break;
                    case THIS_YEAR:
                        prevStartDate = LocalDate.of(prevEndDate.getYear() - 1, 1, 1); // First day of last year
                        prevEndDate = LocalDate.of(prevEndDate.getYear() - 1, 12, 31); // Last day of last year
                        System.out.println("dao.ThongKeTongQuan_DAO.getLineChartPrevData()");
                        break;
                    case LAST_YEAR:
                        prevStartDate = LocalDate.of(prevEndDate.getYear() - 2, 1, 1); // First day of the year before last year
                        prevEndDate = LocalDate.of(prevEndDate.getYear() - 2, 12, 31); // Last day of the year before last year
                        System.out.println("dao.ThongKeTongQuan_DAO.getLineChartPrevData()");
                        break;
                    case CUSTOM:
                        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
                        prevStartDate = startDate.minusDays(daysBetween);
                        prevEndDate = endDate.minusDays(daysBetween);
                        System.out.println("Days Between: " + daysBetween);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid date range");
                }
            } else {
                long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
                        prevStartDate = startDate.minusDays(daysBetween);
                        prevEndDate = endDate.minusDays(daysBetween);
            }
            

            ps.setDate(1, java.sql.Date.valueOf(prevStartDate));
            ps.setDate(2, java.sql.Date.valueOf(prevEndDate));
            rs = ps.executeQuery();
            
            while (rs.next()) {
                listData.add(new ModelKhachHangMoi(
                        rs.getDate("ngay").toLocalDate(),
                        rs.getInt("soLuong")
                ));
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

        return listData;
    }


}
