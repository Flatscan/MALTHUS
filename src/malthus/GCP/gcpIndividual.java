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
import java.util.BitSet;
import java.util.Vector;

import malthus.Individual;
import malthus.util.Map;
import malthus.util.Random;

public class gcpIndividual extends Individual<Integer>
{
/**
 * A representation of the users data set/solution space as a string of bits.
 * Will be extended in the future the Map functions will to take the user
 * data and find the best way to represent it based on empirical information.
 * 
 * @see #phenotype
 */
	private Vector<Integer> genotype;
/**
 * Takes the genotype of the caller and the argument parent Individuala and
 * fills in a new BitSet up to a point (determined by the population's Random
 * object) from the caller and the rest from the argument.
 * 	 
 * @param p2
 * @param random
 * @return BitSet newGenotype
 */
	private Vector<Integer> crossover( gcpIndividual p2, Random random)
	{
		BitSet newGenotype = new BitSet( genotype.length() );
		int crossPnt = (int) Math.floor( random.nextFloat() * genotype.length() ); 
			
		for( int i=0; i < crossPnt ; i++ )
			newGenotype.set( i, this.genotype.get( i ) );
		for( int i=crossPnt; i < genotype.length() ; i++ )
			newGenotype.set( i, p2.genotype.get( i ) );
			
		return newGenotype; 
	}
	
/**
 * Flips the value of random bits in an Individual's genome. 
 */
	private void mutate( )
	{
		for( int i=0; i < this.indMuteRate; i++ )
		{
			int mutePnt = (int) Math.floor( Math.random() * genotype.size() );
			genotype.set( mutePnt, !genotype.get( mutePnt ) );
		}
	}
}
