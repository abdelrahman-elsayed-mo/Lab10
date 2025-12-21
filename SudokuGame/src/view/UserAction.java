/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author DELL
 */

public class UserAction {

    private final int x;
    private final int y;
    private final int value;
    private final int prev;

    public UserAction(int x, int y, int value, int prev) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.prev = prev;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    public int getPrev() {
        return prev;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + value + "," + prev + ")";
    }
}
