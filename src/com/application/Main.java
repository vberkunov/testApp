package com.application;


import com.application.knightsTourProblem.KnightsTour;
import com.application.magicSquare.MagicSquare;
import com.application.queenProblem.QueenProblem;
import com.application.queenProblem.QueenProblemBaB;

public class Main {

    public static void main(String[] args) {
        System.out.println("Simple Queen Problem solution");
        QueenProblem queenProblem = new QueenProblem();
        queenProblem.solveProblem(0);

//        System.out.println("Branch&Bound Queen Problem solution");
//        QueenProblemBaB queenProblemBaB = new QueenProblemBaB();
//        queenProblemBaB.solveProblem();

        System.out.println("Knights Tour Problem solution");
        KnightsTour knightsTour = new KnightsTour();
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





