import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;

/**
 * 
 * This class keeps track of the species in play (on the SpeciesBoard).
 * Each player has a species board containing their living Species.
 *
 */
public class SpeciesBoard {
	
	//HashMap <Integer, Species> newPlayerBoard;
	ObservableList<Node> newPlayerBoard = FXCollections.observableArrayList();
	Species newSpecies = new Species(1,1,1,0,false,false,false,0, new ArrayList<String>(),"1");

	/**
	 * When the SpeciesBoard is first constructed at the game's start, each player begins with one Species.
	 * @param observableList 
	 * @param observableList 
	 */
	public SpeciesBoard(ObservableList<Node> species){
		newPlayerBoard = species;
		//newPlayerBoard = new HashMap <Integer, Species>();
		Species newSpecies1 = new Species(1,1,1,0,false,false,false,0, new ArrayList<String>(),"1");
		newPlayerBoard.add((Species) newSpecies1);
	}
	
	/**
	 * This method adds a new species to the player board on the right hand side.
	 */
	public void addNewSpeciestoRight() {
		//added new species since if it doesn't exist, it will cause error: Children: duplicate children added: parent 
		Species newSpecies2 = new Species(1,1,1,0,false,false,false,0, new ArrayList<String>(),"1");
		newPlayerBoard.add(newSpecies2);
		displaySpeciesBoard();
	}
	
	
	/**
	 * This method adds a new species to the player board on the left hand side.
	 * TODO: this code is buggy. It still causes: Children: duplicate children added: parent
	 */
	public void addNewSpeciestoLeft() {
		
		
		Species tmp = new Species(1, 1, 1, 0, false, false, false, 0, new ArrayList<String>(),"1");
			
		for (int i = newPlayerBoard.size(); i > 0; i--) {
			System.out.println("Board size: " + newPlayerBoard.size());		
			// Add species to the right that is the same as the last species on the board
			newPlayerBoard.add(newPlayerBoard.get(i-1));
			// Repeated this until all the elements have been iterated through.			
		}
		
		newPlayerBoard.add(0,tmp);
		
		displaySpeciesBoard();
	}
	
	/**
	 * 
	 * @return
	 * Returns the number of living species on a player's SpeciesBoard.
	 */
	public int numberOfSpeciesInPlay() {
				
		return newPlayerBoard.size();
		
	}
	
