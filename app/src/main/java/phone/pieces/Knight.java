package phone.pieces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import phone.androidchess84.ChessPiece;

public class Knight extends ChessPiece implements Serializable {
    private static final long serialVersionUID = 1L;
    public Knight(boolean color) {
        this.color = color;
    }

    @Override
    public List<Move> getMoves(ChessPiece[][] board) {
        List<Move> moves = new ArrayList<>();
        if (locx + 2 < 8 && locy + 1 < 8) {
            if (board[locx + 2][locy + 1] == null || board[locx + 2][locy + 1].getColor() != color) {
                moves.add(new Move(locx, locy, locx + 2, locy + 1));
            }
        }
        if (locx + 2 < 8 && locy - 1 > -1) {
            if (board[locx + 2][locy - 1] == null || board[locx + 2][locy - 1].getColor() != color) {
                moves.add(new Move(locx, locy, locx + 2, locy - 1));
            }
        }
        if (locx - 2 > -1 && locy + 1 < 8) {
            if (board[locx - 2][locy + 1] == null || board[locx - 2][locy + 1].getColor() != color) {
                moves.add(new Move(locx, locy, locx - 2, locy + 1));
            }
        }
        if (locx - 2 > -1 && locy - 1 > -1) {
            if (board[locx - 2][locy - 1] == null || board[locx - 2][locy - 1].getColor() != color) {
                moves.add(new Move(locx, locy, locx - 2, locy - 1));
            }
        }
        if (locx + 1 < 8 && locy - 2 > -1) {
            if (board[locx + 1][locy - 2] == null || board[locx + 1][locy - 2].getColor() != color) {
                moves.add(new Move(locx, locy, locx + 1, locy - 2));
            }
        }
        if (locx - 1 > -1 && locy - 2 > -1) {
            if (board[locx - 1][locy - 2] == null || board[locx - 1][locy - 2].getColor() != color) {
                moves.add(new Move(locx, locy, locx - 1, locy - 2));
            }
        }
        if (locx - 1 > -1 && locy + 2 < 8) {
            if (board[locx - 1][locy + 2] == null || board[locx - 1][locy + 2].getColor() != color) {
                moves.add(new Move(locx, locy, locx - 1, locy + 2));
            }
        }
        if (locx + 1 < 8 && locy + 2 < 8) {
            if (board[locx + 1][locy + 2] == null || board[locx + 1][locy + 2].getColor() != color) {
                moves.add(new Move(locx, locy, locx + 1, locy + 2));
            }
        }
        return moves;
    }
}
