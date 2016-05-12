# TicTacToe

## Straightforward implementation of TicTacToe game with 'Player vs. Player' and 'Player vs. Computer' modes using AndroidStudio ##


# Home Screen: #


New Game button: Resets the board to an empty board (if Challenge mode is "Computer first" and it is Player vs. Computer mode, then the computer will have made a move)

Game board: 3x3 grid of buttons

Settings: Direct user to settings screen

Note: There are three TextViews which display which user has won, the level of play (if Player vs. Computer mode), and the current mode of play.


# Settings Screen: #


General Settings- 
Player Mode: Preference between Player vs. Player or Player vs. Computer mode

Player vs. Computer Settings-
Level: Preference between Easy, Medium, and Hard

Challenge Mode: Preference between Player goes first or Computer goes first


# Algorithm (for Player vs. Computer mode): #

Since Tic-Tac-Toe is a solved game, the AI revolves around attempting to be a perfect player. A perfect player playing against another perfect player will result in a draw. However, this only applies to playing against the Computer in the Hard level. In the Easy and Medium levels, the AI will be less stringent and "less careful". The algorithm is exactly as follows, trying the first possible choice:

''' Win the game (Medium & Hard)
*Block the opponent from winning immediately (Medium & Hard)
*Attempt to make a fork (Hard)
*Block an opponent's fork (Hard)
*Take the center square (Easy, Medium & Hard)
*Take a corner square (Easy & Hard)
*Take a side square (Easy, Medium & Hard)
*Take any square (Medium)

Note that if Challenge mode is on (which means the Computer will go first) the Computer will probably attempt to make forks more than if Challenge mode is on.



