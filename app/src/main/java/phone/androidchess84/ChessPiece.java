package phone.androidchess84;
import java.io.Serializable;
import java.util.List;

import phone.pieces.Move;
public abstract class ChessPiece implements Serializable {
    private static final long serialVersionUID = 1L;
    public static boolean WHITE = true;
    public static boolean BLACK = false;
    public boolean color;
    protected int locx, locy;
    public boolean firstMove = true;
    public void setLocation(int locx, int locy) {
        this.locx = locx;
        this.locy = locy;
    }
    public int getX() {
        return this.locx;
    }
    public int getY() {
        return this.locy;
    }
    public boolean getColor() {
        return this.color;
    }
    //public abstract boolean canAttack(Move start, Move end);
    //public abstract boolean canMove(Move start, Move end);
    public abstract List<Move> getMoves(ChessPiece[][] board);
}
