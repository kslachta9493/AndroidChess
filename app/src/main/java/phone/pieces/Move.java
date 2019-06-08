package phone.pieces;

import java.io.Serializable;

public class Move implements Serializable {
    private static final long serialVersionUID = 1L;
    private int startx, starty, endx, endy;
    public Move(int startx, int starty, int endx, int endy) {
        this.startx = startx;
        this.starty = starty;
        this.endx = endx;
        this.endy = endy;
    }
    public int getStartX() {
        return this.startx;
    }
    public int getStartY() {
        return this.starty;
    }
    public int getEndX() {
        return this.endx;
    }
    public int getEndY() {
        return this.endy;
    }
}
