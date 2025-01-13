package problems.tictactoe.models;

import java.util.Arrays;

public class Board {
    private char[][] board;
    private int moves;

    public Board() {
        this.board = new char[3][3];
        this.moves = 0;
        initialiseBoard();
    }

    private void initialiseBoard() {
        for (char[] row : board)
            Arrays.fill(row, '-');
    }

    public char getPiece(int row, int col) {
        return board[row][col];
    }

    public void setPiece(Move move, char piece) {
        int row = move.getRow();
        int col = move.getCol();
        this.board[row][col] = piece;
        this.moves += 1;
    }

    public boolean isValidMove(Move move) {
        int row = move.getRow();
        int col = move.getCol();
        return row >= 0 && row < 3 && col >= 0 && col < 3 && getPiece(row, col) == '-';
    }

    public boolean isDraw() {
        return moves == 9 && !hasWinner();
    }

    public boolean hasWinner() {
        // check rows and cols
        for (int i = 0; i < 3; i++) {
            if (getPiece(i, 0) != '-' && getPiece(i, 0) == getPiece(i, 1) && getPiece(i, 1) == getPiece(i, 2))
                return true;
            if (getPiece(0, i) != '-' && getPiece(0, i) == getPiece(1, i) && getPiece(1, i) == getPiece(2, i))
                return true;
        }

        // check diagonals
        return (getPiece(2, 0) != '-' && getPiece(2, 0) == getPiece(1, 1) && getPiece(1, 1) == getPiece(0, 2))
                || (getPiece(0, 0) != '-' && getPiece(0, 0) == getPiece(1, 1) && getPiece(1, 1) == getPiece(2, 2));
    }

    public void printBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(getPiece(row, col) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
