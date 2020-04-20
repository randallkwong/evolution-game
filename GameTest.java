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
		
		// Board creation creates one species
		SpeciesBoard SpeciesBoard1 = new SpeciesBoard(null);
		
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

		// Move food consumed off of the species that was fed
		SpeciesBoard1.moveFoodConsumed(1, 1);
		
		// Feed no species this time and handle starvation again. No species should be remaining
		currentGame.starveSpecies(playerOne, SpeciesBoard1);
		assertEquals(SpeciesBoard1.numberOfSpeciesInPlay(), 0);
		
	}

}
