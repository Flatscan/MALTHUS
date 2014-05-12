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


public class GCPPopulation extends Population
{	
	private int minValidColor = Integer.MAX_VALUE;
	
	
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
	
	@Override
	protected int selectParent()
	{
		Random rand = new Random();
		return rand.nextInt(this.generation.length / 2);
	}
	
	public int getMinValidColor()
	{
		return this.minValidColor;
	}
}
