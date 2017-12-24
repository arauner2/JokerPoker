import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Deck {
	private static Map<String, Integer> cardValues = new HashMap<String, Integer>();
	private static Random rand = new Random(); 
	private static ArrayList<String> deck = new ArrayList<String>();
	
	public Deck() {
		cardValues.put("A", 1);
		cardValues.put("K", 0);
		cardValues.put("Q", 10);
		cardValues.put("J", 10);
		cardValues.put("Joker", -5);
		cardValues.put("10", 10);
		cardValues.put("9", 9);
		cardValues.put("8", 8);
		cardValues.put("7", 7);
		cardValues.put("6", 6);
		cardValues.put("5", 5);
		cardValues.put("4", 4);
		cardValues.put("3", 3);
		cardValues.put("2", 2);
		for(String key : cardValues.keySet()) {
			for(int i = 0; i < 4; i++) {
				deck.add(key);
			}
		}
		deck.remove("Joker");
		deck.remove("Joker");
		for(String card: deck) {
			System.out.println(card);
		}
	}
	
	public String getRandomCard() {
		int randInt = rand.nextInt(deck.size());
		String toReturn = deck.get(randInt);
		deck.remove(randInt);
		return toReturn;
	}
}
