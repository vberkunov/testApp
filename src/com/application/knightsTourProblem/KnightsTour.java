package com.application.knightsTourProblem;

import java.util.Arrays;

public class KnightsTour {
    private final static int N = 8;

    private final static int moveX[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
    private final static int moveY[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
    private final int [][] chessBoard;

    public KnightsTour(){
        this.chessBoard = new int[N][N];
    }

    public boolean solveProblem(){
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                chessBoard[x][y] = -1;
            }
        }
        chessBoard[0][0] = 0;

        if(!checkPoint(0,0,1)){
          return false;
        }
        return true;
    }


    private boolean checkPoint(int x, int y, int num){
        int yNew , xNew;

        if(num == N*N){
            return true;

        }
        for (int i = 0; i < N ; i++) {
            yNew = y + moveY[i];
            xNew = x + moveX[i];

            if (isSafe(xNew, yNew)) {
                chessBoard[xNew][yNew] = num;
                if (checkPoint(xNew, yNew, num + 1)) {
                    return true;
                } else {

                    chessBoard[xNew][yNew] = -1;
                }
            }
        }
        return false;
    }
    private boolean isSafe(int x, int y)
    {

        return  (x >= 0 && x < N && y >= 0 && y < N
                && chessBoard[x][y] == -1) ;

    }

    public void printArr(){
        Arrays.stream(chessBoard).forEach((i) -> {
            Arrays.stream(i).forEach((j) -> System.out.print(j + " "));
            System.out.println();
        });
        System.out.println("");

    }
}
