package com.athome.tobibeck.tictactoe;

/**
 * Created by tobibeck on 02.11.16.
 */

public class ComputerPlayer implements Player {

    GameController g;

    private boolean onTurn;

    public ComputerPlayer(GameController g){
        this.g = g;
        onTurn = false;
    };

    @Override
    public void initializeMove(FieldState current) {
        FieldPath fp = new FieldPath(current);
        //System.out.println("current player:"+this.currentState.currentPlayer);
        FieldState best = fp.bestPossibility();
        //System.out.println("Ideal position: "+best.lastX+"-"+best.lastY);
        this.doMove(best.lastX,best.lastY);
    }

    @Override
    public void doMove(int x, int y) {

        this.g.doMove(x,y);

    }

    @Override
    public void setOnTurn(boolean val) {
        this.onTurn = val;
    }

    @Override
    public boolean isOnTurn() {
        return this.onTurn;
    }
}
