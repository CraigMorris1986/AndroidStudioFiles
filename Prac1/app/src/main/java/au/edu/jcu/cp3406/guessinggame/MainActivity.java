package au.edu.jcu.cp3406.guessinggame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Game game = new Game();
    int magicNumber = game.magicNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkGuess(View view) {
        Context context = getApplicationContext();
        String messageCorrect = "You have guessed correctly";
        String messageIncorrect = "Sorry try again";

        EditText text = (EditText) findViewById(R.id.textBox);
        String textString = text.getText().toString();
        // above two lines takes the EditText XML field string display value by ID and coverts to string

        if (Integer.parseInt(textString) == magicNumber) {
            Toast popUp = Toast.makeText(context, messageCorrect, Toast.LENGTH_SHORT);
            popUp.show();
        } else {
            Toast popUp = Toast.makeText(context, messageIncorrect, Toast.LENGTH_SHORT);
            popUp.show();
        }
    }
}
