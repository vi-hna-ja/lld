package problems.tictactoe.services;

import problems.tictactoe.models.Board;
import problems.tictactoe.models.Move;
import problems.tictactoe.models.Player;

public class Game {
    private final Board board;
    private final Player[] players;
    private int currentPlayer;
    private final MoveProvider moveProvider;
    public Game(MoveProvider moveProvider) {
        this.board = new Board();
        this.players = new Player[2];
        players[0] = new Player(0, 'X');
        players[1] = new Player(1, '0');
        this.currentPlayer = 0;
        this.moveProvider = moveProvider;
    }

    public void startGame() {
        board.printBoard();
        while (!board.isDraw() && !board.hasWinner()) {
            Player player = players[currentPlayer];
            Move nextMove = getNextMove();
            if (board.isValidMove(nextMove)) {
                System.out.printf("Move on row %s, col %s made by player[%s] - %s\n",
                        nextMove.getRow(), nextMove.getCol(), currentPlayer, player.getPiece());
                board.setPiece(nextMove, player.getPiece());
            }
            else {
                System.out.printf("Invalid move by player %s. Please try again.\n", currentPlayer);
                continue;
            }

            board.printBoard();
            currentPlayer = (currentPlayer + 1) % 2;
        }

        if (board.hasWinner()) {
            Player winner = players[getWinner()];
            System.out.printf("Winner is Player %s (%s). Congratulations!\n", winner.getPlayerId(), winner.getPiece());
        } else {
            System.out.println("It's a draw! Well played.");
        }
    }

    private int getWinner() {
        return (currentPlayer + 1) % 2;
    }

    private Player getPlayer() {
        return players[currentPlayer];
    }

    private Move getNextMove() {
        return moveProvider.getNextMove(getPlayer(), board);
    }
}
