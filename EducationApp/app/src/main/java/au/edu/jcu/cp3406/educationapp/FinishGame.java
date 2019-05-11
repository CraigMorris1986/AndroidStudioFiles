package au.edu.jcu.cp3406.educationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinishGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_game);
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
