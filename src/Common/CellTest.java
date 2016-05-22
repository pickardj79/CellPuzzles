package Common;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

public class CellTest {

   @Test
   public void basic() {
      List<Integer> pVals = new ArrayList<>();
      pVals.add(1);
      pVals.add(2);
      pVals.add(3);
      pVals.add(4);
      
      Cell<Integer> cell = new Cell<>(pVals);
      
      assertTrue("cell starts off unsolved", !cell.isSolved());
      assertEquals("got a possible value", Integer.valueOf(1), cell.getPossibleValue());
      
      cell.removeValue(1);
      
      assertTrue("cell is still unsolved", !cell.isSolved());
      assertEquals("cell is still unsolved - null", null, cell.getSolvedVal());
      assertEquals("removed values and got next possible", Integer.valueOf(2), 
            cell.getPossibleValue());
      
      cell.setSolvedValue(3);
      
      assertTrue("cell is solved", cell.isSolved());
      assertEquals("cell is solved correctly", Integer.valueOf(3), cell.getSolvedVal());
      
      cell.clearSolvedVal();
      
      assertTrue("cell no longer solved", !cell.isSolved());
      assertEquals("cell is no longer solved - null", null, cell.getSolvedVal());
      
      cell.removeValue(4);
      
      assertTrue("cell is solved again, by elimination", cell.isSolved());
      assertEquals("cell is solved again - value", Integer.valueOf(2), cell.getSolvedVal());
      
      cell.clearSolvedVal();
      
      assertEquals("all possible values gone, null", null, cell.getPossibleValue());
   }

}
