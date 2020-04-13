import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class GameRun extends Application {
	Stage window;
	Scene scene;
	Button btnSubmit1,btnSubmit2, btnPlay, btnFeed, btnContinue;
	
	// This will serve as "console log" in the player facing frontend.
	static Label pseudoConsoleLog = new Label("Player instructions");
	
	static Game currentGame = new Game();
	static WateringHole wateringHole = new WateringHole();
	static Label wateringHoleDisplay = new Label("Watering Hold Plant Food Available: " + Integer.toString(wateringHole.getCurrentFoodAvailable()));
	static Deck deck = new Deck();
	static Player playerOne = new Player(1);
	static Player playerTwo = new Player(2);
	static Hand handforPlayer1, handforPlayer2;
	static SpeciesBoard SpeciesBoard1, SpeciesBoard2;
	
	//Card card;
	
	HBox player1Cards = new HBox (20);
	HBox player2Cards = new HBox (20);
	
	//Species species;
	
	HBox player1Species = new HBox(20);
	HBox player2Species = new HBox(20);
	
	
	
	public void start(Stage primaryStage) throws Exception {
		handforPlayer1 = new Hand(player1Cards.getChildren());	
		handforPlayer2 = new Hand(player2Cards.getChildren());
		
		SpeciesBoard1 = new SpeciesBoard(player1Species.getChildren());
		SpeciesBoard2 = new SpeciesBoard(player2Species.getChildren());
		
		//System.out.println("player 1 species created");
		
		
		window = primaryStage;
		window.setTitle("Evolution Game");
	
		startNewGame();	
		
		
		//while(currentGame.gameIsNotFinished == false) {
		
			btnContinue = new Button ("Start new round");
			btnContinue.setOnAction(e -> {
				startNewRound();
			});
			
			
			//Phase 2 - select food for watering hole
			
			btnPlay = new Button ("Select plant food");
			btnPlay.setOnAction(e -> {
				TextInputDialog td_wh1 = new TextInputDialog();
				td_wh1.setHeaderText("Player 1: Which card would you like to select to add food to the watering hole?");
				td_wh1.setContentText("Card for Watering Hole");
				TextField userInput = td_wh1.getEditor();
				td_wh1.showAndWait();
				
				String input  = userInput.getText();
				int plantfoodNum1 = handforPlayer1.getValuefromCard(Integer.parseInt(input));
				wateringHole.updateCurrentFoodAvailable(plantfoodNum1);
				handforPlayer1.removeCardfromHand(Integer.parseInt(input));
				wateringHole.displayWH(wateringHoleDisplay);

				TextInputDialog td_wh2 = new TextInputDialog();
				td_wh2.setHeaderText("Player 2: Which card would you like to select to add food to the watering hole?");
				td_wh2.setContentText("Card for Watering Hole");	
				TextField userInput2 = td_wh2.getEditor();
				td_wh2.showAndWait();
				
				String input2  = userInput2.getText();
				int plantfoodNum2 = handforPlayer2.getValuefromCard(Integer.parseInt(input2));
				wateringHole.updateCurrentFoodAvailable(plantfoodNum2);
				handforPlayer2.removeCardfromHand(Integer.parseInt(input2));
				wateringHole.displayWH(wateringHoleDisplay);
			});
			
			System.out.println("Finish of plant food selection");
				
			// Phase 3 - Play Cards
			
			
			// Ready the players.
			
			// Player One plays Phase Three.
			btnSubmit1 = new Button ("Player 1: Start Phase 3");
			btnSubmit1.setOnAction(e -> {
				playerOne.readyForPhaseThree();
				currentGame.playPhaseThree(playerOne, handforPlayer1, SpeciesBoard1, pseudoConsoleLog);
			});
	
			// Player Two plays Phase Three.
			btnSubmit2 = new Button ("Player 2: Start Phase 3");
			btnSubmit2.setOnAction(e -> {
				playerTwo.readyForPhaseThree();
				currentGame.playPhaseThree(playerTwo, handforPlayer2, SpeciesBoard2, pseudoConsoleLog);
			});
			
			// Reveal Trait Cards
			
			System.out.println("Finish of phase 3");
			
			btnFeed = new Button ("Feed");
			btnFeed.setOnAction(e -> {
				feeding();
			});
			
			System.out.println("Finish of feeding");
			
			// Layout
			
			BorderPane border = new BorderPane();
			HBox P1Card = addHBox1();
			HBox P2Card = addHBox2();
			//HBox P1Species = addHBox3();
			//HBox P2Species = addHBox4();
			
			
			border.setTop(P1Card);
			border.setBottom(P2Card);
			//border.setLeft(P2Species);
			//border.setRight(P1Species);
			border.setCenter(addVBox());
			border.setRight(Buttons());
			
			scene = new Scene(border, 1000, 900);
			window.setScene(scene);
			window.show();
			
			
		}

		// End of game is not finished loop	

	//}
	public HBox addHBox1() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	    Text Player1CardInstruction = new Text ("Player 1 Cards:"); 
	    hbox.getChildren().addAll(Player1CardInstruction, player1Cards);

	    return hbox;
	}
	
	public HBox addHBox2() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	    Text Player2CardInstruction = new Text ("Player 2 Cards:"); 
	    hbox.getChildren().addAll(Player2CardInstruction, player2Cards);

	    return hbox;
	}
	
	public HBox addHBox3() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	    Text Player1SpeciesInstruction = new Text ("Player 1 Species:"); 
	    hbox.getChildren().addAll(Player1SpeciesInstruction, player1Species);

	    return hbox;
	}
	
	public HBox addHBox4() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	    Text Player2SpeciesInstruction = new Text ("Player 2 Species:"); 
	    hbox.getChildren().addAll(Player2SpeciesInstruction, player2Species);

	    return hbox;
	}
	
	public VBox Buttons() {
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(btnContinue, btnPlay, btnSubmit1, btnSubmit2, btnFeed, wateringHoleDisplay, pseudoConsoleLog);
		return layout;
	}
	public VBox addVBox() {
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		Text Player1SpeciesInstruction = new Text ("Player 1 Species:"); 
		Text Player2SpeciesInstruction = new Text ("Player 2 Species:"); 
		layout.getChildren().addAll(Player1SpeciesInstruction, player1Species,Player2SpeciesInstruction,player2Species);
		return layout;
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
	
	public static void startNewRound() {
		if(SpeciesBoard1.numberOfSpeciesInPlay() == 0) {
			SpeciesBoard1.addNewSpeciestoRight();
		}
		if(SpeciesBoard2.numberOfSpeciesInPlay() == 0) {
			SpeciesBoard2.addNewSpeciestoRight();
		}
		
		// Clear the player instructions.
		pseudoConsoleLog.setText("");
				
		// Draw cards (Starting hand is 4 cards each).
		// Players draw one card for each species they have on the board
		// plus three additional cards.
		System.out.print("Drawing cards for Player 1: ");
		handforPlayer1.drawCards(SpeciesBoard1.numberOfSpeciesInPlay() + 3, deck);
		handforPlayer1.displayHand();

		System.out.print("Drawing cards for Player 2: ");
		handforPlayer2.drawCards(SpeciesBoard2.numberOfSpeciesInPlay() + 3, deck);
		handforPlayer2.displayHand();
	}
	
	public static void feeding() {
		// Phase 4 - Feeding

		playerOne.isReadyToFeed();
		playerTwo.isReadyToFeed();

		// Long Neck and Fertile handled first

		// TODO: fix bug
//		wateringHole.displayWH(wateringHoleDisplay);

		// Regular feeding loop

		System.out.println("Feeding starts!");
		System.out.println("");

		while ((playerOne.getIsFeeding() + playerTwo.getIsFeeding()) != 0) {

			// Loop through players and allow them the opportunity to feed.
			// Alternate between players.
			for (int i = 1; i < 3; i++) {

				// Player One feeds species.
				currentGame.feedingPhase(i, playerOne, SpeciesBoard1, wateringHole, wateringHoleDisplay, pseudoConsoleLog);

				// Player Two feeds species.
				currentGame.feedingPhase(i, playerTwo, SpeciesBoard2, wateringHole, wateringHoleDisplay, pseudoConsoleLog);

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
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
}
