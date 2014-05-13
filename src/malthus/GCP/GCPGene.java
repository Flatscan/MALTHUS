/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * 
 * @version 0.0
 */

package malthus.GCP;

import malthus.Gene;

import java.util.Random;

/**
 * An extension of the Integer typed standard Gene<T> for
 * use in Graph Coloring Problems (GCP). It contains a 
 * global max for all GCPGenes that corresponds to the
 * total number of nodes in the graph GCPPhenotype to 
 * be colored.
 *  
 * @see	Gene
 */
public class GCPGene extends Gene<Integer>
{	
	/**
	 * The maximum value the gene can have
	 * for this particular problem. This 
	 * value should be the number of nodes
	 * in the graph attempting to be colored. 
	 */
	private static int max = 450;
	
	/**
	 * Retrieves the value stored in the GCPGene.
	 * 
	 * @return The value stored in the GCPGene 
	 * in the form of a double.
	 */
	@Override
	public double getValue()
	{
		return this.getData();
	}

	/**
	 * Randomly generates a random number between
	 * 0 and 1 and then normalizes it to the max
	 * value of the GCPGene.
	 * 
	 * @return A randomly generated Integer less
	 * than the max value.
	 */
	@Override
	protected Integer randomize()
	{
		Random rand = new Random();
		return rand.nextInt(max);
	}
}
