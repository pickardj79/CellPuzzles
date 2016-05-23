package Common;

import java.util.*;

public class ExclusionRule<T> extends Rule<T> {
   
   public ExclusionRule(Cell<T>[] cells) {
      this.cells = new ArrayList<Cell<T>>(Arrays.asList(cells));
   }
   
   @Override
   public void applyRule() throws notSolvableException {
      // 1. all cells must have a possible value (generic - super class?) -> throws notSolvable
      // 2. no two cells can have the same value - if isSolved any of them, then remove from the rest
      for ( Cell<T> c: cells ) {
         if ( c.notSolvable() )
            throw new notSolvableException();
         
         if ( c.isSolved() ) {
            for ( Cell<T> ic: cells ) {
               if ( c == ic )
                  continue;
               if (ic.isSolved())
                  continue;
               
               ic.removeValue(c.getSolvedVal());
               if (ic.notSolvable())
                  throw new notSolvableException();
            }
         }
      }
      
      return;
   }
}
