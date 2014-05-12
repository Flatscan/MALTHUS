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


public class GCPIndividual extends Individual
{ 	
	private static Graph graph;
	static 
	{
		try 
		{
			graph = new Graph( "/home/hnguyen/Documents/MALTHUS/src/malthus/GCP/le450_5a.col" );
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	int maxColor;
	boolean isValidColoring;
	
	
	public GCPIndividual( )
	{
		super();
		this.maxColor = (int) Math.ceil(Math.random() * graph.getNumNodes());
		
	}

	
	@Override
	protected double calcFitness()
	{
		double invalidColoredEdges = 0;
		
		for(int i = 0; i<this.getGenotype().length; i++ )
		{
			int fromNode = graph.getEdges()[0][i] - 1;
			int toNode = graph.getEdges()[1][i] - 1;
			if( (this.getGenotype()[ fromNode ]).getValue() % this.maxColor == (this.getGenotype()[ toNode ]).getValue() % this.maxColor)
				invalidColoredEdges++;
		}
		
		if( invalidColoredEdges == 0 )
			isValidColoring = true;
		
		return (2 - ( this.maxColor / (double) graph.getNumNodes() ) - (  invalidColoredEdges / (double) this.getGenotype().length )) / 2.0;
	}
	
	@Override
	public GCPIndividual reproduce(Individual mate) {
		GCPIndividual child = (GCPIndividual)factory(false);
		child.setMaxColor(this.maxColor);
		
		Gene<?>[] genotype = this.crossover(mate);
		child.setGenotype(genotype);
		child.individualMutationRate = 0.03f;
		
		// Mutate
		child.mutate();
		
		return child;
	}
	
	public void setMaxColor( int c )
	{
		this.maxColor = c;
	}
	
	public boolean isValid()
	{
		return this.isValidColoring;
	}
	
	public int getMaxColor()
	{
		return this.maxColor;
	}
	
	public String toString()
	{
		return "FITNESS: " + fitness + "\tMAX COLOR: " + maxColor;
	}
}
