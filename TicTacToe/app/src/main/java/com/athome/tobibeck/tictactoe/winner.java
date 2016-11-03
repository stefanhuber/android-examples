package com.athome.tobibeck.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class winner extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        Intent i = getIntent();
        TextView t = (TextView) findViewById(R.id.winner);

        if (i.getIntExtra("winner",0)==0) {
            t.setText("Unentschieden");
        } else {
            t.setText("Spieler "+i.getIntExtra("winner",0));
        }
    }


    @Override
    public void onClick(View v) {
        finish();
    }
}
