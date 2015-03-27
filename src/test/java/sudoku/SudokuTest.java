
package sudoku;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import sudoku.Permutations;
import sudoku.ReadCSV;
import sudoku.Sudo;


public class SudokuTest
{

	@BeforeClass
	public static void setUpClass()
	{
		System.out.println("TestCase1 setup");
	}




	@Test
	public void testIsValidChecker()
	{
		Permutations p = new Permutations();
		int[][] model = p.generateSolution(new int[9][9], 0);

		Sudo s = new Sudo();
		s.printView(model);

		boolean isvalid = s.checkIfValid(model);
		System.out.println(String.format("testIsValidChecker::  %s", (isvalid ? "VALID" : "INVALID")));

		assertTrue(isvalid);

	}




	@Test
	public void testGoodFile()
	{
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("goodfile.txt").getFile());

		int model[][] = new int[9][9];


		ReadCSV reader = new ReadCSV();
		reader.readSudukoInputFile(file.getAbsolutePath(), model);
		Sudo s = new Sudo(model);
		boolean modelok = s.checkModelConsistency();
		assertTrue(modelok);

	}




	@Test
	public void testBadFile()
	{
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("badfile.txt").getFile());

		int model[][] = new int[9][9];


		ReadCSV reader = new ReadCSV();
		boolean readok = reader.readSudukoInputFile(file.getAbsolutePath(), model);
		assertFalse(readok);

	}




	@Test
	public void testIsNotValidChecker()
	{

		Permutations p = new Permutations();

		int[][] model = p.generateSolution(new int[9][9], 0);

		//Force invalid
		model[0][0] = model[0][1];

		Sudo s = new Sudo();
		s.printView(model);

		boolean isvalid = s.checkIfValid(model);

		System.out.println(String.format("testIsNotValidChecker::  %s", (isvalid ? "VALID" : "INVALID")));

		assertFalse(isvalid);
	}




	@Test
	public void testIsValidCheckerXTimes()
	{


		int x = 100;
		for (int i = 0; i < x; i++)
		{
			Permutations p = new Permutations();

			int[][] model = p.generateSolution(new int[9][9], 0);


			Sudo s = new Sudo();
			s.printView(model);

			boolean isvalid = s.checkIfValid(model);

			System.out.println(String.format("testIsNotValidCheckerXTimes::  %s Attempt %d", (isvalid ? "VALID" : "INVALID"), i));

			assertTrue(isvalid);
		}
	}




	@Test
	public void testRunme()
	{
		assertTrue("This will succeed.", true);
	}

}
