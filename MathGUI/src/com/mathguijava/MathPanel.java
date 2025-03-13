package com.mathguijava;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MathPanel {
    private final JFrame frame;
    private String difficulty;
    private static final int SCR_W = 900;
    private static final int SCR_H = 700;
    private static final int MAX_PROBLEM_AMOUNT_PANEL = 7;
    private static final int MAX_PROBLEMS_PANEL_AMOUNT = 3;
    private static final Font FONT = new Font("Serif", Font.BOLD, 20);
    private static final Color COLOR = new Color(168,168,168);
    private static int amountOfProblemsLeft;
    private static int amountOfProblemColumnsLeft;
    private JPanel background;
    private JPanel mainPanel;
    private JPanel problemsPanel;
    private JPanel buttonPanel;
    private String[] problems;
    private JTextField[] answers;

    public MathPanel(JFrame frame, int amountOfProblems, String difficulty) {
        this.frame = frame;
        amountOfProblemsLeft = amountOfProblems;
        this.difficulty = difficulty;
        setUpMainPanel();
    }
    private void setUpMainPanel() {
        cleanFrame();
        frame.setSize(SCR_W, SCR_H);
        frame.setLocationRelativeTo(null);

        BorderLayout layout = new BorderLayout();
        background = new JPanel(layout);
        background.
                setBorder(BorderFactory.
                        createEmptyBorder(50, 75, 100, 75));
        background.setBackground(COLOR);

        //Size of mainPanel: width = 750, height = 550
        mainPanel = new JPanel();
        mainPanel.
                setBorder(BorderFactory.
                        createEmptyBorder(5, 50, 45, 50));

        amountOfProblemColumnsLeft = (int) Math.ceil((double) amountOfProblemsLeft / MAX_PROBLEM_AMOUNT_PANEL);
        problems = Array.createProblems(amountOfProblemsLeft, difficulty);
        answers = new JTextField[amountOfProblemsLeft];

        setUpProblemsPanel();

        background.add(mainPanel);
        frame.getContentPane().add(background);
    }
    private void cleanFrame() {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
    }
    public void setUpProblemsPanel() {
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();

        //Size of mathPanel: width = 600, height = 500
        problemsPanel = new JPanel();
        problemsPanel.setForeground(COLOR);

        buttonPanel = new JPanel();
        buttonPanel.setForeground(COLOR);

        createButtonPanel();
        createProblemColumns();
        mainPanel.add(problemsPanel);
        mainPanel.add(buttonPanel);
    }
    private void createProblemColumns() {
        problemsPanel.removeAll();
        problemsPanel.revalidate();
        problemsPanel.repaint();
        if (amountOfProblemColumnsLeft >= MAX_PROBLEMS_PANEL_AMOUNT) {
            for (int j = 0; j < MAX_PROBLEMS_PANEL_AMOUNT; j++) {
                Dimension dim = new Dimension(10 ,0);
                problemsPanel.add(Box.createRigidArea(dim));
                problemsPanel.add(createProblemPanel());
                problemsPanel.add(Box.createRigidArea(dim));
            }
            amountOfProblemColumnsLeft -= MAX_PROBLEMS_PANEL_AMOUNT;
        } else if (amountOfProblemColumnsLeft == 2) {
            for (int j = 0; j < (MAX_PROBLEMS_PANEL_AMOUNT - 1); j++) {
                Dimension dim = new Dimension(75 ,400);
                problemsPanel.add(Box.createRigidArea(dim));
                problemsPanel.add(createProblemPanel());
                problemsPanel.add(Box.createRigidArea(dim));
            }
            amountOfProblemColumnsLeft -= (MAX_PROBLEMS_PANEL_AMOUNT - 1);
        } else if (amountOfProblemColumnsLeft == 1) {
            Dimension dim = new Dimension(200 ,400);
            problemsPanel.add(Box.createRigidArea(dim));
            problemsPanel.add(createProblemPanel());
            problemsPanel.add(Box.createRigidArea(dim));
            amountOfProblemColumnsLeft -= 1;
        }
    }
    private Component createProblemPanel() {
        Box problemColumn = new Box(BoxLayout.Y_AXIS);
        problemColumn.setBackground(COLOR);
        for (int j = 0; j < MAX_PROBLEM_AMOUNT_PANEL; j++) {
            if (amountOfProblemsLeft == 0) {
                break;
            } else {
                ProblemPanel problemPanel = new ProblemPanel();
                problemColumn.add(problemPanel.setUpProblemPanel(problems[(amountOfProblemsLeft - 1)]));
                problemColumn.add(Box.createRigidArea(new Dimension(0, 5)));
                answers[(amountOfProblemsLeft - 1)] = problemPanel.getAnswer();
                amountOfProblemsLeft--;
            }
        }
        return problemColumn;
    }
    private void createButtonPanel() {
        buttonPanel.removeAll();
        buttonPanel.revalidate();
        buttonPanel.repaint();

        JButton stop = new JButton("Stop");
        stop.addActionListener(new StopListener());
        stop.setFont(FONT);

        if (amountOfProblemColumnsLeft <= 3) {
            JButton finishButton = new JButton("Finish");
            finishButton.addActionListener(new FinishListener());
            finishButton.setFont(FONT);
            buttonPanel.add(finishButton);
        } else {
            buttonPanel.add(stop);
            buttonPanel.add(Box.createRigidArea(new Dimension(400,0)));

            JButton nextButton = new JButton("Next");
            nextButton.addActionListener(new NextListener());
            nextButton.setFont(FONT);
            buttonPanel.add(nextButton);
        }
    }
    private int countScore() {
        int score = 0;
        for (int i = 0; i < problems.length; i++) {
            boolean answer = Array.checkCorrectness(problems[i], answers[i]);
            if (answer) {
                score++;
            }
        }
        return score;
    }
    public class StopListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            int score = countScore();
            new FinishPanel(frame, score, problems.length);
        }
    }
    public class NextListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            setUpProblemsPanel();
        }
    }
    public class FinishListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            int score = countScore();
            new FinishPanel(frame, score, problems.length);
        }
    }
}
