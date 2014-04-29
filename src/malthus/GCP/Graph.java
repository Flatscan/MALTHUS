package malthus.GCP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Graph
{
	private int[][] edgeList;
	
	public Graph( )
	{
		edgeList = new int[][]{ {-1} };
	}
	
	public Graph( String graphFilePath ) throws IOException
	{
		int size = getGraphSize( graphFilePath );
		edgeList = getEdges( graphFilePath, size );
	}
	
	public int getGraphSize( String fp ) throws IOException
	{
		BufferedReader br = new BufferedReader( new FileReader( fp ) );
		@SuppressWarnings("unused")
		String line = br.readLine();
		int size = 0;
		while( (line = br.readLine()) != null )
			size++;
		
		br.close();
		return size;
	}
	
	public int[][] getEdges( String fp, int s ) throws IOException
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
	
	
	public static void main( String[] args )
	{
		Graph g = null;
		int[] coloring = new int[451];
		for( int i=0; i<451; i++ )
		{
			coloring[i] = (int) Math.floor( Math.random() * 10 ); 
		}
		
		try
		{
			g = new Graph( "/Users/MalcolmRoss/Downloads/instances/le450_5a.col" );
			System.out.println( g.toString( coloring ) );
			System.out.println( '\07' );
			System.exit( 1 );
		} 
		catch (IOException e)
		{
			System.out.println( "ERROR: File not found." );
			e.printStackTrace();
		}
	}
}
