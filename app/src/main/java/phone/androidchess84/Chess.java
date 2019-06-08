package phone.androidchess84;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import phone.replay.Save_list;

public class Chess extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        Button newgame = (Button) findViewById(R.id.newgame);

        newgame.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                startActivity(new Intent(Chess.this, ChessGame.class));
            }
        });
        Button savedgames = (Button) findViewById(R.id.savedgames);
        savedgames.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                startActivity(new Intent(Chess.this, Save_list.class));
            }
        });
    }

    public void newGame(View view){

    }
    public void savedGames(View view) {

    }
}
