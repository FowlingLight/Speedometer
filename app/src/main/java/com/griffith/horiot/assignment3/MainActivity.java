package com.griffith.horiot.assignment3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    GPSTrackerView gpsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button)findViewById(R.id.tracker_button);
        gpsView = (GPSTrackerView) findViewById(R.id.gps_tracker);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.getText().toString().equals(getResources().getString(R.string.tracker_off))) {
                    button.setText(getResources().getString(R.string.tracker_on));
                    gpsView.startGPS();
                } else {
                    button.setText(getResources().getString(R.string.tracker_off));
                    gpsView.stopGPS();
                    //gpsView.getLocationManager().setTestProviderEnabled(LocationManager.GPS_PROVIDER, false);
                }
            }
        });

    }

    public void updateList() {
        GraphView graph = (GraphView) findViewById(R.id.graph_view);
        graph.updateLocationList(gpsView.getLocationList());
    }

}
