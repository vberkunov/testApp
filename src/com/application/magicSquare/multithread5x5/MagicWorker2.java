package com.application.magicSquare.multithread5x5;

public class MagicWorker2 implements Runnable {
    private int[][] solution;
    private int[][] arrI;
    private int[][] arrJ;
    private int n;
    private int num;

    public MagicWorker2(int[][] arrI, int[][] arrJ, int n, int num) {

        this.solution = new int[n][n];
        this.arrI = arrI;
        this.arrJ = arrJ;
        this.n = n;
        this.num = num;
    }

    public int[][] getSolution() {
        return solution;
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                switch (num) {
                    case 1:
                        solution[i][j] = (arrI[i][j] + arrJ[i][j] + 1) % n;
                        break;
                    case 2:
                        solution[i][j] = (arrI[i][j]+(2*arrJ[i][j])-2)%n;
                        break;
                    case 3:
                        solution[i][j] = n* arrI[i][j] + arrJ[i][j]+1;
                        break;

                }


            }
        }
    }
}
