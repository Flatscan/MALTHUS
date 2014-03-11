package malthus;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */

public class GCPGene extends Gene<Integer>
{
	private static int max_value = 50;

	@Override
	public double getValue()
	{
		return this.get();
	}

	@Override
	protected void randomize()
	{
		Random rand = new Random();
		return rand.nextInt(max_value);
	}


	public static setMax(int newMax)
	{
		max_value = newMax;
	}
}
