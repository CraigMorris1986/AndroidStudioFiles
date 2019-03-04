package au.edu.jcu.cp3406.guessinggame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkGuess(View view) {
        Context context = getApplicationContext();
        CharSequence message = "Checking";
        Toast popUp = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        popUp.show();
    }
}
