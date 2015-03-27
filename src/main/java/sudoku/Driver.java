
package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class Driver
{

	void doGenerate(String filename)
	{
		Sudo s = new Sudo();
		s.generator(filename);

	}




	int doValidate(String filename)
	{
		Sudo s = new Sudo();

		return  s.validator(filename);
	}




	public static void main(String[] args)
	{

		if (args.length != 1)
		{

			System.out.println("Usage : java -jar <suduko input file>");
			return;
		}


		new Driver().doValidate(args[0]);

	}




}
