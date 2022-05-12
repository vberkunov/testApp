package com.application.magicSquare;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class MagicSquare {

    public MagicSquare() {
    }

    // []-> [] - decrement i and increment j
    // if num exist -> j-2 i+1
    // if i == -1 && j==n -> i=0, j = n-2
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
//    ----------------
//    |       |       |
//    |    A  |   B   |
//    |------ |-------|
//    |    C  |   D   |
//    |       |       |
//     ---------------

    public int[][] generateMagicSinglyEven(int n){
        if(n==2){
            System.out.println("No magic square with n =2");
        }
        int[][] square=new int[n][n];
        int[][] quarter;
        quarter=generateMagicSOdd(n/2);
        //If we partition the square into four 55 squares and put the numbers 1,2,...,25
        // in the upper left-hand square by the Siam method, the numbers 26,27,..,50
        // in the lower right-hand square, then numbers 51,52,...,75 in the upper right-hand square and,
        // finally, the numbers 76,77,...,100 in the lower left-hand square, then we get the square below.
        for(int i=0;i<n/2;i++)
            for(int j=0;j<n/2;j++){
                square[i][j]=quarter[i][j];
                square[n/2+i][n/2+j]=quarter[i][j]+(n*n)/4;
                square[i][n/2+j]=quarter[i][j]+(n*n)/2;
                square[n/2+i][j]=quarter[i][j]+3*(n*n)/4;
            }

        //the columns sum to the magic number 505 but the rows do not
        //we need to do first is exchange the corresponding elements in the upper and lower squares in the first and tenth columns.
        // Then in column two we exchange corresponding elements in the upper and lower squares except for rows three and eight.

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

    public int[][] solve(int n){
       int[][]square;
        if(n%2!=0) {
            square = generateMagicSOdd(n);
        }
        else {
            square = generateMagicSinglyEven(n);

        }
        printArr(square);
        return square;
    }

    public void printArr(int[][] square){
        Arrays.stream(square).forEach((i) -> {
            Arrays.stream(i).forEach((j) -> System.out.print(j + " "));
            System.out.println();
        });
        System.out.println("");

    }

    public  void writeToDirectory (String filename, int[][]x)  {
        String filePath = "C:\\Users\\vital\\Reader\\test\\"+filename+".txt";
        BufferedWriter outputWriter = null;
        try {
            outputWriter = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < x.length; i++) {
                for (int j =0; j< x.length; j++) {
                    outputWriter.write(x[i][j] + " ");

                }
                outputWriter.newLine();
            }
            outputWriter.flush();
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


        }





