import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

class GameTest {

	ObservableList<Node> newPlayerBoard = FXCollections.observableArrayList();
	Species newSpecies = new Species(1, 1, 1, 0, false, false, false, 0, new ArrayList<String>(), "1");

	
	
	@Test
	/*
	 * Tests that a species that isn't fed will starve and be removed from the species board.
	 */
	void testStarvationAndSpeciesDeath() {
		
		// Species species;

		Game currentGame = new Game();

		// Create player
		Player playerOne = new Player(1);
		
		// Create species board

		SpeciesBoard SpeciesBoard1 = new SpeciesBoard(newPlayerBoard);
		
		// Add one additional species to the board
		SpeciesBoard1.addNewSpeciestoRight();
		
		// Two species are in play
		assertEquals(SpeciesBoard1.numberOfSpeciesInPlay(), 2);
		
		// Feed one of the species
		SpeciesBoard1.updateFoodConsumed(1);
		
		// Handle starvation
		currentGame.starveSpecies(playerOne, SpeciesBoard1);
		
		// One species should starve and die off, leaving one species left
		assertEquals(SpeciesBoard1.numberOfSpeciesInPlay(), 1);
	}
	
}
