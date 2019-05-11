package au.edu.jcu.cp3406.educationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onClickSettingsActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        // TODO: add code to pass setting to other activities
        startActivity(intent);
    }
}
