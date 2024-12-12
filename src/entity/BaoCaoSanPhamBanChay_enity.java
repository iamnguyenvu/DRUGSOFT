/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Vovan
 */
public class BaoCaoSanPhamBanChay_enity {
    private String maSP;
    private String tenSP;
    private String loaiSP;
    private double gia;
    private int slBan;

    public BaoCaoSanPhamBanChay_enity(String maSP, String tenSP, String loaiSP, double gia, int slBan) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.gia = gia;
        this.slBan = slBan;
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

    public int getSlBan() {
        return slBan;
    }

    public void setSlBan(int slBan) {
        this.slBan = slBan;
    }

    @Override
    public String toString() {
        return "BaoCaoSanPhamBanChay_enity{" + "maSP=" + maSP + ", tenSP=" + tenSP + ", loaiSP=" + loaiSP + ", gia=" + gia + ", slBan=" + slBan + '}';
    }

    public BaoCaoSanPhamBanChay_enity() {
    }

    
    
}
