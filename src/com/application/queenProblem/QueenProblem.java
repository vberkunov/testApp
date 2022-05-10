package com.application.queenProblem;

import java.util.Arrays;
import java.util.stream.Stream;

public class QueenProblem {
    private static final int N = 8;
    private final int[][] chessBoard;
    private long time;



    public QueenProblem(){
        this.chessBoard = new int[N][N];
    }

    public  void solveProblem(){
        time = System.currentTimeMillis();
        for (int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                if (chessBoard[i][j] == 0) {
                    if (checkPoint( i, j)) {
                        chessBoard[i][j] = 1;
                        solveProblem();
                    }
                    if (sumOfQueens(chessBoard) == N) {
                        return ;
                    }
                    chessBoard[i][j] = 0;
                }
            }
        }
    }

    private   boolean checkPoint(int y,int x) {

        for (int i = 0; i < N; i++) {
            if (chessBoard[y][i] == 1) {
                return false;
            }
        }
        for (int i = 0; i < N; i++) {
            if (chessBoard[i][x] == 1) {
                return false;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (chessBoard[i][j] == 1) {
                    if (Math.abs(i - y) == Math.abs(j - x)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private   long sumOfQueens(int[][]arr){
        return Stream.of(arr).map(i-> Arrays.stream(i).filter(j->j==1).count()).reduce(Long::sum).get();

    }

    public void printArr(){
        Arrays.stream(chessBoard).forEach((i) -> {
            Arrays.stream(i).forEach((j) -> System.out.print(j + " "));
            System.out.println();
        });
        System.out.println("");

    }


}
