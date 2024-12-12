/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.model;

/**
 *
 * @author HP
 */
public class ModelTopSanPham {
    private String maSP;
    private String tenSP;
    private int soLuong;

    public ModelTopSanPham() {
    }

    public ModelTopSanPham(String maSP, String tenSP, int soLuong) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
}
