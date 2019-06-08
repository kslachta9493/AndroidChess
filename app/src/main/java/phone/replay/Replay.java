package phone.replay;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import phone.androidchess84.R;
import phone.androidchess84.*;

public class Replay extends AppCompatActivity {

    private GridView replayboard;
    private AdapterReplay adapter;
    private Game game;
    private int position;
    public static ChessBoard board;
    private GameLibrary games;
    public static Context context;
    public boolean turn = ChessPiece.WHITE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay);
        this.context = this;
        position = getIntent().getIntExtra(null, -1);
        games = new GameLibrary(context);
        //games = GameLibrary.read(this);

        List<Game> gamelist = new ArrayList<>();
        gamelist = games.getGames();
        game = gamelist.get(position);
        board = game.nextMove();
        update();
    }
    public void update() {
        replayboard = (GridView) findViewById(R.id.replayboard);
        adapter = new AdapterReplay(this);
        replayboard.setAdapter(adapter);
    }
    public void back(View view) {
        startActivity(new Intent(Replay.this, Save_list.class));
    }
    public void next(View view) {
        if (!game.peek()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("End of replay!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();
        } else {
            board = game.nextMove();
            TextView turnlabel = (TextView) findViewById(R.id.replayturn);
            if (turn)
                turnlabel.setText("White's Turn");
            else
                turnlabel.setText("Black's Turn");
            turn = !turn;
            adapter.notifyDataSetChanged();
        }
    }
    public void prev(View view){
        if (!game.isStart()) {
            board = game.prevMove();
            TextView turnlabel = (TextView) findViewById(R.id.replayturn);
            if (turn)
                turnlabel.setText("White's Turn");
            else
                turnlabel.setText("Black's Turn");
            turn = !turn;
            adapter.notifyDataSetChanged();
        }
    }
    public void delete(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Deleting Replay!");
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                games.getGames().remove(position);
                games.write(context);
                startActivity(new Intent(Replay.this, Save_list.class));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

}
