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
 * total number of nodes in the graph (GCPPhenotype to 
 * be colored.
 *  
 * @see	Gene
 */
public class GCPGene extends Gene<Integer>
{	
	private static int max = 450;
	
	@Override
	public double getValue()
	{
		return this.getData();
	}

	@Override
	protected Integer randomize()
	{
		Random rand = new Random();
		return rand.nextInt(max);
	}
}
