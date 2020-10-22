package sudoku.computationlogic;

import sudoku.problemdomain.Coordinates;
import sudoku.constants.GameState;
import sudoku.problemdomain.SudokuGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

public class GameGenerator {

    public static int[][] getNewGameGrid() {
        return unsolveGame(getSolvedGame());
        /*Random random = new Random(System.currentTimeMillis());

        boolean solvable = false;
        int[][] solvableArray = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        while (solvable == false) {
            SudokuUtilities.copySudokuArrayValues(solvedGame, solvableArray);
            int index = 0;


            while (index < 40) {
                int xCoordinate = random.nextInt(GRID_BOUNDARY);
                int yCoordinate = random.nextInt(GRID_BOUNDARY);

                if (solvableArray[xCoordinate][yCoordinate] != 0) {
                    solvableArray[xCoordinate][yCoordinate] = 0;
                    index++;
                }
            }

            int[][] toBeSolved = new int[GRID_BOUNDARY][GRID_BOUNDARY];
            SudokuUtilities.copySudokuArrayValues(solvableArray, toBeSolved);

            solvable = sudokuSolver.puzzleIsSolvable(toBeSolved);
        }

        return solvableArray; */
    }

    //generate a solved sudoku game
    private static int[][] getSolvedGame() {
        //seed a random number
        Random random = new Random(System.currentTimeMillis());
        //create new grid based on boundaries
        int[][] newGrid = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        //allocate values (1-9) 9 times- to accurately represent a sudoku puzzle
        for (int value = 1; value <= GRID_BOUNDARY; value++) {
            int allocations = 0; //tracks the no of times each value (1-9) has been allocated
            int interrupt = 0;

            List<Coordinates> allocTracker = new ArrayList<>();
            int attempts = 0;
            //while no of times a value is allocated is less than 9- keep attempting to allocate number
            //if interrupt is greater than 200, go thru past allocations and reset to 0 then try again
            while (allocations < GRID_BOUNDARY) {
                if (interrupt > 200) {
                    allocTracker.forEach(coord -> {
                        newGrid[coord.getX()][coord.getY()] = 0;
                    });

                    interrupt = 0;
                    allocations = 0;
                    allocTracker.clear();
                    attempts++;

                    //if attempts > 500
                    //clear whole array and try again from the start
                    if(attempts > 500) {
                        clearArray(newGrid);
                        attempts = 0;
                        value = 1;
                    }
                }

                //when allocating a number to the array...
                //...randomly select a square
                int xCoordinate = random.nextInt(GRID_BOUNDARY);
                int yCoordinate = random.nextInt(GRID_BOUNDARY);

                //if squares value = 0, allocate the new random num to the square
                if (newGrid[xCoordinate][yCoordinate] == 0) {
                    newGrid[xCoordinate][yCoordinate] = value;

                    //immediately check if a valid or invalid sudoku game
                    //if invalid- remove the value just added and increment interrupt
                    //if valid- add num to allocations tracker
                    if (GameLogic.sudokuIsInvalid(newGrid)) {
                        newGrid[xCoordinate][yCoordinate] = 0;
                        interrupt++;
                    } else {
                        allocTracker.add(new Coordinates(xCoordinate, yCoordinate));
                        allocations++;
                    }
                }
            }

        }
        return newGrid;
    }

    private static int[][] unsolveGame(int[][] solvedGame) {
        Random random = new Random(System.currentTimeMillis());

        boolean solvable = false;

        //note: not actually solvable until the algorithm below finishes!
        int[][] solvableArray = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        while (solvable == false){

            //Take values from solvedGame and write to new unsolved; i.e. reset to initial state
            SudokuUtilities.copySudokuArrayValues(solvedGame, solvableArray);

            //remove 40 random numbers
            int index = 0;
            while (index < 40) {
                int xCoordinate = random.nextInt(GRID_BOUNDARY);
                int yCoordinate = random.nextInt(GRID_BOUNDARY);

                if (solvableArray[xCoordinate][yCoordinate] != 0) {
                    solvableArray[xCoordinate][yCoordinate] = 0;
                    index++;
                }
            }

            int[][] toBeSolved = new int[GRID_BOUNDARY][GRID_BOUNDARY];
            SudokuUtilities.copySudokuArrayValues(solvableArray, toBeSolved);
            //check if result is solvable
            solvable = SudokuSolver.puzzleIsSolvable(toBeSolved);

            //TODO Delete after tests
            System.out.println(solvable);
        }

        return solvableArray;
    }

    //helper function
    //traverses array and resets it
    private static void clearArray(int[][] newGrid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                newGrid[xIndex][yIndex] = 0;
            }
        }
    }
}
