
package Solver;


public class BruteForceStrategy implements SolvingStrategy {
    @Override 
    public int [] solve(int [][] board) throws InvalidGameException
    {
        SudokuSolver s = new SudokuSolver(board);
        return s.solve;
    }
    
}
