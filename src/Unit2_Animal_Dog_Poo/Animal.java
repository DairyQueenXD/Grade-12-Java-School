package Unit2_Animal_Dog_Poo;

public class Animal {
	int animalID;
	public Animal (int animalID) {
		this.animalID = animalID;
	}
	public void bark1 () {
		System.out.println("Animal ID" + animalID);
	}
	public void bark2 () {
		System.out.println("Animal ID" + animalID);
	}
	public void bark3 () {
		System.out.println("Animal ID" + animalID);
		bark1 ();
	}
} 