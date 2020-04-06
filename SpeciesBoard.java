import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * 
 * This class keeps track of the species in play (on the SpeciesBoard).
 * Each player has a species board containing their living Species.
 *
 */
public class SpeciesBoard {
	HashMap <Integer, Species> newPlayerBoard;
	Species newSpecies = new Species(1,1,1,0,false,false,false,0, new ArrayList<String>());

	/**
	 * When the SpeciesBoard is first constructed at the game's start, each player begins with one Species.
	 */
	public SpeciesBoard(){
		newPlayerBoard = new HashMap <Integer, Species>();
		newPlayerBoard.put(1,newSpecies);
	}
	
	/**
	 * This method adds a new species to the player board on the right hand side.
	 */
	public void addNewSpeciestoRight() {
		int currentNumofSpecies = newPlayerBoard.size();
		newPlayerBoard.put(currentNumofSpecies+1, newSpecies);
	}
	
	
	/**
	 * This method adds a new species to the player board on the left hand side.
	 */
	public void addNewSpeciestoLeft() {
		for (int i = newPlayerBoard.size(); i > 0; i--) {
			Species tmp = newPlayerBoard.get(i);
			newPlayerBoard.put(i+1,tmp);
		}
		newPlayerBoard.put(1,newSpecies);
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
		
		for (Integer key : newPlayerBoard.keySet()) {
			System.out.print("Species " + key + " - ");
			Species value = newPlayerBoard.get(key);
			System.out.println("Body Size: " + value.getBodysize() + ", " + "Population: " + value.getPopulation() + ", " + "Food Consumed: " + value.getFoodConsumed());
			System.out.println("Traits:");
			for(int i = 0; i < value.getAttachedTraitCards().size(); i++) {
				System.out.print(value.getAttachedTraitCards().get(i) + " ");			
			}

		}
	}
	
	
	/**
	 * This method removes Species that have gone extinct. This occurs when a Species population goes to zero.
	 * Those Species are removed from that player's SpeciesBoard.
	 * 
	 * TODO: Re-index the living species for a SpeciesBoard.
	 */
	public void extinctSpeciesBoard() {
		Iterator<Integer> iterator = newPlayerBoard.keySet().iterator();
		while (iterator.hasNext()) {
			Integer key = iterator.next();
			if (newPlayerBoard.get(key).getPopulation() <= 0) {
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
		Species value = newPlayerBoard.get(pos);
		int isAlive = value.getIsAlive();
		int BodySize = value.getBodysize() + 1;
		int population = value.getPopulation();
		int foodconsumed = value.getFoodConsumed();
		boolean carnivore = value.getCarnivore();
		boolean fatTissue = value.getFatTissue();
		boolean climbing = value.getClimbing();
		int numoftraitcards = value.getTraitcard();
		ArrayList<String>traitsArray = value.getAttachedTraitCards();
		Species newSpecies = new Species(isAlive, population, BodySize, foodconsumed, carnivore, fatTissue, climbing, numoftraitcards, traitsArray);
		newPlayerBoard.put(pos,newSpecies);
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
		Species value = newPlayerBoard.get(pos);
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
		Species newSpecies = new Species(isAlive, population, BodySize, foodconsumed, carnivore, fatTissue, climbing, numoftraitcards, traitsArray);
		newPlayerBoard.put(pos,newSpecies);
	}
	
	/**
	 * This method updates the food consumed for a species.
	 * 
	 * @param pos
	 * Takes the index of a Species on the SpeciesBoard from the player input.
	 */
	public void updateFoodConsumed(int pos) {
		Species value = newPlayerBoard.get(pos);
		int isAlive = value.getIsAlive();
		int BodySize = value.getBodysize();
		int population = value.getPopulation();
		int foodconsumed = value.getFoodConsumed() + 1;
		boolean carnivore = value.getCarnivore();
		boolean fatTissue = value.getFatTissue();
		boolean climbing = value.getClimbing();
		int numoftraitcards = value.getTraitcard();
		ArrayList<String>traitsArray = value.getAttachedTraitCards();
		Species newSpecies = new Species(isAlive, population, BodySize, foodconsumed, carnivore, fatTissue, climbing, numoftraitcards, traitsArray);
		newPlayerBoard.put(pos,newSpecies);
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
		Species value = newPlayerBoard.get(pos);
		int isAlive = value.getIsAlive();
		int BodySize = value.getBodysize();
		int population = value.getPopulation();
		int foodconsumed = value.getFoodConsumed() - foodMoved;
		boolean carnivore = value.getCarnivore();
		boolean fatTissue = value.getFatTissue();
		boolean climbing = value.getClimbing();
		int numoftraitcards = value.getTraitcard();
		ArrayList<String>traitsArray = value.getAttachedTraitCards();
		Species newSpecies = new Species(isAlive, population, BodySize, foodconsumed, carnivore, fatTissue, climbing, numoftraitcards, traitsArray);
		newPlayerBoard.put(pos,newSpecies);
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
	public void updateTraitCard(int pos, String cardName, Scanner scan) {
		Species value = newPlayerBoard.get(pos);
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
			System.out.println("You already have 3 trait cards. Which card would you like to discard?");
			// TODO: Give user a choice to not discard a card
			
			for(int i = 0; i < traitsArray.size(); i++) {
				System.out.println((i+1) +": " + traitsArray.get(i));
			}
			
			int indexOfTraitsToDiscard = scan.nextInt();
			String traitToRemove = traitsArray.get((indexOfTraitsToDiscard -1));
			traitsArray.remove((indexOfTraitsToDiscard-1));
			System.out.println(traitToRemove + " was replaced");
			
			traitsArray.add(cardName);
			
		}
		
		Species newSpecies = new Species(isAlive, population, BodySize, foodconsumed, carnivore, fatTissue, climbing, numoftraitcards, traitsArray);
		newPlayerBoard.put(pos,newSpecies);
	}
}
