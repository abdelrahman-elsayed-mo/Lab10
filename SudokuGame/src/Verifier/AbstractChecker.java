/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Verifier;

import java.util.*;

/**
 *
 * @author Abdelrahman Elsayed
 */
public class AbstractChecker  {
    int[][] board;

    public AbstractChecker(int[][] board) {
        this.board = board;
    }
    
    public List<int[]> findDuplicates(int[] arr, int rowOffset, int colOffset, boolean isRow) {
        List<int[]> duplicates = new ArrayList<>();
        Map<Integer, List<Integer>> valuePositions = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            if (value != 0) {
                valuePositions.computeIfAbsent(value, k -> new ArrayList<>()).add(i);
            }
        }
    
        for (Map.Entry<Integer, List<Integer>> entry : valuePositions.entrySet()) {
            if (entry.getValue().size() > 1) {
                for (int position : entry.getValue()) {
                    int row, col;
                    if (isRow) {
                        row = rowOffset;
                        col = position + colOffset;
                    } else {
                        row = position + rowOffset;
                        col = colOffset;
                    }
                    duplicates.add(new int[]{row, col});
                }
            }
        }
        
        return duplicates;
    }
    
     public List<int[]> findDuplicatesInBox(int boxRow, int boxCol) {
        List<int[]> duplicates = new ArrayList<>();
        Map<Integer, List<int[]>> valuePositions = new HashMap<>();
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int row = boxRow * 3 + i;
                int col = boxCol * 3 + j;
                int value = board[row][col];
                
                if (value != 0) {
                    valuePositions.computeIfAbsent(value, k -> new ArrayList<>())
                                 .add(new int[]{row, col});
                }
            }
        }
     
        for (List<int[]> positions : valuePositions.values()) {
            if (positions.size() > 1) {
                duplicates.addAll(positions);
            }
        }
        
        return duplicates;
    }
}
