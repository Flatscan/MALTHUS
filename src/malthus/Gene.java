package malthus;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */

import malthus.util.Random;

public interface Gene
{
 /**
  * Randomly sets the value of a given Gene based on the value
  * derived from the Random object. It is called in the Individual's
  * mutation function as well as its "random" construct.
  * 
  */
	public abstract void randomize( Random r );

 /**
  * Tests for the equality between two Genes.
  * 
  */

	public abstract boolean equals(Gene gene);

	
	public abstract int hashCode();

/**
  * Returns the double representation of the value stored
  * in a given Gene.
  * 
  */
	
	public abstract double getValue();

}
