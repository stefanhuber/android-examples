package com.athome.tobibeck.tictactoe;

import java.util.LinkedList;

/**
 * Created by tobibeck on 22.10.16.
 */

public class FieldPath extends LinkedList<FieldPath>{

    FieldState stateStart;
    FieldState selected;
    int sum;


    FieldPath(FieldState start){
        super();
        this.stateStart = start;
        this.selected = null;

    }

    public void calcNextLevel(){
        int pOnTurn = stateStart.currentPlayer;

        int[][] field = stateStart.exportField();

        for(int x= 0;x<3;x++){
            for(int y = 0;y<3;y++){
                //System.out.println("("+x+"-"+y+")="+field[x][y]);
                if(field[x][y]==0) {
                    //System.out.println("Field is empty");
                    FieldState f = new FieldState(stateStart);
                    f.setPlayer(x,y,pOnTurn);
                    this.add(new FieldPath(f));
                    /*System.out.println("-----------------");
                    System.out.println("POSSIBILITY");
                    System.out.println("-----------------");
                    f.print();*/
                }

            }
        }



    }
          //x,y
    public FieldState bestPossibility() {
        this.calcNextLevel();
        FieldPath best=null;
        int bestLevel = 0;

        FieldPath[] nexts = this.toArray(new FieldPath[0]);
        //System.out.println("Found next Level: "+nexts.length);
        best = nexts[0];
        for(int i=0;i<nexts.length;i++){
            /*System.out.println("-----------------");
            System.out.println("ONE WAY");
            System.out.println("-----------------");*/
            int sum = nexts[i].calculateSum();
            //System.out.println("Possitiblity: "+nexts[i].stateStart.lastX+"-"+nexts[i].stateStart.lastY+"="+sum);
           // nexts[i].stateStart.print();
            if(sum>bestLevel){
                best = nexts[i];
                bestLevel = sum;
            }
        }
        //System.out.println("BEST:"+best.stateStart.lastX+"-"+best.stateStart.lastY);
       // best.stateStart.print();
        return best.stateStart;


    }

    public int calculateSum(){
        int th = stateStart.calculateLevel();

        if(th==0){
            this.calcNextLevel();
            FieldPath[] fps = this.toArray(new FieldPath[0]);
            //System.out.println("fps.length="+fps.length);
            int[] scores = new int[fps.length];
            if(fps.length!=0) {
                for (int i = 0; i < fps.length; i++) {
                    int s = fps[i].calculateSum();
                    scores[i] = s;
                }

                if (this.stateStart.currentPlayer == 1) {
                    int lastMin = 0;
                    int minID = 0;
                    for (int i = 0; i < fps.length; i++) {
                        if (scores[i] < lastMin) {
                            minID = i;
                        }
                    }
                    this.selected = fps[minID].stateStart;
                    return scores[minID];
                }

                if (this.stateStart.currentPlayer == 2) {
                    int lastMax = 0;
                    int maxID = 0;
                    for (int i = 0; i < fps.length; i++) {
                        if (scores[i] > lastMax) {
                            maxID = i;
                        }
                    }

                    this.selected = fps[maxID].stateStart;
                    return scores[maxID];
                }
            } else {
                return 0;
            }
        }


        return th;

    }



}



/*
 int sum=stateStart.calculateLevel();
        int currentPlayer = stateStart.currentPlayer;

        if(sum==0){
            //System.out.println("NextLEvel:SUM ZERO!");
            this.calcNextLevel();
            FieldPath[] a = this.toArray(new FieldPath[0]);
            if(currentPlayer==2){
                int max = -10;
                for(int i=0;i<a.length;i++){
                    int ns = a[i].calculateSum();
                    //System.out.println("ns="+ns);
                    if(ns>max){
                        //System.out.println("Selected");
                        max = ns;
                    }
                }
                //System.out.println("Searched Max: "+max);
                return max;
            }

            if(currentPlayer==1){
                int min = 10;
                for(int i=0;i<a.length;i++){
                    int ns = a[i].calculateSum();
                    //System.out.println("ns="+ns);
                    if(ns<min){
                        //System.out.println("Selected");
                        min = ns;
                    }
                }
               //System.out.println("Searched min: "+min);
                return min;
            }
        }
       //System.out.println(stateStart.lastX+"-"+stateStart.lastY+" is Not Zero: "+sum);
            return sum;
 */