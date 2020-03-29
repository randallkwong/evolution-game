
public class Species {

	// The population of a species.
	int population;
	
	// The body size of a species.
	int bodySize;
	
	// The maximum amount of food the species can consume.
	int foodCapacity;
	
	// THe current amount of food the species is storing.
	int currentFoodConsumed;
	
	// Traits
	boolean isCarnivore;
	boolean hasFatTissue;
	boolean hasClimbing;
	
	// The current number of trait cards on this species. A species can not have more than 3 trait cards;
	int numberOfTraits;

	public Species() {
		this.population = 1;
		this.bodySize = 1;
		this.currentFoodConsumed = 0;
		this.isCarnivore = false;
		this.hasFatTissue = false;
		this.hasClimbing = false;
		this.numberOfTraits = 0;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public int getBodysize() {
		return bodySize;
	}
	
	public int getTraitcard() {
		return numberOfTraits;
	}
	
	public int getFoodConsumed() {
		return currentFoodConsumed;
	}
	
	public boolean getFatTissue() {
		return hasFatTissue;
	}
	
	public boolean getCarnivore() {
		return isCarnivore;
	}
	
	public boolean getClimbing() {
		return hasClimbing;
	}
	
}
