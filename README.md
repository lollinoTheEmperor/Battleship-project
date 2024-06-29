# Battleship Project
**Group member:**
- Gruber Gabriel ()
- Larcher Lorenzo ()
- Marcon Lorenzo ()
- Pugnani Stefano ()

**Instrcutions** \
How to start the project

**Description** \
This project is a Battleship game with some variations from the normal ones, you can play 1 vs 1 or 1 vs bot, there are different type of ship that do different kind of shots. 
The scope of the game is to eliminate all the ship of the other player.

**User guide** \
?video or text guide?

**Implementation of the project** \
the different (high-level) components and interfaces between components, which third-party libraries were used (if any), and some programming techniques (seen in the course or not) that were particularly relevant for this project.

**Human experience** \
The work was divided equally as follow: \
*-Gruber:* \
 -Main \
 -Ship \
 -Ship_Impl(no take_damage) 

*-Larcher:* \
  -ShotsFeedback \
  -VisualBoard \
  -VisualBoard_Impl \
  -Ship_Heavy \
  -Ship_kill \
*-Marcon:* \
  -PlayerStatus \
  -WinsComparator \
  -Player \
  -Bot_Player (only place_ship) \
  -Ship1_noName \
  -Ship2_noname 
  
*-Pugnani: *\
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
  -SpiralStrategy \
Each one has also done the respective test. \
Git was used to comunicate and develop on different branch each work, because of that we had a lot of branch and sometimes we had to merge but this has let us develop faster the game. 
Each one has faced different challenge in doing this project and some that are similar such as how to organise the code before actualy writing it and others. 
We will describe one individual challenge on our part of code for each memeber: \
*-Gruber:* \
*-Larcher:* \
*-Marcon:* \
*-Pugnani:* The biggest challenge that I faced was creating the bot, I never did a game and I'm new in developing so I didn't know how a bot could work and the different  type of strategy, to resolve this challenge I first thought of how a bot should attack, so it should choose between all different cell and because of that each  should have a probability that there is a ship there. Then I thought how to calculate the probability, if there is a part of a ship hitted then near them the  probability should be higher otherwise you should check where the ship can't be before of his lenght. After I have implemented that part doing the test I saw that  it was very linear till the bot didn't find a ship so I implemented two attack strategy if none of the ship are hitted, the first one a check type the second one a spiral attack. Because of that now the game is more fluent and the bot seems more and is more random then before. With more time it would be interesting to add  more strategy and change how the strategy are changed.
