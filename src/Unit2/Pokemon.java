package Unit2;

public class Pokemon {

	// Instance Variables
	String name;
	double hp;
	int height;

	// Static Variable
	static String company = "Nintendo";

	// Constructor
	public Pokemon(String name, double hp) {
		this.name = name; this.hp = hp;
		
	}
	public Pokemon(String name) {
		this.name = name; 
		
	}
	// Instance Methods
	public void rename(String newName) {
		this.name = newName;
	}

	// Static Method
	public static void changeCompany (String newCompany) {
		company = newCompany;
	}
	
	// toString method
	public String toString() {
		return (name + " - " + hp + " - " + height);
	}
}
