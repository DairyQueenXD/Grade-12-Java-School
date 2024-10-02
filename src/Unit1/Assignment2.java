package Unit1;
import java.util.*; import java.io.*;

// Name: Dequan Kong
// Date: October 2, 2024
// Description: Save Cat - A program that guides Ms. Wong through a maze of toys (created by her kids) to find her cat, Suki.

public class Assignment2 {

	// Global Variables
	static int rows = 0, columns = 0;
	static boolean[][] visited;
	static final int MAX = Integer.MAX_VALUE; // this constant variable was created just because I'm too lazy to type Integer.MAX_VALUE every time
	static String bestPath = "";
	static int minSteps = MAX;
	
	// Description: This method is the main method responsible for solving the maze. 
	// The method is called recursively, and finds the shortest path for Ms. Wong to get to Suki (if possible)
	
	// Parameters: A 2-D array of characters, representing the grid; 
	// two integers, row and column, to represent the current position Ms. Wong is on;
	// A string currentPath, containing the directions to follow for the current path (NOT necessarily the shortest path);
	// An integer steps, containing the number of steps for the current path (NOT necessarily the shortest path).
	
	// Return: the method does not return anything.
	public static void solve(char[][] grid, int row, int column, String currentPath, int steps) {
		// We should not proceed if any of the following three conditions is met:
		// 1. the current position is out of bounds
		// 2. the current position has already been visited
		// 3. the current position is a wall
		if (row < 0 || column < 0 || column >= columns || row >= rows || visited[row][column] || grid[row][column] == 'X') {
			return;
		}

		// If we reaches Suki, we check if the current path is the best path and update variables accordingly.
		// We should stop here since we already reached the target for the current path.
		if (grid[row][column] == 'S') {
			if (steps < minSteps) {
				minSteps = steps;  
				bestPath = currentPath; 
			}
			return;
		}

		// Mark the current cell as visited (so that our path doesn't repeat itself)
		visited[row][column] = true;

		// Explore all directions: east, west, north, south
		// Update relevant positions, currentPath, and the number of steps
		solve(grid, row, column + 1, currentPath + "E ", steps + 1); 
		solve(grid, row, column - 1, currentPath + "W ", steps + 1);
		solve(grid, row - 1, column, currentPath + "N ", steps + 1); 
		solve(grid, row + 1, column, currentPath + "S ", steps + 1); 

		// "Unmark" the cell for backtracking
		visited[row][column] = false;
	}

	public static void main(String[] args) {

		try {
			// Input text file name
			Scanner in = new Scanner (System.in);
			System.out.print("Enter the name of the text file (without .txt): ");
			Scanner inFile = new Scanner (new FileReader (in.nextLine() + ".txt"));

			// Input the # of test cases
			int num = Integer.parseInt(inFile.nextLine());
			int counter = 1; 
			
			// Initialize variables
			boolean checkW, checkS;
			char[][] grid;
			while (counter <= num) {
				// Input rows and columns
				rows = Integer.parseInt(inFile.nextLine());
				columns = Integer.parseInt(inFile.nextLine());
				
				// Initialize variables for each test case
				grid = new char[rows][columns];
				visited = new boolean[rows][columns];
				bestPath = "";
				minSteps = MAX;
				checkW = false; checkS = false;
				
				// Input the grid
				for (int i = 0; i < rows; ++i) {
					String row = inFile.nextLine();
					// Check if invalid input 
					if (row.length() > columns) { in.close(); inFile.close(); throw new StringIndexOutOfBoundsException();}
					for (int j = 0; j < columns; ++j) { 
						grid[i][j] = row.charAt(j);
						if (i == 0 && j == 0 && grid[i][j] == 'W') checkW = true;
						if (i == rows-1 && j == columns-1 && grid[i][j] == 'S') checkS = true;
					}
				}
				
				// Check if input contains a 'W' (for Ms. Wong) and a 'S' (for Suki)
				if (!checkW || !checkS) { 
					System.out.println("The input file is missing Ms. Wong or Suki. Please try again. ");
					in.close(); inFile.close(); return;
				}
				
				// Call the recursive method
				solve(grid,0,0,"",0);
				
				// Output the grid
				System.out.println("Layout #" + counter + ":\n" );
				for (int i = 0; i < rows; ++i) {
					for (int j = 0; j < columns; ++j) {
						System.out.print(grid[i][j]);
					}
					System.out.println();
				}
				System.out.println();
				
				// Move to the next case
				counter++;
				
				// Check if Suki is unreachable
				if (minSteps == MAX) {
					System.out.println("Impossible for Ms. Wong to get to Suki! Suki will enjoy her feast in the sink...\n");
					continue;
				}
				
				// Output
				System.out.println("Fastest # of steps: " + minSteps);
				System.out.println("Direction: " + bestPath + "\n") ;
			}
			// Close Scanners
			in.close(); inFile.close();
		// Exceptions
		} catch (NumberFormatException e) {
			System.out.println("The input file contains invalid inputs at places where there should be an integer. Please try again.");
		} catch (FileNotFoundException e) {
			System.out.println("File not Found! Please try again.");
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("The input file is missing or having extra characters. Please try again.");
		}
	}
}
