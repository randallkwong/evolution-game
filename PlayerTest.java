import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

class PlayerTest {
	ObservableList<Node> newPlayerBoard = FXCollections.observableArrayList();
	Species newSpecies = new Species(1, 1, 1, 0, false, false, false, 0, new ArrayList<String>(), "1");


	@Test
	
	// test to set and add Player's food points
	void testPlayerFoodPoints() {
		Player PlayerOne = new Player(1);
		
		PlayerOne.setFoodPoints(3);
		PlayerOne.addFoodPoints(4);
		
		assertEquals(PlayerOne.foodPoints,7);
		
	}
	
	@Test
	// test calculate score function
	void testPlayerScore() {
		Player PlayerOne = new Player(1);
		
		PlayerOne.setFoodPoints(3);
		
		SpeciesBoard SpeciesBoard1 = new SpeciesBoard(newPlayerBoard);
		
		SpeciesBoard1.addNewSpeciestoRight();
		
		PlayerOne.calculateScore(SpeciesBoard1);
		
		assertEquals(PlayerOne.getScore(),5);

	}
}
