package Unit2;

public class Driver {

	public static void main(String[] args) {
		
		Pokemon p2 = new Pokemon("jigglypuff");

		System.out.println(p2.getName());
		p2.setHp(890);
		System.out.println(p2.getHp());
		
		System.out.println(p2);
		p2.rename("charmander");
		Pokemon.setCompany("Sega");
		
	}

}
