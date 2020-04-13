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
public class Species extends Parent{
	
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
	
	ArrayList<String> attachedTraitCards;
	
	// The current number of trait cards on this species. A species can not have more than 3 trait cards;
	int numberOfTraits;

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
	 */
	public Species(int isAlive, int Population, int BodySize, int FoodConsumed, boolean Carnivore, boolean FatTissue, boolean Climbing, int Traits, ArrayList<String> AttachedTraitCards) {
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
		
		hasFatTissue = false;
		for(int i = 0; i < AttachedTraitCards.size(); i++) {
			if(AttachedTraitCards.get(i).equals("Fat Tissue")){
				hasFatTissue = true;
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
		
		//Added blank trait names so that it can be displayed. If blank name is not added, then GUI display will crash.
		
		AttachedTraitCards.add("");
		AttachedTraitCards.add("");
		AttachedTraitCards.add("");
		
		Text Trait1ofCard = new Text(AttachedTraitCards.get(0));
		Trait1ofCard.setFont(Font.font(18));
		Trait1ofCard.setX(Species_width - Trait1ofCard.getLayoutBounds().getWidth() - 10);
		Trait1ofCard.setY(Trait1ofCard.getLayoutBounds().getHeight());
		
		Text Trait2ofCard = new Text(AttachedTraitCards.get(1));
		Trait2ofCard.setFont(Font.font(18));
		Trait2ofCard.setX(Species_width - Trait2ofCard.getLayoutBounds().getWidth() - 10);
		Trait2ofCard.setY(50);
		
		Text Trait3ofCard = new Text(AttachedTraitCards.get(2));
		Trait3ofCard.setFont(Font.font(18));
		Trait3ofCard.setX(Species_width - Trait2ofCard.getLayoutBounds().getWidth() - 10);
		Trait3ofCard.setY(100);

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
		bodySizeofSpecies.setY(Species_height-10);
		
		getChildren().addAll(background, Trait1ofCard, Trait2ofCard, Trait3ofCard, bodySizeofSpecies,foodofSpecies,populationofSpecies);		
		
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
	
}
