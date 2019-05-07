package au.edu.jcu.cp3406.spiritlevel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensorGravity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
//        for (Sensor sensor:
//             deviceSensors) {
//            Log.i("tag", sensor.toString());
//        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.i("tag", String.format("x= %f y= %f z= %f", event.values[0], event.values[1], event.values[2]));
        @SuppressLint("DefaultLocale") String values = String.format("x= %f\n y= %f\n z= %f", event.values[0], event.values[1], event.values[2]);
        TextView display = findViewById(R.id.displayText);
        display.setText(values);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something if accuracy changes.
    }
    @Override
    protected void onResume() {
        // this requires implementation to initially register the listener event else it will never call
        super.onResume();
        sensorManager.registerListener(this, sensorGravity, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
