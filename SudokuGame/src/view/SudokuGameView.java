/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author DELL
 */

import Control.Catalog;
import Exceptions.NotFoundException;
import Exceptions.InvalidSolutionException;
import Exceptions.InvalidGameException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SudokuGameView extends JFrame {

    private final Controllable facade;
    private int[][] board;

    private JButton verifyBtn;
    private JButton solveBtn;
    private JButton undoBtn;

    public SudokuGameView(Controllable facade) {
        this.facade = facade;
        initUI();
        startupFlow();
    }

    // ---------------- UI ----------------

    private void initUI() {
        setTitle("Sudoku");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        verifyBtn = new JButton("Verify");
        solveBtn = new JButton("Solve");
        undoBtn = new JButton("Undo");

        solveBtn.setEnabled(false);

        JPanel buttons = new JPanel();
        buttons.add(verifyBtn);
        buttons.add(solveBtn);
        buttons.add(undoBtn);

        add(buttons, BorderLayout.SOUTH);

        attachHandlers();
        setVisible(true);
    }

    // ---------------- Startup ----------------

    private void startupFlow() {
        Catalog c = facade.getCatalog();

        try {
            if (c.hasCurrentGame()) {
                board = facade.getGame('i'); // incomplete
            }
            else if (c.hasAllModes()) {
                char d = askDifficulty();
                board = facade.getGame(d);
            }
            else {
                int[][] solved = askSolvedBoard();
                facade.driveGames(solved);
                char d = askDifficulty();
                board = facade.getGame(d);
            }
        } catch (Exception e) {
            showError(e.getMessage());
        }
        
         if (board == null) {
        board = new int[9][9];
    }

        updateSolveState();
    }

    // ---------------- Handlers ----------------

    private void attachHandlers() {

        verifyBtn.addActionListener(e -> {
            boolean[][] result = facade.verifyGame(board);
            // display result (colors / dialog)
        });

        solveBtn.addActionListener(e -> {
            try {
                int[][] solution = facade.solveGame(board);
                applySolution(solution);
            } catch (InvalidGameException ex) {
                showError("Cannot solve");
            }
        });

        undoBtn.addActionListener(e -> {
            // undo logic (read last UserAction, revert board)
            updateSolveState();
        });
    }

    // ---------------- Logic ----------------

    private void updateSolveState() {
        solveBtn.setEnabled(countZeros(board) == 5);
    }

    private int countZeros(int[][] b) {
        if (b == null) return 0;
        int c = 0;
        for (int[] row : b)
            for (int v : row)
                if (v == 0) c++;
        return c;
    }

    private void applySolution(int[][] sol) {
        for (int[] s : sol)
            board[s[0]][s[1]] = s[2];
    }

    // ---------------- Helpers ----------------

    private char askDifficulty() {
        Object[] options = {"Easy", "Medium", "Hard"};
        int r = JOptionPane.showOptionDialog(this,
                "Choose difficulty",
                "Difficulty",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        return (r == 0) ? 'e' : (r == 1) ? 'm' : 'h';
    }

    private int[][] askSolvedBoard() {
          return new int[][] {
        {5,3,4,6,7,8,9,1,2},
        {6,7,2,1,9,5,3,4,8},
        {1,9,8,3,4,2,5,6,7},
        {8,5,9,7,6,1,4,2,3},
        {4,2,6,8,5,3,7,9,1},
        {7,1,3,9,2,4,8,5,6},
        {9,6,1,5,3,7,2,8,4},
        {2,8,7,4,1,9,6,3,5},
        {3,4,5,2,8,6,1,7,9}
    };
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
