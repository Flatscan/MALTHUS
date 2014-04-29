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

import java.util.Random;

import malthus.Individual;


public class GCPIndividual extends Individual
{ 	
		
	public GCPIndividual( )
	{
		fitness = -1;
	}
	
	public GCPIndividual( int chromosomeSize, Graph g )
	{
		genotype = new GCPGene[chromosomeSize];
		int maxColor = (int) Math.ceil(Math.random() * g.getNumNodes());
		
		for( int i=0; i<genotype.length; i++ )
			genotype[i] = new GCPGene( new Integer( (int) Math.ceil(Math.random() * maxColor) ) );
		
		fitness = this.calcFitness( g );
	}

	@Override
	protected double calcFitness()
	{
		return -1.0;
	}
	
	protected double calcFitness( Graph g )
	{
		double maxColor = 0;
		double invalidColoredEdges = 0;
		
		for(int i = 0; i<this.getGenotype().length; i++ )
		{
			int fromNode = g.getEdges()[0][i] - 1;
			int toNode = g.getEdges()[1][i] - 1;
			if( (this.getGenotype()[ fromNode ]).getValue() == (this.getGenotype()[ toNode ]).getValue() )
				invalidColoredEdges++;
			maxColor = Math.max( maxColor, this.getGenotype()[i].getValue() );
		}
		System.out.println( "INVALID: " + invalidColoredEdges + " MAX: " + maxColor );
		System.out.println( (maxColor / g.getNumNodes()) + (invalidColoredEdges / this.getGenotype().length) );
		return (maxColor / g.getNumNodes()) + (invalidColoredEdges / this.getGenotype().length);
	}
	
	public Integer randomize( int max )
	{
		Random rand = new Random();
		return new Integer( rand.nextInt() * max );
	}
	
	public String toString()
	{
		return "FITNESS: " + fitness;
	}
}
