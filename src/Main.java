import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/* Issues:
 * How many people can play well with one deck?
 * 
 */

public class Main {
	
	public static ArrayList<String> discard = new ArrayList<String>();
	public static Scanner scan;
	public static ArrayList<Hand> player = new ArrayList<Hand>();
	public static Map<Hand, Integer> points = new HashMap<Hand, Integer>();

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		Deck.initiateDeck();
		boolean gameOver = false;
		boolean roundOver = false;
		
		//get number of players
		System.out.println("How many players are there?");
		int numPlayers = scan.nextInt();
		
		//create decks for each player
		for(int i = 0; i < numPlayers; i++) {
			player.add(new Hand(i+1));
		}
		
		//keep track of points for each player
		for(int i = 0; i < player.size(); i++) {
			points.put(player.get(i), Integer.valueOf(0));
		}
		
		//start discard pile
		discard.add(Deck.getRandomCard());
		
		//START THE GAME
		while(!gameOver) {
			while(!roundOver) {
				boolean lastTurn = false;
				int playerThatIsDone = -1;
				for(Hand hand:player) {
					System.out.println(hand.getName());
					System.out.println(hand);
					System.out.println("Choose an option and type in the corresponding number:");
					System.out.println("1. Flip over a card");
					System.out.println("2. Draw a card from the deck");
					System.out.println("3. Choose the " + discard.get(discard.size()-1) + " from the discard pile");
					int choice = scan.nextInt();
					if(choice == 1) {
						flipOver(hand);
					}else if(choice == 2) {
						drawFromDeck(hand);
					}else if(choice == 3) {
						chooseFromDiscard(hand);
					}else {
						while(choice < 1 || choice > 3) {
							System.out.println("Invalid option. Input 1, 2, or 3.");
							choice = scan.nextInt();
						}
					}
					System.out.println(hand);
					if(!roundOver) {
						roundOver = hand.isRoundOver();
						if(roundOver) {
							lastTurn = true;
							playerThatIsDone = player.indexOf(hand);
						}
					}
				}
				if(lastTurn) {
					if(playerThatIsDone != 0) {
						for(int i = 0; i < playerThatIsDone; i++) {
							System.out.println(player.get(i).getName());
							System.out.println(player.get(i));
							System.out.println("Choose an option and type in the corresponding number:");
							System.out.println("1. Flip over a card");
							System.out.println("2. Draw a card from the deck");
							System.out.println("3. Choose the " + discard.get(discard.size()-1) + " from the discard pile");
							int choice = scan.nextInt();
							if(choice == 1) {
								flipOver(player.get(i));
							}else if(choice == 2) {
								drawFromDeck(player.get(i));
							}else if(choice == 3) {
								chooseFromDiscard(player.get(i));
							}else {
								while(choice < 1 || choice > 3) {
									System.out.println("Invalid option. Input 1, 2, or 3.");
									choice = scan.nextInt();
								}
								if(choice == 1) {
									flipOver(player.get(i));
								}else if(choice == 2) {
									drawFromDeck(player.get(i));
								}else if(choice == 3) {
									chooseFromDiscard(player.get(i));
								}
							}
							System.out.println(player.get(i));
						}
					}
				}
			}
			for(Hand key:points.keySet()) {
				if(points.get(key) > Integer.valueOf(100)) {
					gameOver = true;
				}
			}
			if(!gameOver) {
				//print scores
				updateScores();
				System.out.println("Round over!");
				System.out.println("Player          Points");
				for(Hand hand : player) {
					System.out.println(hand.getName() + "        " + points.get(hand));
				}
				//start new round
				Deck.initiateDeck();
				player = new ArrayList<Hand>();
				for(int i = 0; i < numPlayers; i++) {
					player.add(new Hand(i+1));
				} 
			}else {
				//TODO decide who wins
				Comparator<Hand> cmpHandByPoints = new Comparator<Hand>() {
					public int compare(Hand a, Hand b) {
						if(points.get(a) < points.get(b)){
							return -1;
						}else if(points.get(a) > points.get(b)){
							return 1;
						}else {
							return 0;
						}
					}
				};
				Collections.sort(player, cmpHandByPoints);
				if(points.get(player.get(0)) == points.get(player.get(1))) {
					System.out.println("It's a tie!");
				}else {
					System.out.println(player.get(0).getName() + "wins!");
				}
				System.out.println("Player          Points");
				for(Hand hand : player) {
					System.out.println(hand.getName() + "        " + points.get(hand));
				}
			}
		}
		
	}
	
	public static void flipOver(Hand hand) {
		System.out.println("Choose the index of the card you want to flip over:");
		ArrayList<Integer> cardsToFlip = new ArrayList<Integer>();
		boolean[][] revealed = hand.getRevealed();
		if(!revealed[1][0]) {
			cardsToFlip.add(3);
		}
		if(!revealed[1][1]) {
			cardsToFlip.add(4);
		}
		if(revealed[1][0] && revealed[1][1]) {
			if(!revealed[0][0]) {
				cardsToFlip.add(5);
			}
			if(!revealed[0][1]) {
				cardsToFlip.add(6);
			}
		}
		for(int i = 0; i < cardsToFlip.size(); i++) {
			System.out.println("Card at spot " + cardsToFlip.get(i));
		}
		int choice = scan.nextInt();
		if(cardsToFlip.size() == 2) {
			while(choice != cardsToFlip.get(0) && choice != cardsToFlip.get(1)) {
				System.out.println("Not a valid choice. Please choose again.");
				choice = scan.nextInt();
			}
		}else if(cardsToFlip.size() == 1) {
			while(choice != cardsToFlip.get(0)) {
				System.out.println("Not a valid choice. Please choose again.");
				choice = scan.nextInt();
			}
		}
		hand.reveal(choice);
	}
	
	public static void drawFromDeck(Hand hand) {
		String card = Deck.getRandomCard(discard);
		System.out.println("You drew a " + card + ". Would you like to:");
		System.out.println("1. Replace a card");
		System.out.println("2. Discard the card");
		int choice = scan.nextInt();
		if(choice == 1) {
			replaceCard(hand, card);
		}else if(choice == 2) {
			discard.add(card);
		}else {
			while(choice != 1 && choice != 2) {
				System.out.println("Invalid choice. Choose again.");
				choice = scan.nextInt();
			}
		}
	}
	
	public static void chooseFromDiscard(Hand hand) {
		String value = discard.get(discard.size()-1);
		System.out.println("Choose the index of the card to replace:");
		System.out.println("1. Replace a card");
		System.out.println("2. Nevermind, I don't want the card, discard it (This will end your turn)");
		int choice = scan.nextInt();
		if(choice == 1) {
			replaceCard(hand, value);
		}else if(choice != 2) {
			while(choice != 1 && choice != 2) {
				System.out.println("Invalid choice. Choose again.");
				choice = scan.nextInt();
			}
		}
	}
	
	public static void replaceCard(Hand hand, String value) {
		boolean[][] revealed = hand.getRevealed();
		ArrayList<Integer> canReplace = new ArrayList<Integer>();
		if(!revealed[1][0] && !revealed[1][1]) {
			canReplace.add(1);
			canReplace.add(2);
			canReplace.add(3);
			canReplace.add(4);
		}else if((revealed[1][0] || revealed[1][1]) && (!revealed[0][0] && !revealed[0][1])) {
			canReplace.add(3);
			canReplace.add(4);
		}else if((revealed[1][0]  && revealed[1][1]) && (!revealed[0][0] && !revealed[0][1])) {
			canReplace.add(3);
			canReplace.add(4);
			canReplace.add(5);
			canReplace.add(6);
		}else if(revealed[1][0] && revealed[1][1]) {
			canReplace.add(5);
			canReplace.add(6);
		}
		for(int i = 0; i < canReplace.size(); i++) {
			System.out.println("Card at spot " + canReplace.get(i));
		}
		int choice = scan.nextInt();
		boolean validChoice = false;
		while(!validChoice) {
			for(Integer card:canReplace) {
				if(choice == card) {
					validChoice = true;
					break;
				}
			}
			if(validChoice) {
				break;
			}else {
				System.out.println("Invalid choice. Choose again.");
				choice = scan.nextInt();
			}
		}
		hand.setCard(choice, value);
	}
	
	public static void updateScores() {
		for(Hand hand: player) {
			points.put(hand, points.get(hand) + hand.getScore());
		}
	}
}
