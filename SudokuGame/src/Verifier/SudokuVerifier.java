/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Verifier;


/**
 *
 * @author Abdelrahman Elsayed
 */

import Control.Game;
import java.util.*;

public class SudokuVerifier {
    
    public String verify(Game game) {
        int[][] board = game.getBoard();
        List<int[]> invalidPositions = new ArrayList<>();
        
        invalidPositions.addAll(new RowChecker(board).check());
        invalidPositions.addAll(new ColumnChecker(board).check());
        invalidPositions.addAll(new BoxChecker(board).check());
        
        invalidPositions = removeDuplicates(invalidPositions);
        
        if (!invalidPositions.isEmpty()) {
            return formatInvalidResult(invalidPositions);
        }
        
        return isBoardComplete(board) ? "valid" : "incomplete";
    }

    private List<int[]> removeDuplicates(List<int[]> positions) {
        Set<String> seen = new HashSet<>();
        List<int[]> unique = new ArrayList<>();
        for (int[] pos : positions) {
            if (seen.add(pos[0] + "," + pos[1])) unique.add(pos);
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
        for (int[] row : board) for (int val : row) if (val == 0) return false;
        return true;
    }
}

