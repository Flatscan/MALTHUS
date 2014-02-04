package malthus;
/**
 * @author MalcolmRoss
 * @author Hao Nguyen
 * @version 0.0
 * 
 * CURRENT TO DO:	-Write Map class/functions
 * 					-Write a calcFitness function
 * 					-Write an enum DEFAULT class for testing constants
 *
 */
import java.util.BitSet;
import malthus.util.Map;
import malthus.util.Random;

public class Individual
{
/**
 * A representation of the users data set/solution space as a string of bits.
 * 
 * @see #phenotype
 */
	private BitSet genotype;
/**
 * How a particular genotype is mapped to the users representation of the data.
 * 
 * @see #genotype
 */
	public static Map phenotype;
/**
 * How quality of a solution that determines whether it ends up in the 
 * mating pool.
 * 
 * @see #testFitness
 * @see #selectBreedingPool()
 */
	private double fitness;
/**
 *  The number of bits an Individual mutates when mutate() is called.
 *  
 *  @see #mutate()
 */
	private int indMuteRate;
	
/**
 * 	Default constructor.
 */
	public Individual( )
	{
		genotype = new BitSet( 0 );
//		phenotype = new Map(0);
		fitness = -1;
		indMuteRate = -1;
	}
	
/**
 * Copy constructor.
 * 
 * @param i individual to be copied. 
 */
	public Individual( Individual i )
	{
		genotype = i.genotype;
		fitness = i.fitness;
		indMuteRate = i.indMuteRate;
	}
/**
 * Real "default" constructor to only be used for initializing the members 
 * of the first generation.
 * 
 * @param size length of the solution string.
 * @param r Random object used by the population. 
 */
	public Individual( int size, Random r )
	{
		genotype = new BitSet( size );
		for( int i=0; i<size; i++ )
			genotype.get( i, (int) r.nextDouble() * 2 );
//		fitness = calcFitness();
//		indMuteRate = ???;
	}
/**
 * Standard constructor. Takes two parent individuals and does crossover at 
 * a random bit (rounded down) along the genotype, calculates the fitness based
 * on the population objective function and then calculates the child's individual 
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
		indMuteRate = (int) calcMuteRate( p1.indMuteRate, p2.indMuteRate ) * genotype.size();
	}
/**
 * Takes the genotype of the caller and the argument parent Individual and
 * fills in a new BitSet up to a point (determined by the population's Random
 * object) from the caller and the rest from the argument.
 * 
 * @param p2
 * @param random
 * @return BitSet newGenotype
 */
	public BitSet crossover( Individual p2, Random random)
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
 * 
 */
	public void mutate( )
	{
		for( int i=0; i < this.indMuteRate; i++ )
		{
			int mutePnt = (int) Math.floor( Math.random() * genotype.size() );
			genotype.set( mutePnt, !genotype.get( mutePnt ) );
		}
	}
/**
 * Takes two doubles that correspond to the mutation rate of two parent Individuals
 * and returns the average of them to be used as the mutation rate of the child.
 * @param m1
 * @param m2
 * @return double Average(m1,m2)
 * @see #indMuteRate
 */
	private double calcMuteRate( double m1, double m2 )
	{
		return ( m1 + m2 ) / 2;
	}
}
