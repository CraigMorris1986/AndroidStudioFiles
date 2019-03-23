package au.edu.jcu.cp3406.utilityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {
    int FONT_SIZE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    public void onClickAcceptSettingsButton(View view) {
        RadioButton smallFont = findViewById(R.id.smallButton);
        RadioButton mediumFont = findViewById(R.id.mediumButton);
        RadioButton largeFont = findViewById(R.id.largeButton);

        if (smallFont.isChecked()) {
            FONT_SIZE = 15;
        } else if (mediumFont.isChecked()) {
            FONT_SIZE = 20;
        } else if (largeFont.isChecked()) {
            FONT_SIZE = 25;
        }

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("fontSize", FONT_SIZE);
        startActivity(intent);
    }
}
