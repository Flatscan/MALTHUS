package malthus;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */
public abstract class Gene<T extends Comparable<T>>
{
	protected Configuration conf;

	private T data;

	public Gene() {
		this.data = randomize();	
	}


	public Gene(T data) {
		this.data = data;
	}


	public int compareTo(Gene<T> gene) {
		return this.data.compareTo(gene.get());
	}


	public T get() {
		return this.data;
	}


	protected abstract T randomize();
	public abstract double getValue();
}
