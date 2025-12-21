package Solver;

import java.util.HashMap;
import java.util.Map;

public class GameManager {
    
    private static GameManager instance = null;
    private final Map<String , SudokuSolver> solvers;
    
    private GameManager ()
    {
        solvers = new HashMap();
    }
    
    public static GameManager getInstance()
    {
        if(instance == null )
            instance = new GameManager();
        
        return instance ;
    }
    
    public void addSolver(String gameId , SudokuSolver solver)
    {
        solvers.put(gameId, solver);
    }
    
    public void removeSolver (String gameId)
    {
        solvers.remove(gameId);
    }
    
    public SudokuSolver getSolver(String gameId)
    {
        return solvers.get(gameId);
    }
            

}