	/**
	 * Prints the active Species and their characteristics on the current SpeciesBoard.
	 */
	public void displaySpeciesBoard() {
		System.out.println(newPlayerBoard.size() + " species on the board." );
	
		for (int j = 0; j < newPlayerBoard.size(); j++) {
			int key = j+1;
			System.out.println("");
			System.out.print("Species " + key + " - ");
			Species value = (Species) newPlayerBoard.get(j);
			System.out.println("Body Size: " + value.getBodysize() + ", " + "Population: " + value.getPopulation() + ", " + "Food Consumed: " + value.getFoodConsumed());
			System.out.println("Traits:");
			for(int i = 0; i < value.getAttachedTraitCards().size(); i++) {
				System.out.print(value.getAttachedTraitCards().get(i) + " ");			
			}
/*		
		for(int i = 0; i < newPlayerBoard.size(); i++) {
			((Species) newPlayerBoard.get(i)).setIndex(Integer.toString(i));
		}
*/
	
		}	
		
		for(int i = 0; i < newPlayerBoard.size(); i++) {
			
			int indexPlusOne = i + 1;
			String newIndex = Integer.toString(indexPlusOne);
			Species value = (Species) newPlayerBoard.get(0);
			int isAlive = value.getIsAlive();
			int BodySize = value.getBodysize();
			int population = value.getPopulation();
			int foodconsumed = value.getFoodConsumed();
			boolean carnivore = value.getCarnivore();
			boolean fatTissue = value.getFatTissue();
			boolean climbing = value.getClimbing();
			int numoftraitcards = value.getTraitcard();
			ArrayList<String>traitsArray = value.getAttachedTraitCards();
			Species newSpecies = new Species(isAlive, population, BodySize, foodconsumed, carnivore, fatTissue, climbing, numoftraitcards, traitsArray, newIndex);

			newPlayerBoard.remove(0);
			newPlayerBoard.add(newSpecies);
			
		}
				
/*
			for(int i = 0; i < newPlayerBoard.size(); i++) {
				((Species) newPlayerBoard.get(i)).setIndex(Integer.toString(i));
				((Species) newPlayerBoard.get(i)).indexOfSpecies.setText("hello");
			}
*/
				
			
	}
	
	
	/**
	 * This method removes Species that have gone extinct. This occurs when a Species population goes to zero.
	 * Those Species are removed from that player's SpeciesBoard.
	 * 
	 * TODO: Re-index the living species for a SpeciesBoard.
	 */
	public void extinctSpeciesBoard() {
		Iterator<Node> iterator = newPlayerBoard.iterator();
		while (iterator.hasNext()) {
			Species key = (Species) iterator.next();
			if (key.getPopulation() <= 0) {
				iterator.remove();
			}
		}
	}
	
	
	/**
	 * This method updates the body size of a species.
	 * 
	 * @param pos
	 * Takes the index of a Species on the SpeciesBoard from the player input.
	 */
	public void updateBodySize(int pos) {
		Species value = (Species) newPlayerBoard.get(pos);
		int isAlive = value.getIsAlive();
		int BodySize = value.getBodysize() + 1;
		int population = value.getPopulation();
		int foodconsumed = value.getFoodConsumed();
		boolean carnivore = value.getCarnivore();
		boolean fatTissue = value.getFatTissue();
		boolean climbing = value.getClimbing();
		int numoftraitcards = value.getTraitcard();
		ArrayList<String>traitsArray = value.getAttachedTraitCards();
		Species newSpecies = new Species(isAlive, population, BodySize, foodconsumed, carnivore, fatTissue, climbing, numoftraitcards, traitsArray,"1");
		newPlayerBoard.set(pos,newSpecies);
	}
	
	/**
	 * This method updates the population size of a species.
	 * 
	 * @param pos
	 * Takes the index of a Species on the SpeciesBoard from the player input.
	 * 
	 * @param amountChanged
	 * Takes the amount to alter a species population by.
	 */
	public void updatePopulation(int pos, int amountChanged) {
		Species value = (Species) newPlayerBoard.get(pos);
		int isAlive = value.getIsAlive();
		int BodySize = value.getBodysize();
		int population = value.getPopulation() + amountChanged;
		if(population <= 0) {
			isAlive = 0;
		}
		int foodconsumed = value.getFoodConsumed();
		boolean carnivore = value.getCarnivore();
		boolean fatTissue = value.getFatTissue();
		boolean climbing = value.getClimbing();
		int numoftraitcards = value.getTraitcard();
		ArrayList<String>traitsArray = value.getAttachedTraitCards();
		Species newSpecies = new Species(isAlive, population, BodySize, foodconsumed, carnivore, fatTissue, climbing, numoftraitcards, traitsArray,"1");
		newPlayerBoard.set(pos,newSpecies);
	}
	
	/**
	 * This method updates the food consumed for a species.
	 * 
	 * @param pos
	 * Takes the index of a Species on the SpeciesBoard from the player input.
	 */
	public void updateFoodConsumed(int pos) {
		Species value = (Species) newPlayerBoard.get(pos);
		int isAlive = value.getIsAlive();
		int BodySize = value.getBodysize();
		int population = value.getPopulation();
		int foodconsumed = value.getFoodConsumed() + 1;
		boolean carnivore = value.getCarnivore();
		boolean fatTissue = value.getFatTissue();
		boolean climbing = value.getClimbing();
		int numoftraitcards = value.getTraitcard();
		ArrayList<String>traitsArray = value.getAttachedTraitCards();
		Species newSpecies = new Species(isAlive, population, BodySize, foodconsumed, carnivore, fatTissue, climbing, numoftraitcards, traitsArray,"1");
		newPlayerBoard.set(pos,newSpecies);
	}

