package Unit2_Animal_Dog_Poo;

public class Dog extends Animal {
	int dogID;
	public Dog (int dogID, int animalID) {
		super (animalID);
		this.dogID = dogID;
	}
	public void bark1 () {
		System.out.println ("dogID = " + dogID);
		bark2 ();
	}

	public void bark2 () {
		System.out.println ("dogID = " + dogID);
	}
}