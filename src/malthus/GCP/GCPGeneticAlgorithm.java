package malthus.GCP;

import malthus.GeneticAlgorithm;


/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 *
 */

public class GCPGeneticAlgorithm extends GeneticAlgorithm
{	
	
	public static void main(String[] args)
	{ 
		GCPPopulation pop1 = new GCPPopulation();
		
		for( int i=0; i<pop1.getGeneration().length; i++ )
		{
			System.out.println(pop1.getGeneration()[i].getFitness());
		}
	}
}
