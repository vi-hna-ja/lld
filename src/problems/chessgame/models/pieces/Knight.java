package problems.chessgame.models.pieces;

import problems.chessgame.models.Board;
import problems.chessgame.models.Color;

public class Knight   extends Piece {
    private int currRow;
    private int currCol;
    public Knight(Color color, int currRow, int currCol) {
        super(color, currRow, currCol, 'N');
    }
    @Override
    public boolean canMove(Board board, int destRow, int destCol) {
        int rowDiff = Math.abs(currRow - destRow);
        int colDiff = Math.abs(currCol - destCol);
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}
