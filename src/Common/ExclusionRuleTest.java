package Common;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ExclusionRuleTest {

   @Test
   public void basic() {
      // create some cells with 4 possible values
      // NOTE: this test depends on the implementation of Cell - boo.
      //    specifically that the possibleValues as returned sequentially
      List<Integer> pVals = new ArrayList<>();
      pVals.add(1);
      pVals.add(2);
      pVals.add(3);
      pVals.add(4);
      
      Cell<Integer> cell0 = new Cell<>(pVals);
      Cell<Integer> cell1 = new Cell<>(pVals);
      Cell<Integer> cell2 = new Cell<>(pVals);

      @SuppressWarnings("unchecked")
      Cell<Integer>[] cells = (Cell<Integer>[]) new Cell[3];
      cells[0] = cell0;
      cells[1] = cell1;
      cells[2] = cell2;
      
      Rule<Integer> rule = new ExclusionRule<Integer>(cells);
      run_applyRule(rule);
      
      // cells start off unsolved, even after a rule is applied
      assertTrue("cell0 not solved", !cell0.isSolved());
      assertTrue("cell1 not solved", !cell1.isSolved());
      assertTrue("cell2 not solved", !cell2.isSolved());
      
      // setting a solved value removes that value from other cells and will
      //    automatically turn other cells to solved
      assertEquals("cell0 has 1 as possible", Integer.valueOf(1),
            cell0.getPossibleValue());
      
      cell0.setSolvedValue(Integer.valueOf(1));
      cell1.removeValue(2);
      cell1.removeValue(3);
      
      run_applyRule(rule);
      assertEquals("cell2 no longer has 1 as possible", Integer.valueOf(2),
            cell2.getPossibleValue());
      assertTrue("cell2 is not solved", !cell2.isSolved());
      assertEquals("cell1 is now solved", Integer.valueOf(4), cell1.getSolvedVal());
      
      // removing the last possible values is an error
      cell2.removeValue(2);
      cell2.removeValue(3);
      
      try {
         rule.applyRule();
         fail("cell2 should result in exception - no possibleValues");
      }
      catch ( notSolvableException e ) {
         assertEquals("cell2 has no possibleValues", null, cell2.getPossibleValue() );
      }
      
   }
   
   private void run_applyRule(Rule<Integer> r) {
      try {
         r.applyRule();
      }
      catch ( notSolvableException e) {
      }
   }

}
