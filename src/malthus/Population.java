package malthus;

import malthus.util.Random;
import malthus.util.Sort;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 *
 */
public class Population
{
	static final float MUTATION_RATE_DEFAULT = 0.1f;

	private Individual[] generation;
	private double populationFitness;
	private double mostFit;
	private double leastFit;

	float mutationRate;
	
	public Population( int populationSize, int individualSize, Random r )
	{
		generation = new Individual[populationSize]; 
		
		for( int i=0; i<populationSize; i++ )
			generation[i] = new Individual( individualSize, r );
		
		Sort.insertion( generation );
		populationFitness = calcFitness();
		mostFit = generation[0].getFitness();
		leastFit = generation[ populationSize - 1 ].getFitness(); 
	}
	
	public Population( Population previousGeneration, Random r )
	{
		generation = new Individual[ previousGeneration.getSize() ];
		
		for( int i=0; i<previousGeneration.getSize(); i++ )
			generation[i] = new Individual( previousGeneration.generation[ select() ], previousGeneration.generation[ select() ], r );
		
		Sort.insertion( generation );
		populationFitness = calcFitness();
		mostFit = generation[0].getFitness();
		leastFit = generation[ previousGeneration.getSize() - 1 ].getFitness();	
		}
	
	public Population( Population previousGeneration, int fitnessThreshold, Random r )
	{
		generation = new Individual[ previousGeneration.getSize() ];
		Individual[] breedingPool = new Individual[ fitnessThreshold ];
		
		for( int i=0; i<fitnessThreshold; i++ )
			breedingPool[i] = previousGeneration.generation[i];
		
		for( int i=0; i<previousGeneration.getSize(); i++ )
			generation[i] = new Individual( breedingPool[ select() ], breedingPool[ select() ], r );
	
		Sort.insertion( generation );
		populationFitness = calcFitness();
		mostFit = generation[0].getFitness();
		leastFit = generation[ previousGeneration.getSize() - 1 ].getFitness();	
	}
	
	
	private double calcFitness(  )
	{ return 0.0; }
	private int select( )
	{
		return (int) ( Math.random() * this.getSize() );
	}
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
