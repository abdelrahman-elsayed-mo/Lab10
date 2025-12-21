/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Verifier;

import Control.Game;
import java.util.*;

/**
 *
 * @author Abdelrahman Elsayed
 */
public class SudokuVerifier {
 
    
    public String verify(Game game) {
        int[][] board = game.getBoard();
        
        RowChecker rowChecker = new RowChecker(board);
        ColumnChecker columnChecker = new ColumnChecker(board);
        BoxChecker boxChecker = new BoxChecker(board);
        
        
        List<int[]> invalidPositions = new ArrayList<>();
        invalidPositions.addAll(rowChecker.check());
        invalidPositions.addAll(columnChecker.check());
        invalidPositions.addAll(boxChecker.check());
        
        invalidPositions = removeDuplicates(invalidPositions);
        
        if (!invalidPositions.isEmpty()) {
            return formatInvalidResult(invalidPositions);
        }
        
        if (isBoardComplete(board)) {
            return "valid";
        } else {
            return "incomplete";
        }
    }
    

    public boolean isCellValid(Game game, int row, int col) {
        int[][] board = game.getBoard();
        int value = board[row][col];
        if (value == 0) return true;
        
  
        RowChecker rowChecker = new RowChecker(board);
        ColumnChecker columnChecker = new ColumnChecker(board);
        BoxChecker boxChecker = new BoxChecker(board);
       
        List<int[]> rowInvalid = rowChecker.checkRow(row);
        for (int[] pos : rowInvalid) {
            if (pos[0] == row && pos[1] == col) return false;
        }
        
        List<int[]> colInvalid = columnChecker.checkColumn(col);
        for (int[] pos : colInvalid) {
            if (pos[0] == row && pos[1] == col) return false;
        }
        
        int boxRow = row / 3;
        int boxCol = col / 3;
        List<int[]> boxInvalid = boxChecker.checkBox(boxRow, boxCol);
        for (int[] pos : boxInvalid) {
            if (pos[0] == row && pos[1] == col) return false;
        }
        
        return true;
    }
    
    public boolean[][] getCellValidities(Game game) {
        boolean[][] validities = new boolean[9][9];
        int[][] board = game.getBoard();
        
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                validities[row][col] = isCellValid(game, row, col);
            }
        }
        
        return validities;
    }
    
    private List<int[]> removeDuplicates(List<int[]> positions) {
        Set<String> seen = new HashSet<>();
        List<int[]> unique = new ArrayList<>();
        
        for (int[] pos : positions) {
            String key = pos[0] + "," + pos[1];
            if (!seen.contains(key)) {
                seen.add(key);
                unique.add(pos);
            }
        }
        
        return unique;
    }
    
    private String formatInvalidResult(List<int[]> invalidPositions) {
        StringBuilder sb = new StringBuilder("invalid");
        for (int[] pos : invalidPositions) {
            sb.append(" ").append(pos[0]).append(",").append(pos[1]);
        }
        return sb.toString();
    }
    
    private boolean isBoardComplete(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}

