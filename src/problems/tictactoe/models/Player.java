package problems.tictactoe.models;

public class Player {
    private final int playerId;
    private final char piece;
    public Player(int playerId, char piece) {
        this.playerId = playerId;
        this.piece = piece;
    }

    public int getPlayerId() {
        return playerId;
    }

    public char getPiece() {
        return piece;
    }
}
