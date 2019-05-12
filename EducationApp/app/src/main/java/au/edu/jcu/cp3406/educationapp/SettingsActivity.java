package au.edu.jcu.cp3406.educationapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private int difficulty;
    private boolean soundIsOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Switch soundSwitch = findViewById(R.id.soundSwitch);
        soundSwitch.setOnCheckedChangeListener(this);
    }

    public void onClickSettingsActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        // TODO: add code to pass setting to other activities
        difficulty = getDifficulty();
        intent.putExtra("difficulty", difficulty);
        intent.putExtra("soundIsOn", soundIsOn);
        startActivity(intent);
    }

    private int getDifficulty() {
        int chosenDifficulty = 1;
        RadioGroup difficultyRadioGroup = findViewById(R.id.radioGroup);
        RadioButton difficultyRadioButton = findViewById(difficultyRadioGroup.getCheckedRadioButtonId());
        switch (difficultyRadioButton.getText().toString().toLowerCase()) {
            case "easy":
                chosenDifficulty = 1;
                break;
            case "standard":
                chosenDifficulty = 2;
                break;
            case "hard":
                chosenDifficulty = 3;
                break;
        }
        return chosenDifficulty;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        soundIsOn = isChecked;
        if (soundIsOn) {
            MediaPlayer sound = MediaPlayer.create(this, R.raw.success);
            sound.start();
        }
    }
}
