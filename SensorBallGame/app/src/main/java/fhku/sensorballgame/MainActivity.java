package fhku.sensorballgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button ballGame;
    Button sensorLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ballGame = (Button) findViewById(R.id.button_ball_game);
        sensorLog = (Button) findViewById(R.id.button_sensor_log);
    }

    public void start(View view) {
        if (ballGame.equals(view)) {
            startActivity(new Intent(this, BallGameActivity.class));
        } else if (sensorLog.equals(view)) {
            startActivity(new Intent(this, SensorLogActivity.class));
        }
    }


}
