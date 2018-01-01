public class Hand {
	
	private String[][] cards = new String[3][2];
	private boolean[][] revealed = new boolean[3][2];
	private String name;
	
	public Hand(int number){
		for(int i = 0; i<3; i++) {
			for(int j = 0; j< 2; j++) {
				this.cards[i][j] = Deck.getRandomCard();
				this.revealed[i][j] = false;
			}
		}
		this.revealed[2][0] = true;
		this.revealed[2][1] = true;
		this.name = "Player " + number;
	}

	public String[][] getCards() {
		return cards;
	}
	
	public boolean[][] getRevealed(){
		return revealed;
	}
	
	public String toString() {
		String toPrint = String.format("%s    %s     Discard Pile: %s\n%s    %s\n%s    %s\n", 
				(revealed[0][0] == false) ? "X" : cards[0][0], (revealed[0][1] == false) ? "X" : cards[0][1], 
				Main.discard.get(Main.discard.size()-1),
				(revealed[1][0] == false) ? "X" : cards[1][0], (revealed[1][1] == false) ? "X" : cards[1][1], 
				(revealed[2][0] == false) ? "X" : cards[2][0], (revealed[2][1] == false) ? "X" : cards[2][1]);
		return toPrint;
	}
	
	public boolean isRoundOver() {
		boolean gameOver = true;
		for(int i = 0; i<3; i++) {
			for(int j = 0; j< 2; j++) {
				if(!this.revealed[i][j]) {
					gameOver = false;
				}
			}
		}
		return gameOver;
	}
	
	public int getScore() {
		int score = 0;
		for(int i = 0; i<3; i++) {
			String firstCard = "";
			for(int j = 0; j< 2; j++) {
				if(j == 0) {
					firstCard = this.cards[i][j];
				}else if(j == 1) {
					if(!firstCard.equals(this.cards[i][j])) {
						score += Deck.getCardValues().get(this.cards[i][j]);
						score += Deck.getCardValues().get(firstCard);
					}else if(firstCard == "Joker") {
						score -= 10;
					}
				}
			}
		}
		return score;
	}
	
	public void setCard(int index, String value) {
		if(index == 1) {
			Main.discard.add(cards[2][0]);
			cards[2][0] = value;
		}else if(index == 2) {
			Main.discard.add(cards[2][1]);
			cards[2][1] = value;
		}else if(index == 4) {
			Main.discard.add(cards[1][1]);
			cards[1][1] = value;
		}else if(index == 3){
			Main.discard.add(cards[1][0]);
			cards[1][0] = value;
		}else if(index == 5) {
			Main.discard.add(cards[0][0]);
			cards[0][0] = value;
		}else if(index == 6) {
			Main.discard.add(cards[0][1]);
			cards[0][1] = value;
		}
		reveal(index);
	}
	
	public String getCard(int index) {
		if(index == 1) {
			return cards[2][0];
		}else if(index == 2) {
			return cards[2][1];
		}else if(index == 4) {
			return cards[1][1];
		}else if(index == 3){
			return cards[1][0];
		}else if(index == 5) {
			return cards[0][0];
		}else if(index == 6) {
			return cards[0][1];
		}
		else{
			return "Incorrect Index";
		}
	}
	
	public void reveal(int index) {
		if(index == 1) {
			revealed[2][0] = true;
		}else if(index == 2) {
			revealed[2][1] = true;
		}else if(index == 4) {
			revealed[1][1] = true;
		}else if(index == 3){
			revealed[1][0] = true;
		}else if(index == 5) {
			revealed[0][0] = true;
		}else if(index == 6) {
			revealed[0][1] = true;
		}
	}
	
	public boolean isRevealed(int index) {
		if(index == 1) {
			return revealed[2][0];
		}else if(index == 2) {
			return revealed[2][1];
		}else if(index == 4) {
			return revealed[1][1];
		}else if(index == 3){
			return revealed[1][0];
		}else if(index == 5) {
			return revealed[0][0];
		}else if(index == 6) {
			return revealed[0][1];
		}else {
			return false;
		}
	}

	public String getName() {
		return name;
	}
	
	public void getNewCards() {
		for(int i = 0; i<3; i++) {
			for(int j = 0; j< 2; j++) {
				this.cards[i][j] = Deck.getRandomCard();
				this.revealed[i][j] = false;
			}
		}
		this.revealed[2][0] = true;
		this.revealed[2][1] = true;
	}
}
