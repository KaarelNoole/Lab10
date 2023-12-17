package com.nagel.lab10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

//TODO: documentation Sensors https://developer.android.com/guide/topics/sensors/sensors_overview
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private TextToSpeech  toSpeech;
    private TextView txtLeft, txtMiddel, txtRight;
    private SensorViewAdapter sensorViewAdapter;
    private float accelerationValue;
    private float accelarationLast;
    private float shake;

    private final String[] fortunes = new String[]{
            "As I see it, yes.", "Ask again later.", "Better not tell you now.", "Cannot predict now.", "Concentrate and ask again.",
            "Don’t count on it.", "It is certain.", "It is decidedly so.", "Most likely.", "My reply is no.", "My sources say no.",
            "Outlook not so good.", "Outlook good.", "Reply hazy, try again.", "Signs point to yes.", "Very doubtful.", "Without a doubt.",
            "Yes.", "Yes – definitely.", "You may rely on it."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Shake device for fortune!", Toast.LENGTH_LONG).show();
        txtLeft = findViewById(R.id.txtLeft);
        txtMiddel = findViewById(R.id.txtMiddle);
        txtRight = findViewById(R.id.txtRight);

        toSpeech = new TextToSpeech(getApplicationContext(), i -> {
           if (i == TextToSpeech.SUCCESS){
               toSpeech.setLanguage(Locale.getDefault());
           }
        });

        setupSensor();

        listAllSensor();
    }

    private void listAllSensor() {
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        RecyclerView list = findViewById(R.id.sensorList);
        list.setLayoutManager(new LinearLayoutManager(this));
        sensorViewAdapter = new SensorViewAdapter(this,deviceSensors);
        list.setAdapter(sensorViewAdapter);
    }

    private void setupSensor() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerationValue = SensorManager.GRAVITY_EARTH;
        accelarationLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] values = sensorEvent.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
        Log.i("SensorTAG",getString(R.string.sensor_value,x,y,z));
        updateTextView(x);
        getFortune(x,y,z);
    }

    private void getFortune(float x, float y, float z) {

    }

    private void updateTextView(float x) {
        TextView[] views = {txtLeft,txtMiddel,txtRight};
        for (TextView text: views){
            text.setBackgroundColor(Color.WHITE);
        }
        if (x >= 5) txtRight.setBackgroundColor(getColor(R.color.gray_700));
        else if (x <= 5) txtMiddel.setBackgroundColor(getColor(R.color.gray_700));
        else txtLeft.setBackgroundColor(getColor(R.color.gray_700));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}