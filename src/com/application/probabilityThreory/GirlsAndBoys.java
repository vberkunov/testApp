package com.application.probabilityThreory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class GirlsAndBoys {

    public void solve(){
        System.out.println("Girls and Boys Problem");
        int girls = 6;
        int boys = 15;
        int num = girls+boys;
        int a =boys*(boys-1);
        int b = num*(num-1);
        float p = (float)(a)/(b);
        System.out.println("Probability:"+ p);

        List<BigInteger> nums = new ArrayList<>();
        nums.add(0, BigInteger.valueOf(4L));
        nums.add(1,BigInteger.valueOf(21L));
        int i = 2;
        BigInteger max = BigInteger.valueOf(3000000000000L);
        BigInteger next =BigInteger.valueOf(0);
        while (next.compareTo(max)==-1 ) {
            next = (nums.get(i-1).multiply(BigInteger.valueOf(6L))).subtract(nums.get(i-2).add(BigInteger.valueOf(2)));
            if(next.compareTo(max)==1){
                break;
            }
            System.out.println(next);
            print(next);
            nums.add(i,next);
            i++;
        }
        System.out.println("Solution count: " +nums.size());




    }

    public void print(BigInteger n){

        BigInteger D = BigInteger.valueOf(4).add((n.multiply(n.subtract(BigInteger.valueOf(1L))).multiply(BigInteger.valueOf(8))));



        if(D.compareTo(BigInteger.valueOf(0))==1) {


            BigInteger x1 = (D.sqrt().add(BigInteger.valueOf(2))).divide(BigInteger.valueOf(4));
            BigInteger x2 = (BigInteger.valueOf(2).subtract(D.sqrt())).divide(BigInteger.valueOf(4));
            if (x1.compareTo(BigInteger.valueOf(0))==1) {
                BigInteger girls1 = n.subtract(x1);
                System.out.println("Boys " + x1);
                System.out.println("Girls " + girls1);
                System.out.println("____________________");
            }
            if (x2.compareTo(BigInteger.valueOf(0))==1) {
                BigInteger girls2 = n.subtract(x2);
                System.out.println("Boys " + x2);
                System.out.println("Girls " + girls2);
            }
        }else {
            long x = 2/4;
        }
    }
}
