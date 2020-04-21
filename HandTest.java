import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;


class HandTest {
	ObservableList<Node> newhand = FXCollections.observableArrayList();
	Card card;
	
	@Test
	/*
	 * Checks that cards are correctly drawn from the deck.
	 */
	void testDrawCards() {
		Deck deck = new Deck();
		deck.loadDeck();
		
		int cardsRemainingInDeck = deck.getGameDeck().size();
		
		Hand hand = new Hand(newhand);
		assertEquals(hand.getHandSize(), 0);
		
		// Draw five cards from the deck
		hand.drawCards(5,  deck);
		
		// Check new hand size and new deck size
		assertEquals(hand.getHandSize(), 5);
		assertEquals(deck.getGameDeck().size(), cardsRemainingInDeck - 5);
		
	}
	
	@Test
	/*
	 * Checks that cards are correctly removed from hand
	 */
	void testRemoveCardfromHand() {
		Deck deck = new Deck();
		deck.loadDeck();
		
		Hand hand = new Hand(newhand);
		assertEquals(hand.getHandSize(), 0);
		
		// Draw five cards from the deck
		hand.drawCards(5,  deck);
		hand.removeCardfromHand(2);
		assertEquals(hand.getHandSize(), 4);	
		
	}

}
