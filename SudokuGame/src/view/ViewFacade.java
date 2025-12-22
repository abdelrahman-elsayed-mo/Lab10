/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author DELL
 */


import Control.Game;
import Exceptions.InvalidGameException;
import Control.Catalog;
import Control.Difficulty;
import Control.Game;
import Control.Viewable;
import Exceptions.InvalidGameException;
import Exceptions.NotFoundException;
import Exceptions.InvalidSolutionException;
import java.io.IOException;
import java.util.Arrays;

public class ViewFacade implements Controllable {

    private final Viewable controller;

    public ViewFacade(Viewable controller) {
        this.controller = controller;
    }

    @Override
    public Catalog getCatalog() {
        return controller.getCatalog();
    }

    @Override
    public int[][] getGame(char level) throws NotFoundException {
        Difficulty diff = null;
        if (level == 'e') diff = Difficulty.EASY;
        else if (level == 'm') diff = Difficulty.MEDIUM;
        else if (level == 'h') diff = Difficulty.HARD;
        
        Game g = controller.getGame(diff);
        return g.getBoard();
    }

    @Override
    public void driveGames(int[][] source) throws InvalidSolutionException {
        controller.driveGames(new Game(source));
    }

    @Override
    public boolean[][] verifyGame(int[][] board) {
        String result = controller.verifyGame(new Game(board));
        boolean[][] validity = new boolean[9][9];
        for (int i = 0; i < 9; i++) Arrays.fill(validity[i], true);

        if (result != null && result.startsWith("invalid")) {
            String[] parts = result.split(" ");
            for (int i = 1; i < parts.length; i++) {
                try {
                    String[] coords = parts[i].split(",");
                    int r = Integer.parseInt(coords[0]);
                    int c = Integer.parseInt(coords[1]);
                    validity[r][c] = false;
                } catch (Exception e) {}
            }
        }
        return validity;
    }

    @Override
    public int[][] solveGame(Game game) throws InvalidGameException {
    int[] sol = controller.solveGame(game);
    
    int[][] currentBoard = game.copy().getBoard();

    if (sol == null || sol.length == 0) {
        throw new InvalidGameException("No solution found");
    }

    for (int i = 0; i < sol.length; i += 3) {
        if (i + 2 < sol.length) {
            int r = sol[i];
            int c = sol[i+1];
            int v = sol[i+2];
            currentBoard[r][c] = v;
        }
    }
    
    return currentBoard;
}

    @Override
    public void logUserAction(UserAction userAction) throws IOException {
        controller.logUserAction(userAction.toString());
    }
}