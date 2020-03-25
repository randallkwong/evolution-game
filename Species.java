
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

	public Species() {
		this.population = 1;
		this.bodySize = 1;
		this.isCarnivore = false;	
	}
	
	
}
