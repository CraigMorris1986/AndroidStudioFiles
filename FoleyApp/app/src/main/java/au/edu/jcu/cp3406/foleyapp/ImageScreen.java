package au.edu.jcu.cp3406.foleyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageScreen extends AppCompatActivity {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_screen);
        image = findViewById(R.id.imageView);
        Intent intent = getIntent();
        String pressedButtonText = intent.getStringExtra("buttonName");
        displayImage(pressedButtonText);
    }

    private void displayImage(String buttonName) {
        switch (buttonName.toLowerCase()) {
            case "pistol":
                image.setImageResource(R.drawable.pistol);
                break;
            case "shotgun":
                image.setImageResource(R.drawable.shotgun);
                break;
            case "drive":
                image.setImageResource(R.drawable.cardrive);
                break;
            case "ignition":
                image.setImageResource(R.drawable.carkeys);
                break;
            case "giggle":
                image.setImageResource(R.drawable.babylaugh);
                break;
            case "baby_talk":
                image.setImageResource(R.drawable.babytalk);
                break;
            case "message":
                image.setImageResource(R.drawable.notification);
                break;
            case "mac":
                image.setImageResource(R.drawable.mac);
                break;
            case "pc":
                image.setImageResource(R.drawable.windows95);
                break;
            case "robot":
                image.setImageResource(R.drawable.hawking);
                break;
            case "pig":
                image.setImageResource(R.drawable.peppapig);
                break;
            case "whale":
                image.setImageResource(R.drawable.whale);
                break;

        }
    }
}
