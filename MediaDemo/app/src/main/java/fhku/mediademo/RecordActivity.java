package fhku.mediademo;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.audiofx.PresetReverb;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener {

    final static String LOG_TAG = "AUDIO_RECORDING";

    final static String START = "START";
    final static String STOP  = "STOP";

    final static String STATE_RECORDING = "recording";
    final static String STATE_PLAYING = "playing";
    final static String STATE_IDLE = "idle";

    Button buttonRecording;
    Button buttonPlaying;

    protected String filePath = "audio_recording.3gp";
    protected MediaRecorder mediaRecorder = null;
    protected MediaPlayer mediaPlayer = null;
    protected FileDescriptor fd = null;
    protected File file = null;

    protected String state = STATE_IDLE;
    protected boolean hasAudio = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        buttonRecording = (Button) findViewById(R.id.button_start_recording);
        buttonRecording.setOnClickListener(this);

        buttonPlaying = (Button) findViewById(R.id.button_start_playing);
        buttonPlaying.setOnClickListener(this);

        try {
            file = new File(getFilesDir(), filePath);

            if (!file.exists()) {
                file.createNewFile();
            }

            if (file.length() > 0) {
                hasAudio = true;
            }

            FileOutputStream fos = new FileOutputStream(file);
            fd = fos.getFD();
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getLocalizedMessage());
        }

    }

    protected void startRecording() {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder = new MediaRecorder();

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(fd);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getLocalizedMessage());
        }

        mediaRecorder.start();

        buttonRecording.setText(R.string.button_stop_recording);
        buttonRecording.setTag(STOP);
        state = STATE_RECORDING;
    }

    protected void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;

        buttonRecording.setText(R.string.button_start_recording);
        buttonRecording.setTag(START);
        state = STATE_IDLE;
        hasAudio = true;
    }

    protected void startPlaying() {
        mediaPlayer = new MediaPlayer();

        try {

            mediaPlayer.setDataSource(getFilesDir() + "/" + filePath);
            mediaPlayer.prepare();
            Log.i(LOG_TAG, "Duration: " + mediaPlayer.getDuration());

            mediaPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getLocalizedMessage());
        }

        buttonPlaying.setText(R.string.button_stop_playing);
        buttonPlaying.setTag(STOP);
        state = STATE_PLAYING;
    }

    protected void stopPlaying() {
        mediaPlayer.release();
        mediaPlayer = null;

        buttonPlaying.setText(R.string.button_start_playing);
        buttonPlaying.setTag(START);
        state = STATE_IDLE;
    }

    @Override
    public void onClick(View v) {

        if (v.equals(buttonRecording) && v.getTag().equals(START)) {
            if (state.equals(STATE_PLAYING)) stopPlaying();
            startRecording();
        } else if (v.equals(buttonRecording) && v.getTag().equals(STOP)) {
            stopRecording();
        } else if (v.equals(buttonPlaying) && v.getTag().equals(START)) {
            if (state.equals(STATE_RECORDING)) stopRecording();
            startPlaying();
        } else if (v.equals(buttonPlaying) && v.getTag().equals(STOP)) {
            stopPlaying();
        }
    }
}
