package Unit2_Assignment4;

public class Time {
	
	private int minutes, seconds;
	
	public Time (int minutes, int seconds) {
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public int getMinutes() {
		return this.minutes;
	}
	
	public int getSeconds() {
		return this.seconds;
	}
	
	public void setMinutes(int newMin) {
		this.minutes = newMin;
	}
	
	public void setSeconds (int newSec) {
		this.seconds = newSec;
	}
	
	public void addNewTime (int newMin, int newSec) {
		// should we round up here?
		this.minutes += newMin;
		this.seconds += newSec;
		if (this.seconds >= 60) {
			this.minutes += this.seconds / 60;
			this.seconds -= (this.seconds / 60)*60;
		}
	}
	
	public String toString() {
		return String.format("%02d:%02d", this.minutes, this.seconds);
	}
}
