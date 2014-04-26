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

import malthus.Individual;


public class GCPIndividual extends Individual
{ 	
	protected double calcFitness()
	{
		double score = 0.0;
		for(int i = 0; i < this.getGenotype().length; i++)
			score = score + ( Math.pow(2.0, i * this.getGeneAt(i).getValue()) );
		return score;
	}
}
