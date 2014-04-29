package malthus.GCP;

import malthus.Gene;

import java.util.Random;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */

public class GCPGene extends Gene<Integer>
{
	private Integer value; 
	
	protected GCPGene( Integer val )
	{
		value = val;
	}

	@Override
	public double getValue()
	{
		return value;
	}

	public Integer randomize( int max )
	{
		Random rand = new Random();
		System.out.println( rand.nextInt() );
		return new Integer( rand.nextInt() * max );
	}

	@Override
	protected Integer randomize()
	{
		return new Integer( -1 );
	}
}
