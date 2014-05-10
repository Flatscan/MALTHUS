package malthus;

import malthus.util.ReflectiveUtils;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */
public abstract class Gene<T extends Comparable<T>>
{
	protected static Configuration conf = new Configuration();

	private T data;
	
	public Gene() {
		this.set(randomize());
	}
	
	@SuppressWarnings("unchecked")
	public Gene<T> clone() {
		Gene<T> newGene = ReflectiveUtils.newInstance(this.getClass());
		newGene.set(this.get());
		return newGene;
	}


	public int compareTo(Gene<T> gene) {
		return this.data.compareTo(gene.get());
	}


	public T get() {
		return this.data;
	}
	
	
	protected void set(T data) {
		this.data = data;
	}
	
	
	@SuppressWarnings("unchecked")
	public Class<T> getType() {
		return (Class<T>) this.data.getClass();
	}
	

	protected abstract T randomize();
	public abstract double getValue();
}
