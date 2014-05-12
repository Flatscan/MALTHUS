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

public class GCPGeneticAlgorithm
{	
	
	public static int maxNumGen;
	
	public static void main(String[] args)
	{ 
		maxNumGen = 500;
		
		Population pop = Population.factory( true );
		String csv = "Generation,Max Fit, Min Fit, Mean Fit, Min Valid Color\n0," + pop + "," + ( (GCPPopulation) pop ).getMinValidColor() + "\n";
		
		for(int i = 0; i < maxNumGen; i++) 
		{
			pop = pop.nextGeneration();
			System.out.println(( (GCPPopulation) pop).getMinValidColor());
			csv = csv + (i+1) + "," + pop + "," + ( (GCPPopulation) pop ).getMinValidColor() + "\n";
		}

		writeData(csv);
	}
	
	
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
