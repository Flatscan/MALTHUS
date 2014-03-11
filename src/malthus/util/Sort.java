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

	public static void heapSort( Individual[] individuals )
	{
		int n = individuals.length - 1;
		
		buildHeap( individuals, n );
		
		for( int i=n; i>0; i-- )
		{
			swap( individuals, 0, i );
			n--;
			maxHeap( individuals, 0, n );
		}
		
	}
	private static void buildHeap( Individual[] individuals, int n )
	{		
		for( int i= n/2; i>=0; i--)
			maxHeap( individuals, i, n);
	}
	private static void maxHeap( Individual[] individuals, int i, int n )
	{
		int left = i * 2;
		int right = i * 2 + 1;
		int largest = 0;
		
		if( left <= n && individuals[left].getFitness() > individuals[i].getFitness() )
			largest = left;
		else
			largest = i;
		
		if( right <= n && individuals[right].getFitness() > individuals[largest].getFitness() )
			largest = right;
		if( largest != i )
		{
			swap( individuals, i, largest );
			maxHeap( individuals, largest, n );
		}
	}
	
	public static void quick( Individual[] individuals )
	{
		sortRecursion( individuals, 0, individuals.length - 1 );
	}
	public static void sortRecursion( Individual[] individuals, int i, int j )
	{
		int high = i;
		int low = j;
		Individual pivot = individuals[ high + (low - high) / 2 ];
		
		while( high <= low )
		{
			while( individuals[high].getFitness() < pivot.getFitness() )
				high++;
			while( individuals[low].getFitness() > pivot.getFitness() )
				low++;
			if( high >= low )
			{
				swap( individuals, high, low );
				high++;
				low--;
			}
		}
		
		if( high < j )
			sortRecursion( individuals, high, j );
		if( i > low)
			sortRecursion( individuals, i, low );
	}
}
