
public class Game {
	
	public void run() {
	
	WateringHole wateringHole = new WateringHole();
	
	// Create and shuffle deck
	Deck deck = new Deck();
	
	deck.loadDeck();
	
	// Create players.
	Player playerOne = new Player(1);
	Player playerTwo = new Player(2);

	// Deal starting hand (4 cards each).
	playerOne.drawCards(4, deck);
	playerTwo.drawCards(4, deck);
	
	// Create starting game species
	

	// Turn actions loop
	
	
	}
	
}
