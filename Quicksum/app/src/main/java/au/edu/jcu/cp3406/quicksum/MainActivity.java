package au.edu.jcu.cp3406.quicksum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sets the initial text view on app creation
        TextView textView = findViewById(R.id.sum);
        textView.setText(String.valueOf(sum));
    }

    /**
     * Handler for button clicked event to caculate the sum of the value of the button pressed in the app.
     *
     * @param view display object passed as argument
     */
    public void buttonClicked(View view) {
        Button button = (Button) view;
        int buttonNumber = Integer.parseInt(button.getText().toString());
        sum += buttonNumber;

        TextView textView = findViewById(R.id.sum);
        String result = "" + sum;
        textView.setText(result);
    }

    /**
     * Resets the text display for app to zero and resets the sum variable to int of value 0.
     *
     * @param view display object passed as argument
     */
    public void resetQuicksum(View view) {
        sum = 0;
        TextView textView = findViewById(R.id.sum);
        textView.setText(String.valueOf(0));

    }
}
