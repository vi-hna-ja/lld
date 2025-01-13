package problems.chessgame.models;

import problems.chessgame.models.pieces.*;

import java.util.Random;

public class Board {
    private Piece[][] chessBoard;
    public Board() {
        this.chessBoard = new Piece[8][8];
        initialiseBoard();
    }

    private void initialiseBoard() {
        // Initialize white pieces
        chessBoard[0][0] = new Rook(Color.WHITE, 0, 0);
        chessBoard[0][1] = new Knight(Color.WHITE, 0, 1);
        chessBoard[0][2] = new Bishop(Color.WHITE, 0, 2);
        chessBoard[0][3] = new Queen(Color.WHITE, 0, 3);
        chessBoard[0][4] = new King(Color.WHITE, 0, 4);
        chessBoard[0][5] = new Bishop(Color.WHITE, 0, 5);
        chessBoard[0][6] = new Knight(Color.WHITE, 0, 6);
        chessBoard[0][7] = new Rook(Color.WHITE, 0, 7);
        for (int i = 0; i < 8; i++) {
            chessBoard[1][i] = new Pawn(Color.WHITE, 1, i);
        }

        // Initialize black pieces
        chessBoard[7][0] = new Rook(Color.BLACK, 7, 0);
        chessBoard[7][1] = new Knight(Color.BLACK, 7, 1);
        chessBoard[7][2] = new Bishop(Color.BLACK, 7, 2);
        chessBoard[7][3] = new Queen(Color.BLACK, 7, 3);
        chessBoard[7][4] = new King(Color.BLACK, 7, 4);
        chessBoard[7][5] = new Bishop(Color.BLACK, 7, 5);
        chessBoard[7][6] = new Knight(Color.BLACK, 7, 6);
        chessBoard[7][7] = new Rook(Color.BLACK, 7, 7);
        for (int i = 0; i < 8; i++) {
            chessBoard[6][i] = new Pawn(Color.BLACK, 6, i);
        }
    }

    public void displayBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = chessBoard[i][j];
                System.out.print((piece == null ? '-' : piece.getPiece()) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isWithinBounds(int row, int col) {
        return !(row < 0 || col < 0 || row >= 8 || col >= 8 || chessBoard[row][col] == null);
    }

    public boolean isValidMove(Move move, Piece piece) {
        int row = move.getDestRow();
        int col = move.getDestCol();
        if (piece == null || !isWithinBounds(row, col))
            return false;
        Piece destPiece = chessBoard[row][col];
        return destPiece == null || piece.getColor() != destPiece.getColor()
                && destPiece.canMove(this, row, col);
    }

    public Piece getPiece(int row, int col) {
        return chessBoard[row][col];
    }

    public void setPiece(Move move) {
        Piece currentPiece = chessBoard[move.getSrcCol()][move.getSrcCol()];
        currentPiece.setPiece('-');
        chessBoard[move.getDestRow()][move.getDestCol()] = move.getPiece();
    }

    public boolean isCheckmate(Color color) {
        Random random = new Random();
        return random.nextBoolean();
    }

    public boolean isStalemate(Color color) {
        Random random = new Random();
        return random.nextBoolean();
    }

}
