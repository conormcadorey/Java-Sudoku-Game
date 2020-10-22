package sudoku.problemdomain;

import sudoku.computationlogic.SudokuUtilities;
import sudoku.constants.GameState;

import java.io.Serializable;

public class SudokuGame implements Serializable {
    //enum - represents various states of the game
    private final GameState gameState;
    private final int [][] gridState;

    public static final int GRID_BOUNDARY = 9; //9 represents total squares in sudoku puzzle

    public SudokuGame(GameState gameState, int[][] gridState) {
        this.gameState = gameState;
        this.gridState = gridState;
    }

    public GameState getGameState() {
        return gameState;
    }

    //return a copy of gridstate
    //main gridstate = immutable
    //this protects the main game object from being modified
    public int[][] getCopyOfGridState() {
        return SudokuUtilities.copyToNewArray(gridState);
    }
}
