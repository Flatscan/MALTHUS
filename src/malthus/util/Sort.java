package malthus.util;
/**
 * @author MalcolmRoss
 * @version 0.0
 *
 */

import malthus.Individual;

public class Sort
{
	public static void insertion( Individual[] individuals ) 
	{
		for(int i = 1; i < individuals.length; i++) 
		{
			int j = i;
			
			while( j>0 && individuals[j].getFitness()<individuals[j - 1].getFitness() ) 
			{
				swap( individuals, j, j - 1 );
				j--;
			}
		}
	}
	
	
	private static void swap( Individual[] arr, int a, int b )
	{
		Individual temp = arr[a];
		
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
