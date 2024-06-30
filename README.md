# Battleship Project
**Group member:**
- Gruber Gabriel (Lytir03)
- Larcher Lorenzo (lollinoTheEmperor)
- Marcon Lorenzo (maconlor)
- Pugnani Stefano (Pugnani and SPugnani@unibz.it (because of change of computer))

**Instrcutions** \
mvn package

**Description** \
Welcome to our Battleship game! In this game, you can choose to play against a bot or another player on the same computer. The objective is to sink all of your opponent's ships by strategically positioning your own ships and attacking your opponent's ships.

**User guide** 
- *Getting Started* 
   + Choosing the Game Mode: \
     When you start the game, you will be prompted to choose whether you want to play against a bot or another player. Select one to procede.
   + Positioning the Ships: \
      You can place your ships on the board by selecting the starting cell and the ending cell. If the ship doesn't fit, or if it is smaller or longer than the designated ship size, you will be notified and prompted to reselect the cells.
   + Types of Ships: 
      *  Ship_Bomber: This ship has a powerful attack that can damage multiple cells.
      *  Ship_Heal: This ship can heal itself after some turns.
      * Ship_Heavy: This ship hits the selected cell and adjacent cells.
      * Ship_OnDeath: This ship triggers a special effect when it is sunk. It attacks a random cell of the owner
      * Ship_Radar: This ship can reveal which quadrant has the most ships to target.

- *Playing the Game*
  + Starting the Game: \
  Once all ships are positioned, the game begins. Players take turns to attack each other’s ships by selecting a cell on the opponent’s board.
  + Attacking: \
    Select a cell to attack. The outcome of the attack depends on the type of ship you are using. Each ship type has a unique attack pattern and effect. For example, the Ship_Bomber can attack multiple cells at once, while the Ship_Heal can restore health to your own ships.
  + Winning the Game: The game continues with players taking turns to attack. The objective is to sink all of your opponent’s ships. The first player to sink all enemy ships wins the game.
  + Tips and Strategies
      - Strategic Placement: Place your ships in a way that makes them hard to find. Avoid clustering them together.
      - Balanced Attacks: Use different types of ships to your advantage.
      - Predict Opponent’s Moves: Try to anticipate where your opponent’s ships might be based on their attack patterns.
- *Conclusion*
That’s all you need to know to start playing our Battleship game! Choose your game mode, position your ships strategically, and outsmart your opponent to become the ultimate Battleship champion. Enjoy the game!


**Implementation of the project** \
The high-level components of our game are the Ship, the BoardGame, and the Player. These components interact with each other within the main class as well as among themselves. The Player class is responsible for deciding how to position the ship and which cell to attack. The BoardGame class manages the ships and keeps track of which cells are attacked. The Ship class maintains its health points (HP) and its position on the board. These high-level components were initially conceptualized and later broken down into more specific classes, interfaces, and subclasses to develop the game with greater precision. 

We only used Maven as a third-party library for testing. All other libraries are part of Java, primarily utilizing Java Swing, ArrayList, Set, Random, Map, BufferedReader, FileWriter, IOException, Files, Path, StandardOpenOption, HashMap, and Stream. 

We applied various techniques taught in our course. First, we used interfaces and subclasses to divide the work and clarify the responsibilities of each method before writing the actual code (interfaces) and to reduce code duplication between classes (subclasses). Another significant technique we employed was using streams to make the code more readable and efficient. Additionally, we learned how to read from and write to files, which we used to save player stats in the game. Lastly, we heavily relied on abstract data types such as Set, List, Map, and HashMap. 

**Human experience** /
The work was divided equally as follow: 
* Gruber:
   - Main 
   - Ship 
   - Ship_Impl(no take_damage)
   - Ship_radar
   - BoardStart(only place_ship then modified a bit)

* Larcher: 
  - ShotsFeedback 
  - VisualBoard 
  - VisualBoard_Impl 
  - Ship_Heavy 
  - Ship_kill 
  
* Marcon: 
  - PlayerStatus 
  - WinsComparator 
  - Player 
  - Bot_Player (only place_ship) 
  - Ship_Bomber 
  - Ship_OnDeath 
  
* Pugnani: 
  - AttackStrategy 
  - BoardFeedback 
  - BoardStart (place_ship made by Gruber with some change) 
  - BotPlayer (place ship made by Marcon) 
  - CheckboardStrategy 
  - GameBoard 
  - GameBoard_Impl 
  - Heatmap 
  - Heatmap_Impl 
  - Ship_impl (only take damage) 
  - ShipManager 
  - SpiralStrategy 
  
Each one has also done the respective tests. \
Git was used to communicate and develop on different branches. Because of this, we had many branches and sometimes needed to merge them, which allowed us to develop the game faster. Each one faced different challenges in this project, some of which were similar, such as how to organize the code before actually writing it. We will describe one individual challenge for each member: 

* Gruber:
* Larcher: 
* Marcon: The biggest challenge for me was programming as part of a team. Mainly I had difficulties in using and understanding what some sections of code do and the overall flow of the program.
* Pugnani: The biggest challenge I faced was creating the bot. I had never made a game before, and I am new to development, so I didn't know how a bot could work and the different types of strategies. To resolve this challenge, I first thought about how a bot should attack; it should choose between all different cells, and each should have a probability that there is a ship there. Then, I considered how to calculate the probability: if a part of a ship is hit, the probability should be higher near that area; otherwise, you should check where the ship can't be because of its length. After implementing that part and testing, I saw that it was very linear until the bot found a ship, so I implemented two attack strategies: if none of the ships are hit, the first one checks the area, and the second one performs a spiral attack. Because of this, the game is now more fluid, and the bot seems more random than before. With more time, it would be interesting to add more strategies and change how the strategies are switched.
