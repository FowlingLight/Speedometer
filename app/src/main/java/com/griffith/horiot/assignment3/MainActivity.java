package com.griffith.horiot.assignment3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    GPSTrackerView gpsView;
    GraphView graphView;
    TextView currentTime;
    TextView currentSpeed;
    TextView avgSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button)findViewById(R.id.tracker_button);
        gpsView = (GPSTrackerView) findViewById(R.id.gps_tracker);
        graphView = (GraphView) findViewById(R.id.graph_view);
        currentTime = (TextView) findViewById(R.id.current_time);
        currentSpeed = (TextView) findViewById(R.id.current_speed);
        avgSpeed = (TextView) findViewById(R.id.average_speed);

        currentTime.setText(getResources().getString(R.string.current_time) + " 0s");
        currentSpeed.setText(getResources().getString(R.string.current_speed) + ' ' + getResources().getString(R.string.na));
        avgSpeed.setText(getResources().getString(R.string.average_speed) + ' ' + getResources().getString(R.string.na));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.getText().toString().equals(getResources().getString(R.string.tracker_off))) {
                    button.setText(getResources().getString(R.string.tracker_on));
                    gpsView.startGPS();
                } else {
                    button.setText(getResources().getString(R.string.tracker_off));
                    gpsView.stopGPS();
                    currentSpeed.setText(getResources().getString(R.string.current_speed) + ' ' + getResources().getString(R.string.na));
                    avgSpeed.setText(getResources().getString(R.string.average_speed) +  ' ' + getResources().getString(R.string.na));
                }
            }
        });

    }

    public void updateList() {
        graphView.updateLocationList(gpsView.getLocationList());
        currentTime.setText(getResources().getString(R.string.current_time) + ' ' + graphView.getTimeCounter() / 60 + "min " + graphView.getTimeCounter() % 60 + 's');
        currentSpeed.setText(getResources().getString(R.string.current_speed) + ' ' + String.format("%.2f", graphView.getCurrentSpeed()) + ' ' + getResources().getString(R.string.km_h));
        avgSpeed.setText(getResources().getString(R.string.average_speed) + ' ' + String.format("%.2f", graphView.getAvgSpeed()) + ' ' + getResources().getString(R.string.km_h));
    }

}
