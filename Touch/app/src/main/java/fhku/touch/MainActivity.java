package fhku.touch;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    protected int pointerId = -1;
    protected float lastX = 0;
    protected float lastY = 0;

    Button button;

    protected GestureDetectorCompat gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button_touch);
        button.setOnTouchListener(this);

        gd = new GestureDetectorCompat(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                button.setX(0);
                button.setY(0);
                button.invalidate();

                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);
        this.gd.onTouchEvent(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i("TOUCH", "Acttion Down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("TOUCH", "Action Move");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("TOUCH", "Acoutn Up");
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int action = MotionEventCompat.getActionMasked(event);

        if (action == MotionEvent.ACTION_DOWN) {
            final int actionIndex = MotionEventCompat.getActionIndex(event);
            this.lastX = MotionEventCompat.getX(event, actionIndex);
            this.lastY = MotionEventCompat.getY(event, actionIndex);
            this.pointerId = MotionEventCompat.getPointerId(event, 0);
        } else if (action == MotionEvent.ACTION_MOVE) {
            final int actionIndex = MotionEventCompat.findPointerIndex(event, pointerId);

            final float x = MotionEventCompat.getX(event, actionIndex);
            final float y = MotionEventCompat.getY(event, actionIndex);

            // Calculate the distance moved
            final float dx = x - lastX;
            final float dy = y - lastY;

            button.setX(button.getX()+dx);
            button.setY(button.getY()+dy);

            this.lastX = x;
            this.lastY = y;
        } else if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            this.pointerId = -1;
        }



        return true;
    }
}
