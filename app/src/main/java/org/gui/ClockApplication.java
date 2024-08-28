package org.gui;

import javax.swing.*;
import java.util.Date;

public class ClockApplication {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Time");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 100);
        frame.add(new JLabel(String.format("%tT", new Date())));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
