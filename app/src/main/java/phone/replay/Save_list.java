package phone.replay;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import phone.androidchess84.R;
import phone.androidchess84.*;

public class Save_list extends AppCompatActivity {
    private GameLibrary games = new GameLibrary();
    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_list);
        //GameLibrary.save(this, games);
        //games = GameLibrary.read(this);
        this.context = this;
        games = new GameLibrary(context);
        //games.write(this);
        List<Game> gameList = games.getGames();
        List<String> stringList = new ArrayList<>();

        for (Game g : gameList) {
            stringList.add(g.toString());
        }
       // stringList.add("test");
       // stringList.add("test2");
        //games.replace(gameList);
        //GameLibrary.save(this, games);
        ListView replaylist = (ListView) findViewById(R.id.replaylist);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simplelayout, stringList);
        replaylist.setAdapter(adapter);
        replaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                replay(position);
            }
        });
    }
    public void replay(int position) {
        Intent intent = new Intent(Save_list.this, Replay.class);
        intent.putExtra(null, position);
        startActivity(intent);
    }
    public void back(View view) {
        startActivity(new Intent(Save_list.this, Chess.class));
    }
    public void datesort(View view) {
        List<Game> gameslist = games.getGames();
        Collections.sort(gameslist, Game.GaDateComparator);
        List<String> stringlist = new ArrayList<>();
        for (Game g: gameslist) {
            stringlist.add(g.toString());
        }
        //games.replace(gameslist);
       // GameLibrary.save(context, games);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.simplelayout, stringlist);
        ListView replaylist = (ListView) findViewById(R.id.replaylist);
        replaylist.setAdapter(adapter);
        replaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                replay(position);
            }
        });
    }
    public void titlesort(View view) {
        List<Game> gameslist = games.getGames();
        Collections.sort(gameslist, Game.GaTitleComparator);
        List<String> stringlist = new ArrayList<>();
        for (Game g: gameslist) {
            stringlist.add(g.toString());
        }
        //games.replace(gameslist);
        //GameLibrary.save(context, games);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.simplelayout, stringlist);
        ListView replaylist = (ListView) findViewById(R.id.replaylist);
        replaylist.setAdapter(adapter);
        replaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                replay(position);
            }
        });
    }
}
