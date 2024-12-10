/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.model;

/**
 *
 * @author HP
 */
public class ModelTopNhanVien {
    private String maNV;
    private String tenNV;
    private double doanhSo;

    public ModelTopNhanVien() {
    }

    public ModelTopNhanVien(String maNV, String tenNV, double doanhSo) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.doanhSo = doanhSo;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public double getDoanhSo() {
        return doanhSo;
    }

    public void setDoanhSo(double doanhSo) {
        this.doanhSo = doanhSo;
    }
    
}
