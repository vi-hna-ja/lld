package problems.tictactoe.services;

import problems.tictactoe.models.Board;
import problems.tictactoe.models.Move;
import problems.tictactoe.models.Player;

import java.util.Random;

public class RandomMoveProvider implements MoveProvider {

    private final Random random = new Random();

    @Override
    public Move getNextMove(Player player, Board board) {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!board.isValidMove(new Move(row, col)));

        return new Move(row, col);
    }
}
