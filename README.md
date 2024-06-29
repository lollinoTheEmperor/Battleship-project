# Battleship Project
**Group member:**
- Gruber Gabriel ()
- Larcher Lorenzo ()
- Marcon Lorenzo ()
- Pugnani Stefano ()

**Instrcutions** \
How to start the project

**Description** \
This project is a Battleship game with some variations from the traditional version. You can play 1 vs 1 or 1 vs bot. There are different types of ships that perform various kinds of shots. The goal of the game is to eliminate all the ships of the other player.

**User guide** \
?video or text guide?

**Implementation of the project** \
the different (high-level) components and interfaces between components, which third-party libraries were used (if any), and some programming techniques (seen in the course or not) that were particularly relevant for this project.

**Human experience** \
The work was divided equally as follow: \
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
  -Ship1_noName \
  -Ship2_noname 
  
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
Git was used to communicate and develop on different branches. Because of this, we had many branches and sometimes needed to merge them, which allowed us to develop the game faster. Each one faced different challenges in this project, some of which were similar, such as how to organize the code before actually writing it. We will describe one individual challenge for each member: \

* Gruber: 
* Larcher: 
* Marcon: 
* Pugnani:  The biggest challenge I faced was creating the bot. I had never made a game before, and I am new to development, so I didn't know how a bot could work and the different types of strategies. To resolve this challenge, I first thought about how a bot should attack; it should choose between all different cells, and each should have a probability that there is a ship there. Then, I considered how to calculate the probability: if a part of a ship is hit, the probability should be higher near that area; otherwise, you should check where the ship can't be because of its length. After implementing that part and testing, I saw that it was very linear until the bot found a ship, so I implemented two attack strategies: if none of the ships are hit, the first one checks the area, and the second one performs a spiral attack. Because of this, the game is now more fluid, and the bot seems more random than before. With more time, it would be interesting to add more strategies and change how the strategies are switched.
