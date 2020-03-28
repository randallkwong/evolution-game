
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

	public Species() {
		this.population = 1;
		this.bodySize = 1;
		this.isCarnivore = false;
		this.currentFoodConsumed = 0;
		this.hasFatTissue = false;
		this.hasClimbing = false;
		this.numofTraits = 0;
	}
	
	
	
}
