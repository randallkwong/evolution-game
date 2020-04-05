import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTest {

	@Test
	/*
	 * Tests that a species that isn't fed will starve and be removed from the species board.
	 */
	void testStarvationAndSpeciesDeath() {

		Game currentGame = new Game();
		
		// Create player
		Player playerOne = new Player(1);
		
		SpeciesBoard SpeciesBoard1 = new SpeciesBoard();
		SpeciesBoard1.addNewSpeciestoLeft();
		
		currentGame.starveSpecies(playerOne, SpeciesBoard1);
		
		assertEquals(SpeciesBoard1.numberOfSpeciesInPlay(), 0);
		
	}

}
