package Common;

import java.util.*;

public class Cell<T> {
   private List<T> possibleVals;
   private T solvedVal;
   
   public Cell (List<T> possibleVals) {
      this.possibleVals = new ArrayList<T>(possibleVals);
   }
   
   public boolean isSolved() {
      return null != solvedVal;
   }
   
   public boolean notSolvable() {
      return !isSolved() && possibleVals.size() == 0;
   }
   
   public void removeValue(T val) {
      for (int i = 0; i < possibleVals.size(); i++) {
         if (val == possibleVals.get(i) ) {
            possibleVals.remove(i);
            
            if (1 == possibleVals.size()) 
               setSolvedValue(getPossibleValue());
            
            break;
         }
      }
      
      if (isSolved() && val == getSolvedVal())
         solvedVal = null;
      
      return;
   }
   
   public T getPossibleValue() {
      if (0 == possibleVals.size())
         return null;
      
      return possibleVals.get(0);
   }
   
   public void setSolvedValue(T val) {
      solvedVal = val;
      return;
   }
   
   public void clearSolvedVal() {
      removeValue(solvedVal);
      solvedVal = null;
      return;
   }
   
   public T getSolvedVal() {
      return solvedVal;
   }
}
