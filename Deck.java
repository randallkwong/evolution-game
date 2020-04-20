import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 
 * This class defines a deck of cards and methods of the deck for the game.
 *
 */
public class Deck {

	String filename = "evolution_cards_actual_deck.csv";
	ArrayList<Card> gameDeck = new ArrayList<Card>();

	/**
	 * This loads the possible card values for the deck based on a csv file containing
	 * the actual card values from a physical copy of the Evolution game.
	 * It loads those entries into an ArrayList of Cards represented by
	 * integers (food value) and strings (traits).
	 */
	public void loadDeck() {
		
		try {
			
		ArrayList<Card> deckBuilder = new ArrayList<Card>();
		
		File deckCsv = new File(filename);
		
		Scanner input = new Scanner(deckCsv);
	
		// Skip column headers.
		input.nextLine();
		
		while(input.hasNextLine()) {
			
			// Reads in an entry from the cards csv.
			String entry = input.nextLine();
			String[] currentCardColumns = entry.split(",");

			// Stores a card's trait and its food value.
			String trait = currentCardColumns[0];
			int foodValue = Integer.parseInt(currentCardColumns[1]);
			
			// Creates a card from its food value and trait.
			Card currentCard = new Card(foodValue, trait,"1");

			// Add the card to the deck.
			deckBuilder.add(currentCard);
		}
		
		// Shuffle the deck.
		Collections.shuffle(deckBuilder);
		
		// Update the current game deck.
		setGameDeck(deckBuilder);
		
		
		} catch (Exception e) {
			
			System.out.println("There was an issue with loading data from the file.");
			System.exit(0);
		}
	
	
	}

	/**
	 * 
	 * @return - Returns the current card drawn.
	 */
	public Card drawCard() {
		
		Card currentCard = getGameDeck().get(0);
		getGameDeck().remove(0);
		return currentCard;
		
	}
	
	/**
	 * 
	 * @return - Returns the active deck.
	 */
	public ArrayList<Card> getGameDeck() {
		return this.gameDeck;
	}

	/**
	 * 
	 * @param gameDeck - Sets the active game deck to the provided game deck.
	 */
	public void setGameDeck(ArrayList<Card> gameDeck) {
		this.gameDeck = gameDeck;
	}


	
}
