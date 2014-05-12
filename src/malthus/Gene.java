/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */

package malthus;

import malthus.util.ReflectiveUtils;


public abstract class Gene<T extends Comparable<T>>
{
	protected static Configuration conf = new Configuration();

	private T data;
	
	public Gene() 
	{
		this.setData( randomize() );
	}

	public int compareTo( Gene<T> gene ) 
	{
		return this.data.compareTo(gene.getData());
	}

	public T getData() 
	{
		return this.data;
	}
	
	
	protected void setData( T data ) 
	{
		this.data = data;
	}
	
	
	@SuppressWarnings( "unchecked" )
	public Class<T> getType() 
	{
		return (Class<T>) this.getData().getClass();
	}
	
	
	@SuppressWarnings( "unchecked" )
	public Gene<T> clone()
	{
		Gene<T> newGene = ReflectiveUtils.newInstance( this.getClass() );
		newGene.setData( this.getData() );
		return newGene;
	}
	

	protected abstract T randomize();
	public abstract double getValue();
	
	
}
