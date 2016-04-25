package com.griffith.horiot.assignment3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by horiot_b on 25/04/2016 for Code and Learn
 */
public class GPSTrackerView extends View {

    Context context;

    public GPSTrackerView(Context context) {
        super(context);
        this.context = context;
    }

    public GPSTrackerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public GPSTrackerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

}
