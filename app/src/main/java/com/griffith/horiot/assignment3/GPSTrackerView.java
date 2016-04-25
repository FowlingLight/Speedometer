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

/**
 * Created by horiot_b on 25/04/2016 for Code and Learn
 */

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

    public LocationManager getLocationManager() {
        return this.lm;
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            // the location of the device has changed so update the textviews to reflect this
                    /*tv_lat.setText("Latitude: " + location.getLatitude());
                    tv_long.setText("Longitude: " + location.getLongitude());*/
            locationList.add(location);
            if (locationList.size() == 101) locationList.remove(locationList.get(0));
            ((MainActivity) context).updateList();
        }

        @Override
        public void onProviderDisabled(String provider) {
            // if GPS has been disabled then update the textviews to reflect this
                    /*if (provider == LocationManager.GPS_PROVIDER) {
                        tv_lat.setText(R.string.tv_lat_text);
                        tv_long.setText(R.string.tv_long_text);
                    }*/
        }

        @Override
        public void onProviderEnabled(String provider) {
            // if there is a last known location then set it on the textviews
            if (provider.equals(LocationManager.GPS_PROVIDER) &&
                    context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == PackageManager.PERMISSION_GRANTED) {
                Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (l != null) {
                    locationList.add(l);
                    if (locationList.size() == 101)
                        locationList.remove(locationList.get(0));
                    ((MainActivity) context).updateList();
                }
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }
}
