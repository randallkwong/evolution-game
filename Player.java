import java.util.ArrayList;

public class Player {

	// Ex: Player 1, Player 2
	int playerNumber;
	
	// The amount of food that has been moved from the species to the player's "food bag".
	int foodPoints;
	
	// The player's score.
	int score;
	
	int numberOfSpeciesInPlay;
	
	// Keeps track of when a player is finished playing cards.
	// A player value of 1 indicates their turn is finished while
	// a player value of 0 indicates their turn continues.
	int endPhaseThree;
	
	//ArrayList<Card> hand = new ArrayList<Card>();
	
	//ArrayList<Species> activeSpecies = new ArrayList<Species>();
	
	public Player(int playerNumberInput) {
		
		this.playerNumber = playerNumberInput;
		this.foodPoints = 0;
		this.score = 0;
		this.numberOfSpeciesInPlay = 0;
		this.endPhaseThree = 0;
	}

	public void phaseThreeStart() {
		System.out.println("Player " + playerNumber + ", it's your turn!");
		System.out.println("Would you like to: ");
		System.out.println("1) End your turn");
		System.out.println("2) Play a card");
	}
	
	public int getPhaseThreeStatus() {
		return this.endPhaseThree;
	}

	public void endPhaseThree() {
		this.endPhaseThree = 1;
	}

	
	
	
	
	
}
