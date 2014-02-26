package malthus;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */
public interface Gene
{
	public abstract void randomize();

	public abstract boolean equals(Gene gene);
	public abstract int hashCode();

	public abstract double getValue();
}
