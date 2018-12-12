# domino_game
Coding challenge : Implementing a classic dominoes game. All use of Lists, external API are forbidden.

#Rules
- 4 players max (will play with a bot if there is only one player),
- a domino can only be placed on the right side,
- get the minimum score to win (sum of each domino's value you still have at the end of the game),
- for more, see : https://en.wikipedia.org/wiki/Dominoes

# Launching
Compile and launch the Main class file. It will invite you to enter the number of players and will generate the set of dominoes. 

    Number of players : 1
        (0) [0 | 0]
        (1) [0 | 1]
        (2) [0 | 2]
        (3) [0 | 3]
        (4) [0 | 4]
        (5) [0 | 5]
        (6) [0 | 6]
        (7) [1 | 1]
        ...
        (26) [5 | 6]
        (27) [6 | 6]

    Human vs Bot
    Enter a name :
    
# Bot
The bot chooses the domino with the highest score among the dominoes it owns. 
