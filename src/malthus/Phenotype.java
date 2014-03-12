package malthus;

public interface Phenotype
{
	public Class<? extends Gene<?>> map(int index);
}
