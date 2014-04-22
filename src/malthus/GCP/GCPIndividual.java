package malthus.GCP;
/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 * CURRENT TO DO (week 8):	-Write Map class/functions
 * 							-Write a calcFitness function
 * 							-Write an enum DEFAULT class for testing constants
 *
 */

import malthus.Gene;
import malthus.Individual;


public class GCPIndividual extends Individual
{ 	
	public GCPIndividual() throws ClassNotFoundException 
	{
		super();
	}

	protected double calcFitness()
	{
		double invalidColoring = 0;
		
		for(int i=0; i<genotype.size(); i++)
		{
			invalidColoring += checkColoring( genotype.elementAt( i ) );
		}
			
		return invalidColoring / genotype.size();
	}

	private int checkColoring(Gene<?> elementAt)
	{
		return 1;
		
	}
}
