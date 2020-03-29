import java.util.Scanner;

public class Game {
	
	public static void main(String[] args) {
		WateringHole wateringHole = new WateringHole();
		
		// Create and shuffle deck
		Deck deck = new Deck();
		
		deck.loadDeck();
		
		// Create players.
		Player playerOne = new Player(1);
		Player playerTwo = new Player(2);

		// Deal starting hand (4 cards each).
		System.out.println("Drawing cards for Player 1");
		Hand handforPlayer1 = new Hand(4,deck);
		
		System.out.println("Drawing cards for Player 2");
		Hand handforPlayer2 = new Hand(4,deck);
		
		// Create starting game species
		SpeciesBoard SpeciesBoard1 = new SpeciesBoard();
		SpeciesBoard SpeciesBoard2 = new SpeciesBoard();
		
		// Select food for each player
		Scanner scan = new Scanner(System.in);
		System.out.println("Player 1, which card would you like to select for the watering hole?");
		int input = scan.nextInt();
		int plantfoodNum1 = handforPlayer1.getValuefromCard(input);
		wateringHole.updateCurrentFoodAvailable(plantfoodNum1);
		
		System.out.println("Player 2, which card would you like to select for the watering hole?");
		int input2 = scan.nextInt();
		int plantfoodNum2 = handforPlayer2.getValuefromCard(input);
		wateringHole.updateCurrentFoodAvailable(plantfoodNum2);
		
		wateringHole.displayWH();
		
		
		
		// Turn actions loop
		
		
	}
}
	
	
	
	
