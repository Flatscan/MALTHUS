package malthus.GCP;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 *
 */

public class GCPGeneticAlgorithm 
{	
	
	public static void main(String[] args)
	{ 
		final int POPULATION_SIZE = Integer.parseInt( args[0] );
		final int MAX_GENERATION = Integer.parseInt( args[1] );
		
		int currentGeneration = 0;
		
		System.out.println( "TEST!: " + POPULATION_SIZE + MAX_GENERATION + currentGeneration );
	}
}
