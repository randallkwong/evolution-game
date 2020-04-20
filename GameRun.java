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
	Button btnSubmit1,btnSubmit2, btnPlay, btnFeed, btnContinue, btnScoreGame;
	
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
	
	
	/**
	 *  The "start" function is the runner method that opens the application window and sets up the user interface,
	 *  starting game conditions, and the buttons that initiate each portion of this event driven game.
	 */
	public void start(Stage primaryStage) throws Exception {

		// Sets up parts of the user interface for the game board and loads the starting conditions.
		startNewGame(player1Cards, player2Cards, player1Species, player2Species);
		
		// Sets the stage.
		window = primaryStage;
		window.setTitle("Evolution Game");
		
		// Creates the play buttons. The play buttons trigger events that initiate each phase of the game.
		createPlayButtons();
		
		// Sets up the layout for the game application.
		BorderPane border = new BorderPane();
		loadLayout(border);

		// Sets up the scene and applies it to the application window.
		scene = new Scene(border, 1000, 900);
		window.setScene(scene);
		window.show();
			
		}

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
		layout.getChildren().addAll(btnContinue, btnPlay, btnSubmit1, btnSubmit2, btnFeed, wateringHoleDisplay, pseudoConsoleLog, btnScoreGame);
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
	
	
	public static void startNewGame(HBox player1Cards, HBox player2Cards, HBox player1Species, HBox player2Species){	
		
		// Sets up the user interface for the players
		handforPlayer1 = new Hand(player1Cards.getChildren());	
		handforPlayer2 = new Hand(player2Cards.getChildren());
		
		SpeciesBoard1 = new SpeciesBoard(player1Species.getChildren());
		SpeciesBoard2 = new SpeciesBoard(player2Species.getChildren());
		
		// Updates the indicies of the Species.
		SpeciesBoard1.displaySpeciesBoard();
		SpeciesBoard2.displaySpeciesBoard();
		
		// Create and shuffle deck
		deck.loadDeck();
		
	}
	
	public static void startNewRound() {
		if(SpeciesBoard1.numberOfSpeciesInPlay() == 0) {
			SpeciesBoard1.addNewSpeciestoRight();
		}
		if(SpeciesBoard2.numberOfSpeciesInPlay() == 0) {
			SpeciesBoard2.addNewSpeciestoRight();
		}
		
		// Updates the Species indicies.
		SpeciesBoard1.displaySpeciesBoard();
		SpeciesBoard2.displaySpeciesBoard();
		
		// Clear the player instructions.
		pseudoConsoleLog.setText("");
				
		// Draw cards (Starting hand is 4 cards each).
		// Players draw one card for each species they have on the board
		// plus three additional cards.
		System.out.print("Drawing cards for Player 1: ");
		handforPlayer1.drawCards(SpeciesBoard1.numberOfSpeciesInPlay() + 3, deck);
		handforPlayer1.updateHand();

		System.out.print("Drawing cards for Player 2: ");
		handforPlayer2.drawCards(SpeciesBoard2.numberOfSpeciesInPlay() + 3, deck);
		handforPlayer2.updateHand();
	}
	
	public static void feeding() {
		// Phase 4 - Feeding

		playerOne.isReadyToFeed();
		playerTwo.isReadyToFeed();

		// Long Neck and Fertile handled first

		// Regular feeding loop

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

		// Update the indicies.
		SpeciesBoard1.displaySpeciesBoard();
		SpeciesBoard2.displaySpeciesBoard();
		
		System.out.println("The loop will restart to selecting cards for the watering hole now");
		pseudoConsoleLog.setText("Feeding is complete. Start the next round!");
	}
	
	public void createPlayButtons() {
		
		// Creates a button to start a new round each turn.
		btnContinue = new Button ("Start new round");
		btnContinue.setOnAction(e -> {
			startNewRound();
			pseudoConsoleLog.setText("\n" + "The cards have been dealt" + "\n" + "\n" + "Continue to \"Select plant food\"");
		});
		
		
		//Phase 2 - select food for watering hole
		// Creates a button so players can select a food card to contribute to the watering hole.
		btnPlay = new Button ("Select plant food");
		btnPlay.setOnAction(e -> {
			
			currentGame.playWateringHoleCard(playerOne, handforPlayer1, wateringHole, wateringHoleDisplay);
			currentGame.playWateringHoleCard(playerTwo, handforPlayer2, wateringHole, wateringHoleDisplay);
			pseudoConsoleLog.setText("\n" + "The food cards have been selected" + "\n" + "\n" + "Continue to \"Phase Three\"");
		});
			
		// Phase 3 - Play Cards
		
		// Player One plays Phase Three.
		// Creates a button so Player 1 can initiate Phase Three.
		btnSubmit1 = new Button ("Player 1: Start Phase 3");
		btnSubmit1.setOnAction(e -> {
			playerOne.readyForPhaseThree();
			currentGame.playPhaseThree(playerOne, handforPlayer1, SpeciesBoard1, pseudoConsoleLog);
		});

		// Player Two plays Phase Three.
		// Creates a button so Player 2 can initiate Phase Three.
		btnSubmit2 = new Button ("Player 2: Start Phase 3");
		btnSubmit2.setOnAction(e -> {
			playerTwo.readyForPhaseThree();
			currentGame.playPhaseThree(playerTwo, handforPlayer2, SpeciesBoard2, pseudoConsoleLog);
		});
		
		// TODO: Game improvements - Reveal Trait Cards
		
		// Creates button to initiate feeding phase.
		btnFeed = new Button ("Feed");
		btnFeed.setOnAction(e -> {
			feeding();
		});
		
		// Create button to end the game.
		btnScoreGame = new Button ("End Game");
		btnScoreGame.setOnAction(e -> {

			// Scores the game and displays the final score.
			currentGame.scoreGame(playerOne, SpeciesBoard1, playerTwo, SpeciesBoard2);

		});
		
	}
	
	// Loads the layout for the game.
	public void loadLayout(BorderPane border) {

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
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
}
