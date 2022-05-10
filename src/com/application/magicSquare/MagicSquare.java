package com.application.magicSquare;

import java.util.Arrays;

public class MagicSquare {
    private final static int N = 7;
    private final int [][] square;

    public MagicSquare() {
        this.square = new int[N][N];
    }
    // []-> [] - decrement i and increment j
    // if num exist -> j-2 i+1
    // if i == -1 && j==n -> i=0, j = n-2
    public void generate(){

        int i = N / 2;
        int j = N - 1;

        for (int num = 1; num <= N * N;) {
            if (i == -1 && j == N) {
                j = N - 2;
                i = 0;
            }
            else {

                if (j == N) {
                    j = 0;
                }
                if (i < 0) {
                    i = N - 1;
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

    }

    public void printArr(){
        Arrays.stream(square).forEach((i) -> {
            Arrays.stream(i).forEach((j) -> System.out.print(j + " "));
            System.out.println();
        });
        System.out.println("");

    }


        }





