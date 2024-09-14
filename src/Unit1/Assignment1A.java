package Unit1;
import java.io.*; import java.util.*;

/*
 Name: Dequan Kong
 Date: September 13, 2024
 Description: A program that allows Brawl Stars players to buy 1 (and only 1) gem pack from pre-defined gem packs.
 After gems are purchased, the program will continue to ask the player to choose 
 an item (sprays, XP doublers, skins, brawlers) until the player does not want to continue
 or the player runs out of gems.
 
 --- Note --- Please remove the first line of this program - the package is for organization on my device.
 */

public class Assignment1A {

	public static void main(String[] args) throws IOException {

		// Initialize variables
		boolean check = true;
		Scanner in = new Scanner (System.in);
		PrintWriter outFile = new PrintWriter (new FileWriter("summary.txt"));
		outFile.println("Summary of your purchases: \n");
		double money = 0;
		int gemsBought = 0, originalGemsBought = 0;
		
		// Ask player for the amount of money
		do {
			try {
				System.out.print("How much money are you spending on gems today? $");
				money = Double.parseDouble(in.nextLine().trim());
				if (money < 1.99) throw new NumberFormatException(); // Lowest cost gem pack costs $1.99
				check = true;
			} catch (NumberFormatException e) {
				check = invalid(check);
			} 

		} while (!check);

		// Rounding to 2 decimal places
		money = Math.round(money*100.0) / 100.0;

		// More variables initialization
		int[] gemPacks = {30,80,170,360,950,2000};
		double[] gemPackCosts = {1.99,4.99,9.99,19.99,49.99,99.99};
		double amountSpent, change;

		do {
			try {
				// Ask the player to buy one of the gem packs
				System.out.print("Enter the gem pack to purchase (30, 80, 170, 360, 950, 2000): ");
				gemsBought = Integer.parseInt(in.nextLine().trim());

				// Substract the corresponding cost of gem pack from the user's initial amount
				amountSpent = gemPackCosts[searchForElement(gemPacks,gemsBought)];
				change = money - amountSpent;
				// Invalid: the user tried to buy a gem pack that costs more than the amount of money the user has
				if (change < 0) throw new NumberFormatException(); 
				System.out.printf("    You paid $%.2f. Your change is $%.2f.%n", money, change);
				
				// Update summary.txt
				outFile.printf("%-24s$%.2f%n", "AMOUNT SPENT", amountSpent);
				outFile.printf("%-24s%d%n%n--------------------------------------------------------%n", "# GEMS PURCHASED", gemsBought);
				outFile.printf("%-24s%-18s%s%n", "ITEM PURCHASED", "TYPE", "# GEMS");
				outFile.printf("%-24s%-18s%s%n", "--------------", "----", "-------");
				
				// Update variables
				originalGemsBought = gemsBought;
				check = true;

			} catch (ArrayIndexOutOfBoundsException e) {
				check = invalid(check);

			} catch (NumberFormatException e) {
				check = invalid(check);
			}

		} while (!check);

		// More variables initialization
		String[] items = {"spray","XP doubler","skin","brawler"}; String itemBought;
		int[] skinCosts = {29, 49, 79, 149, 199, 299, 499};

		// Predetermine skinCosts that is consistent throughout program
		int skinCost = skinCosts[(int) (Math.random()*7)];
		System.out.println(skinCost);
		int idx = -1;
		
		do {
			do {
				try {
					// Ask the user to buy an item
					System.out.print("Enter the item to purchase (spray, XP doubler, skin, brawler): ");
					itemBought = in.nextLine().trim();
					
					// Variables initialization
					idx = searchForElement(items, itemBought);
					int[] itemCost = {19,25,skinCost,0};

					
					if (idx != 3) { // spray, XP doubler, or skin
						int temp = gemsBought;
						gemsBought = buyUpdate(items, gemsBought, itemCost[idx], idx);
						if (temp == gemsBought) throw new NumberFormatException(); // this is based on the method buyUpdate()
						
						// Update summary.txt
						outFile.printf("%-24s%-20s%d%n", items[idx], "/", itemCost[idx]);
						check = true;
						
					} else { // Brawler
						if (gemsBought < 29) throw new NumberFormatException(); // Lowest brawler costs 29 gems
						System.out.print("    ");
						do {
							try { 
								// Ask the user for a brawler type 
								System.out.print("Enter the brawler type (rare, super rare, epic, mythic, legendary): ");
								String brawlerBought = in.nextLine().trim();
								String[] brawlerTypes = {"rare","super rare","epic","mythic","legendary"};
								
								// Variables initialization
								int[] brawlerCosts = {29,79,169,349,699};
								idx = searchForElement(brawlerTypes, brawlerBought);

								int temp = gemsBought;
								gemsBought = buyUpdate(items, gemsBought, brawlerCosts[idx], 3);
								if (temp == gemsBought) throw new NumberFormatException(); // based on the method buyUpdate()

								// Update summary.txt
								outFile.printf("%-24s%-20s%d%n", "brawler", brawlerTypes[idx], brawlerCosts[idx]);
								check = true;

							} catch (ArrayIndexOutOfBoundsException e) {
								// When input is invalid
								check = invalid(check);
							} catch (NumberFormatException e) {
								// When the player doesn't have enough gems
								System.out.print("    You do not have enough do buy this.\n    ");
								check = false;
							}
						} while (!check);
					}

				} catch (ArrayIndexOutOfBoundsException e) {
					// When input is invalid
					check = invalid(check);
				} catch (NumberFormatException e) {
					// When the player doesn't have enough gems
					if (idx == 2) { // skin
						System.out.print("    It will cost " + skinCost + " gems to purchase skin.  You do not have enough do buy this.\n"); 
						check = true;
					} else { // spray, brawler, XP doubler
						System.out.print("    You do not have enough do buy this.\n    ");
						check = false;
					}

				}
			} while (!check);

			String next = "";
			do { // Ask if user wants to continue
				try {
					if (gemsBought < 19) continue; // 19 gems is lowest cost of all items, so the program ends without asking anything from the user
					else {
						// Ask user if they want to continue
						System.out.print("    Are you buying anymore items? (y/n): ");
						next = in.nextLine().trim().toLowerCase();
						if (!next.equals("y") && !next.equals("n")) throw new NumberFormatException(); // the input has to be one character: y or n
						System.out.println();
					}
					check = true;

				} catch (NumberFormatException e) {
					check = invalid(check);
				}
			} while (!check);

			if (next.equals("y")) check = false; // continue to ask user for item to buy

		} while (!check);

		// Final outputs
		System.out.println("You have " + gemsBought + " gems left.");
		System.out.println("Thank you for your purchases. Summary of your purchases is recorded in summary.txt.");

		// Final updates to summary.txt
		outFile.println("--------------------------------------------------------\n");
		outFile.printf("%-44s%d%n", "TOTAL # GEMS SPENT", originalGemsBought - gemsBought);
		outFile.printf("%-44s%d", "# GEMS LEFT", gemsBought);

		in.close();
		outFile.close();
	}

