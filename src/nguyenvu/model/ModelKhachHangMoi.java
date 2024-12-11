/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.model;

import java.time.LocalDate;

/**
 *
 * @author HP
 */
public class ModelKhachHangMoi {
    private LocalDate date;
    private int soLuong;

    public ModelKhachHangMoi() {
    }

    public ModelKhachHangMoi(LocalDate date, int soLuong) {
        this.date = date;
        this.soLuong = soLuong;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    
}
