package malthus;
/**
 * @author MalcolmRoss
 * @version 0.0
 *
 */
public class Population
{
	/****** DEFAULT CONTANTS *****/
	float  static final MUTATION_RATE_DEFAULT = 0.1f;

	Individual[] generation;

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
	{}
	
}
