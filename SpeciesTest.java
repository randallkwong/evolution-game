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
		
		Species testSpeciesHasFatTissue = new Species(1, 1, 1, 0, false, true, false, 1, testTraits,"1");
		
		testTraits.remove(0);
		
		Species testSpeciesDoesNotHaveFatTissue = new Species(1, 1, 1, 0, false, false, false, 1, testTraits,"1");
		
		assertEquals(testSpeciesHasFatTissue.getFoodCapacity(), 2);
		assertEquals(testSpeciesDoesNotHaveFatTissue.getFoodCapacity(), 1);
		
	}
	
	
	@Test
	/*
	 * Tests the ability to add traits and return number of trait cards.
	 */
	void testgetTraitsCard() {
		ArrayList<String>testTraits = new ArrayList<String>();
		
		testTraits.add("Carnivore");
				
		Species testSpecies = new Species(1, 1, 1, 0, false, false, false, 1, testTraits,"1");
		assertEquals(testSpecies.getTraitcard(),1);	
	}
	
	@Test
	/*
	 * Tests the ability to add Carnivore traits and return the boolean for isCarnivore
	 */
	void testaddTraits() {
		ArrayList<String>testTraits = new ArrayList<String>();
		testTraits.add("Carnivore");
		
		Species testSpecies = new Species(1, 1, 1, 0, true, false, false, 1, testTraits,"1");
		assertEquals(testSpecies.isCarnivore,true);		
	}
	

}
