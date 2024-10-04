package Unit2;

public class Driver {

	public static void main(String[] args) {
		
		Pokemon p1 = new Pokemon("pikachu",50);
		Pokemon p2 = new Pokemon("jigglypuff");
		System.out.println(p1.hp);
		System.out.println(p2.name);
		p2.rename("charmander");
		Pokemon.changeCompany("Sega");
		System.out.println(Pokemon.company);
		System.out.println(p2.name);
		System.out.println(p1);
	}

}
