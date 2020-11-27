object Main {
  def main(args: Array[String]) {

    var sudoku:Sudoku = new Sudoku(Array.fill(9,9)(0))
     sudoku.proposeGrille(); // Generation grille simple (simple = )
/*
   // Grille du l'énoncé

    sudoku.putOnTheX(0,Array(5,3,0,0,7,0,0,0,0));
    sudoku.putOnTheX(1,Array(6,0,0,1,9,5,0,0,0));
    sudoku.putOnTheX(2,Array(0,9,8,0,0,0,0,6,0));
    sudoku.putOnTheX(3,Array(8,0,0,0,6,0,0,0,3));
    sudoku.putOnTheX(4,Array(4,0,0,8,0,3,0,0,1));
    sudoku.putOnTheX(5,Array(7,0,0,0,2,0,0,0,6));
    sudoku.putOnTheX(6,Array(0,6,0,0,0,0,2,8,0));
    sudoku.putOnTheX(7,Array(0,0,0,4,1,9,0,0,5));
    sudoku.putOnTheX(8,Array(0,0,0,0,8,0,0,7,9));
*/
    println(sudoku.toString());
    println(Console.RED + "Solution : " +  sudoku.solver().length+Console.RESET)
    println(sudoku.toString());
  }
}
