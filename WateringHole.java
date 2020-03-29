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

	public void setCurrentFoodAvailable(int currentFoodAvailable) {
		this.currentFoodAvailable = currentFoodAvailable;
	}
	
	public void updateCurrentFoodAvailable(int newFood) {
		currentFoodAvailable = currentFoodAvailable + newFood;
	}
	
	public void displayWH() {
		System.out.println("Current Watering Hole has " + currentFoodAvailable + " plant food");
	}
}
