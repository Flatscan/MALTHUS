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
		return this.genotype[0].getValue() + this.genotype[1].getValue();
	}
}
