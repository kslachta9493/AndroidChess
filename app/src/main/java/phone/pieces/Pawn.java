package phone.pieces;
import phone.androidchess84.ChessPiece;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Pawn extends ChessPiece implements Serializable {
    private static final long serialVersionUID = 1L;
    public boolean enpassant = false;
    public Pawn(boolean color) {
        this.color = color;
    }
    public void setEnpassant(boolean enpassant) {
        this.enpassant = enpassant;
    }
    public boolean canMove(Move start, Move end) {
        int startx = start.getStartX();
        int starty = start.getStartY();
        int endx = end.getEndX();
        int endy = end.getEndY();
        List<Move> moves = new ArrayList<>();
        Move move;
        return false;
    }
    public boolean canAttack(Move start, Move end) {
        return false;
    }
    public List<Move> getMoves(ChessPiece[][] board) {
        List<Move> moves = new ArrayList<>();
        Move move;

        if (firstMove) {
            if (color == ChessPiece.BLACK) {
                if (locx + 2 < 8) {
                    if (board[locx + 1][locy] == null && board[locx + 2][locy] == null) {
                        move = new Move(locx, locy, locx + 2, locy);
                        moves.add(move);
                    }
                }
            } else {
                if (locx - 2 > -1) {
                    if (board[locx - 1][locy] == null && board[locx - 2][locy] == null) {
                        move = new Move(locx, locy, locx - 2, locy);
                        moves.add(move);
                  }
                }
            }
        }
        if (color == ChessPiece.BLACK) {
            if (locx + 1 < 8) {
                //move forward
                //System.out.println("black");
                if (board[locx + 1][locy] == null) {
                    move = new Move(locx, locy, locx + 1, locy);
                    moves.add(move);
                }
                //attack left
                if (locy - 1 > -1) {
                    if (board[locx + 1][locy -1] != null && board[locx + 1] [locy - 1].getColor() != color) {
                        move = new Move(locx, locy, locx + 1, locy - 1);
                        moves.add(move);
                    }
                }
                //attack right
                if (locy + 1 < 8) {
                    if (board[locx + 1][locy + 1] != null && board[locx + 1] [locy + 1].getColor() != color) {
                        move = new Move(locx, locy, locx + 1, locy + 1);
                        moves.add(move);
                    }
                }
            }
        } else {
            if (locx - 1 > -1) {
                //move forward
                if (board[locx - 1][locy] == null) {
                    move = new Move(locx, locy, locx - 1, locy);
                    moves.add(move);
                }
                //attack left
                if (locy - 1 > -1) {
                    if (board[locx - 1][locy -1] != null && board[locx - 1][locy -1].getColor() != color) {
                        move = new Move(locx, locy, locx - 1, locy - 1);
                        moves.add(move);
                    }
                }
                //attack right
                if (locy + 1 < 8) {
                    if (board[locx - 1][locy + 1] != null && board[locx - 1][locy + 1].getColor() != color) {
                        move = new Move(locx, locy, locx - 1, locy + 1);
                        moves.add(move);
                    }
                }
            }
        }
        return moves;
    }
}
