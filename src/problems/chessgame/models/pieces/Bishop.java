package problems.chessgame.models.pieces;

import problems.chessgame.models.Board;
import problems.chessgame.models.Color;

public class Bishop  extends Piece {
    int currRow;
    int currCol;
    public Bishop(Color color, int currRow, int currCol) {
        super(color, currRow, currCol, 'B');
    }
    @Override
    public boolean canMove(Board board, int destRow, int destCol) {
        int rowDiff = Math.abs(currRow - destRow);
        int colDiff = Math.abs(currCol - destCol);
        return (rowDiff == colDiff);
    }
}