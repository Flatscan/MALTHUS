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
	private static int max_value = 1000;
	

	@Override
	public double getValue()
	{
		return this.get();
	}

	@Override
	protected Integer randomize()
	{
		Random rand = new Random();
		return rand.nextInt(max_value);
	}


	public static void setMax(int newMax)
	{
		max_value = newMax;
	}
}
