package au.edu.jcu.cp3406.stopwatchapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean isRunning = false;
    Handler handler = new Handler();
    Stopwatch stopwatch = new Stopwatch();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView display = findViewById(R.id.display);
        Button toggle = findViewById(R.id.startButton);
        if (savedInstanceState == null) {
            stopwatch = new Stopwatch();
        } else {
            stopwatch.seconds = savedInstanceState.getInt("seconds");
            stopwatch.minutes = savedInstanceState.getInt("minutes");
            stopwatch.hours = savedInstanceState.getInt("hours");
            isRunning = savedInstanceState.getBoolean("isRunning");
            runStopwatch();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isRunning", isRunning);
        outState.putInt("seconds", stopwatch.seconds);
        outState.putInt("minutes", stopwatch.minutes);
        outState.putInt("hours", stopwatch.hours);
    }

    public void enableStopwatch(View view) {
        isRunning = true;
        runStopwatch();

    }

    public void disableStopwatch(View view) {
        isRunning = false;
        Button button = findViewById(R.id.startButton);
        button.setEnabled(true);
    }

    public void resetStopwatch(View view) {
        isRunning = false;
        stopwatch.seconds = 0;
        stopwatch.minutes = 0;
        stopwatch.hours = 0;
        Button button = findViewById(R.id.startButton);
        button.setEnabled(true);
    }

    public void runStopwatch() {
        final TextView textView = findViewById(R.id.display);
        final Button button = findViewById(R.id.startButton);
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    stopwatch.tick();
                    button.setEnabled(false);
                    textView.setText(stopwatch.toString());

                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
