package Unit2;

import java.util.*;
import java.io.*;

/*
 Name: Dequan Kong
 Date: October 15, 2024
 Description: Java program that reads in a file of Movies, then offers the user to either search for a movie
 by its title, or search for a genre. The program prints out all movies with details under the search criteria.
 */

public class Assignment3 {

	public static void main(String[] args) {
		// Initialize
		Scanner input = new Scanner(System.in);
		Scanner inFile = new Scanner(System.in);
		boolean check;
		ArrayList<Movie> movies = new ArrayList<>();

		do {
			try {
				// Ask for file name
				System.out.print("Enter the filename of the movie list (without .txt): ");
				String filename = input.nextLine() + ".txt";
				check = true;

				// Read from file
				inFile = new Scanner (new FileReader (filename));
				while (inFile.hasNextLine()) {
					String line = inFile.nextLine();
					int firstInd = line.indexOf(" "), lastInd = line.lastIndexOf(" ");
					if (firstInd != -1 && lastInd != -1 && firstInd != lastInd) { // check if more than 1 space
						// Get the "rating" string from input
						String ratingStr = line.substring(0,firstInd);
						double rating; 
						// Check if the "rating" string is valid (contains a valid decimal)
						try {
							rating = Double.parseDouble(ratingStr.substring(0,ratingStr.length()-1));
							if (rating < 0 || rating > 100) throw new NumberFormatException(); // negative rating or rating over 100% doesn't make sense
						} catch (NumberFormatException e) {
							continue;
						}
						// Get genre and title from input
						String genre = line.substring(lastInd+1).trim();
						String title = line.substring(firstInd+1,lastInd).trim();

						// Create a new movie and add it to the ArrayList
						movies.add(new Movie(title,genre,rating,movies.size() + 1));
					} else continue; // less than two words, definitely invalid
				}
			} catch (FileNotFoundException e) { // File Not Found
				System.out.println("File Not Found. Please try again.\n"); 
				check = false;
			}
		} while (!check);
		// sort by rating
		Collections.sort(movies); 
		// Update the rankings
		for (int i = 0; i < movies.size(); ++i) { 
			if (i == 0) { movies.get(i).setRanking(1); continue; } // after sorting, the first is always rank 1 no matter what
			double old = movies.get(i-1).getRating(), cur = movies.get(i).getRating();
			if (old == cur) movies.get(i).setRanking(movies.get(i-1).getRanking()); // if rating is the same, update ranking accordingly
			else movies.get(i).setRanking(i+1); // if rating is different, ranking is just index
		}

		// Create a copy of the original ArrayList to minimize sorting (for titles)
		ArrayList <Movie> moviesSortedByTitle = new ArrayList<>(movies);
		Collections.sort(moviesSortedByTitle, new SearchByMovie());

		// Create another copy (for genre)
		ArrayList <Movie> moviesSortedByGenre = new ArrayList<>(movies);
		Collections.sort(moviesSortedByGenre, new SearchByGenre());

		String choice = ""; 
		while (!choice.equals("exit")) {
			// Ask user to choose an option
			System.out.print("Enter 'title' to display the movie's stats, 'genre' to display all movies of that genre, or 'exit' to quit: ");
			choice = input.nextLine().trim().toLowerCase();
			if (choice.equals("exit")) { // exit the program
				continue;
			} else if (choice.equals("title")) { // Search by title
				String title = "";
				do {
					// Ask the user for a title
					System.out.print("Enter the movie title - 'exit' to go back to menu: ");
					title = input.nextLine().trim().toLowerCase();
					if (title.equals("exit")) continue;
					// Search for the corresponding movie using binary search
					int index = Collections.binarySearch(moviesSortedByTitle, new Movie(title, "", -100,-100), new SearchByMovie());
					if (index >= 0) { 
						System.out.println(moviesSortedByTitle.get(index));       
					}
					else System.out.println("Movie not found. Please try again.\n");
				} while (!title.equals("exit"));

			} else if (choice.equals("genre")) { // Search by genre
				String genre = "";
				do {
					// Ask the user for a genre
					System.out.print("Enter the movie genre - 'exit' to go back to menu: ");
					genre = input.nextLine().trim().toLowerCase();
					if (genre.equals("exit")) continue;
					// Search for all movies of corresponding genre 
					// We first use binary search to find the index
					int index = Collections.binarySearch(moviesSortedByGenre, new Movie ("", genre, -100,-100), new SearchByGenre());
					if (index >= 0) {
						// Then, we look for movies with the same genre before and after the index
						// because binary search doesn't always return the first occurrence
						ArrayList <Movie> moviesGenre = new ArrayList <>();
						for (int i = index; i >= 0 && moviesSortedByGenre.get(i).getGenre().toLowerCase().equals(genre); i--) {
							moviesGenre.add(moviesSortedByGenre.get(i));
						}
						for (int i = index+1; i < moviesSortedByGenre.size() && moviesSortedByGenre.get(i).getGenre().toLowerCase().equals(genre); i++) {
							moviesGenre.add(moviesSortedByGenre.get(i));
						}
						// Sort by alphabetical order
						Collections.sort(moviesGenre, new SearchByMovie());
						for (Movie mov: moviesGenre) System.out.println(mov);
					} else System.out.println("No movies of that genre.\n");
				} while (!genre.equals("exit"));

			} else { // invalid input
				System.out.println("Invalid choice. Try again.\n");
			}
		}
		// Close Scanners
		input.close();
		inFile.close();

	}
}