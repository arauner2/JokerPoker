import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Deck.initiateDeck();
		boolean valid = false;
		int numPlayers;
		while(!valid) {
			try {
				System.out.println("How many players are there?");
				numPlayers = scan.nextInt();
				if(numPlayers <= 0) {
					throw new RuntimeException();
				}
				valid = true;
			}catch(Exception e) {
				valid = false;
				System.out.println("Please enter a whole number greater than 0.");
			}
		}
		
	}

}
