package au.edu.jcu.cp3406.stopwatchapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void acceptClicked(View view) {
        EditText editText = findViewById(R.id.editText);
        String speed = editText.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        // destination of intent object
        intent.putExtra("speedString", speed);
        //stored memory values within the intent object
        startActivity(intent);
    }
}
