/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author DELL
 */

import Control.Catalog;
import Control.Difficulty;
import Control.Game;
import Control.Viewable;
import Exceptions.InvalidGameException;
import Exceptions.NotFoundException;
import Exceptions.InvalidSolutionException;
import java.io.IOException;

public class ViewFacade implements Controllable {

    private final Viewable controller;

    public ViewFacade(Viewable controller) {
        this.controller = controller;
    }

    // ------------------ Startup ------------------

    @Override
    public Catalog getCatalog() {
        return controller.getCatalog();
    }

    // ------------------ Load Game ------------------

    @Override
    public int[][] getGame(char level) throws NotFoundException {
        Difficulty diff;

        switch (level) {
            case 'e':
                diff = Difficulty.EASY;
                break;
            case 'm':
                diff = Difficulty.MEDIUM;
                break;
            case 'h':
                diff = Difficulty.HARD;
                break;
            case 'i': 
            diff = null; 
            break;
            default:
                throw new IllegalArgumentException("Invalid difficulty");
        }

        Game game = controller.getGame(diff);
        return game.getBoard(); // مهم: Game لازم يكون عنده getBoard()
    }

    // ------------------ Drive Games ------------------

    @Override
    public void driveGames(int[][] source) throws InvalidSolutionException {
        Game solvedGame = new Game(source);
        controller.driveGames(solvedGame);
    }

    // ------------------ Verify ------------------

    @Override
    public boolean[][] verifyGame(int[][] board) {
        Game game = new Game(board);
        String result = controller.verifyGame(game);

        boolean[][] validity = new boolean[9][9];

        // default: كله صح
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                validity[i][j] = true;

        // لو الـ controller بيرجع duplicate info في String
        // هنا انت بتمسك الخلايا الغلط وتحط false
        // (هتظبط الجزء ده حسب String اللى صحابك عاملينه)

        return validity;
    }

    // ------------------ Solve ------------------

    @Override
    public int[][] solveGame(int[][] board) throws InvalidGameException {
        Game game = new Game(board);
        int[] solution = controller.solveGame(game);

        // solution: [x1,y1,val1, x2,y2,val2, ...]
        int n = solution.length / 3;
        int[][] result = new int[n][3];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            result[i][0] = solution[idx++];
            result[i][1] = solution[idx++];
            result[i][2] = solution[idx++];
        }

        return result;
    }

    // ------------------ Logging ------------------

    @Override
    public void logUserAction(UserAction action) throws IOException {
        controller.logUserAction(action.toString());
    }
}
