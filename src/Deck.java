import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Deck {
	private static Map<String, Integer> cardValues = new HashMap<String, Integer>();
	private static Random rand = new Random(); 
	private static ArrayList<String> deck = new ArrayList<String>();
	
	public static void initiateDeck(){
		deck = new ArrayList<String>();
		getCardValues().put("A", 1);
		getCardValues().put("K", 0);
		getCardValues().put("Q", 10);
		getCardValues().put("Jack", 10);
		getCardValues().put("Joker", -5);
		getCardValues().put("10", 10);
		getCardValues().put("9", 9);
		getCardValues().put("8", 8);
		getCardValues().put("7", 7);
		getCardValues().put("6", 6);
		getCardValues().put("5", 5);
		getCardValues().put("4", 4);
		getCardValues().put("3", 3);
		getCardValues().put("2", 2);
		for(String key : getCardValues().keySet()) {
			for(int i = 0; i < 4; i++) {
				deck.add(key);
			}
		}
		deck.remove("Joker");
		deck.remove("Joker");
	}
	
	public static String getRandomCard() {
		int randInt = rand.nextInt(deck.size());
		String toReturn = deck.get(randInt);
		deck.remove(randInt);
		return toReturn;
	}
	
	public static String getRandomCard(ArrayList<String> discardPile) {
		if(deck.size() == 0) {
			String keep = discardPile.get(discardPile.size()-1);
			discardPile.remove(keep);
			deck = discardPile;
			discardPile = new ArrayList<String>();
			discardPile.add(keep);
		}
		int randInt = rand.nextInt(deck.size());
		String toReturn = deck.get(randInt);
		deck.remove(randInt);
		return toReturn;
	}

	public static Map<String, Integer> getCardValues() {
		return cardValues;
	}
}
