package com.example.motion;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager != null) {
            Sensor proxi = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            if (proxi != null) {
                sensorManager.registerListener(this, proxi, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if( sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            ((TextView)findViewById(R.id.text1)).setText("Proxi: "+ sensorEvent.values[0]);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}