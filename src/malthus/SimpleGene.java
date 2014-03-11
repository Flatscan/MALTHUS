package malthus;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */

import malthus.util.Random;

public class SimpleGene implements Gene 
{
//	private static final Configuration configuration = new Configuration();
	
/**
 * The value stored in the Gene that maps to a particular
 * value in the Individual's phenotype. 
 * 
 * For the SimpleGene implementation, this is some int between
 * zero and geneMax.
 * 
 * @see #geneMax
 */
	private int value;
	
/**
 * The maximum value that a particular Gene can have relative
 * to zero.
 */
	private int geneMax;

/**
 * No argument constructor. Not to be used!
 */
	public SimpleGene()
	{
		this.value = -1;
	}
	
/**
 * The default constructor. Takes a max value that the
 * Gene can have a randomly assigns a value to it based
 * on and the Random object passed to it. 
 * 
 * @param r
 * @param max
 */
	public SimpleGene( int max, Random r )
	{
		this.geneMax = max;
		this.value = (int) Math.floor( Math.random() ) * max;
	}

 /**
  * Tests for the equality between two Genes
  * based on the value of each Gene.
  * 
  * @param gene
  */
	
	public boolean equals(Gene gene) 
	{
		return ( this.value == gene.getValue() );
	}


	public int hashCode()
	{
		return this.value;
	}

/**
 * Return the value stored in the SimpleGene as 
 * a double.
 * 
 */
	public double getValue()
	{
		return (double) this.value;
	}
	
/**
 * Changes a Gene's value to one randomly within its
 * range based on the Random object passed to it.
 * 
 * @param r
 */
	@Override
	public void randomize( Random r )
	{
		this.value = (int) Math.floor( r.nextDouble() * this.geneMax );
				
	}



}
