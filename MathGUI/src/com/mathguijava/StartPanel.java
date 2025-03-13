package com.mathguijava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class StartPanel {
    private JFrame frame;
    private static final String[] LEVELS = {"Easy", "Medium", "Hard"};
    private final Color red;
    private ArrayList<JButton> buttons;
    private String difficulty;
    private JPanel textFieldPanel;
    private JTextField textField;
    private Box buttonBox;
    private int problemAmount = 0;
    public StartPanel(JFrame frame) {
        this.frame = frame;
        red = new Color(255, 50, 50);
    }
    public Component setUpStartPanel() {
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBackground(new Color(199, 199, 199));
        background.
                setBorder(BorderFactory.
                        createEmptyBorder(110, 200, 110, 200));

        //Size of main panel: w = 250, h = 250
        JPanel mainPanel = new JPanel();

        JLabel difficulty = new JLabel("Difficulty:");
        buttonBox = new Box(BoxLayout.Y_AXIS);
        buttons = new ArrayList<>();

        for (String level:LEVELS) {
            JButton button = new JButton(level);
            button.addActionListener(new DifficultyListener());
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            if (level.equals("Medium")) {
                buttonBox.add(Box.createRigidArea(new Dimension(0, 10)));
                buttonBox.add(button);
                buttonBox.add(Box.createRigidArea(new Dimension(0, 10)));
            } else {
                buttonBox.add(button);
                buttons.add(button);
            }
        }

        JLabel amount = new JLabel("Amount of problems:");
        textFieldPanel = new JPanel();
        textField = new JTextField("", 5);
        textField.addActionListener(new StartListener());
        textField.setHorizontalAlignment(JTextField.CENTER);
        textFieldPanel.add(textField);

        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(new StartListener());

        mainPanel.add(difficulty);
        mainPanel.add(buttonBox);
        mainPanel.add(amount);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        mainPanel.add(textFieldPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        mainPanel.add(startButton);
        background.add(mainPanel);

        return background;
    }
    public class DifficultyListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            buttonBox.setBorder(null);
            difficulty = ev.getActionCommand();
        }
    }
    public class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            if (Objects.isNull(difficulty) && textField.getText().isEmpty()) {
                buttonBox.
                        setBorder(BorderFactory.
                                createLineBorder(red));
                textFieldPanel.
                        setBorder(BorderFactory.
                                createLineBorder(red));
            } else if (Objects.isNull(difficulty)) {
                buttonBox.
                        setBorder(BorderFactory.
                                createLineBorder(red));
            } else if (textField.getText().isEmpty()) {
                textFieldPanel.
                        setBorder(BorderFactory.
                                createLineBorder(red));
            } else if (!textField.getText().isEmpty()) {
                textFieldPanel.setBorder(null);
                problemAmount = Integer.parseInt(textField.getText());
                new MathPanel(frame, problemAmount, difficulty);
            }
        }
    }
}