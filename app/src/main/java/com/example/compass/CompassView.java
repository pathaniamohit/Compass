package com.example.compass;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CompassView extends View {

    private Paint circlePaint;
    private Paint needlePaint;
    private Paint textPaint;
    private float bearing;

    public CompassView(Context context) {
        super(context);
        init();
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(10);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLACK);

        needlePaint = new Paint();
        needlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        needlePaint.setStrokeWidth(5);
        needlePaint.setAntiAlias(true);
        needlePaint.setColor(Color.RED);

        textPaint = new Paint();
        textPaint.setTextSize(60);
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setBearing(float bearing) {
        this.bearing = bearing;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(centerX, centerY) - 20;

        canvas.drawCircle(centerX, centerY, radius, circlePaint);

        canvas.save();
        canvas.rotate(-bearing, centerX, centerY);

        // Draw compass needle
        canvas.drawLine(centerX, centerY, centerX, centerY - radius, needlePaint);
        canvas.drawLine(centerX, centerY, centerX, centerY + radius / 2, needlePaint);

        // Draw cardinal directions
        canvas.drawText("N", centerX, centerY - radius + 80, textPaint);
        canvas.drawText("S", centerX, centerY + radius - 40, textPaint);
        canvas.drawText("E", centerX + radius - 40, centerY + 20, textPaint);
        canvas.drawText("W", centerX - radius + 40, centerY + 20, textPaint);

        canvas.restore();
    }
}
