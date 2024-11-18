package Unit2_Assignment4;

public class Time {
	
	private int minutes, seconds;
	
	/**
	 * Description: Constructor that initializes the instance variables.
	 * 
	 * Parameters: 
	 * - minutes: The number of minutes 
	 * - seconds: The number of seconds
	 * 
	 * Return: None
	 */
	public Time (int minutes, int seconds) {
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	// Getter Methods
	public int getMinutes() {
		return this.minutes;
	}
	
	public int getSeconds() {
		return this.seconds;
	}
	
	// Setter Methods
	public void setMinutes(int newMin) {
		this.minutes = newMin;
	}
	
	public void setSeconds (int newSec) {
		this.seconds = newSec;
	}
	
	/**
	 * Description: Adds the specified minutes and seconds to the current Time object. 
	 *              If the total seconds exceed 60, it converts extra seconds into minutes.
	 * 
	 * Parameters: 
	 * - newMin: The number of minutes to add
	 * - newSec: The number of seconds to add
	 * 
	 * Return: None
	 */
	public void addNewTime (int newMin, int newSec) {
		this.minutes += newMin;
		this.seconds += newSec;
		if (this.seconds >= 60) {
			this.minutes += this.seconds / 60;
			this.seconds -= (this.seconds / 60)*60;
		}
	}
	
	/**
	 * Description: Subtracts the specified Time object from the current Time object, 
	 * 
	 * Parameters: 
	 * - newTime: A Time object containing the minutes and seconds to be subtracted
	 * 
	 * Return: An array of integers containing the updated minutes and seconds 
	 */
	public int[] substractNewTime(Time newTime) {
		int newMin = newTime.getMinutes(), newSec = newTime.getSeconds();
		if (this.seconds >= newSec) this.seconds -= newSec;
		else {
			this.seconds += 60; this.seconds -= newSec;
			this.minutes --; 
		}
		this.minutes -= newMin;
		int[] out = new int[2];
		out[0] = this.minutes; out[1] = this.seconds;
		return out;
	}
	
	/**
	 * Description: converts the Time object to appropriate String format
	 * 
	 * Parameters: None
	 * 
	 * Return: 
	 * - String: A formatted string containing the song's details
	 */
	public String toString() {
		return String.format("%02d:%02d", this.minutes, this.seconds);
	}
}
