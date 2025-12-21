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
public class RowChecker extends AbstractChecker {
     public RowChecker(int[][] board) {
        super(board);
    }

    public List<int[]> check() {
        List<int[]> allInvalid = new ArrayList<>();
        
        for (int row = 0; row < 9; row++) {
            
            int[] rowArray = new int[9];
            for (int col = 0; col < 9; col++) {
                rowArray[col] = board[row][col];
            }
         
            List<int[]> rowDuplicates = findDuplicates(rowArray, row, 0, true);
            allInvalid.addAll(rowDuplicates);
        }
        
        return allInvalid;
    }
   
    public List<int[]> checkRow(int rowIndex) {
        int[] rowArray = new int[9];
        for (int col = 0; col < 9; col++) {
            rowArray[col] = board[rowIndex][col];
        }
        return findDuplicates(rowArray, rowIndex, 0, true);
    }
}
