package com.application;


import com.application.knightsTourProblem.KnightsTour;
import com.application.magicSquare.MagicSquare;
import com.application.queenProblem.QueenProblem;
import com.application.queenProblem.QueenProblemBaB;

public class Main {

    public static void main(String[] args) {
        System.out.println("Simple Queen Problem solution");
        QueenProblem queenProblem = new QueenProblem();
        queenProblem.printArr();
        double time1 = System.currentTimeMillis();
        queenProblem.solveProblem();
        queenProblem.printArr();
        System.out.println("Execution Time: "+ ((System.currentTimeMillis() - time1)/1000)+ "s" + '\n');

        System.out.println("Branch&Bound Queen Problem solution");
        QueenProblemBaB queenProblemBaB = new QueenProblemBaB();
        queenProblemBaB.printArr();
        double time2 = System.currentTimeMillis();
        queenProblemBaB.solveProblem();
        queenProblemBaB.printArr();
        System.out.println("Execution Time: "+ ((System.currentTimeMillis() - time2)/1000) + "s" +'\n');

        System.out.println("Knights Tour Problem solution");
        KnightsTour knightsTour = new KnightsTour();
        knightsTour.printArr();
        double time3 = System.currentTimeMillis();
        knightsTour.solveProblem();
        knightsTour.printArr();
        System.out.println("Execution Time: "+ ((System.currentTimeMillis() - time3)/1000) + "s" +'\n');

        System.out.println("Magic Square Problem solution");
        MagicSquare square = new MagicSquare();
        square.generate();
        square.printArr();

    }




}





