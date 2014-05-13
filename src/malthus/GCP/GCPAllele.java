package malthus.GCP;


import malthus.Gene;
import malthus.Allele;

/**
 * The map from a particular GCPGene
 * and its Class. 
 */
public class GCPAllele implements Allele
{
	/**
	 * Retrieves the Class of the GCPGene.
	 * 
	 * @return The class of the GCPGene.
	 */
	@Override
	public Class<? extends Gene<?>> map(int index) 
	{
		return GCPGene.class;
	}
}
