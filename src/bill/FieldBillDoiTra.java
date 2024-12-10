/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bill;

/**
 *
 * @author HP
 */
public class FieldBillDoiTra {

    private String tenSP;
    private int soLuong;
    private double donGia;
    private double thanhTien;
    private String tinhTrang;
    private String loaiDoiTra;

    public FieldBillDoiTra() {
    }

    public FieldBillDoiTra(String tenSP, int soLuong, double donGia, double thanhTien, String tinhTrang, String loaiDoiTra) {
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.tinhTrang = tinhTrang;
        this.loaiDoiTra = loaiDoiTra;
    }

    public String getLoaiDoiTra() {
        return loaiDoiTra;
    }

    public void setLoaiDoiTra(String loaiDoiTra) {
        this.loaiDoiTra = loaiDoiTra;
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

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

}
