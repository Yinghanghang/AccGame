package edu.sjsu.android.project3yingyingzhao;

import static android.view.Surface.ROTATION_0;
import static android.view.Surface.ROTATION_90;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;

public class MyView extends View implements SensorEventListener {

    // You can change the ball size if you want
    private static final int BALL_SIZE = 200;
    private Bitmap field;
    private Bitmap ball;
    private Bitmap f;
    private float XOrigin;
    private float YOrigin;
    private float horizontalBound;
    private float verticalBound;
    private final Particle mBall = new Particle();

    // Paint object is used to draw your name
    private Paint paint = new Paint();

    private SensorManager manager;
    private Sensor acc;
    int rotation;
    float x, y, z;
    long time;

    public MyView(Context context) {
        super(context);

        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        ball = Bitmap.createScaledBitmap(b, BALL_SIZE, BALL_SIZE, true);
        f = BitmapFactory.decodeResource(getResources(), R.drawable.field);

        // Initialize the objects related to the sensor
        manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            acc = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        // Override onLayout() to set XOrigin, YOrigin, HorizontalBound and VerticalBound based on the screen size
    }

    @Override
    //Called when the view should render its content.
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // draw the field and the ball
        canvas.drawBitmap(field, 0, 0, null);

        paint.setColor(Color.WHITE);
        paint.setTextSize(75);
        canvas.drawText("Yingying Zhao", 300, 350, paint);

        // control the ball based on the sensor data
        mBall.updatePosition(x, y, z, time);
        mBall.resolveCollisionWithBounds(horizontalBound, verticalBound);
        canvas.drawBitmap(ball,
                (XOrigin - BALL_SIZE / 2f) + mBall.mPosX,
                (YOrigin - BALL_SIZE / 2f) - mBall.mPosY, null);
        //If the view's appearance may need to be changed, the view will call invalidate().
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        horizontalBound = (right - left) / 2 - BALL_SIZE /2f;
        verticalBound = (bottom - top) / 2 - BALL_SIZE / 2f;
        XOrigin = (right - left) / 2 ;
        YOrigin =  (bottom - top) / 2 ;
//        Log.i("Print", "left" + left);
//        Log.i("Print", "top" + top);
//        Log.i("Print", "right" + right);
//        Log.i("Print", "bottom" + bottom);
        field = Bitmap.createScaledBitmap(f, right - left, bottom - top, true);
    }

    public void startSimulation() {
        // Register sensor event listener
        if(acc != null) {
            manager.registerListener(this, acc, SensorManager.SENSOR_DELAY_UI);
        }
}

    public void stopSimulation() {
        // Unregister sensor event listener
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // get the sensor data
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if (rotation== ROTATION_0) {
                x = sensorEvent.values[0];
                y = sensorEvent.values[1];
                z = sensorEvent.values[2];
                time = System.nanoTime();
            } else if (rotation == ROTATION_90) {
                x = sensorEvent.values[1];
                y = sensorEvent.values[0];
                z = sensorEvent.values[2];
                time = System.nanoTime();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}