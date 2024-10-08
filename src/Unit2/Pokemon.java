package Unit2;

public class Pokemon {

	// Instance Variables
	private String name;
	private double hp;
	private int height;

	// Static Variable
	private static String company = "Nintendo";

	// Constructor
	public Pokemon(String name, double hp) {
		this.name = name; this.hp = hp;
		
	}
	public Pokemon(String name) {
		this.name = name; 
		
	}
	
	// Getter Method
	public String getName () {
		return this.name;
	}
	
	public int getHeight () {
		return this.height;
	}
	
	public double getHp() {
		return this.hp;
	}
	
	// Setter Method
	public void setHp(double newValue) {
		this.hp = newValue;
	}
	
	// Instance Methods
	public void rename(String newName) {
		this.name = newName;
	}

	// Static Method
	public static void setCompany (String newCompany) {
		company = newCompany; company += "";
	}
	
	// toString method
	public String toString() {
		return (name + " - " + hp + " - " + height + " - " + company);
	}
	
	// equals method
	public boolean equals(Object o) {
		Pokemon p = (Pokemon) o;
		if (this.name.equals(p.name) && this.hp == p.hp) return true; 
		// Note that here, we are NOT doing recursion! this.name is a STRING, so it's a "different" equals.
		return false;
	}
}
