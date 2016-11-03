package com.athome.tobibeck.tictactoe;

/**
 * Created by tobibeck on 22.10.16.
 */

public class FieldState {
    private int[][] gameField;

    int currentPlayer = 0; //1=O, 2=X //X=Computer
    int lastX = 0;
    int lastY = 0;


    FieldState(){
        this.gameField = new int[3][3];
        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
                //System.out.println(x+"."+y);
                gameField[x][y]=0;
            }
        }

        this.currentPlayer=1;
    }

    FieldState(FieldState fs,int player){
        this.gameField = fs.exportField();

        this.currentPlayer=player;
    }

    FieldState(FieldState fs){
        this.gameField = fs.exportField();

        if(fs.currentPlayer == 2){
            this.currentPlayer=1;
        } else {
            this.currentPlayer=2;
        }
        //System.out.printf("Current PLayer in this is "+currentPlayer);
    }


    public int[][] exportField(){
        int[][] ret = new int[3][3];
        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
                ret[x][y]=gameField[x][y];
            }
        }
        //ret[2][2]=4;
        //System.out.println("gf VAL: "+gameField[2][2]);
        //ret[2][2]=0;
        return ret;
    }

    public void setPlayer(int x,int y, int player){
        lastX = x;
        lastY = y;
        gameField[x][y]=player;
    }

   public int getWinner(){
        //Player O: -1;
        //Player X: +1;

        int[] rows = new int[8]; //r1,r2,r3,c1,c2,c3,d1,d2
        for(int i=0;i<8;i++){
            rows[i] = 0;
        }

        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
                if(gameField[x][y] != 0){
                    int val = 0;
                    switch(gameField[x][y]){
                        case 1:
                            val=-1;
                        break;
                        case 2:
                            val=1;
                        break;
                    }

                    rows[y]+=val;
                    rows[x+3]+=val;

                    if((x==0 && y==0) || (x==2 && y==2)){
                        rows[6]+=val;
                    }

                    if((x==0 && y==2) || (x==2 && y==0)){
                        rows[7]+=val;
                    }

                    if((x==1 && y==1)){
                        rows[7]+=val;
                        rows[6]+=val;
                    }
                }

            }

            for(int i =0;i<8;i++){
                if(rows[i]==3){
                   /* System.out.println("---------------------------------");
                    System.out.println("Winner would be 2 with "+i);
                    print();
                    System.out.println("---------------------------------");*/
                    return 2;
                } else if(rows[i]==-3){
                    /*System.out.println("---------------------------------");
                    System.out.println("Winner would be 1 with "+i);
                    print();
                    System.out.println("---------------------------------");*/
                    return 1;
                }

            }
        }

        return 0;

    }

    public int calculateLevel(){
        int winner = this.getWinner();
       // System.out.println("The Winner is: "+winner);
        if(winner==0){
            return 0;
        }else if(winner==2){
           return 10;
        } else {
            return -10;
        }
    }


    public void print(){
        for(int oy= 0;oy<3;oy++){
            String out = "";
            //System.out.println("╂───╂───╂───╂ \n");
            for(int ox = 0;ox<3;ox++){

                out+="│     "+gameField[ox][oy]+"    │";

            }
            System.out.println(out);
        }
    }

}
