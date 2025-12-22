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




import Exceptions.InvalidGameException;
import Exceptions.InvalidSolutionException;
import Exceptions.NotFoundException;
import Generator.GameGenerator;
import Solver.SudokuSolver;
import Verifier.SudokuVerifier;
import java.io.IOException;

public class ControllerFacade implements Viewable {

    private final SudokuVerifier verifier;
    private final GameGenerator generator;
    private final Storage storage;
    private SudokuSolver solver;
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
        return new Catalog(hasUnfinished, hasEasy && hasMedium && hasHard);
    }

    @Override
    public Game getGame(Difficulty level) throws NotFoundException {
        try {
            if (level == null) return storage.loadUnfinishedGame();
            return storage.loadGame(level);
        } catch (IOException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public void driveGames(Game game) throws InvalidSolutionException {
        if (!verifier.verify(game).equals("valid")) {
            throw new InvalidSolutionException("Invalid Board");
        }
        games = generator.generateGame(game);
        try {
            storage.saveGame(Difficulty.EASY, games[0]);
            storage.saveGame(Difficulty.MEDIUM, games[1]);
            storage.saveGame(Difficulty.HARD, games[2]);
            storage.saveUnfinishedGame(games[0]);
        } catch (IOException e) {
            throw new InvalidSolutionException(e.getMessage());
        }
    }

    @Override
    public String verifyGame(Game game) {
        return verifier.verify(game);
    }

    @Override
    public int[] solveGame(Game game) throws InvalidGameException {
      int empty = game.countEmptyCells();
    if (empty < 1 || empty > 5) {
            throw new InvalidGameException("Empty cells must be 5");
        }
        
        solver = new SudokuSolver(game.getBoard());
        
        try {
            return solver.solve();
        } catch (Exception e) {
            throw new InvalidGameException(e.getMessage());
        }
    }

    @Override
    public void logUserAction(String userAction) throws IOException {
        storage.logAction(userAction);
    }
}