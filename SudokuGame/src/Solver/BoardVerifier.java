
package Solver;

import java.util.List;


public class BoardVerifier {
    private final int[][] board;
    private final List<EmptyCell> emptyCells;
    
    public BoardVerifier(int[][] board, List<EmptyCell> emptyCells) {
        this.board = board;
        this.emptyCells = emptyCells;
    }
    
    public boolean isValidPermutation(int[] guessedNumbers) {
        boolean[][] rowNumbers = new boolean[9][10];
        boolean[][] colNumbers = new boolean[9][10];
        boolean[][][] boxNumbers = new boolean[3][3][10];
        
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int number = board[row][col];
                
                if (number != 0) {
                    if (number < 1 || number > 9) {
                        return false;
                    }
                    
                    if (rowNumbers[row][number]) {
                        return false;
                    }
                    rowNumbers[row][number] = true;
                    
                    if (colNumbers[col][number]) {
                        return false;
                    }
                    colNumbers[col][number] = true;
                    
                    int boxRow = row / 3;
                    int boxCol = col / 3;
                    
                    if (boxNumbers[boxRow][boxCol][number]) {
                        return false;
                    }
                    boxNumbers[boxRow][boxCol][number] = true;
                }
            }
        }
        
        for (int guessIndex = 0; guessIndex < emptyCells.size(); guessIndex++) {
            EmptyCell emptySpot = emptyCells.get(guessIndex);
            int row = emptySpot.row;
            int col = emptySpot.column;
            int guessedNumber = guessedNumbers[guessIndex];
            
            if (guessedNumber < 1 || guessedNumber > 9) {
                return false;
            }
            
            if (rowNumbers[row][guessedNumber]) {
                return false;
            }
            rowNumbers[row][guessedNumber] = true;
            
            if (colNumbers[col][guessedNumber]) {
                return false;
            }
            colNumbers[col][guessedNumber] = true;
            
            int boxRow = row / 3;
            int boxCol = col / 3;
            if (boxNumbers[boxRow][boxCol][guessedNumber]) {
                return false;
            }
            boxNumbers[boxRow][boxCol][guessedNumber] = true;
        }
        
        return true;
    }
    
    public int[] getSolutionForDisplay(int[] correctNumbers) {
        int[] solutionInfo = new int[emptyCells.size() * 3];
        int infoIndex = 0;
        
        for (int i = 0; i < emptyCells.size(); i++) {
            EmptyCell emptySpot = emptyCells.get(i);
            
            solutionInfo[infoIndex] = emptySpot.row;
            infoIndex++;
            
             solutionInfo[infoIndex] = emptySpot.column;
            infoIndex++;
            
            solutionInfo[infoIndex] = correctNumbers[i];
            infoIndex++;
        }
        
        return solutionInfo;
    }
}
