/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectDB;
import entity.BaoCaoDoanhThu_enity;
import entity.BaoCaoSanPhamHetHan_enity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Vovan
 */
public class BaoCaoSanPhamHetHan_DAO {
    public ArrayList<BaoCaoSanPhamHetHan_enity> getAllBaoCaoSanPhamHetHan() {
	    ArrayList<BaoCaoSanPhamHetHan_enity> dsBC = new ArrayList<BaoCaoSanPhamHetHan_enity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT \n" +
                    "    sp.maSP AS maSP,\n" +
                    "    sp.tenSP AS tenSP,\n" +
                    "    lsp.tenLoaiSP AS loaiSP,\n" +
                    "    ctnh.ngaySanXuat AS ngaySX,\n" +
                    "    ctnh.ngayHetHan AS ngayHH\n" +
                    "FROM \n" +
                    "    SanPham sp\n" +
                    "INNER JOIN \n" +
                    "    LoaiSanPham lsp ON sp.maLoaiSP = lsp.maLoaiSP\n" +
                    "INNER JOIN \n" +
                    "    ChiTietNhapHang ctnh ON sp.maSP = ctnh.maSP\n" +
                    "WHERE \n" +
                    "    ctnh.ngayHetHan < GETDATE();";
	    try {
	        java.sql.Statement st = con.createStatement();  
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String maSP = rs.getString("maSP");
	            String tenSP = rs.getString("tenSP");
	            String loaiSP = rs.getString("loaiSP");
	            Date ngaySX = rs.getDate("ngaySX");
	            Date ngayHH = rs.getDate("ngayHH");
	            BaoCaoSanPhamHetHan_enity bc = new BaoCaoSanPhamHetHan_enity(maSP, tenSP, loaiSP, ngaySX, ngayHH);
	            dsBC.add(bc);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsBC;
	}
}
