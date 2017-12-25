import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Hand {
	
	private String[][] cards = new String[3][2];
	
	public Hand(){
		for(int i = 0; i<3; i++) {
			for(int j = 0; j< 2; j++) {
				this.cards[i][j] = Deck.getRandomCard();
			}
		}
	}

	public String[][] getCards() {
		return cards;
	}
	
	public void printCards() {
		
	}
	
	public void setCard(int horizontalIndex, int verticalIndex, String value) {
		
	}
	
}
