import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class GameRun extends Application {
	Stage window;
	Scene scene;
	Button btnSubmit1,btnSubmit2, btnPlay, btnFeed;
	static Game currentGame = new Game();
	static WateringHole wateringHole = new WateringHole();
	static Deck deck = new Deck();
	static Player playerOne = new Player(1);
	static Player playerTwo = new Player(2);
	static Hand handforPlayer1 = new Hand();
	static Hand handforPlayer2 = new Hand();
	static SpeciesBoard SpeciesBoard1 = new SpeciesBoard();
	static SpeciesBoard SpeciesBoard2 = new SpeciesBoard();
	Card card;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Evolution Game");
	
		startNewGame();
		
		while(currentGame.gameIsNotFinished == false) {
			if(SpeciesBoard1.numberOfSpeciesInPlay() == 0) {
				SpeciesBoard1.addNewSpeciestoRight();
			}
			if(SpeciesBoard2.numberOfSpeciesInPlay() == 0) {
				SpeciesBoard2.addNewSpeciestoRight();
			}
			// Draw cards (Starting hand is 4 cards each).
			// Players draw one card for each species they have on the board
			// plus three additional cards.
			System.out.print("Drawing cards for Player 1: ");
			handforPlayer1.drawCards(SpeciesBoard1.numberOfSpeciesInPlay() + 3, deck);
			handforPlayer1.displayHand();

			System.out.print("Drawing cards for Player 2: ");
			handforPlayer2.drawCards(SpeciesBoard2.numberOfSpeciesInPlay() + 3, deck);
			handforPlayer2.displayHand();
			
			//Phase 2 - select food for watering hole
			
			btnPlay = new Button ("Select plant food");
			btnPlay.setOnAction(e -> {
				TextInputDialog td_wh1 = new TextInputDialog("Player 1: Which watering hole would you like to select?");
				td_wh1.setContentText("Card for Watering Hole");
				TextField userInput = td_wh1.getEditor();
				
				String input  = userInput.getText();
				int plantfoodNum1 = handforPlayer1.getValuefromCard(Integer.parseInt(input));
				wateringHole.updateCurrentFoodAvailable(plantfoodNum1);
				handforPlayer1.removeCardfromHand(Integer.parseInt(input));

				TextInputDialog td_wh2 = new TextInputDialog("Player 1: Which watering hole would you like to select?");
				td_wh2.setContentText("Card for Watering Hole");
				TextField userInput2 = td_wh2.getEditor();
				
				String input2  = userInput2.getText();
				int plantfoodNum2 = handforPlayer2.getValuefromCard(Integer.parseInt(input2));
				wateringHole.updateCurrentFoodAvailable(plantfoodNum2);
				handforPlayer2.removeCardfromHand(Integer.parseInt(input2));	
			});
				
			// Phase 3 - Play Cards
			
			
			// Ready the players.
			
			// Player One plays Phase Three.
			btnSubmit1 = new Button ("Player 1: Phase 3 starts");
			btnSubmit1.setOnAction(e -> {
				playerOne.readyForPhaseThree();
				currentGame.playPhaseThree(playerOne, handforPlayer1, SpeciesBoard1);
			});
	
			// Player Two plays Phase Three.
			btnSubmit2 = new Button ("Player 2: Phase 3 starts");
			btnSubmit2.setOnAction(e -> {
				playerTwo.readyForPhaseThree();
				currentGame.playPhaseThree(playerTwo, handforPlayer2, SpeciesBoard2);
			});
			
			// Reveal Trait Cards
			
			btnFeed = new Button ("Feed");
			btnFeed.setOnAction(e -> {
				feeding();
			});
			
			// Layout
			VBox layout = new VBox(10);
			layout.setPadding(new Insets(20, 20, 20, 20));

			//layout.getChildren().addAll(btnPlay, btnSubmit1, btnSubmit2, btnFeed);

			scene = new Scene(layout, 300, 250);
			window.setScene(scene);
			window.show();
		}

		// End of game is not finished loop	

	}
	
	public static void startNewGame(){	
		// Create and shuffle deck
		
		deck.loadDeck();
		
		// Create starting game species board
		
		System.out.print("Player 1 Board: ");
		SpeciesBoard1.displaySpeciesBoard();

		
		System.out.print("Player 2 Board: ");
		SpeciesBoard2.displaySpeciesBoard();
		
	}
	
	public static void feeding() {
		// Phase 4 - Feeding

		playerOne.isReadyToFeed();
		playerTwo.isReadyToFeed();

		// Long Neck and Fertile handled first

		wateringHole.displayWH();

		// Regular feeding loop

		System.out.println("Feeding starts!");
		System.out.println("");

		while ((playerOne.getIsFeeding() + playerTwo.getIsFeeding()) != 0) {

			// Loop through players and allow them the opportunity to feed.
			// Alternate between players.
			for (int i = 1; i < 3; i++) {

				// Player One feeds species.
				currentGame.feedingPhase(i, playerOne, SpeciesBoard1, wateringHole);

				// Player Two feeds species.
				currentGame.feedingPhase(i, playerTwo, SpeciesBoard2, wateringHole);

			}

		}

		System.out.println("End of feeding loop");

		// Handles starving species that lose population or die.
		currentGame.starveSpecies(playerOne, SpeciesBoard1);
		currentGame.starveSpecies(playerTwo, SpeciesBoard2);

		// Move food tokens to food bag.
		currentGame.moveConsumedFoodToFoodBag(playerOne, SpeciesBoard1);
		currentGame.moveConsumedFoodToFoodBag(playerTwo, SpeciesBoard2);

		// Remove species that died.

		System.out.println("The loop will restart to selecting cards for the watering hole now");
	}
	
	
}
