package com.mathguijava;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class FinishPanel {
    private static final int SCR_W = 700;
    private static final int SCR_H = 500;
    private final JFrame frame;
    private JPanel mainPanel;
    private int score;
    private int problemAmount;
    private Color backgroundCol;

    public FinishPanel(JFrame frame, int score, int problemAmount) {
        this.frame = frame;
        this.score = score;
        this.problemAmount = problemAmount;
        setUpBackgroundCol();
        setUpFinishPanel();
    }
    private void cleanFrame() {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
    }
    private void setUpFinishPanel() {
        cleanFrame();

        frame.setSize(SCR_W, SCR_H);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel background = new JPanel();
        background.setBackground(backgroundCol);
        background.
                setBorder(BorderFactory.
                    createEmptyBorder(150, 50, 100, 50));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.
                createCompoundBorder(
                        BorderFactory
                                .createRaisedBevelBorder(),
                        BorderFactory.
                                createBevelBorder(BevelBorder.RAISED)
                ));
        Font font = new Font("TimesRoman", Font.ITALIC, 40);

        JLabel scoreLabel = new JLabel("Your score");
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setFocusable(false);
        scoreLabel.setFont(font);

        JTextField scoreField = new JTextField();
        scoreField.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreField.setHorizontalAlignment(JTextField.CENTER);
        scoreField.setText(score + "/" + problemAmount);
        scoreField.setFocusable(false);
        scoreField.setEditable(false);
        scoreField.setBorder(null);
        scoreField.setFont(font);

        mainPanel.add(scoreLabel);
        mainPanel.add(scoreField);
        background.add(BorderLayout.CENTER, mainPanel);
        frame.getContentPane().add(background);
        frame.setVisible(true);
    }

    private void setUpBackgroundCol() {
        float percent = ((float) score/problemAmount) * 100;
        if (percent <= 30) {
            backgroundCol = new Color(255, 102, 102);
        } else if (percent <= 60) {
            backgroundCol = new Color(255, 255, 204);
        } else if (percent <= 100) {
            backgroundCol = new Color(204, 255, 204);
        }
    }
}
