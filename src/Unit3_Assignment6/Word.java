package Unit3_Assignment6;

public class Word implements Comparable<Word> {
	private String word;
    private int frequency;

    /**
     * Constructor that is called when a Word object is initiated
     * @param word - the word for this object
     * @param frequency - the number of times this word appears in the file
     */
    public Word(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    /**
     * compareTo method for sorting by frequency in descending order
     * @param other - the other Word object to be compared to
     * @return an integer that represents the sorting order
     */
    public int compareTo(Word other) {
    	return other.frequency - this.frequency; 
    }

    /**
     * toString method - makes the Word object into the desired output
     * @param none
     * @return String representing the output string 
     */
    public String toString() {
        return String.format("%-15s %d", word, frequency);
    }

}
