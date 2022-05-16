package com.application;


import com.application.hashMap.HashMapTest;
import com.application.knightsTourProblem.KnightsTour;
import com.application.magicSquare.MagicSquare;
import com.application.magicSquare.multithread10x10.MagicSquareCreator;
import com.application.magicSquare.multithread5x5.MagicWorker;
import com.application.magicSquare.multithread5x5.MagicWorker2;
import com.application.probabilityThreory.GirlsAndBoys;
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


        double time2 = System.currentTimeMillis();
        MagicWorker worker1 = new MagicWorker(5,1);
        Thread thread1 = new Thread(worker1);
        MagicWorker worker2 = new MagicWorker(5,2);
        Thread thread2 = new Thread(worker2);

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MagicWorker2 worker3 = new MagicWorker2(worker1.getSolution(),worker2.getSolution(),5,1);
        Thread thread3 = new Thread(worker3);
        MagicWorker2 worker4 = new MagicWorker2(worker1.getSolution(),worker2.getSolution(),5,2);
        Thread thread4 = new Thread(worker4);

        thread3.start();
        thread4.start();
        try {
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        MagicWorker2 worker5 = new MagicWorker2(worker3.getSolution(),worker4.getSolution(),5,3);
        Thread thread5 = new Thread(worker5);

        thread5.start();
        try {
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <5 ; i++) {
            for (int j = 0; j <5 ; j++) {

                System.out.print(worker5.getSolution()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Execution Time: "+ ((System.currentTimeMillis() - time2)/10) + "s" +'\n');

        System.out.println("Magic Square Problem solution");
        double time4 = System.currentTimeMillis();
        MagicSquare square = new MagicSquare();
        square.solve(10);
        System.out.println("Execution Time: "+ ((System.currentTimeMillis() - time4)/10) + "s" +'\n');

        double time5 = System.currentTimeMillis();
        MagicSquareCreator creator = new MagicSquareCreator();
        creator.printArr(creator.createMagicSinglyEven());
        System.out.println("Execution Time: "+ ((System.currentTimeMillis() - time5)/10) + "s" +'\n');

        GirlsAndBoys f = new GirlsAndBoys();
        f.solve();

        System.out.println("HashMap Corruption");
        HashMapTest test = new HashMapTest();
        test.testHashMap();

    }




}





