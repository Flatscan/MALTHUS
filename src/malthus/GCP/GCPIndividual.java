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
import java.util.Vector;

import malthus.Individual;
import malthus.SimpleGene;
import malthus.util.Random;

public class GCPIndividual extends Individual
{
/**
 * A representation of the users data set/solution space as a string of bits.
 * Will be extended in the future the Map functions will to take the user
 * data and find the best way to represent it based on empirical information.
 * 
 * @see #phenotype
 */
	private Vector<SimpleGene> genotype;
	
/**
 * Represents to portion of the Individuals genotype that
 * is mutated should it be selected.
 * 
 * @see #genotype
 */
	private double individualMutationRate = 0.01;

	
public GCPIndividual( int size, Random r )
{
	genotype = new Vector<SimpleGene>( size );
	for( int i=0; i<size; i++ )
		( genotype.get( i ) ).randomize( r );
//	fitness = calcFitness();
	individualMutationRate = .01;
}


public GCPIndividual( GCPIndividual p1, GCPIndividual p2, Random r )
{
	genotype = p1.crossover( p2, r );
//	fitness = calcFitness();
	individualMutationRate = (int) calculateMutationRate( p1.individualMutationRate, p2.individualMutationRate ) * genotype.size();
	
}






/**
 * Takes the genotype of the caller and the argument parent Individuals and
 * fills in a new Vector<Gene> up to a point (determined by the population's Random
 * object) from the caller and the rest from the argument.
 * 	 
 * @param p2
 * @param random
 * @return Vector<SimpleGene> newGenotype
 */
	private Vector<SimpleGene> crossover( GCPIndividual p2, Random random)
	{
		Vector<SimpleGene> newGenotype = new Vector<SimpleGene>( genotype.size() );
		int crossPnt = (int) Math.floor( random.nextFloat() * genotype.size() ); 
			
		for( int i=0; i < crossPnt ; i++ )
			newGenotype.set( i, this.genotype.get( i ) );
		for( int i=crossPnt; i < genotype.size() ; i++ )
			newGenotype.set( i, p2.genotype.get( i ) );
			
		return newGenotype; 
	}
	
/**
 * Randomly selects a Gene in an Individual's genotype
 * and then calls that Gene's randomize function. The 
 * number of Genes mutated is equal to the Individual's
 * mutation rate multiplied by the size of its genotype.
 * 
 * @param range
 * @param r
 * @see genotype
 * @see individualMutationRate
 */
	@SuppressWarnings("unused")
	private void mutate( int range, Random r )
	{
		int rate = (int) Math.floor( this.individualMutationRate * genotype.size() );
		
		for( int i=0; i < rate; i++ )
		{
			int mutationPoint = (int) Math.floor( r.nextDouble() * genotype.size() );
			( genotype.get( mutationPoint ) ).randomize( r );
		}
	}

}
