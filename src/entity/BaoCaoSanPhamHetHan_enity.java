/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author Vovan
 */
public class BaoCaoSanPhamHetHan_enity {
    private String maSP;
    private String tenSP;
    private String loaiSP;
    private Date ngaySX;
    private Date ngayHH;

    public BaoCaoSanPhamHetHan_enity() {
    }

    public BaoCaoSanPhamHetHan_enity(String maSP, String tenSP, String loaiSP, Date ngaySX, Date ngayHH) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.ngaySX = ngaySX;
        this.ngayHH = ngayHH;
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

    public Date getNgaySX() {
        return ngaySX;
    }

    public void setNgaySX(Date ngaySX) {
        this.ngaySX = ngaySX;
    }

    public Date getNgayHH() {
        return ngayHH;
    }

    public void setNgayHH(Date ngayHH) {
        this.ngayHH = ngayHH;
    }

    @Override
    public String toString() {
        return "BaoCaoSanPhamHetHan_enity{" + "maSP=" + maSP + ", tenSP=" + tenSP + ", loaiSP=" + loaiSP + ", ngaySX=" + ngaySX + ", ngayHH=" + ngayHH + '}';
    }

    
    
}
