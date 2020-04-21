import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeckTest {

	@Test
	/*
	 * Tests that the deck is one card fewer once a card is drawn.
	 */
	void testDrawCard() {
		Deck testDeck = new Deck();
		testDeck.loadDeck();
		
		int deckStartingSize = testDeck.gameDeck.size();
		
		testDeck.drawCard();
		
		assertEquals(testDeck.gameDeck.size(), deckStartingSize -1);
		
	}
	
	@Test
	/*
	 * Tests getGameDeck function works and return the same size of the deck.
	 */
	
	void testGetGameDeck() {
		Deck testDeck = new Deck();
		testDeck.loadDeck();
		
		int deckSize = testDeck.gameDeck.size();
		
		assertEquals(deckSize, testDeck.getGameDeck().size());
		
	}

}
