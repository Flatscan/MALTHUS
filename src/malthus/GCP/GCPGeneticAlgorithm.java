/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * 
 * @version 0.0
 */

package malthus.GCP;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import malthus.Population;

/**
 * An example of the main portion of code to be written
 * by the user of Malthus. 
 * 
 * It contains a single global variable for the number of 
 * generations to be run, a main method that calls all the
 * user written extensions of the standard GA operators
 * and a method for writing out data to a csv file.
 *  
 */
public class GCPGeneticAlgorithm
{	
	/**
	 * The number of generations to be run.
	 */
	public static int maxNumGen;
	
	
	/**
	 * Calls all the GCP specific genetic operators.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{ 
		maxNumGen = 500;
		
		GCPPopulation pop = (GCPPopulation) Population.factory( true );
		String csv = "Generation,Max Fit, Min Fit, Mean Fit, Min Valid Color\n0," + pop + "," + ( (GCPPopulation) pop ).getMinValidColor() + "\n";
		
		for(int i = 0; i < maxNumGen; i++) 
		{
			pop = pop.nextGeneration();
			System.out.println( pop.getMinValidColor() );
			csv = csv + (i+1) + "," + pop + "," + pop.getMinValidColor() + "\n";
		}

		writeData(csv);
	}
	
	
	/**
	 * Takes the data to be written as a String
	 * in csv format to be written to a file 
	 * whose name is procedurally generated.
	 * 
	 * @param s
	 */
	public static void writeData( String s )
	{
		Calendar c = Calendar.getInstance();
		try
		{
			FileWriter f = new FileWriter( "gcpData." + c.get(Calendar.HOUR_OF_DAY) + "_" + c.get(Calendar.DAY_OF_MONTH) + "_" + c.get(Calendar.MONTH) + "_" + c.get(Calendar.YEAR) + ".csv" );
			f.write( s );
			f.close();
		} 
		catch (IOException e)
		{
			System.out.println( "ERROR: Unable to write data to file." );
			e.printStackTrace();
		}
	}
}
