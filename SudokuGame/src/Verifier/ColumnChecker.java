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
public class ColumnChecker extends AbstractChecker {
    
     public ColumnChecker(int[][] board) {
        super(board);
    }
    

    public List<int[]> check() {
        List<int[]> allInvalid = new ArrayList<>();
        
        for (int col = 0; col < 9; col++) {
           
            int[] colArray = new int[9];
            for (int row = 0; row < 9; row++) {
                colArray[row] = board[row][col];
            }
            
            List<int[]> colDuplicates = findDuplicates(colArray, 0, col, false);
            allInvalid.addAll(colDuplicates);
        }
        
        return allInvalid;
    }
    
    public List<int[]> checkColumn(int colIndex) {
        int[] colArray = new int[9];
        for (int row = 0; row < 9; row++) {
            colArray[row] = board[row][colIndex];
        }
        return findDuplicates(colArray, 0, colIndex, false);
    }
}
