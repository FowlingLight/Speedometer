package com.griffith.horiot.assignment3;

import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button)findViewById(R.id.tracker_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.getText().toString().equals(getResources().getString(R.string.tracker_off))) button.setText(getResources().getString(R.string.tracker_on));
                else button.setText(getResources().getString(R.string.tracker_off));
            }
        });

    }

    public void location() {
        LocationManager lm = new LocationManager();

    }
}
