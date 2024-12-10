/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectDB;
import entity.SanPham_entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
            ps = con.prepareStatement("select FORMAT(ngayLapHD, 'yyyy-MM-dd') AS ngay, \n" +
            "	SUM(DISTINCT (tongTien - tienGiam)) as doanhThu, \n" +
            "	SUM(giaNhap * CTHD.soLuongSanPham) as chiPhi, \n" +
            "	SUM((thue * gia / 100) * CTHD.soLuongSanPham) as tongThue,\n" +
            "	COUNT(DISTINCT CASE WHEN maLoaiHD = 'BanSanPham' THEN HD.maHD ELSE NULL END) AS slDonBan, \n" +
            "    COUNT(DISTINCT CASE WHEN maLoaiHD = 'DoiSanPham' THEN HD.maHD ELSE NULL END) AS slDonDoi,\n" +
            "    COUNT(DISTINCT CASE WHEN maLoaiHD = 'TraSanPham' THEN HD.maHD ELSE NULL END) AS slDonTra,\n" +
            "    SUM(CTHD.soLuongSanPham) AS tongSoSPDaBan\n" +
            "from HoaDon HD join ChiTietHoaDon CTHD on HD.maHD = CTHD.maHD join SanPham SP on CTHD.maSP = SP.maSP\n" +
            "where ngayLapHD between ? and ?\n" +
            "group by FORMAT(ngayLapHD, 'yyyy-MM-dd')");
            
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
                listData.add(new ModelLineChart(
                        rs.getDate("ngay").toLocalDate(), 
                        rs.getDouble("doanhThu"), 
                        rs.getDouble("chiPhi"), 
                        rs.getDouble("tongThue"), 
                        rs.getInt("slDonBan"), 
                        rs.getInt("slDonDoi"), 
                        rs.getInt("slDonTra"), 
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
            ps = con.prepareStatement("select FORMAT(ngayLapHD, 'yyyy-MM-dd') AS ngay, \n" +
            "	SUM(DISTINCT (tongTien - tienGiam)) as doanhThu, \n" +
            "	SUM(giaNhap * CTHD.soLuongSanPham) as chiPhi, \n" +
            "	SUM((thue * gia / 100) * CTHD.soLuongSanPham) as tongThue,\n" +
            "	COUNT(DISTINCT CASE WHEN maLoaiHD = 'BanSanPham' THEN HD.maHD ELSE NULL END) AS slDonBan, \n" +
            "    COUNT(DISTINCT CASE WHEN maLoaiHD = 'DoiSanPham' THEN HD.maHD ELSE NULL END) AS slDonDoi,\n" +
            "    COUNT(DISTINCT CASE WHEN maLoaiHD = 'TraSanPham' THEN HD.maHD ELSE NULL END) AS slDonTra,\n" +
            "    SUM(CTHD.soLuongSanPham) AS tongSoSPDaBan\n" +
            "from HoaDon HD join ChiTietHoaDon CTHD on HD.maHD = CTHD.maHD join SanPham SP on CTHD.maSP = SP.maSP\n" +
            "where ngayLapHD between ? and ?\n" +
            "group by FORMAT(ngayLapHD, 'yyyy-MM-dd')");
            
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
            rs = ps.executeQuery();
            
            while (rs.next()) {
                listData.add(new ModelLineChart(
                        rs.getDate("ngay").toLocalDate(), 
                        rs.getDouble("doanhThu"), 
                        rs.getDouble("chiPhi"), 
                        rs.getDouble("tongThue"), 
                        rs.getInt("slDonBan"), 
                        rs.getInt("slDonDoi"), 
                        rs.getInt("slDonTra"), 
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
            ps = con.prepareStatement("select top 10 count(CTHD.maSP) as soLuong, CTHD.maSP, SP.tenSP\n" +
                "from HoaDon HD join ChiTietHoaDon CTHD on HD.maHD = CTHD.maHD join SanPham SP on CTHD.maSP = SP.maSP\n" +
                "where ngayLapHD between ? and ? and maLoaiHD = 'BanSanPham'\n" +
                "group by CTHD.maSP, SP.tenSP\n" +
                "order by soLuong desc");
            
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
            ps = con.prepareStatement("select top 10 \n" +
                "	NV.maNV, NV.hotenNV,\n" +
                "	sum(distinct tongTien - tienGiam) as doanhSo\n" +
                "from HoaDon HD join NhanVien NV on HD.maNV = NV.maNV\n" +
                "where ngayLapHD between ? and ? and maLoaiHD = 'BanSanPham'\n" +
                "group by NV.maNV, NV.hotenNV\n" +
                "order by sum(distinct tongTien - tienGiam) desc");

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
            ps = con.prepareStatement("select top 10 \n" +
                "	KH.sdtKH, KH.tenKH,\n" +
                "	sum(distinct tongTien - tienGiam) as tongTienMua\n" +
                "from HoaDon HD join KhachHang KH on HD.sdtKH = KH.sdtKH\n" +
                "where ngayLapHD between ? and ? and maLoaiHD = 'BanSanPham'\n" +
                "group by KH.sdtKH, KH.tenKH\n" +
                "order by sum(distinct tongTien - tienGiam) desc");

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
}
