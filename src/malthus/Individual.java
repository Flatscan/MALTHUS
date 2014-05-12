/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 1.0
 */

package malthus;

import java.util.Arrays;

import malthus.util.ReflectiveUtils;
import malthus.util.Random.Random;


public abstract class Individual
{
	protected static Configuration conf = new Configuration();
	static 
	{
		try 
		{
			allele = conf.newInstance("allele", Allele.class);
			random = conf.newInstance("random", Random.class);
			
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected Gene<?>[] genotype;
	protected static Allele allele;
	protected double fitness;
	public float individualMutationRate;
	
	protected static Random random;
	
	
	public static Individual factory(boolean init) 
	{
		int size = conf.getInt("gene_size");
		Class<Individual> clazz = conf.getClass("individual", Individual.class);
	
		Individual newIndv = ReflectiveUtils.newInstance(clazz);
		if(init) 
		{
			Gene<?>[] genotype = new Gene<?>[size];
			for( int i = 0; i < genotype.length; i++ )
			{
				Gene<?> gene = ReflectiveUtils.newInstance( allele.map(i) );				
				genotype[i] = gene;
			}
			
			newIndv.setGenotype(genotype);
			newIndv.individualMutationRate = 0.03f;
		}
		
		return newIndv;
	}


	public Individual reproduce(Individual mate) 
	{
		Individual child = factory(false);
		Gene<?>[] genotype = this.crossover(mate);
		child.setGenotype(genotype);
		
		child.individualMutationRate = 0.03f;
		child.mutate();
		
		return child;
	}

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
	
	public void mutate( )
	{
		if(random.nextFloat() < this.individualMutationRate) 
		{
			int mutePnt = (int) Math.floor( random.nextFloat() * this.genotype.length );
			Gene<?> gene = ReflectiveUtils.newInstance( allele.map(mutePnt) );
			genotype[mutePnt] = gene;
		}
	}

	protected double calculateMutationRate( double m1, double m2 )
	{
		return ( m1 + m2 ) / 2;
	}
	
	public Individual clone() 
	{
		Individual newIndv = factory(false);
		newIndv.setGenotype(Arrays.copyOf(this.genotype, this.genotype.length));
		newIndv.individualMutationRate = this.individualMutationRate;
		return newIndv;
	}
	
	
	public Gene<?>[] getGenotype() 
	{
		return this.genotype;
	}
	
	public Gene<?> getGeneAt(int index) 
	{
		return this.genotype[index];
	}
	
	public void setGenotype(Gene<?>[] genotype) 
	{
		this.genotype = genotype;
		this.fitness = this.calcFitness();
	}
	
	public double getFitness() 
	{
		return this.fitness;
	}
	
	
 	protected abstract double calcFitness();
}
