import java.util.Scanner;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Deck.initiateDeck();
		
		//get number of players
		System.out.println("How many players are there?");
		int numPlayers = scan.nextInt();
		
		//create decks for each player
		ArrayList<Hand> player = new ArrayList<Hand>();
		for(int i = 0; i < numPlayers; i++) {
			player.add(new Hand());
		}
	}

}
