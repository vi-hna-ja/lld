package problems.chessgame.services;

import problems.chessgame.models.*;
import problems.chessgame.models.pieces.King;
import problems.chessgame.models.pieces.Piece;

import java.util.Random;

public class ChessGame {
    private final Board board;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private Player currentPlayer;
    private final Random random;
    private GameStatus status;

    public ChessGame() {
        this.board = new Board();
        this.whitePlayer = new Player(1, Color.WHITE);
        this.blackPlayer = new Player(0, Color.BLACK);
        this.currentPlayer = blackPlayer;
        this.random = new Random();
        this.status = GameStatus.ACTIVE;
    }

    public void startGame() {
        board.displayBoard();
        while (status == GameStatus.ACTIVE) {
            Move nextMove = getNextMove();
            if (board.isValidMove(nextMove, nextMove.getPiece())) {
                currentPlayer.makeMove(board, nextMove);
            }
            else {
                System.out.printf("Invalid move by player %s. Please try again.\n", currentPlayer.getPlayerId());
                continue;
            }

            updateGameStatus();
            togglePlayer();
            board.displayBoard();
        }

        System.out.printf("Game ended with status %s\n", status);
    }

    private void updateGameStatus() {
        if (board.isCheckmate(currentPlayer.getColor())) {
            status = GameStatus.CHECKMATE;
        } else if (board.isStalemate(currentPlayer.getColor())) {
            status = GameStatus.STALEMATE;
        }
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer.getColor() == Color.WHITE) ? blackPlayer : whitePlayer;
    }

    private Move getNextMove() {
        int sourceRow, sourceCol, destinationRow, destinationCol;
        Piece currentPiece;
        do {
            sourceRow = random.nextInt(8);
            sourceCol = random.nextInt(8);
        } while (!board.isWithinBounds(sourceRow, sourceCol));

        currentPiece = board.getPiece(sourceRow, sourceCol);

        do {
            destinationRow = random.nextInt(8);
            destinationCol = random.nextInt(8);
        } while (!board.isWithinBounds(destinationRow, destinationCol));

        return new Move(currentPiece, sourceRow, sourceCol, destinationRow, destinationCol);
    }

}
