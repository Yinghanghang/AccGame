package edu.sjsu.android.project3yanchen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.View;

public class MyView extends View implements SensorEventListener {

    // You can change the ball size if you want
    private static final int BALL_SIZE = 100;
    private Bitmap field;
    private Bitmap ball;
    private float XOrigin;
    private float YOrigin;
    private float horizontalBound;
    private float verticalBound;
    private final Particle mBall = new Particle();

    // Paint object is used to draw your name
    private Paint paint = new Paint();

    // TODO: set attributes (objects) needed for sensor
    // HINT: 2 of them are classes in the sensor framework
    //      1 is used for getting the rotation from "natural" orientation
    //      4 of them are used for the sensor data (3 axes + timestamp)

    public MyView(Context context) {
        super(context);

        // You will see errors here because there are no image files yet.
        // Add the images under drawable folder to get rid of the errors.
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        ball = Bitmap.createScaledBitmap(b, BALL_SIZE, BALL_SIZE, true);
        field = BitmapFactory.decodeResource(getResources(), R.drawable.field);

        // TODO: Initialize the objects related to the sensor except for sensor data
    }

    // TODO: set XOrigin, YOrigin, HorizontalBound and VerticalBound based on the screen size
    // HINT: there is a method in View class related to the size of the screen.
    //      The method's parameters include width and height of the screen.
    //      So override that method to set the 4 attributes.
    //      Also, you should createScaledBitmap for the basketball field,
    //      use the width and height of the screen in this method.

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // draw the field and the ball
        canvas.drawBitmap(field, 0, 0, null);
        canvas.drawBitmap(ball,
                (XOrigin - BALL_SIZE / 2f) + mBall.mPosX,
                (YOrigin - BALL_SIZE / 2f) - mBall.mPosY, null);
        // TODO: "draw" your name (make it easy to see)

        // TODO: control the ball based on the sensor data using methods in Particle class

        invalidate();
    }

    public void startSimulation() {
        // TODO: Register sensor event listener
    }

    public void stopSimulation() {
        // TODO: Unregister sensor event listener
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // TODO: get the sensor data (set them to the corresponding class attributes)
        // Remember to interpret the data as discussed in Lesson 14 page 16.
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}