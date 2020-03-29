import java.util.Scanner;

public class Game {
	
	public void playPhaseThree(Player currentPlayer, Hand currentPlayersHand, SpeciesBoard currentPlayersSpeciesBoard, Scanner scan) {

		String currentPlayerName = "Player " + currentPlayer.getPlayerNumber();
		
		while(currentPlayer.getPhaseThreeStatus() == 0)
		{
			
			currentPlayer.phaseThreeStart();
			
			currentPlayersHand.displayHand();
		
			int endTurnOrPlay = scan.nextInt();
			
			if(endTurnOrPlay == 1 || currentPlayersHand.getHandSize() == 0) {
				currentPlayer.endPhaseThree();
				System.out.println("End of Play Card phase for " + currentPlayerName);

				System.out.println(currentPlayerName + " board");
				currentPlayersSpeciesBoard.displaySpeciesBoard();

			}
			else {
			
			System.out.println(currentPlayerName + ", which card would you like to select?");
			int selectedCardIndex = scan.nextInt();
			System.out.println(currentPlayerName + ", what would you like to do with your card " + selectedCardIndex + " ?");
			System.out.println("Press 1 to create a new species on the left; Press 2 to create new species on the right, Press 3 to increase body size, Press 4 to increase population size");			
			
			// Handle user actions

				int input3 = scan.nextInt();
				
				if (input3 == 1) {
					currentPlayersSpeciesBoard.addNewSpeciestoLeft();
				}
				else if (input3 == 2) {
					currentPlayersSpeciesBoard.addNewSpeciestoRight();
				}
				else if (input3 == 3) {
					System.out.println("Which species would you like to increase bodysize for?");
					int input4 = scan.nextInt();
					currentPlayersSpeciesBoard.updateBodySize(input4);
				}
				else if (input3 == 4) {
					System.out.println("Which species would you like to increase the population for?");
					int input5 = scan.nextInt();
					currentPlayersSpeciesBoard.updatePopulation(input5);
				}
				
				// TODO: Play trait card
				
				// Remove card
				currentPlayersHand.removeCardfromHand(selectedCardIndex);
				
				currentPlayersSpeciesBoard.displaySpeciesBoard();
				System.out.println("");
			}
			
		}	
		
		
	}
	
	public static void main(String[] args) {
		
		Game currentGame = new Game();
		
		WateringHole wateringHole = new WateringHole();
		
		// Create and shuffle deck
		Deck deck = new Deck();
		
		deck.loadDeck();
		
		// Phase 1 - Deal Cards and set up the board
		
		// Create players.
		Player playerOne = new Player(1);
		Player playerTwo = new Player(2);

		// Deal starting hand (4 cards each).
		System.out.print("Drawing cards for Player 1: ");
		Hand handforPlayer1 = new Hand(4,deck);
		
		System.out.print("Drawing cards for Player 2: ");
		Hand handforPlayer2 = new Hand(4,deck);
		
		// Create starting game species board
		SpeciesBoard SpeciesBoard1 = new SpeciesBoard();
		System.out.print("Player 1 Board: ");
		SpeciesBoard1.displaySpeciesBoard();

		SpeciesBoard SpeciesBoard2 = new SpeciesBoard();
		System.out.print("Player 2 Board: ");
		SpeciesBoard2.displaySpeciesBoard();
		
		// Phase 2 - Each player selects food for the watering hole 
		Scanner scan = new Scanner(System.in);
		System.out.println("Player 1, which card would you like to select for the watering hole?");
		int input = scan.nextInt();
		int plantfoodNum1 = handforPlayer1.getValuefromCard(input);
		wateringHole.updateCurrentFoodAvailable(plantfoodNum1);
		handforPlayer1.removeCardfromHand(input);
		
		System.out.println("Player 2, which card would you like to select for the watering hole?");
		int input2 = scan.nextInt();
		int plantfoodNum2 = handforPlayer2.getValuefromCard(input2);
		wateringHole.updateCurrentFoodAvailable(plantfoodNum2);
		handforPlayer2.removeCardfromHand(input2);
		
		
		
		// Phase 3 - Play Cards

		// Player One plays Phase Three.
		currentGame.playPhaseThree(playerOne, handforPlayer1, SpeciesBoard1, scan);
		
		// Player Two plays Phase Three.
		currentGame.playPhaseThree(playerTwo, handforPlayer2, SpeciesBoard2, scan);

		
		// Phase 4 - Feeding
		
		// Reveal the food cards in watering hole
		
		System.out.println("");
		System.out.println("Feeding starts!");
		wateringHole.displayWH();
		
		// TODO: feeding species
		
		// Turn actions loop
		
		
	}
}
	
	
	
	
