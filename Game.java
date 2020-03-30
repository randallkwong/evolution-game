import java.util.Scanner;
import java.util.HashMap;

public class Game {
	
	boolean gameIsNotFinished;
	
	public Game() {
		this.gameIsNotFinished = false;
	}
	
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
				System.out.println();

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
					currentPlayersSpeciesBoard.updatePopulation(input5, 1);
				}
				
				// TODO: Play trait card
				
				// Remove card
				currentPlayersHand.removeCardfromHand(selectedCardIndex);
				
				currentPlayersSpeciesBoard.displaySpeciesBoard();
				System.out.println("");
			}
			
		}	
		
		
	}
	
	public void feedingPhase(int i, Player currentPlayer, SpeciesBoard currentPlayersSpeciesBoard, WateringHole wateringHole, Scanner scan) {
		
		// TODO: Add logic to check if user wants to feed carnivorous species off the species board.
		
		if((i == currentPlayer.getPlayerNumber())&&(currentPlayer.getIsFeeding() == 1)) {

			String currentPlayerName = "Player " + currentPlayer.getPlayerNumber();
			
			// Variable to break loop.
			boolean ableToContinue = false;
			
			while(ableToContinue == false) {

			// Prompt the player to feed. If the selected species to feed is invalid, re-prompt the user for input.
			System.out.println(currentPlayerName + ": Which species would you like to feed?");
			System.out.println("Enter 0 to skip one feeding");
			System.out.println("Enter -1 to skip all feeding");
			
			currentPlayersSpeciesBoard.displaySpeciesBoard();
			
			int speciesToFeed = scan.nextInt();
				
			if(speciesToFeed == -1) {
				currentPlayer.isDoneFeeding();
				ableToContinue = true;
			}
			else 
			{
				if (speciesToFeed == 0) {
				System.out.println(currentPlayerName + " skips one round of feeding");
				ableToContinue = true;
				}
				else {
					
					// TODO: Refactor species boards to belong to players.
					if((currentPlayersSpeciesBoard.newPlayerBoard.get(speciesToFeed).getFoodConsumed() < currentPlayersSpeciesBoard.newPlayerBoard.get(speciesToFeed).getFoodCapacity()) && (wateringHole.getCurrentFoodAvailable() > 0)) {
						currentPlayersSpeciesBoard.updateFoodConsumed(speciesToFeed);
						currentPlayersSpeciesBoard.displaySpeciesBoard();
						
						// Decrement available food in the watering hole.
						wateringHole.decrementFoodAvailable(1);
						wateringHole.displayWH();
						
						ableToContinue = true;
						
					} else
					{
						System.out.println("You cannot feed Species " + speciesToFeed);
					}						
					
				}
				
			}
			
			// Next player can continue
			}
				
		}		
		
		
	}
	
	public void moveConsumedFoodToFoodBag(Player currentPlayer, SpeciesBoard currentPlayersSpeciesBoard) {
		
		String currentPlayerName = "Player " + currentPlayer.getPlayerNumber();
		
		System.out.println(currentPlayerName + " tally up food points");
		for (Integer key : currentPlayersSpeciesBoard.newPlayerBoard.keySet()) {
			System.out.print("Species " + key + " - ");
			Species value = currentPlayersSpeciesBoard.newPlayerBoard.get(key);
			int foodPointsToAdd = value.getFoodConsumed();
			System.out.println("Food Consumed: " + value.getFoodConsumed());
			
			// Add consume food to player's food bag.
			currentPlayer.addFoodPoints(foodPointsToAdd);
			
			// Reset food consumed for current species.
			currentPlayersSpeciesBoard.moveFoodConsumed(key, foodPointsToAdd);
			
		}
		
		System.out.println(currentPlayerName + " has " + currentPlayer.getFoodPoints() + " food points");
		
	}
	
	public void starveSpecies(Player currentPlayer, SpeciesBoard currentPlayersSpeciesBoard) {
		
		String currentPlayerName = "Player " + currentPlayer.getPlayerNumber();
		
		System.out.println("Handle " + currentPlayerName + " species that starved if any");
		
		for (Integer key : currentPlayersSpeciesBoard.newPlayerBoard.keySet()) {
			System.out.print("Species " + key + " - ");
			Species value = currentPlayersSpeciesBoard.newPlayerBoard.get(key);
			System.out.println("Is Alive: " + value.getIsAlive() + ", Body Size: " + value.getBodysize() + ", " + "Population: " + value.getPopulation() + ", " + "Food Consumed: " + value.getFoodConsumed());
			int foodDeficit = (value.getPopulation() - value.getFoodConsumed()) * -1;
			currentPlayersSpeciesBoard.updatePopulation(key, foodDeficit);
			value = currentPlayersSpeciesBoard.newPlayerBoard.get(key);
			System.out.println("Is Alive: " + value.getIsAlive() + ", Body Size: " + value.getBodysize() + ", " + "Population: " + value.getPopulation() + ", " + "Food Consumed: " + value.getFoodConsumed());
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
			
			while(playerOne.getIsFeeding() + playerTwo.getIsFeeding() != 0) {
				
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
	
	
	
	
