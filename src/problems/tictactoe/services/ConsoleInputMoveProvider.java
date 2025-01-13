package problems.tictactoe.services;

import problems.tictactoe.models.Board;
import problems.tictactoe.models.Move;
import problems.tictactoe.models.Player;

import java.util.Scanner;

public class ConsoleInputMoveProvider implements MoveProvider {
    private final Scanner scanner = new Scanner(System.in);
    @Override
    public Move getNextMove(Player player, Board board) {
        int row, col;
        do {
            System.out.printf("Player %s (%s), enter your move (row and col): ", player.getPlayerId(), player.getPiece());
            row = scanner.nextInt();
            col = scanner.nextInt();
        } while (!board.isValidMove(new Move(row, col)));
        return new Move(row, col);
    }
}
