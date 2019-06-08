package phone.pieces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import phone.androidchess84.ChessPiece;

public class Queen extends ChessPiece implements Serializable {
    private static final long serialVersionUID = 1L;
    public Queen(boolean color) {
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
        for (int i = locx - 1; i > -1; i--) {
            if (board[i][locy] == null) {
                moves.add(new Move(locx, locy, locx + (i - locx), locy));
            } else if (board[i][locy].getColor() != color) {
                moves.add(new Move(locx, locy, locx + (i - locx), locy));
                break;
            } else {
                break;
            }
        }
        //south
        for (int i = locx + 1; i < 8; i++)  {
            if (board[i][locy] == null) {
                // System.out.println("Found:" + locx + ":" + i + ":locy:" + locy);
                moves.add(new Move(locx, locy, locx + (i - locx), locy));
            } else if (board[i][locy].getColor() != color) {
                moves.add(new Move(locx, locy, locx + (i - locx), locy));
                break;
            } else {
                break;
            }
        }
        //east
        for (int i = locy - 1; i > -1; i--) {
            if (board[locx][i] == null) {
                moves.add(new Move(locx, locy, locx, locy + (i - locy)));
            } else if (board[locx][i].getColor() != color) {
                moves.add(new Move(locx, locy, locx, locy + (i - locy)));
                break;
            } else {
                break;
            }
        }
        //west
        for (int i = locy + 1; i < 8; i++)  {
            if (board[locx][i] == null) {
                //System.out.println("W:" + locx + ":" + i + ":locy:" + locy);
                moves.add(new Move(locx, locy, locx, locy + (i - locy)));
            } else if (board[locx][i].getColor() != color) {
                //System.out.println("W:" + locx + ":" + i + ":locy:" + locy);
                moves.add(new Move(locx, locy, locx, locy + (i - locy)));
                break;
            } else {
                break;
            }
        }
        return moves;
    }
}
