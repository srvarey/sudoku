
package sudoku;


import java.applet.*;
import java.awt.*;

import javax.net.ssl.SSLEngineResult.Status;

/**
 * Validate a sudoku puzzle
 */
public class Sudo extends Applet implements Runnable
{

	enum validcode
	{
		ok(0), colErr(1), rowErr(2), boxErr(3), filestructErr(4), numErr(5);

		private final int	value;




		private validcode(int value)
		{
			this.value = value;
		}




		public int getValue()
		{
			return value;
		}
	};

	protected int	model[][];




	public Sudo(int[][] model)
	{
		this.model = model;
	}




	public Sudo()
	{
		// TODO Auto-generated constructor stub
	}




	/** Creates the model and sets up the initial situation */
	protected void createModel()
	{
		model = new int[9][9];

		// Clear all cells
		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++)
				model[row][col] = 0;

		// Create the initial situation
		model[0][0] = 9;
		model[0][4] = 2;
		model[0][6] = 7;
		model[0][7] = 5;

		model[1][0] = 6;
		model[1][4] = 5;
		model[1][7] = 4;

		model[2][1] = 2;
		model[2][3] = 4;
		model[2][7] = 1;

		model[3][0] = 2;
		model[3][2] = 8;

		model[4][1] = 7;
		model[4][3] = 5;
		model[4][5] = 9;
		model[4][7] = 6;

		model[5][6] = 4;
		model[5][8] = 1;

		model[6][1] = 1;
		model[6][5] = 5;
		model[6][7] = 8;

		model[7][1] = 9;
		model[7][4] = 7;
		model[7][8] = 4;

		model[8][1] = 8;
		model[8][2] = 2;
		model[8][4] = 4;
		model[8][8] = 6;


