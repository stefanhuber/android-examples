package com.athome.tobibeck.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements ViewStub.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(this,GameActivity.class);
        Button b = (Button) findViewById(R.id.button);

        if(b.equals(v)){
            i.putExtra("humans",1);
        } else {
            i.putExtra("humans",2);
        }

        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        this.startActivity(i);
    }
}
