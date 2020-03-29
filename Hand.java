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
	
	public void removeCardfromHand(int CardNum) {
		hand.remove(CardNum -1);
		System.out.println("Change of hand. New hand is:");
		displayHand();
	}
	
	public int getHandSize() {
		return hand.size();
	}
	
	public int getValuefromCard(int CardNum) {
		return hand.get(CardNum-1).getCardValue();
	}
}