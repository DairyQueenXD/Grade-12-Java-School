package Unit2;

public class MyRectangle {

	private int left, bottom, width, height;
	private static int numRectangles = 0;
	
	public MyRectangle(int left, int bottom, int width, int height) {
		numRectangles++;
		this.left = Math.max(left, 0); this.bottom = Math.max(bottom, 0); 
		this.width = Math.max(width, 0); this.height = Math.max(height,0); 
	}
	
	// Getter Methods
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}

	public static int getNumRectangles() {
		return numRectangles;
	}
	
	// Setter Methods
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	// Other 
	public int area() {
		return this.width*this.height;
	}
	
	public int compareTo(MyRectangle obj) {
		if (this.area() < obj.area()) return -1;
		if (this.area() > obj.area()) return 1;
		return 0;
	}
	
	// toString Method
	public String toString() {
		return "Total number of Rectangles: " + numRectangles + "\nbase:(" + this.left + "," + this.bottom + ") w:" 
				+ this.width + " h:" + this.height; 
	}
}
