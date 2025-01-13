package problems.chessgame.models.pieces;

import problems.chessgame.models.Board;
import problems.chessgame.models.Color;
import problems.chessgame.models.Move;

import java.util.List;

public abstract class Piece {
    Color color;
    int currRow;
    int currCol;
    char piece;
    protected Piece(Color color, int currRow, int currCol, char piece) {
        this.color = color;
        this.currRow = currRow;
        this.currCol = currCol;
        this.piece = piece;
    }

    protected Piece() {
        this.piece = '-';
    }

    public abstract boolean canMove(Board board, int destRow, int destCol);

    public Color getColor() {
        return color;
    }

    public char getPiece() {
        return piece;
    }

    public int getCurrRow() {
        return currRow;
    }

    public int getCurrCol() {
        return currCol;
    }

    public void setPiece(char piece) {
        this.piece = piece;
    }
}
