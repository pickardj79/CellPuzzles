package Common;

import java.util.*;

public abstract class PuzzleBoard<T> {
   private Cell<T>[][] cells;
   private List<Rule<T>> rules;
   private List<Guess> guessHistory;
   
   public PuzzleBoard() {
      
   }
   
   public PuzzleBoard<T> solve () throws notSolvableException {
      if ( isSolved() )
         return this;
      
      try {
         runRules();
         Guess newGuess = this.makeGuess();
         guessHistory.add(newGuess);
      }
      catch (notSolvableException e) {
         if (0 == guessHistory.size())
            throw e;
         
         // roll back most recent guess
         Guess badGuess = guessHistory.remove(guessHistory.size() - 1);
         badGuess.markIncorrect();
      }
      
      return this.solve();
   }
   
   private void runRules () throws notSolvableException {
      for (Rule<T> r: rules) {
         r.applyRule();
      }
      
      return;
   }
   
   private boolean isSolved() {
      if ( null == findNotSolvedCell() )
         return true;

      return false;
   }
   
   private Guess makeGuess() throws notSolvableException {
      // finds any cell that is not solved, throws notSolvableException if there are no possible values
      // otherwise sets cell solvedVal equal to one of the possible values and creates a Guess
      Cell<T> notSolvedCell = findNotSolvedCell();
      
      if (null == notSolvedCell)
         throw new notSolvableException();
      
      T guessedVal = notSolvedCell.getPossibleValue(); 
      if (null == guessedVal) 
         throw new notSolvableException();
      
      notSolvedCell.setSolvedValue(guessedVal);
      return new Guess(notSolvedCell, guessedVal);
   }
   
   private Cell<T> findNotSolvedCell() {
      for (int i = 0; i < cells.length; i++ ) {
         for ( int j = 0; j < cells[i].length; j++ ) {
            if (!cells[i][j].isSolved())
               return cells[i][j];
         }
      }
      
      return null;
   }
   
  
   private class Guess {
      private Cell<T> cell;
      private T guess;
      
      public Guess(Cell<T> cell, T guess) {
         this.cell = cell;
         this.guess = guess;
      }
      
      public void markIncorrect() {
         cell.clearSolvedVal();
         cell.removeValue(guess);
         return;
      }
   }
}
