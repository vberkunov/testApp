package com.application;


import com.application.knightsTourProblem.KnightsTour;
import com.application.magicSquare.MagicSquare;
import com.application.queenProblem.QueenProblem;

public class Main {

    public static void main(String[] args) {
        System.out.println("Simple Queen Problem solution");
        QueenProblem queenProblem = new QueenProblem();
        queenProblem.solveProblem(0);


        System.out.println("Knights Tour Problem solution");
        KnightsTour knightsTour = new KnightsTour();
        double time3 = System.currentTimeMillis();
        knightsTour.solveProblem();
        knightsTour.printArr();
        System.out.println("Execution Time: "+ ((System.currentTimeMillis() - time3)/1000) + "s" +'\n');

        System.out.println("Magic Square Problem solution");
        MagicSquare square = new MagicSquare();
        System.out.println("Write solution to file with name solution.txt");
        square.writeToDirectory("solution",square.solve(10));




    }




}





