package problems.tictactoe;

import problems.tictactoe.services.Game;
import problems.tictactoe.services.RandomMoveProvider;

/*
Requirements:
The Tic-Tac-Toe game should be played on a 3x3 grid.
Two players take turns marking their symbols (X or O) on the grid.
The first player to get three of their symbols in a row (horizontally, vertically, or diagonally) wins the game.
If all the cells on the grid are filled and no player has won, the game ends in a draw.
The game should have a user interface to display the grid and allow players to make their moves.
The game should handle player turns and validate moves to ensure they are legal.
The game should detect and announce the winner or a draw at the end of the game.
 */

public class TicTacToeDemo {
    public void run() {
        Game game = new Game(new RandomMoveProvider());
        game.startGame();
    }
}
