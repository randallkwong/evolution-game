import java.util.Scanner;

public class WateringHole {

	// The current number of food tokens in the watering hole.
	int currentFoodAvailable;
	
	public WateringHole() {
		this.currentFoodAvailable = 0;
	}

	public int getCurrentFoodAvailable() {
		return currentFoodAvailable;
	}

	public void decrementFoodAvailable(int foodConsumed) {
		this.currentFoodAvailable = this.currentFoodAvailable - foodConsumed;
	}
	
	public void setCurrentFoodAvailable(int currentFoodAvailable) {
		this.currentFoodAvailable = currentFoodAvailable;
	}
	
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
	
	public void displayWH() {
		System.out.println("Current Watering Hole has " + currentFoodAvailable + " plant food");
		System.out.println();
	}
}
