import java.util.Scanner;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class Game {
	
	//Button btnEndRound;
	
	Card card;
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
	
	public void playPhaseThree(Player currentPlayer, Hand currentPlayersHand, SpeciesBoard currentPlayersSpeciesBoard, Label pseudoConsoleLog) {

		// Clears player prompt.
		pseudoConsoleLog.setText("");
		
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
				currentPlayersHand.updateHand();
				
					TextInputDialog td_endRound = new TextInputDialog();
					td_endRound.setHeaderText("Press 0 to continue this round; \nPress 1 to end this round" );
					TextField endRoundInput = td_endRound.getEditor();
					td_endRound.showAndWait();
					
					int endTurnOrPlay = Integer.parseInt(endRoundInput.getText());
					
					if(endTurnOrPlay == 1) {
						currentPlayer.endPhaseThree();
						System.out.println("End of Play Card phase for " + currentPlayerName);
		
						System.out.println(currentPlayerName + " board");
						currentPlayersSpeciesBoard.displaySpeciesBoard();
						System.out.println();
		
					}
					else {
						System.out.println(currentPlayerName + ", which card would you like to select?");
						
						TextInputDialog td_card = new TextInputDialog();
						td_card.setHeaderText(currentPlayerName + ", which card would you like to select?");
						td_card.setContentText("Card");
						TextField cardInput = td_card.getEditor();
						td_card.showAndWait();

						int selectedCardIndex = Integer.parseInt(cardInput.getText());
						String currentCardTrait = ((Card) currentPlayersHand.hand.get(selectedCardIndex-1)).getTrait();
						System.out.println(currentPlayerName + ", what would you like to do with your card " + selectedCardIndex + " ?");
						//System.out.println("Press 1 to create a new species on the left; Press 2 to create new species on the right, Press 3 to increase body size, Press 4 to increase population size, Press 5 to attach " + currentCardTrait);
						
						TextInputDialog td_action = new TextInputDialog();
						
						td_action.setHeaderText("Enter action for this card: \nEnter 1 to create a new species on the left \nEnter 2 to create new species on the right \nEnter 3 to increase body size \nEnter 4 to increase population size \nEnter 5 to attach traits");
						td_action.setContentText("Action");
						TextField actionInput = td_action.getEditor();
						td_action.showAndWait();
						
						TextInputDialog td_species = new TextInputDialog();
						
						td_species.setHeaderText("Select a species");
						td_species.setContentText("Species");
						TextField speciesInput = td_species.getEditor();
						
						
						// Handle user actions
							
							int input3 = Integer.parseInt(actionInput.getText());
							//int input3 = scan.nextInt();
							
							if (input3 == 1) {
								currentPlayersSpeciesBoard.addNewSpeciestoLeft();
							}
							else if (input3 == 2) {
								currentPlayersSpeciesBoard.addNewSpeciestoRight();
							}
							else if (input3 == 3) {
								pseudoConsoleLog.setText("Which species would you like to increase bodysize for?");
								td_species.showAndWait();
								int input4 = Integer.parseInt(speciesInput.getText());
								//int input4 = scan.nextInt();
								currentPlayersSpeciesBoard.updateBodySize(input4-1);
								
								// Clears player prompt
								pseudoConsoleLog.setText("");
							}
							else if (input3 == 4) {
								pseudoConsoleLog.setText("Which species would you like to increase the population for?");
								td_species.showAndWait();
								int input5 = Integer.parseInt(speciesInput.getText());
								//int input5 = scan.nextInt();
								currentPlayersSpeciesBoard.updatePopulation(input5-1, 1);
								
								// Clears player prompt
								pseudoConsoleLog.setText("");
							}
							else if (input3 == 5) {
								String whichSpeciesToAttachPrompt = "Which species would you like to attach " + currentCardTrait + "?";
								pseudoConsoleLog.setText(whichSpeciesToAttachPrompt);
								td_species.showAndWait();
								int playTraitOnSpeciesIndex = Integer.parseInt(speciesInput.getText());
								currentPlayersSpeciesBoard.updateTraitCard(playTraitOnSpeciesIndex-1, currentCardTrait, pseudoConsoleLog);
								
								// Clears player prompt
//								pseudoConsoleLog.setText("");
								
								//int playTraitOnSpeciesIndex = scan.nextInt();
								//currentPlayersSpeciesBoard.updateTraitCard(playTraitOnSpeciesIndex, currentCardTrait, scan);
							}

							
							// TODO: Play trait card
							
							// Remove card
							currentPlayersHand.removeCardfromHand(selectedCardIndex);
							
							currentPlayersSpeciesBoard.displaySpeciesBoard();
							System.out.println("");
					}
				//int endTurnOrPlay = scan.nextInt();
			
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
	public void feedingPhase(int i, Player currentPlayer, SpeciesBoard currentPlayersSpeciesBoard, WateringHole wateringHole, Label wateringHoleDisplay, Label pseudoConsoleLog) {
		
		// TODO: Add logic to check if user wants to feed carnivorous species off the species board.
		wateringHole.displayWH(wateringHoleDisplay);
		
		if((i == currentPlayer.getPlayerNumber())&&(currentPlayer.getIsFeeding() == 1)) {

			String currentPlayerName = "Player " + currentPlayer.getPlayerNumber();
			
			// Variable to break loop.
			boolean ableToContinue = false;
			
			// Add logic to handle case where there is no plant food available in the watering hole. 
			
			if (wateringHole.getCurrentFoodAvailable() == 0) {
				currentPlayer.isDoneFeeding();
				ableToContinue = true;
			}
			
			while(ableToContinue == false) {
				
			// Prompt the player to feed. If the selected species to feed is invalid, re-prompt the user for input.
			TextInputDialog td_species1 = new TextInputDialog();
				
			td_species1.setHeaderText(currentPlayerName + ", \nEnter species for feeding. \nEnter 0 to skip one feeding. \nEnter -1 to skip all feeding");
			td_species1.setContentText("Species");
			td_species1.showAndWait(); 
			TextField speciesInput = td_species1.getEditor();
			
			currentPlayersSpeciesBoard.displaySpeciesBoard();
			
			int speciesToFeed = Integer.parseInt(speciesInput.getText())-1;
				
			if(speciesToFeed == -2) {
				currentPlayer.isDoneFeeding();
				ableToContinue = true;
			}
			else 
			{
				if (speciesToFeed == -1) {
				System.out.println(currentPlayerName + " skips one round of feeding");
				ableToContinue = true;
				}
				else {
					// TODO: Add logic which only allows player to feed a species that exists.
					// TODO: Refactor species boards to belong to players.
					if((((Species) currentPlayersSpeciesBoard.newPlayerBoard.get(speciesToFeed)).getFoodConsumed() < ((Species) currentPlayersSpeciesBoard.newPlayerBoard.get(speciesToFeed)).getFoodCapacity()) && (wateringHole.getCurrentFoodAvailable() > 0)) {
						currentPlayersSpeciesBoard.updateFoodConsumed(speciesToFeed);
						System.out.println("Displaying Species Board for " + currentPlayerName);
						currentPlayersSpeciesBoard.displaySpeciesBoard();
						
						// Decrement available food in the watering hole.
						wateringHole.decrementFoodAvailable(1);
						wateringHole.displayWH(wateringHoleDisplay);
						
						ableToContinue = true;
						
					} 
					else
					{
						int speciesToFeedActual = speciesToFeed + 1;
						String youCannotFeedSpeciesWarning = "You cannot feed Species " + speciesToFeedActual;
						pseudoConsoleLog.setText(youCannotFeedSpeciesWarning);
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
		
		for (int i = 0; i < currentPlayersSpeciesBoard.newPlayerBoard.size(); i++) {
			System.out.print("Species " + i+1 + " - ");
			Species value = (Species) currentPlayersSpeciesBoard.newPlayerBoard.get(i);
			int foodPointsToAdd = value.getFoodConsumed();
			System.out.println("Food Consumed: " + value.getFoodConsumed());
			
			// Add consume food to player's food bag.
			currentPlayer.addFoodPoints(foodPointsToAdd);
			
			// Reset food consumed for current species.
			currentPlayersSpeciesBoard.moveFoodConsumed(i, foodPointsToAdd);
		}
		
		System.out.println(currentPlayerName + " has " + currentPlayer.getFoodPoints() + " food points");
		
	}
	
	/**
	 * This method handles species starving, which is when they do not receive food equal
	 * to their population size. Population decreases and species with no population left
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
		
		for (int i = 0; i < currentPlayersSpeciesBoard.newPlayerBoard.size(); i++) {
			
			System.out.print("Species " + i + 1 + " - ");
			Species value = (Species) currentPlayersSpeciesBoard.newPlayerBoard.get(i);
			System.out.println("Is Alive: " + value.getIsAlive() + ", Body Size: " + value.getBodysize() + ", " + "Population: " + value.getPopulation() + ", " + "Food Consumed: " + value.getFoodConsumed());
			int foodDeficit = (value.getPopulation() - value.getFoodConsumed()) * -1;
			currentPlayersSpeciesBoard.updatePopulation(i, foodDeficit);
			value = (Species) currentPlayersSpeciesBoard.newPlayerBoard.get(i);
			System.out.println("Is Alive: " + value.getIsAlive() + ", Body Size: " + value.getBodysize() + ", " + "Population: " + value.getPopulation() + ", " + "Food Consumed: " + value.getFoodConsumed());
		}
		currentPlayersSpeciesBoard.extinctSpeciesBoard();
		System.out.println("Display player board after extinction");
		currentPlayersSpeciesBoard.displaySpeciesBoard();
	}
	
	public void scoreGame(Player playerOne, SpeciesBoard SpeciesBoard1, Player playerTwo, SpeciesBoard SpeciesBoard2) {
		
		// Score both players and determine the winner.
		playerOne.calculateScore(SpeciesBoard1);
		playerTwo.calculateScore(SpeciesBoard2);
		
		String winningPlayer = calculateWinner(playerOne, playerTwo);
		
		// Display the winner and game stats to the players.
		Alert alertEndGame = new Alert(AlertType.INFORMATION);
		alertEndGame.setTitle("Game over");

		alertEndGame.setHeaderText(winningPlayer + " won the game");

		String scoreComparisonText = "The final scores were...\n\n" + "Player 1: " + playerOne.getScore() + " points\n" + "Player 2: " + playerTwo.getScore() + " points\n\n" + "Thanks for playing!";
		alertEndGame.setContentText(scoreComparisonText);
		alertEndGame.show();
	}

	public String calculateWinner(Player playerOne, Player playerTwo) {
		
		String winner = "";
		
		if(playerOne.getScore() > playerTwo.getScore()) {
			winner = "Player One";
		}
		if (playerOne.getScore() < playerTwo.getScore()) {
			winner = "Player Two";
		}
		if (playerOne.getScore() == playerTwo.getScore()) {
			winner = "Nobody";
		}
		
		return winner;
	}
	
}
	
	
	
	
