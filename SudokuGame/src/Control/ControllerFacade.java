/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Exceptions.InvalidGameException;
import Exceptions.InvalidSolutionException;
import Exceptions.NotFoundException;
import Generator.GameGenerator;
import Solver.SudokuSolver;
import Verifier.SudokuVerifier;
import java.io.IOException;

/**
 *
 * @author Abdelrahman Elsayed
 */
public class ControllerFacade implements Viewable {

    private final SudokuVerifier verifier;
    private final GameGenerator generator;
    private final Storage storage;
    private  SudokuSolver solver;

    private Game[] games;

    public ControllerFacade() {
        this.verifier = new SudokuVerifier();
        this.generator = new GameGenerator();
        this.storage = new Storage();
        this.games = new Game[3];

    }

    @Override
    public Catalog getCatalog() {
        boolean hasUnfinished = storage.hasUnfinishedGame();
        boolean hasEasy = storage.hasGame(Difficulty.EASY);
        boolean hasMedium = storage.hasGame(Difficulty.MEDIUM);
        boolean hasHard = storage.hasGame(Difficulty.HARD);
        boolean allModesExist = hasEasy && hasMedium && hasHard;

        return new Catalog(hasUnfinished, allModesExist);
    }

    @Override

    public Game getGame(Difficulty level) throws NotFoundException {
        try {
            int x = getIndexLevel(level);
            if(games[x]!= null){
                Game game = games[x];
                
                return game;
            }
            
            Game game = storage.loadGame(level);
            storage.saveUnfinishedGame(game);
            return game;
        } catch (IOException e) {
            throw new NotFoundException("Failed to load game");
        }
    }
    public void driveGames(Game game) throws InvalidSolutionException {
      
        String result = verifier.verify(game);
        if (!result.equals("valid")) {
            throw new InvalidSolutionException("Source game is not valid");
        }
        
        games = generator.generateGame(game);
 
        try {
            storage.saveGame(Difficulty.EASY, games[0]);
            storage.saveGame(Difficulty.MEDIUM, games[1]);
            storage.saveGame(Difficulty.HARD, games[2]);
            
            storage.saveUnfinishedGame(games[0]);
            
        } catch (IOException e) {
            throw new InvalidSolutionException("Failed to save games");
        }
    }

     @Override
    public String verifyGame(Game game) {
        return verifier.verify(game);
    }
    
    @Override
    public int[] solveGame(Game game) throws InvalidGameException {
        if (game.countEmptyCells() != 5) {
            throw new InvalidGameException("Need exactly 5 empty cells");
        }
        solver = new SudokuSolver(game.getBoard());
        return solver.solve();
    }
    
    @Override
    public void logUserAction(String userAction) throws IOException {
        storage.logAction(userAction);
    }
    
    
    private int getIndexLevel(Difficulty level) {
        switch (level) {
            case EASY:
                return 0;
            case MEDIUM:
                return 1;
            case HARD:
                return 2;
            default:
                return 0;
        }
    }

   
}
