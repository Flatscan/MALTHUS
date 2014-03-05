package malthus.GCP;

import malthus.util.Sort;
import malthus.Population;
import malthus.util.Random;
import malthus.GCP.GCPIndividual;


/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 *
 */
public class GCPPopulation extends Population
{


	static final float MUTATION_RATE_DEFAULT = 0.1f;

	private GCPIndividual[] generation;
	@SuppressWarnings("unused")
	private double populationFitness;
	@SuppressWarnings("unused")
	private double mostFit;
	@SuppressWarnings("unused")
	private double leastFit;

	float mutationRate;
	
	public GCPPopulation( int populationSize, int individualSize, Random r )
	{
		generation = new GCPIndividual[populationSize]; 
		
		for( int i=0; i<populationSize; i++ )
			generation[i] = new GCPIndividual( individualSize, r );
		
		Sort.insertion( generation );
		populationFitness = calcFitness();
		mostFit = generation[0].getFitness();
		leastFit = generation[ populationSize - 1 ].getFitness(); 
	}
	
	public GCPPopulation( Population previousGeneration, Random r )
	{
		generation = new GCPIndividual[ previousGeneration.getSize() ];
		
		for( int i=0; i<previousGeneration.getSize(); i++ )
			generation[i] = new GCPIndividual( (GCPIndividual) previousGeneration.generation[ select() ], (GCPIndividual) previousGeneration.generation[ select() ], r );

		Sort.insertion( generation );
		populationFitness = calcFitness();
		mostFit = generation[0].getFitness();
		leastFit = generation[ previousGeneration.getSize() - 1 ].getFitness();	
		}
	
	public GCPPopulation( GCPPopulation previousGeneration, int fitnessThreshold, Random r )
	{
		generation = new GCPIndividual[ previousGeneration.getSize() ];
		GCPIndividual[] breedingPool = new GCPIndividual[ fitnessThreshold ];
		
		for( int i=0; i<fitnessThreshold; i++ )
			breedingPool[i] = previousGeneration.generation[i];
		
		for( int i=0; i<previousGeneration.getSize(); i++ )
			generation[i] = new GCPIndividual( (GCPIndividual) breedingPool[ select() ], (GCPIndividual) breedingPool[ select() ], r );
	
		Sort.insertion( generation );
		populationFitness = calcFitness();
		mostFit = generation[0].getFitness();
		leastFit = generation[ previousGeneration.getSize() - 1 ].getFitness();	
	}
	
	
	protected  double calcFitness( )
	{
		return -1.0;
	}

	protected  int select( )
	{
		return -1;
	}	
	
}
