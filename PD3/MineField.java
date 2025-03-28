//Name: Yadong Wu
//USC NetID:3795420637
//CSCI 455 PA3
//Spring 2025


import java.util.Random;

/**
 MineField
 Class with locations of mines for a minesweeper game.
 This class is mutable, because we sometimes need to change it once it's created.
 Mutators: populateMineField, resetEmpty
 Includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {

    // <put instance variables here>
    private boolean[][] mineData;
    private int numMines = 0;

    /**
     Create a minefield with same dimensions as the given array, and populate it with the mines in
     the array such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice
     versa.  numMines() for this minefield will corresponds to the number of 'true' values in
     mineData.
     @param mineData  the data for the mines; must have at least one row and one col,
     and must be rectangular (i.e., every row is the same length)
     */
    public MineField(boolean[][] mineData) {
        if (mineData == null || mineData.length == 0 || mineData[0].length == 0) {
            throw new IllegalArgumentException("Minefield cannot be empty.");
        }
        int row = mineData.length;
        int col = mineData[0].length;
        this.mineData = new boolean [row][col];
        for (int i = 0; i < row; i++) {
            if (mineData[i].length != col) {
                throw new IllegalArgumentException("Minefield must be rectangular.");
            }

            for (int j = 0; j < col; j++) {

                //copy the original array into the one in this class
                this.mineData[i][j] = mineData[i][j];
                if (mineData[i][j]){
                    numMines++;
                }
            }
        }
    }

    /**
     Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once
     populateMineField is called on this object).  Until populateMineField is called on such a
     MineField, numMines() will not correspond to the number of mines currently in the MineField.
     @param numRows  number of rows this minefield will have, must be positive
     @param numCols  number of columns this minefield will have, must be positive
     @param numMines   number of mines this minefield will have,  once we populate it.
     PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations).
     */
    public MineField(int numRows, int numCols, int numMines) {
        if (numRows <=0 || numCols <=0 || numMines >= (numRows * numCols) / 3){
            throw new IllegalArgumentException("Invalid 2D array or invalid number of mines");
        }
        this.mineData = new boolean[numRows][numCols];
        this.numMines = numMines;
    }

    /**
     Removes any current mines on the minefield, and puts numMines() mines in random locations on
     the minefield, ensuring that no mine is placed at (row, col).
     @param row the row of the location to avoid placing a mine
     @param col the column of the location to avoid placing a mine
     PRE: inRange(row, col) and numMines() < (1/3 * numRows() * numCols())
     */

    public void populateMineField(int row, int col) {
        if (!inRange(row, col)) {
            throw new IllegalArgumentException("Invalid starting location.");
        }

        // Clear current minefield
        for (int i = 0; i < mineData.length; i++) {
            for (int j = 0; j < mineData[0].length; j++) {
                mineData[i][j] = false;
            }
        }

        Random mine = new Random();
        int minesPut = 0;
        int totalRows = mineData.length;
        int totalCols = mineData[0].length;

        // populate the mine into random location
        while (minesPut < numMines) {
            int rowIndex = mine.nextInt(totalRows);
            int colIndex = mine.nextInt(totalCols);
            if (!mineData[rowIndex][colIndex] && ( rowIndex!= row || colIndex!= col)) {
                mineData[rowIndex][colIndex] = true;
                minesPut++;
            }
        }
    }

    /**
     Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or
     numCols().  Thus, after this call, the actual number of mines in the minefield does not match
     numMines().
     Note: This is the state a minefield created with the three-arg constructor is in at the
     beginning of a game.
     */
    public void resetEmpty() {
        for (int i = 0; i < mineData.length; i++) {
            for (int j = 0; j < mineData[i].length; j++) {
                mineData[i][j] = false;
            }
        }
    }


    /**
     Returns the number of mines adjacent to the specified location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
     */
    public int numAdjacentMines(int row, int col) {
        if (!inRange(row, col)) {
            throw new IllegalArgumentException("Invalid range");
        }

        int minesCount = 0;
        int[] rowsAdjacent = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colsAdjacent = {1, 0, -1, 1, -1, 1, 0, -1};

        for (int i = 0; i < 8; i++) {
            int rowIndex = row + rowsAdjacent[i];
            int colsIndex = col + colsAdjacent[i];

            if(inRange(rowIndex, colsIndex) && mineData[rowIndex][colsIndex]){
                minesCount++;
            }
        }
        return minesCount;
    }


    /**
     Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
     start from 0.
     @param row  row of the location to consider
     @param col  column of the location to consider
     @return whether (row, col) is a valid field location
     */
    public boolean inRange(int row, int col) {
        return row >= 0 && row < mineData.length && col >= 0 && col < mineData[0].length;
    }


    /**
     Returns the number of rows in the field.
     @return number of rows in the field
     */
    public int numRows() {
        return mineData.length;       // DUMMY CODE so skeleton compiles
    }


    /**
     Returns the number of columns in the field.
     @return number of columns in the field
     */
    public int numCols() {
        return mineData[0].length;       // DUMMY CODE so skeleton compiles
    }


    /**
     Returns whether there is a mine in this square
     @param row  row of the location to check
     @param col  column of the location to check
     @return whether there is a mine in this square
     PRE: inRange(row, col)
     */
    public boolean hasMine(int row, int col) {
        if (!inRange(row, col)) {
            throw new IllegalArgumentException("Invalid range");
        }
        return mineData[row][col];
    }


    /**
     Returns the number of mines you can have in this minefield.  For mines created with the 3-arg
     constructor, some of the time this value does not match the actual number of mines currently
     on the field.  See doc for that constructor, resetEmpty, and populateMineField for more
     details.
     @return number of mines
     */
    public int numMines() {
        return numMines;
    }


    // <put private methods here>


}

