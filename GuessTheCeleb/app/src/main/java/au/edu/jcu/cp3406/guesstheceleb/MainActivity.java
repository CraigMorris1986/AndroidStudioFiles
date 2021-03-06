package au.edu.jcu.cp3406.guesstheceleb;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements StateListener {
    private GameFragment gameFragment;
    private StatusFragment statusFragment;
    private QuestionFragment questionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        AssetManager manager = getAssets();
//        {
//            try {
//                String[] names = manager.list("celebs");
//                System.out.println(Arrays.toString(names));
//                ImageView imageView = findViewById(R.id.image);
//                InputStream stream = manager.open("celebs/" + names[2]);
//                Bitmap bitmap = BitmapFactory.decodeStream(stream);
//                imageView.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                System.out.println("failed to get names");
//            }
//        }
//        CelebrityManager imageManager = new CelebrityManager(this.getAssets(), "celebs");
//        ImageView imageView = findViewById(R.id.image);
//        imageView.setImageBitmap(imageManager.get(2))

        FragmentManager fragmentManager = getSupportFragmentManager();
        gameFragment = (GameFragment) fragmentManager.findFragmentById(R.id.game);
        statusFragment = (StatusFragment) fragmentManager.findFragmentById(R.id.status);
        questionFragment = (QuestionFragment) fragmentManager.findFragmentById(R.id.question);
//        isLargeScreen = statusFragment != null;
    }


    @Override
    public void onUpdate(State state) {
        String text = String.format(Locale.getDefault(), "state: {}", state);
        Log.i("MainActivity", text);
    }
}
