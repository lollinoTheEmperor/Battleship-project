# Battleship Project
**Group member:**
- Gruber Gabriel (Lytir03)
- Larcher Lorenzo (lollinoTheEmperor)
- Marcon Lorenzo (maconlor)
- Pugnani Stefano (Pugnani and SPugnani@unibz.it (because of change of computer))

**Instrcutions** \
mvn package

**Description** \
This project is a Battleship game with some variations from the traditional version. You can play 1 vs 1 or 1 vs bot. There are different types of ships that perform various kinds of shots. The goal of the game is to eliminate all the ships of the other player.

**User guide** \
?video or text guide?

**Implementation of the project** \
The high-level components of our game are the Ship, the BoardGame, and the Player. These components interact with each other within the main class as well as among themselves. The Player class is responsible for deciding how to position the ship and which cell to attack. The BoardGame class manages the ships and keeps track of which cells are attacked. The Ship class maintains its health points (HP) and its position on the board. These high-level components were initially conceptualized and later broken down into more specific classes, interfaces, and subclasses to develop the game with greater precision. \

We only used Maven as a third-party library for testing. All other libraries are part of Java, primarily utilizing Java Swing, ArrayList, Set, Random, Map, BufferedReader, FileWriter, IOException, Files, Path, StandardOpenOption, HashMap, and Stream. \

We applied various techniques taught in our course. First, we used interfaces and subclasses to divide the work and clarify the responsibilities of each method before writing the actual code (interfaces) and to reduce code duplication between classes (subclasses). Another significant technique we employed was using streams to make the code more readable and efficient. Additionally, we learned how to read from and write to files, which we used to save player stats in the game. Lastly, we heavily relied on abstract data types such as Set, List, Map, and HashMap. 

**Human experience** \
The work was divided equally as follow: 
* Gruber: \
 -Main \
 -Ship \
 -Ship_Impl(no take_damage) 

* Larcher: \
  -ShotsFeedback \
  -VisualBoard \
  -VisualBoard_Impl \
  -Ship_Heavy \
  -Ship_kill 
  
* Marcon: \
  -PlayerStatus \
  -WinsComparator \
  -Player \
  -Bot_Player (only place_ship) \
  -Ship_Bomber \
  -Ship_OnDeath 
  
* Pugnani: \
  -AttackStrategy \
  -BoardFeedback \
  -BoardStart (place_ship made by Gruber with some change) \
  -BotPlayer (place ship made by Marcon) \
  -CheckboardStrategy \
  -GameBoard \
  -GameBoard_Impl \
  -Heatmap \
  -Heatmap_Impl \
  -Ship_impl (only take damage) \
  -ShipManager \
  -SpiralStrategy 
  
Each one has also done the respective tests. \
Git was used to communicate and develop on different branches. Because of this, we had many branches and sometimes needed to merge them, which allowed us to develop the game faster. Each one faced different challenges in this project, some of which were similar, such as how to organize the code before actually writing it. We will describe one individual challenge for each member: 

* Gruber:
* Larcher: 
* Marcon: The biggest challenge for me was programming as part of a team. Mainly I had difficulties in using and understanding what some sections of code do and the overall flow of the program.
* Pugnani: The biggest challenge I faced was creating the bot. I had never made a game before, and I am new to development, so I didn't know how a bot could work and the different types of strategies. To resolve this challenge, I first thought about how a bot should attack; it should choose between all different cells, and each should have a probability that there is a ship there. Then, I considered how to calculate the probability: if a part of a ship is hit, the probability should be higher near that area; otherwise, you should check where the ship can't be because of its length. After implementing that part and testing, I saw that it was very linear until the bot found a ship, so I implemented two attack strategies: if none of the ships are hit, the first one checks the area, and the second one performs a spiral attack. Because of this, the game is now more fluid, and the bot seems more random than before. With more time, it would be interesting to add more strategies and change how the strategies are switched.
