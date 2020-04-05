import java.util.Scanner;

public class GameRun {
	
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

		// Create starting hands.
		Hand handforPlayer1 = new Hand();
		Hand handforPlayer2 = new Hand();
		
		// Create starting game species board
		SpeciesBoard SpeciesBoard1 = new SpeciesBoard();
		System.out.print("Player 1 Board: ");
		SpeciesBoard1.displaySpeciesBoard();

		SpeciesBoard SpeciesBoard2 = new SpeciesBoard();
		System.out.print("Player 2 Board: ");
		SpeciesBoard2.displaySpeciesBoard();
		
		while(currentGame.gameIsNotFinished == false) {
			

			Scanner scan = new Scanner(System.in);
			
			// Draw cards (Starting hand is 4 cards each).
			// Players draw one card for each species they have on the board
			// plus three additional cards.
			System.out.print("Drawing cards for Player 1: ");
			handforPlayer1.drawCards(SpeciesBoard1.numberOfSpeciesInPlay() + 3, deck);
			handforPlayer1.displayHand();
			
			System.out.print("Drawing cards for Player 2: ");
			handforPlayer2.drawCards(SpeciesBoard2.numberOfSpeciesInPlay() + 3,deck);
			handforPlayer2.displayHand();

			// Phase 2 - Each player selects food for the watering hole 
			System.out.println("Player 1 Hand:");
			handforPlayer1.displayHand();
			System.out.println("Player 1, which card would you like to select for the watering hole?");
			int input = scan.nextInt();
			int plantfoodNum1 = handforPlayer1.getValuefromCard(input);
			wateringHole.updateCurrentFoodAvailable(plantfoodNum1);
			handforPlayer1.removeCardfromHand(input);
			
			System.out.println("Player 2 Hand:");
			handforPlayer2.displayHand();
			System.out.println("Player 2, which card would you like to select for the watering hole?");
			int input2 = scan.nextInt();
			int plantfoodNum2 = handforPlayer2.getValuefromCard(input2);
			wateringHole.updateCurrentFoodAvailable(plantfoodNum2);
			handforPlayer2.removeCardfromHand(input2);
			
			
			
			// Phase 3 - Play Cards
			
			// Ready the players.
			playerOne.readyForPhaseThree();
			playerTwo.readyForPhaseThree();
	
			// Player One plays Phase Three.
			currentGame.playPhaseThree(playerOne, handforPlayer1, SpeciesBoard1, scan);
			
			// Player Two plays Phase Three.
			currentGame.playPhaseThree(playerTwo, handforPlayer2, SpeciesBoard2, scan);
	
			// Reveal Trait Cards
			
			
			// Phase 4 - Feeding
			
			playerOne.isReadyToFeed();
			playerTwo.isReadyToFeed();
			
			// Long Neck and Fertile handled first
	
			wateringHole.displayWH();
			
			// Regular feeding loop
			
			System.out.println("Feeding starts!");
			System.out.println("");
			
			while((playerOne.getIsFeeding() + playerTwo.getIsFeeding())!= 0) {
				
				// Loop through players and allow them the opportunity to feed.
				// Alternate between players.
				for(int i = 1; i < 3; i++) {
				
					// Player One feeds species.
					currentGame.feedingPhase(i, playerOne, SpeciesBoard1, wateringHole, scan);
					
					// Player Two feeds species.
					currentGame.feedingPhase(i, playerTwo, SpeciesBoard2, wateringHole, scan);
	
					}
	
				}
				
			System.out.println("End of feeding loop");			
			
			// Handles starving species that lose population or die.
			currentGame.starveSpecies(playerOne, SpeciesBoard1);
			currentGame.starveSpecies(playerTwo, SpeciesBoard2);
			
			// Move food tokens to food bag.
			currentGame.moveConsumedFoodToFoodBag(playerOne, SpeciesBoard1);
			currentGame.moveConsumedFoodToFoodBag(playerTwo, SpeciesBoard2);
			
			// Remove species that died.
			
			
			

			System.out.println("The loop will restart to selecting cards for the watering hole now");
			}

	// End of game is not finished loop
	}
		

}
