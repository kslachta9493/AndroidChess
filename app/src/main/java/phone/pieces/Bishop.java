package phone.pieces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import phone.androidchess84.ChessPiece;

public class Bishop extends ChessPiece implements Serializable {
    private static final long serialVersionUID = 1L;
    public Bishop(boolean color) {
        this.color = color;
    }
    @Override
    public List<Move> getMoves(ChessPiece[][] board) {
        List<Move> moves = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            if (locx + i > 7 || locy + i > 7) {
                //break;
            } else {
                if (board[locx + i][locy + i] == null) {
                    moves.add(new Move(locx, locy, locx + i, locy + i));
                } else if (board[locx + i][locy + i].getColor() != color) {
                    moves.add(new Move(locx, locy, locx + i, locy + i));
                    break;
                } else {
                    break;
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (locx - i < 0 || locy - i < 0) {
                //break;
            } else {
                if (board[locx - i][locy - i] == null) {
                    moves.add(new Move(locx, locy, locx - i, locy - i));
                } else if (board[locx - i][locy - i].getColor() != color) {
                    moves.add(new Move(locx, locy, locx - i, locy - i));
                    break;
                } else {
                    break;
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (locx + i > 7 || locy - i < 0) {
                //break;
            } else {

                if (board[locx + i][locy - i] == null) {
                    moves.add(new Move(locx, locy, locx + i, locy - i));
                } else if (board[locx + i][locy - i].getColor() != color) {
                    moves.add(new Move(locx, locy, locx + i, locy - i));
                    break;
                } else {
                    break;
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (locx - i < 0 || locy + i > 7) {
               // break;
            } else {
                if (board[locx - i][locy + i] == null) {
                    moves.add(new Move(locx, locy, locx - i, locy + i));
                } else if (board[locx - i][locy + i].getColor() != color) {
                    moves.add(new Move(locx, locy, locx - i, locy + i));
                    break;
                } else {
                    break;
                }
            }
        }
        return moves;
    }
}
