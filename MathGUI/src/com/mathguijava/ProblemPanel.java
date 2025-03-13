package com.mathguijava;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class ProblemPanel {
    private JTextField problem;
    private JTextField answer;
    public JTextField getProblem() {
        return problem;
    }
    public JTextField getAnswer() {
        return answer;
    }
    public Component setUpProblemPanel(String mathProblem) {
        JPanel problemPanel = new JPanel();
        problemPanel.
                setBorder(BorderFactory.
                    createCompoundBorder(
                        BorderFactory
                                .createRaisedBevelBorder(),
                        BorderFactory.
                                createBevelBorder(BevelBorder.RAISED)
                    )
                );

        Font font = new Font("Serif", Font.BOLD, 24);
        problem = new JTextField();
        problem.setText(mathProblem);
        problem.setHorizontalAlignment(JTextField.CENTER);
        problem.setEditable(false);
        problem.setBorder(null);
        problem.setFont(font);

        JTextField equal = new JTextField();
        equal.setEditable(false);
        equal.setBorder(null);
        equal.setFont(font);
        equal.setText("=");

        answer = new JTextField(2);
        answer.setHorizontalAlignment(JTextField.CENTER);
        answer.setFont(font);

        problemPanel.add(problem);
        problemPanel.add(equal);
        problemPanel.add(answer);

        return problemPanel;
    }

}
