package malthus.GCP;

import malthus.Population;


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
		Population pop1 = Population.factory(true);
		for(int i = 0; i < 100; i++) {
			pop1 = pop1.nextGeneration();
			System.out.print("Score = " + pop1.getMeanPopulationFitness() + " ");
			System.out.println("Max = " + pop1.getFitest().getFitness() + " ");
		}
	}
}
