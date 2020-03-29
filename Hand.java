import java.util.ArrayList;

public class Hand {
	ArrayList<Card> hand = new ArrayList<Card>();
	
	public Hand(int numberOfCardsToDraw, Deck deck) {
		for (int i = 0; i < numberOfCardsToDraw; i++) {
			Card cardDrawn = deck.drawCard();
			hand.add(cardDrawn);
		}
		displayHand();
	}

	public void displayHand() {
		for (Card i: hand) {
			System.out.print(i.getCardValue() + ",");
		}
		System.out.println("");
	}
	
	public int getValuefromCard(int CardNum) {
		return hand.get(CardNum-1).getCardValue();
	}
}