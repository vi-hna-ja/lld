package problems.chessgame.models;

public class Player {
    private final int playerId;
    private final Color color;

    public Player(int playerId, Color color) {
        this.playerId = playerId;
        this.color = color;
    }

    public int getPlayerId() {
        return playerId;
    }

    public Color getColor() {
        return color;
    }

    public void makeMove(Board board, Move move) {
        System.out.printf("Player %s making move for piece %s to [%s, %s]\n",
                playerId, move.getPiece(), move.getDestRow(), move.getDestCol());
        board.setPiece(move);
    }
}