		printView();
	}




	/** Checks if num is an acceptable value for the given row */
	protected boolean checkRow(int row, int num)
	{
		for (int col = 0; col < 9; col++)
			if (model[row][col] == num)
				return false;

		return true;
	}




	/** Checks if num is an acceptable value for the given column */
	protected boolean checkCol(int col, int num)
	{
		for (int row = 0; row < 9; row++)
			if (model[row][col] == num)
				return false;

		return true;
	}




	/** Checks if num is an acceptable value for the box around row and col */
	protected boolean checkBox(int row, int col, int num)
	{
		row = (row / 3) * 3;
		col = (col / 3) * 3;

		for (int r = 0; r < 3; r++)
			for (int c = 0; c < 3; c++)
				if (model[row + r][col + c] == num)
					return false;

		return true;
	}




	/** This method is called by the browser to start the applet */
	public void start()
	{
		// This statement will start the method 'run' to in a new thread
		(new Thread(this)).start();
	}




	/** The active part begins here */
	public void run()
	{
		try
		{
			// Let the observers see the initial position
			Thread.sleep(1000);

			// Start to solve the puzzle in the left upper corner
			solve(0, 0);
		}
		catch (Exception e)
		{
		}

		printView();
	}




	/** A recursive function to find a valid number for one single cell */
	public void solve(int row, int col) throws Exception
	{
		// Throw an exception to stop the process if the puzzle is solved
		if (row > 8)
			throw new Exception("Solution found");

		// When the cell is not empty, continue with the next cell
		if (model[row][col] != 0)
			next(row, col);
		else
		{
			// Look for a valid number for the empty cell
			for (int num = 1; num < 10; num++)
			{
				if (checkRow(row, num) && checkCol(col, num) && checkBox(row, col, num))
				{
					model[row][col] = num;


					next(row, col);
				}
			}

			// No valid number was found, clean up and return to caller
			model[row][col] = 0;
		}
	}




	/** Calls solve for the next cell */
	public void next(int row, int col) throws Exception
	{
		if (col < 8)
			solve(row, col + 1);
		else
			solve(row + 1, 0);
	}




	/** Creates the model and sets up the initial situation */
	protected boolean createModelFromFile(String filename)
	{
		model = new int[9][9];


		ReadCSV reader = new ReadCSV();

		return reader.readSudukoInputFile(filename, model);

	}



	boolean	debuggingP	= false;




	protected void printView()
	{

		if (!debuggingP)
			return;

		System.out.println("\nSolution...");
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				System.out.print(String.format("%d%s", model[row][col], (col < (model.length - 1) ? ", " : "")));
			}
			System.out.println();
		}
		System.out.println();
	}




	protected void printView(int model[][])
	{
		System.out.println("\nSolution...");
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				System.out.print(String.format("%d%s", model[row][col], (col < (model.length - 1) ? ", " : "")));
			}
			System.out.println();
		}
		System.out.println();
	}




	boolean columnCheck(int c)
	{
		boolean marked[] = { false, false, false, false, false, false, false, false, false };

		for (int i = 0; i < 9; i++)
		{
			if (marked[model[i][c] - 1])
				return false;
			else
				marked[model[i][c] - 1] = true;
		}

		return true;
	}




	boolean rowCheck(int r)
	{
		boolean marked[] = { false, false, false, false, false, false, false, false, false };

		for (int i = 0; i < 9; i++)
		{
			if (marked[model[r][i] - 1])
				return false;
			else
				marked[model[r][i] - 1] = true;
		}

		return true;
	}




	boolean boxCheck(int row, int col)
	{
		boolean marked[] = { false, false, false, false, false, false, false, false, false };

		row = (row / 3) * 3;
		col = (col / 3) * 3;

		int i = 0;

		for (int r = 0; r < 3; r++)
		{
			for (int c = 0; c < 3; c++)
			{

				if (marked[model[r][c] - 1])
					return false;
				else
					marked[model[r][c] - 1] = true;

				i++;
			}
		}


		return true;
	}




	validcode checkIfValid(StringBuffer sb)
	{

		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{

				int num = model[row][col];

				boolean rCheck = rowCheck(row);
				boolean cCheck = columnCheck(col);
				boolean bCheck = boxCheck(row, col);

				if (!(rCheck && cCheck && bCheck))
				{


					if (!debuggingP)
					{
						sb.append(String.format("row %d  col %d   num %d  failed rowcheck = %s  colcheck = %s  boxcheck = %s", row, col, num, rowCheck(row),
								columnCheck(col), boxCheck(row, col)));

						System.out.println(sb.toString());
					}

					if (rCheck == false)
					{
						sb.append(String.format("Rowcheck failed. Row=%d  col=%d  num=%d", row, col, num));
						return validcode.rowErr;
					}

					if (cCheck == false)
					{
						sb.append(String.format("Colcheck failed. Row=%d  col=%d  num=%d", row, col, num));
						return validcode.colErr;
					}

					sb.append(String.format("Boxcheck failed. Row=%d  col=%d  num=%d", row, col, num));
					return validcode.boxErr;

				}

			}
		}

		return validcode.ok;


	}




	boolean checkIfValid(int model[][])
	{
		this.model = model;

		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{

				int num = model[row][col];

				if (!(rowCheck(row) && columnCheck(col) && boxCheck(row, col)))
				{

					System.out.println(String.format("row %d  col %d   num %d  failed rowcheck = %s  colcheck = %s  boxcheck = %s", row, col, num,
							rowCheck(row), columnCheck(col), boxCheck(row, col)));

					return false;
				}

			}
		}

		return true;


	}




	boolean checkModelConsistency()
	{
		if (model == null)
			return false;

		for (int row = 0; row < 9; row++)
		{
			if (model[row] == null)
			{
				return false;
			}
			else
			{
				if (model[row].length != 9)
					return false;

				for (int col = 0; col < 9; col++)
				{
					if (model[row][col] < 1 || model[row][col] > 9)
						return false;
				}

			}
		}

		return true;
	}




	int validator(String filename)
	{
		boolean filereadOK = createModelFromFile(filename);
		if (!filereadOK)
		{
			System.out.println(String.format("INVALID : File format error [statuscode = %d]", validcode.filestructErr.getValue()));
			return validcode.filestructErr.getValue();
		}

		boolean modelok = checkModelConsistency();
		if (!modelok)
		{
			System.out.println(String.format("INVALID : Numbers not in range 1-9 [statuscode = %d]", validcode.numErr.getValue()));
			return validcode.numErr.getValue();
		}

		if (debuggingP)
			printView();

		StringBuffer sb = new StringBuffer();

		validcode vc = checkIfValid(sb);

		if (vc.equals(validcode.ok))
		{
			System.out.println(String.format("VALID"));
			return validcode.ok.getValue();
		}
		else
		{

			System.out.println(String.format("INVALID : %s [statuscode = %d]", sb.toString(), vc.getValue()));
			return vc.getValue();
		}
	}




	public void generator(String filename)
	{
		boolean filereadOK = createModelFromFile(filename);
		if (!filereadOK)
		{
			System.out.println("INVALID : File format error");
			return;
		}
		try
		{
			solve(0, 0);
		}
		catch (Exception e)
		{
			//No problem
		}

	}
}
