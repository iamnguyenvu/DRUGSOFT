/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.model;

/**
 *
 * @author HP
 */
public class ModelDoanhThu {
    private double doanhThu;
    private int soGiaoDich;

    public ModelDoanhThu(double doanhThu, int soGiaoDich) {
        this.doanhThu = doanhThu;
        this.soGiaoDich = soGiaoDich;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public int getSoGiaoDich() {
        return soGiaoDich;
    }

    public void setSoGiaoDich(int soGiaoDich) {
        this.soGiaoDich = soGiaoDich;
    }
    
    
    
}
