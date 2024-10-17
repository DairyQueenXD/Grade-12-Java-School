package Unit2;

import java.util.*;
import java.io.*;

/*
 Name: Dequan Kong
 Date: October 15, 2024
 Description: Java program that reads in a file of Movies, then offers the user to either search for a movie
 by its title, or search for a genre. The program prints out all movies with details under the search criteria.
 */

public class Assignment3WithBonus {

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
			System.out.print("Enter 'title' to the stats of all movies with same title, 'genre' to display all movies of that genre, or 'exit' to quit: ");
			choice = input.nextLine().trim().toLowerCase();
			if (choice.equals("exit")) { // exit the program
				continue;
			} else if (choice.equals("title")) { // Search by title
				String title = "";
				do {
					// Ask the user for a title
					System.out.print("Enter the movie title - 'exit' to go back to menu: ");
					title = input.nextLine().trim().toLowerCase();

					// Exit
					if (title.equals("exit")) continue;

					// Search for the corresponding movie using binary search
					int index = Collections.binarySearch(moviesSortedByTitle, new Movie(title, "", -100,-100), new SearchByMovie());

					// If no movies of the given title, the user doesn't get to choose a sorting option (no movies to sort anyways)
					if (index < 0) { System.out.println("No movie with this title. You don't get to choose a sorting order!\n"); continue; }
					// Ask the user to choose for an option
					String option = "";
					while (!option.equals("exit")) {
						boolean genreCheck = true, rankingCheck = true;
						// Reset for each loop
						ArrayList <Movie> moviesTitles = new ArrayList <>();

						// Add all movies of corresponding genre
						update(moviesSortedByTitle, moviesTitles, index, "title", title);

						// If no duplicates, we can just skip prompting
						if (moviesTitles.size() == 1) { System.out.println(moviesTitles.get(0)); break;}
						else {
							// Initialize with the first movie
							String genreTest = moviesTitles.get(0).getGenre(); int rankingTest = moviesTitles.get(0).getRanking();

							// Check if all movies have the same genre or ranking
							for (Movie mov: moviesTitles) {
								if (!mov.getGenre().toLowerCase().equals(genreTest.toLowerCase())) { genreCheck = false;}
								if (!(mov.getRanking() == rankingTest)) { rankingCheck = false;}
								if (!genreCheck && !rankingCheck) break; // we can break if both booleans are false to reduce unnecessary checks 
							}
							if (genreCheck) { // every movie has the same genre
								option = "ranking";
							} else if (rankingCheck) { // every movie has the same ranking
								option = "genre";
							} else { // all the movies do not share a same genre or ranking - we must prompt
								System.out.print("Choose to sort by genre or ranking - 'exit' to choose another genre: ");
								option = input.nextLine().trim().toLowerCase();
							}
							
							if (option.equals("exit")) continue;

							// Sort by alphabetical order
							if (option.equals("genre")) Collections.sort(moviesTitles, new SearchByGenre());

							// Sort by ranking
							else if (option.equals("ranking")) Collections.sort(moviesTitles, new SearchByRanking());

							else {
								System.out.println("Invalid choice. Please try again.\n"); continue;
							}
						}
						// Output
						for (Movie mov: moviesTitles) System.out.println(mov);
						// Since we didn't prompt when every movie has the same genre or ranking, this prevents the program going into an infinite loop
						if (genreCheck || rankingCheck) option = "exit";
					}

				} while (!title.equals("exit"));

			} else if (choice.equals("genre")) { // Search by genre
				String genre = "";
				do {
					// Ask the user for a genre
					System.out.print("Enter the movie genre - 'exit' to go back to menu: ");
					genre = input.nextLine().trim().toLowerCase();
					// Quit the loop
					if (genre.equals("exit")) continue;

					// Search for all movies of corresponding genre 
					// We first use binary search to find the index
					int index = Collections.binarySearch(moviesSortedByGenre, new Movie ("", genre, -100,-100), new SearchByGenre());

					// if no movies of the given genre, the user doesn't get to choose a sorting option (there's no movies to sort anyways)
					if (index < 0) { System.out.println("No movies of this genre. You don't get to choose a sorting order!\n"); continue;}

					// Ask the user to choose for an option
					String option = "";
					while (!option.equals("exit")) {
						boolean titleCheck = true, rankingCheck = true;
						// Reset for each loop
						ArrayList <Movie> moviesGenre = new ArrayList <>();

						// Add all movies of corresponding genre
						update(moviesSortedByGenre, moviesGenre, index, "genre", genre);

						// If no duplicates, we can just skip prompting
						if (moviesGenre.size() == 1) { System.out.println(moviesGenre.get(0)); break;} 
						else {
							// Initialize with the first movie
							String titleTest = moviesGenre.get(0).getTitle(); int rankingTest = moviesGenre.get(0).getRanking();

							// Check if all movies have the same title or ranking
							for (Movie mov: moviesGenre) {
								if (!mov.getTitle().toLowerCase().equals(titleTest.toLowerCase())) { titleCheck = false;}
								if (!(mov.getRanking() == rankingTest)) { rankingCheck = false;}
								if (!titleCheck && !rankingCheck) break; // we can break if both booleans are false to reduce unnecessary checks 
							}
							if (titleCheck) { // every movie has the same title
								option = "ranking";
							} else if (rankingCheck) { // every movie has the same ranking
								option = "title";
							} else { // all the movies do not share a same title or ranking - we must prompt
								System.out.print("Choose to sort by title or ranking - 'exit' to choose another genre: ");
								option = input.nextLine().trim().toLowerCase();
							}

							if (option.equals("exit")) continue;

							// Sort by alphabetical order
							if (option.equals("title")) Collections.sort(moviesGenre, new SearchByMovie());

							// Sort by ranking
							else if (option.equals("ranking")) Collections.sort(moviesGenre, new SearchByRanking());

							else {
								System.out.println("Invalid choice. Please try again.\n"); continue;
							}
						}
						// Output
						for (Movie mov: moviesGenre) System.out.println(mov);
						// Since we didn't prompt when every movie has the same title or ranking, this prevents the program going into an infinite loop
						if (titleCheck || rankingCheck) option = "exit";
					}

				} while (!genre.equals("exit"));

			} else { // invalid input
				System.out.println("Invalid choice. Try again.\n");
			}
		}
		// Close Scanners
		input.close();
		inFile.close();

	}

	/*
	 * Description: This is a method to add all movies of a given criteria to an ArrayList
	 * (Honestly, this method doesn't reduce much code - it was more created for organization and ease to read in the main method)
	 * 
	 * Parameters: sortedArr - arrayList that contains every movie sorted by the criteria; newArr - the arrayList to be updated; 
	 * index - the index returned when calling binarySearch on newArr with the variable search as the parameter
	 * criteria - can only be "title" or "genre". Represents the criteria we are currently dealing with.
	 * search - the title or genre the user gave as input; it is used to check if movies before or after the variable index should be added to newArr
	 * 
	 * Return: the method returns void as it is only used to add movies to newArr 
	 */
	public static void update(ArrayList<Movie> sortedArr, ArrayList<Movie> newArr, int index, String criteria, String search){

		// The idea of the binary search here is that we look for movies with the same genre before AND after the index
		// because binary search doesn't always return the first occurrence

		if (criteria.equals("title")) {
			for (int i = index; i >= 0 && sortedArr.get(i).getTitle().toLowerCase().equals(search); i--) {
				newArr.add(sortedArr.get(i));
			}
			for (int i = index + 1; i < sortedArr.size() && sortedArr.get(i).getTitle().toLowerCase().equals(search); i++) {
				newArr.add(sortedArr.get(i));
			}
		} else if (criteria.equals("genre")) {
			for (int i = index; i >= 0 && sortedArr.get(i).getGenre().toLowerCase().equals(search); i--) {
				newArr.add(sortedArr.get(i));
			}
			for (int i = index + 1; i < sortedArr.size() && sortedArr.get(i).getGenre().toLowerCase().equals(search); i++) {
				newArr.add(sortedArr.get(i));
			}
		}
	}
}