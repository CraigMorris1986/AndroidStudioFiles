package au.edu.jcu.cp3406.educationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishGame extends AppCompatActivity {
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_game);
        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        TextView scoreDisplay = findViewById(R.id.finishText);
        scoreDisplay.setText(String.format("Good work!\nYour score is %d", score));
    }

    public void onClickFinishActivity(View view) {
        Intent intent;
        int clickedButtonID = view.getId();
        Button clickedButton = findViewById(clickedButtonID);
        String buttonText = clickedButton.getText().toString().toLowerCase();
        switch (buttonText) {
            case "play again":
                intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                break;
            case "main menu":
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
