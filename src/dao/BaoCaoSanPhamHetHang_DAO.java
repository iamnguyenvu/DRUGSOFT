/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.connectDB;
import entity.BaoCaoSanPhamHetHan_enity;
import entity.BaoCaoSanPhamHetHang_enity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Vovan
 */
public class BaoCaoSanPhamHetHang_DAO {
    public ArrayList<BaoCaoSanPhamHetHang_enity> getAllBaoCaoSanPhamHetHang() {
	    ArrayList<BaoCaoSanPhamHetHang_enity> dsBC = new ArrayList<BaoCaoSanPhamHetHang_enity>();
	    Connection con = connectDB.accessDataBase();
	    if (con == null) {
	        return null;
	    }
	    String sql = "SELECT \n" +
                    "    sp.maSP AS maSanPham,\n" +
                    "    sp.tenSP AS tenSanPham,\n" +
                    "    lsp.tenLoaiSP AS loaiSanPham,\n" +
                    "    sp.gia AS gia,\n" +
                    "    sp.soLuong AS soLuongTon\n" +
                    "FROM \n" +
                    "    SanPham sp\n" +
                    "LEFT JOIN \n" +
                    "    LoaiSanPham lsp ON sp.maLoaiSP = lsp.maLoaiSP\n" +
                    "WHERE \n" +
                    "    sp.soLuong = 0;";
	    try {
	        java.sql.Statement st = con.createStatement();  
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String maSP = rs.getString("maSanPham");
	            String tenSP = rs.getString("tenSanPham");
	            String loaiSP = rs.getString("loaiSanPham");
	            double gia = rs.getDouble("gia");
	            int slt = rs.getInt("soLuongTon");
	            BaoCaoSanPhamHetHang_enity bc = new BaoCaoSanPhamHetHang_enity(maSP, tenSP, loaiSP, gia, slt);
	            dsBC.add(bc);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dsBC;
	}
}
