/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generator;

import Control.Game;
import java.util.List;

/**
 *
 * @author Abdelrahman Elsayed
 */
public class GameGenerator {
    private final RandomPairs RP;

    public GameGenerator() {
        this.RP = new RandomPairs();
    }
    
    public Game[] generateGame(Game game){
        int[][] board = game.getBoard();
        Game[] difficultyLevel = new Game[3];
        
        difficultyLevel[0] = removeCells(board,10);
        difficultyLevel[1] = removeCells(board,20);
        difficultyLevel[2] = removeCells(board,25);
        
        return difficultyLevel;
    }

    private Game removeCells(int[][] board, int remove) {
        int[][] newBoard = copyBoard(board);
        
        List<int[]> CellPositions = RP.generateDistinctPairs(remove);
        
        for (int[] position : CellPositions) {
            newBoard[position[0]][position[1]] = 0;
        }
        return new Game (newBoard);
         }
    
    private int[][] copyBoard(int[][] board) {
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, 9);
        }
        return copy;
    }
}
