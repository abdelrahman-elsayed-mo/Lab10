/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author DELL
 */

import java.util.Stack;

public class UndoManager {
    private final Stack<UserAction> history = new Stack<>();

    public void pushAction(UserAction action) {
        history.push(action);
    }

    public UserAction popAction() {
        if (history.isEmpty()) return null;
        return history.pop();
    }

    public boolean canUndo() {
        return !history.isEmpty();
    }
}
