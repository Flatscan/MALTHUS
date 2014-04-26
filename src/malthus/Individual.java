package malthus;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */

import java.util.Arrays;

import malthus.util.ReflectiveUtils;
import malthus.util.Random.Random;


public abstract class Individual
{
	protected static Phenotype phenotype;
	protected static Random random;
	protected static Configuration conf = new Configuration();
	
	static 
	{
		try 
		{
			phenotype = conf.newInstance("phenotype", Phenotype.class);
			random = conf.newInstance("random", Random.class);
			
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
/**
 * A representation of the users data set/solution space as a Vector of Genes,
 * an interface that the user will have to implement for their particular needs.
 * 
 * @see #phenotype
 */
	protected Gene<?>[] genotype;


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
	protected float individualMutationRate;
	
	
	public static Individual factory(boolean init) {
		int size = conf.getInt("gene_size");
		Class<Individual> clazz = conf.getClass("individual", Individual.class);
	
		Individual newIndv = ReflectiveUtils.newInstance(clazz);
		if(init) {
			Gene<?>[] genotype = new Gene<?>[size];
			for( int i = 0; i < genotype.length; i++ )
			{
				Gene<?> gene = ReflectiveUtils.newInstance(phenotype.map(i));				
				genotype[i] = gene;
			}
			
			newIndv.setGenotype(genotype);
			newIndv.individualMutationRate = 0.03f;
		}
		
		return newIndv;
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
 * @throws ClassNotFoundException 
 */
	protected Gene<?>[] crossover( Individual p2)
	{
		Gene<?>[] newGenotype = new Gene<?>[this.genotype.length];
		int crossPnt = (int) Math.floor( random.nextFloat() * this.genotype.length ); 
		
		
		for( int i=0; i < crossPnt ; i++ )
		{	
			newGenotype[i] = this.genotype[i].clone();
		}
		
		for( int i=crossPnt; i < genotype.length ; i++ )
		{
			newGenotype[i] = p2.genotype[i].clone();
		}
		
		return newGenotype;
	}
	
	
/**
 * Randomly changes the value of a particular Gene less
 * than or equal to the geneMax.
 * @throws ClassNotFoundException 
 * 
 *  @see #geneMax
 */
	protected void mutate( )
	{
		// Mutating Decision
		if(random.nextFloat() < this.individualMutationRate) {
			int mutePnt = (int) Math.floor( random.nextFloat() * this.genotype.length );
			Gene<?> gene = ReflectiveUtils.newInstance(phenotype.map(mutePnt));
			genotype[mutePnt] = gene;
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
	
	
	public Individual clone() {
		Individual newIndv = factory(false);
		newIndv.setGenotype(Arrays.copyOf(this.genotype, this.genotype.length));
		newIndv.individualMutationRate = this.individualMutationRate;
		return newIndv;
	}
	
	
	public Individual reproduce(Individual mate) {
		Individual child = factory(false);
		Gene<?>[] genotype = this.crossover(mate);
		child.setGenotype(genotype);
		child.individualMutationRate = 0.03f;
		
		// Mutate
		child.mutate();
		
		return child;
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

	
	public void setGenotype(Gene<?>[] genotype) {
		this.genotype = genotype;
		this.fitness = this.calcFitness();
	}
	
	
	public Gene<?>[] getGenotype() {
		return this.genotype;
	}
	
	
	public Gene<?> getGeneAt(int index) {
		return this.genotype[index];
	}
}
