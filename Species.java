import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

/**
 * 
 * This class defines a Species of the Evolution game. Species have various characteristics
 * such as their population and body size and may receive traits to evolve new abilities during game play.
 *
 */
public class Species extends Parent implements Comparable{
	
	//initialize size of the Species Card
	
	int Species_width = 140;
	int Species_height = 140;

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
	int numberOfForagingCardsAttached;
	
	ArrayList<String> attachedTraitCards;
	
	// The current number of trait cards on this species. A species can not have more than 3 trait cards;
	int numberOfTraits;

	String index;

	
	/**
	 * Constructs a species
	 * 
	 * @param isAlive
	 * Takes a value to indicate with a 1 or a 0 whether a species is living or rather has starved or been eaten to zero population.
	 * 
	 * @param Population
	 * Takes the population and applies it to the species.
	 * 
	 * @param BodySize
	 * Takes the body size and applies it to the species.
	 * 
	 * @param FoodConsumed
	 * Takes the food consumed and applies it to the species.
	 * 
	 * @param Carnivore
	 * Takes the status of carnivore and applies it to the species.
	 * 
	 * @param FatTissue
	 * Takes the fat tissue status and applies it to the species.
	 * 
	 * @param Climbing
	 * Takes the climbing status and applies it to the species.
	 * 
	 * @param Traits
	 * Takes the number of traits and appiles it to the species.
	 * 
	 * @param AttachedTraitCards
	 * Takes the attached traits arraylist and applies it to the species.
	 * 
	 * @param Index
	 * Takes the index to display for a species which allows us to differentiate it from the other species on the board.
	 */
	
	public Species(int isAlive, int Population, int BodySize, int FoodConsumed, boolean Carnivore, boolean FatTissue, boolean Climbing, int Traits, ArrayList<String> AttachedTraitCards, String Index) {
		index = Index;
		this.isAlive = isAlive;
		population = Population;
		bodySize = BodySize;
		currentFoodConsumed = FoodConsumed;
		isCarnivore = Carnivore;
		// TODO: get rid of Fat Tissue variable in Species constructor and Species methods.
		hasFatTissue = FatTissue;
		hasClimbing = Climbing;
		numberOfTraits = Traits;
		attachedTraitCards = AttachedTraitCards;
		numberOfForagingCardsAttached = 0;
		
		hasFatTissue = false;
		for(int i = 0; i < AttachedTraitCards.size(); i++) {
			if(AttachedTraitCards.get(i).equals("Fat Tissue")){
				hasFatTissue = true;
			}
			
			// Count the number of Foraging cards attached
			if(AttachedTraitCards.get(i).equals("Foraging")){
				numberOfForagingCardsAttached = numberOfForagingCardsAttached + 1;
			}
			
		}
		
		if(hasFatTissue == false) {
			foodCapacity = Population;
		}
		else {
			// TODO: Food stored by fat tissue must be subtracted.
			foodCapacity = Population + BodySize;
		}
		
		//Add GUI for Species Card to display
		Rectangle background = new Rectangle (Species_width, Species_height);
		background.setArcWidth(20);
		background.setArcHeight(20);
		background.setFill(Color.WHITE);
		
		//Added blank trait names so that it can be displayed. If blank name is not added, then GUI display will crash
		
		for(int j = 0; j < 3; j++) {
			if(AttachedTraitCards.size() < 3) {
				AttachedTraitCards.add("Empty");
			}
		}
		
		Text Trait1ofCard = new Text("T1:" + AttachedTraitCards.get(0));
		Trait1ofCard.setFont(Font.font(18));
		Trait1ofCard.setX(10);
		Trait1ofCard.setY(Trait1ofCard.getLayoutBounds().getHeight());
		
		Text Trait2ofCard = new Text("T2:" + AttachedTraitCards.get(1));
		Trait2ofCard.setFont(Font.font(18));
		Trait2ofCard.setX(10);
		Trait2ofCard.setY(45);
		
		Text Trait3ofCard = new Text("T3:" + AttachedTraitCards.get(2));
		Trait3ofCard.setFont(Font.font(18));
		Trait3ofCard.setX(10);
		Trait3ofCard.setY(70);

		Text indexOfSpecies = new Text("[" + index + "]");
		indexOfSpecies.setFont(Font.font(18));
		indexOfSpecies.setX(10);
		indexOfSpecies.setY(Species_height-40);
		
		Text bodySizeofSpecies = new Text("B:" + String.valueOf(bodySize));
		bodySizeofSpecies.setFont(Font.font(18));
		bodySizeofSpecies.setX(10);
		bodySizeofSpecies.setY(Species_height-10);
		
		Text foodofSpecies = new Text("F:" + String.valueOf(currentFoodConsumed));
		foodofSpecies.setFont(Font.font(18));
		foodofSpecies.setX(50);
		foodofSpecies.setY(Species_height-10);
		
		Text populationofSpecies = new Text("P:" + String.valueOf(population));
		populationofSpecies.setFont(Font.font(18));
		populationofSpecies.setX(90);
		populationofSpecies.setY(Species_height-10);
		
		getChildren().addAll(background, Trait1ofCard, Trait2ofCard, Trait3ofCard, indexOfSpecies, bodySizeofSpecies,foodofSpecies,populationofSpecies);		
		
	}
	
	
	/**
	 * 
	 * @return
	 * Returns an integer 1 or 0 indicating whether or not a species is alive.
	 */
	public int getIsAlive() {
		return isAlive;
	}


