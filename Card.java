import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * This class defines a card by its food value and trait.
 *
 */
public class Card extends Parent{
	
	int Card_width = 100;
	int Card_height = 140;

	// The amount of food this trait card is worth when played into the watering hole.
	int foodValue;
	String trait;
	
	/**
	 * This constructor populates starting values for a card by its provided food value and trait.
	 * 
	 * @param foodValueInput - The food value of a trait card used to populate the watering hole.
	 * @param traitInput - The trait that may be attached to a player's species.
	 */
	public Card(int foodValueInput, String traitInput) {
		
		this.foodValue = foodValueInput;
		this.trait = traitInput;
		
		Rectangle background = new Rectangle (Card_width, Card_height);
		background.setArcWidth(20);
		background.setArcHeight(20);
		background.setFill(Color.WHITE);
		
		Text nameofCard = new Text(trait);
		nameofCard.setFont(Font.font(14));
		nameofCard.setX(Card_width - nameofCard.getLayoutBounds().getWidth() - 10);
		nameofCard.setY(nameofCard.getLayoutBounds().getHeight());

		Text valueofCard = new Text(String.valueOf(foodValue));
		valueofCard.setFont(Font.font(14));
		valueofCard.setX(10);
		valueofCard.setY(Card_height-10);
		
		Text indexOfCardInHand = new Text("[]");
		indexOfCardInHand.setFont(Font.font(14));
		indexOfCardInHand.setX(Card_width - 10);
		indexOfCardInHand.setY(Card_height - 10);
		
		getChildren().addAll(background, nameofCard, valueofCard, indexOfCardInHand);		
	}

	/*
	 *  This is an overloaded constructor that takes an additional
	 *  argument for card index.
	 */
	public Card(int foodValueInput, String traitInput, String index) {
		
		this.foodValue = foodValueInput;
		this.trait = traitInput;
		
		Rectangle background = new Rectangle (Card_width, Card_height);
		background.setArcWidth(20);
		background.setArcHeight(20);
		background.setFill(Color.WHITE);
		
		Text nameofCard = new Text(trait);
		nameofCard.setFont(Font.font(14));
		nameofCard.setX(Card_width - nameofCard.getLayoutBounds().getWidth() - 10);
		nameofCard.setY(nameofCard.getLayoutBounds().getHeight());

		Text valueofCard = new Text(String.valueOf(foodValue));
		valueofCard.setFont(Font.font(14));
		valueofCard.setX(10);
		valueofCard.setY(Card_height-10);
		
		Text indexOfCardInHand = new Text("[" + index + "]");
		indexOfCardInHand.setFont(Font.font(14));
		indexOfCardInHand.setX(Card_width - 25);
		indexOfCardInHand.setY(Card_height - 10);
		
		getChildren().addAll(background, nameofCard, valueofCard, indexOfCardInHand);		
	}
	
	/**
	 * 
	 * @return - Returns the food value of a card as an integer.
	 */
	public int getCardValue() {
		return foodValue;
	}

	/**
	 * 
	 * @return - Returns the string value of a card's trait.
	 */
	public String getTrait() {
		return trait;
	}
}
