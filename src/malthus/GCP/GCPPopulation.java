package malthus.GCP;

import malthus.Individual;
import malthus.Population;
import malthus.GCP.GCPIndividual;

import java.util.Random;

/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 *
 */


public class GCPPopulation extends Population
{
	protected int[] selectIndividuals( )
	{
		int[] selected;

		selected = new int[this.generation.length / 2];
		for(int i = 0; i < selected.length; i++)
			selected[i] = i;

		return selected;
	}		


	protected int selectParent()
	{
		Random rand = new Random();
		return rand.nextInt(this.generation.length / 2);
	}
}
