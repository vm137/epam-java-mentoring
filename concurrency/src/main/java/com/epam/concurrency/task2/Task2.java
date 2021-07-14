package com.epam.concurrency.task2;

import java.util.ArrayList;
import java.util.List;

/**
 * Task 2 - Deadlocks
 *
 * Create three threads:
 * 1st thread is infinitely writing random number to the collection;
 * 2nd thread is printing sum of the numbers in the collection;
 * 3rd is printing square root of sum of squares of all numbers in the collection.
 * Make these calculations thread-safe using synchronization block. Fix the possible deadlock.
 */

public class Task2 {

  private final List<Integer> list = new ArrayList<>();

  public static void main(String[] args) {
    Task2 task = new Task2();

    new Thread(task::operation1, "T1").start();
    new Thread(task::operation2, "T2").start();
    new Thread(task::operation3, "T3").start();
  }

  public void operation1() {
    while (true) {
      synchronized (this) {
        list.add(getRandomNumber(1, 100));
      }
    }
  }

  public void operation2() {
    while (true) {
      synchronized (this) {
        long sum = list.stream()
            .reduce(0, Integer::sum);
        System.out.println("sum: " + sum);
      }
    }
  }

  public void operation3() {
    while (true) {
      synchronized (this) {
        double sum = list.stream()
            .reduce(0, (a, b) -> a + b * b);
        System.out.println("sqr: " + Math.sqrt(sum));
      }
    }
  }

  public int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }
}
