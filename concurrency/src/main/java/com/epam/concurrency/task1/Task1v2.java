package com.epam.concurrency.task1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1v2 {

  Map<Integer, Integer> map = new HashMap<>();
  static AtomicInteger counter = new AtomicInteger(1);

  public static void main(String[] args) throws InterruptedException {
    Task1v2 task1 = new Task1v2();

    new Thread(task1::operation1, "T1").start();
    new Thread(task1::operation2, "T2").start();
  }

  public void operation1() {
      while (true) {
        map.put(counter.incrementAndGet(), counter.get());
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(counter.get());
      }
    }

  public void operation2() {
      while (true) {
        int sum = 0;
        Iterator<Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
          Entry<Integer, Integer> entry = it.next();
          int key = entry.getKey();
          int value = entry.getValue();
          sum += value;
          if (value > 10) {
            map.remove(key);
          }
        }
        System.out.println("sum: " + sum);
      }
    }
}
