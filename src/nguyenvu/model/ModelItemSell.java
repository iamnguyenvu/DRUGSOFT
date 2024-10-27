/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.model;

import java.text.DecimalFormat;

/**
 *
 * @author HP
 */
public class ModelItemSell {

    private String hinhAnhSP;
    private String maSP;
    private String tenSP;
    private String donViTinh;
    private int soLuong;
    private double donGia;
    private double thanhTien;

    public ModelItemSell() {
    }

    public ModelItemSell(String hinhAnhSP, String maSP, String tenSP, String donViTinh, int soLuong, double donGia, double thanhTien) {
        this.hinhAnhSP = hinhAnhSP;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donViTinh = donViTinh;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public String getHinhAnhSP() {
        return hinhAnhSP;
    }

    public void setHinhAnhSP(String hinhAnhSP) {
        this.hinhAnhSP = hinhAnhSP;
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

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    
    public Object[] toTableRow(int col) {
        DecimalFormat df = new DecimalFormat("#,##0.##");
        return new Object[] {this, col, hinhAnhSP, maSP, tenSP, donViTinh, soLuong, df.format(donGia), df.format(thanhTien)};
    }
}
