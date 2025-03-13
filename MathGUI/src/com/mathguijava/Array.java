package com.mathguijava;

import javax.swing.JTextField;
import java.util.Objects;
import java.util.Scanner;

public class Array {
    private static final int EASY_LEVEL_SUM_SUBTRACT = 100;
    private static final int MEDIUM_LEVEL_SUM_SUBTRACT = 250;
    private static final int HARD_LEVEL_SUM_SUBTRACT = 500;
    private static final int EASY_LEVEL_MUL_DIV = 10;
    private static final int MEDIUM_LEVEL_MUL_DIV = 30;
    private static final int HARD_LEVEL_MUL_DIV = 50;
    private static final int MIN = 2;
    public static String[] createProblems(int amount, String difficulty) {
        String[] problems;
        if (difficulty.equalsIgnoreCase("easy")) {
            problems = easyArray(amount);
        } else if (difficulty.equalsIgnoreCase("medium")) {
            problems = mediumArray(amount);
        } else {
            problems = hardArray(amount);
        }
        return problems;
    }
    private static String[] easyArray(int amount) {
        String[] problems = new String[amount];
        for (int i = 0; i < amount; i++) {
            int rand = (int) Math.ceil(Math.random() * 3);
            switch (rand) {
                case 0:
                    problems[i] = sum(EASY_LEVEL_SUM_SUBTRACT);
                    break;
                case 1:
                    problems[i] = subtract(EASY_LEVEL_SUM_SUBTRACT);
                    break;
                case 2:
                    problems[i] = multiply(EASY_LEVEL_MUL_DIV);
                    break;
                case 3:
                    problems[i] = divide(EASY_LEVEL_MUL_DIV);
                    break;
            }
        }
        return problems;
    }
    private static String[] mediumArray(int amount) {
        String[] problems = new String[amount];
        for (int i = 0; i < amount; i++) {
            int rand = (int) Math.ceil(Math.random() * 3);
            switch (rand) {
                case 0:
                    problems[i] = sum(MEDIUM_LEVEL_SUM_SUBTRACT);
                    break;
                case 1:
                    problems[i] = subtract(MEDIUM_LEVEL_SUM_SUBTRACT);
                    break;
                case 2:
                    problems[i] = multiply(MEDIUM_LEVEL_MUL_DIV);
                    break;
                case 3:
                    problems[i] = divide(MEDIUM_LEVEL_MUL_DIV);
                    break;
            }
        }
        return problems;
    }
    private static String[] hardArray(int amount) {
        String[] problems = new String[amount];
        for (int i = 0; i < amount; i++) {
            int rand = (int) Math.ceil(Math.random() * 3);
            switch (rand) {
                case 0:
                    problems[i] = sum(HARD_LEVEL_SUM_SUBTRACT);
                    break;
                case 1:
                    problems[i] = subtract(HARD_LEVEL_SUM_SUBTRACT);
                    break;
                case 2:
                    problems[i] = multiply(HARD_LEVEL_MUL_DIV);
                    break;
                case 3:
                    problems[i] = divide(HARD_LEVEL_MUL_DIV);
                    break;
            }
        }
        return problems;
    }
    private static String sum(int threshold) {
        String problem;
        int num1 = (int)(Math.random() * threshold);
        int num2 = (int)(Math.random() * threshold);
        problem = num1 + " + " + num2;
        return problem;
    }
    private static String subtract(int threshold) {
        String problem;
        int num1 = (int)(Math.random() * threshold);
        int num2 = (int)(Math.random() * threshold);

        problem = num1 + " - " + num2;
        if (num1 < num2) {
            problem = num2 + " - " + num1;
        }
        return problem;
    }
    private static String multiply(int threshold) {
        String problem;
        int num1 = (int)(Math.random() * threshold) + MIN;
        int num2 = (int)(Math.random() * threshold) + MIN;
        problem = num1 + " * " + num2;
        return problem;
    }
    private static String divide(int threshold) {
        String problem;

        int num1 = (int)(Math.random() * threshold) + MIN;
        int num2 = (int)(Math.random() * threshold) + MIN;

        while (num1 % num2 != 0) {
            num1 = (int)(Math.random() * threshold) + MIN;
            num2 = (int)(Math.random() * threshold) + MIN;
            while (num1 == num2) {
                num2 = (int)(Math.random() * threshold) + MIN;
            }
        }
        problem = num1 + " / " + num2;
        return problem;
    }
    public static String[][] collectProblemsAnswers(String[] problems, JTextField[] answers) {
        String[][] set = new String[problems.length][2];
        for (int i = 0; i < problems.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    set[i][j] = problems[i];
                } else
                    set[i][j] = answers[i].getText();
            }
        }
        return set;
    }
    public static boolean checkCorrectness(String problem, JTextField user_answer) {
        int ans;
        int solution;

        if (Objects.isNull(user_answer)) {
            ans = 0;
        } else if (user_answer.getText().isEmpty()) {
            ans = -1;
        } else {
            ans = Integer.parseInt(user_answer.getText());
        }

        String[] symbols = new String[3];
        Scanner sc = new Scanner(problem);
        sc.useDelimiter(" ");
        int i = 0;
        while (sc.hasNext()) {
            symbols[i] = sc.next();
            i++;
        }
        switch (symbols[1]) {
            case "+":
                solution = Integer.parseInt(symbols[0]) + Integer.parseInt(symbols[2]);
                return solution == ans;
            case "-":
                solution = Integer.parseInt(symbols[0]) - Integer.parseInt(symbols[2]);
                return solution == ans;
            case "*":
                solution = Integer.parseInt(symbols[0]) * Integer.parseInt(symbols[2]);
                return solution == ans;
            default:
                solution = Integer.parseInt(symbols[0]) / Integer.parseInt(symbols[2]);
                return solution == ans;
        }
    }
}