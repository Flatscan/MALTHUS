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

import java.io.IOException;

import malthus.Gene;
import malthus.Individual;


/** 
 * A particular solution to the GCP for a given graph. 
 * 
 * @see gcp.txt
 */
public class GCPIndividual extends Individual
{ 	
	/**
	 * The graph (represented as an edge lsit) that 
	 * all individuals in a population are possible
	 * solutions to.
	 */
	private static Graph phenotype;
	static 
	{
		try 
		{
			phenotype = new Graph( "/home/hnguyen/Documents/MALTHUS/src/malthus/GCP/le450_5a.col" );
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * The number of colors that this particular
	 * Individual is using to color the graph.
	 * 
	 * @see gcp.txt
	 */
	int maxColor;
	/**
	 * True if the Individual is a valid coloring
	 * (ie not neighboring edges are the same 
	 * color) of the give graph.
	 * 
	 * False if any two neighboring nodes are the
	 * same color. 
	 * 
	 * @see gcp.txt
	 */
	boolean isValidColoring;
	
	
	/**
	 * Calls the factory for the standard individual 
	 * and then sets the maxColor to some random 
	 * value less than or equal to the number of nodes
	 * in the graph.
	 */
	public GCPIndividual( )
	{
		super();
		this.maxColor = (int) Math.ceil( Math.random() * phenotype.getNumNodes() );
	}

	
	/**
	 * Iterates over the individual to count the number of 
	 * invalid edges then sets if the coloring is valid or
	 * not. 
	 * 
	 * Returns the fitness as the difference of the max
	 * color of the individual the number of invalid edges
	 * divided (both normalized) and subtracted from one. 
	 * 
	 * @return The fitness of a solution.
	 */
	@Override
	protected double calcFitness()
	{
		double invalidColoredEdges = 0;
		
		for(int i = 0; i<this.getGenotype().length; i++ )
		{
			int fromNode = phenotype.getEdges()[0][i] - 1;
			int toNode = phenotype.getEdges()[1][i] - 1;
			if( (this.getGenotype()[ fromNode ]).getValue() % this.maxColor == (this.getGenotype()[ toNode ]).getValue() % this.maxColor)
				invalidColoredEdges++;
		}
		
		if( invalidColoredEdges == 0 )
			isValidColoring = true;
		
		return (1 - ( this.maxColor / (double) phenotype.getNumNodes() ) - ( invalidColoredEdges / (double) this.getGenotype().length )) / 2.0;
	}
	
	
	/**
	 * Creates a new GCPIndividual using the Individual
	 * factory, setting the max color to that of the 
	 * calling parent. 
	 * 
	 * The child's genome is then set to the result
	 * of the crossover of both parents and then
	 * mutates the child.
	 * 
	 * @return The product of crossover and mutation from
	 * the two parent individuals.
	 * @see crossover
	 */
	@Override
	public GCPIndividual reproduce( Individual mate ) 
	{
		GCPIndividual child = (GCPIndividual)factory(false);
		child.setMaxColor( this.maxColor );
		
		Gene<?>[] genotype = this.crossover( mate );
		child.setGenotype(genotype);
		child.individualMutationRate = mate.individualMutationRate;
		
		child.mutate();
		
		return child;
	}

	
	/**
	 * @return If the coloring is valid or not.
	 */
	public boolean isValid()
	{
		return this.isValidColoring;
	}
	
	/**
	 * @return The max color of the individual.
	 */
	public int getMaxColor()
	{
		return this.maxColor;
	}
	
	/**
	 * @param The value to set the max color to.
	 */
	public void setMaxColor( int c )
	{
		this.maxColor = c;
	}
	
	
	/**
	 * @return The max color of the individual.
	 */
	public String toString()
	{
		return "FITNESS: " + fitness + "\tMAX COLOR: " + maxColor;
	}
}
