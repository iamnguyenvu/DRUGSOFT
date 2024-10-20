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
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class BanHang_DAO {
    public ArrayList<SanPham_entity> searchSanPham(String key) {
        Connection con = connectDB.accessDataBase();
        if(con == null) return null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SanPham_entity> listSP = null;
        try {
            ps = con.prepareStatement("SELECT * FROM SanPham_entity WHERE tenSP LIKE '% "+key+"%' OR maSP LIKE '% "+key+"%' LIMIT 8");
            rs = ps.executeQuery();
            if (rs.next()) {
                SanPham_entity sp = new SanPham_entity(rs.getString(10), rs.getString(1), rs.getString(2), rs.getString(6), rs.getInt(12), rs.getDouble(8)); 
                listSP.add(sp);
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
        
        return listSP;
    }
}
