package com.nagel.lab10;

import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SensorViewAdapter{
  public static class SensorViewHolder extends RecyclerView.Adapter<SensorViewAdapter.SensorViewHolder>{

        private final List<Sensor> sensorList;
        private final LayoutInflater layoutInflater;



        private final TextView name;
        private final TextView type;
        private final TextView power;
        private final TextView range;

        public SensorViewHolder(@NonNull View itemView) {
            name = itemView.findViewById(R.id.txtSensorName);
            type = itemView.findViewById(R.id.txtSensorType);
            power = itemView.findViewById(R.id.txtSensorPower);
            range = itemView.findViewById(R.id.txtSensorRange);
        }

        @NonNull
        @Override
        public SensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.single_sensor,parent,false);
            return new SensorViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SensorViewHolder holder, int position) {
            Sensor sensor = sensorList.get(position);
            holder.name.setText(String.format("Name: %s",sensor.getName()));
            holder.type.setText(String.format("type: %s",sensorTypeToString(sensor.getType())));
            holder.power.setText(String.format("power: %s",sensor.getPower()));
            holder.range.setText(String.format("range: %s",sensor.getMaximumRange()));

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
    private static String sensorTypeToString(int sensorType) {
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                return "Accelerometer";
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
            case Sensor.TYPE_TEMPERATURE:
                return "Ambient Temperature";
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                return "Game Rotation Vector";
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                return "Geomagnetic Rotation Vector";
            case Sensor.TYPE_GRAVITY:
                return "Gravity";
            case Sensor.TYPE_GYROSCOPE:
                return "Gyroscope";
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                return "Gyroscope Uncalibrated";
            case Sensor.TYPE_HEART_RATE:
                return "Heart Rate Monitor";
            case Sensor.TYPE_LIGHT:
                return "Light";
            case Sensor.TYPE_LINEAR_ACCELERATION:
                return "Linear Acceleration";
            case Sensor.TYPE_MAGNETIC_FIELD:
                return "Magnetic Field";
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                return "Magnetic Field Uncalibrated";
            case Sensor.TYPE_ORIENTATION:
            case Sensor.TYPE_PRESSURE:
                return "Orientation";
            case Sensor.TYPE_PROXIMITY:
                return "Proximity";
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                return "Relative Humidity";
            case Sensor.TYPE_ROTATION_VECTOR:
                return "Rotation Vector";
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                return "Significant Motion";
            case Sensor.TYPE_STEP_COUNTER:
                return "Step Counter";
            case Sensor.TYPE_STEP_DETECTOR:
                return "Step Detector";
            default:
                return "Unknown " + sensorType;
        }
    }
}
