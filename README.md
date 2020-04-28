Hello, welcome to MCIT Evolution. This project is a simplified version of the Evolution board game implemented in Java.

**Introduction & Setup:** 

The object of the game is to score the most points to win. Players can earn points by 1) spawning species and using them to consume food and 2) by growing their species’ population, which will be tallied at the end of the game. During gameplay, players may use cards they are dealt to generate new species or to evolve their existing species so they can gain various advantages. Players take turns feeding their species until the end of a round at which point the species that did not feed starve off and shrink in population or go extinct. For the purposes of this demo, you may end the game and tally up the final score after four rounds to declare a winner.

Please follow the instructions below to play and score the game. This is a two player game and we would ask you to pretend to be two different players at the same time. As noted, rules differ slightly from the real game.

To get started, first download our github project repository and install JavaFx dependencies for Eclipse.
http://www.eclipse.org/efxclipse/install.html

If you have trouble getting JavaFx working, please visit this thread https://stackoverflow.com/questions/27913114/cannot-get-javafx-to-work-in-eclipse

**How does the game work:** 

*Note: you can also watch 5 min video that explains our game: https://youtu.be/uDEX1TvRihQ*

Step 1: run the program. Once you hit run for “GameRun.java”, a GUI window should pop up. 

Step 2: click “Start new round”. After clicking, you should expect to see new cards dealt for this round. These are the cards in the Players’ hands. Each Player starts the game with four cards.

Step 3: click “Select plant food”. Each Player selects one card from their hand which will add food to the Watering Hole. The card’s food value is displayed in each card’s lower left hand corner. The card’s (index), which you’ll use to enter your selection, is in the bottom right corner. The card you select will be discarded and its value added to the total available plant food in the Watering Hole from which species can feed.

Step 4: Click “Player 1: Start Phase 3”. In this phase, you will follow instructions to play each card. Players can use one card to do any of these 5 actions.

1) Create a species on the left side of the Player board
2) Create a species on the right side of the Player board
3) Increase a species’ body size
4) Increase a species’ population size
5) Upgrade one of your species with a new ability by attaching the trait card to it

Step 5: Click “Player 2: Start Phase 3.” Same as Step 4, but Player 2 does their actions. 

Step 6: Click “Feed”. Each Player will take turns choosing one of their species to feed. By default, species cannot consume more food than their population size (which is the P: number on the bottom right corner). Some traits allow species to consume additional food. After each Player has indicated they are finished feeding, a new round can begin by repeating steps 2-6.

It’s important to note that after feeding ends, species that did not feed will go into extinction from the species board. 

Step 7: After round 4, you should click “End Game”. By clicking “End Game”, it will calculate the scores for each player and declare a winner. 

**Additional Information**

Gameplay - Trait Cards Effects

*Foraging:*
This species receives an additional Plant Food from the same source (Watering Hole or Food Bank) anytime it takes Plant Food (unless it does not have a hungry population for the additional Food).

*Fertile:*
Before selecting Food Cards, this species gains 1 Population if there is Food on the Watering Hole (from the previous round).

*Long Neck:*
Take 1 Plant Food from the Food Bank (not the Watering Hole) before Food Cards are played each round.

*Fat Tissue:*
This species has the option to take Food even when it is not hungry up to the species’ Body Size.

*Cooperation:*
Anytime this species takes food from the Watering Hole, if you have a species to the right of it, that species takes 1 food from the Watering Hole.

Enjoy the game!

