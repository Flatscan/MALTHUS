package malthus;
/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */

import java.util.Vector;

import malthus.SimpleGene;
import malthus.util.Random;

public abstract class Individual
{
/**
 * A representation of the users data set/solution space as a Vector of Genes,
 * an interface that the user will have to implement for their particular needs.
 * 
 * @see #phenotype
 */
	protected Vector<Gene> genotype;

/**
 * The maximum value a particular Gene can have when mapped onto the natural 
 * numbers, counting from zero.
 * 
 * @see #genotype
 */
	private int geneMax;
	
/**
 * The relative quality of a solution based on the user's implementation of the
 * testFitness function.
 * 
 * @see #testFitness
 * @see #selectBreedingPool()
 */
	protected double fitness;
	
	
/**
 *  The number of Genes in an Individual that mutates when mutate() is called.
 *  This happens based on the populationMutationRate. 
 *  
 *  @see #mutate()
 */
	protected int individualMutationRate;
	
	
/**
 * No argument constructor. Not to be used!
 */
	public Individual( )
	{
		genotype = new Vector<Gene>( 0 );
		fitness = -1;
		individualMutationRate = -1;
	}

	
/**
 * A simple copy constructor.
 * 
 * @param i individual to be copied. 
 */
	public Individual( Individual i )
	{
		genotype = i.genotype;
		fitness = i.fitness;
		individualMutationRate = i.individualMutationRate;
	}

	
/**
 * Real "default" constructor, to be used only for initializing the  
 * of the first Population generation by randomly creating Individuals.
 * Ideally, a user will overload this so that they can use a Gene of 
 * their choosing. 
 * 
 * @param size length of the solution string.
 * @param r Random object used by the Population. 
 */
	public Individual( int size, int range , Random r )
	{
		geneMax = range;
		genotype = new Vector<Gene>( size );
		for( int i=0; i<size; i++ )
			genotype.set( i, new SimpleGene( range ) );
//		fitness = calcFitness();
//		indMuteRate = ???;
	}
	
	
/**
 * Standard constructor. Takes two parent Individuals and calls crossover() at 
 * a random Gene (rounded down) along the genotype. It then calculates the fitness 
 * based on the population  testFunction() and then calculates the child's individual 
 * mutation rate as the average of its parents.
 * 
 * @param p1 parent providing the leading genotype of the child.
 * @param p2  parent providing the tail genotype of the child. 
 * @param random Random object used by the population.
 * @see #crossover(Individual, Random)
 * @see #calcFitness()
 * @see #mutate()
 */
	public Individual( Individual p1, Individual p2, Random random)
	{
		genotype = p1.crossover( p2, random );
//		fitness = calcFitness();
		individualMutationRate = (int) calculateMutationRate( p1.individualMutationRate, p2.individualMutationRate ) * genotype.size();
	}
	
	
/**
 * This function takes the genotype of the caller and the argument parent 
 * Individuals and fills in a new Vector<Gene> up to a point determined by 
 * the population's Random object. The first portion comes from the caller 
 * Individual and the rest from the argument Individual.
 * 
 * @param p2
 * @param random
 * @return Vector<Gene> newGenotype
 */
	private Vector<Gene> crossover( Individual p2, Random random)
	{
		Vector<Gene> newGenotype = new Vector<Gene>( genotype.size() );
		int crossPnt = (int) Math.floor( random.nextFloat() * genotype.size() ); 
		
		for( int i=0; i < crossPnt ; i++ )
			newGenotype.set( i, (SimpleGene) this.genotype.elementAt( i ) );
		for( int i=crossPnt; i < genotype.size() ; i++ )
			newGenotype.set( i, (SimpleGene) p2.genotype.elementAt( i ) );
		
		return newGenotype; 
	}
	
	
/**
 * Randomly changes the value of a particular Gene less
 * than or equal to the geneMax.
 * 
 *  @see #geneMax
 */
	@SuppressWarnings("unused")
	private void mutate( )
	{
		for( int i=0; i < this.individualMutationRate; i++ )
		{
			int mutePnt = (int) Math.floor( Math.random() * genotype.size() );
			genotype.set( mutePnt, new SimpleGene( (int) Math.floor( Math.random() * this.geneMax ) ) );
		}
	}

	
/**
 * Takes two doubles that correspond to the mutation rate of the parent 
 * Individuals and returns the average of them to be used as the 
 * mutation rate of the child Individual.
 * 
 * @param m1
 * @param m2
 * @return double Average(m1,m2)
 * @see #indMuteRate
 */
	private double calculateMutationRate( double m1, double m2 )
	{
		return ( m1 + m2 ) / 2;
	}

	
/**
 * Returns the fitness of the caller Individual.
 * 
 * @return fitness
 */
	public double getFitness() 
	{
		return this.fitness;
	}

}
