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
public class BoxChecker extends AbstractChecker{
    
    public BoxChecker(int[][] board) {
        super(board);
    }
    
    public List<int[]> check() {
        List<int[]> allInvalid = new ArrayList<>();
        
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                List<int[]> boxDuplicates = checkBox(boxRow, boxCol);
                allInvalid.addAll(boxDuplicates);
            }
        }
        
        return allInvalid;
    }
    
    public List<int[]> checkBox(int boxRow, int boxCol) {
        return findDuplicatesInBox(boxRow, boxCol);
    }
    
}
