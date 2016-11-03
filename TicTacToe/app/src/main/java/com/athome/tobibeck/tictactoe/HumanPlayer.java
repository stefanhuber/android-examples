package com.athome.tobibeck.tictactoe;

/**
 * Created by tobibeck on 02.11.16.
 */

public class HumanPlayer implements Player {

    GameController g;

    private boolean onTurn;

    public HumanPlayer(GameController g){
        this.g = g;
    }


    @Override
    public void initializeMove(FieldState current) {
        //does nothing
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
