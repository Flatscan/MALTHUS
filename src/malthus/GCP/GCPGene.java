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
	private static int max = 450;
	
	@Override
	public double getValue()
	{
		return this.get();
	}

	@Override
	protected Integer randomize()
	{
		Random rand = new Random();
		return rand.nextInt(max);
	}
}
