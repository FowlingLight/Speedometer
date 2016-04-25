package com.griffith.horiot.assignment3;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GPSTrackerView extends View {

    private Context context;
    private LocationManager lm;
    private List<Location> locationList;
    private MyLocationListener listener;

    private void init(Context context) {
        this.context = context;
        this.locationList = new ArrayList<>();
        this.listener = new MyLocationListener();
    }

    public GPSTrackerView(Context context) {
        super(context);
        this.init(context);
    }

    public GPSTrackerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    public GPSTrackerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context);
    }

    public void startGPS() {
        if (this.context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == PackageManager.PERMISSION_GRANTED) {
            this.lm = (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);
            this.lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this.listener);
        }
    }

    public void stopGPS() {
        if (this.context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == PackageManager.PERMISSION_GRANTED) {
            this.lm.removeUpdates(listener);
            this.lm = null;
        }
    }

    public List<Location> getLocationList() {
        return this.locationList;
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            locationList.add(location);
            if (locationList.size() == 101) locationList.remove(locationList.get(0));
            ((MainActivity) context).updateList();
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }
}
