package malthus.GCP;

import java.io.IOException;

import malthus.Population;


/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 *
 */

public class GCPGeneticAlgorithm
{	
	public static void main(String[] args) throws IOException
	{ 
		Graph g = new Graph( "/Users/MalcolmRoss/Downloads/instances/le450_5a.col" );
		GCPIndividual[] population = new GCPIndividual[100];

		System.out.println( "\n---INDIVIDUALS---\n" );
		for( int i=0; i<100; i++ )
			population[i] = new GCPIndividual( g.getNumNodes(), g );
		
		double maxFitness = 0.0;
		double meanFitness = 0.0;
		double minFitness = Double.MAX_VALUE;
		for( int i=0; i<100; i++ )
		{
			double f = population[i].getFitness();
			maxFitness = Math.max( maxFitness, f );
			meanFitness += f;
			minFitness = Math.min( minFitness, f );
		}
		meanFitness = meanFitness / 100;
		
		System.out.println( "\n---POPULATION---\nMAX: " + maxFitness + "\nMIN: " + minFitness + "\nMEAN: " + meanFitness + "\n" );
	}
}
