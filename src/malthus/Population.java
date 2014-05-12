/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * 
 * @version 1.0
 */

package malthus;

import malthus.util.Sort;
import malthus.util.ReflectiveUtils;


public abstract class Population
{
	protected static Configuration conf = new Configuration();
	protected static final Class<Individual> individualImpl = conf.getClass("individual", Individual.class);

	protected Individual[] generation;

	protected double populationFitness;
	protected double mostFit;
	protected double leastFit;


	public static Population factory( boolean init ) 
	{
		int size = (Integer) conf.getInt("population_size");
		Class<Population> clazz = conf.getClass("population", Population.class);
		
		Population population = ReflectiveUtils.newInstance( clazz );
		if(init) 
		{
			Individual[] generation = new Individual[size];
			for(int i = 0; i < generation.length; i++)
				generation[i] = Individual.factory(true);
			population.setIndividuals( generation );
		}
		
		return population;
	}

	
	public Population nextGeneration() 
	{
		Population newPop = factory(false);
		
		int size = conf.getInt( "population_size" );
		Individual[] children = new Individual[size];

		int[] selected = this.selectIndividuals();
		for(int i = 0 ; i < children.length; i++)
		{
			Individual dad = this.generation[ selected[selectParent()] ];
			Individual mom = this.generation[ selected[selectParent()] ];
			children[i] = dad.reproduce(mom);
		}
		
		newPop.setIndividuals( children );
		return newPop;
	}
	
	private void calcStatistics()
	{
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		double ave = 0.0;

		for(int i = 0; i < this.generation.length; i++) 
		{
			max = (max < this.generation[i].getFitness()) ? this.generation[i].getFitness() : max;
			min = (min > this.generation[i].getFitness()) ? this.generation[i].getFitness() : min;
			ave += this.generation[i].getFitness();
		}

		this.mostFit = max;
		this.leastFit = min;
		this.populationFitness = (ave / (double) this.generation.length);
	}


	public Individual getFitest() 
	{
		return this.generation[ this.generation.length - 1 ];
	}
	
	public double getPopulationFitness()
	{
		return populationFitness;
	}	

	public double getMeanPopulationFitness()
	{
		return populationFitness;
	}
	
	public int getSize()
	{
		return generation.length;
	}
	
	public Individual[] getIndividuals()
	{
		return generation;
	}

	public void setIndividuals(Individual[] indvs) 
	{
		this.generation = indvs;
		Sort.heap(this.generation);
		this.calcStatistics();
	}
	
	
	
	
	@Override
	public String toString() 
	{
		return this.mostFit + "," + this.leastFit + "," + this.populationFitness; 
	}
	
	
	protected abstract int[] selectIndividuals( );
	protected abstract int selectParent( );
}
