import Array._
import Color._

class Board() {
  // Instantiate an 8x8 array of Piece objects.
  var gameBoard = ofDim[Piece](8,8)

  /* Map letters to number coordinates so that board coordinates can be
  // accessed using two numbers.*/ 
  val m = Map('A' -> 0, 'B' -> 1, 'C' -> 2, 'D' -> 3, 'E' -> 4, 
              'F' -> 5, 'G' -> 6, 'H' -> 7);

  /* Initialize a new game of checkers on the game board.*/
  def setUpGame() : Unit = {
    // Initialize the board by building a two-dimensional matrix of Piece objects. 
    for (i <- 0 to (gameBoard.length-1)) {
      for ( j <- 0 to (gameBoard.length-1)) {
        gameBoard(i)(j) = new Piece();
      }
    }
    // Place black pieces according to correct position on board.
    for (i <- 0 to 2) {
      for ( j <- 0 to (gameBoard.length-1)) {
        if(i%2==0 && j%2==0){
          gameBoard(i)(j).setColor(black);
        }
        else if (i%2==1 && j%2==1){
          gameBoard(i)(j).setColor(black);
        }
      }
    }
    // Place red pieces according to correct position on board.
    for (i <- 5 to 7) {
      for ( j <- 0 to (gameBoard.length-1)) {
        if(i%2==0 && j%2==0){
          gameBoard(i)(j).setColor(red);
        }
        else if (i%2==1 && j%2==1){
          gameBoard(i)(j).setColor(red);
        }
      }
    }
  }

  /* Process a move on the game board. Assumes input is correct, ergo no error checking.*/
  def makeMove(moves:(String, String)) : Unit = {
    // Get starting and ending coordinates of a piece based on user input.
    var i1 = m(moves._1.charAt(0)); 
    var j1 = (moves._1.charAt(1).asDigit)-1;
    var i2 = m(moves._2.charAt(0));
    var j2 = (moves._2.charAt(1).asDigit)-1;
    var currentPieceColor = gameBoard(i1)(j1).pieceColor;

    // Remove piece from current position.
    gameBoard(i1)(j1).setColor(null);
    
    // Check what direction (left or right) the current move is going.
    if (j2 > j1) {
      // Moving right

      // Check what direction (up or down) the current move is going.
      if (i2 > i1) {
        // Going down

        // Check if jump is occuring.
        if(math.abs(j2-j1)>1){
          // Jump has occured.
          // Remove piece being jumped from board.
          gameBoard(i1+1)(j1+1).setColor(null);
        }
        // Place moving piece on new new position.
        gameBoard(i2)(j2).setColor(currentPieceColor);
      }
      else{
        // Going up

        // Check if jump is occuring.
        if(math.abs(j2-j1)>1){
          // Jump has occured.
          // Remove piece being jumped from board.
          gameBoard(i1-1)(j1+1).setColor(null);
        }
        // Place moving piece on new new position.
        gameBoard(i2)(j2).setColor(currentPieceColor);
      }
    }
    else {
      // Moving left
      
      // Check what direction (up or down) the current move is going.
      if (i2 > i1) {
        // Going down

        // Check if jump is occuring.
        if(math.abs(j2-j1)>1){
          // Jump has occured.
          gameBoard(i1+1)(j1-1).setColor(null);
        }
        // Place piece on new new position.
        gameBoard(i2)(j2).setColor(currentPieceColor);
      }
      else{
        // Going up

        // Check if jump is occuring.
        if(math.abs(j2-j1)>1){
          // Jump has occured.
          gameBoard(i1-1)(j1-1).setColor(null);
        }
        // Place piece on new new position.
        gameBoard(i2)(j2).setColor(currentPieceColor);
      }
    }

    if(currentPieceColor == red){
      flipBoard();
      printBoard();
      flipBoard();
    }
    else{
      printBoard();
    }
  }

  /* Reverse the coordinates of a board so that it appears from the view of the
  opposite player. Use after each move is made.*/
  def flipBoard() : Unit = {
    // Reverse each array column.
    for (i <- 0 to (gameBoard.length-1)/2) {
      var temp:Array[Piece] = gameBoard(i);
      gameBoard(i) = gameBoard(gameBoard.length - i - 1);
      gameBoard(gameBoard.length - i - 1) = temp;
    }
    // Reverse each array row.
    for (i <- 0 to (gameBoard.length-1)) {
      gameBoard(i) = gameBoard(i).reverse;
    }
  }

  /* Output a full game board with all pieces currently on board. */
  def printBoard() : Unit = {
    // Loop through two-dimensional array and print each piece.
    for (i <- 0 to (gameBoard.length-1)) {
       for ( j <- 0 to (gameBoard.length-1)) {
          print(" " + gameBoard(i)(j).printPiece());
       }  
       println();
    }
  }

}