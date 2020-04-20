import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

class SpeciesBoardTest {

	ObservableList<Node> newPlayerBoard = FXCollections.observableArrayList();
	Species newSpecies = new Species(1, 1, 1, 0, false, false, false, 0, new ArrayList<String>(), "1");
	Species value;

	@Test
	/*
	 * Tests update body size function
	 */
	void testUpdatedBodySize() {
		
		// Create species board

		SpeciesBoard SpeciesBoard1 = new SpeciesBoard(newPlayerBoard);
		
		SpeciesBoard1.addNewSpeciestoRight();
		
		SpeciesBoard1.updateBodySize(1);
		
		value = (Species) newPlayerBoard.get(1);

		assertEquals(value.getBodysize(),2);
	}
	@Test
	/*
	 * Tests update species population
	 */
	
	void testUpdatedPopulation() {
		
		// Create species board

		SpeciesBoard SpeciesBoard1 = new SpeciesBoard(newPlayerBoard);
		
		SpeciesBoard1.addNewSpeciestoRight();
		
		SpeciesBoard1.updatePopulation(1, 2);
		
		value = (Species) newPlayerBoard.get(1);

		assertEquals(value.getPopulation(),3);
		
	}
}
