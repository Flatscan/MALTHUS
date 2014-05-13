/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * 
 * @version 0.0
 */

package malthus.GCP;


import malthus.Individual;
import malthus.Population;

import java.util.Random;


/**
 * A collection of GCPIndividuals where most of the
 * actual behavior takes place.
 */
public class GCPPopulation extends Population
{	
	/**
	 * The current, minimum valid color for any 
	 * solution in the GCPPopulation. It is the 
	 * maximum number of colors any solution
	 * can have. 
	 */
	private int minValidColor = Integer.MAX_VALUE;
	
	/**
	 * Creates a GCPPopulation from the current
	 * generation by calling the reproduce
	 * function on two random parents from
	 * the selected sub-population.
	 */
	public GCPPopulation nextGeneration() 
	{
		GCPPopulation newPop = (GCPPopulation) factory(false);
		
		int size = conf.getInt( "population_size" );
		GCPIndividual[] children = new GCPIndividual[size];

		int[] selected = this.selectIndividuals();
		newPop.minValidColor = this.minValidColor;

		for(int i = 0 ; i < children.length; i++)
		{
			Individual parent1 = this.generation[ selected[selectParent()] ];
			Individual parent2 = this.generation[ selected[selectParent()] ];
			children[i] = (GCPIndividual) parent1.reproduce( parent2 );
			
			if( children[i].isValid() && children[i].getMaxColor() < this.minValidColor )
				minValidColor = children[i].getMaxColor();
		}
		
		newPop.setIndividuals(children);
		return newPop;
	}
	
	/**
	 * The problem specific selection function. 
	 * 
	 * For our GCP population we chose an elitist
	 * selection function which selects the top
	 * half of the population as the breeding 
	 * population.
	 * 
	 * @return An array of ints corresponding to the
	 * 		   indexes of the selected GCPIndividuals.
	 */
	@Override
	protected int[] selectIndividuals( )
	{		
		int[] selected;
		selected = new int[this.generation.length / 2];
		
		for(int i = 0, j = this.generation.length - 1; i < selected.length; i++)
		{
			GCPIndividual gi = (GCPIndividual) this.generation[j];
			gi.setMaxColor( gi.getMaxColor() % (minValidColor + 1) );
			selected[i] = j--;
		}

		return selected;
	}		
	
	/**
	 * @return A random int less than half the
	 * generation size. 
	 */
	@Override
	protected int selectParent()
	{
		Random rand = new Random();
		return rand.nextInt(this.generation.length / 2);
	}
	
	
	/**
	 * @return The current minimum valid coloring.
	 */
	public int getMinValidColor()
	{
		return this.minValidColor;
	}
}
