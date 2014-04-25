package malthus.GCP;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;


public class Graph
{
	private int[][] adjacencyList;
	
	public Graph( )
	{
		adjacencyList = new int[][]{ {-1} };
	}
	
	public Graph( int n, String graphFilePath ) throws IOException
	{
		BufferedReader br = new BufferedReader( new FileReader( graphFilePath ) );
		
		adjacencyList = new int[ Integer.parseInt( br.readLine() ) ][];
		preprocess( br );
		fillGraph( br );
	}
	
	public void preprocess( BufferedReader br ) throws NumberFormatException, IOException
	{
		int i = 0;
		int j = 1;
		String line;
		
		while( ( line = br.readLine() ) != null )
		{
			if( Integer.parseInt( line.substring( 0, ' ' ) ) == j )
				i++;
			else
			{
				adjacencyList[j-1] = new int[i];
				i = 1;
				j++;
			}
		}
		br.close();
	}
	
	public void fillGraph( BufferedReader br ) throws IOException
	{
		String line;
		
		for( int i=0, j=0 ; ( line = br.readLine() ) != null; )
		{
			if( Integer.parseInt( line.substring( 0, ' ' ) ) - 1 == i )
			{
				int toNode = Integer.parseInt( line.substring( line.indexOf( ' ' ) + 1 ) ) - 1;
				adjacencyList[i][j++] = toNode;
			}
			else
			{
				i++;
				j = 0;
				int toNode = Integer.parseInt( line.substring( line.indexOf( ' ' ) + 1 ) ) - 1;
				adjacencyList[i][j++] = toNode;
			}
		}
		br.close();
	}
}
