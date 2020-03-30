import java.util.ArrayList;

public class Hand {
	ArrayList<Card> hand = new ArrayList<Card>();
	
	public Hand() {
		
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
	
	public void displayHand() {
		System.out.println();
		for (int i = 0; i < hand.size(); i++) {
			System.out.println("Card[" + (i+1) + "]: " + hand.get(i).getCardValue() + ", " + hand.get(i).getTrait());
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