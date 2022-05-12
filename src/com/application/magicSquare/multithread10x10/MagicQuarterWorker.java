package com.application.magicSquare.multithread10x10;

import java.util.Arrays;

public class MagicQuarterWorker implements Runnable {
    private int[][] solution;
    private int[][] quarter;
    private int n;
    private int num;

    public MagicQuarterWorker(int[][] quarter, int n, int num) {
        this.solution = new int[n][n];
        this.quarter = quarter;
        this.n = n;
        this.num = num;
    }

    public int[][] getSolution() {
        return solution;
    }

    @Override
    public void run() {
        for (int i = 0; i < n / 2; i++)
            for (int j = 0; j < n / 2; j++) {
                switch (num) {
                    case 1:
                        solution[i][j] = quarter[i][j];
                        break;
                    case 2:
                        solution[n / 2 + i][n / 2 + j] = quarter[i][j] + (n * n) / 4;
                        break;
                    case 3:
                        solution[i][n / 2 + j] = quarter[i][j] + (n * n) / 2;
                        break;
                    case 4:
                        solution[n / 2 + i][j] = quarter[i][j] + 3 * (n * n) / 4;
                        break;
                }

            }


    }
}
