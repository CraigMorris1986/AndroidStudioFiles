package au.edu.jcu.cp3406.educationapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private int difficulty;
    private boolean soundIsOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkForSettingsIntent();
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

    /**
     * Method for main menu navigation by handling Button objects onClick events by checking for
     * the buttons text value in a switch statement.
     * @param view take the activity view object as a parameter
     */
    public void onClickMainActivity(View view) {
        Intent intent;
        int clickedButtonID = view.getId();
        Button clickedButton = findViewById(clickedButtonID);
        String buttonText = clickedButton.getText().toString().toLowerCase();
        Animation animate = AnimationUtils.loadAnimation(this, R.anim.bounce);
        clickedButton.startAnimation(animate);
        switch (buttonText) {
            case "play":
                intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("difficulty", difficulty);
                intent.putExtra("soundIsOn", soundIsOn);
                if (soundIsOn) {
                    MediaPlayer sound = MediaPlayer.create(this, R.raw.start);
                    sound.start();
                }
                startActivity(intent);
                break;
            case "scores":
                intent = new Intent(MainActivity.this, ScoresActivity.class);
                startActivity(intent);
                break;
            case "settings":
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
            case "quit":
                this.finishAffinity();
                break;
        }
    }
}
