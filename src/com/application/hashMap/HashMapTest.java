package com.application.hashMap;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class HashMapTest {


    private ReentrantReadWriteLock   reentrantReadWriteLock = new ReentrantReadWriteLock();
@Test(timeout = 20)
    public void testHashMap() throws InterruptedException {
        Map<Integer, TestEntity> simpleMap  = new HashMap<>();
        Map<Integer, TestEntity> simpleMapWithSync = new HashMap<>();
        Map<Integer, TestEntity> lockMap = new HashMap<>();
        Map<Integer, TestEntity> syncMap =Collections.synchronizedMap(new HashMap<>());
        Map<Integer,TestEntity> concurrentHashMap = new ConcurrentHashMap<>();
        testCurruption(simpleMap,1);
      //  testCurruption(simpleMapWithSync,2);
      //  testCurruption(lockMap,3);
      //  testCurruption(syncMap,1);
      //  testCurruption(concurrentHashMap,4);

    }


    public void testCurruption(Map<Integer, TestEntity> map, int event) throws InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(30);
        double time3 = System.currentTimeMillis();
        for (int i = 0; i < 30; i++) {
            int randNum = i;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    switch (event) {
                        case 1:
                            for (int j = 0; j < 50000; j++) {
                                TestEntity entity = new TestEntity(randNum, "Hello" + randNum);
                                map.put(randNum, entity);
                            }
                            break;
                        case 2:
                            putToMap(randNum, map);
                            break;
                        case 3:
                            methodThatModifiesMap(randNum, map);
                            break;
                        case 4:
                            for (int j = 0; j < 8000; j++) {
                                TestEntity entity = new TestEntity(randNum, "Hello" + randNum);
                                map.putIfAbsent(randNum, entity);
                            }
                            break;


                    }


                }
            });

        }
        System.out.println("Execution Time: "+ ((System.currentTimeMillis() - time3)) + "s" +'\n');
        pool.shutdown();
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        System.out.println("Size of  map: " + map.size());
    }



    synchronized void putToMap(int randNum,Map<Integer,TestEntity> syncMap){
        for (int j = 0; j < 8000; j++) {
            TestEntity entity = new TestEntity(randNum, "Hello" + randNum);
            syncMap.put(randNum, entity);
        }
    }


    private void methodThatModifiesMap(int randNum,Map<Integer,TestEntity> lockMap) {
        for (int j = 0; j < 8000; j++) {
            TestEntity entity = new TestEntity(randNum, "Hello" + randNum);
            reentrantReadWriteLock.writeLock().lock();
            try {
                lockMap.put(randNum, entity);
            } finally {

                reentrantReadWriteLock.writeLock().unlock();
            }
        }
    }
}
