/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bill;

import java.io.InputStream;
import java.util.List;

/**
 *
 * @author HP
 */
public class ParameterBill {
    private String ngayLapHD;
    private String tenNV;
    private String tenKH;
    private String SDT;
    private double tongTien;
    private int giamTru;
    private double thanhToan;
    private int diemThuong;
    private String maHD;
    private InputStream qrcode;
    private List<FieldBill> listFB;

    public ParameterBill() {
    }

    public ParameterBill(String ngayLapHD, String tenNV, String tenKH, String SDT, double tongTien, int giamTru, double thanhToan, int diemThuong, String maHD, InputStream qrcode, List<FieldBill> listFB) {
        this.ngayLapHD = ngayLapHD;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.tongTien = tongTien;
        this.giamTru = giamTru;
        this.thanhToan = thanhToan;
        this.diemThuong = diemThuong;
        this.maHD = maHD;
        this.qrcode = qrcode;
        this.listFB = listFB;
    }

    public String getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(String ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getGiamTru() {
        return giamTru;
    }

    public void setGiamTru(int giamTru) {
        this.giamTru = giamTru;
    }

    public double getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(double thanhToan) {
        this.thanhToan = thanhToan;
    }

    public int getDiemThuong() {
        return diemThuong;
    }

    public void setDiemThuong(int diemThuong) {
        this.diemThuong = diemThuong;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public InputStream getQrcode() {
        return qrcode;
    }

    public void setQrcode(InputStream qrcode) {
        this.qrcode = qrcode;
    }

    public List<FieldBill> getListFB() {
        return listFB;
    }

    public void setListFB(List<FieldBill> listFB) {
        this.listFB = listFB;
    }
    
    
    
    
}
