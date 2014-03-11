package malthus;

import malthus.util.Random;
import malthus.util.Sort;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 *
 */


public abstract class Population
{
	static final float MUTATION_RATE_DEFAULT = 0.1f;

	protected Configuration conf;

	protected Individual[] generation;

	protected double populationFitness;
	@SuppressWarnings("unused")
	protected double mostFit;
	@SuppressWarnings("unused")
	protected double leastFit;

	float mutationRate;
	
	public Population( )
	{
		this.conf = new Configuration();

		// Initilize Population
		int size = (Integer) this.conf.get("population_size");
		this.generation = new Individual[this.size];
		for(int i = 0; i < this.generation.length; i++)
			this.generation[i] = new Individual();

		// Subject to changes for a better sorting algorithm
		Sort.heap(generation);

		calStatistics();
	}


	public Population(Population previousPopulation) 
	{
		this.conf = new Configuration;

		// Initilize Population
		int size = (Integer) this.conf.get("population_size");
		this.generation = new Individual[this.size];

		// Generate New Population
		Individual[] selected = previousPopulation.selectIndividuals();
		for(int i = 0 ; i < this.generation.length; i++)
			generation[i] = new Individual(selected[selectParent()], selected[selectParent()]);
		
		// Subject to changes for a better sorting algorithm
		Sort.heap(generation);

		calStatistics();

	}


	private void calStatistics()
	{
		double max = Double.MAX_VALUE;
		double min = Double.MIN_VALUE;
		double ave = 0.0;


		for(int i = 0; i < this.generation.length; i++) {
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


	protected abstract Individual[] selectIndividuals( );
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
