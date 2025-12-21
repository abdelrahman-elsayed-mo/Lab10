/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author Abdelrahman Elsayed
 */

import Exceptions.InvalidGameException;
import Exceptions.InvalidSolutionException;
import Exceptions.NotFoundException;
import java.io.IOException;


public interface Viewable {
   
    Catalog getCatalog();
    Game getGame(Difficulty level) throws NotFoundException;
    String verifyGame(Game game);
    int[] solveGame(Game game) throws InvalidGameException;
    void driveGames(Game sourceGame) throws InvalidSolutionException;
    void logUserAction(String userAction) throws IOException;

    
}
