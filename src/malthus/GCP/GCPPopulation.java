package malthus.GCP;

import malthus.Individual;
import malthus.Population;

import java.util.Random;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 *
 */


public class GCPPopulation extends Population
{	
	private int minValidColor = 450;
	
	public GCPPopulation nextGeneration() {
		GCPPopulation newPop = (GCPPopulation) factory(false);
		
		// Breeding
		int size = conf.getInt("population_size");
		Individual[] children = new Individual[size];

		int[] selected = this.selectIndividuals();
		newPop.minValidColor = this.minValidColor;

		for(int i = 0 ; i < children.length; i++)
		{
			Individual dad = this.generation[ selected[selectParent()] ];
			Individual mom = this.generation[ selected[selectParent()] ];
			children[i] = dad.reproduce(mom);
		}
		
		newPop.setIndividuals(children);
		return newPop;
	}
	
	@Override
	protected int[] selectIndividuals( )
	{
		for( int i=0; i<this.generation.length; i++ )
		{
			GCPIndividual individual = (GCPIndividual) this.generation[i];
			if( individual.isValid() && individual.getMaxColor() < this.minValidColor )
				minValidColor = individual.getMaxColor();
		}
		
		int[] selected;
		selected = new int[this.generation.length / 2];
		for(int i = 0, j = this.generation.length - 1; i < selected.length; i++)
		{
			GCPIndividual indi = (GCPIndividual) this.generation[j];
			indi.setMaxColor( indi.getMaxColor() % (minValidColor + 1) );
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
