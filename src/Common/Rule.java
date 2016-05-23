package Common;

import java.util.*;

public abstract class Rule<T> {
   protected List<Cell<T>> cells;
   
   public abstract void applyRule() throws notSolvableException;

}
