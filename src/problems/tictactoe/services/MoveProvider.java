package problems.tictactoe.services;

import problems.tictactoe.models.Board;
import problems.tictactoe.models.Move;
import problems.tictactoe.models.Player;

public interface MoveProvider {
    Move getNextMove(Player player, Board board);
}
