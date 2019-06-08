package phone.androidchess84;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import phone.pieces.Move;
import phone.pieces.*;
import phone.replay.*;

public class ChessGame extends AppCompatActivity {
    public static ChessBoard board;
    public static ChessBoard holder;
    public static Context context;
    private GameLibrary games = new GameLibrary();
    private boolean canUndo = false;
    private String m_Text = "";
    private GridView gridboard;
    private Adapter adapter;
    private boolean checkmate = false;
    private ArrayList<ChessBoard> chessBoard= new ArrayList<>();
    private int pos = 0;
    private Location start = null;
    private Location end = null;
    private View prev;
    private boolean turn = ChessPiece.WHITE;
    private ArrayList<Location> begIndex = new ArrayList<>();
    private ArrayList<Location> endIndex = new ArrayList<>();
    private Location startUndo = null;
    private Location endUndo = null;
    private int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_game);
        context = this;
        //games = GameLibrary.read(this);
        games = new GameLibrary(context);
        setupBoard();
        drawBoard();
    }
    public void undo(View view){
        if (chessBoard.size() > 1) {
            if (!canUndo)
                return;
            board = chessBoard.get(chessBoard.size() - 2);
            chessBoard.remove(chessBoard.size() - 1);
            pos--;
            turn = !turn;
            TextView tv1 = (TextView)findViewById(R.id.replayturn);
            if (board.board[startUndo.row][startUndo.column] != null)
                board.board[startUndo.row][startUndo.column].setLocation(startUndo.row, startUndo.column);
            if (board.board[endUndo.row][endUndo.column] != null)
                board.board[endUndo.row][endUndo.column].setLocation(endUndo.row, endUndo.column);

            if (board.board[startUndo.row][startUndo.column] instanceof Pawn && board.board[startUndo.row][startUndo.column].getColor() == ChessPiece.WHITE && startUndo.row == 6) {
                board.board[startUndo.row][startUndo.column].firstMove = true;
            }
            if (board.board[startUndo.row][startUndo.column] instanceof Pawn && board.board[startUndo.row][startUndo.column].getColor() == ChessPiece.BLACK && startUndo.row == 1) {
                board.board[startUndo.row][startUndo.column].firstMove = true;
            }
            if (board.board[startUndo.row][startUndo.column] instanceof King && board.board[startUndo.row][startUndo.column].getColor() == ChessPiece.WHITE && startUndo.row == 7 && startUndo.column == 4) {
                board.board[startUndo.row][startUndo.column].firstMove = true;
            }
            if (board.board[startUndo.row][startUndo.column] instanceof King && board.board[startUndo.row][startUndo.column].getColor() == ChessPiece.BLACK && startUndo.row == 0 && startUndo.column == 4) {
                board.board[startUndo.row][startUndo.column].firstMove = true;
            }

            if (turn){
                tv1.setText("White's Turn");
            }
            else{
                tv1.setText("Black's Turn");
            }
            adapter.notifyDataSetChanged();
            canUndo = false;
        }
    }
    public void draw(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (turn == ChessPiece.WHITE)
            builder.setMessage("Does Black wish to draw?");
        else
            builder.setMessage("Does White wish to draw?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog.Builder draw = new AlertDialog.Builder(context);
                draw.setMessage("Draw!");
                draw.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (chessBoard.size() == 1) {
                            startActivity(new Intent(ChessGame.this, Chess.class));
                            return;
                        }
                        TextView turnlabel = (TextView) findViewById(R.id.replayturn);
                        turnlabel.setText("Draw!");
                        //ADD popup for naming
                        AlertDialog.Builder name = new AlertDialog.Builder(context);
                        name.setTitle("Save File");
                        final EditText input = new EditText(context);
                        input.setInputType(InputType.TYPE_CLASS_TEXT);
                        name.setView(input);
                        name.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                m_Text = input.getText().toString();
                                Game game = new Game(chessBoard, m_Text);
                                games.addGame(game);
                                //GameLibrary.save(context, games);
                                games.write(context);
                                startActivity(new Intent(ChessGame.this, Chess.class));
                            }
                        });
                        name.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                startActivity(new Intent(ChessGame.this, Chess.class));
                            }
                        });
                        name.show();
                    }
                });
                draw.show();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    public void ai(View view){

        // TextView turnlabel = (TextView) findViewById(R.id.turn);
        List<Move> moves;
        if (board.madeCheck(turn)) {
            moves = board.getAICheck(turn);
        } else {
            moves = board.getAI(turn);
        }
        Random ran = new Random();
        if (moves.size() != 0) {
            int random = ran.nextInt(moves.size());
            int sx = moves.get(random).getStartX();
            int sy = moves.get(random).getStartY();
            int ex = moves.get(random).getEndX();
            int ey = moves.get(random).getEndY();
            start = new Location(sx, sy);
            end = new Location(ex, ey);
            update();
        }
    }
    public void findAi() {

    }

    public void resign(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (turn == ChessPiece.WHITE)
            builder.setMessage("Does White wish to resign?");
        else
            builder.setMessage("Does Black wish to resign?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ADD popup for naming
                TextView turnlabel = (TextView) findViewById(R.id.replayturn);
                if (turn == ChessPiece.WHITE)
                    turnlabel.setText("Black Wins!");
                else
                    turnlabel.setText("White Wins!");
                if (chessBoard.size() == 1) {
                    startActivity(new Intent(ChessGame.this, Chess.class));
                    return;
                }
                AlertDialog.Builder name = new AlertDialog.Builder(context);
                name.setTitle("Save replay?");
                final EditText input = new EditText(context);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                name.setView(input);
                name.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        Game game = new Game(chessBoard, m_Text);
                        games.addGame(game);
                        //GameLibrary.save(context, games);
                        games.write(context);
                        startActivity(new Intent(ChessGame.this, Chess.class));
                    }
                });
                name.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        startActivity(new Intent(ChessGame.this, Chess.class));
                    }
                });
                name.show();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void drawBoard() {
        gridboard = (GridView) findViewById(R.id.replayboard);
        adapter = new Adapter(this);
        gridboard.setAdapter(adapter);
        chessBoard.add(pos, board);
        gridboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ChessBoard holder = new ChessBoard(board);

                if (start == null) {
                    start = new Location(position /8, position % 8);
                    //System.out.println("POS:" + position);
                    view.setBackgroundColor(Color.YELLOW);
                    prev = view;
                } else {
                    end = new Location(position / 8, position % 8);

                    update();
                }
            }
        });

        gridboard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //gridboard.getOnItemClickListener().onItemClick(null, v, 20, 20);
                        position = gridboard.pointToPosition((int)event.getX(), (int)event.getY());
                       // start = new Location (position / 8, position % 8);
                        //gridboard.getChildAt(position).setBackgroundColor(Color.YELLOW);
                        //System.out.println("Started + " + gridboard.pointToPosition((int)event.getX(), (int)event.getY()));

                       gridboard.getOnItemClickListener().onItemClick(null, gridboard.getChildAt(position), gridboard.pointToPosition((int)event.getX(), (int)event.getY()), 0);

                        return true;
                    case MotionEvent.ACTION_UP:
                       // System.out.println("ended + " + gridboard.pointToPosition((int)event.getX(), (int)event.getY()));
                        if (position != gridboard.pointToPosition((int)event.getX(), (int)event.getY())) {
                            position = gridboard.pointToPosition((int)event.getX(), (int)event.getY());
                            gridboard.getOnItemClickListener().onItemClick(null, gridboard.getChildAt(position), gridboard.pointToPosition((int)event.getX(), (int)event.getY()), 0);
                        }

                        return true;
                }
                return true;
            }
        });
    }
    public void update() {

        ChessBoard holder = new ChessBoard(board);
        TextView turnlabel = (TextView) findViewById(R.id.replayturn);
        if (prev != null)
            prev.setBackgroundColor(Color.TRANSPARENT);
        if (updateBoard(start.row, start.column, end.row, end.column)) {
            canUndo = true;
            chessBoard.add(pos, holder);
            holder = null;
            pos++;
            startUndo = start;
            endUndo = end;
            adapter.notifyDataSetChanged();
            turn = !turn;
            if (checkMate()) {
                System.out.println("CheckMate");
                checkmate = true;
                if (turn == ChessPiece.WHITE)
                    turnlabel.setText("Checkmate! Black Wins!");
                else
                    turnlabel.setText("Checkmate! White Wins!");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Save replay?");
                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        Game game = new Game(chessBoard, m_Text);
                        games.addGame(game);
                        //GameLibrary.save(context, games);
                        games.write(context);
                        startActivity(new Intent(ChessGame.this, Chess.class));
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        startActivity(new Intent(ChessGame.this, Chess.class));
                    }
                });

                builder.show();
            }
        }
        start = null;
        end = null;
        if (turn)
            turnlabel.setText("White's Turn");
        else
            turnlabel.setText("Black's Turn");
        if (board.madeCheck(turn)) {
            if (turn)
                turnlabel.setText("Check! White's Turn");
            else
                turnlabel.setText("Check! Black's Turn");
        }
        if (checkmate) {
            if (turn)
                turnlabel.setText("Checkmate! Black Wins");
            else
                turnlabel.setText("Checkmate! White Wins");

        }
    }

    public boolean updateBoard(int startx, int starty, int endx, int endy) {
        Move move = new Move(startx, starty, endx, endy);
        if (board.canMove(move, turn)) {
            if (board.getCheck(move, turn)) {
                return false;
            }
            if (board.putInCheck(move, turn)) {
                return false;
            }
            board.isPromotion(move, turn);
            return true;
        }
        return false;
    }
    public boolean checkMate() {
        if (board.checkMate(turn)) {
            return true;
        }
        return false;
    }
    public void setupBoard() {
        board = new ChessBoard();
    }
}