	/**
	 * This method updates the food consumed of a species when food is moved from the SpeciesBoard to a player's food bag.
	 * 
	 * @param pos
	 * Takes the index of a Species on the SpeciesBoard from the player input.
	 * 
	 * @param foodMoved
	 * Takes the amount of food moved.
	 */
	public void moveFoodConsumed(int pos, int foodMoved) {
		Species value = (Species) newPlayerBoard.get(pos);
		int isAlive = value.getIsAlive();
		int BodySize = value.getBodysize();
		int population = value.getPopulation();
		int foodconsumed = value.getFoodConsumed() - foodMoved;
		boolean carnivore = value.getCarnivore();
		boolean fatTissue = value.getFatTissue();
		boolean climbing = value.getClimbing();
		int numoftraitcards = value.getTraitcard();
		ArrayList<String>traitsArray = value.getAttachedTraitCards();
		Species newSpecies = new Species(isAlive, population, BodySize, foodconsumed, carnivore, fatTissue, climbing, numoftraitcards, traitsArray,"1");
		newPlayerBoard.set(pos,newSpecies);
	}
	
	/**
	 * This method updates the traits attached to a species.
	 * 
	 * @param pos
	 * Takes the index of a Species on the SpeciesBoard from the player input.
	 * 
	 * @param cardName
	 * Takes the trait to update.
	 * 
	 * @param scan
	 * Takes the active scanner to capture user input.
	 * 
	 */
	public void updateTraitCard(int pos, String cardName, Label pseudoConsoleLog) {
		Species value = (Species) newPlayerBoard.get(pos);
		int isAlive = value.getIsAlive();
		int BodySize = value.getBodysize();
		int population = value.getPopulation();
		int foodconsumed = value.getFoodConsumed();
		boolean carnivore = value.getCarnivore();
		boolean fatTissue = value.getFatTissue();
		boolean climbing = value.getClimbing();
		int numoftraitcards = value.getTraitcard()+1;
		ArrayList<String>traitsArray = value.getAttachedTraitCards();
		
		if (cardName == "Carnivore") {
			carnivore = true;
		}
		if (cardName == "Climbing") {
			climbing = true;
		}
		if (cardName == "Fat Tissue") {
			fatTissue = true;
		}
		
		if(traitsArray.size() < 3) {
			// Adds trait card to species' traits array.
			traitsArray.add(cardName);
		}
		else {
			// Prompt user to discard an attached trait card.
			pseudoConsoleLog.setText("You already have 3 trait cards. Which card would you like to discard?");

			// TODO: Give user a choice to not discard a card
			
			for(int i = 0; i < traitsArray.size(); i++) {
				int key = i+1;
				System.out.println((key) +": " + traitsArray.get(i));
			}
			
			int indexOfTraitsToDiscard = -999;
			
			String traitReplacementHeaderText = "Select a trait card to replace";
			String traitReplacementContentText = "Trait 1-3";
			
			while(
					!((indexOfTraitsToDiscard <= 3) && (indexOfTraitsToDiscard > 0))
				 ) {
				indexOfTraitsToDiscard = Game.promptUserInputForInteger(traitReplacementHeaderText, traitReplacementContentText);
				pseudoConsoleLog.setText("Please select a valid trait slot of 1-3");			
			}
			
			// Replaces the selected trait with the new card's trait.
			String traitToRemove = traitsArray.get((indexOfTraitsToDiscard -1));
			traitsArray.remove((indexOfTraitsToDiscard-1));

			pseudoConsoleLog.setText(traitToRemove + " was replaced");
			
			traitsArray.add(indexOfTraitsToDiscard-1, cardName);
			
		}
		
		Species newSpecies = new Species(isAlive, population, BodySize, foodconsumed, carnivore, fatTissue, climbing, numoftraitcards, traitsArray,"1");
		newPlayerBoard.set(pos,newSpecies);
	}
}
