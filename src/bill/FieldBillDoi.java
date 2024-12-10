/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bill;

/**
 *
 * @author HP
 */
public class FieldBillDoi {

    private String tenSPDoi;
    private int soLuongDoi;
    private double donGiaDoi;
    private double thanhTienDoi;

    public FieldBillDoi() {
    }

    public FieldBillDoi(String tenSPDoi, int soLuongDoi, double donGiaDoi, double thanhTienDoi) {
        this.tenSPDoi = tenSPDoi;
        this.soLuongDoi = soLuongDoi;
        this.donGiaDoi = donGiaDoi;
        this.thanhTienDoi = thanhTienDoi;
    }

    public String getTenSPDoi() {
        return tenSPDoi;
    }

    public void setTenSPDoi(String tenSPDoi) {
        this.tenSPDoi = tenSPDoi;
    }

    public int getSoLuongDoi() {
        return soLuongDoi;
    }

    public void setSoLuongDoi(int soLuongDoi) {
        this.soLuongDoi = soLuongDoi;
    }

    public double getDonGiaDoi() {
        return donGiaDoi;
    }

    public void setDonGiaDoi(double donGiaDoi) {
        this.donGiaDoi = donGiaDoi;
    }

    public double getThanhTienDoi() {
        return thanhTienDoi;
    }

    public void setThanhTienDoi(double thanhTienDoi) {
        this.thanhTienDoi = thanhTienDoi;
    }

}
