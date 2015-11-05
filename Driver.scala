import java.io._

/* Driver used to start new game. Instantiates a new board object and
then calls the appropriate methods to setup and print the initial board. */
object Driver {
  def main(args: Array[String]) {
    var board = new Board();
    board.setUpGame();
    board.printBoard();
    
    // Continuous loop that accepts user input.
    do {
      print("Enter move (Ctrl+C to exit): ");
      // Takes input in the form of "A1, B2", etc.
      var input = readf2("{0}, {1}").asInstanceOf[(String, String)]; 
      board.makeMove(input);
    } while (true)
  }
}