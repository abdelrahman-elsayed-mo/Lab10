/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author Abdelrahman Elsayed
 */
public enum Difficulty {
    EASY(10), MEDIUM(20),HARD(25);

    private final int removedCells;

    Difficulty(int removedCells) {
        this.removedCells = removedCells;
    }

    public int getRemovedCells() {
        return removedCells;
    }
}
