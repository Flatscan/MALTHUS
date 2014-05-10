package malthus.util.Random;


public interface Random {
	public int nextInt();
	public double nextDouble();
	public float nextFloat();

	public void setSeed(long seed);
}
