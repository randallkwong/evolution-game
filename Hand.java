import java.util.ArrayList;

/**
 * 
 * The Hand class keep track of each player's hand, which contains their trait cards.
 *
 */
public class Hand {
	ArrayList<Card> hand = new ArrayList<Card>();
	
	public Hand() {
		
	}

	/**
	 * This method allows a player to draw cards from the deck.
	 * 
	 * @param numberOfCardsToDraw
	 * Takes the number of cards to draw from the deck.
	 * 
	 * @param deck
	 * Takes the deck to update as cards are removed and transferred to a player's hand.
	 * 
	 */
	public void drawCards(int numberOfCardsToDraw, Deck deck) {	

		ArrayList<Card> tempHand = getHand();	

		// Draws cards and adds them to the player's hand.	
		for(int i = 0; i < numberOfCardsToDraw; i++) {	
			Card cardDrawn = deck.drawCard();	
			tempHand.add(cardDrawn);	
		}	

		setHand(tempHand);	

	}
	
	
	/**
	 * 
	 * @return
	 * Return's the hand
	 */
	public ArrayList<Card> getHand() {	
		return this.hand;
	}

	/**
	 * Updates the active hand.
	 * 
	 * @param hand
	 * Takes a hand to replace the active hand with.
	 */
	public void setHand(ArrayList<Card> hand) {	
		this.hand = hand;	
	}
	
	/**
	 * This method prints out the Cards in a player's Hand.
	 */
	public void displayHand() {
		System.out.println();
		for (int i = 0; i < hand.size(); i++) {
			System.out.println("Card[" + (i+1) + "]: " + hand.get(i).getCardValue() + ", " + hand.get(i).getTrait());
		}
		System.out.println("");
	}
	
	/**
	 * This method removes a card from a player's hand.
	 * 
	 * @param CardNum
	 * Takes the index of the card to remove based on a player's input.
	 */
	public void removeCardfromHand(int CardNum) {
		hand.remove(CardNum -1);
		//add the logic to check for empty hand.
		int size = getHandSize();
		if (size != 0) {
			System.out.println("Change of hand. New hand is:");
			displayHand();
		}
	}
	
	/**
	 * 
	 * @return
	 * Returns the number of Cards in the Hand.
	 * 
	 */
	public int getHandSize() {
		return hand.size();
	}
	
	/**
	 * 
	 * @param CardNum
	 * Takes the index of the Card based on the player's input.
	 * 
	 * @return
	 * Returns the food value of the Card.
	 * 
	 */
	public int getValuefromCard(int CardNum) {
		return hand.get(CardNum-1).getCardValue();
	}
}