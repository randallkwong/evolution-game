
public class Species {

	// Indicates whether the species is currently living.
	int isAlive;
	
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

	public Species(int isAlive, int Population, int BodySize, int FoodConsumed, boolean Carnivore, boolean FatTissue, boolean Climbing, int Traits) {
		this.isAlive = isAlive;
		population = Population;
		bodySize = BodySize;
		currentFoodConsumed = FoodConsumed;
		isCarnivore = Carnivore;
		hasFatTissue = FatTissue;
		hasClimbing = Climbing;
		numberOfTraits = Traits;
		
		if(hasFatTissue == false) {
			foodCapacity = Population;
		}
		else {
			// TODO: Food stored by fat tissue must be subtracted.
			foodCapacity = Population + BodySize;
		}
		
	}
	
	
	public int getIsAlive() {
		return isAlive;
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

	public int getFoodCapacity() {
		return foodCapacity;
	}
	
	
	
}
