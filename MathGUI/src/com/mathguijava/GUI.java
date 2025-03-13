package com.mathguijava;

import javax.swing.*;

public class GUI {
    JFrame frame;
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.go();
    }
    public void go() {
        frame = new JFrame("First math GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 500);
        frame.setLocationRelativeTo(null);
        StartPanel startPanel = new StartPanel(frame);
        frame.getContentPane().add(startPanel.setUpStartPanel());
        frame.setResizable(false);
        frame.setVisible(true);
    }
}