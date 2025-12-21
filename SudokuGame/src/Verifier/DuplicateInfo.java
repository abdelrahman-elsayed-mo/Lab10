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
public class DuplicateInfo {
    private final int number;
    private final List<Integer> positions;
    
     public DuplicateInfo(int number) {
        this.number = number;
        this.positions = new ArrayList<>();
    }
    
    public void addPosition(int position) {
        positions.add(position);
    }
    
    public List<Integer> getPositions() {
        return positions;
    }
    
    public boolean hasDuplicates() {
        return positions.size() > 1;
    }
}
