package problems.chessgame.models.pieces;

import problems.chessgame.models.Board;
import problems.chessgame.models.Color;

public class Pawn extends Piece {
    private int currRow;
    private int currCol;
    public Pawn(Color color, int currRow, int currCol) {
        super(color, currRow, currCol, 'P');
    }
    @Override
    public boolean canMove(Board board, int destRow, int destCol) {
        int rowDiff = Math.abs(currRow - destRow);
        int colDiff = Math.abs(currCol - destCol);

        if (color == Color.WHITE) {
            return (rowDiff == 1 && colDiff == 0) ||
                    (currRow == 1 && rowDiff == 2 && colDiff == 0) ||
                    (rowDiff == 1 && colDiff == 1 && board.getPiece(destRow, destCol) != null);
        }

        return (rowDiff == -1 && colDiff == 0) ||
                    (currRow == 6 && rowDiff == -2 && colDiff == 0) ||
                    (rowDiff == -1 && colDiff == 1 && board.getPiece(destRow, destCol) != null);
    }
}
