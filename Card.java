
public class Card {

	// The amount of food this trait card is worth when played into the watering hole.
	int foodValue;
	String trait;
	
	public Card(int foodValueInput, String traitInput) {
		
		this.foodValue = foodValueInput;
		this.trait = traitInput;
				
	}

	public int getCardValue() {
		return foodValue;
	}

	public String getTrait() {
		return trait;
	}
	
}
