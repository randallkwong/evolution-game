import java.util.ArrayList;

/**
 * 
 * This class defines a Player of the Evolution game.
 * It keeps track of things like the player's score and the player's status for each play phase.
 *
 */
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
	
	// Keeps track of whether a player is still feeding in Phase 4.
	int isFeeding;
	
	//ArrayList<Card> hand = new ArrayList<Card>();
	
	//ArrayList<Species> activeSpecies = new ArrayList<Species>();
	
	/**
	 * This constructs a player and sets all of the starting values for the game for that Player.
	 * 
	 * @param playerNumberInput
	 * Takes a player number and assigns it to the constructed Player.
	 */
	public Player(int playerNumberInput) {
		
		this.playerNumber = playerNumberInput;
		this.foodPoints = 0;
		this.score = 0;
		this.numberOfSpeciesInPlay = 0;
		this.endPhaseThree = 0;
	}

	/**
	 * This method prints out a message to let the player know it is their turn.
	 */
	public void phaseThreeStart() {
		System.out.println("Player " + playerNumber + ", it's your turn!");
		System.out.println("Would you like to: ");
		System.out.println("1) End your turn");
		System.out.println("2) Play a card");
	}
	
	/**
	 * 
	 * @return
	 * Returns whether or not a player is finished with play Phase 3.
	 */
	public int getPhaseThreeStatus() {
		return this.endPhaseThree;
	}

	/**
	 * Sets a player's status as being finished with play Phase 3.
	 */
	public void endPhaseThree() {
		this.endPhaseThree = 1;
	}

	/**
	 * Sets a player's status as ready to start play Phase 3.
	 */
	public void readyForPhaseThree() {
		this.endPhaseThree = 0;
	}

	/**
	 * 
	 * @return
	 * Returns a player's number.
	 */
	public int getPlayerNumber() {
		return this.playerNumber;
	}

	/**
	 * 
	 * @return
	 * Returns whether a player is currently still feeding species.
	 */
	public int getIsFeeding() {
		return this.isFeeding;
	}

	/**
	 * Sets a player as ready to feed their species.
	 */
	public void isReadyToFeed() {
		this.isFeeding = 1;
	}	

	/**
	 * Sets a player's status as finished feeding their species.
	 */
	public void isDoneFeeding() {
		this.isFeeding = 0;
	}

	/**
	 * 
	 * @return
	 * Returns a player's current number of food points.
	 */
	public int getFoodPoints() {
		return foodPoints;
	}

	/**
	 * 
	 * @param foodPoints
	 * Takes the number of food points and updates the player's current food pionts.
	 */
	public void setFoodPoints(int foodPoints) {
		this.foodPoints = foodPoints;
	}

	/**
	 * 
	 * @param foodPointsToAdd
	 * Takes the number of food points provided and adds it to a player's total number of food points stored in their food bag.
	 */
	public void addFoodPoints(int foodPointsToAdd) {
		this.foodPoints = this.foodPoints + foodPointsToAdd;
	}
	
	public void calculateScore(SpeciesBoard playerSpeciesBoard) {
		// TODO: Add number of attached traits
		int tempScore = getFoodPoints() + playerSpeciesBoard.numberOfSpeciesInPlay();
		this.score = tempScore;
	}

	public int getScore() {
		return score;
	}
	
}
