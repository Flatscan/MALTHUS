package malthus.util;


/**
 * @author MalcolmRoss
 * @version 0.0
 *
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;



public class Parse
{
	
	public int[][] csvInt( String filePath, int tableWidth, int tableLength )
	{
		int[][] table = null;
		BufferedReader ingester = null;
		
		try
		{
			String line;
			table = new int[ tableWidth ][ tableLength ];
			ingester = new BufferedReader( new FileReader( filePath ) );
			
			while ( (line = ingester.readLine() ) != null ) 
			{
				int j = 0;
				int stringIndex = 0;
				
				for( int i=0; i<line.length(); i++ )
				{
					table[i][j] = Integer.parseInt( line.substring( stringIndex , line.indexOf( ',', stringIndex++ ) ) );
				}
				
				j++;
 			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println( "\n\nERROR: Invalid File Path.\n" );
		}
		catch (IOException e)
		{
			System.out.println( "\n\nERROR: Unable To Read Line.\n" );
		};
		
		try
		{
			ingester.close(); 	
		} 
		catch (IOException e)
		{
			System.out.println( "\n\nERROR: BufferedReader is invalid.\n" );
		};
		
		return table;
	}
}
