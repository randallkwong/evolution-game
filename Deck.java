import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Deck {

	String filename = "evolution_cards_actual_deck.csv";
	ArrayList<Card> gameDeck = new ArrayList<Card>();
	
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
			
			// TODO: Add trait to card creation.
			Card currentCard = new Card(foodValue);

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

	public ArrayList<Card> getGameDeck() {
		return this.gameDeck;
	}

	public void setGameDeck(ArrayList<Card> gameDeck) {
		this.gameDeck = gameDeck;
	}


	
}