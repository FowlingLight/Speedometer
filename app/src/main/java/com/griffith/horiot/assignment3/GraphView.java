package com.griffith.horiot.assignment3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.location.Location;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by horiot_b on 25/04/2016 for Code and Learn
 */
public class GraphView extends View {

    private Paint blue, red;
    private int size;
    private List<Location> locationList;

    private void init() {
        this.blue = new Paint();
        this.red = new Paint();

        this.blue.setColor(ContextCompat.getColor(getContext(), R.color.blue));
        this.red.setColor(ContextCompat.getColor(getContext(), R.color.red));

        this.blue.setStyle(Paint.Style.STROKE);
        this.blue.setStrokeWidth(3);

        this.red.setStyle(Paint.Style.STROKE);
        this.red.setStrokeWidth(4);
    }

    public GraphView(Context context) {
        super(context);
        this.init();
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public GraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    public void onDraw(Canvas canvas) {
        for (int y = 0; y < 6; y++) {
            canvas.save();
            canvas.drawLine(0, y * (this.size / 6), this.size, y * (this.size / 6), this.blue);
            canvas.restore();
        }
        canvas.save();
        canvas.drawLine(0, this.size - 1, this.size, this.size - 1, this.blue);

        if (this.locationList != null) {
            int i = 0;
            double prevSpeed = 0;

            for (Location l : this.locationList) {
                double realSpeed = l.getSpeed() * 3.6;

                if (realSpeed > 60) {
                    realSpeed = 60;
                }
                if (realSpeed < 0) {
                    realSpeed = 0;
                }

                if (i != 0) {
                    canvas.save();
                    canvas.drawLine((i - 1) * this.size / 99, (float)(this.size - (prevSpeed * this.size / 60)), i * this.size / 99, (float)(this.size - (realSpeed * this.size / 60)), this.red);
                    canvas.restore();
                }

                prevSpeed = realSpeed;
                i++;
            }
        }

        super.onDraw(canvas);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        this.size = width > height ? height : width;
        setMeasuredDimension(size, size);
    }

    public void updateLocationList(List<Location> locationList) {
        this.locationList = locationList;
        this.invalidate();
    }
}