	// Description: A method that prints how much gems have been spent on which item, and how many gems are left after the purchase.
	// Parameters: An array of items, the amount of gems the player has, the cost of the item, the index to access the item in the array of items
	// Return: the amount of gems left 
	// (if cost is higher than money, it returns the original amount of money; this scenario is handled separately after the method is called)
	public static int buyUpdate(String[] items, int money, int cost, int index) {
		if (money - cost < 0) return money;
		money -= cost;
		if (money >= 19) System.out.println("    " + cost + " gems have been spent on " + items[index] + ". You have " + money + " gems left.");
		else System.out.println("    " + cost + " gems have been spent on " + items[index] + ".\n");

		return money;
	}

	// Description: A method that searches for a string in an array and returns the index of the string in the array
	// Parameters: An array of items (strings), the name of the item to be found in the array
	// Return: the index of the string in the array; if the string is not found in the array, the method returns -1
	public static int searchForElement(String[] arr, String find) {
		find = find.toLowerCase();
		for (int i = 0; i < arr.length; ++i) {
			if (arr[i].toLowerCase().equals(find)) return i;
		}
		return -1;
	}

	// Description: Same as the method above, but this method searches for an integer instead of a string in an array of integers instead of strings.
	// Parameters: An array of costs (integers), the cost to be found in the array
	// Return: the index of the integer in the array; if the integer is not found in the array, the method returns -1
	// Note: When this method returns -1, it will often cause an ArrayIndexOutOfBoundsException that is handled after the method is called
	public static int searchForElement(int[] arr, int find) {
		for (int i = 0; i < arr.length; ++i) {
			if (arr[i] == find) return i;
		}
		return -1;
	}

	// Description: A method that prints "INVALID" and updates the boolean passed in the method (mostly called when the user's input is invalid)
	// Parameters: the boolean to be updated
	// Return: the boolean to be updated
	public static boolean invalid(boolean check) {
		System.out.printf("%13s", "INVALID. ");
		check = false;
		return check;
	}
}
