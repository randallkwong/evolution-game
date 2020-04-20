import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WateringHoleTest {
	WateringHole wh = new WateringHole();
	
	@Test
	void testdecrementFoodAvailable() {
		wh.setCurrentFoodAvailable(3);
		wh.decrementFoodAvailable(1);
		
		assertEquals (wh.currentFoodAvailable, 2);
	}
	
	@Test
	void testupdateFoodAavailable() {
		wh.setCurrentFoodAvailable(4);
		wh.updateCurrentFoodAvailable(2);
		
		assertEquals(wh.currentFoodAvailable,6);
		
		wh.setCurrentFoodAvailable(1);
		wh.updateCurrentFoodAvailable(-2);
		
		assertEquals(wh.currentFoodAvailable,0);
	}
	
	@Test
	void testsetFoodAavailable() {
		wh.setCurrentFoodAvailable(4);		
		assertEquals(wh.currentFoodAvailable,4);
	}

}
