package com.application.hashMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class HashMapTest {


    private ReentrantReadWriteLock   reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void testHashMap(){
        Object o = new Object();
        Map<Integer, TestEntity> simpleMap  = new HashMap<>();
        Map<Integer, TestEntity> simpleMapWithSync = new HashMap<>();
        Map<Integer, TestEntity> lockMap = new HashMap<>();
        Map<Integer, TestEntity> syncMap =Collections.synchronizedMap(new HashMap<>());
        Map<Integer,TestEntity> concurrentHashMap = new ConcurrentHashMap<>();
        testCurruption(simpleMap,1);
        testCurruption(simpleMapWithSync,2);
        testCurruption(lockMap,3);
        testCurruption(syncMap,1);
        testCurruption(concurrentHashMap,4);

    }


    public void testCurruption(Map<Integer, TestEntity> map, int event){
        List<Thread> listOfThreads = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int randNum = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    switch (event){
                            case 1:
                                for (int j = 0; j < 8000; j++) {
                                    TestEntity entity = new TestEntity(randNum, "Hello" + randNum);
                                    map.put(randNum, entity);
                                }
                            break;
                            case 2:
                                putToMap(randNum,map);
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

            thread.start();
            listOfThreads.add(thread);
        }
        for (Thread thread : listOfThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
