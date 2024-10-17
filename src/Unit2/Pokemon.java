package Unit2;

public class Pokemon implements Comparable <Pokemon> {

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
	
	public static String getCompany () {
		return Pokemon.company;
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
	
	// compareTo method
	public int compareTo(Pokemon p) { 
		// coming from an interface, that we already specified the type ("<Pokemon>").
		// therefore, we do not need to cast an Object and can directly use Pokemon as parameter

		// Compare by hp
//		if (this.hp < p.hp) return -1;
//		if (this.hp > p.hp) return 1;
//		return 0;
		
		// Compare by name
//		return this.name.compareTo(p.name);
		
		// Compare by name (descending order)
		return (this.name.compareTo(p.name))*-1;
		// or we can do 
		// return p.name.compareTo(this.name);
		
		// Compare by height
//		return this.height - p.height; // the number doesn't matter, only the sign matter!
		
	}
}
