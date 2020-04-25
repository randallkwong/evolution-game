import java.util.Scanner;

import javafx.scene.control.Label;

/**
 * This class keeps track of and alters the amount of plant food in the WateringHole.
 * 
 *
 */
public class WateringHole {

	// The current number of food tokens in the watering hole.
	int currentFoodAvailable;
	
	/**
	 * Constructs the WateringHole and initializes it with no food to begin with.
	 */
	public WateringHole() {
		this.currentFoodAvailable = 0;
	}

	/**
	 * 
	 * @return
	 * Returns an integer indicating the current number of plant food in the WateringHole.
	 */
	public int getCurrentFoodAvailable() {
		return currentFoodAvailable;
	}

	/**
	 * This method decreases the amount of plant food available.
	 * 
	 * @param foodConsumed
	 * Takes the amount of food that was consumed that will be subtracted from the available food.
	 */
	public void decrementFoodAvailable(int foodConsumed) {
		this.currentFoodAvailable = this.currentFoodAvailable - foodConsumed;
	}
	
	/**
	 * 
	 * @param currentFoodAvailable
	 * Takes a food value integer and sets the current available plant food to that number.
	 */
	public void setCurrentFoodAvailable(int currentFoodAvailable) {
		this.currentFoodAvailable = currentFoodAvailable;
	}

	/**
	 * This method updates the available food number by a given integer.
	 * 
	 * @param newFood
	 * Takes an integer value to alter the available food by.
	 */
	public void updateCurrentFoodAvailable(int newFood) {
		
		// Some food values provided may be negative, so we need to check
		// if the watering hole would be empty before updating the value.
		int newTotal = currentFoodAvailable + newFood;
		
		if(newTotal >= 0) {
		currentFoodAvailable = currentFoodAvailable + newFood;
		}
		else {
			currentFoodAvailable = 0;
		}
	}
	
	/**
	 * This method prints the current available plant food in the WateringHole.
	 */
	public void displayWH(Label wateringHoleDisplay) {
		String wateringHoleFoodAvailable = "Watering Hole \nPlant Food Available: " + Integer.toString(getCurrentFoodAvailable());
		wateringHoleDisplay.setText(wateringHoleFoodAvailable);
	}
}
