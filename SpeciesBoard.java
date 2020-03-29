import java.util.HashMap;

public class SpeciesBoard {
	HashMap <Integer, Species> newPlayerBoard;
	
	public SpeciesBoard(){
		newPlayerBoard = new HashMap <Integer, Species>();
		Species newSpecies = new Species(1,1,0,false,false,false,0);
		newPlayerBoard.put(1,newSpecies);
	}
	
	//add a new species to the player board
	public void addNewSpeciestoRight() {
		int currentNumofSpecies = newPlayerBoard.size();
		Species newSpecies = new Species(1,1,0,false,false,false,0);
		newPlayerBoard.put(currentNumofSpecies+1, newSpecies);
	}
	
	public void displaySpeciesBoard() {
		System.out.println("There are currently " + newPlayerBoard.size() + " species on the board." );
		
		for (Integer key : newPlayerBoard.keySet()) {
			System.out.println("Stats for species #" + key);
			Species value = newPlayerBoard.get(key);
			System.out.println("Body Size: " + value.getBodysize() + ", " + "Population: " + value.getPopulation() + ", " + "Food Consumed: " + value.getFoodConsumed());
		}
	}
	
	
	/*
	 * input position of the species
	 */
	public void updateBodySize(int pos) {
		Species value = newPlayerBoard.get(pos);
		int BodySize = value.getBodysize() + 1;
		int population = value.getPopulation();
		int foodconsumed = value.getFoodConsumed();
		boolean carnivore = value.getCarnivore();
		boolean fatTissue = value.getFatTissue();
		boolean climbing = value.getClimbing();
		int numoftraitcards = value.getTraitcard();
		Species newSpecies = new Species(BodySize,population,foodconsumed,carnivore,fatTissue,climbing,numoftraitcards);
		newPlayerBoard.put(pos,newSpecies);
	}
	
	public void updatePopulation(int pos) {
		Species value = newPlayerBoard.get(pos);
		int BodySize = value.getBodysize();
		int population = value.getPopulation() + 1;
		int foodconsumed = value.getFoodConsumed();
		boolean carnivore = value.getCarnivore();
		boolean fatTissue = value.getFatTissue();
		boolean climbing = value.getClimbing();
		int numoftraitcards = value.getTraitcard();
		Species newSpecies = new Species(BodySize,population,foodconsumed,carnivore,fatTissue,climbing,numoftraitcards);
		newPlayerBoard.put(pos,newSpecies);
	}
	
	public void updateTraitCard(int pos, String cardName) {
		Species value = newPlayerBoard.get(pos);
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
		
		Species newSpecies = new Species(BodySize,population,foodconsumed,carnivore,fatTissue,climbing,numoftraitcards);
		newPlayerBoard.put(pos,newSpecies);
	}
}
