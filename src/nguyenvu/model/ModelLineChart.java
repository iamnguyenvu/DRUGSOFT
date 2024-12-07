/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author HP
 */
public class ModelLineChart {
    private LocalDate ngay;
    private double doanhThu;
    private double chiPhi;
    private double thue;
    private int slDonBan;
    private int slDonDoi;
    private int slDonTra;
    private int tongSP;

    public ModelLineChart() {
    }

    public ModelLineChart(LocalDate ngay, double doanhThu, double chiPhi, double thue, int slDonBan, int slDonDoi, int slDonTra, int tongSP) {
        this.ngay = ngay;
        this.doanhThu = doanhThu;
        this.chiPhi = chiPhi;
        this.thue = thue;
        this.slDonBan = slDonBan;
        this.slDonDoi = slDonDoi;
        this.slDonTra = slDonTra;
        this.tongSP = tongSP;
    }

    public LocalDate getNgay() {
        return ngay;
    }

    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public double getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(double chiPhi) {
        this.chiPhi = chiPhi;
    }

    public double getThue() {
        return thue;
    }

    public void setThue(double thue) {
        this.thue = thue;
    }

    public int getSlDonBan() {
        return slDonBan;
    }

    public void setSlDonBan(int slDonBan) {
        this.slDonBan = slDonBan;
    }

    public int getSlDonDoi() {
        return slDonDoi;
    }

    public void setSlDonDoi(int slDonDoi) {
        this.slDonDoi = slDonDoi;
    }

    public int getSlDonTra() {
        return slDonTra;
    }

    public void setSlDonTra(int slDonTra) {
        this.slDonTra = slDonTra;
    }

    public int getTongSP() {
        return tongSP;
    }

    public void setTongSP(int tongSP) {
        this.tongSP = tongSP;
    }

}
