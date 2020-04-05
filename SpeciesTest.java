import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class SpeciesTest {

	@Test
	/*
	 * Tests that food capacity is increased when Fat Tissue trait is attached.
	 */
	void testFatTissueFoodCapacity() {

		ArrayList<String>testTraits = new ArrayList<String>();
		
		testTraits.add("Fat Tissue");
		
		Species testSpecies = new Species(1, 1, 1, 0, false, true, false, 1, testTraits);
		
		assertEquals(testSpecies.getFoodCapacity(), 2);
		
	}

}
