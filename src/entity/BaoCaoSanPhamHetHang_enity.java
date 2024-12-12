/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Vovan
 */
public class BaoCaoSanPhamHetHang_enity {
    private String maSP;
    private String tenSP;
    private String loaiSP;
    private double gia;
    private int slt;

    public BaoCaoSanPhamHetHang_enity(String maSP, String tenSP, String loaiSP, double gia, int slt) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.gia = gia;
        this.slt = slt;
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

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSlt() {
        return slt;
    }

    public void setSlt(int slt) {
        this.slt = slt;
    }

    public BaoCaoSanPhamHetHang_enity() {
    }

    @Override
    public String toString() {
        return "BaoCaoSanPhamHetHang_enity{" + "maSP=" + maSP + ", tenSP=" + tenSP + ", loaiSP=" + loaiSP + ", gia=" + gia + ", slt=" + slt + '}';
    }
    
    
}
