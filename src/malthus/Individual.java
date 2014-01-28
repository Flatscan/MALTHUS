package malthus;
/**
 * @author MalcolmRoss
 * @version 0.0
 *
 */
import java.util.BitSet;

public class Individual
{
	BitSet genome;
	double fitness;
	
	public Individual( )
	{}
	
	public Individual( Individual p1, Individual p2 )
	{}
	
	public BitSet crossover( Individual p1, Individual p2 )
	{ return new BitSet(); }
	
	public void mutate( BitSet g )
	{}
	
	public void mutate( BitSet g, double rate )
	{}
}
