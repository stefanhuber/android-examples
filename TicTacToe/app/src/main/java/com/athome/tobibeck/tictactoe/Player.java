package com.athome.tobibeck.tictactoe;

/**
 * Created by tobibeck on 02.11.16.
 */

public interface Player {
    public void initializeMove(FieldState current);
    public void doMove(int x,int y);

    public void setOnTurn(boolean val);
    public boolean isOnTurn();

}
