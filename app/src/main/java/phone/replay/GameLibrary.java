package phone.replay;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameLibrary implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Game> games = new ArrayList<>();
    private static String fileloc = "replay";
    private static String fileloc2 = "replays";
    public GameLibrary() {
        //this.games = new ArrayList<>();
    }
    public GameLibrary(Context context) {
        populate(context);
    }
    public void addGame(Game game) {
        games.add(game);
    }
    public void removeGame(Game game) {
        games.remove(game);
    }
    public void replace(List<Game> games) {
        this.games = games;
    }
    public List<Game> getGames() {
        return games;
    }
    /*
    public static void save(Context context, GameLibrary gameList) {
        try {
            FileOutputStream fs = context.openFileOutput(fileloc, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(gameList);
            os.close();
            fs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static GameLibrary read(Context context) {
        try {
            FileInputStream fs = context.openFileInput(fileloc);
            ObjectInputStream os = new ObjectInputStream(fs);
            GameLibrary gamelist = (GameLibrary) os.readObject();
            os.close();
            fs.close();
            return gamelist;
        } catch (Exception e) {
            System.out.println("YOU U");
            e.printStackTrace();
        }
        return null;
    }
    */
    public void write(Context context) {
        try {
            FileOutputStream fs = context.openFileOutput(fileloc2, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(games);
            os.close();
            fs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void populate(Context context) {
        try {
            FileInputStream fs = context.openFileInput(fileloc2);
            ObjectInputStream os = new ObjectInputStream(fs);
            games = (List<Game>) os.readObject();
            os.close();
            fs.close();
        } catch (Exception e) {
            System.out.println("YOU U");
            e.printStackTrace();
        }
    }


}
