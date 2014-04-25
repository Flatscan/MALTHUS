package malthus.GCP;

import malthus.GeneticAlgorithm;
import malthus.Population;


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
		Population pop1 = Population.factory(true);
		for(int i = 0; i < 100; i++) {
			pop1 = pop1.nextGeneration();
			System.out.println(pop1.getMeanPopulationFitness() + " " + pop1.getPopulationFitness());
		}
	}
}
