package malthus.GCP;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import malthus.Population;


/**
 * @author MalcolmRoss
 * @author HaoNguyen
 * @version 0.0
 *
 */



public class GCPGeneticAlgorithm
{	
	
	public static int maxNumGenerations = 100;
	
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
	
	
	public static void main(String[] args)
	{ 
		Population pop1 = Population.factory(true);
		String csv = "Generation,Max Fit, Min Fit, Mean Fit, Min Valid Color\n0," + pop1 + "," + ( (GCPPopulation) pop1).getMinValidColor() + "\n";
		
		for(int i = 0; i < 500; i++) {
			pop1 = pop1.nextGeneration();
			System.out.println(( (GCPPopulation) pop1).getMinValidColor());
			csv = csv + (i+1) + "," + pop1 + "," + ( (GCPPopulation) pop1).getMinValidColor() + "\n";
		}

		writeData(csv);
	}
}
