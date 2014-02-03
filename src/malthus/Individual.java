package malthus;
/**
 * @author MalcolmRoss
 * @version 0.0
 *
 */
import java.util.BitSet;
import malthus.util.Random;

public class Individual
{
	BitSet genome;
	double fitness;
	int indMuteRate;
	
	public Individual( )
	{
		genome = new BitSet( 0 );
		fitness = -1;
		indMuteRate = -1;
	}
	
	public Individual( Individual i )
	{
		genome = i.genome;
		fitness = i.fitness;
		indMuteRate = i.indMuteRate;
	}
	
	public Individual( int size, Random r )
	{
		genome = new BitSet( size );
		for( int i=0; i<size; i++ )
			genome.get( i, (int) r.nextDouble() * 2 );
//		fitness = calcFitness();
//		indMuteRate = ???;
	}
	
	public Individual( Individual p1, Individual p2, Random random)
	{
		genome = p1.crossover( p2, random );
//		fitness = calcFitness();
		indMuteRate = (int) calcMuteRate( p1.indMuteRate, p2.indMuteRate ) * genome.size();
	}
	
	public BitSet crossover( Individual p2, Random random)
	{
		BitSet newGenome = new BitSet( genome.length() );
		int crossPnt = (int) Math.floor( random.nextFloat() * genome.length() ); 
		
		for( int i=0; i < crossPnt ; i++ )
			newGenome.set( i, this.genome.get( i ) );
		for( int i=crossPnt; i < genome.length() ; i++ )
			newGenome.set( i, p2.genome.get( i ) );
		
		return newGenome; 
	}
	
	public void mutate( )
	{
		for( int i=0; i < this.indMuteRate; i++ )
		{
			int mutePnt = (int) Math.floor( Math.random() * genome.size() );
			genome.set( mutePnt, !genome.get( mutePnt ) );
		}
	}
	private double calcMuteRate( double m1, double m2 )
	{
		return ( m1 + m2 ) / 2;
	}
}
