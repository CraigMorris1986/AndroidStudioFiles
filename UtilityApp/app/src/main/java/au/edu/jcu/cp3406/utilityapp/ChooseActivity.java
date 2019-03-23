package au.edu.jcu.cp3406.utilityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ChooseActivity extends AppCompatActivity {
    int FONT_SIZE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        Intent intent = getIntent();
        final Button button = findViewById(R.id.convertButton);
        TextView textView = findViewById(R.id.chooseText);
        EditText editText = findViewById(R.id.userInput);
        String formattedText = String.format("Please select the %s unit to convert", intent.getStringExtra("unit"));
        textView.setText(formattedText);
        //above code sets the ChooseActivity text display to reflect the button pushed (eg temperature, weight, distance)
        Intent homeIntent = getIntent();
        FONT_SIZE = homeIntent.getIntExtra("fontSize", 20);
        setFontSize();

        arraySelection();


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            /**
             * actionListener currently works for physical keyboard enter but not softkey enter, it is a known issue.
             */
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)){
                    button.performClick();
                }
                return false;
            }
        });

        if (savedInstanceState != null) {
            editText.setText(savedInstanceState.getString("editTextValue"));
        } //saved Instance state for the edit text value.

    }

    @Override
    protected void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);
        EditText editText = findViewById(R.id.userInput);
        outstate.putString("editTextValue", editText.getText().toString());

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
        intent.putExtra("fontSize", FONT_SIZE);

        startActivity(intent);
    }

    public void setFontSize() {
        TextView textView = findViewById(R.id.chooseText);
        Button convertButton = findViewById(R.id.convertButton);
        EditText editText = findViewById(R.id.userInput);

        textView.setTextSize((float) (FONT_SIZE * 1.5));
        convertButton.setTextSize(FONT_SIZE);
        editText.setTextSize(FONT_SIZE);

    }

}

