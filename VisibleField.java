//Name: Yadong Wu
//USC NetID:3795420637
//CSCI 455 PA3
//Spring 2025


/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because
  it's what the user can see about the minefield). Client can call getStatus(row, col) for any 
  square.  It actually has data about the whole current state of the game, including the underlying
  mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), 
  isGameOver().  It also has mutators related to actions the player could do (resetGameDisplay(),
  cycleGuess(), uncover()), and changes the game state accordingly.
  
  It, along with the MineField (accessible in mineField instance variable), forms the Model for the
  game application, whereas GameBoardPanel is the View and Controller in the MVC design pattern.  It
  contains the MineField that it's partially displaying.  That MineField can be accessed
  (or modified) from outside this class via the getMineField accessor.  
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus values [0,8] mentioned in comments below) are the
   // possible states of one location (a "square") in the visible field (all are values that can be
   // returned by public method getStatus(row, col)).
   
   // The following are the covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // The following are the uncovered states (all non-negative values):
   
   // values in the range [0,8] corresponds to number of mines adjacent to this opened square
   
   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already
                                          // (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of
                                                  // losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused
                                                 // you to lose)
   // ----------------------------------------------------------   
  
   // <put instance variables here>
   private MineField mineField;  // Reference to the underlying MineField.  mineField is an instance variable of type MineField inside the VisibleField class.
   private int[][] visibleField; // Stores the current visible state of each cell
   private boolean gameOver;     // Tracks whether the game has ended
   private int numMinesRemain;     // Tracks the number of mines left to be flagged

   /**
      Create a visible field that has the given underlying mineField.
      The initial state will have all the locations covered, no mines guessed, and the game not
      over.
      @param mineField  the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {
      this.mineField = mineField;

      int rowIndex = mineField.numRows();
      int colIndex = mineField.numCols();
      this.visibleField = new int[rowIndex][colIndex];

      for (int i =0; i < rowIndex; i++) {
         for (int j = 0; j < colIndex; j++) {
            visibleField[i][j] = COVERED;
         }
      }

      numMinesRemain = mineField.numMines();
   }
   
   
   /**
      Reset the object to its initial state (see constructor comments), using the same underlying
      MineField. 
   */     
   public void resetGameDisplay() {

      int rowIndex = mineField.numRows();
      int colIndex = mineField.numCols();

      for (int i =0; i < rowIndex; i++) {
         for (int j = 0; j < colIndex; j++) {
            visibleField[i][j] = COVERED;
         }
      }

      gameOver = false;
      numMinesRemain = mineField.numMines();
   }
  
   
   /**
      Returns a reference to the mineField that this VisibleField "covers"
      @return the minefield
    */
   public MineField getMineField() {
      return mineField;       // DUMMY CODE so skeleton compiles
   }
   
   
   /**
      Returns the visible status of the square indicated.
      @param row  row of the square
      @param col  col of the square
      @return the status of the square at location (row, col).  See the public constants at the
            beginning of the class for the possible values that may be returned, and their meanings.
      PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {
      if (!getMineField().inRange(row, col)) {
         return COVERED; // Or other safe default
      }

      return visibleField[row][col];
   }

   
   /**
      Returns the the number of mines left to guess.  This has nothing to do with whether the mines
      guessed are correct or not.  Just gives the user an indication of how many more mines the user
      might want to guess.  This value will be negative if they have guessed more than the number of
      mines in the minefield.     
      @return the number of mines left to guess.
    */
   public int numMinesLeft() {
      int mineGuess = 0;

      for (int i = 0; i < visibleField.length; i++) {
         for (int j = 0; j < visibleField[0].length; j++) {
            if (visibleField[i][j] == MINE_GUESS) {
               mineGuess++;
            }
         }
      }
      return numMinesRemain - mineGuess;
   }
 
   
   /**
      Cycles through covered states for a square, updating number of guesses as necessary.  Call on
      a COVERED square changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to
      QUESTION;  call on a QUESTION square changes it to COVERED again; call on an uncovered square
      has no effect.  
      @param row  row of the square
      @param col  col of the square
      PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
      if (!getMineField().inRange(row, col)) {
         throw new IllegalArgumentException("Position is out of bounds");
      }

      if (visibleField[row][col] == COVERED ) {
         visibleField[row][col] = MINE_GUESS;
      } else if (visibleField[row][col] == MINE_GUESS) {
         visibleField[row][col] = QUESTION;
      } else if (visibleField[row][col] == QUESTION) {
         visibleField[row][col] = COVERED;
      }
   }

   
   /**
      Uncovers this square and returns false iff you uncover a mine here.
      If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in the
      neighboring area that are also not next to any mines, possibly uncovering a large region.
      Any mine-adjacent squares you reach will also be uncovered, and form (possibly along with
      parts of the edge of the whole field) the boundary of this region.
      Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
      Note: this action may cause the game to end: either in a win (opened all the non-mine squares)
      or a loss (opened a mine).
      @param row  of the square
      @param col  of the square
      @return false   iff you uncover a mine at (row, col)
      PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
      if (!getMineField().inRange(row, col)) {
         //throw new IllegalArgumentException("Position is out of bounds");
         return false;
      }

      // do nothing if it's a guessed mine
      else if (visibleField[row][col] == MINE_GUESS) {
         return true;
      }

      //game is over if there is a mine
      else if (mineField.hasMine(row, col)) {
         visibleField[row][col] = EXPLODED_MINE;
         gameOver = true;

         revealAllMines(row, col);
         return false;
      }

      // No need to uncover again if already uncovered
      if (isUncovered(row, col)) {
         return true;
      }

      int adjacentMines = mineField.numAdjacentMines(row, col);

      // This stores the number of adjacent mines in the visible field
      visibleField[row][col] = adjacentMines;

      // If the square is 0 and no mines next to it, start recursive uncover
      if (adjacentMines == 0) {
         floodFill(row, col);
      }

      // Check if you win
      checkGameOver();
      return true;
   }
 
   
   /**
      Returns whether the game is over.
      (Note: This is not a mutator.)
      @return whether game has ended
    */
   public boolean isGameOver() {
      return gameOver;
   }
 
   
   /**
      Returns whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
      vs. any one of the covered states).
      @param row of the square
      @param col of the square
      @return whether the square is uncovered
      PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {

      //checking it if it is out of bounds
      if (!getMineField().inRange(row, col)) {
         return false;
      }

      else if (visibleField[row][col] >= 0){
         return true;
      }
      return false;
   }

   // Reveal all incorrect guesses and un-guessed mines
   private void revealAllMines(int row, int col) {
      for (int i = 0; i < mineField.numRows(); i++) {
         for (int j = 0; j < mineField.numCols(); j++) {
            if (i == row && j == col) {

               // Don't overwrite the exploded mine
               continue;
            }
            if (mineField.hasMine(i, j) && visibleField[i][j] != MINE_GUESS) {

               // Reveal un-guessed mines
               visibleField[i][j] = MINE;
            }
            else if (!mineField.hasMine(i, j) && visibleField[i][j] == MINE_GUESS) {

               // Reveal incorrect guesses
               visibleField[i][j] = INCORRECT_GUESS;
            }
         }
      }
   }

   // <put private methods here>
   private void floodFill(int row, int col) {
      int[] rowsAdjacent = {-1, -1, -1, 0, 0, 1, 1, 1};
      int[] colsAdjacent = {1, 0, -1, 1, -1, 1, 0, -1};
      visibleField[row][col] = 0;

      for (int i = 0; i < 8; i++) {
         int rowsIndex = row + rowsAdjacent[i];
         int colsIndex = col + colsAdjacent[i];

         if (mineField.inRange(rowsIndex, colsIndex) && !isUncovered(rowsIndex, colsIndex) && visibleField[rowsIndex][colsIndex] != MINE_GUESS) {
            int adjacentMines = mineField.numAdjacentMines(rowsIndex, colsIndex);
            visibleField[rowsIndex][colsIndex] = adjacentMines;
            if (adjacentMines == 0) {
               floodFill(rowsIndex, colsIndex);
            }
         }
      }
   }

   private void checkGameOver() {
      for (int i = 0; i < mineField.numRows(); i++) {
         for (int j = 0; j < mineField.numCols(); j++) {
            if (!isUncovered(i, j) && !mineField.hasMine(i, j)) {

               // Still have non-mine squares to uncover
               return;
            }
         }
      }

      // If the loop above didn’t return, then all safe cells have been uncovered
      gameOver = true;

      // When a player successfully opens all of the non-mine locations,
      // the field display changes to show where the other mines are (it shows them as guesses, in yellow)
      for (int i = 0; i < mineField.numRows(); i++) {
         for (int j = 0; j < mineField.numCols(); j++) {
            if (mineField.hasMine(i, j) && visibleField[i][j] != MINE_GUESS) {
               visibleField[i][j] = MINE_GUESS;
            }
         }
      }
   }
}
