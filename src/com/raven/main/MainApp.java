package com.raven.main;
import javax.swing.JFrame;

import gui.barChar_ThongKe;

public class MainApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new barChar_ThongKe());
        frame.setSize(1056, 768);  // Kích thước phù hợp cho hiển thị
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
