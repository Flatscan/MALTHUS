package malthus;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */

public class SimpleGene implements Gene 
{
	private static final Configuration configuration = new Configuration();
	private int value;

	public SimpleGene()
	{
		this.value = -1;
	}
	
	public SimpleGene( int size )
	{
		this.value = (int) Math.floor( Math.random() ) * size;
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

	@Override
	public void randomize()
	{
		// TODO Auto-generated method stub
		
	}
}
