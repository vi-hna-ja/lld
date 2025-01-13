package problems.chessgame.models.pieces;

import problems.chessgame.models.Board;
import problems.chessgame.models.Color;

public class Queen extends Piece {
    private int currRow;
    private int currCol;
    public Queen(Color color, int currRow, int currCol) {
        super(color, currRow, currCol, 'Q');
    }
    @Override
    public boolean canMove(Board board, int destRow, int destCol) {
        int rowDiff = Math.abs(currRow - destRow);
        int colDiff = Math.abs(currCol - destCol);
        return (rowDiff == colDiff) || (currRow == destRow || currCol == destCol);
    }
}