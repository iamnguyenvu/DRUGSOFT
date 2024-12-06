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
    private String role;
    private String tenNV;
    private String type;
    private double thanhTien;
    private LocalDateTime date;

    public ModelSellTransaction(String role, String tenNV, String type, double thanhTien, LocalDateTime date) {
        this.role = role;
        this.tenNV = tenNV;
        this.type = type;
        this.thanhTien = thanhTien;
        this.date = date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    
    
}
