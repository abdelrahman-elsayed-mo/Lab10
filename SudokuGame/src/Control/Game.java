/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author Abdelrahman Elsayed
 */
public class Game {
    private final int[][] board;

    public Game(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }

     public int getCell(int row, int col) {
        return board[row][col];
    }
    
    public void setCell(int row, int col, int value) {
        board[row][col] = value;
    }
    
    public boolean isCellEmpty(int row, int col) {
        return board[row][col] == 0;
    }
    
    public int countEmptyCells() {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public boolean isComplete() {
        return countEmptyCells() == 0;
    }
    
    
    
    public Game copy() {
        int[][] copyBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(board[i], 0, copyBoard[i], 0, 9);
        }
        return new Game(copyBoard);
    }
    
}
