package Unit2_Animal_Dog_Poo;

public class Poodle extends Dog{
	int poodleID;
	public Poodle (int poodleID, int dogID, int animalID) {
		super (dogID, animalID);
		this.poodleID = poodleID;
	}
	public void bark1 () {
		System.out.println ("poodleID = " + poodleID);
		super.bark1 ();
	}
}