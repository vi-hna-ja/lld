package problems.chessgame.models.pieces;

import problems.chessgame.models.Board;
import problems.chessgame.models.Color;

public class Rook  extends Piece {
    private int currRow;
    private int currCol;
    public Rook(Color color, int currRow, int currCol) {
        super(color, currRow, currCol, 'R');
    }
    @Override
    public boolean canMove(Board board, int destRow, int destCol) {
        return (currCol == destCol) || (currRow == destRow);
    }
}