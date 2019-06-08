package phone.androidchess84;

import java.io.Serializable;

public class Location implements Serializable {
    private static final long serialVersionUID = 1L;
    int row;
    int column;

    Location(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
