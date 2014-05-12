package malthus.GCP;


import malthus.Gene;
import malthus.Allele;


public class GCPAllele implements Allele
{
	@Override
	public Class<? extends Gene<?>> map(int index) 
	{
		return GCPGene.class;
	}
}
