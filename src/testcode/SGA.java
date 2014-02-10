package testcode;

/*
 * This will be a test class to run a simple as possible GA 
 * to get a bottom reference point for future versions of the
 * code.
 * 
 */

public class SGA
{
	public static final int POPULATION_SIZE = 30;
	public static final int GENOME_SIZE = 30;
//	public static final int CROSSOVER_POINT = 10;
	public static final double MUTATION_RATE = .0333;
	public static final int NUMBER_OF_GENERATIONS = 1000;
	
	public static double[] generation = new double[NUMBER_OF_GENERATIONS];
	public static Individual[] population = new Individual[POPULATION_SIZE];
	public static double sumFitness = 0;
	public static int currentGeneration = 0;
	
	public static int select( double sumFitness )
	{
		double rand = Math.random() * sumFitness;
		double acc = 0.0;
		
		int i = 0;
		while(acc <= rand && i < POPULATION_SIZE) 
		{ 
			acc += population[i].fitness;
			i++;
		}
		
		return i - 1;
	}
	
	public static void generateNewPopulation( Individual[] pop )
	{
		sumFitness = 0;
		
		for( int i=0; i<POPULATION_SIZE; i++ )
			sumFitness += pop[i].fitness;
		
		generation[ currentGeneration++ ] = sumFitness / POPULATION_SIZE; 
		
		Individual[] tempGen = new Individual[POPULATION_SIZE];
		for( int i=0; i<POPULATION_SIZE; i++ )
		{
			tempGen[i] = new Individual( pop[ select(sumFitness) ], pop[ select(sumFitness) ] );
			if( Math.random() < MUTATION_RATE )
				tempGen[i].genotype[ (int) Math.random() * GENOME_SIZE ] = Math.abs( tempGen[i].genotype[ (int) Math.random() * GENOME_SIZE ] - 1 ); 
		}
		population = tempGen;
	}
	public static long decode( Individual ind )
	{
		long decodedValue = 0;
		
		for( int i=0; i<GENOME_SIZE; i++ )
			decodedValue += ind.genotype[i] * Math.pow( 2.0, (double) i );
		
		return decodedValue;
	}
	public static double evaluateFitness( long v )
	{
		return Math.pow( ( v / Math.pow( 2.0, (double) GENOME_SIZE )), 10);
	}
	
	public static void main(String[] args)
	{
		for( int i=0; i<POPULATION_SIZE; i++ )
			population[i] = new Individual();
//		for( int i=0; i<POPULATION_SIZE; i++ )
//			System.out.println( "POPULATION[" + i + "]: " + population[i] );
		
		for( int i=0; i<POPULATION_SIZE; i++ )
			sumFitness += population[i].fitness;
		
		generation[ currentGeneration++ ] = sumFitness / POPULATION_SIZE;
		
		System.out.println( generation[ currentGeneration - 1 ] );
		
		while( currentGeneration < NUMBER_OF_GENERATIONS )
		{
			generateNewPopulation( population );
			System.out.println( generation[ currentGeneration - 1 ] );
		}
	}
	 
	
	public static class Individual
	{
		public int[] genotype;
		public double fitness;
		
		public Individual( )
		{
			genotype = new int[ GENOME_SIZE ];
			
			for( int i=0; i<GENOME_SIZE; i++ )
				genotype[i] = (int) Math.floor( Math.random() * 1.5 );
			fitness = evaluateFitness( decode( this ) );
		}
		
		
		public Individual( Individual i1, Individual i2 )

		{
			genotype = new int[ GENOME_SIZE ];

			int crossoverPoint = (int) Math.floor( Math.random() * GENOME_SIZE );
			
			for( int i=0; i<5; i++ )
				genotype[i] = i1.genotype[i];
			for( int i=5; i<GENOME_SIZE; i++ )
				genotype[i] = i2.genotype[i];
			
			fitness = evaluateFitness( decode( this ) );
		}
		
		public String toString( )
		{
			String indi = "";
			
			for( int i=0; i<GENOME_SIZE; i++ )
				indi = genotype[i] + indi;
			
			indi += "\t, " + decode(this) + ",\t" + fitness;
			
			return indi;
		}
	}
	
	
	
	
}
