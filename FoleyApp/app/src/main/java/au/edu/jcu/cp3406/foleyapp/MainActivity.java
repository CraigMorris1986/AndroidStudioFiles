package au.edu.jcu.cp3406.foleyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View view) {
        Intent intent = new Intent(this, ImageScreen.class);
        int buttonId = view.getId();
        Button button = findViewById(buttonId);
        String buttonText = (String) button.getText();
        intent.putExtra("buttonName", buttonText);

        startActivity(intent);
    }
}
