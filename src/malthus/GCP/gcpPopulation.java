package malthus.GCP;

import malthus.Population;


/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 *
 */
public class gcpPopulation extends Population
{

	//DOES NOT FUNCTION
	protected double calcFitness(  )
	{ 
		return 0.0; 
	}
	
	protected int select( )
	{
		return (int) ( Math.random() * this.getSize() );
	}

	
}
