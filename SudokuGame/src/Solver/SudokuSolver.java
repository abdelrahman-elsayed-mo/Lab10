package Solver;

import java.util.*;

public class SudokuSolver {
    private final int[][] board;

    public SudokuSolver(int[][] board) {
        this.board = board;
    }

    public int[] solve() throws InvalidGameException {
        List<EmptyCell> emptyCells = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    emptyCells.add(new EmptyCell(i, j));
                }
            }
        }

        if (emptyCells.size() != 5) {
            throw new InvalidGameException("The solver requires exactly 5 empty cells.");
        }

        BoardVerifier verifier = new BoardVerifier(board, emptyCells);
        PermutationIteratorFactory factory = new PermutationIteratorFactory();
        PermutationIterator iterator = factory.createFiveCellIterator();

        while (iterator.hasNext()) {
            int[] currentPerm = iterator.next();
            if (verifier.isValidPermutation(currentPerm)) {
                return verifier.getSolutionForDisplay(currentPerm);
            }
        }

        throw new InvalidGameException("No valid solution found for these 5 cells.");
    }
}