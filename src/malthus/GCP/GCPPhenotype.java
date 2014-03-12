package malthus.GCP;


import malthus.Gene;
import malthus.Phenotype;


public class GCPPhenotype implements Phenotype
{
	@Override
	public Class<? extends Gene<?>> map(int index) {
		return GCPGene.class;
	}
}
