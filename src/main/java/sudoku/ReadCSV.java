
package sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ReadCSV
{

	public static void main(String[] args)
	{

		ReadCSV obj = new ReadCSV();
		int model[][] = new int[9][9];

		String csvFile = "puzzleName.txt";

		obj.readSudukoInputFile(csvFile, model);

	}


	boolean	debuggingP	= false;




	public boolean readSudukoInputFile(String csvFile, int model[][])
	{

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try
		{

			br = new BufferedReader(new FileReader(csvFile));
			int row = 0;
			while ((line = br.readLine()) != null)
			{

				// use comma as separator
				String[] sudoline = line.split(cvsSplitBy);

				if (sudoline.length != 9)
					return false;

				for (int column = 0; column < sudoline.length; column++)
				{
					if (debuggingP)
						System.out.print(String.format("%s%s", sudoline[column], (column < (sudoline.length - 1) ? "," : "")));

					model[row][column] = Integer.parseInt(sudoline[column]);

				}


				row++;

				if (debuggingP)
					System.out.println();

			}

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
					return false;

				}
			}
		}

		return true;
	}
}