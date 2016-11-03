package com.athome.tobibeck.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity  implements View.OnClickListener{

    GameController c;

    Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        buttons = new Button[3][3];

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        gridLayout.setColumnCount(3);
        gridLayout.setColumnCount(3);
        for (int x=0;x<3;x++) {
            for(int y=0;y<3;y++) {
                Button b = new Button(this);
                b.setText("");
                b.setTag(x+"-"+y);
                b.setOnClickListener(this);
                b.setLayoutParams(gridLayout.getLayoutParams());
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();

                //params.height = GridLayout.LayoutParams.WRAP_CONTENT;
                //params.width = GridLayout.LayoutParams.WRAP_CONTENT;
                //params.rightMargin = 5;
                //params.topMargin = 5;
                //params.setGravity(Gravity.CENTER);
                params.columnSpec = GridLayout.spec(x);
                params.rowSpec = GridLayout.spec(y);
                buttons[x][y]=b;
                gridLayout.addView(b, params);
            }
        }

        Intent i = getIntent();

        if (i.getIntExtra("humans",1)==2) {
            c = new GameController(this,true);
        } else {
            c = new GameController(this,false);
        }


    }

    public void onClick(View v){
        if(c.currentPlayer instanceof HumanPlayer) {
            Button b = (Button) v;
            String[] pos = ((String) b.getTag()).split("-");
            int x = Integer.parseInt(pos[0]);
            int y = Integer.parseInt(pos[1]);

            c.doMove(x,y);
        }


    }

    public void drawField(){
        int[][] fields= c.currentState.exportField();

        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
                if(fields[x][y]==1){
                    this.buttons[x][y].setText("O");
                } else if(fields[x][y]==2){
                    this.buttons[x][y].setText("X");
                } else {
                    this.buttons[x][y].setText("");
                }
            }
        }
    }

    public void triggerWinnerMessage(int end){
        /*if(end==2){
            Toast toast = Toast.makeText(this, "You loose!", Toast.LENGTH_SHORT);
            toast.show();
        } else if(end==1){
            Toast toast = Toast.makeText(this, "You win!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(this, "It is a draw!", Toast.LENGTH_SHORT);
            toast.show();
        }*/
        Intent i = new Intent(this,winner.class);
        i.putExtra("winner",end);
        startActivity(i);
    }
}
