package malthus.GCP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * A representation of a graph as a list of
 * node pairs that correspond to the edges in
 * the graph.
 * 
 * This was chosen in part because this is the
 * way the graphs in DIMACS' benchmark graphs 
 * are formated, and also because evaluation 
 * of solutions can be done in linear time.
 */
public class Graph
{
	/**
	 * The number of nodes in the graph.
	 */
	private int numNodes;
	/**
	 * The number of edges in the graph,
	 * represented as a pair of nodes. 
	 */
	private int[][] edgeList;
	
	
	/**
	 * A dummy default constructor.
	 */
	public Graph( )
	{
		edgeList = new int[][]{ {-1} };
	}
	
	/**
	 * Creates a graph from a file based on the DIMACS
	 * benchmark graph format: the number of nodes in 
	 * the graph followed by a list of undirected edges.  
	 * 
	 * @param graphFilePath
	 * @throws IOException
	 */
	public Graph( String graphFilePath ) throws IOException
	{
		int size = getGraphSize( graphFilePath );
		edgeList = setEdges( graphFilePath, size );
	}
	
	
	/**
	 * Reads the first line of a file based on the DIMACS
	 * benchmark graph format which corresponds to the 
	 * number of nodes in the graph.
	 * 
	 * @param fp
	 * @return The number of nodes in the graph.
	 * @throws IOException
	 */
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
	
	/**
	 * Reads the rest of a file based on the DIMACS
	 * benchmark graph format which correspond to 
	 * the edges in the graph as pairs of nodes.
	 * 
	 * @param fp
	 * @param s
	 * @return The edge list as node pairs.
	 * @throws IOException
	 */
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

	
	/**
	 * @return The list of edges in the graph.
	 */
	public int[][] getEdges()
	{
		return edgeList;
	}
	
	/**
	 * @return The number of nodes in the graph.
	 */
	public int getNumNodes()
	{
		return numNodes;
	}
	
	
	/**
	 * @return The graph as a list of edges 
	 * separated by a marking to show if that
	 * edge is valid or not.
	 */
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
