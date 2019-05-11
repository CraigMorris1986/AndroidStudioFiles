package au.edu.jcu.cp3406.educationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
    }

    public void onClickScoresActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
