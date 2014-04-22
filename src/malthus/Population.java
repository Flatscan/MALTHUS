package malthus;

import malthus.util.ReflectiveUtils;
import malthus.util.Sort;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 *
 */


public abstract class Population
{
	protected Configuration conf = new Configuration();
	protected final Class<Individual> individualImpl = this.conf.getClass("individual", Individual.class);;


	protected Individual[] generation;

	protected double populationFitness;
	protected double mostFit;
	protected double leastFit;

	
	public Population( )
	{
		// Initilize Population
		int size = (Integer) this.conf.getInt("population_size");
		
		this.generation = new Individual[size];
		for(int i = 0; i < this.generation.length; i++)
			this.generation[i] = ReflectiveUtils.newInstance(individualImpl);

		// Subject to changes for a better sorting algorithm
		Sort.heap(generation);

		calStatistics();
	}


	public Population(Population previousPopulation) 
	{
		// Initilize Population
		int size = this.conf.getInt("population_size");
		this.generation = new Individual[size];

		// Generate New Population
		int[] selected = previousPopulation.selectIndividuals();
		for(int i = 0 ; i < this.generation.length; i++)
		{
			Class<?> parameterTypes[] = {Individual.class, Individual.class};
			Object parameters[] = {this.generation[selected[selectParent()]], this.generation[selected[selectParent()]]};
			
			generation[i] = ReflectiveUtils.newInstance(individualImpl, parameterTypes, parameters);
		}
		
		// Subject to changes for a better sorting algorithm
		Sort.heap(generation);

		calStatistics();

	}


	private void calStatistics()
	{
		double max = Double.MAX_VALUE;
		double min = Double.MIN_VALUE;
		double ave = 0.0;


		for(int i = 0; i < this.generation.length; i++) 
		{
			// Maximum
			max = (max < this.generation[i].getFitness()) ? this.generation[i].getFitness() : max;
			
			// Minimum
			min = (min > this.generation[i].getFitness()) ? this.generation[i].getFitness() : min;


			// Average
			ave += this.generation[i].getFitness();
		}

		this.mostFit = max;
		this.leastFit = min;
		this.populationFitness = (ave / (double) this.generation.length);
	}


	/**
	 * Selects individuals within the population and returns the indices
	 * @return
	 */
	protected abstract int[] selectIndividuals( );
	
	
	/**
	 * Select a random individual within the selected pool
	 * @return
	 */
	protected abstract int selectParent( );
	

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
}
