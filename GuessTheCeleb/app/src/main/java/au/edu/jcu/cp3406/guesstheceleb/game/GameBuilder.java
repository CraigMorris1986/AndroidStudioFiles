package au.edu.jcu.cp3406.guesstheceleb.game;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ContextThemeWrapper;

import java.io.IOException;
import java.io.InputStream;

import au.edu.jcu.cp3406.guesstheceleb.MainActivity;

public class GameBuilder {
//    public static int EASY = 1;
    MainActivity mainActivity = new MainActivity();
    Context context = mainActivity.getApplicationContext();
    AssetManager manager = context.getAssets();
    CelebrityManager celebrityManager;
    public class Difficulty {
       public int EASY = 1;
       public int MEDIUM = 2;
       public int HARD = 3;
    }


    public GameBuilder(CelebrityManager celebrityManager) {
        this.celebrityManager = celebrityManager;
    }

    public Game create(int difficulty) {
        Question[] questions = new Question[difficulty];
        String[] possibleNames = new String[difficulty];
        try {
            String[] names = manager.list("celebs");
//            CelebrityManager celebrityManager = new CelebrityManager(manager, "/assets/celebs");
            for (int i = 0; i < difficulty; ++i) {
                possibleNames[i] = celebrityManager.getName(i);
            }
            for (int i = 0; i < difficulty ; ++i) {
                questions[i] = new Question(celebrityManager.getName(i), celebrityManager.get(i), names);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Game game = new Game(questions);
        return game;
    }
}
