package au.edu.jcu.cp3406.educationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void onClickGameActivityNav(View view) {
        Intent intent;
        int clickedButtonID = view.getId();
        Button clickedButton = findViewById(clickedButtonID);
        String buttonText = clickedButton.getText().toString().toLowerCase();
        switch (buttonText) {
            case "leave game":
                intent = new Intent(this, MainActivity.class);
                //TODO: add Toast popup to show game was left early
                startActivity(intent);
                break;
            case "skip":
                //TODO: add code to skip the question
                //TODO: add Toast popup to show the question was skipped
                break;
        }
    }
}