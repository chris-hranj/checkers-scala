import Color._

/* Class used to represent a checker on the checkerboard. */
class Piece() {
  //Pieces are set to null by default.
  var pieceColor : Color = null;

  /* Return a string depending on what color the piece is set to.*/
  def printPiece() : String = {
    pieceColor match {
      case null => "-"
      case `red` => "o"
      case `black` => "x"
    }
  }

  /* Change the color of a piece.*/
  def setColor(color: Color) : Unit = {
    pieceColor = color;
  }
}