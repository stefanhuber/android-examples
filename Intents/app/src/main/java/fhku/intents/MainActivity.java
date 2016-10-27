package fhku.intents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonExplicitIntent;
    Button buttonImplicitIntent;
    Button buttonForResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.buttonExplicitIntent = (Button) findViewById(R.id.button_explicit_intent);
        this.buttonExplicitIntent.setOnClickListener(this);

        this.buttonImplicitIntent = (Button) findViewById(R.id.button_implicit_intent);
        this.buttonImplicitIntent.setOnClickListener(this);

        this.buttonForResult = (Button) findViewById(R.id.button_for_result);
        this.buttonForResult.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_camera:
                this.onClick(this.buttonForResult);
                return true;
            case R.id.item_explicit_intent:
                this.onClick(this.buttonExplicitIntent);
                return true;
            case R.id.item_implicit_intent:
                this.onClick(this.buttonImplicitIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {
        if (buttonExplicitIntent.equals(v)) {
            this.startActivity(new Intent(this, ExplicitIntentActivity.class));
        } else if (buttonForResult.equals(v)) {
            this.startActivity(new Intent(this, ForResultActivity.class));
        } else if (buttonImplicitIntent.equals(v)) {
            Intent linkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.fh-kufstein.ac.at"));
            this.startActivity(Intent.createChooser(linkIntent, "Choose Browser"));
        }
    }
}
