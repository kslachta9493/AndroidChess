package phone.replay;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

import phone.pieces.*;
import phone.androidchess84.*;
public class Game implements Serializable {
    private static final long serialVersionUID = 2L;
    public List<ChessBoard> boards;
    private String title;
    private int location = 0;
    private String fulltitle;
    private Calendar date;
    private boolean initalized = false;

    public Game(ArrayList<ChessBoard> boards, String title) {
        this.boards = boards;
        this.title = title;
        this.date = Calendar.getInstance();
        this.fulltitle = this.title +  "|" + this.date.getTime();
    }
    public String toString() {
        return this.title + ":" + this.date.getTime();
    }
    public String getTitle() {
        return this.title;
    }
    public boolean isStart() {
        if (location == 0)
            return true;
        else
            return false;
    }
    public boolean peek() {
        if (location == boards.size() - 1)
            return false;
        else
            return true;
    }
    public ChessBoard nextMove() {
        if (peek()) {
            location++;
        }
        if (!initalized) {
            initalized = true;
            location--;
        }
        ChessBoard chessboard = boards.get(location);
        return chessboard;
    }
    public ChessBoard prevMove() {
        if (!isStart()) {
            location--;
        }
        ChessBoard chessboard = boards.get(location);
        return chessboard;
    }
    public Calendar getDate() {
        return this.date;
    }
    public static Comparator<Game> GaTitleComparator = new Comparator<Game>() {

        public int compare(Game s1, Game s2) {
            String s1name = s1.getTitle().toUpperCase();
            String s2name = s2.getTitle().toUpperCase();
            return s1name.compareTo(s2name);
        }
    };
    public static Comparator<Game> GaDateComparator = new Comparator<Game>() {

        public int compare(Game s1, Game s2) {
            Calendar d1 = s1.getDate();
            Calendar d2 = s2.getDate();
            return d1.compareTo(d2);
        }
    };

}
