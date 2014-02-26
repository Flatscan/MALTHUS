package malthus;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 * 
 */
public interface Gene
{
	public void randomize();

	public boolean equals(Gene gene);
	public int hashCode();

	public int getValue();
}
