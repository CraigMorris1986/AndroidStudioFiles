package au.edu.jcu.cp3406.foleyapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class ImageScreen extends AppCompatActivity {
    ImageView image;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_screen);
        image = findViewById(R.id.imageView);
        Intent intent = getIntent();
        final String pressedButtonText = intent.getStringExtra("buttonName");
        displayImage(pressedButtonText);

        if (pressedButtonText.toLowerCase().equals("pistol")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.pistol);
        } else if (pressedButtonText.toLowerCase().equals("shotgun")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.shotgun);
        } else if (pressedButtonText.toLowerCase().equals("drive")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.cardrive);
        } else if (pressedButtonText.toLowerCase().equals("ignition")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.carstart);
        } else if (pressedButtonText.toLowerCase().equals("giggle")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.kidlaugh);
        } else if (pressedButtonText.toLowerCase().equals("baby_talk")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.kidtalk);
        } else if (pressedButtonText.toLowerCase().equals("message")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.notification);
        } else if (pressedButtonText.toLowerCase().equals("mac")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.macstart);
        } else if (pressedButtonText.toLowerCase().equals("pc")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.pcstart);
        } else if (pressedButtonText.toLowerCase().equals("robot")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.pcspeak);
        } else if (pressedButtonText.toLowerCase().equals("pig")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.pig);
        } else if (pressedButtonText.toLowerCase().equals("whale")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.whale);
        }


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });
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
