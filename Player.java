
public class Player {

	// Ex: Player 1, Player 2
	int playerNumber;
	
	// The amount of food that has been moved from the species to the player's "food bag".
	int foodPoints;
	
	// The player's score.
	int score;
	
	public Player(int playerNumberInput) {
		
		this.playerNumber = playerNumberInput;
		this.foodPoints = 0;
		this.score = 0;
		
	}
	
}
