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

	public Individual[] generation;
	private double populationFitness;
	@SuppressWarnings("unused")
	private double mostFit;
	@SuppressWarnings("unused")
	private double leastFit;

	float mutationRate;
	
	public Population( )
	{
		populationFitness = -1;
		mostFit = -1;
		leastFit = -1;
	}


	protected abstract double calcFitness( );

	protected abstract int select( );
	
	public double getPopulationFitness()
	{
		return populationFitness;
	}
	
	public double getMeanPopulationFitness()
	{
		return populationFitness / generation.length;
	}
	
	public int getSize()
	{
		return generation.length;
	}

	
}
