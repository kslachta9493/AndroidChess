package phone.androidchess84;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import phone.pieces.*;
public class ChessBoard implements Serializable {
    private static final long serialVersionUID = 7l;
    ChessPiece[][] board;
    ChessPiece holder = null;
    ChessPiece prevPawn = null;
    ChessPiece whiteKing = null;
    ChessPiece blackKing = null;
    boolean turng = true;
    int wkingx;
    int wkingy;
    int bkingx;
    int bkingy;
    public ChessBoard() {
        board = new ChessPiece[8][8];
        board[7][0] = new Rook(true);
        board[7][1] = new Knight(ChessPiece.WHITE);
        board[7][2] = new Bishop(ChessPiece.WHITE);
        board[7][3] = new Queen(ChessPiece.WHITE);
        board[7][4] = new King(ChessPiece.WHITE);
        whiteKing = board[7][4];
        board[7][5] = new Bishop(ChessPiece.WHITE);
        board[7][6] = new Knight(ChessPiece.WHITE);
        board[7][7] = new Rook(ChessPiece.WHITE);
        wkingx = 7;
        wkingy = 4;
        board[0][0] = new Rook(false);
        board[0][1] = new Knight(ChessPiece.BLACK);
        board[0][2] = new Bishop(ChessPiece.BLACK);
        board[0][3] = new Queen(ChessPiece.BLACK);
        board[0][4] = new King(ChessPiece.BLACK);
        blackKing = board[0][4];
        board[0][5] = new Bishop(ChessPiece.BLACK);
        board[0][6] = new Knight(ChessPiece.BLACK);
        board[0][7] = new Rook(ChessPiece.BLACK);
        bkingx = 0;
        bkingy = 4;
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(ChessPiece.BLACK);
            board[6][i] = new Pawn(ChessPiece.WHITE);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null)
                 board[i][j].setLocation(i, j);
            }
        }
    }
    public ChessBoard(ChessBoard chessboard) {
        this.board = new ChessPiece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = chessboard.board[i][j];
            }
        }
    }
    public boolean checkKing(Move move, boolean turn) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getColor() != turn) {
                    List<Move> moves = board[i][j].getMoves(board);
                    for (Move m: moves) {
                        if (m.getEndX() == move.getEndX()) {
                            if (m.getEndY() == move.getEndY()) {
                                return true;
                            }
                        }
                    }
                }

            }
        }
        return false;
    }
    public boolean putInCheck(Move move, boolean turn) {
        //move(move);
        if (madeCheck(turn)) {
            revertMove(move);
            return true;
        }
        //make the move
        //check for check
        //revert if in check
        return false;
    }
    public boolean canCastle(Move move, boolean turn) {
        ChessPiece holder;
        //white king
        if (move.getStartX() == 7 && move.getStartY() == 4) {
            //Castle Right
            if (move.getEndX() == 7 && move.getEndY() == 6) {
                if (board[7][7] != null && board[7][7] instanceof Rook) {
                    //rook hasn't moved
                    if (board[7][7].firstMove) {
                        //Row is empty
                        if (board[7][6] == null && board[7][5] == null) {
                            //not in check
                            if (!inCheck(turn)) {
                                holder = board[move.getStartX()][move.getStartY()];
                                board[7][5] = board[move.getStartX()][move.getStartY()];
                                if (!inCheck(turn)) {
                                    board[7][6] = board[7][5];
                                    board[7][5] = null;
                                    if (!inCheck(turn)) {
                                        board[7][4] = holder;
                                        board[7][6] = null;
                                        return true;
                                    } else {
                                        board[7][4] = holder;
                                        board[7][6] = null;
                                    }
                                } else {
                                    board[7][4] = holder;
                                    board[7][5] = null;
                                }
                            }
                        }
                    }
                }
            }
            //Castle left
            if (move.getEndX() == 7 && move.getEndY() == 2) {
                if (board[7][0] != null && board[7][0] instanceof Rook) {
                    //rook hasn't moved
                    if (board[7][0].firstMove) {
                        //Row is empty
                        if (board[7][1] == null && board[7][2] == null && board[7][3] == null) {
                            //not in check
                            if (!inCheck(turn)) {
                                holder = board[move.getStartX()][move.getStartY()];
                                board[7][3] = board[move.getStartX()][move.getStartY()];
                                if (!inCheck(turn)) {
                                    board[7][2] = board[7][3];
                                    board[7][3] = null;
                                    if (!inCheck(turn)) {
                                        board[7][4] = holder;
                                        board[7][2] = null;
                                        return true;
                                    } else {
                                        board[7][4] = holder;
                                        board[7][2] = null;
                                    }
                                } else {
                                    board[7][4] = holder;
                                    board[7][3] = null;
                                }
                            }
                        }
                    }
                }
            }
        }
        //black king
        if (move.getStartX() == 0 && move.getStartY() == 4) {
            //Castle Right
            if (move.getEndX() == 0 && move.getEndY() == 6) {
                if (board[0][7] != null && board[0][7] instanceof Rook) {
                    //rook hasn't moved
                    if (board[0][7].firstMove) {
                        //Row is empty
                        if (board[0][6] == null && board[0][5] == null) {
                            //not in check
                            if (!inCheck(turn)) {
                                holder = board[move.getStartX()][move.getStartY()];
                                board[0][5] = board[move.getStartX()][move.getStartY()];
                                if (!inCheck(turn)) {
                                    board[0][6] = board[0][5];
                                    board[0][5] = null;
                                    if (!inCheck(turn)) {
                                        board[0][4] = holder;
                                        board[0][6] = null;
                                        return true;
                                    } else {
                                        board[0][4] = holder;
                                        board[0][6] = null;
                                    }
                                } else {
                                    board[0][4] = holder;
                                    board[0][5] = null;
                                }
                            }
                        }
                    }
                }
            }
            //castle left
            if (move.getEndX() == 0 && move.getEndY() == 2) {
                if (board[0][0] != null && board[0][0] instanceof Rook) {
                    //rook hasn't moved
                    if (board[0][0].firstMove) {
                        //Row is empty
                        if (board[0][1] == null && board[0][2] == null && board[0][3] == null) {
                            //not in check
                            if (!inCheck(turn)) {
                                holder = board[move.getStartX()][move.getStartY()];
                                board[0][3] = board[move.getStartX()][move.getStartY()];
                                if (!inCheck(turn)) {
                                    board[0][2] = board[0][3];
                                    board[0][3] = null;
                                    if (!inCheck(turn)) {
                                        board[0][4] = holder;
                                        board[0][2] = null;
                                        return true;
                                    } else {
                                        board[0][4] = holder;
                                        board[0][2] = null;
                                    }
                                } else {
                                    board[0][4] = holder;
                                    board[0][3] = null;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean canEnpassant (Move move, boolean turn) {

        if (board[move.getStartX()][move.getStartY()] instanceof Pawn) {
            if (prevPawn != null) {
                if (prevPawn.getX() == move.getStartX()) {
                    if (prevPawn.getY() == move.getStartY() - 1 || prevPawn.getY() == move.getStartY() + 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean canMove(Move move, boolean turn) {
        //List<Move> moves = board[move.getStartX()][move.getStartY()].getMoves();
        //if (board[move.getStartX()][move.getStartY()].)
        this.turng = turn;
        int startx = move.getStartX();
        int starty = move.getStartY();
        int endx = move.getEndX();
        int endy = move.getEndY();

        if (board[startx][starty] == null || board[startx][starty].getColor() != turn) {
            return false;
        }
        List<Move> moves = board[startx][starty].getMoves(board);
        if (canEnpassant(move, turn) ) {
            if (prevPawn.getColor() == ChessPiece.BLACK) {
                moves.add(new Move(10, 10,2, prevPawn.getY()));
            } else {
                moves.add(new Move(20, 20,5, prevPawn.getY()));
            }
        }
        //Castle Check
        if (board[move.getStartX()][move.getStartY()] instanceof King) {
            if (board[move.getStartX()][move.getStartY()].firstMove) {
                if (canCastle(move, turn)) {
                    if (move.getStartX() == 7 && move.getEndY() == 6)
                        moves.add(new Move(7, 4, 7, 6));
                    if (move.getStartX() == 7 && move.getEndY() == 2)
                        moves.add(new Move(7, 4, 7, 2));
                    if (move.getStartX() == 0 && move.getEndY() == 6)
                        moves.add(new Move(0, 4, 0, 6));
                    if (move.getStartX() == 0 && move.getEndY() == 2)
                        moves.add(new Move(0, 4, 0, 2));
                }
            }
        }

        for (Move m : moves) {
            if (m.getEndX() == endx) {
                if (m.getEndY() == endy) {
                    return true;
                }
            }
        }
        System.out.println("Failed to find move");
        return false;
    }
    public void move(Move move) {
        holder = board[move.getEndX()][move.getEndY()];
        board[move.getEndX()][move.getEndY()] = board[move.getStartX()][move.getStartY()];
        board[move.getEndX()][move.getEndY()].setLocation(move.getEndX(), move.getEndY());

        if (move.getStartX() == 7 && move.getStartY() == 4 && move.getEndX() == 7 && move.getEndY() == 6) {
            holder = board[7][7];
            board[7][5] = board[7][7];
            board[7][7] = null;
        }
        if (move.getStartX() == 7 && move.getStartY() == 4 && move.getEndX() == 7 && move.getEndY() == 2) {
            holder = board[7][0];
            board[7][3] = board[7][0];
            board[7][0] = null;
        }
        if (move.getStartX() == 0 && move.getStartY() == 4 && move.getEndX() == 0 && move.getEndY() == 6) {
            holder = board[0][7];
            board[0][5] = board[0][7];
            board[0][7] = null;
        }
        if (move.getStartX() == 0 && move.getStartY() == 4 && move.getEndX() == 0 && move.getEndY() == 2) {
            holder = board[0][0];
            board[0][3] = board[0][0];
            board[0][0] = null;
        }
        if (board[move.getEndX()][move.getEndY()] instanceof Pawn) {
            if (prevPawn != null) {
                if (move.getEndY() == prevPawn.getY()) {
                    if (move.getStartX() == 4 && move.getStartY() == prevPawn.getY() + 1) {
                        board[4][move.getEndY()] = null;
                    } else if (move.getStartX() == 4 && move.getStartY() == prevPawn.getY() - 1) {
                        board[4][move.getEndY()] = null;
                    } else if (move.getStartX() == 3 && move.getStartY() == prevPawn.getY() + 1) {
                        board[3][move.getEndY()] = null;
                    } else if (move.getStartX() == 3 && move.getStartY() == prevPawn.getY() - 1) {
                        board[3][move.getEndY()] = null;
                    }
                }
            }
        }
        board[move.getStartX()][move.getStartY()] = null;
        if (board[move.getEndX()][move.getEndY()] instanceof Pawn && board[move.getEndX()][move.getEndY()].firstMove == true) {
            prevPawn = board[move.getEndX()][move.getEndY()];
        } else {
            prevPawn = null;
        }
        //board[move.getEndX()][move.getEndY()].firstMove = false;
    }
    public void print(int x, int y) {
        List<Move> moves = board[x][y].getMoves(board);
        for (Move move : moves) {
            System.out.println("Start:[" + move.getStartX() + "," + move.getStartY() + "] to End:[" + move.getEndX() + "," + move.getEndY() + "]");
        }
    }
    public void revertMove(Move move) {
        board[move.getStartX()][move.getStartY()] = board[move.getEndX()][move.getEndY()];
        board[move.getStartX()][move.getStartY()].setLocation(move.getStartX(), move.getStartY());
        board[move.getEndX()][move.getEndY()] = holder;
       // board[move.getEndX()][move.getEndY()].setLocation(move.getEndX(), move.getEndY());
        holder = null;
    }
    public boolean getCheck(Move move, boolean turn) {
        //if in check and cancels check return true and move piece
        if (inCheck(turn)) {
            move(move);
            if (inCheck(turn)) {
                revertMove(move);
                return true;
            } else {
                board[move.getEndX()][move.getEndY()].firstMove = false;
                return false;
            }
        } else {
                move(move);
                board[move.getEndX()][move.getEndY()].firstMove = false;
        }
        return false;
    }
    private void findKings() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j] instanceof King && board[i][j].getColor() == ChessPiece.WHITE) {
                    whiteKing = board[i][j];
                }
                if (board[i][j] != null && board[i][j] instanceof King && board[i][j].getColor() == ChessPiece.BLACK) {
                    blackKing = board[i][j];
                }
            }
        }
    }
    public List<Move> getAI(boolean turn) {
        List<Move> moves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getColor() == turn) {
                    moves.addAll(board[i][j].getMoves(board));
                }
            }
        }
        return moves;
    }
    public List<Move> getAICheck(boolean turn) {
        ChessPiece temp = null;
        List<Move> possibilites;
        List<Move> output = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8;j++) {
                if (board[i][j] != null && board[i][j].getColor() == turn) {
                    possibilites = board[i][j].getMoves(board);
                    temp = null;
                    for (Move m: possibilites) {
                        if (checkMateCheck(m, turn)) {
                            if (checkMateSecondCheck(m, turn)) {
                                output.add(m);
                            }

                        }
                    }
                }
            }
        }
        return output;
    }
    public boolean madeCheck(boolean turn) {
        findKings();
        wkingx = whiteKing.getX();
        wkingy = whiteKing.getY();
        bkingx = blackKing.getX();
        bkingy = blackKing.getY();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getColor() != turn) {
                    List<Move> moves = board[i][j].getMoves(board);
                    for (Move move : moves) {
                        if (turn != ChessPiece.BLACK && move.getEndX() == wkingx) {
                            if (move.getEndY() == wkingy) {
                                return true;
                            }
                        }
                        if (turn != ChessPiece.WHITE && move.getEndX() == bkingx) {
                            if (move.getEndY() == bkingy) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public void isPromotion (Move move, boolean turn) {
        if (board[move.getEndX()][move.getEndY()] != null && board[move.getEndX()][move.getEndY()] instanceof Pawn) {
            if (move.getEndX() == 0) {
                board[move.getEndX()][move.getEndY()] = new Queen(ChessPiece.WHITE);
                board[move.getEndX()][move.getEndY()].setLocation(move.getEndX(), move.getEndY());
            } else if (move.getEndX() == 7) {
                board[move.getEndX()][move.getEndY()] = new Queen(ChessPiece.BLACK);
                board[move.getEndX()][move.getEndY()].setLocation(move.getEndX(), move.getEndY());
            }
        }
    }

    public boolean inCheck(boolean turn) {
        findKings();
        wkingx = whiteKing.getX();
        wkingy = whiteKing.getY();
        bkingx = blackKing.getX();
        bkingy = blackKing.getY();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getColor() != turn) {
                    List<Move> moves = board[i][j].getMoves(board);
                    for (Move move : moves) {
                        if (turn == ChessPiece.WHITE && move.getEndX() == wkingx) {
                            if (move.getEndY() == wkingy) {
                               // System.out.println("Check established " + move.getEndX() + ":" + move.getEndY());
                                return true;
                            }
                        }
                        if (turn == ChessPiece.BLACK && move.getEndX() == bkingx) {
                            if (move.getEndY() == bkingy) {
                                //System.out.println("Check established " + move.getEndX() + ":" + move.getEndY());
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean checkMate(boolean turn) {
        if (!inCheck(turn)) {
            return false;
        }
        ChessPiece temp = null;
        List<Move> possibilites;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8;j++) {
                if (board[i][j] != null && board[i][j].getColor() == turn) {
                    possibilites = board[i][j].getMoves(board);
                    temp = null;
                    for (Move m: possibilites) {
                        //System.out.println("Move: [" + m.getStartX() + "," + m.getStartY() + "] [" + m.getEndX() + "," + m.getEndY() + "]");
                        if (checkMateCheck(m, turn)) {
                            if (checkMateSecondCheck(m, turn)) {
                                System.out.println("Move found: ["+ m.getStartX() + "," + m.getStartY() + "] [" + m.getEndX() + "," + m.getEndY() + "]");
                                return false;
                            }

                        }
                    }
                }
            }
        }
        return true;
    }
    public boolean checkMateCheck(Move move, boolean turn) {
        move(move);
        if (inCheck(turn)) {
            revertMove(move);
            return false;
        } else {
            revertMove(move);
            return true;
        }
    }
    public boolean checkMateSecondCheck(Move move, boolean turn) {
        move(move);
        if (madeCheck(turn)) {
            revertMove(move);
            return false;
        }
        revertMove(move);
        return true;
    }

}
