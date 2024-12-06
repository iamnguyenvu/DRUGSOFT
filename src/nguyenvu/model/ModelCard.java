/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nguyenvu.model;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.Icon;

/**
 *
 * @author HP
 */
public class ModelCard {
    private String title;
    private double value;
    private FlatSVGIcon iconPath;

    public ModelCard() {
    }

    public ModelCard(String title, double value, FlatSVGIcon iconPath) {
        this.title = title;
        this.value = value;
        this.iconPath = iconPath;
    }

    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public FlatSVGIcon getIconPath() {
        return iconPath;
    }

    public void setIconPath(FlatSVGIcon iconPath) {
        this.iconPath = iconPath;
    }

    
}
