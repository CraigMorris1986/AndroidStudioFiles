package au.edu.jcu.cp3406.educationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMainActivity(View view) {
        Intent intent;
        int clickedButtonID = view.getId();
        Button clickedButton = findViewById(clickedButtonID);
        String buttonText = clickedButton.getText().toString().toLowerCase();
        switch (buttonText) {
            case "play":
                intent = new Intent(MainActivity.this, GameActivity.class);
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
