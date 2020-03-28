
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
	int numofTraits;

	public Species(int Population, int BodySize, int FoodConsumed, boolean Carnivore, boolean FatTissue, boolean Climbing, int Traits) {
		population = Population;
		bodySize = BodySize;
		currentFoodConsumed = FoodConsumed;
		isCarnivore = Carnivore;
		hasFatTissue = FatTissue;
		hasClimbing = Climbing;
		numofTraits = Traits;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public int getBodysize() {
		return bodySize;
	}
	
	public int getTraitcard() {
		return numofTraits;
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
