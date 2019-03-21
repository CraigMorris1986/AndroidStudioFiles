package au.edu.jcu.cp3406.utilityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        Intent intent = getIntent();
        TextView textView = findViewById(R.id.chooseText);
        String formattedText = String.format("Please select the %s unit to convert", intent.getStringExtra("unit"));
        textView.setText(formattedText);
        //above code sets the ChooseActivity text display to reflect the button pushed (eg temperature, weight, distance)

        arraySelection();

        //TODO: create method to retrieve spinner selection to pass into custom Converter Class to handle conversions
        //TODO: add savedInstanceState



    }

    /**
     * Method gets Intent object from HomeActivity and unpacks the arrayName String variable to set
     * the spinner values during the Activity onCreate method.
     */
    private void arraySelection() {
        Intent intent = getIntent();
        String arrayName = intent.getStringExtra("arrayName");
        Spinner spinner = findViewById(R.id.spinner);
        switch (arrayName) {
            case "temperatures": {
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.temperatures, android.R.layout.simple_spinner_item);
                spinner.setAdapter(adapter);
                break;
            }
            case "weights": {
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.weights, android.R.layout.simple_spinner_item);
                spinner.setAdapter(adapter);
                break;
            }
            case "distances": {
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.distances, android.R.layout.simple_spinner_item);
                spinner.setAdapter(adapter);
                break;
            }
        }

    }

    public void onClickConvertButton(View view) {
        Intent intent = new Intent(this, DisplayConversionActivity.class);
        EditText editText = findViewById(R.id.userInput);
        String userInputString = editText.getText().toString();


        Spinner spinner = findViewById(R.id.spinner);
        String userChoice = String.valueOf(spinner.getSelectedItem());

        intent.putExtra("userString", userInputString);
        intent.putExtra("userChoice", userChoice);

        startActivity(intent);
    }


}

