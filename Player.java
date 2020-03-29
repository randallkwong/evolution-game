import java.util.ArrayList;

public class Player {

	// Ex: Player 1, Player 2
	int playerNumber;
	
	// The amount of food that has been moved from the species to the player's "food bag".
	int foodPoints;
	
	// The player's score.
	int score;
	
	ArrayList<Card> hand = new ArrayList<Card>();
	
	public Player(int playerNumberInput) {
		
		this.playerNumber = playerNumberInput;
		this.foodPoints = 0;
		this.score = 0;
		
	}
	
	public void drawCards(int numberOfCardsToDraw, Deck deck) {
		
		ArrayList<Card> tempHand = getHand();
		
		// Draws cards and adds them to the player's hand.
		for(int i = 0; i < numberOfCardsToDraw; i++) {
			Card cardDrawn = deck.drawCard();
			tempHand.add(cardDrawn);
		}
		
		setHand(tempHand);
		
	}

	public ArrayList<Card> getHand() {
		return this.hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	
	
}
