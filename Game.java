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
	private boolean gameIsNotFinished;
	
	/**
	 *  The Game class contains methods which handle the primary game logic and outline the various
	 *  play phases that each player advances through during one round of the species'
	 *  creation, evolution, feeding, starvation, and transfer of food.
	 */
	public Game() {
		// A variable to indicate whether or not the game is finished.
		this.gameIsNotFinished = true;
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
		
		// Updates the Species indicies.
		currentPlayersSpeciesBoard.displaySpeciesBoard();
		
		String currentPlayerName = "Player " + currentPlayer.getPlayerNumber();
		
		while(currentPlayer.getPhaseThreeStatus() == 0)
		{
		
			if (currentPlayersHand.getHandSize() == 0) {
				
				currentPlayer.endPhaseThree();
				pseudoConsoleLog.setText("End of Play Card phase for " + currentPlayerName + "\n" + "Continue to the next button");
				
			}
			else {
				
				currentPlayer.phaseThreeStart();
				currentPlayersHand.updateHand();
				
				String phaseThreePlayerHelpText = "During Phase Three, you may: " + "\n" + "1) Create a new species on the left" + "\n" + "2) Create new species on the right" + "\n" + "3) Increase body size" + "\n" + "4) Increase population size" + "\n" + "5) Attach traits to your species";
				pseudoConsoleLog.setText(phaseThreePlayerHelpText);
				
					int endTurnOrPlay = -1;
					
					String endTurnOrPlayHeaderText = "Enter 0 to continue this round \nEnter 1 to end this round";
					String endTurnOrPlayContentText = "";
	
					while(
							!((endTurnOrPlay <= 1) && (endTurnOrPlay >= 0))
						 ) {
					
						endTurnOrPlay = promptUserInputForInteger(endTurnOrPlayHeaderText, endTurnOrPlayContentText);
					
					}
					
					if(endTurnOrPlay == 1) {
						currentPlayer.endPhaseThree();
						System.out.println("End of Play Card phase for " + currentPlayerName);
		
						System.out.println(currentPlayerName + " board");
						currentPlayersSpeciesBoard.displaySpeciesBoard();
						System.out.println();
		
					}
					else {
						
						System.out.println(currentPlayerName + ", which card would you like to select?");
						
						
						String cardSelectionHeaderText = currentPlayerName + ", which card would you like to select?";
						String cardSelectionContentText = "Card";

						int selectedCardIndex = -1;
						
						while(
								!((selectedCardIndex <= currentPlayersHand.hand.size()) && (selectedCardIndex > 0))
							 ) {
						
							selectedCardIndex = promptUserInputForInteger(cardSelectionHeaderText, cardSelectionContentText);
						
						}
						
						String currentCardTrait = ((Card) currentPlayersHand.hand.get(selectedCardIndex-1)).getTrait();
						
						pseudoConsoleLog.setText(currentPlayerName + ", what would you like to do with your card " + "[" + selectedCardIndex + "]" + " ?");
												
						String userActionHeaderText = "Enter action for this card: \nEnter 1 to create a new species on the left \nEnter 2 to create new species on the right \nEnter 3 to increase body size \nEnter 4 to increase population size \nEnter 5 to attach traits";
						String userActionContextText = "Action";
						
						String selectSpeciesHeaderText = "Select a species";
						String selectSpeciesContentText = "Species";
						
						// Handle user actions
						
						int userActionInput = -1;
							
						while(
								!((userActionInput <= 5) && (userActionInput > 0))
							 ) {
							
								// Collects input for user action.
								userActionInput = promptUserInputForInteger(userActionHeaderText, userActionContextText);
								
								}
								
								if (userActionInput == 1) {
									currentPlayersSpeciesBoard.addNewSpeciestoLeft();
								}
								else if (userActionInput == 2) {
									currentPlayersSpeciesBoard.addNewSpeciestoRight();
								}
								else if (userActionInput == 3) {
									
									int input4 = -1;
								
									while(
											!((input4 <= currentPlayersSpeciesBoard.newPlayerBoard.size()) && (input4 > 0))
										 ) {
		
										pseudoConsoleLog.setText("Which species would you like to increase bodysize for?");
										input4 = promptUserInputForInteger(selectSpeciesHeaderText, selectSpeciesContentText);
										
										}
	
									currentPlayersSpeciesBoard.updateBodySize(input4-1);
									
									// Clears player prompt
									pseudoConsoleLog.setText("");
									
								}
	
	
								else if (userActionInput == 4) {
									
									int input5 = -1;
									
									while(
											!((input5 <= currentPlayersSpeciesBoard.newPlayerBoard.size()) && (input5 > 0))
										 ) {
	
									pseudoConsoleLog.setText("Which species would you like to increase the population for?");
									input5 = promptUserInputForInteger(selectSpeciesHeaderText, selectSpeciesContentText);
									
									}
									
									currentPlayersSpeciesBoard.updatePopulation(input5-1, 1);
									
									// Clears player prompt
									pseudoConsoleLog.setText("");
									
								}
								else if (userActionInput == 5) {
									
									int playTraitOnSpeciesIndex = -1;
									
									while(
											!((playTraitOnSpeciesIndex <= currentPlayersSpeciesBoard.newPlayerBoard.size()) && (playTraitOnSpeciesIndex > 0))
										 ) {
									
									String whichSpeciesToAttachPrompt = "Which species would you like to attach " + currentCardTrait + "?";
									pseudoConsoleLog.setText(whichSpeciesToAttachPrompt);
									
									playTraitOnSpeciesIndex = promptUserInputForInteger(selectSpeciesHeaderText, selectSpeciesContentText);
									
									}
									
									currentPlayersSpeciesBoard.updateTraitCard(playTraitOnSpeciesIndex-1, currentCardTrait, pseudoConsoleLog);
									// Clears player prompt
									pseudoConsoleLog.setText("");
									
								}
	
								
								// TODO: Play trait card
								
								// Remove card
								currentPlayersHand.removeCardfromHand(selectedCardIndex);
								
								currentPlayersSpeciesBoard.displaySpeciesBoard();
								System.out.println("");
								
								
						// End of userActionInput while loop
//						}
						
				
				// End of endTurnOrPlay input
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
	
	/**
	 * This method calculates the scores for the game and displays the end game scores and winner.
	 * 
	 * @param playerOne
	 * Takes player one as an input.
	 * 
	 * @param SpeciesBoard1
	 * Takes player one's species board as an input. The contents of the board will be used to calculate scores.
	 * 
	 * @param playerTwo
	 * Takes player two as an input.
	 * 
	 * @param SpeciesBoard2
	 * Takes player two's species board as an input. The contents of the board will be used to calculate scores.
	 * 
	 */
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

	/**
	 * This method calculates the winner of the game.
	 * 
	 * @param playerOne
	 * Takes player one as an input.
	 * 
	 * @param playerTwo
	 * Takes player two as an input.
	 * 
	 * @return
	 * Returns a string for the player who won.
	 */
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
	
	
	/**
	 * This method takes the header text and content text and returns the user input as an integer.
	 * 
	 * @param headerText
	 * This is the header text that will populate in the dialog box.
	 * 
	 * @param contentText
	 * This is the content text that will populate in the dialog box.
	 * 
	 * @return
	 */
	public static int promptUserInputForInteger(String headerText, String contentText) {
		
		TextInputDialog td_species = new TextInputDialog();
		td_species.setHeaderText(headerText);
		td_species.setContentText(contentText);
		TextField textInput = td_species.getEditor();
		td_species.showAndWait();
		int returnInt = Integer.parseInt(textInput.getText());

		return returnInt;
	}
	
	/**
	 * This method handles the logic for when a player selects a card from their hand to contribute
	 * its food value to the watering hole.
	 * 
	 * @param currentPlayer
	 * Takes the current player.
	 * 
	 * @param currentHand
	 * Takes the current player's hand of cards.
	 * 
	 * @param wateringHole
	 * Takes the game's watering hole.
	 * 
	 * @param wateringHoleDisplay
	 * Take's the watering hole label which displays the available plant food.
	 */
	public void playWateringHoleCard(Player currentPlayer, Hand currentHand, WateringHole wateringHole, Label wateringHoleDisplay) {
		
		String feedingHeaderTextP1 = "Player " + currentPlayer.getPlayerNumber() + ": Which card would you like to select to add food to the watering hole?";
		String feedingContentTextP1 = "Select which [Card] you'd like to use for Watering Hole";
		
		// Set a dummy value before entering the loop.
		int playerSelectedFoodCard = -1;
		
		while(
				!((playerSelectedFoodCard <= currentHand.getHandSize()) && (playerSelectedFoodCard > 0))
			 ) {
			// Gets the user input.
			playerSelectedFoodCard = Game.promptUserInputForInteger(feedingHeaderTextP1, feedingContentTextP1);			
		}
		
		int plantFoodValue = currentHand.getValuefromCard(playerSelectedFoodCard);
		wateringHole.updateCurrentFoodAvailable(plantFoodValue);
		
		// Remove the selected card from the player's hand.
		currentHand.removeCardfromHand(playerSelectedFoodCard);

		// Update the available plant food in the watering hole.
		wateringHole.displayWH(wateringHoleDisplay);
		
	}

	/**
	 * 
	 * @return
	 * Returns a boolean indicating whether or not the game is finished.
	 */
	public boolean isGameIsNotFinished() {
		return gameIsNotFinished;
	}
	
	/**
	 * 
	 * @param gameIsNotFinished
	 * Sets the boolean that indicates whether or not the game is finished.
	 */
	public void setGameIsNotFinished(boolean gameIsNotFinished) {
		this.gameIsNotFinished = gameIsNotFinished;
	}

}	
	
