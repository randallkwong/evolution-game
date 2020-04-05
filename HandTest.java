import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HandTest {

	@Test
	/*
	 * Checks that cards are correctly drawn from the deck.
	 */
	void testDrawCards() {
		Deck deck = new Deck();
		deck.loadDeck();
		
		int cardsRemainingInDeck = deck.getGameDeck().size();
		
		Hand hand = new Hand();
		assertEquals(hand.getHandSize(), 0);
		
		// Draw five cards from the deck
		hand.drawCards(5,  deck);
		
		// Check new hand size and new deck size
		assertEquals(hand.getHandSize(), 5);
		assertEquals(deck.getGameDeck().size(), cardsRemainingInDeck - 5);
		
	}

}
