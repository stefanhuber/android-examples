package fhku.mediademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button recordActivity;
    Button voiceActivity;
    Button cameraActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.recordActivity = (Button) findViewById(R.id.button_record_activity);
        this.recordActivity.setOnClickListener(this);

        this.voiceActivity = (Button) findViewById(R.id.button_tts_activity);
        this.voiceActivity.setOnClickListener(this);

        this.cameraActivity = (Button) findViewById(R.id.button_camera_activity);
        this.cameraActivity.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(this.recordActivity)) {
            startActivity(new Intent(this, RecordActivity.class));
        } else if (v.equals(this.voiceActivity)) {
            startActivity(new Intent(this, VoiceActivity.class));
        } else if (v.equals(this.cameraActivity)) {
            startActivity(new Intent(this, CameraActivity.class));
        }
    }
}
