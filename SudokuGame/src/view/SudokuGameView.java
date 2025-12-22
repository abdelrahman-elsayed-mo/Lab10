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
import Control.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SudokuGameView extends JFrame {

    private final Controllable facade;
    private int[][] board = new int[9][9];
    private final UndoManager undoManager = new UndoManager();
    private JTextField[][] cells = new JTextField[9][9];
    private JButton verifyBtn, solveBtn, undoBtn;

    public SudokuGameView(Controllable facade) {
        this.facade = facade;
        initUI();
        startupFlow();
    }

    private void initUI() {
        setTitle("Sudoku Game");
        setSize(550, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                final int r = i, c = j;

               
                cells[i][j].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        handleInput(r, c);
                    }
                });
                boardPanel.add(cells[i][j]);
            }
        }

        verifyBtn = new JButton("Verify");
        solveBtn = new JButton("Solve");
        undoBtn = new JButton("Undo");

        verifyBtn.addActionListener(e -> {
            syncBoardFromUI();
            highlightErrors(facade.verifyGame(board));
        });

        undoBtn.addActionListener(e -> {
            if (undoManager.canUndo()) {
                UserAction last = undoManager.popAction();
                board[last.getX()][last.getY()] = last.getPrev();
                syncUIWithBoard();
            }
        });

        solveBtn.addActionListener(e -> {
    try {
        syncBoardFromUI();

        Game g = new Game(board);

        int[][] moves = facade.solveGame(g);

        for (int[] m : moves) {
            board[m[0]][m[1]] = m[2];
        }

       
        syncUIWithBoard();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(
            this,
            "Solver works only when empty cells are between 1 and 5",
            "Solver Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
});


        JPanel btnPanel = new JPanel();
        btnPanel.add(verifyBtn); btnPanel.add(solveBtn); btnPanel.add(undoBtn);

        add(boardPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startupFlow() {
        try {
            Catalog cat = facade.getCatalog();
            if (cat.hasCurrentGame()) board = facade.getGame('i');
            else if (cat.hasAllModes()) board = facade.getGame(askDifficulty());
            else {
                facade.driveGames(getInitialBoard());
                board = facade.getGame('e');
            }
            syncUIWithBoard();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Loading Error: " + e.getMessage());
        }
    }

    private void handleInput(int r, int c) {
        try {
            String text = cells[r][c].getText().trim();
            int newVal = text.isEmpty() ? 0 : Integer.parseInt(text);
            if (board[r][c] != newVal) {
                undoManager.pushAction(new UserAction(r, c, newVal, board[r][c]));
                board[r][c] = newVal;
                facade.logUserAction(new UserAction(r, c, newVal, board[r][c]));
            }
        } catch (Exception e) {
            cells[r][c].setText("");
        }
        updateSolveBtnState();
    }

    private void syncBoardFromUI() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String t = cells[i][j].getText().trim();
                board[i][j] = t.isEmpty() ? 0 : Integer.parseInt(t);
            }
        }
    }

    private void syncUIWithBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setText(board[i][j] == 0 ? "" : String.valueOf(board[i][j]));
                cells[i][j].setBackground(Color.WHITE);
            }
        }
        solveBtn.setEnabled(countZeros() <= 5 && countZeros() > 0);

    }

    private int countZeros() {
        int count = 0;
        for (int[] row : board) for (int cell : row) if (cell == 0) count++;
        return count;
    }

    private void updateSolveBtnState() {
        int zeros = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) zeros++;
            }
        }
        solveBtn.setEnabled(zeros <= 5 && zeros > 0);

    }

    private void highlightErrors(boolean[][] validity) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setBackground(validity[i][j] ? Color.WHITE : Color.PINK);
            }
        }
    }

    private char askDifficulty() {
        String[] options = {"Easy", "Medium", "Hard"};
        int res = JOptionPane.showOptionDialog(null, "Select Difficulty", "New Game", 0, 3, null, options, options[0]);
        return res == 1 ? 'm' : res == 2 ? 'h' : 'e';
    }

    private int[][] getInitialBoard() {
        return new int[][]{{5,3,4,6,7,8,9,1,2},{6,7,2,1,9,5,3,4,8},{1,9,8,3,4,2,5,6,7},
                           {8,5,9,7,6,1,4,2,3},{4,2,6,8,5,3,7,9,1},{7,1,3,9,2,4,8,5,6},
                           {9,6,1,5,3,7,2,8,4},{2,8,7,4,1,9,6,3,5},{3,4,5,2,8,6,1,7,9}};
    }
}