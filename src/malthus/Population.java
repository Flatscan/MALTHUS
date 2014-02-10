package malthus;
/**
 * @author MalcolmRoss
 * @version 0.0
 *
 */
public class Population
{
	/****** DEFAULT CONTANTS *****/
	static final float MUTATION_RATE_DEFAULT = 0.1f;

	private Individual[] generation;
	private double populationFitness;
	private int fittest;
	private int leastFit;

	float mutationRate;
	
	public Population( int populationSize )
	{}
	public Population( Population previousGeneration )
	{}
	public Population( Population previousGeneration, int fitnessThreshold )
	{}
	private void testFitness(  )
	{}
	private Individual[] selectBreedingPool( )
	{return generation;}
	public double getPopulationFitness()
	{
		return populationFitness;
	}
	public double getMeanPopulationFitness()
	{
		return populationFitness / generation.length;
	}
	public Individual getFittest()
	{
		return generation[fittest];
	}
	public Individual getLeastFit()
	{
		return generation[leastFit];
	}

	
}
