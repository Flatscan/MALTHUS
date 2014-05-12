package malthus.GCP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Graph
{
	int numNodes;
	private int[][] edgeList;
	
	
	public Graph( )
	{
		edgeList = new int[][]{ {-1} };
	}
	
	public Graph( String graphFilePath ) throws IOException
	{
		int size = getGraphSize( graphFilePath );
		edgeList = setEdges( graphFilePath, size );
	}
	
	
	public int getGraphSize( String fp ) throws IOException
	{
		BufferedReader br = new BufferedReader( new FileReader( fp ) );
		String line = br.readLine();
		numNodes = Integer.parseInt( line );
		int size = 0;
		while( (line = br.readLine()) != null )
			size++;
		
		br.close();
		return size;
	}
	
	public int[][] setEdges( String fp, int s ) throws IOException
	{
		BufferedReader br = new BufferedReader( new FileReader( fp ) );
		String line = br.readLine();
		
		int[][] edges = new int[2][s];
		int fromNode; 
		int toNode;
		
		for( int i=0; i<s; i++ )
		{
			line = br.readLine();
			fromNode = Integer.parseInt( line.substring( 0, line.indexOf( ' ' ) ) );
			toNode = Integer.parseInt( line.substring( line.indexOf( ' ' ) + 1 ) );
			
			edges[0][i] = fromNode;
			edges[1][i] = toNode;
		}
		br.close();
		return edges;
	}
	
	
	public int[][] getEdges()
	{
		return edgeList;
	}
	
	public int getNumNodes()
	{
		return numNodes;
	}
	
	
	public String toString( int[] coloring )
	{
		String graph = "\n";
		for( int i = 0; i<edgeList[1].length; i++ )
		{
			String edge;
			if( coloring[ edgeList[0][i] ] == coloring[ edgeList[1][i] ] )
				edge = "--x--";
			else
				edge = "--O--";
				
			graph = graph + edgeList[0][i] + edge + edgeList[1][i] + "\n" ;
		}	
		return graph;
	}
}
