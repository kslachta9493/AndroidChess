package phone.pieces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import phone.androidchess84.ChessPiece;
public class King extends ChessPiece implements Serializable {
    private static final long serialVersionUID = 1L;
    public King(boolean color) {
        this.color = color;
    }
    @Override
    public List<Move> getMoves(ChessPiece[][] board) {
        List<Move> moves = new ArrayList<>();
        if (locx + 1 < 8) {
            if (board[locx + 1][locy] == null || board[locx + 1][locy].getColor() != color) {
                moves.add(new Move(locx, locy, locx + 1, locy));
            }
        }
        if (locx - 1 > -1) {
            if (board[locx - 1][locy] == null || board[locx - 1][locy].getColor() != color) {
                moves.add(new Move(locx, locy, locx - 1, locy));
            }
        }
        if (locx + 1 < 8 && locy + 1 < 8) {
            if (board[locx + 1][locy + 1] == null || board[locx + 1][locy + 1].getColor() != color) {
                moves.add(new Move(locx, locy, locx + 1, locy + 1));
            }
        }
        if (locx + 1 < 8 && locy - 1 > -1) {
            if (board[locx + 1][locy - 1] == null || board[locx + 1][locy - 1].getColor() != color) {
                moves.add(new Move(locx, locy, locx + 1, locy - 1));
            }
        }
        if (locy - 1 > -1) {
            if (board[locx][locy - 1] == null || board[locx][locy - 1].getColor() != color) {
                moves.add(new Move(locx, locy, locx, locy - 1));
            }
        }
        if (locy + 1 < 8) {
            if (board[locx][locy + 1] == null || board[locx][locy + 1].getColor() != color) {
                moves.add(new Move(locx, locy, locx, locy + 1));
            }
        }
        if (locx - 1 > -1 && locy - 1 > -1) {
            if (board[locx - 1][locy - 1] == null || board[locx - 1][locy - 1].getColor() != color) {
                moves.add(new Move(locx, locy, locx - 1, locy - 1));
            }
        }
        if (locx - 1 > -1 && locy + 1 < 8) {
            if (board[locx - 1][locy + 1] == null || board[locx - 1][locy + 1].getColor() != color) {
                moves.add(new Move(locx, locy, locx - 1, locy + 1));
            }
        }

        if (firstMove) {
            if (color == ChessPiece.WHITE) {
                if (locx == 7 && locy == 4) {
                    if (board[7][0] != null && board[7][0] instanceof Rook && board[7][0].firstMove) {

                    }
                }
            } else {
                if (locx == 0 && locy == 4) {

                }
            }
        }
        return moves;
    }
}
