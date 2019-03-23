package au.edu.jcu.cp3406.utilityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    public static String UNIT_TO_COVERT_TYPE;
    int FONT_SIZE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent settingsIntent = getIntent();
        if (settingsIntent.getExtras() != null) {
           FONT_SIZE = settingsIntent.getIntExtra("fontSize", 20);
        }
        setFontSize();

    }

    public void onClickTemperature(View view) {
        Button button = findViewById(R.id.temperatureButton);
        UNIT_TO_COVERT_TYPE = button.getText().toString();
        Intent intent = new Intent(this, ChooseActivity.class);
        intent.putExtra("unit", UNIT_TO_COVERT_TYPE.toLowerCase());
        intent.putExtra("arrayName", "temperatures");
        intent.putExtra("fontSize", FONT_SIZE);
        startActivity(intent);
    }

    public void onClickWeight(View view) {
        Button button = findViewById(R.id.weightButton);
        UNIT_TO_COVERT_TYPE = button.getText().toString();
        Intent intent = new Intent(this, ChooseActivity.class);
        intent.putExtra("unit", UNIT_TO_COVERT_TYPE.toLowerCase());
        intent.putExtra("arrayName", "weights");
        intent.putExtra("fontSize", FONT_SIZE);
        startActivity(intent);
    }

    public void onClickDistance(View view) {
        Button button = findViewById(R.id.distanceButton);
        UNIT_TO_COVERT_TYPE = button.getText().toString();
        Intent intent = new Intent(this, ChooseActivity.class);
        intent.putExtra("unit", UNIT_TO_COVERT_TYPE.toLowerCase());
        intent.putExtra("arrayName", "distances");
        intent.putExtra("fontSize", FONT_SIZE);
        startActivity(intent);
    }


    public void onClickSettings(View view) {
        //TODO: create a settings screen, possibly for changing font sizes (make variable to be passed through to all activities to set font size
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void setFontSize() {
        TextView textView = findViewById(R.id.welcomeText);
        Button temperatureButton = findViewById(R.id.temperatureButton);
        Button weightButton = findViewById(R.id.weightButton);
        Button distanceButton = findViewById(R.id.distanceButton);
        Button settingsButton = findViewById(R.id.settingsButton);

        textView.setTextSize((float) (FONT_SIZE * 1.5));
        temperatureButton.setTextSize(FONT_SIZE);
        weightButton.setTextSize(FONT_SIZE);
        distanceButton.setTextSize(FONT_SIZE);
        settingsButton.setTextSize(FONT_SIZE);
    }
}
