package au.edu.jcu.cp3406.utilityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    //TODO: create DisplayConvertedActivity to show the conversions from ChooseActivity
    public static String UNIT_TO_COVERT_TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClickTemperature(View view) {
        Button button = findViewById(R.id.temperatureButton);
        UNIT_TO_COVERT_TYPE = button.getText().toString();
        Intent intent = new Intent(this, ChooseActivity.class);
        intent.putExtra("unit", UNIT_TO_COVERT_TYPE.toLowerCase());
        intent.putExtra("arrayName", "temperatures");
        startActivity(intent);
    }

    public void onClickWeight(View view) {
        Button button = findViewById(R.id.weightButton);
        UNIT_TO_COVERT_TYPE = button.getText().toString();
        Intent intent = new Intent(this, ChooseActivity.class);
        intent.putExtra("unit", UNIT_TO_COVERT_TYPE.toLowerCase());
        intent.putExtra("arrayName", "weights");
        startActivity(intent);
    }

    public void onClickDistance(View view) {
        Button button = findViewById(R.id.distanceButton);
        UNIT_TO_COVERT_TYPE = button.getText().toString();
        Intent intent = new Intent(this, ChooseActivity.class);
        intent.putExtra("unit", UNIT_TO_COVERT_TYPE.toLowerCase());
        intent.putExtra("arrayName", "distances");
        startActivity(intent);
    }

    public void onClickSettings() {
        //TODO: create a settings screen, possibly for changing font sizes (make variable to be passed through to all activities to set font size
    }
}
