package com.athome.tobibeck.tictactoe;

import android.os.Handler;

/**
 * Created by tobibeck on 22.10.16.
 */

public class GameController{
    FieldState currentState = null;

    Player p1;
    Player p2;

    Player currentPlayer;

    GameActivity m = null;

    public GameController(GameActivity m,boolean twoPlayer){
        this.m = m;

        this.currentState = new FieldState();

        if(twoPlayer){
            p1=new HumanPlayer(this);
            p2=new HumanPlayer(this);
        } else {
            p1=new HumanPlayer(this);
            p2=new ComputerPlayer(this);
        }

        this.currentPlayer=p1;
    }

    public void doMove(int x,int y){
        if(this.currentState.exportField()[x][y]==0) {
            FieldState f = new FieldState(currentState);
            f.setPlayer(x, y, currentState.currentPlayer);

            m.buttons[x][y].setText((currentState.currentPlayer == 1 ? "O" : "X"));
            this.currentState = f;
            //currentState.print();

            this.m.drawField();

            this.triggerAfterMove();
        }


    }

    public void doComputerMove(){
        FieldPath fp = new FieldPath(currentState);
        //System.out.println("current player:"+this.currentState.currentPlayer);
        FieldState best = fp.bestPossibility();
        //System.out.println("Ideal position: "+best.lastX+"-"+best.lastY);
        this.doMove(best.lastX,best.lastY);

    }

    public void triggerAfterMove(){

        boolean end = false;

        if(this.currentState.getWinner()!=0){
            final GameController h = this;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    h.m.triggerWinnerMessage(h.currentState.getWinner());
                }
            }, 500);
            end = true;
        } else{
            FieldPath p = new FieldPath(new FieldState(currentState));
            p.calcNextLevel();

            if(p.toArray().length==0){
                final GameController h = this;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        h.m.triggerWinnerMessage(0);
                    }
                }, 500);
                end = true;
            }
        }

        if(end==false){
            if(currentState.currentPlayer==1){
                this.currentPlayer = this.p1;
            } else {
                this.currentPlayer = this.p2;
            }
            this.currentPlayer.initializeMove(currentState);
        } else {
            /*final GameController h = this;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    h.setupField();
            }
            }, 2000);
            */


        }



    }

    public void setupField(){
        this.currentState = new FieldState();
        this.m.drawField();
    }


}
