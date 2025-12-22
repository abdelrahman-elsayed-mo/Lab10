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
import Control.Game;
import Exceptions.NotFoundException;
import Exceptions.InvalidSolutionException;
import Exceptions.InvalidGameException;
import java.io.IOException;

public interface Controllable {

    Catalog getCatalog();

    int[][] getGame(char level) throws NotFoundException;

    void driveGames(int[][] source) throws InvalidSolutionException;

    boolean[][] verifyGame(int[][] game);

        
        int[][] solveGame(Game game) throws InvalidGameException;

    void logUserAction(UserAction userAction) throws IOException;
}
