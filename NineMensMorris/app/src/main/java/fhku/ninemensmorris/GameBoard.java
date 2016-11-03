package fhku.ninemensmorris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GameBoard extends View {

    public static final String LOG_TAG = "GAME_BOARD";

    public GameBoard(Context context) {
        super(context);
    }
    public GameBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public GameBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public GameBoard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected int size;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        Log.i(LOG_TAG, "on measure: " + width + "/" + height);
        Log.i(LOG_TAG, "width mode: " + MeasureSpec.getMode(widthMeasureSpec));
        Log.i(LOG_TAG, "height mode: " + MeasureSpec.getMode(heightMeasureSpec));

        if (width > height) {
            width = height;
        } else {
            height = width;
        }

        size = width;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int square = size / 8;

        Log.i(LOG_TAG, "on draw: " + getWidth() + "/" + getHeight());

        Paint whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);

        for (int i = 0; i < 8; i++) {
            int alternation = i % 2;

            for (int j = 0; j < 4; j++) {
                int top    = i * square;
                int bottom = top + square;
                int left   = (alternation*square) + (j*2*square);
                int right  = left + square;

                canvas.drawRect(left,top,right,bottom,whitePaint);
            }
        }
    }
}
