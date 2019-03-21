package au.edu.jcu.cp3406.utilityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayConversionActivity extends AppCompatActivity {
    Converter converter = new Converter();
    String UNITS_TO_CONVERT;
    String DISPLAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_conversion);
        Intent intent = getIntent();
        String recievedInput = intent.getStringExtra("userString");
        String recievedChoice = intent.getStringExtra("userChoice");
        UNITS_TO_CONVERT = recievedInput;
        convertFromChoice(recievedChoice);
    }

    public void onClickReturnHome(View view) {
        Intent intentHome = new Intent(this, HomeActivity.class);
        startActivity(intentHome);
    }

    public void convertFromChoice(String choice) {
        TextView textView = findViewById(R.id.convertedText);
        if (choice.equals("Celsius")) {
            DISPLAY = converter.convertCelsius(UNITS_TO_CONVERT);
        }
        if (choice.equals("Fahrenheit")) {
            DISPLAY = converter.convertFarenheit(UNITS_TO_CONVERT);
        }
        if (choice.equals("Kelvin")) {
            DISPLAY = converter.convertKelvin(UNITS_TO_CONVERT);
        }
        if (choice.equals("Kilogram")) {
            DISPLAY = converter.convertKilogram(UNITS_TO_CONVERT);
        }
        if (choice.equals("Pound")) {
            DISPLAY = converter.convertPound(UNITS_TO_CONVERT);
        }
        if (choice.equals("Newton")) {
            DISPLAY = converter.convertNewton(UNITS_TO_CONVERT);
        }
        textView.setText(DISPLAY);
    }
}
