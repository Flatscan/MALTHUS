package malthus;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */
<<<<<<< HEAD

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
	
=======
public abstract class Gene<T extends Comparable<T>>
{
	protected Configuration conf;

	private T data;

	public Gene() {
		this.data = randomize();	
	}


	public Gene(T data) {
		this.data = data;
	}


	public int compareTo(Gene<T> gene) {
		return this.data.compareTo(gene.get());
	}


	public T get() {
		return this.data;
	}


	protected abstract T randomize();
>>>>>>> FETCH_HEAD
	public abstract double getValue();

}
