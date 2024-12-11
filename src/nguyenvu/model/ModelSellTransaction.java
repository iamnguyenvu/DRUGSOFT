/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.model;

import java.time.LocalDateTime;

/**
 *
 * @author HP
 */
public class ModelSellTransaction {
    private String phanQuyen;
    private String tenNV;
    private String loaiHoaDon;
    private double thanhTien;
    private double tienTraLai;
    private double tienKhachTraThem;
    private LocalDateTime date;

    public ModelSellTransaction(String phanQuyen, String tenNV, String loaiHoaDon, double thanhTien, double tienTraLai, double tienKhachTraThem, LocalDateTime date) {
        this.phanQuyen = phanQuyen;
        this.tenNV = tenNV;
        this.loaiHoaDon = loaiHoaDon;
        this.thanhTien = thanhTien;
        this.tienTraLai = tienTraLai;
        this.tienKhachTraThem = tienKhachTraThem;
        this.date = date;
    }

    public String getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(String phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getLoaiHoaDon() {
        return loaiHoaDon;
    }

    public void setLoaiHoaDon(String loaiHoaDon) {
        this.loaiHoaDon = loaiHoaDon;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public double getTienTraLai() {
        return tienTraLai;
    }

    public void setTienTraLai(double tienTraLai) {
        this.tienTraLai = tienTraLai;
    }

    public double getTienKhachTraThem() {
        return tienKhachTraThem;
    }

    public void setTienKhachTraThem(double tienKhachTraThem) {
        this.tienKhachTraThem = tienKhachTraThem;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    
    
}
