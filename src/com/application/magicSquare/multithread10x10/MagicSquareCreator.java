package com.application.magicSquare.multithread10x10;

import java.util.Arrays;

public class MagicSquareCreator {
    public MagicSquareCreator() {
    }

    public int[][] createMagicSinglyEven() {
        int n =10;
        int[][] square = new int[n][n];
        int[][] quarter;
        quarter = generateMagicSOdd(n / 2);
        MagicQuarterWorker worker1 = new MagicQuarterWorker(quarter,10,1);
        Thread thread1 = new Thread(worker1);
        MagicQuarterWorker worker2 = new MagicQuarterWorker(quarter,10,2);
        Thread thread2 = new Thread(worker2);
        MagicQuarterWorker worker3 = new MagicQuarterWorker(quarter,10,3);
        Thread thread3 = new Thread(worker3);
        MagicQuarterWorker worker4 = new MagicQuarterWorker(quarter,10,4);
        Thread thread4 = new Thread(worker4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <n ; i++) {
            for (int j = 0; j < n; j++) {
                square[i][j] = worker1.getSolution()[i][j]+worker4.getSolution()[i][j]
                        +worker3.getSolution()[i][j]+worker2.getSolution()[i][j];
            }

        }


        int k=(n-1)/4;

        for(int i=0;i<k;i++){
            for(int j=0;j<n/2;j++){
                int temp=square[j][i];
                square[j][i]=square[j+n/2][i];
                square[j+n/2][i]=temp;
                if((i+1)<k){
                    temp=square[j][n-i-1];
                    square[j][n-i-1]=square[j+n/2][n-i-1];
                    square[j+n/2][n-i-1]=temp;
                }
            }
        }

        //undo the unneccessary swap
        int temp=square[n/4][k-1];  // 2,4
        square[n/4][k-1]=square[3*n/4][k-1]; // 7 4
        square[3*n/4][k-1]=temp;

        // Then we exchange elements in rows three and eight in column three to get the following magic square of order 10.
        temp=square[n/4][k];
        square[n/4][k]=square[3*n/4][k];
        square[3*n/4][k]=temp;


        return square;


    }


    public int[][] generateMagicSOdd(int n){
        int[][] square=new int[n][n];
        int i = n / 2;
        int j = n - 1;

        for (int num = 1; num <= n * n;) {
            if (i == -1 && j == n) {
                j = n - 2;
                i = 0;
            }
            else {

                if (j == n) {
                    j = 0;
                }
                if (i < 0) {
                    i = n - 1;
                }
            }

            if (square[i][j] != 0) {
                j -= 2;
                i++;
                continue;
            }
            else {

                square[i][j] = num++;
            }


            j++;
            i--;
        }
        return square;
    }

    public void printArr(int[][] square){
        Arrays.stream(square).forEach((i) -> {
            Arrays.stream(i).forEach((j) -> System.out.print(j + " "));
            System.out.println();
        });
        System.out.println("");

    }
}
