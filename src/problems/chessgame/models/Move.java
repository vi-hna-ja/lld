package problems.chessgame.models;

import problems.chessgame.models.pieces.Piece;

public class Move {
    private final Piece piece;
    private final int srcRow;
    private final int srcCol;
    private final int destRow;
    private final int destCol;

    public Move(Piece piece, int srcRow, int srcCol, int destRow, int destCol) {
        this.piece = piece;
        this.srcRow = srcRow;
        this.srcCol = srcCol;
        this.destRow = destRow;
        this.destCol = destCol;
    }

    public int getDestRow() {
        return destRow;
    }

    public int getDestCol() {
        return destCol;
    }

    public int getSrcRow() {
        return srcRow;
    }

    public int getSrcCol() {
        return srcCol;
    }

    public Piece getPiece() {
        return piece;
    }
}
