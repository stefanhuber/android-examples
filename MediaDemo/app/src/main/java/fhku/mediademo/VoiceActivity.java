package fhku.mediademo;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VoiceActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    protected Button startButton;
    protected TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        startButton = (Button) findViewById(R.id.play_button);
        startButton.setOnClickListener(this);
        startButton.setEnabled(false);

        tts = new TextToSpeech(this, this);
    }

    @Override
    public void onClick(View v) {
        tts.speak("Hallo liebe Studenten, schlaft ihr schon?", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            this.startButton.setEnabled(true);
        }
    }
}
