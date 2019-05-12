package au.edu.jcu.cp3406.educationapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class FinishGame extends AppCompatActivity {
    private SQLiteDatabase db;
    int score;
    String userName;
    int difficulty;
    private boolean soundIsOn;
    MediaPlayer sound;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_game);
        checkForSettingsIntent();
        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);

        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();
        Log.i("tag", db.toString());

        TextView scoreDisplay = findViewById(R.id.finishText);
        scoreDisplay.setText(String.format("Good work!\nYour score is %d", score));
    }

    /**
     * Method to handle the onClick events for the Activities Button objects via a switch statement that
     * checks the Button objects text value. On a Button text value of "save score" this method will
     * take the users name from the EditText object and the users score from the GameActivity and save it
     * to the SQLite database. This method also checks if the user did not enter a name and assigns "Nemo"
     * as the default.
     * @param view takes a view object as a parameter
     */
    public void onClickFinishActivity(View view) {
        Intent intent;
        int clickedButtonID = view.getId();
        Button clickedButton = findViewById(clickedButtonID);
        Animation animate =  AnimationUtils.loadAnimation(this, R.anim.bounce);
        clickedButton.startAnimation(animate);
        String buttonText = clickedButton.getText().toString().toLowerCase();
        switch (buttonText) {
            case "play again":
                intent = new Intent(this, GameActivity.class);
                intent.putExtra("difficulty", difficulty);
                intent.putExtra("soundIsOn", soundIsOn);
                playSound(this, R.raw.start);
                startActivity(intent);
                break;
            case "main menu":
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("difficulty", difficulty);
                intent.putExtra("soundIsOn", soundIsOn);
                startActivity(intent);
                break;
            case "tweet":
                String textToSend = String.format("I just scored %s in the Education App", Integer.valueOf(score).toString());
                Intent twitterIntent = new Intent(Intent.ACTION_SEND);
                twitterIntent.putExtra(Intent.EXTRA_TEXT, textToSend);
                twitterIntent.setType("text/plain");
                PackageManager packManager = getPackageManager();
                List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(twitterIntent, PackageManager.MATCH_DEFAULT_ONLY);

                boolean isResolved = false;
                for (ResolveInfo resolveInfo : resolvedInfoList) {
                    if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
                        twitterIntent.setClassName(
                                resolveInfo.activityInfo.packageName,
                                resolveInfo.activityInfo.name);
                        isResolved = true;
                        break;
                    }
                }
                if (isResolved) {
                    startActivity(twitterIntent);
                } else {
                    Intent i = new Intent();
                    i.putExtra(Intent.EXTRA_TEXT, textToSend);
                    i.setAction(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://twitter.com/intent/tweet?text=" + urlEncode(textToSend)));
                    startActivity(i);
                    Toast.makeText(this, "Twitter app isn't found", Toast.LENGTH_LONG).show();
                }
                playSound(this, R.raw.tweet);
                break;
            case "save score":
                EditText userInput = findViewById(R.id.editText);
                userName = userInput.getText().toString().trim();
                if (userName.equals("")) {
                    userName = "Nemo";
                }
                ContentValues values = new ContentValues();
                values.put("NAME", userName);
                values.put("SCORE", score);
                Log.i("tag", values.toString());
                db.insert("SCORES", null, values);
                Toast toast = Toast.makeText(this, "Score save successful!", Toast.LENGTH_SHORT);
                toast.show();
        }

    }

    private void checkForSettingsIntent() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            Intent settingsIntent = getIntent();
            difficulty = settingsIntent.getIntExtra("difficulty", 2);
            soundIsOn = settingsIntent.getBooleanExtra("soundIsOn", true);
        } else {
            difficulty = 2;
            soundIsOn = true;
        }
    }

    private String urlEncode(String textToEncode) {
        try {
            return URLEncoder.encode(textToEncode, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.i("tag", "UTF-8 should always be supported", e);
            return "";
        }
    }
    /**
     * Method assigns a MediaPlayer object to the class sound attribute and plays a sound if the class
     * attribute soundIsOn is set to true.
     * @param context takes the activity Context object for the MediaPlayer create() method
     * @param soundID takes the resource int ID for the sound file from R.raw.soundfile
     */
    private void playSound(Context context, int soundID) {
        if (soundIsOn) {
            sound = MediaPlayer.create(context, soundID);
            sound.start();
        }
    }

    /**
     * Class onDestroy method to close the SQLite database when the Activity is finished.
     */
    @Override
    public void onDestroy(){
        super.onDestroy();
        db.close();
    }


}
