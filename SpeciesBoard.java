import java.util.HashMap;

public class SpeciesBoard {
	HashMap <Integer, Species> newPlayerBoard;
	Species newSpecies = new Species(1,1,1,0,false,false,false,0);
	
	public SpeciesBoard(){
		newPlayerBoard = new HashMap <Integer, Species>();
		newPlayerBoard.put(1,newSpecies);
	}
	
	//add a new species to the player board on the right hand side
	public void addNewSpeciestoRight() {
		int currentNumofSpecies = newPlayerBoard.size();
		newPlayerBoard.put(currentNumofSpecies+1, newSpecies);
	}
	
	
	//add a new species to the player board on the left hand side
	public void addNewSpeciestoLeft() {
		for (int i = newPlayerBoard.size(); i > 0; i--) {
			Species tmp = newPlayerBoard.get(i);
			newPlayerBoard.put(i+1,tmp);
		}
		newPlayerBoard.put(1,newSpecies);
	}
	
	public int numberOfSpeciesInPlay() {
				
		return newPlayerBoard.size();
		
	}
	
	public void displaySpeciesBoard() {
		System.out.println(newPlayerBoard.size() + " species on the board." );
		
		for (Integer key : newPlayerBoard.keySet()) {
			System.out.print("Species " + key + " - ");
			Species value = newPlayerBoard.get(key);
			System.out.println("Body Size: " + value.getBodysize() + ", " + "Population: " + value.getPopulation() + ", " + "Food Consumed: " + value.getFoodConsumed());
		}
	}
	
	
	/*
	 * input position of the species
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
		Species newSpecies = new Species(isAlive, population,BodySize,foodconsumed,carnivore,fatTissue,climbing,numoftraitcards);
		newPlayerBoard.put(pos,newSpecies);
	}
	
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
		Species newSpecies = new Species(isAlive, population,BodySize,foodconsumed,carnivore,fatTissue,climbing,numoftraitcards);
		newPlayerBoard.put(pos,newSpecies);
	}
	
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
		Species newSpecies = new Species(isAlive, population,BodySize,foodconsumed,carnivore,fatTissue,climbing,numoftraitcards);
		newPlayerBoard.put(pos,newSpecies);
	}
	
	
	public void updateTraitCard(int pos, String cardName) {
		Species value = newPlayerBoard.get(pos);
		int isAlive = value.getIsAlive();
		int BodySize = value.getBodysize();
		int population = value.getPopulation();
		int foodconsumed = value.getFoodConsumed();
		boolean carnivore = value.getCarnivore();
		boolean fatTissue = value.getFatTissue();
		boolean climbing = value.getClimbing();
		int numoftraitcards = value.getTraitcard()+1;
		
		if (cardName == "Carnivore") {
			carnivore = true;
		}
		if (cardName == "Climbing") {
			climbing = true;
		}
		if (cardName == "Fat Tissue") {
			fatTissue = true;
		}
		
		Species newSpecies = new Species(isAlive, BodySize,population,foodconsumed,carnivore,fatTissue,climbing,numoftraitcards);
		newPlayerBoard.put(pos,newSpecies);
	}
}
