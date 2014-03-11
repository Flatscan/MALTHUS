package malthus;
/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */

import java.util.Vector;

public abstract class Individual
{
	protected Configuration conf;

/**
 * A representation of the users data set/solution space as a Vector of Genes,
 * an interface that the user will have to implement for their particular needs.
 * 
 * @see #phenotype
 */
	protected Vector<Gene> genotype;


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
	protected double individualMutationRate;
	
	
/**
<<<<<<< HEAD
 * No argument constructor. Not to be used!
 */
	public Individual()
	{
		genotype = new Vector<Gene>( 0 );
		fitness = -1;
		individualMutationRate = -1;
	}

	
/**
=======
>>>>>>> FETCH_HEAD
 * A simple copy constructor.
 * 
 * @param i individual to be copied. 
 */
	public Individual( Individual i )
	{
		this.conf = new Configuration();

		genotype = i.genotype.clone();
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
<<<<<<< HEAD
	public Individual( int size, Random r )
	{
		genotype = new Vector<Gene>( size );
		for( int i=0; i<size; i++ )
			( genotype.get( i ) ).randomize( r );
//		fitness = calcFitness();
		individualMutationRate = .01;
=======
	public Individual( )
	{
		this.conf = new Configuration();

		Map<Integer, Class<? extends Gene>> phenotype = (Map<Integer, Class<? extends Gene>>) this.conf.get("phenotype");
		int size = (Integer) this.conf.get("gene_size");
		
		// Randomize genotype
		this.genotype = new Vector<Gene>( size );
		for( int i = 0; i < size; i++ )
		{
			Gene gene = phenotype.get(i).getConstructor().newInstance();
			this.genotype.setElementAt(gene, i);
		}

		
		//Calculate fitness
		this.fitness = calFitness();
>>>>>>> FETCH_HEAD
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
	public Individual( Individual p1, Individual p2)
	{
		this.conf = new Configuration();

		genotype = p1.crossover(p2);
		fitness = calcFitness();

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
	protected Vector<Gene> crossover( Individual p2)
	{
		Random random = (Random) this.conf.get("random");

		Vector<Gene> newGenotype = new Vector<Gene>( genotype.size() );
		int crossPnt = (int) Math.floor( random.nextFloat() * genotype.size() ); 
		
		for( int i=0; i < crossPnt ; i++ )
			newGenotype.setElementAt( this.genotype.elementAt( i ).clone(), i);
		for( int i=crossPnt; i < genotype.size() ; i++ )
			newGenotype.setElementAt( p2.genotype.elementAt( i ).clone(), i);
		
		return newGenotype;
	}
	
	
/**
 * Randomly changes the value of a particular Gene less
 * than or equal to the geneMax.
 * 
 *  @see #geneMax
 */
	@SuppressWarnings("unused")
<<<<<<< HEAD
	private void mutate( double range, Random r )
	{
		for( int i=0; i < this.individualMutationRate; i++ )
		{
			int mutePnt = (int) Math.floor( Math.random() * genotype.size() );
			( genotype.get( mutePnt ) ).randomize( r );;
=======
	protected void mutate( )
	{
		for( int i=0; i < this.individualMutationRate; i++ )
		{
			Random random = (Random) this.conf.get("random");
			Map<Integer, Class<? extends Gene>> phenotype = this.conf.get("phenotype");

			// Randomize a gene
			int mutePnt = (int) Math.floor( random.nextFloat() * genotype.size() );
			Gene gene = phenotype.get(i).getConstructor().newInstance();
			genotype.set( mutePnt, gene);
>>>>>>> FETCH_HEAD
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
	protected double calculateMutationRate( double m1, double m2 )
	{
		return ( m1 + m2 ) / 2;
	}


/**
 *
 *
 */
 	protected abstract double calcFitness();

	
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
