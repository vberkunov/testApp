package com.application.magicSquare.multithread;

public class MagicWorker implements Runnable{
    private int[][]solution;
    private int n;
    private int num;

    public MagicWorker( int n, int num) {

        this.solution = new int[n][n];
        this.n = n;
        this.num = num;
    }

    public int[][] getSolution() {
        return solution;
    }

    @Override
    public void run() {
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j <n ; j++) {
                switch (num){
                    case 1:
                        solution[i][j] = i+1;
                        break;
                    case 2:
                        solution[i][j] = j+1;
                        break;

                }


            }
        }
    }
}
