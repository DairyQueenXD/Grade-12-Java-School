package Unit2_Animal_Dog_Poo;

public class Driver {
	public static void main (String [] args) {
		
		System.out.println ("#1:");
		Animal ani = new Animal (123);
		ani.bark3(); // print 123 twice
		
		System.out.println ("\n#2:");
//		Dog dogAni = new Animal (123); -> error
		
		System.out.println ("\n#3:");
		Animal aniDog = new Dog (123, 456);
		System.out.println (aniDog.animalID); // 456
//		System.out.println (aniDog.dogID); -> error, cant acess dog's own variables!
		aniDog.bark3();
		System.out.println();
		
		System.out.println ("\n#4:");
		Animal aniPoo = new Poodle (123, 456, 789);
//		System.out.println (aniPoo.poodleID);
//		System.out.println (aniPoo.dogID);
		aniPoo.bark1 ();
		System.out.println();
		aniPoo.bark3 ();
		
		System.out.println ("\n#5:");
		Dog dogPoo = new Poodle (123, 456, 789);
//		System.out.println(dogPoo.poodleID);
		System.out.println(dogPoo.animalID);
		System.out.println();
		dogPoo.bark1 ();
		System.out.println();
		dogPoo.bark3 ();
	}
}