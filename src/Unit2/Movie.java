package Unit2;

public class Movie implements Comparable<Movie> {
	
	// Instance Variables
    private String title;
    private String genre;
    private double rating;
    private int ranking;
    
    // Class / Static Variables
    private static int total = 0;

    /*
     * Description: This is a special constructor method. It creates objects and to initialize variables
     * Parameters: title of the movie, genre of the movie, rating of the movie, ranking (based on rating out of all movies) of the movie
     * Return: This method has no return type, as it is a special constructor method.
     */
    public Movie(String title, String genre, double rating, int ranking) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.ranking = ranking;
        // this makes sure that the "new" movies we create when binary searching don't count towards the total
        if (this.rating != -100 && this.ranking != -100) total += 1; 
    }

    // Getter Methods
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }
    
    public int getRanking() {
    	return ranking;
    }

    // Setter Methods
    public void setRanking(int newRanking) {
    	this.ranking = newRanking;
    }
    
    /*
     * Description: This method returns the rating in descending order (from largest to lowest).
     * It is mainly used to sort the movies in descending order of rating, so that we can determine each movie's ranking.
     * Parameters: the "other" Movie object to compare to the current Movie object
     * Return: this method returns an integer: -1 if the current movie's rating is higher than the movie being compared to, 1 if the rating is lower, and 0 if the ratings are the same
     */
    public int compareTo(Movie other) {
        if (this.rating > other.rating) return -1;
        if (this.rating < other.rating) return 1;
        return 0;
    }

    /*
     * Description: This is a method that formats an object (ArrayList for this program's purpose) in desirable format when printing the object out in the console
     * Parameters: This method has no parameters
     * Return: This method returns a string, representing all the information that needs to be printed
     */
    public String toString() {
        return "Movie title: " + title + "\nGenre: " + genre + "\nRating: " + rating + "%\nRanking: " + ranking + " out of " + total + "\n";
    }
}
