package malthus.GCP;

import malthus.Gene;

import java.util.Random;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */

public class GCPGene extends Gene<Boolean>
{
	@Override
	public double getValue()
	{
		if(this.get())
			return 1.0;
		return 0.0;
	}

	@Override
	protected Boolean randomize()
	{
		Random rand = new Random();
		return (rand.nextFloat() > 0.8f) ? true : false;
	}
}
