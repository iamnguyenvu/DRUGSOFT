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
import java.util.ArrayList;
import nguyenvu.model.ModelLineChart;

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

    
    public static ArrayList<ModelLineChart> getLineChartDataCustom(DateRange dateRange, LocalDate startDate, LocalDate endDate) {
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
    
}
