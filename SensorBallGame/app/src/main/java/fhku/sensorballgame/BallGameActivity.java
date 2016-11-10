package fhku.sensorballgame;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class BallGameActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;

    int ballSize = 0;
    int xMax = 0;
    int yMax = 0;

    View ball;
    View gameboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ball_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ball = findViewById(R.id.circle);
        gameboard = findViewById(R.id.gameboard);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        ballSize = (int) (size.x * 0.1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    public void updateBall(float x, float y) {
        float xNew = ball.getX() - x;
        float yNew = ball.getY() + y;

        if (xNew < 0) {
            ball.setX(0);
        } else if (xNew > xMax) {
            ball.setX(xMax);
        } else {
            ball.setX(xNew);
        }

        if (yNew < 0) {
            ball.setY(0);
        } else if (yNew > yMax) {
            ball.setY(yMax);
        } else {
            ball.setY(yNew);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (yMax + xMax <= 0) {
            yMax = gameboard.getHeight() - ballSize;
            xMax = gameboard.getWidth() - ballSize;
        }

        updateBall(sensorEvent.values[0], sensorEvent.values[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
