import java.util.Scanner;
import java.util.HashMap;

public class Game {
	
	boolean gameIsNotFinished;
	
	public Game() {
		this.gameIsNotFinished = false;
	}
	
	/**
	 * 
	 * This method handles Phase 3 of Evolution where players can discard cards to increase
	 * population, increase body size, create new species, or attach a trait card to a species in play.
	 * 
	 * @param currentPlayer
	 * Takes a player to provide details such as the number and play status.
	 * 
	 * @param currentPlayersHand
	 * Takes a player's current hand so that cards may be played to the game board.
	 * 
	 * @param currentPlayersSpeciesBoard
	 * Takes a player's species board so that it may be updated according to a player's actions.
	 * 
	 * @param scan
	 * Takes the current scanner to capture user input.
	 * 
	 */
	public void playPhaseThree(Player currentPlayer, Hand currentPlayersHand, SpeciesBoard currentPlayersSpeciesBoard, Scanner scan) {

		String currentPlayerName = "Player " + currentPlayer.getPlayerNumber();
		
		while(currentPlayer.getPhaseThreeStatus() == 0)
		{
			
			if (currentPlayersHand.getHandSize() == 0) {
				currentPlayer.endPhaseThree();
				System.out.println("End of Play Card phase for " + currentPlayerName);

				System.out.println(currentPlayerName + " board");
				currentPlayersSpeciesBoard.displaySpeciesBoard();
				System.out.println();
			}
			else {
				currentPlayer.phaseThreeStart();
				currentPlayersHand.displayHand();
			
				int endTurnOrPlay = scan.nextInt();
				
				if(endTurnOrPlay == 1) {
					currentPlayer.endPhaseThree();
					System.out.println("End of Play Card phase for " + currentPlayerName);
	
					System.out.println(currentPlayerName + " board");
					currentPlayersSpeciesBoard.displaySpeciesBoard();
					System.out.println();
	
				}
				else {
				
					System.out.println(currentPlayerName + ", which card would you like to select?");
					int selectedCardIndex = scan.nextInt();
					String currentCardTrait = currentPlayersHand.hand.get(selectedCardIndex-1).getTrait();
					System.out.println(currentPlayerName + ", what would you like to do with your card " + selectedCardIndex + " ?");
					System.out.println("Press 1 to create a new species on the left; Press 2 to create new species on the right, Press 3 to increase body size, Press 4 to increase population size, Press 5 to attach " + currentCardTrait);
					
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
						else if (input3 == 5) {
							System.out.println("Which species would you like to attach " + currentCardTrait + "?");
							int playTraitOnSpeciesIndex = scan.nextInt();
							currentPlayersSpeciesBoard.updateTraitCard(playTraitOnSpeciesIndex, currentCardTrait, scan);
						}

						
						// TODO: Play trait card
						
						// Remove card
						currentPlayersHand.removeCardfromHand(selectedCardIndex);
						
						currentPlayersSpeciesBoard.displaySpeciesBoard();
						System.out.println("");
				}
			
			}	
		}
		
		
	}
	
	/**
	 * This method handles the feeding phase of the players so they can feed their living species.
	 * 
	 * @param i
	 * This keeps track of which player should take their turn as they rotate back and forth in the feeding loop.
	 * 
	 * @param currentPlayer
	 * Takes the current player to keep track of things such as the player's number and play status.
	 * 
	 * @param currentPlayersSpeciesBoard
	 * Takes the current player's species board so that changes to active species may be updated (ex: species receives food).
	 * 
	 * @param wateringHole
	 * Takes the watering hole so that food consumed from the watering hold by herbivores can be updated as needed.
	 * 
	 * @param scan
	 * Takes the active scanner so we can capture user input and apply the right player actions.
	 */
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
					// TODO: Add logic which only allows player to feed a species that exists.
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
	
	/**
	 * This method moves food consumed by species during the feeding round to each player's
	 * food bag where food points are collected to keep track of players' score.
	 * 
	 * @param currentPlayer
	 * Takes the current player so that points may be moved to the active player's food bag.
	 * 
	 * @param currentPlayersSpeciesBoard
	 * Takes the current player's species board so that food consumed may be cleared from
	 * the species that were fed to the player's food bag, essentially resetting them for the next round.
	 * 
	 */
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
	
	/**
	 * This method handles species starving, which is when they do not receive food equal
	 * to their popuulation size. Population decreases and species with no population left
	 * die off.
	 * 
	 * @param currentPlayer
	 * Takes the current player so that we keep track of the player's identity when providing game updates.
	 * 
	 * @param currentPlayersSpeciesBoard
	 * Takes the current player's species board so that updates to species' populations and status as living or not living can be updated.
	 */
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
		currentPlayersSpeciesBoard.extinctSpeciesBoard();
		System.out.println("Display player board after extinction");
		currentPlayersSpeciesBoard.displaySpeciesBoard();
	}
	

}
	
	
	
	
