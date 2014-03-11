package malthus.GCP;

import malthus.util.Sort;
import malthus.Population;
import malthus.util.Random;
import malthus.GCP.GCPIndividual;


/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 *
 */
public class GCPPopulation extends Population
{
	protected  int selectIndividuals( )
	{
		Individual[] selected;

		selected = new Individual[this.generation.length / 2];
		for(int i = 0; i < selected.length; i++)
			selected[i] = this.generation[i];

		return selected;
	}		


	protected int selectParent()
	{
		Random rand = new Random();
		return rand.nextInt(this.generation.length / 2);
	}
}