	/**
	 * 
	 * @return
	 * Returns an integer indicating the population size of a species.
	 */
	public int getPopulation() {
		return population;
	}
	
	/**
	 * 
	 * @return
	 * Returns an integer indicating the body size of a species.
	 */
	public int getBodysize() {
		return bodySize;
	}
	

	public int getTraitcard() {
		return numberOfTraits;
	}

	/**
	 * 
	 * @return
	 * Returns an integer indicating how much food a species has currently consumed.
	 */
	public int getFoodConsumed() {
		return currentFoodConsumed;
	}

	/**
	 * 
	 * @return
	 * Returns a boolean indicating whether a species has the Fat Tissue trait attached.
	 */
	public boolean getFatTissue() {
		return hasFatTissue;
	}
	
	/**
	 * 
	 * @return
	 * Returns a boolean indicating whether a species has the Carnivore trait attached.
	 */
	public boolean getCarnivore() {
		return isCarnivore;
	}

	/**
	 * 
	 * @return
	 * Returns a boolean indicating whether a species has the Climbing trait attached.
	 */
	public boolean getClimbing() {
		return hasClimbing;
	}

	/**
	 * 
	 * @return
	 * Returns an integer indicating how much food a species can consume.
	 */
	public int getFoodCapacity() {
		return foodCapacity;
	}
	
	/**
	 * 
	 * @return
	 * Returns an arraylist of attached trait cards for a species.
	 */
	public ArrayList<String> getAttachedTraitCards() {
		return attachedTraitCards;
	}

	/**
	 * 
	 * @param attachedTraitCards
	 * Takes an ArrayList of attached trait cards and applies it to a species.
	 */
	public void setAttachedTraitCards(ArrayList<String> attachedTraitCards) {
		this.attachedTraitCards = attachedTraitCards;
	}

	/**
	 * 
	 * @return
	 * Returns the indentifying index of the Species.
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * 
	 * @param index
	 * Sets the identifying index of the Species.
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * 
	 * @return
	 * Returns the number of Foraging trait cards attached to the species.
	 */
	public int getNumberOfForagingCardsAttached() {
		return numberOfForagingCardsAttached;
	}


	@Override
	/**
	 *  We need to create a way to compare species so that we can sort the observable list. This avoids
	 *  the error we were having with duplicate children when species were added to the species board.
	 */
	public int compareTo(Object otherSpecies) {
		Species myOtherSpecies = (Species) otherSpecies;
		
		int myOtherSpeciesIndex = Integer.parseInt(myOtherSpecies.getIndex());
		int thisSpeciesIndex = Integer.parseInt(index);
		
		if(myOtherSpeciesIndex == thisSpeciesIndex) return 0;
		if((thisSpeciesIndex - myOtherSpeciesIndex) < 1) {
			return -1;
		}
		// Otherwise return 1 if index is greater than myOtherSpecies.index
		return 1;
	}
	
}
