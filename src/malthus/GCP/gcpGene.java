package malthus.GCP;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */


import malthus.Gene;


public class gcpGene implements Gene 
{
	private static final Configuration configuration = new Configuration();
	private int value;


	public gcpGene(int value)
	{
		this.value = value;
	}


	public void randomize()
	{
		Random random = configuration.getClass("random")
					.getConstructor().newInstance();
		return random.nextInt();
	}


	public boolean equals(Gene gene) 
	{
		return (this.value == gene.getValue());
	}


	public int hashCode()
	{
		return this.value;
	}

	
	public int getValue()
	{
		return this.value;
	}
}
