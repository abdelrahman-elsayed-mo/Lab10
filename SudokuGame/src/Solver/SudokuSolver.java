
package Solver;


import java.util.*;

public class SudokuSolver {
    private final int[][] originalBoard;
    private final List<EmptyCell> emptyCells;
    private final int emptyCount;
    
    public SudokuSolver(int[][] board) {
        this.originalBoard = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            this.originalBoard[i] = Arrays.copyOf(board[i], board[i].length);
        }
        
        this.emptyCells = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    emptyCells.add(new EmptyCell(i, j));
                }
            }
        }
        this.emptyCount = emptyCells.size();
    }
    
    public int[] solve() throws InvalidGameException {
        if (emptyCount != 5) {
            throw new InvalidGameException("");
        }
        
        return solve(originalBoard);
    }
    
    public int[] solve(int[][] board) throws InvalidGameException {
        List<EmptyCell> cells = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    cells.add(new EmptyCell(i, j));
                }
            }
        }
        
        if (cells.size() != 5) {
            throw new InvalidGameException("");
        }
        
        BoardVerifier verifier = new BoardVerifier(board, cells);
        PermutationIterator iterator = new PermutationIterator(5, 9);
        
        while (iterator.hasNext()) {
            int[] permutation = iterator.next();
            if (verifier.isValidPermutation(permutation)) {
                int[] result = new int[cells.size() * 3];
                int idx = 0;
                
                for (int i = 0; i < cells.size(); i++) {
                    EmptyCell cell = cells.get(i);
                    result[idx++] = cell.row;
                    result[idx++] = cell.column;
                    result[idx++] = permutation[i];
                }
                return result;
            }
        }
        throw new InvalidGameException("");
    }
    
    public int getEmptyCellCount() {
        return emptyCount;
    }
    
    public static boolean canEnableSolver(int[][] board) {
        int emptyCount = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    emptyCount++;
                    if (emptyCount > 5) {
                        return false;
                    }
                }
            }
        }
        return emptyCount == 5;
    }
}

