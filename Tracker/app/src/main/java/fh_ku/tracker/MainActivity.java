package fh_ku.tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        if (view.getTag().equals("MAP")) {
            startActivity(new Intent(this, MapsActivity.class));
        } else {
            startActivity(new Intent(this, TrackerActivity.class));
        }
    }
}
