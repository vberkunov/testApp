package com.application.queenProblem;

import java.util.Arrays;

public class QueenProblemBaB {
    private static final int N = 8;
    private final int[][] chessBoard;
    private final int[]   rows;
    private final int[][] mainDiagonal;
    private final int[]   mainDiagonalHelper;
    private final int[][] secondaryDiagonal;
    private final int[]   secondaryDiagonalHelper;


    public QueenProblemBaB() {
        this.chessBoard = new int[N][N];
        this.rows = new int[N];
        this.mainDiagonal = new int[N][N];
        this.mainDiagonalHelper = new int[2*N-1];
        this.secondaryDiagonal = new int[N][N];
        this.secondaryDiagonalHelper = new int[2*N-1];

    }

    public boolean solveProblem(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mainDiagonal[i][j] = i + j;
                secondaryDiagonal[i][j] = i - j + 7;
            }
        }

        if (!checkPoint(0))
        {
            return false;
        }
        return true;
    }

    public boolean checkPoint(int col){
        if (col >= N) {
            return true;
        }

        for (int j = 0; j < N; j++)
        {

            if ( isSafe(j, col) )
            {

                chessBoard[j][col] = 1;
                rows[j] = 1;
                mainDiagonalHelper[mainDiagonal[j][col]] = 1;
                secondaryDiagonalHelper[secondaryDiagonal[j][col]] = 1;


                if ( checkPoint( col+1) )
                    return true;
                chessBoard[j][col] = 0;
                rows[j] = 0;
                mainDiagonalHelper[mainDiagonal[j][col]] = 0;
                secondaryDiagonalHelper[secondaryDiagonal[j][col]] = 0;
            }
        }
        return false;
    }


    private boolean isSafe(int row, int col)
    {

        if (mainDiagonalHelper[mainDiagonal[row][col]]==1 ||
                secondaryDiagonalHelper[secondaryDiagonal[row][col]]==1 ||
                rows[row]==1) {
            return false;
        }
        return true;
    }

    public void printArr(){
        Arrays.stream(chessBoard).forEach((i) -> {
            Arrays.stream(i).forEach((j) -> System.out.print(j + " "));
            System.out.println();
        });
        System.out.println("");


    }
}
